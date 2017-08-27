package shop.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import shop.entity.products.Comment;
import shop.entity.products.Product;

import java.util.List;

public interface CommentDAO extends JpaRepository<Comment, Integer> {
    List<Comment> findByProduct(Product product);

    @Query("select c from Comment c join fetch c.user where c.product=:product order by c.date")
    List<Comment> findAllByProductWithUser(@Param("product") Product product);

    @Query(value = "select c from Comment c join fetch c.user where c.product=:product order by c.date",
    countQuery = "select count(c) from Comment c inner join c.user where c.product=:product")
    Page<Comment> findAllByProductWithUser(@Param("product") Product product, Pageable pageable);

    @Query("select c.commentId from Comment c where c=:comment")
    int findIdByComment(@Param("comment") Comment comment);
}
