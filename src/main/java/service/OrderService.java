package service;

import entity.Item;
import entity.Order;
import exception.InsufficientStockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import repository.ItemRepository;
import repository.OrderRepository;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ItemRepository itemRepository;

    public List<Order> getAllOrders(Pageable pageable) {
        return (List<Order>) orderRepository.findAll(pageable);
    }

    public Order getOrder(Long id) {
        return orderRepository.findById(id).orElseThrow();
    }

    public Order saveOrder(Order order) {
        Item item = itemRepository.findById(order.getItem().getId()).orElseThrow();
        if (item.getStock() < order.getQty()) {
            throw new InsufficientStockException();
        }
        item.setStock(item.getStock() - order.getQty());
        itemRepository.save(item);
        return orderRepository.save(order);
    }

    public Order editOrder(Long id, Order order) {
        Order existingOrder = getOrder(id);
        existingOrder.setQty(order.getQty());
        return saveOrder(existingOrder);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}