package org.example.coffeeshop.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST, orphanRemoval = true)
    @JsonManagedReference
    private List<OrderItem> orderItems;

    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Users> users;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;

    @Column(nullable = false)
    private double totalAmount;

    @Column(updatable = false)
    private LocalDate createdAt = LocalDate.now();

//    @Version
//    private Long version;

    public Order() {}

    public Order(Users user, OrderStatus status, double totalAmount) {
        this.orderItems = new ArrayList<>();
        if(users == null)
            users = new ArrayList<>();
        this.users.add(user);
        this.status = status;
        this.totalAmount = totalAmount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public List<Users> getUsers() {
        return users;
    }

    public void setUsers(List<Users> users) {
        this.users = users;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void addUser(Users user){
        this.users.add(user);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", items=" + orderItems +
                ", user=" + users.getFirst() +
                ", status=" + status +
                ", totalAmount=" + totalAmount +
                ", createdAt=" + createdAt +
                '}';
    }
}

