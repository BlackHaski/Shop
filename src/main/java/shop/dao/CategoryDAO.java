package shop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.entity.Category;

/**
 * Created by blackhaski on 21.06.17.
 */
public interface CategoryDAO extends JpaRepository<Category, Integer> {

}
