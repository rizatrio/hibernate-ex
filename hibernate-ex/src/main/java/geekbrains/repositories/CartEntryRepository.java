package geekbrains.repositories;

import geekbrains.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartEntryRepository extends JpaRepository<Order, Long> {
}

