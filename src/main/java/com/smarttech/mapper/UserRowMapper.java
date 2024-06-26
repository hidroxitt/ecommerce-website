package com.smarttech.mapper;

import com.smarttech.constant.RoleConstant;
import com.smarttech.dto.user.UserSearchResponse;
import lombok.experimental.UtilityClass;

import javax.persistence.Tuple;
import java.math.BigInteger;
import java.util.Date;
import java.util.function.Function;

@UtilityClass
public class UserRowMapper {

    public Function<Tuple, UserSearchResponse> userSearchResponseRowMapper() {
        return tuple -> {
            String email = tuple.get("email", String.class);
            String role = tuple.get("role", String.class);
            Boolean active = tuple.get("active", Boolean.class);
            UserSearchResponse userResponse = new UserSearchResponse(email, active, RoleConstant.valueOf(role));
            userResponse.setAddress(tuple.get("address", String.class));
            userResponse.setCreatedDate(tuple.get("createdDate", Date.class));
            userResponse.setFirstName(tuple.get("firstName", String.class));
            userResponse.setLastName(tuple.get("lastName", String.class));
            userResponse.setId(tuple.get("id", BigInteger.class).longValue());
            userResponse.setEmail(email);
            userResponse.setPhone(tuple.get("phone", String.class));
            return userResponse;
        };
    }
}
