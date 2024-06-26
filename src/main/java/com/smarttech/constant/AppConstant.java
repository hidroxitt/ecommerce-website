package com.smarttech.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AppConstant {

    public static final BigDecimal ZERO = new BigDecimal("0");
    public static final BigDecimal ONE_HUNDRED = new BigDecimal("100");
    public static final int DATE_INDICATE_NEWEST = 30;
    public static final int PERCENT_INDICATE_HOT_SALE = 35;
    public static final int NUMBER_BEST_SELLER_SHOW = 8;
    public static final int NUMBER_IMAGE_SHOW_HOME = 3; // UI only shows three images

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class RequestKey {
        public static final String REDIRECT_URL = "_REDIRECT_URL_KEY";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class ResponseKey {
        public static final String PAGING = "_PAGING_KEY";
        public static final String JS = "_JS_KEY";
        public static final String CSS = "_CSS_KEY";
        public static final String RESULT = "_RESULT_KEY";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Endpoint {
        public static final String HOME = "/trang-chu";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class VnPay {
        public static final Map<String, String> TRANSACTION_STATUS = Map.of(
            "00", "Giao dịch thành công",
            "01", "Giao dịch chưa hoàn tất",
            "02", "Giao dịch bị lỗi",
            "04", "Giao dịch đảo (Khách hàng đã bị trừ tiền tại Ngân hàng nhưng GD chưa thành công ở VNPAY)",
            "05", "VNPAY đang xử lý giao dịch này (GD hoàn tiền)",
            "06", "VNPAY đã gửi yêu cầu hoàn tiền sang Ngân hàng (GD hoàn tiền)",
            "07", "Giao dịch bị nghi ngờ gian lận",
            "09", "GD Hoàn trả bị từ chối"
        );
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Date {
        public static final String DATE_TIME_FORMAT1 = "yyyyMMddHHmmss";
    }
}
