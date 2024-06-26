package com.smarttech.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smarttech.dto.category.*;
import com.smarttech.dto.product.ProductResponse;
import com.smarttech.entity.Category;
import com.smarttech.entity.Product;
import com.smarttech.exception.ValidationException;
import com.smarttech.repository.CategoryRepository;
import com.smarttech.repository.ProductRepository;
import com.smarttech.repository.custom.CustomProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ObjectMapper objectMapper;
    private final CustomProductRepository customProductRepository;
    private final ProductRepository productRepository;

    public List<CategoryProductDTO> findCategoryProduct(int limitProduct) {
        return this.categoryRepository.findRootActiveCategory().stream()
                .map(x -> {
                    CategoryProductDTO x1 = new CategoryProductDTO();
                    x1.setActive(x.getActive());
                    x1.setName(x.getName());
                    x1.setId(x.getId());
                    x1.setCode(x.getCode());

                    List<CategoryDTO> child = this.findActiveByParentId(x.getId());
                    x1.setChild(child);

                    List<Long> categoryIds = child.stream()
                            .map(CategoryDTO::getId)
                            .collect(Collectors.toList());
                    categoryIds.add(x1.getId());

                    List<ProductResponse> products = this.customProductRepository.getByCategoryId(limitProduct, categoryIds);
                    x1.setProducts(products);
                    return x1;
                })
                .filter(x -> !CollectionUtils.isEmpty(x.getProducts()))
                .collect(Collectors.toList());
    }

    public List<CategoryDTO> findActiveByParentId(Long parentId) {
        return this.categoryRepository.findByParentIdAndActive(parentId, true)
                .stream()
                .map(entity -> objectMapper.convertValue(entity, CategoryDTO.class))
                .collect(Collectors.toList());
    }

    public List<CategoryDTO> findActiveCategory() {
        return this.categoryRepository.findActiveCategory()
                .stream()
                .map(entity -> objectMapper.convertValue(entity, CategoryDTO.class))
                .collect(Collectors.toList());
    }

    public List<CategoryDTO> findActiveChildCategory() {
        return this.categoryRepository.findActiveChildCategory()
                .stream()
                .map(entity -> objectMapper.convertValue(entity, CategoryDTO.class))
                .collect(Collectors.toList());
    }

    public List<CategoryDTO> findAll() {
        return this.categoryRepository.findAll()
                .stream()
                .map(entity -> this.objectMapper.convertValue(entity, CategoryDTO.class))
                .collect(Collectors.toList());
    }

    public List<CategoryTree> findAllTree() {
        Sort sortByCreatedDate = Sort.by(Sort.Direction.DESC, "createdDate");
        List<Category> categories = this.categoryRepository.findAll(sortByCreatedDate);
        List<CategoryTree> categoryTreeList = categories.stream()
                .map(x -> {
                    CategoryTree categoryTree = new CategoryTree();
                    categoryTree.setId(x.getId());
                    categoryTree.setCode(x.getCode());
                    categoryTree.setName(x.getName());
                    categoryTree.setActive(x.getActive());
                    categoryTree.setParentId(x.getParentId());
                    categoryTree.setChild(new ArrayList<>());
                    return categoryTree;
                })
                .collect(Collectors.toList());
        Map<Long, List<CategoryTree>> categoryMap = categoryTreeList.stream()
                .filter(x -> Objects.nonNull(x.getParentId()))
                .collect(Collectors.groupingBy(CategoryTree::getParentId));

        List<CategoryTree> roots = new ArrayList<>();
        for (CategoryTree categoryTree : categoryTreeList) {
            if (Objects.isNull(categoryTree.getParentId())) {
                roots.add(categoryTree);
            }
            List<CategoryTree> child = categoryMap.get(categoryTree.getId());
            if (Objects.nonNull(child)) {
                categoryTree.setChild(child);
            }
        }
        return roots;
    }

    public Category findByIdThrowIfNotExist(Long id) {
        return this.categoryRepository.findById(id)
                .orElseThrow(() -> new ValidationException("khong tim thay danh mục: " + id));
    }

    @Transactional
    public void createCategory(CreateCategoryDTO createCategoryDTO) {
        Optional<Category> categoryByCode = this.categoryRepository.findByCode(createCategoryDTO.getCode());
        if (categoryByCode.isPresent()) {
            Category category = categoryByCode.get();
            throw new ValidationException("Mã danh mục đã tồn tại với tên: " + category.getName());
        }

        this.validateParentId(createCategoryDTO.getParentId());
        Category category = new Category();
        category.setCode(createCategoryDTO.getCode());
        category.setName(createCategoryDTO.getName());
        category.setParentId(createCategoryDTO.getParentId());
        category.setActive(false);
        this.categoryRepository.save(category);
    }

    @Transactional
    public void updateCategory(UpdateCategoryDTO updateCategoryDTO) {
        Category category = this.findByIdThrowIfNotExist(updateCategoryDTO.getId());

        Optional<Category> categoryByCode = this.categoryRepository.findByCode(updateCategoryDTO.getCode())
                .filter(x -> !x.getId().equals(category.getId()));
        if (categoryByCode.isPresent()) {
            Category c = categoryByCode.get();
            throw new ValidationException("Mã danh mục đã tồn tại với tên: " + c.getName());
        }

        this.validateParentId(updateCategoryDTO.getParentId());
        category.setCode(updateCategoryDTO.getCode());
        category.setName(updateCategoryDTO.getName());
        category.setParentId(updateCategoryDTO.getParentId());
        this.categoryRepository.save(category);
    }

    @Transactional
    public void deleteCategory(Long id) {
        Category category = this.findByIdThrowIfNotExist(id);
        List<Product> productsByCategoryId = this.productRepository.findByCategoryId(id);
        List<Category> categoryByParentId = this.categoryRepository.findByParentId(id);
        if (!categoryByParentId.isEmpty()) {
            throw new ValidationException("Danh mục này đã có danh mục con. Không thể xóa");
        }
        if (!productsByCategoryId.isEmpty()) {
            throw new ValidationException("Danh mục này đã có sản phẩm. Không thể xóa");
        }
        this.categoryRepository.delete(category);
    }

    @Transactional
    public void changeStatus(Long id) {
        Category category = this.findByIdThrowIfNotExist(id);
        category.setActive(!category.getActive());
        List<Category> children = this.categoryRepository.findByParentId(id);
        children.forEach(x -> x.setActive(category.getActive()));
        children.add(category);
        if (Objects.nonNull(category.getParentId())) {
            this.categoryRepository.findById(category.getParentId())
                .ifPresent(x -> {
                    if (category.getActive()) {
                        x.setActive(true);
                    } else {
                        List<Category> child = this.categoryRepository.findByParentId(x.getId());
                        x.setActive( child.stream().filter(y -> !y.getId().equals(category.getId())).anyMatch(y -> y.getActive()));
                    }
                    this.categoryRepository.save(x);
                });
        }
        this.categoryRepository.saveAll(children);
    }

    private void validateParentId(Long id) {
        if (Objects.nonNull(id)) {
            Optional<Category> categoryById = this.categoryRepository.findById(id);
            if (!categoryById.isPresent()) {
                throw new ValidationException("Danh mục cha không tồn tại");
            }
        }
    }
}
