package vn.edu.hcmuaf.fit.shopzonerestfulapi.services;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.models.AdminEntity;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.models.Role;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.models.SellerEntity;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.models.UserEntity;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.repositories.AdminRepository;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.repositories.SellerRepository;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.repositories.UserRepository;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;
    private SellerRepository sellerRepository;
    private AdminRepository adminRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (userRepository.existsByUsername(username)) {
            return loadUser(username);
        } else if (sellerRepository.existsByUsername(username)) {
            return loadSeller(username);
        } else if (adminRepository.existsByUsername(username)) {
            return loadAdmin(username);
        } else {
            throw new UsernameNotFoundException("Username not found");
        }
    }

    private UserDetails loadUser(String username) {
        UserEntity userEntity = userRepository.findByUsername(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new User(userEntity.getUsername(), userEntity.getPassword(), mapRolesToAuthorities(userEntity.getRoles()));
    }

    private UserDetails loadAdmin(String username) {
        AdminEntity adminEntity = adminRepository.findByUsername(username);
        if (adminEntity == null) {
            throw new UsernameNotFoundException("Admin not found");
        }
        return new User(adminEntity.getUsername(), adminEntity.getPassword(), mapRolesToAuthorities(adminEntity.getRoles()));
    }

    private UserDetails loadSeller(String username) {
        SellerEntity sellerEntity = sellerRepository.findByUsername(username);
        if (sellerEntity == null) {
            throw new UsernameNotFoundException("Seller not found");
        }
        return new User(sellerEntity.getUsername(), sellerEntity.getPassword(), mapRolesToAuthorities(sellerEntity.getRoles()));
    }

    private Collection<GrantedAuthority> mapRolesToAuthorities(Set<Role> roles) {
        return roles
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}
