package shop.list;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shop.service.CategoryService;

/**
 * Created by blackhaski on 21.06.17.
 */
@Component
public class MenuList {
    @Autowired
    CategoryService categoryService;


}

