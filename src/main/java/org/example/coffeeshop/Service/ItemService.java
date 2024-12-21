package org.example.coffeeshop.Service;

import org.example.coffeeshop.Entity.Category;
import org.example.coffeeshop.Entity.Item;
import org.example.coffeeshop.Repository.ItemRepository;
import org.example.coffeeshop.Specification.ItemSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    public List<Item> getAll(){
        return itemRepository.findAll();
    }

    public Item createItem(Item Item){
        return itemRepository.save(Item);
    }

    public Item getItemById(Long id){
        return itemRepository.findById(id).orElse(null);
    }

    public void deleteItemById(Long id){
        itemRepository.deleteById(id);
    }

    public Item updateItem(Item Item){
        return itemRepository.save(Item);
    }

    public Item findByName(String name){
        return itemRepository.findByName(name);
    }

    public List<Item> findByCategory(Category category){
        return itemRepository.findByCategory(category);
    }

    public List<Item> findByPriceBetween(double min, double max){
        return itemRepository.findByPriceBetween(min, max);
    }

    public List<Item> hasPriceMoreThanCriteria(double price){
        Sort sort = Sort.by(Sort.Direction.ASC, "price");
        return itemRepository.findAll(ItemSpecification.hasPriceMoreThan(price), sort);
    }
}
