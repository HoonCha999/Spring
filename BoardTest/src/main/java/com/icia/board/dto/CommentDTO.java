package com.icia.board.dto;

import lombok.Data;

@Data
public class CommentDTO {
	
	private int cnum;			// 댓글 번호(PK)
	private int cbnum;			// 게시글 번호(FK)
	private String cwriter;		// 작성자
	private String ccontents;	// 댓글 내용
	
}
