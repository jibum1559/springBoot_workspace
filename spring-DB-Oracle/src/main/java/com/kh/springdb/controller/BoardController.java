package com.kh.springdb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.springdb.model.Board;
import com.kh.springdb.service.BoardService;

@Controller
@RequestMapping("/boards")
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@GetMapping //("/boards")
	public String getAllBoards(Model model) {
		//게시물 전체보는 Controller
		List<Board> boards = boardService.getAllBoards();
		model.addAttribute("boards", boards);
		return "board-list";
	}
	
	@GetMapping("/{boardId}") // /boards/{boardId} 이 주소로 앞에 boards가 내장되어 있음
	public String getBoardById(@PathVariable int boardId, Model model) {
		Board board = boardService.getBoardById(boardId);
		model.addAttribute("board", board);
		return "board-detail";
	}
	
	// GetMapping을 사용해서 게시글 작성하는 html로 이동한 후
	@GetMapping("/create") //HTTP GET 요청이 /create 라는 경로로 들어올 때 호출
	public String displayCreateForm(Model model) { 
		//Model 객체를 매개변수로 받아서 templates(view)으로 데이터를 전달할 수 있음
		model.addAttribute("board", new Board());
		//new Board로 새로운 Board 객체를 생성해서 모델에 추가
		return "board-form"; //board-form.html 템플릿에서 해당 뷰를 보여줌
	}
	
	// PostMapping을 사용해서 작성해놓은 insertHTML form 가져온다.
	@PostMapping("/create")
	public String createBoard(@ModelAttribute Board board) {
		boardService.insertBoard(board);
		return "redirect:/boards"; //글이 작성된 후 돌아갈 템플릿 작성	
	}
	
	@PostMapping("/update/{boardId}")
	public String updateForm(@PathVariable int boardId, @ModelAttribute Board board) {
		//URL에서 가져온 boardId값을 Board 객체에 저장
		board.setBoardId(boardId);
		boardService.updateBoard(board);
		
		//수정이 완료된 후 게시글 목록 페이지로 돌아가기
		return "redirect:/boards";		
	}
	
	@GetMapping("/update/{boardId}")
	public String displayUpdateForm(@PathVariable int boardId, Model model) {
		Board board = boardService.getBoardById(boardId);
		model.addAttribute("board", board);
		return "board-form";
	}
	
	//게시글 삭제하기
	@GetMapping("/delete/{boardId}")
	public String deleteBoard(@PathVariable int boardId) {
		boardService.deleteBoard(boardId);
		return "redirect:/boards";
	}
	
	//게시물 모두 삭제하기
	@GetMapping("/delete-all-boards")
	public String deleteAllBoards() {
		boardService.deleteAllBoards();
		return "redirect:/boards";		
	}
}

/*
 Model model
 - 컨트롤러에서 뷰로 데이터를 전달할 때 사용하는 인터페이스
 - 컨트롤러에 있는 메서드에서 매개변수로 Model을 선언하면 Get에 추가한 데이터는 자동으로 뷰에 전달됨
 - Select 해서 DB에서 담겨온 데이터는 자동으로 모델에 담겨져 View(html) 파일로 전달됨
 
 @ModelAttribute
  - 폼 데이터나 URL 경로에서 전달된 데이터를 객체에 넣어줄 때 사용
  - 클라이언트에서 전송한 데이터를 객체로 값을 넣어주고 컨트롤러에서 사용할 수 있도록 해주는 것
  - 전달된 데이터는 mapper를 통해 DB에 저장됨
 */










