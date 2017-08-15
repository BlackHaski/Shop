package shop.service;

import shop.entity.products.Product;
import shop.entity.products.SoldOut;
import shop.entity.security.User;

public interface SoldOutService {
    void save(SoldOut soldOut);

    SoldOut findByProductAndUser(Product product,User user);

}
