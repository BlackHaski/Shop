package shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import shop.dao.ProductDAO;
import shop.entity.Category;
import shop.entity.products.Product;
import shop.entity.products.Rating;
import shop.entity.products.SoldOut;
import shop.entity.security.User;
import shop.functions.Cart;
import shop.functions.TypeConverter;
import shop.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.*;

@RestController
public class ProductRestController {
    @Autowired
    ProductService productService;
    @Autowired
    CommentService commentService;
    @Autowired
    RatingService ratingService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    UserService userService;
    @Autowired
    ProductDAO productDAO;
    @Autowired
    SoldOutService soldOutService;

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
        Product product = productService.findByProductName(String.valueOf(name));
        List<Category> categories = categoryService.findAll();
        Map<String, Object> response = new HashMap<>();
        response.put("product", product);
        response.put("categories", categories);
        return response;
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
                Rating rating = new Rating(typeRating, product, user);
                ratingService.save(rating);
                result = "save";
                return result + String.valueOf(rating.isRating());
            } else {
                String type = String.valueOf(currentRating.isRating());
                result = "delete";
                ratingService.delete(currentRating);
                return result + type;
            }
        } else {
            return "anonim";
        }
    }

    @PostMapping("/addToCartProduct")
    public String addToCartProduct(@RequestBody Map<String, String> params, Principal principal, HttpSession httpSession) {
        if (principal == null) {
            return "anonim";
        } else {
            Cart cart = (Cart) httpSession.getAttribute("cart");
            Product productToCart = productService.findByProductName(params.get("nameProduct"));
            int countProduct = Integer.parseInt(params.get("countProduct"));
            Map<Product, Integer> productsInCart = cart.getProducts();

            if (productsInCart.size() > 0) {
                for (Map.Entry<Product, Integer> entry : productsInCart.entrySet()) {
                    if (entry.getKey().equals(productToCart)) {
                        entry.setValue(entry.getValue() + countProduct);
                        return "changeCount";
                    }
                }
            }
            cart.addToCart(productToCart, countProduct);
            return "add";
        }
    }

    @PostMapping("/deleteProductFromCart")
    public boolean deleteProductFromCart(@RequestBody String productName, HttpSession httpSession) {
        Cart cart = (Cart) httpSession.getAttribute("cart");
        for (Map.Entry<Product, Integer> entry : cart.getProducts().entrySet()) {
            if (entry.getKey().getProductName().equals(productName)) {
                cart.getProducts().remove(entry.getKey());
                return true;
            }
        }
        return false;
    }

    @PostMapping("/deleteProduct")
    public void deleteProduct(@RequestBody String productName) {
        productService.deleteByProductName(productName);
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/updateProduct")
    public boolean updateProduct(@RequestBody Map<String, String> params) {
        Product product = productService.findByProductName(params.get("productName"));
        Class productRef = product.getClass();
        TypeConverter typeConverter = new TypeConverter();
        Field field = null;
        try {
            field = productRef.getDeclaredField(params.get("column"));
        } catch (NoSuchFieldException e) {
            try {
                field = productRef.getSuperclass().getDeclaredField(params.get("column"));
            } catch (NoSuchFieldException e1) {
                e1.printStackTrace();
            }
        }
        try {
            if (field != null) {
                field.setAccessible(true);
                Class<?> type = field.getType();
                if (type.getName().equals(Date.class.getName())){
                    field.set(product,
                            Date.from(new SimpleDateFormat("yyyy-MM-dd").parse(params.get("value")).toInstant().plus(1, ChronoUnit.DAYS)));
                }
                else if (type.getName().equals(String.class.getName())) {
                    field.set(product, params.get("value"));
                }
                else {
                    Number convert = typeConverter.convert(params.get("value"), type);
                    if (convert != null) {
                        field.set(product, convert);
                    }
                }
                productService.save(product);
                return true;
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    @PostMapping("/changeProductCategory")
    public void changeProductCategory(@RequestBody Map<String, String> params) {
        Category category = categoryService.findByCategoryName(params.get("categoryName"));
        productService.updateProductCategory(params.get("productName"), category);
    }

    @PostMapping("/buyProduct")
    public boolean buyProduct(@RequestBody List<Map<String, String>> params, HttpSession httpSession, Principal principal) {
        for (Map<String, String> param : params) {
            Product product = productService.findByProductName(param.get("name"));
            User user = (User) userService.loadUserByUsername(principal.getName());
            product.setSoldOut(
                    product.getSoldOut() + Integer.parseInt(param.get("count"))
            );
            product.setCount(
                    product.getCount() - Integer.parseInt(param.get("count"))
            );
            Cart cart = (Cart) httpSession.getAttribute("cart");
            cart.getProducts().clear();
            productService.save(product);

            SoldOut soldOut = soldOutService.findByProductAndUser(product, user);
            if (soldOut != null) {
                soldOut.setCount(
                        soldOut.getCount() + Integer.parseInt(param.get("count"))
                );
            } else {
                soldOut = new SoldOut(user, product, Integer.parseInt(param.get("count")));
            }
            soldOutService.save(soldOut);
        }
        return true;
    }

    @PostMapping("/deleteProductImg")
    public void deleteProductImg(@RequestBody String img) {
        productService.deleteImg(img);
    }

    @PostMapping("/addImgToProduct")
    public void addImgToProduct(@RequestParam("img") MultipartFile img, HttpServletRequest httpRequest,
                                HttpServletResponse response) {
        String prodName = httpRequest.getHeader("Referer");
        prodName = prodName.substring(prodName.indexOf("-") + 1);
        Product product = productService.findByProductName(prodName);
        if (img.getOriginalFilename().length() > 0 &&
                (img.getOriginalFilename().endsWith(".jpg")
                        || img.getOriginalFilename().endsWith(".png"))) {
            String realPath = System.getProperty("user.home")
                    + File.separator + "Programming" + File.separator
                    + "JavaComplex" + File.separator
                    + "productImages" + File.separator;
            String fileName = prodName + img.getOriginalFilename();
            try {
                img.transferTo(new File(realPath + fileName));
                product.getImages().add("productImages/"+fileName);
                productService.save(product);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            response.sendRedirect(httpRequest.getHeader("Referer"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/getUserGoods")
    public List<SoldOut> getUserGoods(Principal principal) {
        User user = (User)userService.loadUserByUsername(principal.getName());
        return soldOutService.findProductByUser(user);
    }

}

