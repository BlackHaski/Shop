package shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import shop.entity.Category;
import shop.service.CategoryService;
import shop.service.ProductService;

import java.util.List;

/**
 * Created by blackhaski on 27.06.17.
 */

@RestController
public class AdminRestController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;

    @GetMapping("/show")
    public List<Category> show() {
        return categoryService.findAll();
    }

    @PostMapping("/saveCategory")
    public void saveCategory(@RequestBody Category category) {
        categoryService.save(category);
    }

    @PostMapping("/deleteCategory")
    public void deleteCategory(@RequestBody int[] ids) {
        for (int i = 0; i < ids.length; i++) {
            categoryService.delete(ids[i]);
        }
    }

    @PostMapping("/changeName")
    public void changeName(@RequestBody Category category) {
        categoryService.updateNameById(category.getCategoryId(),category.getCategoryName());
    }

    @PostMapping("/changeParentId")
    public void changeParentId(@RequestBody Category category){
        categoryService.updateParentId(category.getCategoryId(),category.getParentId());
    }

}
