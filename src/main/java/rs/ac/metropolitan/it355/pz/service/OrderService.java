package rs.ac.metropolitan.it355.pz.service;

import rs.ac.metropolitan.it355.pz.model.Order;

import java.util.List;

public interface OrderService {
    void save(Order order);

    List<Order> getAllOrder();

    List<Order> getAllOrderByUser(int id);

    void updateOrder(int statusID, int orderID);

    Order getOrderById(int orderID);

}
