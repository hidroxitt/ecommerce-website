package com.smarttech.service;

import com.smarttech.constant.ImageType;
import com.smarttech.dto.product.ColorProductDTO;
import com.smarttech.dto.product.ProductDetailRequest;
import com.smarttech.dto.product.ProductResponse;
import com.smarttech.entity.Image;
import com.smarttech.entity.Product;
import com.smarttech.entity.Specifications;
import com.smarttech.exception.ValidationException;
import com.smarttech.repository.ImageRepository;
import com.smarttech.repository.ProductRepository;
import com.smarttech.repository.SpecificationsRepository;
import com.smarttech.repository.custom.CustomProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductDetailService {
    private final ProductRepository productDao;
    private final ImageRepository imageDao;
    private final CustomProductRepository customProductRepository;
    private final SpecificationsRepository specificationsRepository;

    public ProductResponse getDetailProduct(String productCode) {
        Product product = this.productDao.findByCode(productCode).orElseThrow(() -> new ValidationException(productCode + " không tồn tại"));
        Image image = this.imageDao.findByTypeAndObjectId(ImageType.PRODUCT, String.valueOf(product.getId())).orElse(new Image());
        List<Specifications> byProductId = this.specificationsRepository.findByProductId(product.getId());
        ProductResponse productResponse = new ProductResponse();
        productResponse.setCode(product.getCode());
        productResponse.setName(product.getName());
        productResponse.setImage(image.getUrl());
        productResponse.setId(product.getId());
        productResponse.setCreatedDate(product.getCreatedDate());
        productResponse.setDescription(product.getDescription());
        productResponse.setCategoryId(product.getCategoryId());
        productResponse.setIsShowHome(product.getIsShowHome());
        productResponse.setShortDescription(product.getShortDescription());
        productResponse.setSpecifications(byProductId);

        Map<ColorProductDTO, List<ProductDetailRequest>> details = this.customProductRepository.getSizeAndColorProduct(product.getId());
        productResponse.setDetails(details);
        return productResponse;
    }
}
