package com.agilstore.agilstore.product.services;

import com.agilstore.agilstore.category.entities.Category;
import com.agilstore.agilstore.category.repositories.CategoryRepository;
import com.agilstore.agilstore.genericExceptions.dtos.ResourceNotFoundException;
import com.agilstore.agilstore.product.Specification.ProductSpecification;
import com.agilstore.agilstore.product.dto.ProductCategoryDTO;
import com.agilstore.agilstore.product.entities.Product;
import com.agilstore.agilstore.product.repositories.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class ProductService {

    CategoryRepository categoryRepository;
    ProductRepository productRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public ProductCategoryDTO createProduct(ProductCategoryDTO dto) {
        Product product = new Product();
        convertDtoToEntity(dto, product);
        Category category = categoryRepository.getReferenceById(dto.getCategory().getId());
        product.setCategory(category);
        product = productRepository.save(product);
        return new ProductCategoryDTO(product);

    }

    @Transactional
    public ProductCategoryDTO updateProduct(UUID id, ProductCategoryDTO dto) {
        try {
            Product product = productRepository.getReferenceById(id);
            convertDtoToEntity(dto, product);
            Category category = categoryRepository.getReferenceById(dto.getCategory().getId());
            product.setCategory(category);
            product = productRepository.save(product);
            return new ProductCategoryDTO(product);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Product not found");
        }
    }

    @Transactional
    public void deleteProduct(UUID id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found");
        }
        productRepository.deleteById(id);

    }

    @Transactional(readOnly = true)
    public ProductCategoryDTO getProductById(UUID id) {
        Product entity = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        ProductCategoryDTO dto = new ProductCategoryDTO(entity);
        return dto;

    }
    @Transactional(readOnly = true)
   public Page<ProductCategoryDTO> getProducts(String name, Long categoryId, Double minPrice, Double maxPrice, Integer minAmount, Integer maxAmount, Pageable pageable) {
        Specification<Product> spec = Specification.where(ProductSpecification.hasName(name)
                .and(ProductSpecification.hasCategory(categoryId)).
                and(ProductSpecification.hasPrice(minPrice, maxPrice))
                .and(ProductSpecification.hasAmount(minAmount, maxAmount)));
        Page<Product> products = productRepository.findAll(spec, pageable);
        return products.map(ProductCategoryDTO::new);
    }

    private void convertDtoToEntity(ProductCategoryDTO dto, Product product) {
        product.setName(dto.getName());
        product.setAmount(dto.getAmount());
        product.setPrice(dto.getPrice());
    }
}
