package org.example.coffeeshop.Service;

import org.example.coffeeshop.Entity.Payment;
import org.example.coffeeshop.Repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    protected PaymentService(){}

    public List<Payment> getAll(){
        return paymentRepository.findAll();
    }

    public Payment createPayment(Payment payment){
        return paymentRepository.save(payment);
    }

    public Payment getPaymentById(Long id){
        return paymentRepository.findById(id).orElse(null);
    }

    public void deletePaymentById(Long id){
        paymentRepository.deleteById(id);
    }

    public void updatePayment(Payment payment){
        paymentRepository.save(payment);
    }

    public Payment findByOrderId(Long orderId){
        return paymentRepository.findByOrderId(orderId);
    }

    public List<Payment> getAllByYearAndMonth(int year, int month){
        return paymentRepository.getAllByYearAndMonth(year,month);
    }

    public Payment findMax(){
        return paymentRepository.findMax();
    }
}
