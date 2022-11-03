package geekbrains;



import geekbrains.entities.Product;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public final class ProductSpecifications {
    public static Specification<Product> productPrefix(String prefix) {
        return (root, query, builder) -> builder.like(root.get("name"), prefix + "%");
    }

    public static Specification<Product> minPrice(BigDecimal minPrice) {
        return (root, query, builder) -> builder.ge(root.get("price"), minPrice);
    }

    public static Specification<Product> maxPrice(BigDecimal maxPrice) {
        return (root, query, builder) -> builder.le(root.get("price"), maxPrice);
    }
}