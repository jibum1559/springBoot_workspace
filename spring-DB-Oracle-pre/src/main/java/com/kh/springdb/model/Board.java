package com.kh.springdb.model;

import java.sql.Timestamp;

public class Board {
/*
 BOARD_ID	NUMBER(10,0)
TITLE	VARCHAR2(100 BYTE)
CONTENT	VARCHAR2(200 BYTE)
IMAGE	BLOB
CREATED_AT	TIMESTAMP(6)
AUTHOR	VARCHAR2(50 BYTE) 
 */
	private int board_id;
	private String title;
	private String content;
	private String author;
	
	public int getBoard_id() {
		return board_id;
	}
	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}

	
}
