package shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.dao.ProductDAO;
import shop.entity.products.Product;
import shop.service.ProductService;

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

}
