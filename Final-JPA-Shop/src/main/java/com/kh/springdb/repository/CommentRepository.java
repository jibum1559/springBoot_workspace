package com.kh.springdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kh.springdb.model.Comments;

public interface CommentRepository extends JpaRepository<Comments, Long> {
}
