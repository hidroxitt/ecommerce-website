package vn.edu.hcmuaf.fit.shopzonerestfulapi.services;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.request.LoginRequest;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.response.ApiResponse;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.response.AuthResponse;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.security.JwtTokenProvider;

@Service
@AllArgsConstructor
public class AuthService {
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;

    @Transactional
    public ApiResponse<AuthResponse> login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.generateToken(authentication);
        AuthResponse authResponse = new AuthResponse(token);
        return ApiResponse.<AuthResponse>builder()
                .code(200)
                .message("Login successful!")
                .result(authResponse)
                .build();
    }
}
