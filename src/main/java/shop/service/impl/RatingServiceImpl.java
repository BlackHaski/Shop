package shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.dao.RatingDAO;
import shop.entity.products.Product;
import shop.entity.products.Rating;
import shop.service.RatingService;

import java.util.List;

@Service
@Transactional
public class RatingServiceImpl implements RatingService{
    @Autowired
    RatingDAO ratingDAO;

    @Override
    public List<Rating> findAllByProductWithUser(Product product) {
        return ratingDAO.findAllByProductWithUser(product);
    }

    @Override
    public Rating findByProductProductNameAndUserUsername(String productName, String username) {
        return ratingDAO.findByProductProductNameAndUserUsername(productName,username);
    }

    @Override
    public void save(Rating rating) {
        ratingDAO.save(rating);
    }

    @Override
    public void delete(Rating rating) {
        ratingDAO.delete(rating);
    }
}
