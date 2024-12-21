package org.example.coffeeshop.Service;

import jakarta.persistence.OptimisticLockException;
import jakarta.transaction.Transactional;
import org.example.coffeeshop.Entity.*;
import org.example.coffeeshop.Repository.ItemRepository;
import org.example.coffeeshop.Repository.OrderItemRepository;
import org.example.coffeeshop.Repository.OrderRepository;
import org.example.coffeeshop.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;

    protected OrderService() {}

    public List<Order> getAll(){
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id){
        return orderRepository.findById(id).orElse(null);
    }

    public void deleteByOrderId(Long id){
        orderRepository.deleteById(id);
    }

    public void updateOrder(Order order){
        orderRepository.save(order);
    }

    public List<Order> findOrderByItemName(String itemName){
        return orderRepository.findOrderByItemName(itemName);
    }

    @Transactional
    public List<Order> findByYearAndMonth(int year, int month){
        return orderRepository.findByYearAndMonth(year,month);
    }

    public Order findMaxByTotalAmount(){
        return orderRepository.findMaxByTotalAmount();
    }

    @Transactional
    public void createOrder(OrderRequest orderRequest) {
        try{
            List<Item> items = itemRepository.findAllById(orderRequest.getItemIds());
            Users user = userRepository.findById(orderRequest.getUserId()).isPresent() ? userRepository.findById(orderRequest.getUserId()).get() : null;
            Order order = new Order(user, orderRequest.getStatus(), calculateTotalAmount(items));
            order = orderRepository.save(order);

            for(Item item: items){
                OrderItem orderItem = new OrderItem();
                orderItem.setOrder(order);
                orderItem.setItem(item);
                orderItemRepository.save(orderItem);
                order.getOrderItems().add(orderItem);
            }
            orderRepository.save(order);
        }
        catch (OptimisticLockException e){
            throw new RuntimeException("Optimistic Lock Exception Occurred!" + e.getMessage());
        }
    }

    private double calculateTotalAmount(List<Item> items) {
        return items.stream().mapToDouble(Item::getPrice).sum();
    }
}
