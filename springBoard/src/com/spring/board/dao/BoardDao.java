package com.spring.board.dao;

import java.util.List;
import java.util.Map;

import com.spring.board.vo.BoardVo;
import com.spring.board.vo.ComCodeVo;
import com.spring.board.vo.PageVo;
import com.spring.board.vo.UserInfoVo;

public interface BoardDao {

	public String selectTest() throws Exception;

	public List<BoardVo> selectBoardList(PageVo pageVo) throws Exception;

	public BoardVo selectBoard(BoardVo boardVo) throws Exception;

	public int selectBoardCnt() throws Exception;

	public int boardInsert(BoardVo boardVo) throws Exception;
	
	public int boardUpdate(BoardVo boardVo) throws Exception;
	
	public int boardDelete(BoardVo boardVo) throws Exception;

    public List<BoardVo> searchBoardList(Map<String, Object> keyList) throws Exception;
    
    public List<ComCodeVo> codeList(ComCodeVo comCodeVo) throws Exception;
    
    public int userInsert(UserInfoVo userinfoVo) throws Exception;
    
    public UserInfoVo userinfoSelect(String userId) throws Exception;
    
    public UserInfoVo idcheck(String userId) throws Exception;
    //아이디 비번찾기
    public UserInfoVo pwcheck(UserInfoVo userinfoVo) throws Exception;
    
    public int pwCheckCount(UserInfoVo userinfoVo) throws Exception;
    
    
    
}
