package geekbrains.service;


import geekbrains.entities.Order;
import geekbrains.entities.Product;
import geekbrains.persistence.Cart;
import geekbrains.repositories.CartEntryRepository;
import geekbrains.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@Data
@AllArgsConstructor(onConstructor = @__(@Autowired))
//@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartEntryRepository cartEntryRepository;
    private final ProductService productService;

    public Order newOrder() {
        Order order = new Order();
        orderRepository.save(order);
        return order;
    }


    public void addProduct(Cart cart, Product product, Integer quantity) {
        if (product != null) cart.getCartMap().merge(product, quantity, Integer::sum);
        if (cart.getCartMap().get(product) < 1) cart.getCartMap().remove(product);
    }

    public void addProductById(Cart cart, Long prodId, Integer quantity) {
        Product product = productService.findById(prodId).get();
        this.addProduct(cart, product, quantity);
    }

    public BigDecimal getSum(Cart cart) {
        BigDecimal sum = BigDecimal.valueOf(0);
        for (Map.Entry<Product, Integer> entry : cart.getCartMap().entrySet()) {
            sum = sum.add(entry.getKey().getPrice().multiply(BigDecimal.valueOf(entry.getValue())));
        }
        return sum;
    }

    public int getProductQuantity(Cart cart, Product product) {
        if (cart.getCartMap().containsKey(product)) {
            return cart.getCartMap().get(product);
        }
        return 0;
    }

    public Integer getItemsAmount(Cart cart) {
        Integer amount = 0;
        for (Map.Entry<Product, Integer> entryMap : cart.getCartMap().entrySet()) {
            amount += entryMap.getValue();
        }
        return amount;
    }

    public int getProductQuantity(Cart cart, Long prodId) {
        Product product = productService.findById(prodId).get();
        return this.getProductQuantity(cart, product);
    }

    public List<Product> getCartListSorted(Cart cart) {
        List<Product> cartList = new ArrayList<>(cart.getCartMap().keySet());
        Collections.sort(cartList, (p1, p2) -> {
            if (p1.getId() > p2.getId()) {
                return 1;
            } else if (p1.getId() < p2.getId()) {
                return -1;
            }
            return 0;
        });
        return cartList;
    }

}
