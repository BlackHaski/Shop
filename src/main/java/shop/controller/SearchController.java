package shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import shop.entity.products.Product;
import shop.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class SearchController {
    @Autowired
    ProductService productService;
    private static int pageSize = 3;

    @GetMapping("/category-{key}")
    public String searchProductsByCategory(@PathVariable("key") String categoryName,
                                           Model model,
                                           HttpSession httpSession) {
        Page<Product> products = null;
        Integer page = (Integer) httpSession.getAttribute("page");
        Sort sort = (Sort) httpSession.getAttribute("sort");
        if (sort == null){
            sort = new Sort(Sort.Direction.DESC, "date");
        }
        if (page != null) {
            products = productService
                    .findAllByCategoryCategoryName(categoryName,
                            new PageRequest(page, pageSize, sort));
        } else {
            products = productService
                    .findAllByCategoryCategoryName(categoryName,
                            new PageRequest(0, pageSize, sort));
        }
        model.addAttribute("currentProducts", products.getContent());
        model.addAttribute("pages", products.getTotalPages());
        model.addAttribute("key", categoryName);
        return "main";
    }

    @RequestMapping(value = "/searchProducts", method = {RequestMethod.GET, RequestMethod.POST})
    public String searchProducts(@RequestParam(value = "key", required = false, defaultValue = "") String searchName,
                                 HttpSession httpSession,
                                 Model model) {
        Integer page = (Integer) httpSession.getAttribute("page");
        if (searchName.length() <= 0) {
            searchName = (String) httpSession.getAttribute("key");
        }
        Page<Product> products = null;
        Sort sort = (Sort) httpSession.getAttribute("sort");
        if (sort == null){
            sort = new Sort(Sort.Direction.DESC, "date");
        }
        if (page != null) {
            products = productService
                    .findAllByProductNameIsContaining(searchName, new PageRequest(page, pageSize,sort));
        } else {
            products = productService
                    .findAllByProductNameIsContaining(searchName, new PageRequest(0, pageSize,sort));
        }
        model.addAttribute("currentProducts", products.getContent());
        model.addAttribute("pages", products.getTotalPages());
        httpSession.setAttribute("key", searchName);
        return "main";
    }

    @GetMapping("/page")
    public String getPageProducts(@RequestParam("number") String page,
                                  HttpSession httpSession,
                                  HttpServletRequest httpRequest) {
        httpSession.setAttribute("page", Integer.parseInt(page) - 1);
        String referer = httpRequest.getHeader("Referer");
        return "redirect:/" + referer.substring(referer.lastIndexOf("/") + 1);
    }

    @PostMapping("/sortProducts")
    public String sort(@RequestParam("column") String column,
                       @RequestParam("direction") String direction,
                       HttpSession httpSession,
                       HttpServletRequest httpRequest) {
        Sort sort = new Sort(Sort.Direction.valueOf(direction), column);
        httpSession.setAttribute("sort", sort);
        String referer = httpRequest.getHeader("Referer");
        return "redirect:/" + referer.substring(referer.lastIndexOf("/") + 1);
    }

}
