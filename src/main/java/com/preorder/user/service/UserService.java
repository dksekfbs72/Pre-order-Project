package com.preorder.user.service;

import com.preorder.user.domain.dto.SingUpForm;
import com.preorder.user.domain.dto.LoginForm;
import com.preorder.user.domain.entity.User;
import com.preorder.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final BCryptPasswordEncoder encoder;

    public String signUp(SingUpForm req) {
        // loginId 중복 체크
        if (checkLoginIdDuplicate(req.getEmail())) {
            return "로그인 아이디가 중복됩니다.";
        }
        // 닉네임 중복 체크
        if (checkNicknameDuplicate(req.getName())) {
            return "닉네임이 중복됩니다.";
        }
        // password와 passwordCheck가 같은지 체크
        if (!req.getPassword().equals(req.getPasswordCheck())) {
            return "바밀번호가 일치하지 않습니다.";
        }
        userRepository.save(req.toEntity(encoder.encode(req.getPassword()), UUID.randomUUID().toString()));
        return "회원가입 성공";
    }


    public User login(LoginForm req) {
        Optional<User> optionalUser = userRepository.findByEmail(req.getEmail());

        if(optionalUser.isEmpty()) {
            return null;
        }

        User user = optionalUser.get();

        if(!encoder.matches(req.getPassword(), user.getPassword())) {
            return null;
        }

        return user;
    }


    public User getLoginUserByLoginId(String email) {
        if(email == null) return null;

        Optional<User> optionalUser = userRepository.findByEmail(email);
        return optionalUser.orElse(null);

    }

    public boolean checkLoginIdDuplicate(String email) {
        return userRepository.existsByEmail(email);
    }

    public boolean checkNicknameDuplicate(String name) {
        return userRepository.existsByName(name);
    }

}
