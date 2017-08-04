package shop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import shop.entity.products.Product;
import shop.entity.products.Rating;

import java.util.List;

public interface RatingDAO extends JpaRepository<Rating, Integer> {

    @Query("select r from Rating r  join fetch r.user where r.product=:product")
    List<Rating> findAllByProductWithUser(@Param("product") Product product);

    Rating findByProductProductNameAndUserUsername(String productName, String username);
}
