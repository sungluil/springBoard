package com.spring.board.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.board.dao.BoardDao;
import com.spring.board.vo.BoardVo;
import com.spring.board.vo.ComCodeVo;
import com.spring.board.vo.PageVo;
import com.spring.board.vo.UserInfoVo;

@Repository
public class BoardDaoImpl implements BoardDao{
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public String selectTest() throws Exception {
		// TODO Auto-generated method stub
		
		String a = sqlSession.selectOne("board.boardList");
		
		return a;
	}
	/**
	 * 
	 * */
	@Override
	public List<BoardVo> selectBoardList(PageVo pageVo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("board.boardList",pageVo);
	}
	
	@Override
	public int selectBoardCnt() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("board.boardTotal");
	}
	
	@Override
	public BoardVo selectBoard(BoardVo boardVo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("board.boardView", boardVo);
	}
	
	@Override
	public int boardInsert(BoardVo boardVo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.insert("board.boardInsert", boardVo);
	}
	@Override
	public int boardUpdate(BoardVo boardVo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.update("board.boardUpdate", boardVo);
	}

	@Override
	public int boardDelete(BoardVo boardVo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.delete("board.boardDelete", boardVo);
	}
	@Override
	public List<BoardVo> searchBoardList(Map<String, Object> keyList) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("board.boardSearchList", keyList);
	}
	@Override
	public List<ComCodeVo> codeList(ComCodeVo comCodeVo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("board.codeList", comCodeVo);
	}
	@Override
	public int userInsert(UserInfoVo userinfoVo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.insert("board.userInsert", userinfoVo);
	}
	@Override
	public UserInfoVo userinfoSelect(String userId) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("board.userinfoSelect", userId);
	}
	@Override
	public UserInfoVo idcheck(String userId) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("board.idcheck", userId);
	}
	@Override
	public UserInfoVo pwcheck(UserInfoVo userinfoVo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("board.pwcheck", userinfoVo);
	}
	@Override
	public int pwCheckCount(UserInfoVo userinfoVo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("board.pwCheckCount", userinfoVo);
	}
	
	
}
