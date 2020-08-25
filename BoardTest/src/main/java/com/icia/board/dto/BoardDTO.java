package com.icia.board.dto;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class BoardDTO {
	private int bnum;
	private String bwriter;
	private String bpw;
	private String btitle;
	private String bcontents;
	private Date bdate;
	private int bhit;
	
	private MultipartFile bfile;
	private String bfilename;
}
