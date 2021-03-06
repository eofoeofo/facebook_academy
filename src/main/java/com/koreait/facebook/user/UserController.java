package com.koreait.facebook.user;

import com.koreait.facebook.user.model.UserEntity;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/login")
    public void login(UserEntity userEntity) {}

    @GetMapping("/join")
    public void join(UserEntity userEntity) {}

    @PostMapping("/join")
    public String joinProc(UserEntity userEntity) {
        service.join(userEntity);
        return "redirect:login?needEmail=1";
    }
    @GetMapping("/email")
    public String email() {
        service.sendEamil();
        return "";
    }
    @GetMapping("/auth")
    public String auth(UserEntity param) {
        int result = service.auth(param);
        // 1이라면 인증 성공, 아니라면 인증 실패
        return "redirect:login?auth="+result;
    }
}









