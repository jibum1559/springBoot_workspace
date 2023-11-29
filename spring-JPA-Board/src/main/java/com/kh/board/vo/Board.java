package com.kh.board.vo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="Board")
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="board_id_sequence")  //이 어노테이션으로 트리거 역할
	@SequenceGenerator(name = "board_id_sequence", sequenceName="board_id_sequence", allocationSize = 1)
	//시퀀스를 만드는 권한이 없어서 우선 db에서 임의로 시퀀스 설정 후 작업진행
	@Column(name = "board_id") //-> 컬럼명이 다를 때
	private Long boardId;
	private String title;
	private String content;
	private String author;
}
