package org.example.coffeeshop.Repository;

import org.example.coffeeshop.Entity.Category;
import org.example.coffeeshop.Entity.Item;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    Item findByName(String name);
    List<Item> findByCategory(Category category);
    List<Item> findByPriceBetween(double min, double max);
    List<Item> findAll(Specification<Item> itemSpecification, Sort sort);
}
