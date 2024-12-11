package org.example.coffeeshop.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Order {
    @Id @GeneratedValue
    private int id;
    @OneToMany
    private List<Item> items;
    @OneToOne
    private Employee employee;
    private OrderStatus status;
    private double totalAmount;

}
