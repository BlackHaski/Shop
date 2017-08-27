package shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.dao.CommentDAO;
import shop.entity.products.Comment;
import shop.entity.products.Product;
import shop.service.CommentService;

import java.util.List;

@Service
@Transactional
public class CommentServiceImpl implements CommentService{

    @Autowired
    CommentDAO commentDAO;

    @Override
    public Page<Comment> findAllByProductWithUser(Product product, Pageable pageable) {
        return commentDAO.findAllByProductWithUser(product,pageable);
    }

    @Override
    public List<Comment> findByProduct(Product product) {
        return commentDAO.findByProduct(product);
    }

    @Override
    public void save(Comment comment) {
        commentDAO.save(comment);
    }

    @Override
    public List<Comment> findAllByProductWithUser(Product product) {
        return commentDAO.findAllByProductWithUser(product);
    }

    @Override
    public int findIdByComment(Comment comment) {
        return commentDAO.findIdByComment(comment);
    }

    @Override
    public void delete(int id) {
        commentDAO.delete(id);
    }
}
