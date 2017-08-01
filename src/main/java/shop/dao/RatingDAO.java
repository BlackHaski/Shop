package shop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.entity.products.Product;
import shop.entity.products.Rating;

import java.util.List;

public interface RatingDAO extends JpaRepository<Rating, Integer> {

    List<Rating> findByProduct(Product product);
}
