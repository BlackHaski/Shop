package shop.service;

import shop.entity.security.UserInfo;

public interface UserInfoService {
    int changeUserAvatar(int idUser, String path);

    UserInfo findByUserId(int id);

}
