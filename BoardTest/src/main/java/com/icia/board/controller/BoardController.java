package com.icia.board.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.icia.board.dto.BoardDTO;
import com.icia.board.service.BoardService;

@Controller
public class BoardController {
	
	private ModelAndView mav=new ModelAndView();
	
	@Autowired
	private BoardService bdsvc;
	
	//게시판글쓰기페이지로 이동
	@RequestMapping(value="/boardForm")
	public String boardForm() {
		return "BoardWrite";
	}
	
	
	//게시판글쓰기
	@RequestMapping(value="/boardWriteFile",method=RequestMethod.POST)
	public ModelAndView boardWriteFile(@ModelAttribute BoardDTO board)throws IllegalStateException,IOException {
		mav=bdsvc.boardWriteFile(board);
		return mav;
	}
	

	//게시판목록
	@RequestMapping(value="/boardList")
	public ModelAndView boardList(){
		mav=bdsvc.boardList();
		return mav;
	}


	//게시판페이징
		@RequestMapping(value="/pagingList")
		public ModelAndView pagingList(@RequestParam(value="page",required=false,defaultValue="1")int page) {
			if(page==0) {
				page=1;
			}
			mav=bdsvc.pagingList(page);
			return mav;
		}


//required=false : 필수로 page를 받아오지 않아도 된다
//defaultValue="1" : 기본값은 1
//		같은 역할
//int page=1;
//page=request.getParameter("page");

		
		//게시글 상세보기
		@RequestMapping(value="/boardView")
		public ModelAndView boardView(@RequestParam(value="bnum")int bnum,@RequestParam(value="page",required=false,defaultValue="1")int page){
			mav=bdsvc.boardView(bnum,page);
			bdsvc.boardhit(bnum);
			return mav;
		}
		
		
		//게시글 삭제
		@RequestMapping(value="/boardDelete")
		public ModelAndView boardDelete(@RequestParam(value="bnum")int bnum,@RequestParam(value="page",required=false,defaultValue="1")int page){
			mav=bdsvc.boardDelete(bnum,page);
			return mav;
		}
		
		//게시글 수정페이지로
		@RequestMapping(value="/boardModify")
		public ModelAndView boardModify(@RequestParam(value="bnum")int bnum,@RequestParam(value="page",required=false,defaultValue="1")int page){
			mav=bdsvc.boardModify(bnum,page);
			return mav;
		}
		
		//게시글 수정
		@RequestMapping(value="/modifyProcess")
		public ModelAndView modifyProcess(@ModelAttribute BoardDTO board, @RequestParam(value="page",required=false,defaultValue="1")int page) throws IllegalStateException, IOException{
			mav=bdsvc.modifyProcess(board,page);
			return mav;
		}
		
		//게시글 검색
		@RequestMapping(value="/boardSearch")
		public ModelAndView boardSearch(@RequestParam(value="type") String type,
				@RequestParam(value="keyword") String keyword){
			System.out.println("========== 게시글 검색 ==========");
			System.out.println("type : " + type + ", keyword : " + keyword);
				mav=bdsvc.boardSearch(type, keyword);
			return mav;
		}
		
}

