package org.example.coffeeshop.Entity;

import java.time.LocalDate;
import java.util.List;

public class OrderRequest {
    private List<Long> itemIds;
    private Long userId;
    private OrderStatus status;
    private double totalAmount;
    private LocalDate createdAt;

    public OrderRequest(List<Long> itemIds, Long userId, OrderStatus status, double totalAmount, LocalDate createdAt) {
        this.itemIds = itemIds;
        this.userId = userId;
        this.status = status;
        this.totalAmount = totalAmount;
        this.createdAt = createdAt;
    }

    public List<Long> getItemIds() {
        return itemIds;
    }

    public void setItemIds(List<Long> itemIds) {
        this.itemIds = itemIds;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    @Override
    public String toString() {
        return "OrderRequest{" +
                "itemIds=" + itemIds +
                ", userId=" + userId +
                ", status=" + status +
                ", totalAmount=" + totalAmount +
                ", createdAt=" + createdAt +
                '}';
    }
}
