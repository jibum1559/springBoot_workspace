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


/*
@Lob : Blob, Clob 타입을 매핑
@CreationTimestamp : insert 시 시간을 자동으로 저장
@UpdateTimestamp : update 시 시간을 자동으로 저장
@Temporal : 날짜 타입 매핑
@CreateDate : 엔티티가 생성되어 저장될 때 시간을 저장

@Column : 테이블 안에 컬럼을 생성하거나
			존재하는 테이블에 컬럼값을 찾아 매핑하는 역할을 함
			option
				name : 데이터베이스에서 존재하는 컬럼이름과
						자바 클래스에서 존재하는 필드이름이 일치하지 않을 경우
						자바와 데이터베이스 값이 일치할 수 있도록
						매핑해주는 역할 
				unique : 유니크 제약 조건 설정
				insertable : insert 가능 여부
				updateable : update 가능 여부
				    length : String 타입의 문자 길이 제약조건 설정
		  columnDefinition : 데이터베이스 컬럼 정보 직접 기술
		  			@Column(columnDefinition = "varchar(10) not null")
		  		precision  : 큰 값에서 사용할 수 있음 소수점을 포함한 전체 자리수
		  		scale(DDL) : 소수점 자리수 Double과 foalt 타입에는 적용되지 않음
@GeneratedValue(strategy= GenerationType.)
	GenerationType.AUTO(default) : JPA 자동으로 알아서 생성 전략 결정
	GenerationType.SEQUENCE      : 데이터베이스 시퀀스를 이용해서 기본키를 생성
									간혹 @SequenceGenerator를 사용해서
											시퀀스를 등록할 필요가 있음
	GenerationType.TABLE		 : 키 생성용 테이블 사용
										@TableGenerator 필요
	GenerationType.IDENTITY		 : 기본키 생성을 데이터베이스에 넘겨줌
									ex) mysql 데이터베이스의 경우 AUTO_INCREMENT 사용해서 기본키를 생성
* */