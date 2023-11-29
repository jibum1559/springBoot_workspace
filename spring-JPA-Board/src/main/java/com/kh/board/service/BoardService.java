package com.kh.board.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.board.repository.BoardRepository;
import com.kh.board.vo.Board;

@Service
public class BoardService {
	private final BoardRepository boardRepository;
	
	@Autowired //빈 객체 생성
	public BoardService(BoardRepository boardRepository) {
		this.boardRepository = boardRepository;
	}
	
	//모든 게시물 조회하는 메서드
	//Repository findAll 지정된 메서드 가지고오기
	public List<Board> getAllBoards(){
		return boardRepository.findAll();
	}
	
	//게시물 1개만 조회하는 메서드
	public Optional<Board> getBoardById(Long id){
		return boardRepository.findById(id);
	}
	
	
	//삭제하는 메서드
	public void deleteBoardById(Long id) {
		boardRepository.deleteById(id);
	}
	
	//게시물 추가하는(저장하는) 메서드
	public Board saveBoard(Board board) {
		return boardRepository.save(board);
	}

	//삭제하는 메서드
	public void deleteBoard(Long id) {
		boardRepository.deleteById(id);
	}
	
	//게시글 모두 삭제하기 메서드
	public void deleteAllBoards() {
		boardRepository.deleteAll();
	}
}









