package vn.edu.hcmuaf.fit.shopzonerestfulapi.service;

import com.cloudinary.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.request.CategoryRequest;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.response.ApiResponse;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.model.Category;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.repository.CategoryRepository;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public ApiResponse<Category> createCategory(CategoryRequest categoryRequest) {
        Category category = new Category();
        category.setName(categoryRequest.getName());
        category.setImage(categoryRequest.getImage());
        categoryRepository.save(category);
        return ApiResponse.<Category>builder()
                .result(category)
                .message("Create category successfully")
                .build();
    }
}
