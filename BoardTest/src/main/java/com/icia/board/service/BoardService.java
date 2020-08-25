package com.icia.board.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.icia.board.dao.BoardDAO;
import com.icia.board.dao.CommentDAO;
import com.icia.board.dto.BoardDTO;
import com.icia.board.dto.CommentDTO;
import com.icia.board.dto.PageDTO;

@Service
public class BoardService {

	ModelAndView mav=new ModelAndView();
	
	@Autowired
	private BoardDAO bdao;
	
	@Autowired
	private CommentDAO cdao;
	
	//게시판작성
	public ModelAndView boardWriteFile(BoardDTO board) throws IllegalStateException, IOException{
		MultipartFile bFile=board.getBfile();
		String fileName=bFile.getOriginalFilename();
		
		String savePath="D:/DEV/Spring/BoardTest/src/main/webapp/resources/fileUpload/"+fileName;
		
		if(!bFile.isEmpty()) {
			bFile.transferTo(new File(savePath));
		}
		board.setBfilename(fileName);
		
		int writeResult=bdao.boardWriteFile(board);
		if(writeResult>0) {
			mav.setViewName("index");
		}else {
			mav.setViewName("index");
		}
		return mav;
	}


	//게시판목록
	public ModelAndView boardList() {
		List<BoardDTO> boardlist=bdao.boardList();
		mav.addObject("board",boardlist);
		mav.setViewName("boardList");
		return mav;
	}

	
	//페페페페페이징

	private static final int PAGE_LIMIT=5; //한 페이지에 보여줄 글 갯수
	private static final int BLOCK_LIMIT=5; //회면에 보여줄 페이지 번호
	
	public ModelAndView pagingList(int page) {
		PageDTO paging=new PageDTO();
		
		//게시글 갯수 조회
		int listcount=bdao.listCount();
		
		int startrow=(page-1)*PAGE_LIMIT+1;
		int endrow=page*PAGE_LIMIT;
		
		paging.setPage(page);		
		paging.setStartrow(startrow);
		paging.setEndrow(endrow);
		
		int maxpage=(int)(Math.ceil((double)listcount/PAGE_LIMIT));
		int startpage=(((int)(Math.ceil((double)page/BLOCK_LIMIT)))-1)*BLOCK_LIMIT+1;
		int endpage=startpage+BLOCK_LIMIT-1;
		
		if(endpage>maxpage) {
			endpage=maxpage;
		}
		/*
		 * 	(1) 올림 : Math.ceil
		 * 	(2) 내림 : Math.floor
		 * 	(3) 반올림 : Math.round 
		 */
		

		paging.setMaxpage(maxpage);
		paging.setStartpage(startpage);
		paging.setEndpage(endpage);
		
		List<BoardDTO> paginglist=bdao.paginglist(paging);
		mav.addObject("paginglist",paginglist);
		mav.addObject("paging", paging);
		mav.setViewName("pagingList");
		
		return mav;
	}

	
	//상세보기
	public ModelAndView boardView(int bnum, int page) {
		BoardDTO bView=bdao.boardView(bnum);
		
		// Commment(댓글)로 인해 추가한 부분
		List<CommentDTO> commentList = cdao.commentList(bnum);		  
		mav.addObject("commentList", commentList);
		
		mav.addObject("bView",bView);
		mav.addObject("page", page);
		mav.setViewName("boardView");
		
		return mav;
	}


	//조회수
	public void boardhit(int bnum) {
		bdao.boardhit(bnum); 
	}


	//게시글 삭제
	public ModelAndView boardDelete(int bnum, int page) {
		int delResult=bdao.boardDelete(bnum);
		if(delResult>0) {
			mav.setViewName("redirect:/pagingList?page="+page);
		}else {
			mav.setViewName("index");
		}
		return mav;
	}


	//게시글 수정페이지로
	public ModelAndView boardModify(int bnum, int page) {
		BoardDTO bModify=bdao.boardView(bnum); //상세보기랑 연결하면 dao,mapper만들 필요 XXX
		
		mav.addObject("page",page);
		mav.addObject("bModfiy",bModify);
		mav.setViewName("BoardModify");
		return mav;
	}


	//게시글 찐수정
	public ModelAndView modifyProcess(BoardDTO board, int page) throws IllegalStateException, IOException {
		System.out.println("==================1===============");
		System.out.println(board);
		MultipartFile bFile=board.getBfile();
		//실제 업로드 된 파일
		String fileName=bFile.getOriginalFilename();
		//업로드 된 파일의 이름
		String savePath="D:/dev/spring/BoardTest/src/main/webapp/resources/fileUpload/"+fileName;
		
		//파일 업로드 될 경우
		if(!bFile.isEmpty()) {
			bFile.transferTo(new File(savePath));
		}
		
		
		board.setBfilename(fileName);
		System.out.println("==================2===============");
		System.out.println(board);
		
		int mResult=bdao.modifyProcess(board);
		if(mResult>0) {
			mav.addObject("page", page);
			mav.setViewName("redirect:/boardView?bnum="+board.getBnum());
		}else {
			mav.setViewName("pagingList?page="+page);
		}
		return mav; 
	}


	public ModelAndView boardSearch(String type, String keyword) {
		List<BoardDTO> searchList = bdao.boardSearch(type, keyword);
		mav.addObject("paginglist", searchList);
		mav.setViewName("pagingList");
		
		return mav;
	}

}
