package shop.service;

import shop.entity.UserInfo;

public interface UserInfoService {
    int changeUserAvatar(int idUser, String path);

    UserInfo findByUserId(int id);
}
