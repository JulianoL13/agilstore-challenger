package com.agilstore.agilstore.product.dto;

import com.agilstore.agilstore.category.dto.CategoryDTO;
import com.agilstore.agilstore.product.entities.Product;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.UUID;

public class ProductDTO {
    private UUID id;
    @NotBlank(message = "Name must be informed")
    @Min(value = 3, message = "Name must have at least 3 characters")
    private String name;
    @PositiveOrZero(message = "Amount must be positive")
    @NotNull(message = "Amount must be informed")
    private Integer amount;
    @PositiveOrZero(message = "Price must be positive")
    @NotNull(message = "Price must be informed")
    private Double price;

    public ProductDTO() {
    }

    public ProductDTO(String name, Integer amount, Double price) {
        this.name = name;
        this.amount = amount;
        this.price = price;
    }

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.amount = product.getAmount();
        this.price = product.getPrice();
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

}




