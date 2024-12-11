package org.example.coffeeshop.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Payment {
    @Id@GeneratedValue
    private int id;
    private PaymentType type;
    private double amount;
}
