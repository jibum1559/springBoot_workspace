package com.kh.springdb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.springdb.model.User;

@Mapper  //어노테이션 유무 항상 체크!!!
public interface UserMapper {
	//모든 유저 조회
	List<User> getAllUsers();
	
	//유저 한 명 조회
	User getUserById(int id);
	
	void saveUser(User user); //이거 확인 필요
	
	void insertUser(User user);
	
	//유저 로그인 메서드
	User loginUser(String memail);
	
	//유저 정보 수정 메서드
	void updateUser(User user);
	
	//유저 정보 삭제 메서드
	void deleteUser(int mno);
}

//UserMapper.xml 에서 만든 숫자 만큼 만들어줌
//인서트나 업데이트는 void 사용(왜 그런지 확인해보자)