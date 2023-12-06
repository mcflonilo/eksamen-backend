package com.example.eksamen_backend.order;

import com.example.eksamen_backend.model.Customer;
import com.example.eksamen_backend.model.Order;
import com.example.eksamen_backend.repository.OrderRepository;
import com.example.eksamen_backend.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(properties = {"command.line.runner.enabled=false"})
public class OrderServiceUnitTest {
    @MockBean
    private OrderRepository orderRepo;
    @Autowired
    private OrderService orderService;

    @Test
    void shouldGetOrderById() {
        var order = new Order();
        when(orderRepo.findById(1L)).thenReturn(Optional.of(order));
        var foundOrder = orderService.getOrderById(1L);
        assert foundOrder == order;
    }

    //https://stackoverflow.com/questions/55448188/spring-boot-pagination-mockito-repository-findallpageable-returns-null
    @Test
    void shouldGetOrdersPageable() {
        var order = new Order();
        Page<Order> orders = new PageImpl<>(List.of(order));
        when(orderRepo.findAll(org.mockito.ArgumentMatchers.isA(Pageable.class))).thenReturn(orders);
        var foundOrder = orderService.getOrdersPageable(0);
        assert foundOrder.equals( orders.stream().toList());
    }
    @Test
    void shouldAddOrder() {
        var order = new Order();
        when(orderRepo.save(order)).thenReturn(order);
        var savedOrder = orderService.addOrder(order);
        assert savedOrder == order;
    }
    @Test
    void shouldDeleteOrder() {
        var order = new Order();
        when(orderRepo.save(order)).thenReturn(order);
        order = orderService.addOrder(order);
        when(orderRepo.findById(order.getOrderId())).thenReturn(Optional.of(order));
        orderService.deleteOrder(order.getOrderId());
        verify(orderRepo).deleteById(order.getOrderId());
    }
    @Test
    void shouldUpdateOrder() {
        var order = new Order();
        when(orderRepo.save(order)).thenReturn(order);
        var savedOrder = orderService.addOrder(order);
        savedOrder.setCustomer(new Customer("test unitTestesen2"));
        when(orderRepo.save(savedOrder)).thenReturn(savedOrder);
        var updatedOrder = orderService.updateOrder(savedOrder);
        assert updatedOrder.getCustomer().getCustomerName() == "test unitTestesen2";
    }
}
