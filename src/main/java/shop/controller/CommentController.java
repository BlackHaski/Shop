package shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import shop.entity.products.Comment;
import shop.entity.products.Product;
import shop.entity.security.User;
import shop.service.CommentService;
import shop.service.ProductService;
import shop.service.UserService;

import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CommentController {
    @Autowired
    ProductService productService;
    @Autowired
    UserService userService;
    @Autowired
    CommentService commentService;

    @MessageMapping("/comment")
    @SendTo("/topic/product")
    public Map<String, String> comment(Map<String, String> params, Principal principal) {
        if (principal != null) {
            Date currentDate = new Date();
            Product product = productService.findByProductName(params.get("product"));
            User user = (User) userService.loadUserByUsername(principal.getName());
            Comment comment = new Comment(params.get("message"), currentDate, product, user);

            commentService.save(comment);

            Map<String, String> response = new HashMap<>();
            response.put("username", user.getUsername());
            response.put("comment", comment.getText());
            response.put("commentId", String.valueOf(commentService.findIdByComment(comment)));
            response.put("date", String.valueOf(comment.getDate()));
            return response;
        }else {
            return null;
        }
    }
}
