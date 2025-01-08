package com.agilstore.agilstore.product.controllers;

import com.agilstore.agilstore.product.dto.ProductCategoryDTO;
import com.agilstore.agilstore.product.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {
    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductCategoryDTO> createProduct(@Valid @RequestBody ProductCategoryDTO dto) {
        ProductCategoryDTO product = productService.createProduct(dto);
        return ResponseEntity.ok(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductCategoryDTO> updateProduct(@PathVariable UUID id, @Valid @RequestBody ProductCategoryDTO dto) {
        ProductCategoryDTO product = productService.updateProduct(id, dto);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductCategoryDTO> getProductById(@PathVariable UUID id) {
        ProductCategoryDTO product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @GetMapping
    public ResponseEntity<Page<ProductCategoryDTO>> getProducts(
            @RequestParam(name = "name", defaultValue = "") String name,
            @RequestParam(name = "categoryId", required = false) Long categoryId,
            @RequestParam(name = "minPrice", required = false) Double minPrice,
            @RequestParam(name = "maxPrice", required = false) Double maxPrice,
            @RequestParam(name = "minAmount", required = false) Integer minAmount,
            @RequestParam(name = "maxAmount", required = false) Integer maxAmount,
            Pageable pageable) {
        Page<ProductCategoryDTO> products = productService.getProducts(name, categoryId, minPrice, maxPrice, minAmount, maxAmount, pageable);
        return ResponseEntity.ok(products);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }



}
