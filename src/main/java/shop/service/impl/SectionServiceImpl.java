package shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.dao.SectionDAO;
import shop.entity.Section;
import shop.service.SectionService;

import java.util.List;

/**
 * Created by blackhaski on 21.06.17.
 */
@Service
@Transactional
public class SectionServiceImpl implements SectionService {

    @Autowired
    SectionDAO sectionDAO;

    public List<Section> findAll() {
        return sectionDAO.findAll();
    }

    public void save(Section section) {
        sectionDAO.save(section);
    }
}
