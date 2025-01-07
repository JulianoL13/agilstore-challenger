package com.agilstore.agilstore.category.dto;

import com.agilstore.agilstore.category.entities.Category;
import jakarta.validation.constraints.NotBlank;

import java.util.Objects;

public class CategoryDTO {
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String description;

    public CategoryDTO() {
    }

    public CategoryDTO(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public CategoryDTO(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.description = category.getDescription();
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }


}
