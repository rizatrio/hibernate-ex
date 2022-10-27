package geekbrains.service;

import geekbrains.entities.Product;
import geekbrains.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll() {return productRepository.findAll();}
    public List<Product> getAllByCostGreaterThan(BigDecimal minCost){return productRepository.findAllByPriceGreaterThan(minCost);}
    public List<Product> getAllByCostLessThan(BigDecimal maxCost){return productRepository.findAllByPriceLessThan(maxCost);}
    public List<Product> getAllByCostBetween(BigDecimal minCost, BigDecimal maxCost){return productRepository.findAllByPriceBetween(minCost, maxCost);}

    public List<Product> getAllByTitleContains(String title){return productRepository.findAllSortedByName(title);}

    public Product getById(long id){return productRepository.getOne(id);}

    public Product save(Product product){return productRepository.save(product);}

    public void deleteById(long id){productRepository.deleteById(id);}
}
