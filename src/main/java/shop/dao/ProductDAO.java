package shop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import shop.entity.products.Product;

import java.util.List;

public interface ProductDAO extends JpaRepository<Product, Integer> {

    @Query("select distinct p.productType from Product p")
    List<String> findProductTypes();

    @Query("from Product p where p.category.categoryName=:categoryName")
    List<Product> findAllByCategoryName(@Param("categoryName") String categoryName);

    Product findByProductName(String productName);

//    @Query("select p from Product p where lower(p.productName) like lower(%:productName%) ")
    List<Product> findAllByProductNameIsContaining(/*@Param("productName")*/ String productName);

}
