package shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import shop.entity.products.Human;
import shop.entity.products.Pc;
import shop.service.CategoryService;
import shop.service.ProductService;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by blackhaski on 29.06.17.
 */
@Controller
public class AdminController {

    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;

    @GetMapping("/admin")
    public String initAdmin() {
        return "admin/admin";
    }

    @GetMapping("/editCategory")
    public String getCategories() {
        return "/admin/categories";
    }

    @GetMapping("/createProductPage")
    public String createProductPage(Model model) {
        return "/admin/createProductPage";
    }

    @PostMapping("/createProduct")
    public String createProduct(@RequestParam("prodNameInp") String prodName,
                                @RequestParam("productCategory") String productCategory,
                                @RequestParam("prodCountInp") int count,
                                @RequestParam("prodPriceInp") int price,
                                @RequestParam("rebateInp") double rebate,
                                @RequestParam("description") String description,
                                @RequestParam("productImages") List<MultipartFile> productImages,
                                @RequestParam("characteristic") List<String> characteristics,
                                @RequestParam("productType") String tableName) {
        List<String> images = new ArrayList<>();
        if (productImages.size() <= 1) {
            images.add("productImages/defaultGoodImg.png");
        } else {
            productImages.remove(productImages.size() - 1);
            for (MultipartFile img : productImages) {
                if (img.getOriginalFilename().length() > 0 &&
                        (img.getOriginalFilename().endsWith(".jpg")
                                || img.getOriginalFilename().endsWith(".png"))) {
                    String realPath = System.getProperty("user.home")
                            + File.separator + "Programming" + File.separator
                            + "JavaComplex" + File.separator
                            + "productImages" + File.separator;
                    String fileName = prodName + img.getOriginalFilename();
                    images.add("productImages/" + fileName);
                    try {
                        img.transferTo(new File(realPath + fileName));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        switch (tableName) {
            case "Pc": {
                Pc pc = new Pc(prodName, price, rebate, description, count,
                        categoryService.findByCategoryName(productCategory),
                        characteristics.get(0), characteristics.get(1), Integer.parseInt(characteristics.get(2)),
                        images);
                productService.save(pc);
                break;
            }
            case "Human": {
                Human human = new Human(prodName, price, rebate, description, count,
                        categoryService.findByCategoryName(productCategory), Integer.parseInt(characteristics.get(0)),
                        Integer.parseInt(characteristics.get(1)), Integer.parseInt(characteristics.get(2)), images);
                productService.save(human);
                break;
            }
            default: {

            }
        }
        return "main";
    }
}
