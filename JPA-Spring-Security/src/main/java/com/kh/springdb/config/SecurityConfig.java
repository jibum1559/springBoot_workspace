package com.kh.springdb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

//스프링 시큐리티 설정을 의미하는 어노테이션
//스프링에서 환경설정 파일을 나타내는 어노테이션
@Configuration 
@EnableWebSecurity //모든 URL이 스프링 시큐리티의 제어를 받오록 만든 어노테이션
public class SecurityConfig { 
	
	//필터를 많이 사용해서 보안을 한다.
	@Bean //객체의 생성, 관리, 주입(새로 넣어줌)을 담당 //this 사용하지 않고 코드를 조금 간편화함
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http
		//.authorizeRequests 10월부터 http가 빠져서 업데이트 됨
		.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
		.requestMatchers(new AntPathRequestMatcher("/**")).permitAll())
		
		//로그인을 하고난 후 로그인 세션을 유지하기 위한 설정  *****위에는 전체 기본 설정이고 해당 코드가 중요 *****
		.formLogin((formLogin) -> formLogin
		.loginPage("/user/login")
		.defaultSuccessUrl("/"))
		
		//-------------------- 전체허용으로 권한을 설정한 것 -------------------
		
		//로그아웃 하고 난 후 로그인 세션을 끝내기 위한 설정
		.logout((logout -> logout
		.logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
		.logoutSuccessUrl("/")
		.invalidateHttpSession(true)))
		;

		//------------------전체 로그인 세션을 준 것 (마이페이지 가는데 로그인 또 하면 불편하니까)--------------
		
		return http.build();
	}
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration a) throws Exception{
		return a.getAuthenticationManager();
	}
	
	//비밀번호 변경 시 어떤 식으로 암호 처리를 할 지 설정
	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder(); 
		//BCryptPasswordEncoder 형식으로 비밀번호를 저장
	}
	
}
