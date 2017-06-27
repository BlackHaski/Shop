package shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.dao.CategoryDAO;
import shop.entity.Category;
import shop.service.CategoryService;

import java.util.List;

/**
 * Created by blackhaski on 21.06.17.
 */
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryDAO categoryDAO;

    public List<Category> findAllWithoutProducts(){
        return categoryDAO.findAllWithoutProducts();
    }

    public void save(Category category) {
        categoryDAO.save(category);
    }

}
