package org.example.coffeeshop.Controller;

import org.example.coffeeshop.Entity.Category;
import org.example.coffeeshop.Entity.Item;
import org.example.coffeeshop.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private ItemService itemService;
    protected ItemController() {}

    @GetMapping(produces = { "application/json", "application/xml"})
    public ResponseEntity<List<Item>> getAll(){
        List<Item> items = itemService.getAll();
        if(items.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Items not found");
        else
            return ResponseEntity.ok(items);
    }

    @GetMapping(path="/{id}", produces = { "application/json", "application/xml"})
    public ResponseEntity<Item> getItemById(@PathVariable("id") Long id){
        Item item = itemService.getItemById(id);
        if(item == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item not found");
        else
            return ResponseEntity.ok(item);
    }

    @PostMapping(produces = { "application/json", "application/xml"})
    public ResponseEntity<Item> createItem(@RequestBody Item item){
        if(item.getPrice() <= 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Price must be greater than 0");
        Item createdItem = itemService.createItem(item);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdItem);
    }

    @DeleteMapping(path ="/{id}", produces = { "application/json", "application/xml"})
    public ResponseEntity<String> deletedById(@PathVariable("id") Long id){
        try {
            itemService.deleteItemById(id);
            return ResponseEntity.ok("Successfully deleted!");
        }
        catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PutMapping(produces = { "application/json", "application/xml"})
    public ResponseEntity<Item> updateItem(@RequestBody Item item, @RequestParam("id") Long id){
        Item updatedItem = itemService.getItemById(id);
        if(item == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item not found");
        updatedItem.setName(item.getName());
        updatedItem.setPrice(item.getPrice());
        updatedItem.setCategory(item.getCategory());
        itemService.updateItem(updatedItem);
        return ResponseEntity.ok(updatedItem);
    }

    @GetMapping(path = "/findByName", produces = {"application/json", "application/xml"})
    public ResponseEntity<Item> findByName(@RequestParam("name") String name){
        Item item = itemService.findByName(name);
        if(item == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item not found");
        else
            return ResponseEntity.ok(item);
    }

    @GetMapping(path="/findByCategory", produces = {"application/json", "application/xml"})
    public ResponseEntity<List<Item>> findByCategory(@RequestParam("category") Category category){
        List<Item> items = itemService.findByCategory(category);
        if(items.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Items not found");
        else
            return ResponseEntity.ok(items);
    }

    @GetMapping(path = "/findByPriceBetween", produces = {"application/json", "application/xml"})
    public ResponseEntity<List<Item>> findByPriceBetween(@RequestParam("min") int min, @RequestParam("max") int max){
        List<Item> items = itemService.findByPriceBetween(min, max);
        if(items.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Items not found");
        else
            return ResponseEntity.ok(items);
    }

    @GetMapping(path = "/hasPriceMoreThanCriteria", produces = {"application/json", "application/xml"})
    public ResponseEntity<List<Item>> hasPriceMoreThanCriteria(@RequestParam("price") double price){
        List<Item> items = itemService.hasPriceMoreThanCriteria(price);
        if(items.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Items not found");
        else
            return ResponseEntity.ok(items);
    }
}
