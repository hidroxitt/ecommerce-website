package com.smarttech.service.payment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smarttech.constant.AppConstant;
import com.smarttech.dto.order.CreateOrderRequest;
import com.smarttech.dto.payment.PaymentResponse;
import com.smarttech.entity.Cart;
import com.smarttech.entity.Order;
import com.smarttech.entity.ProductDetail;
import com.smarttech.exception.ValidationException;
import com.smarttech.service.OrderService;
import com.smarttech.service.UserService;
import com.smarttech.service.payment.model.VnPayInput;
import com.smarttech.service.payment.model.VnPayQueryInput;
import com.smarttech.service.payment.model.VnPayQueryOutput;
import com.smarttech.service.payment.model.VnPayRefundInput;
import com.smarttech.service.payment.properties.VnPayProperties;
import com.smarttech.utils.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@Service
@ConditionalOnBean(VnPayProperties.class)
@RequiredArgsConstructor
@Slf4j
public class VNPPaymentService extends AbstractPaymentService {

    private final VnPayProperties vnPayProperties;
    private final RestTemplate defaultRestTemplate;
    private final ObjectMapper objectMapper;
    private final UserService userService;
    private final Random rnd = new Random();

    @Override
    public PaymentResponse payment(CreateOrderRequest createOrderRequest) {
        log.info("payment with vnpay: {}", createOrderRequest);
        List<Cart> carts = this.getMyCartByIds(createOrderRequest);
        Map<Long, ProductDetail> productDetailMap = this.getProductDetail(carts);
        BigDecimal total = calculateTotalPrice(carts, productDetailMap);
        total = total.multiply(AppConstant.ONE_HUNDRED).setScale(0,
                RoundingMode.CEILING);
        String txnRef = this.getRandomNumber(8);
        VnPayInput vnPayInput = new VnPayInput();
        vnPayInput.setVnp_Version(this.vnPayProperties.getVersion());
        vnPayInput.setVnp_Command("pay");
        vnPayInput.setVnp_TmnCode(this.vnPayProperties.getTmnCode());
        vnPayInput.setVnp_Amount(total.toString());
        vnPayInput.setVnp_CurrCode(this.vnPayProperties.getCurrency());
        vnPayInput.setVnp_TxnRef(txnRef);
        vnPayInput.setVnp_OrderInfo("Thanh toan don hang: " + txnRef);
        vnPayInput.setVnp_OrderType("other");
        vnPayInput.setVnp_ReturnUrl(this.returnUrl + "?provider=VNPAY&uuid=" +
                createOrderRequest.getUuid());
        vnPayInput.setVnp_IpAddr(createOrderRequest.getIpAddress());
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
        vnPayInput.setVnp_CreateDate(DateUtil.format(calendar.getTime(),
                AppConstant.Date.DATE_TIME_FORMAT1));
        calendar.add(Calendar.MINUTE, this.vnPayProperties.getTtl());
        vnPayInput.setVnp_ExpireDate(DateUtil.format(calendar.getTime(),
                AppConstant.Date.DATE_TIME_FORMAT1));

        String queryParam = vnPayInput.toQueryParam();
        String secureHash = this.hmacSHA512(this.vnPayProperties.getSecretKey(),
                queryParam);
        queryParam += "&vnp_SecureHash=" + secureHash;
        String url = this.vnPayProperties.getUrl() + "?" + queryParam;
        return PaymentResponse.builder()
                .url("redirect:" + url)
                .build();
    }

    @Override
    @Transactional
    public Order afterPayment(CreateOrderRequest createOrderRequest, Map<String, Object> data) {
        String secureHash = (String) data.get("vnp_SecureHash");

        data.remove("uuid");
        data.remove("provider");
        data.remove("vnp_SecureHashType");
        data.remove("vnp_SecureHash");

        Map<String, Object> _data = new HashMap<>();
        data.forEach((key, value) -> {
            String fieldName = this.encode(key);
            String fieldValue = this.encode(value.toString());
            _data.put(fieldName, fieldValue);
        });

        String signValue = this.hashAllFields(_data,
                this.vnPayProperties.getSecretKey());
        if (!secureHash.equals(signValue)) {
            throw new ValidationException("Chữ ký không hợp lệ!");
        }

        if (!"00".equals(data.get("vnp_TransactionStatus"))) {
            String message = AppConstant.VnPay.TRANSACTION_STATUS.get(data.get("vnp_TransactionStatus"));
            throw new ValidationException(message);
        }
        String txnRef = (String) data.get("vnp_TxnRef");
        String payDate = (String) data.get("vnp_PayDate");
        // todo: fix later
        VnPayQueryOutput vnPayQueryOutput = this.queryTransactionVnPay(txnRef,
                payDate, createOrderRequest.getIpAddress());
        if (!"00".equals(vnPayQueryOutput.getTransactionStatus())) {
            String message = AppConstant.VnPay.TRANSACTION_STATUS.get(vnPayQueryOutput.getTransactionStatus());
            throw new ValidationException(message);
        }
        createOrderRequest.setPaymentId(txnRef);
        return this.createOrder(createOrderRequest);
    }

