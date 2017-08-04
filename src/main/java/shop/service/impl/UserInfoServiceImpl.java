package shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.dao.UserInfoDAO;
import shop.entity.security.UserInfo;
import shop.service.UserInfoService;

import java.io.File;

@Service
@Transactional
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    UserInfoDAO userInfoDAO;

    public int changeUserAvatar(int idUser, String path) {
        if (path.length() > 0 && (path.endsWith(".jpg") || path.endsWith(".png"))) {
            String oldAvatarPath = System.getProperty("user.home")
                    + File.separator + "Programming" + File.separator
                    + "JavaComplex" + userInfoDAO.findByUserId(idUser).getImg();
            File file = new File(oldAvatarPath);

            if (!oldAvatarPath.endsWith("/default/defaultAvatar.png")){
                file.delete();
            }
            return userInfoDAO.changeUserAvatar(idUser, "/avatars/" + path);
        }else {
            return 0;
        }
    }

    public UserInfo findByUserId(int id) {
        return userInfoDAO.findByUserId(id);
    }

}
