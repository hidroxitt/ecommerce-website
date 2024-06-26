package com.smarttech.controller.admin;

import com.smarttech.dto.dashboard.Chart;
import com.smarttech.dto.dashboard.DashboardRequest;
import com.smarttech.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/rest/dashboard")
@RequiredArgsConstructor
public class DashboardRestController {

    private final DashboardService dashboardService;

    @PostMapping("/chart/bill")
    public List<Chart> statisticChartBill(DashboardRequest dashboardRequest) {
        return dashboardService.statisticBill(dashboardRequest);
    }

    @PostMapping("/chart/user")
    public List<Chart> statisticChartUser(DashboardRequest dashboardRequest) {
        return dashboardService.statisticUser(dashboardRequest);
    }

    @PostMapping("/chart/product")
    public List<Chart> statisticChartProduct(DashboardRequest dashboardRequest) {
        return dashboardService.statisticProduct(dashboardRequest);
    }

    @PostMapping("/chart/revenue")
    public List<Chart> statisticChartRevenue(DashboardRequest dashboardRequest) {
        return dashboardService.statisticRevenue(dashboardRequest);
    }

    @PostMapping("/chart/profit")
    public List<Chart> statisticChartProfit(DashboardRequest dashboardRequest) {
        return dashboardService.statisticProfit(dashboardRequest);
    }

    @PostMapping("/chart/cost")
    public List<Chart> statisticChartCost(DashboardRequest dashboardRequest) {
        return dashboardService.statisticCost(dashboardRequest);
    }
}
