package shop.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.entity.Category;
import shop.entity.products.Product;

import java.util.List;

public interface ProductService {
    List<String> findProductTypes();

    void save(Product product);

    void save(List<Product> product);

    List<Product> findAllByCategoryName(String categoryName);

    Page<Product> findAllByCategoryCategoryName(String categoryName, Pageable pageable);

    Product findByProductName(String productName);

    List<Product> findAllByProductNameIsContaining(String productName);

    Page<Product> findAllByProductNameIsContaining(String productName, Pageable pageable);

    void deleteByProductName(String productName);

    String findProductTypeByName(String name);

    List<Product> findAllByCategoryCategoryId(int categoryId);

    void delete(List<Product>products);

    void updateProductCategory(String productName,Category category);

    void deleteImg(String img);

}
