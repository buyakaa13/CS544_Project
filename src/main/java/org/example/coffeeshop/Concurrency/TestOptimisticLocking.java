package org.example.coffeeshop.Concurrency;

import jakarta.transaction.Transactional;
import org.example.coffeeshop.Entity.OrderRequest;
import org.example.coffeeshop.Entity.OrderStatus;
import org.example.coffeeshop.Service.OrderService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TestOptimisticLocking {
    private OrderService orderService;

    @Transactional
    public void testOptimisticLocking() {
        List<Long> itemIds = new ArrayList<>();
        itemIds.add(1L);
        itemIds.add(2L);
        orderService.createOrder(new OrderRequest(itemIds, 1l, OrderStatus.PENDING, 20, LocalDate.now()));
        orderService.createOrder(new OrderRequest(itemIds, 1l, OrderStatus.CANCELED, 13, LocalDate.now()));
        orderService.createOrder(new OrderRequest(itemIds, 1l, OrderStatus.PAID, 43, LocalDate.now()));
    }

}
