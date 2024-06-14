package vn.edu.hcmuaf.fit.shopzonerestfulapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.model.AdminEntity;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.model.SellerEntity;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.model.UserEntity;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.repository.AdminRepository;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.repository.SellerRepository;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private final AdminRepository adminRepository;
    private final SellerRepository sellerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username);
        if (user != null) {
            return user;
        }

        AdminEntity admin = adminRepository.findByUsername(username);
        if (admin != null) {
            return admin;
        }

        SellerEntity seller = sellerRepository.findByUsername(username);
        if (seller != null) {
            return seller;
        }

        throw new UsernameNotFoundException("User not found with username: " + username);
    }

}
