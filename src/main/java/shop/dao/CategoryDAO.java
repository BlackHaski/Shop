package shop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import shop.entity.Category;
import shop.entity.products.Product;

import java.util.List;

/**
 * Created by blackhaski on 21.06.17.
 */
public interface CategoryDAO extends JpaRepository<Category, Integer> {
    @Modifying
    @Query("update Category set categoryName =:newName where categoryId=:id")
    void updateNameById(@Param("id") int id,@Param("newName")String newName);

    @Modifying
    @Query("update Category set parentId =:newParentId where categoryId=:id")
    void updateParentId(@Param("id") int id,@Param("newParentId")int newParentId);

    Category findByCategoryName(String name);

    List<Category> findAllByParentId(int parentId);

    Category findByProductsIn(Product product);

}
