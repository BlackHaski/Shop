package shop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import shop.entity.products.Comment;
import shop.entity.products.Product;

import java.util.List;

public interface CommentDAO extends JpaRepository<Comment, Integer> {
    List<Comment> findByProduct(Product product);

    @Query("select c from Comment c  join fetch c.user where c.product=:product")
    List<Comment> findAllByProductWithUser(@Param("product") Product product);

    @Query("select c.commentId from Comment c where c=:comment")
    int findIdByComment(@Param("comment") Comment comment);
}
