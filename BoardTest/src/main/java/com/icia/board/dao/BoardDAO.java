package com.icia.board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icia.board.dto.BoardDTO;
import com.icia.board.dto.PageDTO;
@Repository
public class BoardDAO {

	@Autowired
	private SqlSessionTemplate sql;
	
	//게시글 작성
	public int boardWriteFile(BoardDTO board) {
		return sql.insert("Board.boardWrite",board);
	}

	//목록
	public List<BoardDTO> boardList() {
		return sql.selectList("Board.boardList");
	}

	//게시글 갯수 조회
	public int listCount() {
		return sql.selectOne("Board.listCount");
	}


	//페이징
	public List<BoardDTO> paginglist(PageDTO paging) {
		return sql.selectList("Board.pagingList",paging);
	}


	//상세상세
	public BoardDTO boardView(int bnum) {
		return sql.selectOne("Board.boardView",bnum);
	}

	//조회수
	public int boardhit(int bnum) {
		return sql.update("Board.boardhit",bnum);		
	}

	//삭제
	public int boardDelete(int bnum) {
		return sql.delete("Board.boardDelete",bnum);
	}

	public int modifyProcess(BoardDTO board) {
		return sql.update("Board.modifyProcess",board);
	}

	/*
	 * public List<BoardDTO> boardSearch(String type, String keyword) {
	 * if(type.contentEquals("1")) { return sql.selectList("Board.search1",
	 * keyword); } else { return sql.selectList("Board.search2", keyword); } }
	 */
	
	public List<BoardDTO> boardSearch(String type, String keyword) {
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("type", type);
		map.put("keyword", keyword);
		return sql.selectList("Board.search", map);
		
		
		/*
		 * if(type.equals("BWRITER")) { return sql.selectList("Board.search1", keyword);
		 * } else { return sql.selectList("Board.search2", keyword); }
		 */
	}

	
}
