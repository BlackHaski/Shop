package shop.service;

import shop.entity.products.Product;
import shop.entity.products.SoldOut;
import shop.entity.security.User;

import java.util.List;

public interface SoldOutService {
    void save(SoldOut soldOut);

    SoldOut findByProductAndUser(Product product, User user);

    List<SoldOut> findProductByUser(User user);

}
