package shop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import shop.entity.products.Product;
import shop.entity.products.SoldOut;
import shop.entity.security.User;

import java.util.List;

public interface SoldOutDAO extends JpaRepository<SoldOut,Integer> {
    SoldOut findByProductAndUser(Product product,User user);

    @Query("select s from SoldOut s join fetch s.product where s.user=:user")
    List<SoldOut> findProductByUser(@Param("user") User user);
}
