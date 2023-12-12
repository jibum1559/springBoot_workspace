package com.kh.springdb.service;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kh.springdb.model.SecurityUser;
import com.kh.springdb.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SecurityUser create(String username, String email, String password) {
    	SecurityUser user = new SecurityUser();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
                                         //입력받은 비밀번호를 암호화하여 반환
        this.userRepository.save(user);
        return user;
    }
}