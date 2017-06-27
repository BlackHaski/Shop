package shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.entity.Category;
import shop.service.CategoryService;

import java.util.List;

/**
 * Created by blackhaski on 27.06.17.
 */

@RestController
public class MyRestController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("show")
    public List<Category>  show() {
        return categoryService.findAllWithoutProducts();
    }
}
