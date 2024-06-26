package com.smarttech.controller.admin;

import com.smarttech.controller.BaseController;
import com.smarttech.dto.dashboard.DashboardDTO;
import com.smarttech.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminHomeController extends BaseController {

    private final DashboardService dashboardService;

    @GetMapping("/home")
    public String homePage(Model model) {
        Date now = new Date();
        DashboardDTO totalStatistic = this.dashboardService.statisticMoney(null);
        DashboardDTO todayStatistic = this.dashboardService.statisticMoney(now);
        model.addAttribute("totalStatistic", totalStatistic);
        model.addAttribute("todayStatistic", todayStatistic);
        model.addAttribute("now", now);
        this.addJavascript(model, "/admin/javascript/chart.min", "/admin/javascript/dashboard", "/admin/javascript/dashboard-chart");
        return "admin/pages/dashboard/index";
    }
}
