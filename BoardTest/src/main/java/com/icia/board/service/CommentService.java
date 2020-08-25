package com.icia.board.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.icia.board.dao.CommentDAO;
import com.icia.board.dto.CommentDTO;

@Service
public class CommentService {

	@Autowired
	private CommentDAO cdao;

	List<CommentDTO> commentList = new ArrayList<CommentDTO>();
	
	private ModelAndView mav = new ModelAndView();
	
	// comment.getCbnum() => cbnum
	public List<CommentDTO> commentList(CommentDTO comment) {
		commentList = cdao.commentList(comment.getCbnum());
		mav.addObject("commentList", commentList);
		mav.setViewName("boardView");
		return commentList;
	}

	public List<CommentDTO> commentWrite(CommentDTO comment) {
		int writeResult = cdao.commentWrite(comment);
		
		if(writeResult > 0) {
			commentList = cdao.commentList(comment.getCbnum());
		} else {
			commentList = null;
		}
		return commentList;
	}

	// 댓글 삭제
	public List<CommentDTO> commentDelete(CommentDTO comment) {
		int delResult = cdao.commentDelete(comment.getCnum());
		
		if(delResult > 0) {
			commentList = cdao.commentList(comment.getCbnum());
		} else {			
			commentList = null;
		}
		return commentList;
	}
	
}
