package com.kh.springdb.controller;


import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.springdb.model.UserCheckForm;
import com.kh.springdb.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/signup")
    public String signup(UserCheckForm usercheckForm) {
        return "signup_form";
    }

    @PostMapping("/signup")
    public String signup(@Valid UserCheckForm usercheckForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "signup_form";
        }

        if (!usercheckForm.getPassword1().equals(usercheckForm.getPassword2())) {
        	//1. 비밀번호와 비밀번호 확인이 일치하지 않을 때
    		//bindingResult.rejectValue(a,b,c)
    		// a = 검증을 실패한 필드의 이름을 나타냄 
    		//밑에서는 password2가 검증에 실패했음을 의미함
    		// b = 검증에 실패할 경우 보여질 메세지를 담은 변수
    		// c = b가 담고 있는, 비밀번호가 일치하지 않는 에러가 났을 경우 보여질 메세지
    		
            bindingResult.rejectValue("password2", "passwordInCorrect", 
                    "2개의 패스워드가 일치하지 않습니다.");
            return "signup_form";
        }
      //모든 내용을 잘 작성했다면 DB에 저장해주기
        userService.create(usercheckForm.getUsername(), 
        		usercheckForm.getEmail(), usercheckForm.getPassword1());

        return "redirect:/";
    }
    
    
    @GetMapping("/login")
    public String login() {
        return "login_form";
    }
    
}