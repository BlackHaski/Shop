package shop.service;

import shop.entity.products.Product;
import shop.entity.products.Rating;

import java.util.List;

public interface RatingService {

    List<Rating> findAllByProductWithUser(Product product);

    Rating findByProductProductNameAndUserUsername(String productName, String username);

    void save(Rating rating);

    void delete(Rating rating);
}
