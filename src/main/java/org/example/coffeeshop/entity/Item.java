package org.example.coffeeshop.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Item {
    @Id@GeneratedValue
    public int id;
    public String name;
    public Category category;
    public double price;
    public int quantity;
}
