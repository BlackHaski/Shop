package shop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.entity.Section;

/**
 * Created by blackhaski on 21.06.17.
 */
public interface SectionDAO extends JpaRepository<Section,Integer> {
}
