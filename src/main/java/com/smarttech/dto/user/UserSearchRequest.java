package com.smarttech.dto.user;

import com.smarttech.constant.RoleConstant;
import com.smarttech.dto.page.PageRequest;
import lombok.Data;

@Data
public class UserSearchRequest extends PageRequest {
    private String email;
    private Boolean active;
    private String name;
    private RoleConstant role;
    private String currentUser;
}
