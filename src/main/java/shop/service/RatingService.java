package shop.service;

import shop.entity.products.Product;
import shop.entity.products.Rating;

import java.util.List;

public interface RatingService {

    List<Rating> findByProduct(Product product);

}
