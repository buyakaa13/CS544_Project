package org.example.coffeeshop.Repository;

import jakarta.persistence.LockModeType;
import org.example.coffeeshop.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query(value = "SELECT o FROM Order o WHERE year(o.createdAt) = ?1 AND month(o.createdAt) = ?2")
    List<Order> findByYearAndMonth(int year, int month);
    @Query("SELECT o FROM Order o ORDER BY o.totalAmount DESC LIMIT 1")
    Order findMaxByTotalAmount();

    @Query(value = "select A.* From orders A\n" +
            "inner join order_item B ON a.id = B.order_id\n" +
            "inner join item C ON B.item_id = C.id\n" +
            "where C.name = \"Cappucino\" ", nativeQuery = true)
    List<Order> findOrderByItemName(String name);
}
