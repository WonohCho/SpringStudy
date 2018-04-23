package kr.co.jwo.board.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.jwo.board.dto.BoardCommentDTO;
import kr.co.jwo.board.dto.BoardDocDTO;
import kr.co.jwo.board.dto.BoardMapDTO;
import kr.co.jwo.board.dto.BoardSearchDTO;
import kr.co.jwo.board.service.IBoardCommentService;
import kr.co.jwo.board.service.IBoardDocService;
import kr.co.jwo.board.service.IBoardMapService;
import kr.co.jwo.user.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/board/doc/")
public class BoardDocController {

	@Autowired private IBoardDocService boardDocService = null;
	@Autowired private IBoardMapService mapService = null;
	@Autowired private IBoardCommentService commentService = null;
	/**
	 * 게시판 목록
	 */
	@RequestMapping(value="/list.god", method=RequestMethod.GET)
	public void list(Model model, @ModelAttribute("search") BoardSearchDTO search) {
		log.debug("/board/doc/list.god/의 search확인!!!!" + search);
		
		
		//게시판 목록 가져오기
		
		List<BoardDocDTO> list = boardDocService.list(search);
		model.addAttribute("list", list);
		
		//통합맵 정보 가져오기
		BoardMapDTO boardMapDTO = mapService.view(search.getMapId());
		model.addAttribute("mapDTO", boardMapDTO);
	}
	
	//글쓰기화면으로 이동
	@RequestMapping(value="/write.god", method=RequestMethod.GET)
	public void goWrite(Model model, Integer mapId) {
		model.addAttribute("mapId", mapId);
		log.debug("mapId@@@@@@@@@@@@@@@@@@@@@@" + mapId);
	}
	
	
//	@ResponseBody
//	@RequestMapping(value="/write.god", method=RequestMethod.GET)
//	public void goUpdate(Model model, String boardContents, String title, Integer mapId) {
//		log.debug("업데이트화면 boardContents와 title 가져오기~~~~~>>>>>" + boardContents + ", " + title);
//	}
	
	@ResponseBody
	@RequestMapping(value="/write.god", method=RequestMethod.POST)
	public void doWrite(Model model, HttpSession session, BoardDocDTO boardDocDTO) {
		UserDTO userDTO = (UserDTO) session.getAttribute("_user");
		boardDocDTO.setUserId(userDTO.getUserId());
		boardDocService.add(boardDocDTO);
		
		log.debug("====@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@=====>>" + userDTO);
		log.debug("====@@@@@@@@@@@@@@  boardDocDTO  @@@@@@@@@@@@@@@@@@@@@@@@ =====>>" + boardDocDTO);
	}
	
	/**
	 * 조회
	 * @param model
	 * @param search
	 * @param docId
	 */
	@RequestMapping(value="/view.god", method=RequestMethod.GET)
	public void view(Model model, @ModelAttribute("search") BoardSearchDTO search, Integer docId) {
		//통합맵 정보 가져오기
		BoardMapDTO boardMapDTO = mapService.view(search.getMapId());
		model.addAttribute("mapDTO", boardMapDTO);
		
		log.debug("view의  search" + search);
		
		// 조회
		BoardDocDTO docDTO = boardDocService.view(docId);
		log.debug("view의 docDTO  >>>>>" + docDTO);
		model.addAttribute("docDTO", docDTO);
	}
	
	//게시판 수정이동
	@RequestMapping(value="/edit.god", method=RequestMethod.GET)
	public void goEdit(Model model, Integer docId, @ModelAttribute("search") BoardSearchDTO search) {
		model.addAttribute("docId", docId);
		BoardDocDTO docDTO = boardDocService.view(docId);
		log.debug("view의 docDTO  >>>>>" + docDTO);
		model.addAttribute("docDTO", docDTO);
	}
	
	
//	@ResponseBody
//	@RequestMapping(value="/write.god", method=RequestMethod.GET)
//	public void goUpdate(Model model, String boardContents, String title, Integer mapId) {
//		log.debug("업데이트화면 boardContents와 title 가져오기~~~~~>>>>>" + boardContents + ", " + title);
//	}
	
	/**
	 * 게시판 수정
	 * @param model
	 * @param session
	 * @param boardDocDTO
	 */
	@RequestMapping(value="/edit.god", method=RequestMethod.POST)
	public String doEdit(Model model, HttpSession session, BoardDocDTO boardDocDTO, @ModelAttribute("search") BoardSearchDTO search) {
		UserDTO userDTO = (UserDTO) session.getAttribute("_user");
		boardDocDTO.setUserId(userDTO.getUserId());
		boardDocService.edit(boardDocDTO);
		
		log.debug("====@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@=====>>" + userDTO);
		log.debug("====@@@@@@@@@@@@@@  boardDocDTO  @@@@@@@@@@@@@@@@@@@@@@@@ =====>>" + boardDocDTO);
		return "redirect:./view.god?docId=" + boardDocDTO.getDocId() + "&" + search.getParams();
	}
	


}
