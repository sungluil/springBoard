package com.spring.board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring.board.HomeController;
import com.spring.board.service.boardService;
import com.spring.board.vo.BoardVo;
import com.spring.board.vo.ComCodeVo;
import com.spring.board.vo.PageVo;
import com.spring.board.vo.UserInfoVo;
import com.spring.common.CommonUtil;
import com.sun.org.apache.bcel.internal.util.BCELifier;

@Controller
public class BoardController {
	
	@Autowired 
	boardService boardService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/board/boardList.do", method = RequestMethod.GET)
	public String boardList(Locale locale , ComCodeVo comCodeVo, PageVo pageVo,BoardVo boardVo, Model model) throws Exception{
		
		List<BoardVo> boardList = new ArrayList<BoardVo>();
		List<ComCodeVo> codeList = new ArrayList<ComCodeVo>();
		
		int page = 1;
		int totalCnt = 0;
		
		if(pageVo.getPageNo() == 0){
			pageVo.setPageNo(page);;
		}
		codeList = boardService.codeList(comCodeVo);
		
		boardList = boardService.SelectBoardList(pageVo);
		totalCnt = boardService.selectBoardCnt();
		
		
		model.addAttribute("boardList", boardList);
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("pageNo", page);
		model.addAttribute("board", boardVo);
		model.addAttribute("codeList", codeList);
		model.addAttribute("comCodeVo", comCodeVo);
		
		return "board/boardList";
	}
	
	@RequestMapping(value = "/board/{boardType}/{boardNum}/boardView.do", method = RequestMethod.GET)
	public String boardView(Locale locale, Model model
			,@PathVariable("boardType")String boardType
			,@PathVariable("boardNum")int boardNum, BoardVo boardVo) throws Exception{
		
		//BoardVo boardVo = new BoardVo();
		
		
		boardVo = boardService.selectBoard(boardType,boardNum);
		
		//model.addAttribute("boardType", boardType);
		//model.addAttribute("boardNum", boardNum);
		model.addAttribute("board", boardVo);
		
		return "board/boardView";
	}
	
	@RequestMapping(value = "/board/boardWrite.do", method = RequestMethod.GET)
	public String boardWrite(Locale locale,ComCodeVo comCodeVo,PageVo pageVo, Model model) throws Exception{
		
		List<ComCodeVo> codeList = new ArrayList<ComCodeVo>();
		
		
		codeList = boardService.codeList(comCodeVo);
		
		model.addAttribute("codeList", codeList);
		model.addAttribute("comCodeVo", comCodeVo);
		
		return "board/boardWrite";
	}
	
	@RequestMapping(value = "/board/boardWriteAction.do", method = RequestMethod.POST)
	@ResponseBody
	public String boardWriteAction(Locale locale,BoardVo boardVo) throws Exception{
		
		HashMap<String, String> result = new HashMap<String, String>();
		CommonUtil commonUtil = new CommonUtil();
		
		int resultCnt = boardService.boardInsert(boardVo);
		
		result.put("success", (resultCnt > 0)?"Y":"N");
		String callbackMsg = commonUtil.getJsonCallBackString(" ",result);
		
		System.out.println("callbackMsg::"+callbackMsg);
		return callbackMsg;
	}
	
	//게시글 변경
	@RequestMapping("/board/{boardType}/{boardNum}/boardUpdate.do")
	public String boardUpdate(Model model
			,@PathVariable("boardType")String boardType
			,@PathVariable("boardNum")int boardNum, BoardVo boardVo) throws Exception{
		

		boardVo = boardService.selectBoard(boardType,boardNum);
		
		model.addAttribute("board", boardVo);
		
		return "board/boardUpdate";
	}

	
	@RequestMapping(value = "/board/boardUpdateAction.do", method = RequestMethod.POST)
	@ResponseBody
	public String boardUpdate(BoardVo boardVo) throws Exception {
		
		HashMap<String, String> result = new HashMap<String, String>();
		CommonUtil commonUtil = new CommonUtil();
		
		int resultCnt = boardService.boardUpdate(boardVo);
		
		result.put("success", (resultCnt > 0)?"Y":"N");
		String callbackMsg = commonUtil.getJsonCallBackString(" ",result);
		
		System.out.println("callbackMsg::"+callbackMsg);
		return callbackMsg;
	}
	
	@RequestMapping(value = "/booard/boardDeleteAction.do", method = RequestMethod.POST)
	@ResponseBody
	public String boardDelete(BoardVo boardVo) throws Exception {
		
		//에러 발생시 사용자에게 에러공유
		/*
		if(boardService.selectBoard(boardType, boardNum)==null ) {
			throw new RuntimeException("에러발생");
		} else {
			boardService.boardDelete(boardVo);			
			return "success";
		}
		*/
		
		int resultCnt = boardService.boardDelete(boardVo);
		if(resultCnt>0) {
			return "success";
		} else {
			throw new RuntimeException("게시판목록이 존재하지 않습니다.");
		}
	}
	
