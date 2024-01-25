package com.preorder.user.controller;

import com.preorder.global.dto.WebResponseData;
import com.preorder.user.domain.dto.LoginForm;
import com.preorder.user.domain.dto.SingUpForm;
import com.preorder.user.domain.entity.User;
import com.preorder.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public WebResponseData<String> join(@RequestBody SingUpForm singUpForm) {

        return WebResponseData.ok(userService.signUp(singUpForm));
    }

    @PostMapping("/login")
    public WebResponseData<String> login(@RequestBody LoginForm loginForm) {

        return WebResponseData.ok(userService.login(loginForm));
    }

    @GetMapping("/emailAuth")
    public WebResponseData<String> emailAuth(@RequestParam String emailKey){
        return WebResponseData.ok(userService.emailAuth(emailKey));
    }

    @GetMapping("/info")
    public String userInfo(Authentication auth) {
        User loginUser = userService.getLoginUserByLoginId(auth.getName());

        return String.format("loginId : %s\nnickname : %s\nrole : %s",
                loginUser.getEmail(), loginUser.getName(), loginUser.getRole().name());
    }
}
