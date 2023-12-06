package com.example.eksamen_backend.service;

import com.example.eksamen_backend.model.Order;
import com.example.eksamen_backend.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    //get one by id
    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }
    //get all with pagination
    public List<Order> getOrdersPageable(int pageNumber) {
        return orderRepository.findAll(PageRequest.of(pageNumber, 10)).stream().toList();
    }
    //create one
    public Order addOrder(Order order) {
        return orderRepository.save(order);
    }
    //delete one
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
    //update one
    public Order updateOrder(Order order) {
        return orderRepository.save(order);
    }


}
