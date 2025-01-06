package com.agilstore.agilstore.product.services;

import com.agilstore.agilstore.category.entities.Category;
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
    ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = new Product();
        convertDtoToEntity(productDTO, product);
        productRepository.save(product);
        return productDTO;
    }

    @Transactional
    public ProductDTO updateProduct(UUID id, ProductDTO productDTO) {
        Product product = productRepository.getReferenceById(id);
        convertDtoToEntity(productDTO, product);
        productRepository.save(product);
        return productDTO;
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

    private void convertDtoToEntity(ProductDTO productDTO, Product product) {
        product.setName(productDTO.getName());
        product.setAmount(productDTO.getAmount());
        product.setPrice(productDTO.getPrice());

        Category category = new Category();
        category.setId(productDTO.getCategory().getId());
        category.setName(productDTO.getCategory().getName());
        category.setDescription(productDTO.getCategory().getDescription());
        product.setCategory(category);
    }
}