	//체크박스 검색
	@RequestMapping(value = "/board/boardSearch.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> searchBoardList(String boardType, PageVo pageVo,BoardVo boardVo, Model model) throws Exception{
		
		int page = 1;

		if(pageVo.getPageNo() == 0){
			pageVo.setPageNo(page);;
		}
		
		String[] str=boardType.split(",");
		
		List<String> List = new ArrayList<String>();
		
		for(int i=0;i<str.length;i++) {
			List.add(str[i]);
		}
		
		Map<String, Object> keyList = new HashMap<String, Object>();
		
		///////////////키////////값
		keyList.put("keyList1", List);
		keyList.put("pageNo", page);

		List<BoardVo> boardList = boardService.searchBoardList(keyList);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("boardList", boardList);
		

		return map;
		
	}
	//체크박스 검색HTML 방식
//	@RequestMapping(value = "/board/boardSearch.do", method = RequestMethod.POST)
//	public String searchBoardList(String boardType, PageVo pageVo,BoardVo boardVo, Model model) throws Exception{
//		
//		int page = 1;
//
//		if(pageVo.getPageNo() == 0){
//			pageVo.setPageNo(page);;
//		}
//		
//		List<String> List = new ArrayList<String>();
//		
//		String[] str=boardType.split(",");
//		
//		for(int i=0;i<str.length;i++) {
//			List.add(str[i]);
//		}
//		
//		Map<String, Object> keyList = new HashMap<String, Object>();
//		
//		///////////////키////////값
//		keyList.put("keyList", List);
//		keyList.put("pageNo", page);
//
//
//		
//		List<BoardVo> boardList = boardService.searchBoardList(keyList);
//		model.addAttribute("boardList", boardList);
//		
//		return "board/searchTable";
//	
//	}
	
	
	//회원가입
	@RequestMapping("/board/memberWrite.do")
	public String memberWrite(UserInfoVo userinfoVo, ComCodeVo comCodeVo,Model model) throws Exception {
		
		List<ComCodeVo> codeList = new ArrayList<ComCodeVo>();
		
		UserInfoVo userinfo=new UserInfoVo();
		
		codeList = boardService.codeList(comCodeVo);
		
		model.addAttribute("codeList1", codeList);
		model.addAttribute("comCodeVo", comCodeVo);
		model.addAttribute("userinfo", userinfo);
		return "board/memberWrite";
	}
	
	//로그인
	@RequestMapping("/board/memberLogin.do")
	public String memberLogin() {
		
		return "board/memberLogin";
	}
	@RequestMapping(value = "/board/idchecked.do", method = RequestMethod.POST)
	@ResponseBody
	public int idchecked(String userId, String userPw, HttpSession session,UserInfoVo userinfoVo) throws Exception {
		
		
		UserInfoVo userinfo=new UserInfoVo();
		
		userinfo.setUserId(userId);
		userinfo.setUserPw(userPw);
		
		int result = 0;
		
		//UserInfoVo result1 = boardService.pwcheck(userId, userPw);
		result = boardService.pwCheckCount(userinfo);
		
		System.out.println(result);
		
		if(result != 0) {
			result=1;
		}
		if(result == 1) {
			session.setAttribute("userinfo", boardService.userinfoSelect(userinfoVo.getUserId()));
		}
		
		return result;
	}
	
	
	@RequestMapping(value = "/board/boardList.do")
	@ResponseBody
	public String memberLoginAction(@ModelAttribute UserInfoVo userinfoVo, Model model, HttpSession session) throws Exception {
		
		//UserInfoVo userinfo=boardService.userinfoSelect(userinfoVo.getUserId());
		
		session.setAttribute("userinfo", boardService.userinfoSelect(userinfoVo.getUserId()));
		return "redirect:/board/boardList.do";
	}
	
	
	@RequestMapping(value = "/board/memberWriteAction.do",method = RequestMethod.POST)
	public String memberJoin(UserInfoVo userinfoVo, Model model) throws Exception {

		try {
			boardService.userInsert(userinfoVo);
		} catch (Exception e) {
			model.addAttribute("message", e.getMessage());
			return "board/memberWrite";
		}
		
		return "board/memberLogin";
	}
	
	@RequestMapping("/board/IdCheckForm.do")
	public String loginCheck() {
		
		return "board/IdCheckForm";
	}
	@RequestMapping("/board/memberLogout.do")
	public String logout(HttpSession session) {
		
		session.invalidate();
		
		return "redirect:/board/boardList.do";
	}
	@RequestMapping("/board/MemberModify.do")
	public String memberModify() {
		
		
		return "board/memberModify";
	}
	
	@RequestMapping(value = "/board/test.do", method = RequestMethod.GET)
	@ResponseBody
	public String test(Locale locale,BoardVo boardVo) throws Exception{
		
		HashMap<String, Integer> result = new HashMap<String, Integer>();
		CommonUtil commonUtil = new CommonUtil();
		
		
		result.put("test", 1111);
		result.put("test1", 2222);
		result.put("test2", 333);
		result.put("test3", 4444);
		result.put("test4", 55555);
		
		String callbackMsg = commonUtil.getJsonCallBackString(" ",result);
		
		System.out.println("callbackMsg::"+callbackMsg);
		return callbackMsg;
	}
	
	@RequestMapping(value = "/idcheck.do", method = RequestMethod.POST)
	@ResponseBody
	public int idcheck(HttpServletRequest req) throws Exception {
		
		logger.info("idCheck");
		
		String userId = req.getParameter("userId");
		
		UserInfoVo idCheck = boardService.idcheck(userId);
		
		
		int result=0;
		
		if(idCheck != null) {
			result = 1;
		}
		
		return result;
	}

	
	
}
