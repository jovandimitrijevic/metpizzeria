package rs.ac.metropolitan.it355.pz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.metropolitan.it355.pz.model.OrderStatus;
import rs.ac.metropolitan.it355.pz.repository.OrderStatusRepository;
import rs.ac.metropolitan.it355.pz.service.OrderStatusService;

import java.util.List;

@Service
public class OrderStatusServiceImpl implements OrderStatusService {

    OrderStatusRepository orderStatusRepository;

    @Autowired
    public OrderStatusServiceImpl(OrderStatusRepository orderStatusRepository) {
        this.orderStatusRepository = orderStatusRepository;
    }

    @Override
    public List<OrderStatus> getAll() {
        List<OrderStatus> statuses = orderStatusRepository.findAll();
        return statuses;
    }
}
