package org.example.coffeeshop.Specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.example.coffeeshop.Entity.Item;
import org.springframework.data.jpa.domain.Specification;

public class ItemSpecification {
    public static Specification<Item> hasPriceMoreThan(double price) {
        return new Specification<Item>() {
            @Override
            public Predicate toPredicate(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.greaterThan(root.get("price"), price);
            }
        };
    }
}
