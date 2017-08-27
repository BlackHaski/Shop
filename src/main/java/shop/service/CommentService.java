package shop.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.entity.products.Comment;
import shop.entity.products.Product;

import java.util.List;

public interface CommentService {
    List<Comment> findByProduct(Product product);

    void save(Comment comment);

    List<Comment> findAllByProductWithUser(Product product);

    Page<Comment> findAllByProductWithUser(Product product, Pageable pageable);

    int findIdByComment(Comment comment);

    void delete(int id);

}
