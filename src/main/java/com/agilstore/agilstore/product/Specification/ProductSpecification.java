package com.agilstore.agilstore.product.Specification;

import com.agilstore.agilstore.product.entities.Product;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class ProductSpecification {
    public static Specification<Product>hasName(String name){
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.hasText(name)){
                return criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%");
            }
            return null;
        };
    }
    public static Specification<Product> hasCategory(Long categoryId){
        return (root, query, criteriaBuilder) -> {
            if (categoryId != null){
                return criteriaBuilder.equal(root.get("category").get("id"), categoryId);
            }
            return null;
        };
    }

    public static Specification<Product> hasPrice(Double minPrice, Double maxPrice){
        return (root, query, criteriaBuilder) -> {
            if (minPrice != null && maxPrice != null){
                return criteriaBuilder.between(root.get("price"), minPrice, maxPrice);
            }
            if (minPrice != null) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);
            }
            if (maxPrice != null){
                return criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice);
            }

            return null;
        };
    }

    public static Specification<Product> hasAmount(Integer minAmount, Integer maxAmount){
        return (root, query, criteriaBuilder) -> {
            if (minAmount != null && maxAmount != null){
                return criteriaBuilder.between(root.get("amount"), minAmount, maxAmount);
            }
            if (minAmount != null) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("amount"), minAmount);
            }
            if (maxAmount != null){
                return criteriaBuilder.lessThanOrEqualTo(root.get("amount"), maxAmount);
            }

            return null;
        };
    }

}
