package shop.service;

import org.springframework.data.repository.query.Param;
import shop.entity.products.Product;

import java.util.List;

public interface ProductService {
    List<String> findProductTypes();

    void save(Product product);

    List<Product> findAllByCategoryName(@Param("categoryName") String categoryName);

    Product findAllByProductName(String productName);

}
