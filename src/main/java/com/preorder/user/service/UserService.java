package com.preorder.user.service;

import com.preorder.global.email.MailComponents;
import com.preorder.user.domain.dto.LoginForm;
import com.preorder.user.domain.dto.SingUpForm;
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
    private final MailComponents mailComponents;

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
        String uuid = UUID.randomUUID().toString();
        userRepository.save(req.toEntity(encoder.encode(req.getPassword()), uuid));
        mailComponents.sendMail(req.getEmail(),
                "[예약 구매 서비스] 회원 가입을 축하드립니다.",
                    "<p>안녕하세요. 예약 구매 서비스 회원 가입을 축하드립니다.<p>" +
                        "<p>아래 링크를 클릭하시면 이메일 인증이 완료됩니다.<p>" +
                        "<a href='http://localhost:8080/user/emailAuth?emailKey=" + uuid + "'>회원가입 완료</a>");
        return "회원가입 성공";
    }


    public User login(LoginForm req) {
        Optional<User> optionalUser = userRepository.findByEmail(req.getEmail());

        if (optionalUser.isEmpty()) {
            return null;
        }

        User user = optionalUser.get();

        if (!encoder.matches(req.getPassword(), user.getPassword())) {
            return null;
        }

        return user;
    }


    public User getLoginUserByLoginId(String email) {
        if (email == null) return null;

        Optional<User> optionalUser = userRepository.findByEmail(email);
        return optionalUser.orElse(null);

    }

    public String emailAuth(String emailKey) {
        Optional<User> optionalUser = userRepository.findByEmailKey(emailKey);
        if (optionalUser.isEmpty()) {
            throw new RuntimeException();
        }
        User user = optionalUser.get();
        user.setEmailCert(true);
        userRepository.save(user);
        return "인증 성공";
    }

    public boolean checkLoginIdDuplicate(String email) {
        return userRepository.existsByEmail(email);
    }

    public boolean checkNicknameDuplicate(String name) {
        return userRepository.existsByName(name);
    }

}
