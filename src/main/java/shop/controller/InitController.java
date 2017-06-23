package shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import shop.list.MenuList;
import shop.service.CategoryService;

/**
 * Created by blackhaski on 21.06.17.
 */
@Controller
public class InitController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    MenuList menuList;

    @GetMapping("/")
    public String start(Model model) {
//        model.addAttribute("sections",menuList.getCategories());
        return "main";
    }
    @GetMapping("/about")
    public String about(){
        return "about";
    }

    @GetMapping("/contacts")
    public String contacts(){
        return "contacts";
    }

    @GetMapping("/registration")
    public String registration(){

        return "registration";
    }
}
