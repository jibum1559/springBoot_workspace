package com.kh.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kh.board.vo.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {

	//쿼리문을 추가로 작성하지 않고도 JpaRepository 이 안에 findAll 등이 있기 때문에 
}
