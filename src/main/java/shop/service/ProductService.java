package shop.service;

import shop.entity.products.Product;

import java.util.List;

public interface ProductService {
    List<String> findProductTypes();

    void save(Product product);

    List<Product> findAllByCategoryName(String categoryName);

    Product findByProductName(String productName);


}
