package shop.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import shop.entity.Category;
import shop.entity.products.Product;

import java.util.List;

public interface ProductDAO extends JpaRepository<Product, Integer> {

    @Query("select distinct p.productType from Product p")
    List<String> findProductTypes();

    @Query("select p.productType from Product p where p.productName=:name")
    String findProductTypeByName(@Param("name") String name);

    @Query("from Product p where p.category.categoryName=:categoryName")
    List<Product> findAllByCategoryName(@Param("categoryName") String categoryName);

    Page<Product> findAllByCategoryCategoryName(String categoryName, Pageable pageable);

    Product findByProductName(String productName);

    List<Product> findAllByProductNameIsContaining(String productName);

    Page<Product> findAllByProductNameIsContaining(String productName, Pageable pageable);

    void deleteByProductName(String productName);

    List<Product> findAllByCategoryCategoryId(int categoryId);

    @Modifying
    @Query("update Product p set p.category=:category where p.productName=:productName")
    void updateProductCategory(@Param("productName") String productName,@Param("category") Category category);

    @Modifying
    @Query(value = "delete Product_images FROM Product_images WHERE Product_images.images =:img",nativeQuery = true)
    void deleteImg(@Param("img") String img);
}
