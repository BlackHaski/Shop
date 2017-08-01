package shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Comment> findByProduct(Product product) {
        return commentDAO.findByProduct(product);
    }
}
