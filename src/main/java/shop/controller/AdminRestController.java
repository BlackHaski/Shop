package shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import shop.entity.Category;
import shop.service.CategoryService;
import shop.service.ProductService;

import java.lang.reflect.Field;
import java.util.ArrayList;
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
}
