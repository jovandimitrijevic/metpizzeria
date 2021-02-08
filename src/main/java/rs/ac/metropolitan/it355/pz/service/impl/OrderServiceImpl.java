package rs.ac.metropolitan.it355.pz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.metropolitan.it355.pz.model.Order;
import rs.ac.metropolitan.it355.pz.repository.OrderRepository;
import rs.ac.metropolitan.it355.pz.service.OrderService;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void save(Order order) {
        orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrder() {
        List<Order> orders = orderRepository.findAll();
        return orders;
    }

    @Override
    public List<Order> getAllOrderByUser(int id) {
        List<Order> userOrder = orderRepository.findByUsers_Id(id);
        return userOrder;
    }

    @Override
    public void updateOrder(int statusID, int orderID) {
        orderRepository.updateOrder(statusID, orderID);
    }

    @Override
    public Order getOrderById(int orderID) {
        Optional<Order> orderResult = orderRepository.findById(orderID);

        Order order = null;
        if (orderResult.isPresent()) {
            order = orderResult.get();
        } else {
            throw new RuntimeException("There is no order with id: " + orderID);
        }
        return order;
    }
}
