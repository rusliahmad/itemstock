package com.example.itemstock;

import entity.Item;
import entity.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import service.OrderService;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTest {
    @Autowired
    private OrderService orderService;

    @Test
    public void testGetAllOrders() {
        List<Order> orders = orderService.getAllOrders(PageRequest.of(0, 10));
        assertNotNull(orders);
    }

    @Test
    public void testGetOrder() {
        Order order = orderService.getOrder(1L);
        assertNotNull(order);
    }

    @Test
    public void testSaveOrder() {
        Order order = new Order(new Item(1L), 10, "O1");
        Order savedOrder = orderService.saveOrder(order);
        assertNotNull(savedOrder);
    }

    @Test
    public void testEditOrder() {
        Order order = orderService.getOrder(1L);
        order.setQty(20);
        Order updatedOrder = orderService.editOrder(1L, order);
        assertNotNull(updatedOrder);
    }

    @Test
    public void testDeleteOrder() {
        orderService.deleteOrder(1L);
        assertNull(orderService.getOrder(1L));
    }
}