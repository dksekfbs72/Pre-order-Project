package com.preorder.user.controller;

import com.preorder.global.dto.WebResponseData;
import com.preorder.user.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/follow")
public class FollowController {
    private final FollowService followService;

    @PostMapping
    public WebResponseData<String> follow(Authentication auth, @RequestParam long followId) {
        return WebResponseData.ok(followService.follow(auth, followId));
    }
}
