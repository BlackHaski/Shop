package shop.service;

import shop.entity.Category;
import shop.entity.products.Product;

import java.util.List;

/**
 * Created by blackhaski on 21.06.17.
 */
public interface CategoryService {
    void save(Category category);

    List<Category> findAll();

    void delete(int id);

    void updateNameById(int id, String newName);

    void updateParentId(int id, int newParentId);

    Category findOne(int id);

    Category findByCategoryName(String name);

    List<Category> findAllByParentId(int parentId);

    void save(List<Category> categories);

    Category findByProductsIn(Product product);


}
