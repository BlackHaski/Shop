package shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import shop.service.ProductService;

@Controller
public class SearchController {
    @Autowired
    ProductService productService;

    @GetMapping("/category-{categoryName}")
    public String searchProductsByCategory(@PathVariable("categoryName")String categoryName, Model model) {
        model.addAttribute("currentProducts",productService.findAllByCategoryName(categoryName));
        return "main";
    }
}
