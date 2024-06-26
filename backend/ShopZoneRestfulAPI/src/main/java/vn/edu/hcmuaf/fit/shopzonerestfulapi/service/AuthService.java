package vn.edu.hcmuaf.fit.shopzonerestfulapi.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.request.LoginRequest;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.request.RefreshTokenRequest;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.response.ApiResponse;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.response.AuthResponse;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.security.JwtTokenProvider;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService customUserDetailsService;

    public ApiResponse<AuthResponse> login(HttpServletRequest request, LoginRequest loginRequest) {
        request.getSession().removeAttribute("logout");
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        var user = customUserDetailsService.loadUserByUsername(loginRequest.getUsername());
        var token = jwtTokenProvider.generateToken(user);
        var refreshToken = jwtTokenProvider.generateRefreshToken(new HashMap<>(), user);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setToken(token);
        authResponse.setRefreshToken(refreshToken);
        return ApiResponse.<AuthResponse>builder()
                .code(200)
                .message("Login successfully")
                .result(authResponse)
                .build();
    }

    public ApiResponse<String> logout(HttpServletRequest request) {
        Boolean logout = (Boolean) request.getSession().getAttribute("logout");
        if (logout != null && logout) {
            return ApiResponse.<String>builder()
                    .code(400)
                    .result("You have already logged out!")
                    .build();
        }
        request.getSession().invalidate();
        SecurityContextHolder.clearContext();
        request.getSession().setAttribute("logout", true);
        return ApiResponse.<String>builder()
                .code(200)
                .result("Logout successfully")
                .build();
    }

    public ApiResponse<AuthResponse> refreshToken(RefreshTokenRequest refreshTokenRequest) {
        String username = jwtTokenProvider.extractUsername(refreshTokenRequest.getToken());
        var user = customUserDetailsService.loadUserByUsername(username);
        if (jwtTokenProvider.isValidateToken(refreshTokenRequest.getToken(), user)) {
            var token = jwtTokenProvider.generateToken(user);
            var refreshToken = jwtTokenProvider.generateRefreshToken(new HashMap<>(), user);

            AuthResponse authResponse = new AuthResponse();
            authResponse.setToken(token);
            authResponse.setRefreshToken(refreshToken);
            return ApiResponse.<AuthResponse>builder()
                    .code(200)
                    .message("Refresh token successfully")
                    .result(authResponse)
                    .build();
        }
        return ApiResponse.<AuthResponse>builder()
                .code(400)
                .message("Refresh token failed")
                .build();
    }
}
