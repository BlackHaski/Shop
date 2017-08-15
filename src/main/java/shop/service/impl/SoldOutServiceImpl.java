package shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.dao.SoldOutDAO;
import shop.entity.products.Product;
import shop.entity.products.SoldOut;
import shop.entity.security.User;
import shop.service.SoldOutService;

@Service
@Transactional
public class SoldOutServiceImpl implements SoldOutService {
    @Autowired
    SoldOutDAO soldOutDAO;

    @Override
    public void save(SoldOut soldOut) {
        soldOutDAO.save(soldOut);
    }

    @Override
    public SoldOut findByProductAndUser(Product product, User user) {
        return soldOutDAO.findByProductAndUser(product,user);
    }
}
