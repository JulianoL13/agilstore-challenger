package com.agilstore.agilstore.product.dto;

import com.agilstore.agilstore.category.dto.CategoryDTO;
import com.agilstore.agilstore.product.entities.Product;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.UUID;

public class ProductDTO {
    private UUID id;
    @NotBlank
    @Min(3)
    private String name;
    @PositiveOrZero
    @NotBlank
    private Integer amount;
    @PositiveOrZero
    @NotBlank
    private Double price;

    private CategoryDTO category;

    public ProductDTO() {
    }

    public ProductDTO(String name, Integer amount, Double price, CategoryDTO category) {
        this.name = name;
        this.amount = amount;
        this.price = price;
        this.category = category;
    }

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.amount = product.getAmount();
        this.price = product.getPrice();
        this.category = new CategoryDTO(product.getCategory());
    }

    public String getName() {
        return name;
    }

    public Integer getAmount() {
        return amount;
    }

    public Double getPrice() {
        return price;
    }

    public CategoryDTO getCategory() {
        return category;
    }
}




