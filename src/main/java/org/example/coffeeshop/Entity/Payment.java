package org.example.coffeeshop.Entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "payment")
@NamedQuery(name = "Payment.findMax", query = "SELECT p FROM Payment p ORDER BY p.amount DESC LIMIT 1")
public class Payment {
    @Id@GeneratedValue
    private Long id;
    @Enumerated(EnumType.STRING)
    private PaymentType type;
    private double amount;
    @Temporal(TemporalType.DATE)
    private Date createdAt;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "m_order_id", referencedColumnName = "id")
    private Order order;

    public PaymentType getType() {
        return type;
    }

    public void setType(PaymentType type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
