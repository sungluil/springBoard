package com.spring.board.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.board.dao.BoardDao;
import com.spring.board.service.boardService;
import com.spring.board.vo.BoardVo;
import com.spring.board.vo.ComCodeVo;
import com.spring.board.vo.PageVo;
import com.spring.board.vo.UserInfoVo;
import com.sun.org.apache.bcel.internal.util.BCELifier;

@Service
public class boardServiceImpl implements boardService{
	
	@Autowired
	BoardDao boardDao;
	
	@Override
	public String selectTest() throws Exception {
		// TODO Auto-generated method stub
		return boardDao.selectTest();
	}
	
	@Override
	public List<BoardVo> SelectBoardList(PageVo pageVo) throws Exception {
		// TODO Auto-generated method stub
		
		return boardDao.selectBoardList(pageVo);
	}
	
	@Override
	public int selectBoardCnt() throws Exception {
		// TODO Auto-generated method stub
		return boardDao.selectBoardCnt();
	}
	
	@Override
	public BoardVo selectBoard(String boardType, int boardNum) throws Exception {
		// TODO Auto-generated method stub
		BoardVo boardVo = new BoardVo();
		
		boardVo.setBoardType(boardType);
		boardVo.setBoardNum(boardNum);
		
		return boardDao.selectBoard(boardVo);
	}
	
	@Override
	public int boardInsert(BoardVo boardVo) throws Exception {
		// TODO Auto-generated method stub
		return boardDao.boardInsert(boardVo);
	}

	@Override
	public int boardUpdate(BoardVo boardVo) throws Exception {
		// TODO Auto-generated method stub
		
		return boardDao.boardUpdate(boardVo);
	}

	@Override
	public int boardDelete(BoardVo boardVo) throws Exception {
		// TODO Auto-generated method stub
		
		return boardDao.boardDelete(boardVo);
	}

	@Override
	public List<BoardVo> searchBoardList(Map<String, Object> keyList) throws Exception {
		// TODO Auto-generated method stub
		return boardDao.searchBoardList(keyList);
	}

	@Override
	public List<ComCodeVo> codeList(ComCodeVo comCodeVo) throws Exception {
		// TODO Auto-generated method stub
		return boardDao.codeList(comCodeVo);
	}

	@Override
	public int userInsert(UserInfoVo userinfoVo) throws Exception {
		return boardDao.userInsert(userinfoVo);
	}

	@Override
	public UserInfoVo userinfoSelect(String userId) throws Exception {
		
		return boardDao.userinfoSelect(userId);
	}

	@Override
	public UserInfoVo idcheck(String userId) throws Exception {
		// TODO Auto-generated method stub
		return boardDao.idcheck(userId);
	}

	@Override
	public UserInfoVo pwcheck(String userId, String userPw) throws Exception {
		// TODO Auto-generated method stub
		UserInfoVo userinfoVo=new UserInfoVo();
		userinfoVo.setUserId(userId);
		userinfoVo.setUserPw(userPw);
		
		return boardDao.pwcheck(userinfoVo);
	}

	@Override
	public int pwCheckCount(UserInfoVo userinfoVo) throws Exception {
		// TODO Auto-generated method stub
		//UserInfoVo userinfoVo=new UserInfoVo();
		//userinfoVo.setUserId(userId);
		//userinfoVo.setUserPw(userPw);
		return boardDao.pwCheckCount(userinfoVo);
	}






	
}
