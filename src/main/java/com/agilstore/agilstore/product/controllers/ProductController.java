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
    public ResponseEntity<ProductCategoryDTO> updateProduct(@Valid @PathVariable UUID id, @RequestBody ProductCategoryDTO dto) {
        ProductCategoryDTO product = productService.updateProduct(id, dto);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductCategoryDTO> getProductById(@PathVariable UUID id) {
        ProductCategoryDTO product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @GetMapping
    public ResponseEntity<Page<ProductCategoryDTO>> getProducts(@RequestParam(name = "name", defaultValue = "")
                                                                    String name, Pageable pageable) {
        Page<ProductCategoryDTO> products = productService.getProducts(name, pageable);
        return ResponseEntity.ok(products);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }



}
