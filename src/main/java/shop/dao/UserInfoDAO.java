package shop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import shop.entity.security.UserInfo;

public interface UserInfoDAO extends JpaRepository<UserInfo, Integer> {
    @Modifying
    @Query("update UserInfo ui set ui.img=:path where ui.user.userId=:idUser")
    int changeUserAvatar(@Param("idUser") int idUser, @Param("path") String path);

    @Query("from UserInfo ui where ui.user.userId=:id")
    UserInfo findByUserId(@Param("id") int id);
}
