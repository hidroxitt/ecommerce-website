package com.smarttech.dto.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.smarttech.constant.MethodPayment;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
public class CreateOrderRequest {

    @JsonIgnore
    private String uuid;

    @NotBlank(message = "Tên không được trống")
    private String firstName;

    @NotBlank(message = "Họ không được trống")
    private String lastName;

    @NotBlank(message = "Địa chỉ nhận hàng không được trống")
    private String address;

    @NotBlank(message = "Số điện thoại nhận hàng không được trống")
    private String phone;

    @Size(max = 200, message = "Ghi chú không được vượt quá 200 ký tự")
    private String note;

    @NotNull(message = "Phương thức thanh toán không được trống")
    private MethodPayment method = MethodPayment.COD;

    @NotEmpty(message = "Danh sách sản phẩm không được trống")
    private List<Long> cartId = new ArrayList<>();

    private String createdBy;

    private String paymentId;

    private String ipAddress;

    private String paymentOnlineId;

    private String paymentOnlineDate;

    public String getFullName() {
        return String.format("%s %s", this.lastName, this.firstName);
    }
}
