package com.smarttech.dto.user;

import com.smarttech.constant.RoleConstant;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
public class UserResponse extends User {
    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;
    private String email;
    private RoleConstant role;
    private Date createdDate;

    public UserResponse() {
        super(" ", "", new ArrayList<>());
    }

    public UserResponse(com.smarttech.entity.User user) {
        super(user.getEmail(), user.getPassword(), user.getActive(), true, true, true, roleToAuthority(user.getRole()));
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.phone = user.getPhone();
        this.address = user.getAddress();
        this.email = user.getEmail();
        this.role = user.getRole();
        this.createdDate = user.getCreatedDate();
    }

    public UserResponse(String username, boolean active, RoleConstant role) {
        super(username, "", active, true, true, true, roleToAuthority(role));
    }

    public static List<GrantedAuthority> roleToAuthority(RoleConstant...roles) {
        return Arrays.stream(roles)
                .map(RoleConstant::name)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    public UserResponse(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }
}
