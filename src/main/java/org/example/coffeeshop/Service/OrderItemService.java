package org.example.coffeeshop.Service;

import org.example.coffeeshop.Repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService {
    @Autowired
    private OrderItemRepository orderItemRepo;

    public void deleteByOrderId(Long id){
        orderItemRepo.deleteByOrderId(id);
    }

    protected OrderItemService(){}
}
