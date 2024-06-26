package com.smarttech.service;

import com.smarttech.constant.NotificationConstant;
import com.smarttech.dto.base.Result;
import com.smarttech.dto.page.PageResponse;
import com.smarttech.dto.user.*;
import com.smarttech.entity.User;
import com.smarttech.exception.ValidationException;
import com.smarttech.repository.UserRepository;
import com.smarttech.repository.custom.CustomsUserRepository;
import com.smarttech.service.notify.INotifyService;
import com.smarttech.service.notify.model.Notification;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    @Value("${spring.mail.username}")
    private String adminEmail;

    private final CustomsUserRepository customsUserRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Setter(onMethod_ = @Autowired, onParam_ = @Qualifier("MailNotifyService"))
    private INotifyService notifyService;

    @Override
    public UserResponse loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByEmail(username)
                .map(UserResponse::new)
                .orElseThrow(() -> new UsernameNotFoundException("Tên đăng nhập hoặc mật khẩu không chính xác"));
    }

    public User findByIdThrowIfNotPresent(Long id) {
        return this.userRepository.findById(id)
                .orElseThrow(() -> new ValidationException("User id không tồn tại: {0}", id));
    }

    @Transactional
    public Result<UserResponse> registerUser(UserRegisterRequest userRegisterRequest) {
        log.info("register user: {}", userRegisterRequest);
        User user = new User();
        if (userRegisterRequest.getId() != null) {
            user = this.findByIdThrowIfNotPresent(userRegisterRequest.getId());
            userRegisterRequest.setEmail(user.getEmail());
        } else {
            this.userRepository.findByEmail(userRegisterRequest.getEmail())
                    .filter(x -> !x.getId().equals(userRegisterRequest.getId()))
                    .ifPresent(u -> {
                        throw new ValidationException("user.email.valdiate.exist", u.getEmail());
                    });
        }

        if (Objects.nonNull(userRegisterRequest.getPhone())) {
            this.userRepository.findByPhone(userRegisterRequest.getPhone())
                    .filter(x -> !x.getId().equals(userRegisterRequest.getId()))
                    .ifPresent(u -> {
                        throw new ValidationException("user.phone.validate.exist", u.getPhone());
                    });
        }

        user.setEmail(userRegisterRequest.getEmail().toLowerCase());
        user.setActive(true);
        user.setRole(userRegisterRequest.getRole());
        user.setPassword(this.passwordEncoder.encode(userRegisterRequest.getPassword()));
        user.setFirstName(userRegisterRequest.getFirstName());
        user.setLastName(userRegisterRequest.getLastName());
        user.setPhone(userRegisterRequest.getPhone());
        user.setEmail(userRegisterRequest.getEmail());
        user.setAddress(userRegisterRequest.getAddress());
        user = this.userRepository.save(user);
        UserResponse userResponse = new UserResponse(user);
        if (userRegisterRequest.getId() == null) {
            this.sendEmail(user);
        }
        return Result.success(userResponse, "user.register.success");
    }

    public void sendEmail(User user) {
        Notification notification = new Notification();
        notification.setFrom(this.adminEmail);
        notification.setDestination(user.getEmail());
        notification.setTemplate(NotificationConstant.MailTemplate.REGISTER_ACC);
        notification.setTitle("[No-REPLY] Đăng ký tài khoản thành công.");
        notifyService.asyncSend(notification, additionalInfo -> {
            additionalInfo.put("fullName", user.getLastName() + " " + user.getFirstName());
        });
    }

    public PageResponse<UserSearchResponse> searchUser(UserSearchRequest userSearchRequest) {
        UserResponse currentUserThrowIfNot = this.getCurrentUserThrowIfNot();
        userSearchRequest.setCurrentUser(currentUserThrowIfNot.getEmail());
        return this.customsUserRepository.searchUser(userSearchRequest);
    }

    @Transactional
    public void changeStatus(Long id) {
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new ValidationException("user.id.validate.not-exist", id));
        user.setActive(!user.getActive());
        this.userRepository.save(user);
    }

    public UserResponse getCurrentUserThrowIfNot() {
        return Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .map(Authentication::getPrincipal)
                .filter(UserResponse.class::isInstance)
                .map(UserResponse.class::cast)
                .orElseThrow(() -> new ValidationException(""));
    }

    public void updateSelf(SelfUpdateRequest selfUpdateRequest) {
        UserResponse userResponse = this.getCurrentUserThrowIfNot();
        User u = this.findByIdThrowIfNotPresent(userResponse.getId());
        u.setAddress(selfUpdateRequest.getAddress());
        u.setPhone(selfUpdateRequest.getPhone());
        u.setFirstName(selfUpdateRequest.getFirstName());
        u.setLastName(selfUpdateRequest.getLastName());

        userResponse.setFirstName(u.getFirstName());
        userResponse.setLastName(u.getLastName());
        userResponse.setPhone(u.getPhone());
        userResponse.setAddress(u.getAddress());
        Authentication authentication = new UsernamePasswordAuthenticationToken(userResponse, userResponse.getPassword(), userResponse.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        this.userRepository.save(u);
    }

    public void updateUser(UserUpdateRequest request) {
        User user = this.userRepository.findById(request.getId())
                .orElseThrow(() -> new ValidationException(request.getId() + " không tìm thấy"));
        user.setAddress(request.getAddress());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPhone(request.getPhone());
        this.userRepository.save(user);
    }

    public void changePassword(ChangePasswordDTO changePassword) {
        UserResponse userResponse = this.getCurrentUserThrowIfNot();
        User u = this.findByIdThrowIfNotPresent(userResponse.getId());
        if (!this.passwordEncoder.matches(changePassword.getPassword(), u.getPassword())) {
            throw new ValidationException("Mật khẩu không chính xác");
        }
        String newPassword = this.passwordEncoder.encode(changePassword.getNewPassword());
        u.setPassword(newPassword);
        this.userRepository.save(u);
    }
}
