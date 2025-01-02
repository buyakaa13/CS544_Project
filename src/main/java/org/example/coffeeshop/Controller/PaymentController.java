package org.example.coffeeshop.Controller;

import org.example.coffeeshop.Entity.Payment;
import org.example.coffeeshop.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @GetMapping(produces = { "application/json", "application/xml"})
    public ResponseEntity<List<Payment>> getAll(){
        List<Payment> payments = paymentService.getAll();
        if(payments.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Payment not found");
        else
            return ResponseEntity.ok(payments);
    }

    @GetMapping(path="/{id}", produces = { "application/json", "application/xml"})
    public ResponseEntity<Payment> getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        if(payment == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Payment not found");
        else
            return ResponseEntity.ok(payment);
    }

    @PostMapping(produces = { "application/json", "application/xml"})
    public ResponseEntity<Payment> createPayment(@RequestBody Payment payment){
        if(payment.getAmount() <= 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Amount must be greater than 0");
        Payment createdPayment = paymentService.createPayment(payment);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPayment);
    }

    @DeleteMapping(path ="/{id}", produces = { "application/json", "application/xml"})
    public ResponseEntity<Void> deletedById(@PathVariable("id") Long id){
        try {
            paymentService.deletePaymentById(id);
            return ResponseEntity.noContent().build();
        }
        catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PutMapping(produces = { "application/json", "application/xml"})
    public ResponseEntity<Payment> updatePayment(@RequestBody Payment payment, @RequestParam("id") Long id){
        Payment updatedPayment = paymentService.getPaymentById(id);
        if(payment == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Payment not found");
        updatedPayment.setAmount(payment.getAmount());
        updatedPayment.setType(payment.getType());
        paymentService.updatePayment(updatedPayment);
        return ResponseEntity.ok(updatedPayment);
    }

    @GetMapping(path="/findByOrderId", produces = {"application/json", "application/xml"})
    public ResponseEntity<Payment> findByOrderId(@RequestParam("orderId") Long orderId){
        Payment payment = paymentService.findByOrderId(orderId);
        if(payment == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Payment not found");
        else
            return ResponseEntity.ok(payment);
    }

    @GetMapping(path = "/getAllByYearAndMonth", produces = {"application/json", "application/xml"})
    @Secured({"ROLE_MANAGER"})
    public ResponseEntity<List<Payment>> getAllByYearAndMonth(@RequestParam("year") int year, @RequestParam("month") int month){
        List<Payment> payments = paymentService.getAllByYearAndMonth(year, month);
        if(payments.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Payments not found");
        else
            return ResponseEntity.ok(payments);
    }

    @GetMapping(path = "/findMax", produces = {"application/json", "application/xml"})
    public ResponseEntity<Payment> findMax(){
        Payment payment = paymentService.findMax();
        if(payment == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Payment not found");
        else
            return ResponseEntity.ok(payment);
    }

}
