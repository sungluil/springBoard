package com.spring.board.service;

import java.util.List;
import java.util.Map;

import com.spring.board.vo.BoardVo;
import com.spring.board.vo.ComCodeVo;
import com.spring.board.vo.PageVo;
import com.spring.board.vo.UserInfoVo;

public interface boardService {

	public String selectTest() throws Exception;

	public List<BoardVo> SelectBoardList(PageVo pageVo) throws Exception;
	
	public List<BoardVo> selectPaperBoardList(Map<String, Object> map) throws Exception;

	public BoardVo selectBoard(String boardType, int boardNum) throws Exception;

	public int selectBoardCnt() throws Exception;

	public int boardInsert(BoardVo boardVo) throws Exception;
	
	public int boardUpdate(BoardVo boardVo) throws Exception;
	
	public int boardDelete(BoardVo boardVo) throws Exception;
	
	public List<BoardVo> searchBoardList(Map<String, Object> keyList) throws Exception;
	
	public List<ComCodeVo> codeList(ComCodeVo comCodeVo) throws Exception;
	
	public int userInsert(UserInfoVo userinfoVo) throws Exception;
	
	public UserInfoVo userinfoSelect(String userId) throws Exception;
	
	public UserInfoVo idcheck(String userId) throws Exception;
	
	public UserInfoVo pwcheck(String userId, String userPw) throws Exception;
	
	public int pwCheckCount(UserInfoVo userinfoVo) throws Exception;
	
	public BoardVo updateReadHits(String boardType, int boardNum) throws Exception;
	
	//´ä±Û
    public int boardInsertRef(BoardVo boardVo) throws Exception;
    public int boardUpdateRef(BoardVo boardVo) throws Exception;

}
