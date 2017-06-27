package shop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import shop.entity.Category;

import java.util.List;

/**
 * Created by blackhaski on 21.06.17.
 */
public interface CategoryDAO extends JpaRepository<Category, Integer> {
    @Query("select c.categoryId, c.categoryName, c.parentId from Category c")
    List<Category> findAllWithoutProducts();
}
