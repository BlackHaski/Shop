package shop.service;

import shop.entity.Category;

import java.util.List;

/**
 * Created by blackhaski on 21.06.17.
 */
public interface CategoryService {
    List<Category> findAll();
    void save(Category category);
}
