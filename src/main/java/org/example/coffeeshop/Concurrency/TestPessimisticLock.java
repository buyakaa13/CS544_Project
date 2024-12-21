package org.example.coffeeshop.Concurrency;

import jakarta.transaction.Transactional;
import org.example.coffeeshop.Service.OrderService;

public class TestPessimisticLock {
    OrderService orderService;

    @Transactional
    public void testPessimisticLocking() {
        new Thread(() -> orderService.findByYearAndMonth(2024, 12)).start();
        new Thread(() -> orderService.findByYearAndMonth(2024, 11)).start();
    }

}
