package shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;
import shop.entity.products.Comment;
import shop.entity.products.Product;
import shop.entity.products.Rating;
import shop.entity.security.User;
import shop.functions.Cart;
import shop.service.CommentService;
import shop.service.ProductService;
import shop.service.RatingService;
import shop.service.UserService;

import java.util.List;
import java.util.Map;

/**
 * Created by blackhaski on 21.06.17.
 */
@Controller
@SessionAttributes({"user", "cart"})
public class InitController {
    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;
    @Autowired
    CommentService commentService;
    @Autowired
    RatingService ratingService;

    @GetMapping("/")
    public String start() {
        return "main";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/contact")
    public String contacts() {
        return "contact";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("isLogin", false);
        return "registration/registration";
    }

    @GetMapping("/login")
    public String loginUser(Model model) {
        model.addAttribute("isLogin", true);
        Cart cart = new Cart();
        model.addAttribute("cart", cart);
        return "registration/registration";
    }

    @GetMapping("/product-{productName}")
    public String product(@PathVariable String productName, Model model) {
        Product product = productService.findByProductName(String.valueOf(productName));
        List<Rating> ratings = ratingService.findAllByProductWithUser(product);
        List<Comment> comments = commentService.findAllByProductWithUser(product);

        int pos = 0;
        int neg = 0;
        for (Rating rating : ratings) {
            if (rating.isRating())
                pos++;
            else neg++;
        }
        model.addAttribute("posRating", pos);
        model.addAttribute("negRating", neg);
        model.addAttribute("comments", comments);
        return "product";
    }

    @GetMapping("/shopcart")
    public String shopcart(Model model) {
        Cart cart = (Cart) model.asMap().get("cart");
        int resultSum = 0;
        for (Map.Entry<Product, Integer> entry : cart.getProducts().entrySet()) {
            if (entry.getValue() > entry.getKey().getCount()) {
                entry.setValue(entry.getKey().getCount());
            }
            resultSum += entry.getKey().getPrice() * entry.getValue();
        }
        model.addAttribute("resultSum", resultSum);
        return "shopcart";
    }

    @GetMapping("/cabinet-{username}")
    public String cabinet(@PathVariable String username, Model model) {
        User principal = null;
        User currUser = (User) userService.loadUserByUsername(username);
        if (currUser != null) {
            if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() != "anonymousUser") {
                principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            }
            if (principal != null && principal.getUsername().equals(username))
                model.addAttribute("canEdit", true);
            else {
                model.addAttribute("canEdit", false);
            }
            model.addAttribute("user", userService.findByUsernameWithDetails(username));
            return "cabinet/cabinet";
        }
        return "cabinet/emptyCabinet";
    }

}
