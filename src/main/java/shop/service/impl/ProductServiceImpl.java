package shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.dao.ProductDAO;
import shop.entity.Category;
import shop.entity.products.Product;
import shop.service.ProductService;

import java.io.File;
import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{
    @Autowired
    ProductDAO productDAO;

    public List<String> findProductTypes() {
        return productDAO.findProductTypes();
    }

    @Override
    public void save(Product product) {
        productDAO.save(product);
    }

    @Override
    public void save(List<Product> product) {
        productDAO.save(product);
    }

    @Override
    public List<Product> findAllByCategoryName(String categoryName) {
        return productDAO.findAllByCategoryName(categoryName);
    }

    @Override
    public Product findByProductName(String productName) {
        return productDAO.findByProductName(productName);
    }

    @Override
    public List<Product> findAllByProductNameIsContaining(String productName) {
        return productDAO.findAllByProductNameIsContaining(productName);
    }

    @Override
    public void deleteByProductName(String productName) {
        List<String> images = findByProductName(productName).getImages();
        String realPath = System.getProperty("user.home")
                + File.separator + "Programming" + File.separator
                + "JavaComplex" + File.separator
                + "productImages" + File.separator;
        for (String image : images) {
            image = image.substring(image.indexOf("/"));
            File file = new File(realPath.concat(image));
            file.delete();
        }
        productDAO.deleteByProductName(productName);
    }

    @Override
    public String findProductTypeByName(String name) {
        return productDAO.findProductTypeByName(name);
    }

    @Override
    public List<Product> findAllByCategoryCategoryId(int categoryId) {
        return productDAO.findAllByCategoryCategoryId(categoryId);
    }

    @Override
    public void updateProductCategory(String productName, Category category) {
        productDAO.updateProductCategory(productName,category);
    }

    @Override
    public void delete(List<Product> products) {
        productDAO.delete(products);
    }


}
