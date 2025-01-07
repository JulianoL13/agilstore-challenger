package com.agilstore.agilstore.category.repositories;

import com.agilstore.agilstore.category.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
