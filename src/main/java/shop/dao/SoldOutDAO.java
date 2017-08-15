package shop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.entity.products.Product;
import shop.entity.products.SoldOut;
import shop.entity.security.User;

public interface SoldOutDAO extends JpaRepository<SoldOut,Integer> {
    SoldOut findByProductAndUser(Product product,User user);
}
