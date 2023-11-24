package com.kh.springdb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.springdb.mapper.LoginMapper;
import com.kh.springdb.model.User;

@Service
// LoginService : 스프링에서 사용자한테 필요한 메서드를 제공하는 서비스 역할을 하고 있음
public class LoginService {
	
	// DB가 담긴 loginMapper 를 사용하기 위해 선언해준 것
	private LoginMapper loginMapper;
	
	@Autowired //의존성 주입(DI : 인터페이스를 주입받는 생성자임을 정의내림)
	public LoginService(LoginMapper loginMapper) {
		this.loginMapper = loginMapper;
	} //초기화되어야하는 위치이기 때문과 값을 받아와할 것이 있기 때문에 여기에 @Autowired 
	  //UserService에서는 뿌려주기만 하면 되기 때문에 필드에 썼음	
	
	//로그인 기능을 수행하는 login 메서드를 정희하고 있음
	public User login(String mname, String memail) {
		//loginMapper 안에 있는 LoginByMnameAndMemail 메서드를 호출해서
		//mname, memail 사용해서 데이터베이스에서 해당 정보를 조회할 것임을 나타냄
		//추후 public User 를 통해 조회된 결과인 User 에 담긴 객체를 반환
		return loginMapper.LoginByMnameAndMemail(mname, memail);
	}
	
}
