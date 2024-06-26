package com.smarttech.utils;

import com.smarttech.dto.page.PageRequest;
import com.smarttech.dto.page.PageResponse;
import lombok.experimental.UtilityClass;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.Tuple;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@UtilityClass
public class PagingUtil {

    public <R> Function<EntityManager, PageResponse<R>> paginate(String sqlNative, Map<String, Object> params, PageRequest pageRequest, Function<Tuple, R> rowMapper) {
        return entityManager -> {
            String sqlCount = new StringBuilder("SELECT COUNT(1) FROM (")
                    .append(sqlNative)
                    .append(" ) as counter")
                    .toString();
            Query query = entityManager.createNativeQuery(sqlNative, Tuple.class);
            Query queryCount = entityManager.createNativeQuery(sqlCount);
            params.forEach((key, value) -> {
                query.setParameter(key, value);
                queryCount.setParameter(key, value);
            });
            query.setFirstResult((pageRequest.getPage() - 1) * pageRequest.getPageSize());
            query.setMaxResults(pageRequest.getPageSize());
            BigInteger count = (BigInteger) queryCount.getSingleResult();
            List<Tuple> tupleList = query.getResultList();
            List<R> items = tupleList.stream()
                    .map(rowMapper)
                    .collect(Collectors.toList());
            return PageResponse.<R>builder()
                    .page(pageRequest.getPage())
                    .pageSize(pageRequest.getPageSize())
                    .total(count.intValue())
                    .items(items)
                    .build();
        };

    }

    public List<Object> getPages(int currentPage, int lastPage) {
        int _x = 2;
        Integer x = null;
        int start = currentPage - _x;
        int end = currentPage + _x + 1;

        List<Object> rangeWithDots = new ArrayList<>();
        for (int i = 1; i <= lastPage; i++) {
            if (i == 1 || i == lastPage || (i >= start && i < end)) {
                if (Objects.nonNull(x)) {
                    if (i - x == 2) {
                        rangeWithDots.add(x + 1 + "");
                    } else if (i - x != 1) {
                        rangeWithDots.add("...");
                    }
                }
                rangeWithDots.add(i);
                x = i;
            }
        }

        return rangeWithDots;
    }
}
