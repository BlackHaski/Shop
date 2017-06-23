package shop.service;

import shop.entity.Section;

import java.util.List;

/**
 * Created by blackhaski on 21.06.17.
 */
public interface SectionService {
    List<Section> findAll();
    void save(Section section);
}
