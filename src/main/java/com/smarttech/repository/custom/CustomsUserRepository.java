package com.smarttech.repository.custom;

import com.smarttech.dto.dashboard.Chart;
import com.smarttech.dto.page.PageResponse;
import com.smarttech.dto.user.UserSearchRequest;
import com.smarttech.dto.user.UserSearchResponse;
import com.smarttech.mapper.UserRowMapper;
import com.smarttech.utils.PagingUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class CustomsUserRepository {

    private final Session entityManager;

    public String sqlSearchUser(UserSearchRequest userSearchRequest, Map<String, Object> param) {
        StringBuilder sqlBuilder = new StringBuilder("SELECT u.first_name as firstName, u.last_name as lastName")
                .append(" , u.role as role, u.address as address, u.created_date as createdDate")
                .append(" , u.id as id, u.email as email, u.phone as phone, u.active as active")
                .append(" FROM _user u WHERE 1 = 1");

        if (StringUtils.isNotBlank(userSearchRequest.getName())) {
            sqlBuilder.append(" AND (u.email LIKE :name OR u.first_name LIKE :name OR u.last_name LIKE :name)");
            param.put("name", "%" + userSearchRequest.getName() + "%");
        }

        if (Objects.nonNull(userSearchRequest.getRole())) {
            sqlBuilder.append(" AND u.role = :role");
            param.put("role", userSearchRequest.getRole().name());
        }

        if (Objects.nonNull(userSearchRequest.getActive())) {
            sqlBuilder.append(" AND u.active = :active");
            param.put("active", userSearchRequest.getActive());
        }

        if (StringUtils.isNotBlank(userSearchRequest.getCurrentUser())) {
            sqlBuilder.append(" AND u.email <> :email");
            param.put("email", userSearchRequest.getCurrentUser());
        }
        sqlBuilder.append(" ORDER BY u.created_date DESC");
        return sqlBuilder.toString();
    }

    public PageResponse<UserSearchResponse> searchUser(UserSearchRequest userSearchRequest) {
        Map<String, Object> param = new HashMap<>();
        String sql = this.sqlSearchUser(userSearchRequest, param);
        return PagingUtil.paginate(sql, param, userSearchRequest, UserRowMapper.userSearchResponseRowMapper())
                .apply(this.entityManager);
    }

    public List<Chart> statisticUser(Integer month, Integer year) {
        StringBuilder sqlBuilder = new StringBuilder("SELECT u.active, count(1) FROM _user u WHERE 1 = 1");
        Map<String, Object> param = new HashMap<>();
        if (Objects.nonNull(month)) {
            sqlBuilder.append(" AND MONTH(u.created_date) = :month");
            param.put("month", month);
        }

        if (Objects.nonNull(year)) {
            sqlBuilder.append(" AND YEAR(u.created_date) = :year");
            param.put("year", year);
        }
        sqlBuilder.append(" GROUP BY u.active");
        Query<Tuple> query = entityManager.createNativeQuery(sqlBuilder.toString(), Tuple.class);
        param.forEach(query::setParameter);
        Map<Boolean, BigInteger> result = query.getResultList()
                .stream()
                .collect(Collectors.toMap(tuple -> tuple.get(0, Boolean.class), tuple -> tuple.get(1, BigInteger.class)));
        BigInteger active = result.get(true);
        BigInteger inactive = result.get(false);
        return List.of(
                new Chart("Hoạt động", Objects.nonNull(active) ? active.toString() : "0"),
                new Chart("Không hoạt động", Objects.nonNull(inactive) ? inactive.toString() : "0")
        );
    }
}
