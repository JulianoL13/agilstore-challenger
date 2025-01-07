package com.agilstore.agilstore.product.controllers;

import com.agilstore.agilstore.product.dto.ProductCategoryDTO;
import com.agilstore.agilstore.product.dto.ProductDTO;
import com.agilstore.agilstore.product.services.ProductService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductCategoryDTO> createProduct(@RequestBody ProductCategoryDTO dto) {
        ProductCategoryDTO product = productService.createProduct(dto);
        return ResponseEntity.ok(product);
    }


}
