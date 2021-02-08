package rs.ac.metropolitan.it355.pz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import rs.ac.metropolitan.it355.pz.model.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByUsers_Id(int userID);

    @Modifying
    @Query(value = "UPDATE order o SET o.status_id = ?1 WHERE order_id = ?2", nativeQuery = true)
    @Transactional(rollbackFor = Exception.class)
    void updateOrder(int statusID, int orderID);
}
