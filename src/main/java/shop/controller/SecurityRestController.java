package shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import shop.entity.User;
import shop.service.MailService;
import shop.service.UserService;

import java.util.List;

@RestController
public class SecurityRestController {
    @Autowired
    MailService mailService;

    @Autowired
    UserService userService;

    @PostMapping("checkUser")
    public int checkUser(@RequestBody List<String> params) {
        String username = params.get(0);
        String email = params.get(1);

        User user = (User)userService.loadUserByUsername(username);
        if (user != null){
            if (user.getUsername().equals(username) && user.getEmail().equals(email)) {
                int code = (int) (Math.random()*100000);
                mailService.sendFromAdmin(email, String.valueOf(code));
                return code;
            }
        }
        return 0;
    }

    @PostMapping("/changePassword")
    public void changePassword(@RequestBody List<String> params) {
        System.out.println(params);
        userService.updateUserPassword(params.get(1),params.get(0));
    }
}
