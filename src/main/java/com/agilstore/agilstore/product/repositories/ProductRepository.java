package com.agilstore.agilstore.product.repositories;

import com.agilstore.agilstore.product.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
@Query("SELECT obj"
        + " FROM Product obj"
        + " WHERE LOWER(obj.name) LIKE LOWER(CONCAT('%', :name, '%'))")
Page<Product> searchByName(String name, Pageable pageable);

}
