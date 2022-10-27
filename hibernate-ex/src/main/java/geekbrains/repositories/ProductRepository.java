package geekbrains.repositories;


import geekbrains.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    public List<Product> findAllByPriceGreaterThan(BigDecimal minCost);

    public List<Product> findAllByPriceLessThan(BigDecimal maxCost);

    public List<Product> findAllByPriceBetween(BigDecimal minCost, BigDecimal maxCost);

    public List<Product> findAllSortedByName(String title);
}
