package org.example.coffeeshop.Controller;

import org.example.coffeeshop.Entity.*;
import org.example.coffeeshop.JMS.MessageProducer;
import org.example.coffeeshop.Service.OrderItemService;
import org.example.coffeeshop.Service.OrderService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private MessageProducer messageProducer;

    @Autowired
    private MessageSource messageSource;

    protected OrderController() {}

    @GetMapping(produces = { "application/json", "application/xml"})
    public ResponseEntity<List<Order>> getAll(){
        List<Order> orders = orderService.getAll();
        if(orders.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Orders not found");
        else
            return ResponseEntity.ok(orders);
    }

    @GetMapping(path="/{id}", produces = { "application/json", "application/xml"})
    public ResponseEntity<Order> getOrderById(@PathVariable Long id){
        Order order = orderService.getOrderById(id);
        if(order == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found");
        else
            return ResponseEntity.ok(order);
    }

//    @PostMapping(produces = { "application/json", "application/xml"})
//    private ResponseEntity<?> createOrder(@RequestHeader(name="Accept-Language", required = false) Locale locale, @RequestBody OrderRequest order){
//        if(order.getTotalAmount() <= 0)
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Total amount must be greater than 0 and items must be not null");
//        orderService.createOrder(order);
//        String message = messageSource.getMessage("order.received", null, locale);
//        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
//                "status", HttpStatus.CREATED,
//                "message", message
//        ));
//    }

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody OrderRequest request) {
        if(request.getTotalAmount() <= 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Total amount must be greater than 0 and items must be not null");
        orderService.createOrder(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("Order created successfully!");
    }

    @DeleteMapping(path ="/{id}", produces = { "application/json", "application/xml"})
    private ResponseEntity<String> deletedById(@PathVariable Long id){
        try {
            orderService.deleteByOrderId(id);
            return ResponseEntity.ok("Successfully deleted!");
        }
        catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PutMapping(produces = { "application/json", "application/xml"})
    private ResponseEntity<Order> updateOrder(@RequestBody Order order, @RequestParam Long id){
        Order updatedOrder = orderService.getOrderById(id);
        if(order == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found");
        updatedOrder.setOrderItems(order.getOrderItems());
        updatedOrder.setStatus(order.getStatus());
        updatedOrder.setUsers(order.getUsers());
        updatedOrder.setTotalAmount(order.getTotalAmount());
        orderService.updateOrder(updatedOrder);
        return ResponseEntity.ok(updatedOrder);
    }

    @GetMapping(path = "/findByYearAndMonth", produces = {"application/json", "application/xml"})
    @Secured({"ROLE_MANAGER"})
    public ResponseEntity<List<Order>> findByYearAndMonth(@RequestParam int year, @RequestParam int month){
        List<Order> orders = orderService.findByYearAndMonth(year, month);
        if(orders.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Orders not found");
        else
            return ResponseEntity.ok(orders);
    }

    @GetMapping(path = "/findMaxByTotalAmount", produces = {"application/json", "application/xml"})
    @Secured("ROLE_CASHIER")
    public ResponseEntity<Order> findMaxByTotalAmount(){
        Order order = orderService.findMaxByTotalAmount();
        if(order == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found");
        else
            return ResponseEntity.ok(order);
    }

    @Value("${orderQueue}")
    String orderQueue;

    @PostMapping(path = "/senNotif", produces = {"application/json", "application/xml"})
    public void sendNotification(@RequestHeader(name = "Accept-Language", required = false) Locale locale, @RequestParam String orderId) throws JSONException {
        JSONObject obj = new JSONObject();
        obj.put("id", orderId);
        obj.put("message", messageSource.getMessage("order.ready", null, locale));
        messageProducer.sendMessage(orderQueue, obj);
    }

    @GetMapping(path = "/findOrderByItemName", produces = {"application/json", "application/xml"})
    public ResponseEntity<List<Order>> findOrderByItemName(@RequestParam String itemName){
        List<Order> orders = orderService.findOrderByItemName(itemName);
        if(orders.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Orders not found");
        else
            return ResponseEntity.ok(orders);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        String errorMessage = messageSource.getMessage("error.general", null, locale);
        return ResponseEntity.badRequest().body(Map.of(
                "status", "error",
                "message", errorMessage,
                "error", e.getMessage()
        ));
    }
}
