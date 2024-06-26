package com.smarttech.dto.product;

import com.smarttech.entity.Specifications;
import lombok.Data;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Data
public class CreateProductRequest {
    private Long id;

    @NotBlank(message = "Mã sản phẩm không được trống")
    private String code;

    @NotBlank(message = "Tên sản phẩm không được trống")
    private String name;

    @NotNull(message = "Loại sản phẩm không được trống")
    private Long categoryId;
    private MultipartFile image;

    @NotBlank(message = "Mô tả ngắn không được trống")
    @Size(max = 500, message = "Mô tả ngán tối đa 500 ký tự")
    private String shortDescription;

    private String description;
    private String colors;
    private String sizes;
    private Boolean isShowHome;
    private List<ColorProductDTO> colorProductDTOList = new ArrayList<>();
    private Map<String, List<ProductDetailRequest>> productDetails = new LinkedHashMap<>();
    private List<Specifications> specifications = new ArrayList<>();

    @SneakyThrows
    public void setColorSize(Map<String, MultipartFile> fileMap, HttpServletRequest request) {
        if (StringUtils.isBlank(this.colors) || StringUtils.isBlank(this.sizes)) {
            throw new IllegalArgumentException("Sizes and Colors is mandatory");
        }

        String[] colorArray = this.colors.split(",\\s*");
        String[] sizeArray = this.sizes.split(",\\s*");

        for (String color : colorArray) {
            MultipartFile multipartFile = fileMap.get(color);
            ColorProductDTO colorProductDTO = ColorProductDTO.builder()
                    .image(multipartFile)
                    .color(color)
                    .build();
            this.colorProductDTOList.add(colorProductDTO);

            List<ProductDetailRequest> detailRequests = new ArrayList<>();
            for (String size : sizeArray) {
                String quantity = request.getParameter(String.format("quantity-%s-%s", color, size));
                String cost = request.getParameter(String.format("cost-%s-%s", color, size));
                String price = request.getParameter(String.format("price-%s-%s", color, size));
                String discount = request.getParameter(String.format("discount-%s-%s", color, size));
                ProductDetailRequest productDetailRequest = ProductDetailRequest.builder()
                        .color(colorProductDTO)
                        .size(size)
                        .discount(NumberUtils.toInt(discount, 0))
                        .cost(new BigDecimal(cost))
                        .price(new BigDecimal(price))
                        .quantity(NumberUtils.toInt(quantity, 0))
                        .build();
                detailRequests.add(productDetailRequest);
            }
            this.productDetails.put(color, detailRequests);
        }

    }
}
