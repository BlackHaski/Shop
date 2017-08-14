package shop.service;

import shop.entity.Category;
import shop.entity.products.Product;

import java.util.List;

public interface ProductService {
    List<String> findProductTypes();

    void save(Product product);

    void save(List<Product> product);

    List<Product> findAllByCategoryName(String categoryName);

    Product findByProductName(String productName);

    List<Product> findAllByProductNameIsContaining(String productName);

    void deleteByProductName(String productName);

    String findProductTypeByName(String name);

    List<Product> findAllByCategoryCategoryId(int categoryId);

    void delete(List<Product>products);

    void updateProductCategory(String productName,Category category);


}
