package geekbrains.repositories;

import geekbrains.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findOrdersByUserId(Long userId);

    @Query(value = "SELECT o FROM Order o WHERE o.id = :orderId")
    Order findOrderByOrderId (Long orderId);

}
