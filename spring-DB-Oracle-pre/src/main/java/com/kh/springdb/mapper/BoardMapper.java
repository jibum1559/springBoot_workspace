package com.kh.springdb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.springdb.model.Board;

@Mapper
public interface BoardMapper {
	List<Board> getAllBoards();
}
