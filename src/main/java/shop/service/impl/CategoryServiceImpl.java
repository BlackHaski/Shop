package shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.dao.CategoryDAO;
import shop.entity.Category;
import shop.entity.products.Product;
import shop.service.CategoryService;
import shop.service.ProductService;

import java.util.List;

/**
 * Created by blackhaski on 21.06.17.
 */
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryDAO categoryDAO;
    @Autowired
    ProductService productService;

    public List<Category> findAll() {
        return categoryDAO.findAll();
    }

    public void delete(int id) {
        List<Product> categoryProducts = productService.findAllByCategoryCategoryId(id);
        Category deleteCategory = findOne(id);
        List<Category> children = findAllByParentId(id);
        if (!deleteCategory.getCategoryName().equals("other")) {
            for (Category child : children) {
                child.setParentId(deleteCategory.getParentId());
            }
            if (categoryProducts.size() > 0) {
                Category other = findByCategoryName("other");
                if (other == null) {
                    other = new Category("other", 0);
                }
                other.addProducts(categoryProducts);
                for (Product product : categoryProducts) {
                    product.setCategory(other);
                }
                productService.save(categoryProducts);
                save(other);
            }
        }else {
            productService.delete(categoryProducts);
            categoryDAO.delete(children);
        }
        categoryDAO.delete(id);
    }

    public void updateNameById(int id, String newName) {
        categoryDAO.updateNameById(id, newName);
    }

    public void updateParentId(int id, int newParentId) {
        categoryDAO.updateParentId(id, newParentId);
    }

    public Category findOne(int id) {
        return categoryDAO.findOne(id);
    }

    public Category findByCategoryName(String name) {
        return categoryDAO.findByCategoryName(name);
    }

    @Override
    public List<Category> findAllByParentId(int parentId) {
        return categoryDAO.findAllByParentId(parentId);
    }

    @Override
    public void save(List<Category> categories) {
        categoryDAO.save(categories);
    }

    @Override
    public Category findByProductsIn(Product product) {
        return categoryDAO.findByProductsIn(product);
    }

    public void save(Category category) {
        categoryDAO.save(category);
    }

}
