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
	
	void insertUser(User user);
	
	/* 로그인 보류
	User getLoginUser(int id, String name);
	*/
	
}

//UserMapper.xml 에서 만든 숫자 만큼 만들어줌
