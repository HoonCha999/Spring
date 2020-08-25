package com.icia.board.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.icia.board.dto.CommentDTO;
import com.icia.board.service.CommentService;

@Controller
@RequestMapping("/comment/*")
public class CommentController {

	@Autowired
	private CommentService cmtsvc;

	private ModelAndView mav = new ModelAndView();
	
	List<CommentDTO> commentList = new ArrayList<CommentDTO>();
	
	
	// 댓글 목록 조회
	// 'comment/commentlist'로 보내는 값을 받는다.
	// CommentDTO -> cbnum만 받아와도 상관 x
	// JSON형태를 받아오기 위해서 @ResponseBody를 사용한다.
	@RequestMapping(value="commentList")
	public @ResponseBody List<CommentDTO> commentList(@ModelAttribute CommentDTO comment) {
		commentList = cmtsvc.commentList(comment);
		return commentList;
	}
		
	// 댓글 입력
	@RequestMapping(value="commentwrite")
	public @ResponseBody List<CommentDTO> commentWrite(@ModelAttribute CommentDTO comment) {
		System.out.println("1 ============================================");
		System.out.println(comment);
		commentList = cmtsvc.commentWrite(comment);
		return commentList;
		
	}
	
	// 댓글 삭제
	@RequestMapping(value="commentdelete")
	public @ResponseBody List<CommentDTO> commentDelete(@ModelAttribute CommentDTO comment) {
		commentList = cmtsvc.commentDelete(comment);
		return commentList;
			
	}
	
}
