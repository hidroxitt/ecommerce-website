package com.smarttech.repository.custom;

import com.smarttech.constant.MethodPayment;
import com.smarttech.constant.OrderStatus;
import com.smarttech.constant.StatisticType;
import com.smarttech.dto.dashboard.Chart;
import com.smarttech.dto.dashboard.DashboardDTO;
import com.smarttech.dto.dashboard.DashboardRequest;
import com.smarttech.dto.order.OrderDetailResponse;
import com.smarttech.dto.order.OrderSearchRequest;
import com.smarttech.dto.page.PageResponse;
import com.smarttech.entity.Order;
import com.smarttech.entity.OrderDetail;
import com.smarttech.utils.PagingUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.Tuple;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class CustomOrderRepository {

    private final Session entityManager;

    public PageResponse<Order> searchOrder(OrderSearchRequest dataSearch) {
        StringBuilder sqlBuilder = new StringBuilder("SELECT * FROM _order o WHERE 1 = 1");
        Map<String, Object> params = new HashMap<>();

        if (StringUtils.isNotBlank(dataSearch.getCode())) {
            sqlBuilder.append(" AND o.code LIKE :code");
            params.put("code", "%" + dataSearch.getCode() + "%");
        }

        if (StringUtils.isNotBlank(dataSearch.getMethodPayment())) {
            sqlBuilder.append(" AND o.method_payment = :methodPayment");
            params.put("methodPayment", dataSearch.getMethodPayment());
        }

        if (StringUtils.isNotBlank(dataSearch.getPhone())) {
            sqlBuilder.append(" AND o.phone = :phone");
            params.put("phone", dataSearch.getPhone());
        }

        if (StringUtils.isNotBlank(dataSearch.getAddress())) {
            sqlBuilder.append(" AND o.address LIKE :address");
            params.put("address", "%" + dataSearch.getAddress() + "%");
        }

        if (StringUtils.isNotBlank(dataSearch.getCreatedBy())) {
            sqlBuilder.append(" AND o.created_by LIKE :createdBy");
            params.put("createdBy", "%" + dataSearch.getCreatedBy() + "%");
        }

        if (StringUtils.isNotBlank(dataSearch.getFromDate())) {
            sqlBuilder.append(" AND o.created_date >= :fromDate");
            params.put("fromDate", dataSearch.getFromDate());
        }

        if (StringUtils.isNotBlank(dataSearch.getToDate())) {
            sqlBuilder.append(" AND o.created_date <= :toDate");
            params.put("toDate", dataSearch.getToDate());
        }

        if (StringUtils.isNotBlank(dataSearch.getStatus())) {
            sqlBuilder.append(" AND o.status = :status");
            params.put("status", dataSearch.getStatus());
        }

        sqlBuilder.append(" ORDER BY o.created_date DESC");
        return PagingUtil.paginate(sqlBuilder.toString(), params, dataSearch, tuple -> {
            Order order = new Order();
            order.setCode(tuple.get("code", String.class));
            order.setAddress(tuple.get("address", String.class));
            order.setAdminNote(tuple.get("admin_note", String.class));
            order.setCreatedBy(tuple.get("created_by", String.class));
            order.setCreatedDate(tuple.get("created_date", Date.class));
            order.setFullName(tuple.get("full_name", String.class));
            order.setMethodPayment(MethodPayment.valueOf(tuple.get("method_payment", String.class)));
            order.setNote(tuple.get("note", String.class));
            order.setPhone(tuple.get("phone", String.class));
            order.setStatus(OrderStatus.valueOf(tuple.get("status", String.class)));
            order.setTotal(tuple.get("total", BigDecimal.class));
            order.setUserNote(tuple.get("user_note", String.class));
            return order;
        })
            .apply(this.entityManager);
    }

    public List<OrderDetailResponse> findDetailByOrderId(int orderId) {
        String sql = new StringBuilder("SELECT od, p.name as name, p.code as productCode, img.url as image, pd.size as size, cp.name as color")
                .append(" FROM OrderDetail od")
                .append("  JOIN ProductDetail pd ON pd.id = od.productDetailId")
                .append("  JOIN ColorProduct cp ON cp.id = pd.colorProductId")
                .append("  JOIN Product p ON p.id = pd.productId")
                .append("  JOIN Image img ON img.objectId = p.id AND img.type = 'PRODUCT'")
                .append(" WHERE od.orderId = :orderId")
                .toString();
        Query<Tuple> query = entityManager.unwrap(Session.class).createQuery(sql, Tuple.class);
        query.setParameter("orderId", orderId);
        List<Tuple> resultList = query.getResultList();
        return resultList.stream()
                .map(tuple -> {
                    OrderDetail orderDetail = tuple.get(0, OrderDetail.class);
                    OrderDetailResponse orderDetailResponse = new OrderDetailResponse();
                    orderDetailResponse.setCost(orderDetail.getCost());
                    orderDetailResponse.setProductDetailId(orderDetail.getProductDetailId());
                    orderDetailResponse.setDiscount(orderDetail.getPercentDiscount());
                    orderDetailResponse.setOrderId(orderDetail.getOrderId());
                    orderDetailResponse.setId(orderDetail.getId());
                    orderDetailResponse.setPrice(orderDetail.getPrice());
                    orderDetailResponse.setQuantity(orderDetail.getQuantity());
                    orderDetailResponse.setProductCode(tuple.get("productCode", String.class));
                    orderDetailResponse.setProductName(tuple.get("name", String.class));
                    orderDetailResponse.setProductImg(tuple.get("image", String.class));
                    orderDetailResponse.setSize(tuple.get("size", String.class));
                    orderDetailResponse.setColor(tuple.get("color", String.class));
                    return orderDetailResponse;
                })
                .collect(Collectors.toList());

    }

    public DashboardDTO statisticMoney(Date date) {
        String sql = new StringBuilder("SELECT")
                .append("  SUM( od.cost ) as cost")
                .append("  ,SUM( (od.price * od.quantity) * (100 - od.percentDiscount) / 100 ) as revenue")
                .append("  ,SUM( (od.price * od.quantity) * (100 - od.percentDiscount) / 100) - SUM( od.cost ) as profit")
                .append(" FROM Order o")
                .append("  JOIN OrderDetail od ON o.id = od.orderId")
                .append(" WHERE o.status = 'DONE'")
                .append(Objects.nonNull(date) ? " AND o.createdDate = :createdDate" : "")
                .toString();

        Query<Tuple> query = entityManager.createQuery(sql, Tuple.class);
        if (Objects.nonNull(date)) {
            query.setParameter("createdDate", date);
        }
        List<Tuple> resultList = query.getResultList();
        if (resultList.isEmpty()) {
            return new DashboardDTO();
        }
        Tuple result = resultList.get(0);
        DashboardDTO dashboardDTO = new DashboardDTO();
        dashboardDTO.setCost(result.get("cost", BigDecimal.class));
        dashboardDTO.setProfit(result.get("profit", BigDecimal.class));
        dashboardDTO.setRevenue(result.get("revenue", BigDecimal.class));
        return dashboardDTO;
    }

    public List<Chart> statisticRevenue(DashboardRequest request) {
        return this.statistic(request, "SUM( (od.price * od.quantity) * (100 - od.percent_discount) / 100 )");
    }

    public List<Chart> statisticProfit(DashboardRequest request) {
        return this.statistic(request, "SUM( (od.price * od.quantity) * (100 - od.percent_discount) / 100) - SUM( od.cost ) as profit");
    }

    public List<Chart> statisticCost(DashboardRequest request) {
        return this.statistic(request, "SUM( od.cost ) as cost");
    }

    public List<Chart> statistic(DashboardRequest request, String sql) {
        StringBuilder sqlBuilder = new StringBuilder("SELECT")
                .append(StatisticType.MONTH.equals(request.getType()) ? " DAY(o.created_date)" : " MONTH(o.created_date)")
                .append(", ")
                .append(sql)
                .append(" FROM _order o")
                .append(" JOIN _order_detail od ON o.id = od.order_id")
                .append(" WHERE 1 = 1");
        Map<String, Object> params = new HashMap<>();
        if (StatisticType.MONTH.equals(request.getType())) {
            sqlBuilder.append(" AND MONTH(o.created_date) = :month")
                    .append(" GROUP BY DAY(o.created_date)")
                    .append(" ORDER BY DAY(o.created_date) ASC");
            params.put("month", request.getMonth());
        } else if (StatisticType.YEAR.equals(request.getType())) {
            sqlBuilder.append(" AND YEAR(o.created_date) = :year")
                    .append(" GROUP BY MONTH(o.created_date)")
                    .append(" ORDER BY MONTH(o.created_date) ASC");
            params.put("year", request.getYear());
        } else {
            sqlBuilder.append(" AND YEAR(o.created_date) = :year")
                    .append(" GROUP BY MONTH(o.created_date)")
                    .append(" ORDER BY MONTH(o.created_date) ASC");
            params.put("year", Calendar.getInstance().get(Calendar.YEAR));
        }
        Query<Tuple> query = entityManager.createNativeQuery(sqlBuilder.toString(), Tuple.class);
        params.forEach(query::setParameter);
        return query.getResultList()
                .stream()
                .map(tuple -> {
                    Integer month = tuple.get(0, Integer.class);
                    BigDecimal total = tuple.get(1, BigDecimal.class);
                    return new Chart((StatisticType.YEAR.equals(request.getType()) ? "Tháng " : "Ngày ") + month, total.toString());
                })
                .collect(Collectors.toList());
    }

    public List<Chart> statisticBill(Integer month, Integer year) {
        StringBuilder sqlBuilder = new StringBuilder("SELECT o.status, count(1) FROM _order o WHERE 1 = 1");
        Map<String, Object> param = new HashMap<>();
        if (Objects.nonNull(month)) {
            sqlBuilder.append(" AND MONTH(o.created_date) = :month");
            param.put("month", month);
        }

        if (Objects.nonNull(year)) {
            sqlBuilder.append(" AND YEAR(o.created_date) = :year");
            param.put("year", year);
        }
        sqlBuilder.append(" GROUP BY o.status");

        Query<Tuple> query = entityManager.createNativeQuery(sqlBuilder.toString(), Tuple.class);
        param.forEach(query::setParameter);
        Map<String, BigInteger> result = query.getResultList()
                .stream()
                .collect(Collectors.toMap(tuple -> tuple.get(0, String.class), tuple -> tuple.get(1, BigInteger.class)));
        return Arrays.stream(OrderStatus.values())
                .map(status -> {
                    BigInteger count = result.get(status.name());
                    if (Objects.nonNull(count)) {
                        return new Chart(status.getValue(), count.toString());
                    }
                    return new Chart(status.getValue(), "0");
                })
                .collect(Collectors.toList());
    }
}
