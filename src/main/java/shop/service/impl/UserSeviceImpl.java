package shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.dao.UserDAO;
import shop.entity.security.User;
import shop.service.UserService;

import java.util.List;

/**
 * Created by blackhaski on 03.07.17.
 */
@Service
@Transactional
public class UserSeviceImpl implements UserService,UserDetailsService {
    @Autowired
    UserDAO userDAO;

    public void save(User user) {
        userDAO.save(user);
    }

    public User findOne(int id) {
        return userDAO.findOne(id);
    }

    public List<User> findAll() {
        return userDAO.findAll();
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDAO.findByUsername(username);
    }

    public User findByUsernameWithDetails(String username) {
        return userDAO.findByUsernameWithDetails(username);
    }

    public void updateUserPassword(String username, String newPass) {
        userDAO.updateUserPassword(username,newPass);
    }
}
