package shop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.entity.products.Comment;
import shop.entity.products.Product;

import java.util.List;

public interface CommentDAO extends JpaRepository<Comment,Integer>{
    List<Comment> findByProduct(Product product);
}