    @Override
    @SneakyThrows
    @Transactional
    public void refund(Order order) {
        String vnp_RequestId = UUID.randomUUID().toString().replace("-", "");

        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));

        VnPayRefundInput vnPayRefundInput = new VnPayRefundInput();
        vnPayRefundInput.setVnp_RequestId(vnp_RequestId);
        vnPayRefundInput.setVnp_Version(this.vnPayProperties.getVersion());
        vnPayRefundInput.setVnp_TmnCode(this.vnPayProperties.getTmnCode());
        vnPayRefundInput.setVnp_TxnRef(order.getPaymentOnlineId());
        vnPayRefundInput.setVnp_Amount(order.getTotal().multiply(AppConstant.ONE_HUNDRED).setScale(0,
                RoundingMode.CEILING).toString());
        vnPayRefundInput.setVnp_OrderInfo("Hoan tien GD OrderId:" +
                order.getPaymentOnlineId());
        vnPayRefundInput.setVnp_TransactionDate(order.getPaymentOnlineDate());
        vnPayRefundInput.setVnp_CreateBy(this.userService.getCurrentUserThrowIfNot().getUsername());
        vnPayRefundInput.setVnp_CreateDate(DateUtil.format(cld.getTime(),
                AppConstant.Date.DATE_TIME_FORMAT1));
        vnPayRefundInput.setVnp_IpAddr("localhost");
        String checkSum = this.createCheckSum(vnPayRefundInput);

        String vnp_SecureHash = this.hmacSHA512(this.vnPayProperties.getSecretKey(),
                checkSum);
        vnPayRefundInput.setVnp_SecureHash(vnp_SecureHash);
        ResponseEntity<String> stringResponseEntity = this.defaultRestTemplate.postForEntity(
                this.vnPayProperties.getApi(),
                vnPayRefundInput, String.class);
        log.info("{}", stringResponseEntity);
    }

    public String encode(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.US_ASCII.toString());
        } catch (Exception ex) {
            return value;
        }
    }

    private String createCheckSum(Object object) {
        Map<String, Object> map = this.objectMapper.convertValue(object, Map.class);
        return map.keySet().stream()
                .map(map::get)
                .filter(Objects::nonNull)
                .map(String.class::cast)
                .collect(Collectors.joining("|"));

    }

    @SneakyThrows
    public VnPayQueryOutput queryTransactionVnPay(String oderId, String transDate, String ip) {
        String vnp_RequestId = UUID.randomUUID().toString().replace("-", "");
        VnPayQueryInput vnPayInput = new VnPayQueryInput();
        vnPayInput.setVnp_RequestId(vnp_RequestId);
        vnPayInput.setVnp_Version(this.vnPayProperties.getVersion());
        vnPayInput.setVnp_TmnCode(this.vnPayProperties.getTmnCode());
        vnPayInput.setVnp_TxnRef(oderId);
        vnPayInput.setVnp_OrderInfo("Kiem tra ket qua GD OrderId:" + oderId);
        vnPayInput.setVnp_TransactionDate(transDate);
        vnPayInput.setVnp_CreateDate(DateUtil.format(new Date(),
                AppConstant.Date.DATE_TIME_FORMAT1));
        vnPayInput.setVnp_IpAddr(ip);
        String checkSum = this.createCheckSum(vnPayInput);
        String secureHash = this.hmacSHA512(this.vnPayProperties.getSecretKey(),
                checkSum);
        vnPayInput.setVnp_SecureHash(secureHash);
        ResponseEntity<String> response = this.defaultRestTemplate.postForEntity(this.vnPayProperties.getApi(),
                vnPayInput, String.class);
        if (!HttpStatus.OK.equals(response.getStatusCode())) {
            throw new ValidationException("Truy vấn thông tin giao dịch thất bại");
        }
        return this.objectMapper.readValue(response.getBody(),
                VnPayQueryOutput.class);
    }

    @Override
    public boolean isPaymentOnline() {
        return true;
    }

    public String hmacSHA512(final String key, final String data) {
        try {

            if (key == null || data == null) {
                throw new NullPointerException();
            }
            final Mac hmac512 = Mac.getInstance("HmacSHA512");
            byte[] hmacKeyBytes = key.getBytes();
            final SecretKeySpec secretKey = new SecretKeySpec(hmacKeyBytes,
                    "HmacSHA512");
            hmac512.init(secretKey);
            byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);
            byte[] result = hmac512.doFinal(dataBytes);
            StringBuilder sb = new StringBuilder(2 * result.length);
            for (byte b : result) {
                sb.append(String.format("%02x", b & 0xff));
            }
            return sb.toString();

        } catch (Exception ex) {
            return "";
        }
    }

    public String hashAllFields(Map<String, Object> fields, String secretKey) {
        ArrayList fieldNames = new ArrayList(fields.keySet());
        Collections.sort(fieldNames);
        StringBuilder sb = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) fields.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                sb.append(fieldName);
                sb.append("=");
                sb.append(fieldValue);
            }
            if (itr.hasNext()) {
                sb.append("&");
            }
        }
        return this.hmacSHA512(secretKey, sb.toString());
    }

    public String getRandomNumber(int len) {
        String chars = "0123456789";
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        }
        return sb.toString();
    }
}
