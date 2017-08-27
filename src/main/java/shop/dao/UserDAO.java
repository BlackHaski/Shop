package shop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import shop.entity.security.User;

/**
 * Created by blackhaski on 03.07.17.
 */
public interface UserDAO extends JpaRepository<User, Integer> {
    User findByUsername(String username);

    User findByUsernameAndEmail(String username,String email);

    @Query("from User u join fetch u.userInfo where u.username=:name")
    User findByUsernameWithDetails(@Param("name") String username);

    @Modifying
    @Query("update User u set u.password=:pass where u.username=:username")
    void updateUserPassword(@Param("username") String username, @Param("pass") String newPass);
}
