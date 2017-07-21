package shop.service;

import shop.entity.Category;

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


}
