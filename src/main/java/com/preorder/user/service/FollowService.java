package com.preorder.user.service;

import com.preorder.global.exception.UserException;
import com.preorder.global.type.ErrorCode;
import com.preorder.user.domain.entity.Follow;
import com.preorder.user.domain.entity.User;
import com.preorder.user.repository.FollowRepository;
import com.preorder.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FollowService {
    private final UserRepository userRepository;
    private final FollowRepository followRepository;

    public String follow(Authentication auth, long followId) {
        Optional<User> optionalUser = userRepository.findByEmail(auth.getName());
        Optional<User> optionalFollowUser = userRepository.findById(followId);
        if (optionalUser.isEmpty() || optionalFollowUser.isEmpty()) {
            throw new UserException(ErrorCode.NOT_FOUND_USER);
        }
        User user = optionalUser.get();
        User followUser = optionalFollowUser.get();
        if (followRepository.existsByUserAndFollowId(user, followUser)){
            throw new UserException(ErrorCode.ALREADY_FOLLOW_USER);
        }
        Follow follow = Follow.builder()
                    .user(user)
                    .followId(followUser)
                    .build();
        followRepository.save(follow);
        return "팔로우 성공";
    }
}
