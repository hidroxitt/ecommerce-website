package vn.edu.hcmuaf.fit.shopzonerestfulapi.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.request.LoginRequest;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.request.RegisterRequest;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.response.AuthenticationResponse;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.models.AdminEntity;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.models.Role;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.models.SellerEntity;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.models.UserEntity;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.repositories.AdminRepository;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.repositories.RoleRepository;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.repositories.SellerRepository;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.repositories.UserRepository;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.security.JwtTokenProvider;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private AdminRepository adminRepository;
    private SellerRepository sellerRepository;
    private RoleRepository roleRepository;
    private JwtTokenProvider jwtTokenProvider;
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.generateToken(authentication);
        return new ResponseEntity<>(new AuthenticationResponse(token), HttpStatus.OK);
    }

    @PostMapping("/register-user")
    public ResponseEntity<String> registerUser(@RequestBody RegisterRequest registerRequest) {
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(registerRequest.getUsername());
        userEntity.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        userEntity.setFullName(registerRequest.getFullName());
        userEntity.setEmail(registerRequest.getEmail());
        userEntity.setPhone(registerRequest.getPhone());

        Role role = roleRepository.findByName("USER");
        userEntity.setRoles(Collections.singleton(role));
        userRepository.save(userEntity);

        return new ResponseEntity<>("User registered successfully!", HttpStatus.OK);
    }

    @PostMapping("/register-admin")
    public ResponseEntity<String> registerAdmin(@RequestBody RegisterRequest registerRequest) {
        if (adminRepository.existsByUsername(registerRequest.getUsername())) {
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }

        AdminEntity adminEntity = new AdminEntity();
        adminEntity.setUsername(registerRequest.getUsername());
        adminEntity.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        adminEntity.setFullName(registerRequest.getFullName());
        adminEntity.setEmail(registerRequest.getEmail());
        adminEntity.setPhone(registerRequest.getPhone());

        Role role = roleRepository.findByName("ADMIN");
        adminEntity.setRoles(Collections.singleton(role));
        adminRepository.save(adminEntity);

        return new ResponseEntity<>("Admin registered successfully!", HttpStatus.OK);
    }

    @PostMapping("/register-seller")
    public ResponseEntity<String> registerSeller(@RequestBody RegisterRequest registerRequest) {
        if (sellerRepository.existsByUsername(registerRequest.getUsername())) {
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }

        SellerEntity sellerEntity = new SellerEntity();
        sellerEntity.setUsername(registerRequest.getUsername());
        sellerEntity.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        sellerEntity.setFullName(registerRequest.getFullName());
        sellerEntity.setEmail(registerRequest.getEmail());
        sellerEntity.setPhone(registerRequest.getPhone());

        Role role = roleRepository.findByName("SELLER");
        sellerEntity.setRoles(Collections.singleton(role));
        sellerRepository.save(sellerEntity);

        return new ResponseEntity<>("Seller registered successfully!", HttpStatus.OK);
    }
}
