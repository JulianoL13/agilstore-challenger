package com.agilstore.agilstore.product.services;

import com.agilstore.agilstore.category.entities.Category;
import com.agilstore.agilstore.category.repositories.CategoryRepository;
import com.agilstore.agilstore.product.dto.ProductCategoryDTO;
import com.agilstore.agilstore.product.dto.ProductDTO;
import com.agilstore.agilstore.product.entities.Product;
import com.agilstore.agilstore.product.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public ProductCategoryDTO updateProduct(UUID id, ProductCategoryDTO productDTO) {
        Product product = productRepository.getReferenceById(id);
        convertDtoToEntity(productDTO, product);
        product =  productRepository.save(product);
        return new ProductCategoryDTO(product);
    }

    @Transactional
    public void deleteProduct(UUID id) {
        productRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public ProductDTO getProductById(UUID id) {
        Product entity = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        ProductDTO dto = new ProductDTO(entity);
        return dto;
    }

   public Page<ProductDTO> getProducts(Pageable pageable) {
        Page<Product> products = productRepository.findAll(pageable);
        return products.map(ProductDTO::new);
    }

    private void convertDtoToEntity(ProductCategoryDTO dto, Product product) {
        product.setName(dto.getName());
        product.setAmount(dto.getAmount());
        product.setPrice(dto.getPrice());
    }
}
