package shop.service;

import org.springframework.security.core.userdetails.UserDetails;
import shop.entity.security.User;

import java.util.List;

/**
 * Created by blackhaski on 03.07.17.
 */
public interface UserService {
    void save(User user);

    User findOne(int id);

    List<User> findAll();

    UserDetails loadUserByUsername(String username);

    User findByUsernameWithDetails(String username);

    void updateUserPassword(String username, String newPass);

}
