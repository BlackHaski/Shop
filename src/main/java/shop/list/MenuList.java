package shop.list;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shop.entity.Section;
import shop.service.SectionService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by blackhaski on 21.06.17.
 */
@Component
public class MenuList {
    @Autowired
    SectionService sectionService;

    List<Section> sections = new ArrayList<Section>();

    public boolean loadAndUpdate(){
        sections = new ArrayList<Section>();
        sections = sectionService.findAll();
        if (sections.isEmpty()) return false;
        return true;
    }
    public List<Section> getSections(){
        if (sections.isEmpty()){
            loadAndUpdate();
        }
        return sections;
    }
}
