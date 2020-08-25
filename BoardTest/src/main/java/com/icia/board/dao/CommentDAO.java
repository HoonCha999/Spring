package com.icia.board.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icia.board.dto.CommentDTO;

@Repository
public class CommentDAO {

	@Autowired
	private SqlSessionTemplate sql;

	// 댓글 목록 조회
	public List<CommentDTO> commentList(int cbnum) {
		return sql.selectList("Comment.commentList", cbnum);
	}

	// 댓글 입력
	public int commentWrite(CommentDTO comment) {
		return sql.insert("Comment.commentWrite", comment);
	}

	public int commentDelete(int cnum) {
		return sql.delete("Comment.commentDelete", cnum);
	}
	
}
