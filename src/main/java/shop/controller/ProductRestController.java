package shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import shop.dao.ProductDAO;
import shop.entity.products.Comment;
import shop.entity.products.Product;
import shop.entity.products.Rating;
import shop.service.CommentService;
import shop.service.ProductService;
import shop.service.RatingService;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ProductRestController {
    @Autowired
    ProductService productService;
    @Autowired
    ProductDAO productDAO;
    @Autowired
    CommentService commentService;
    @Autowired
    RatingService ratingService;

    @PostMapping("/getProductTypes")
    public List<String> getProductTypes(){
        List<String> types = productService.findProductTypes();
        return types;
    }

    @PostMapping("/getIndividualCharact")
    public List<String> getIndividualCharact(@RequestBody String name, Model model) {
        String path = "shop.entity.products." + name;
        String resultPath = path.replaceAll("\"","");
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
        model.addAttribute("tableName",name);
        return listFields;
    }

    @PostMapping("/getCurrentProduct")
    public Map<String ,Object>  getCurrentProduct(@RequestBody StringBuilder name) {
        Map<String ,Object> result  = new HashMap<>();

        Product product = productService.findByProductName(String.valueOf(name));
        List<Rating> ratings = ratingService.findByProduct(product);
        List<Comment> comments = commentService.findByProduct(product);

        result.put("product",product);
        result.put("ratings",ratings);
        result.put("comments",comments);

        return result;
    }
}
