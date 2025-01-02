package org.example.coffeeshop.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "item")
public class Item {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Enumerated(EnumType.STRING)
    private Category category;
    @Column(nullable = false)
    private double price;
    @Column(nullable = false)
    private int quantity;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonManagedReference
    private List<OrderItem> orderItems;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

//    public List<OrderItem> getOrderItems() {
//        return orderItems;
//    }
//
//    public void setOrderItems(List<OrderItem> orderItems) {
//        this.orderItems = orderItems;
//    }
}
