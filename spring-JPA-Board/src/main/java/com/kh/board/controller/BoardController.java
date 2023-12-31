package com.kh.board.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.board.service.BoardService;
import com.kh.board.vo.Board;

@Controller
@RequestMapping("/boards")
public class BoardController {
	private final BoardService boardService;
	
	@Autowired
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}
	
	//모든 게시물을 보기 위한 게시물 List 확인 메서드
	@GetMapping
	public String getAllBoard(Model model) {
		model.addAttribute("boards", boardService.getAllBoards());
		return "boardList";
	}
	
	//게시물 상세보기(1개만 조회) 메서드
	@GetMapping("/detail/{boardId}")
	public String getBoardById(@PathVariable Long boardId, Model model) {
		Optional<Board> board = boardService.getBoardById(boardId);
		board.ifPresent(value -> model.addAttribute("board",value));
		return "boardDetail";
	}
	
	//게시물의 내용을 추가하기(저장하기) 위한 메서드
	//작성할 url을 불러오기 위한 주소값 설정
	@GetMapping("/new")
	public String displayBoardForm(Model model) {
		model.addAttribute("board", new Board());
		return "boardForm";
	}
	
	//작성한 내용을 저장할 url 설정
	@PostMapping("/save")
	public String saveBoard(@ModelAttribute Board board) {
		boardService.saveBoard(board);
		return "redirect:/boards";
	}
	
	//게시물 수정하기 메서드
	@GetMapping("/update/{boardId}")
	public String getUpdateBoard(@PathVariable Long boardId, Model model) {
		Optional<Board> board = boardService.getBoardById(boardId);
		board.ifPresent(value -> model.addAttribute("board",value));
		return "boardForm";
	}
	
	//게시물 삭제하기 메서드
	@GetMapping("/delete/{boardId}")
	public String deleteBoard(@PathVariable Long boardId) {
		boardService.deleteBoard(boardId);
		return "redirect:/boards";
	}
	
	//게시물 모두 삭제하기 메서드
	@GetMapping("/delete/all")
	public String deleteAllBoards() {
		boardService.deleteAllBoards();
		return "redirect:/boards";
	}
	
	 @GetMapping("/search")
	    public String searchBoards(@RequestParam String keyword, Model model) {
	        // 특정 키워드를 포함하는 게시물 검색
	        List<Board> boards = boardService.findBoardsByTitle(keyword);

	        // 모델에 검색 결과 추가
	        model.addAttribute("boards", boards);

	        // 검색 결과를 보여줄 뷰 페이지 리턴
	        return "searchResults";
	    }
	}


	/*
	@RequestParam : Srping 프레임워크에서 클라이언트로부터 전송된
						HTTP 요청의 파라미터 값을 받아오기 위해 사용되는 어노테이션
							주로 웹 요청에서 쿼리 파라미터나 폼 데이터를 추출하는데 사용
					클라이언트가 전송한 요청의 파라미터 값을 메서드의 매개변수로 받아올 때 사용

	 	예제코드 : 
	 		@GetMapping("/ex")
	 		public String paramMethod
	 		    (@RequestParam String name, @RequestParam int age){
	 		    //name과 age는 클라이언트가 전송한 요청의 쿼리 파라미터 값
	 		    
	 		    return "View";
	 		}
	 		
	 * */