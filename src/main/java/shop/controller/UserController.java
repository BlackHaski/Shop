package shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import shop.entity.security.User;
import shop.service.UserInfoService;
import shop.service.UserService;

import java.io.File;
import java.io.IOException;
import java.security.Principal;

/**
 * Created by blackhaski on 17.07.17.
 */
@Controller
public class UserController {
    @Autowired
    UserInfoService userInfoService;
    @Autowired
    UserService userService;

    @GetMapping("/cabinet/profile")
    public String getProfile() {
        return "/cabinet/profile";
    }

    @GetMapping("/cabinet/editProfile")
    public String getEditPage() {
        return "/cabinet/editProfile";
    }

    @PostMapping("/changeAvatar")
    public String changeAvatar(@RequestParam("avatar") MultipartFile avatar, Principal principal) throws IOException {
        if (avatar.getOriginalFilename().length() > 0 &&
                (avatar.getOriginalFilename().endsWith(".jpg")
                        || avatar.getOriginalFilename().endsWith(".png"))
                            && principal.getName().length() > 0) {

            String realPath = System.getProperty("user.home")
                    + File.separator + "Programming" + File.separator
                    + "JavaComplex" + File.separator
                    + "avatars" + File.separator;
            String fileName = principal.getName() + avatar.getOriginalFilename();

            avatar.transferTo(new File(realPath + fileName));

            User user = (User) userService.loadUserByUsername(principal.getName());
            userInfoService.changeUserAvatar(user.getUserId(), fileName);
        }
        return "redirect:/cabinet-"+principal.getName();
    }

}
