package shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import shop.entity.Role;
import shop.entity.User;
import shop.entity.UserInfo;
import shop.service.UserService;

import java.util.Date;

/**
 * Created by blackhaski on 17.07.17.
 */
@Controller
public class SecurityController {

    @Autowired
    UserService userService;

    @PostMapping("/regUser")
    public String regUser(@RequestParam("username") String username,
                          @RequestParam("email") String email,
                          @DateTimeFormat(iso = DateTimeFormat.ISO.NONE,pattern = "yyyy-MM-dd") @RequestParam("birthday") Date birthday,
                          @RequestParam("password") String password,
                          @RequestParam("checkPassword") String checkPassword){
        User user = new User(username,email,password);
        user.setUserInfo(new UserInfo(birthday,"/avatars/default/defaultAvatar.png", Role.ROLE_USER,user));
        userService.save(user);
        return "main";
    }

    @GetMapping("/recoverPass")
    public String getRecoverPage() {
        return "/registration/recoverPass";
    }

}
