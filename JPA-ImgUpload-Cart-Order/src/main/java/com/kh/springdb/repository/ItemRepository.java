package com.kh.springdb.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.kh.springdb.model.vo.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {
	Item findItemById(int id);
	
	//페이지네이션 처리
	Page<Item> findByNameContaining(String keyword, Pageable pageable);
}

/*
 Page : 페이지당 데이터를 나타내는 클래스
 - 현재 페이지에서 데이터에 관련된 내용과 정보가 표기됨
 - 대표적으로 현재 페이지 번호, 전체 페이지 수를 알 수 있음
  
 Pageable : 페이징 및 정렬을 위해 사용(1,2,3... 어떻게 정렬할지 정함), 인터페이스임
 - 주로 데이터베이스 쿼리 메서드의 매개변수로 사용됨
 - 클라이언트가 요청한 페이지와 정렬에 대한 정보를 전달할 수 있음
 
 */
