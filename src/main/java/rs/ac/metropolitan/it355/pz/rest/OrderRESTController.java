package rs.ac.metropolitan.it355.pz.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rs.ac.metropolitan.it355.pz.model.Order;
import rs.ac.metropolitan.it355.pz.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderRESTController {

    private final OrderService orderService;

    @Autowired
    public OrderRESTController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public List<Order> getAllOrders() {
        return orderService.getAllOrder();
    }

    @PostMapping("/orders")
    public String saveUser(@RequestBody Order order) {
        orderService.save(order);
        return null;
    }

    @GetMapping("/orders/{userID}")
    public List<Order> getUserOrders(@PathVariable int userID) {
        return orderService.getAllOrderByUser(userID);
    }
}
