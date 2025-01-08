package com.agilstore.agilstore.product.dto;

import com.agilstore.agilstore.category.dto.CategoryDTO;
import com.agilstore.agilstore.product.entities.Product;
import jakarta.validation.constraints.*;

import java.util.UUID;

public class ProductCategoryDTO {
    private UUID id;
    @NotBlank(message = "Name must be informed")
    @Size(min = 3, message = "Name must have at least 3 characters")
    private String name;
    @PositiveOrZero(message = "Amount must be positive")
    @NotNull(message = "Amount must be informed")
    private Integer amount;
    @PositiveOrZero(message = "Price must be positive")
    @NotNull(message = "Price must be informed")
    private Double price;
    @NotNull(message = "Category must be informed")
    private CategoryDTO category;

    public ProductCategoryDTO() {
    }

    public ProductCategoryDTO(UUID id, String name, Integer amount, Double price, CategoryDTO category) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.price = price;
        this.category = category;
    }

    public ProductCategoryDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.amount = product.getAmount();
        this.price = product.getPrice();
        this.category = new CategoryDTO(product.getCategory());
    }

    public UUID getId() {
        return id;
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
