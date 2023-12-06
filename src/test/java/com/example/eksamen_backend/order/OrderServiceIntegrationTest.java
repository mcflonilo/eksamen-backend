package com.example.eksamen_backend.order;

import com.example.eksamen_backend.model.Customer;
import com.example.eksamen_backend.model.Order;
import com.example.eksamen_backend.service.OrderService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OrderServiceIntegrationTest {
    @Autowired
    private OrderService orderService;

    @Test
    @Transactional
    void shouldAddAndDeleteOrder() {
        var order = new Order();
        order = orderService.addOrder(order);
        var orders = orderService.getOrdersPageable(0);
        System.out.println(orders.size());
        assert orders.size() == 5;
        orderService.deleteOrder( order.getOrderId());
        orders = orderService.getOrdersPageable(0);
        assert orders.size() == 4;
    }
    @Test
    @Transactional
    void shouldUpdateAndGetById() {
        var customer = new Customer("test customer");
        var order = new Order();

        order = orderService.addOrder(order);
        order.setCustomer(customer);
        order = orderService.updateOrder(order);
        System.out.println(order.getCustomer().getCustomerName());
        System.out.println(customer.getCustomerName());
        assert orderService.getOrderById(order.getOrderId())
                .getCustomer()
                .getCustomerName()
                .equals(customer.getCustomerName());
    }

}
