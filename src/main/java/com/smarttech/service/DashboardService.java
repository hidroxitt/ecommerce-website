package com.smarttech.service;

import com.smarttech.dto.dashboard.Chart;
import com.smarttech.dto.dashboard.DashboardDTO;
import com.smarttech.dto.dashboard.DashboardRequest;
import com.smarttech.repository.OrderRepository;
import com.smarttech.repository.custom.CustomOrderRepository;
import com.smarttech.repository.custom.CustomProductRepository;
import com.smarttech.repository.custom.CustomsUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final CustomOrderRepository orderDao;
    private final OrderRepository orderRepository;
    private final CustomsUserRepository userDao;
    private final CustomProductRepository productDao;

    public DashboardDTO statisticMoney(Date date) {
        DashboardDTO dashboardDTO = this.orderDao.statisticMoney(date);
        if (Objects.nonNull(date)) {
            dashboardDTO.setTotalOrder(this.orderRepository.countByDate(date));
        } else {
            dashboardDTO.setTotalOrder(this.orderRepository.count());
        }
        return dashboardDTO;
    }

    public List<Chart> statisticBill(DashboardRequest request) {
        return this.orderDao.statisticBill(request.getMonth(), request.getYear());
    }

    public List<Chart> statisticUser(DashboardRequest request) {
        return this.userDao.statisticUser(request.getMonth(), request.getYear());
    }

    public List<Chart> statisticProduct(DashboardRequest request) {
        return this.productDao.statisticProduct(request.getMonth(), request.getYear());
    }

    public List<Chart> statisticRevenue(DashboardRequest request) {
        return this.orderDao.statisticRevenue(request);
    }

    public List<Chart> statisticProfit(DashboardRequest request) {
        return this.orderDao.statisticProfit(request);
    }

    public List<Chart> statisticCost(DashboardRequest request) {
        return this.orderDao.statisticCost(request);
    }
}
