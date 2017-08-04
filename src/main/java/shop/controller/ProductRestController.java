package shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import shop.entity.products.Comment;
import shop.entity.products.Product;
import shop.entity.products.Rating;
import shop.entity.security.User;
import shop.service.CommentService;
import shop.service.ProductService;
import shop.service.RatingService;
import shop.service.UserService;

import java.lang.reflect.Field;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ProductRestController {
    @Autowired
    ProductService productService;
    @Autowired
    CommentService commentService;
    @Autowired
    RatingService ratingService;
    @Autowired
    UserService userService;

    @PostMapping("/getProductTypes")
    public List<String> getProductTypes() {
//        List<String> types = productService.findProductTypes();
        List<String> types = new ArrayList<>();
        types.add("Pc");
        types.add("Human");
        return types;
    }

    @PostMapping("/getIndividualCharact")
    public List<String> getIndividualCharact(@RequestBody String name, Model model) {
        String path = "shop.entity.products." + name;
        String resultPath = path.replaceAll("\"", "");
        Class entity = null;
        try {
            entity = Class.forName(resultPath);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Field[] fields = entity.getDeclaredFields();
        List<String> listFields = new ArrayList<String>();
        for (Field field : fields) {
            listFields.add(field.getName());
        }
        System.out.println(listFields);
        model.addAttribute("tableName", name);
        return listFields;
    }

    @PostMapping("/getCurrentProduct")
    public Map<String, Object> getCurrentProduct(@RequestBody StringBuilder name) {
        Map<String, Object> result = new HashMap<>();

        Product product = productService.findByProductName(String.valueOf(name));
        List<Rating> ratings = ratingService.findAllByProductWithUser(product);
        List<Comment> comments = commentService.findByProduct(product);

        result.put("product", product);
        result.put("ratings", ratings);
        result.put("comments", comments);

        return result;
    }

    @PostMapping("/setProductRating")
    public String setProductRating(@RequestBody Map<String, String> params, Principal principal) {
        if (principal != null) {
            String productName = params.get("nameP");
            boolean typeRating = Boolean.parseBoolean(params.get("rating"));

            Rating currentRating = ratingService.findByProductProductNameAndUserUsername(productName, principal.getName());

            User user = (User) userService.loadUserByUsername(principal.getName());
            Product product = productService.findByProductName(productName);

            String result = "";
            if (currentRating == null) {
                Rating rating = typeRating ? new Rating(true, product, user) : new Rating(false, product, user);
                ratingService.save(rating);
                result = "save";
                return result + String.valueOf(rating.isPosRating());
            } else {
                String type = String.valueOf(currentRating.isPosRating());
                result = "delete";
                ratingService.delete(currentRating);
                return result + type;
            }
        }else {
            return "anonim";
        }
    }
}
