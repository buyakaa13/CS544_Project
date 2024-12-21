package org.example.coffeeshop.Repository;

import org.example.coffeeshop.Entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Payment findByOrderId(Long orderId);
    @Query("SELECT p FROM Payment p WHERE year(p.createdAt) = ?1 and month(p.createdAt) =?2")
    List<Payment> getAllByYearAndMonth(int month, int year);
    Payment findMax();
}
