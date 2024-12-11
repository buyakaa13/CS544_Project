package org.example.coffeeshop.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public abstract class Employee {
    @Id@GeneratedValue
    public int id;
    public String firstname;
    public String lastname;
    public String username;
    public String role;
}
