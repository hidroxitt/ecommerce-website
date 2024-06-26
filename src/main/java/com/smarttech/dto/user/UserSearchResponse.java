package com.smarttech.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.smarttech.constant.RoleConstant;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserSearchResponse extends UserResponse {

    private RoleConstant role;
    private Boolean active;

    public UserSearchResponse(String username, boolean active, RoleConstant role) {
        super(username, active, role);
        this.role = role;
        this.active = active;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return super.getPassword();
    }
}
