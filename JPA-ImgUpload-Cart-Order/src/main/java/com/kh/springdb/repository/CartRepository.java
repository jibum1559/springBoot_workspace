package com.kh.springdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kh.springdb.model.vo.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {
	//사용자 Id를 바탕으로 해서 id 주인의 카트를 조회하기 우해 사용한는 메서드
	Cart findByUserId(int id);
	//주어진 Id CartId 바탕으로 해서 카트 내용 조회
	Cart findByCartById(int id);
	//카트에서 cart를 중점으로 UserId를 검색해서 조회
	Cart findCartByUserId(int id);
	
}