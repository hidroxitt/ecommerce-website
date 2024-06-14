package vn.edu.hcmuaf.fit.shopzonerestfulapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.request.ChangePasswordRequest;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.request.SignupRequest;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.model.AdminEntity;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.model.Role;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.repository.AdminRepository;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.repository.RoleRepository;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminEntity createAdmin(SignupRequest signupRequest) {
        AdminEntity adminEntity = new AdminEntity();
        adminEntity.setUsername(signupRequest.getUsername());
        adminEntity.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        adminEntity.setFullName(signupRequest.getFullName());
        adminEntity.setEmail(signupRequest.getEmail());
        adminEntity.setPhone(signupRequest.getPhone());

        Role role = roleRepository.findByName("ADMIN");
        adminEntity.setRole(Collections.singleton(role));

        return adminRepository.save(adminEntity);
    }

    public AdminEntity updateAdmin() {
        return null;
    }

    public  AdminEntity changePassword(ChangePasswordRequest changePasswordRequest) {
        return null;
    }

    public AdminEntity getAllAdmin() {
        return null;
    }

}
