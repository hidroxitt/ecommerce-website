package com.smarttech.dto.dashboard;

import com.smarttech.constant.StatisticType;
import lombok.Data;

@Data
public class DashboardRequest {
    private StatisticType type;
    private Integer date;
    private Integer month;
    private Integer year;
}