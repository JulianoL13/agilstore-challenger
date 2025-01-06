package com.agilstore.agilstore.product.repositories;

import com.agilstore.agilstore.product.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

}
