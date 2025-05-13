package jin.spring.board.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jin.spring.board.dto.BoardDTO;
import jin.spring.board.dto.Criteria;
import jin.spring.board.dto.PageMaker;
import jin.spring.board.service.BoardService;
import lombok.RequiredArgsConstructor;

//Spring MVC에서 컨트롤러 역할을 하는 클래스
@Controller

/*
 * final이 붙은 필드를 매개변수로 받는 생성자가 자동 생성
 * 스프링의 IoC 컨테이너는 BoardService 빈을 찾아 BoardController의 생성자에 주입
 */
@RequiredArgsConstructor
public class BoardController {
	private static Logger logger = LoggerFactory.getLogger(BoardController.class);
	
//	생성자 주입을 위한 final 필드
	private final BoardService boardService;
	
//	게시판 글 작성 뷰
	@GetMapping("/BoardInsert")
	public String insert() {
//		게시글 작성 페이지로 이동
		return "./board/board_insert";
	}
	
//	게시판 글 작성-클라이언트가 POST 요청을 보낼 때 호출됨
	@PostMapping("/BoardInsert")
	public String insert(BoardDTO boardDTO) throws Exception {
		logger.info("글 작성");
//		서비스 호출-BoardService의 boardInsert() 메서드를 호출하여 게시글을 데이터베이스에 저장
		boardService.boardInsert(boardDTO);
		
//		게시글 작성 후 게시글 작성 페이지로 리다이렉트
		return "redirect:/BoardList";
	}
	
//	게시판 목록 조회-페이징된 목록만 가져옴 
//	Criteria: 페이징에 필요한 데이터를 담고 있는 객체
//	클릭된 페이지 번호가 Controller로 전달될 때, 그 값들이 Criteria 객체로 매핑됨 
	@GetMapping("/BoardList")
	public String list(Model model, Criteria criteria) throws Exception {
	    logger.info("list");
	    
//	    criteria: 페이징 처리를 위한 데이터를 담고 있으며, 이 값을 통해 페이징된 게시글 목록을 가져옴
//	    현재 페이지에 해당하는 게시글 목록만 모델에 추가
	    model.addAttribute("list", boardService.boardList(criteria));
	    
//	    페이지 네비게이션을 위한 객체 생성 
	    PageMaker pageMaker = new PageMaker();
//	    현재 페이지와 페이지 당 게시글 수를 Criteria 객체에서 설정한 값으로 설정
	    pageMaker.setCri(criteria);
	    
//	    총 게시글 수를 가져와서 PageMaker 객체에 설정하여 전체 페이지 수를 계산함 
	    pageMaker.setTotalCount(boardService.listCount());
	    
//	    모델에 pageMaker 추가 
	    model.addAttribute("pageMaker", pageMaker);
	    
//	   	게시판 목록 조회 페이지로 이동
	    return "./board/board_select";
	}

//	게시판 글 상세 조회
	@GetMapping("/BoardSelect")
	public String select(Model model, BoardDTO boardDTO) throws Exception {
	    logger.info("select");
	    
		/*
		 * boardDTO 객체에서 bdNum 값을 가져와 boardService의 boardSelect 메서드에 파라미터로 넘겨줌
		 * boardService.boardSelect() 메서드는 게시글의 상세 정보를 BoardDTO 객체로 반환 
		 * 반환된 BoardDTO 객체는 model.addAttribute("boardDTO", ...)를 통해 뷰로 전달
		 */
	    model.addAttribute("boardDTO", boardService.boardSelect(boardDTO.getBdNum()));
	    
//	    게시판 글 상세 조회 페이지로 이동
	    return "./board/board_select_detail";
	}
	
//	게시판 글 수정 뷰 반환
	@GetMapping("/BoardUpdate")
	public String update(Model model, BoardDTO boardDTO) throws Exception {
	    logger.info("updateView");
	    
//		게시글 번호 뷰 페이지로 전달
//	    게시글 번호(bdNum)를 바탕으로 게시글 정보를 조회하고, 그 결과를 boardDTO라는 이름으로 뷰에 전달
		model.addAttribute("boardDTO", boardService.boardSelect(boardDTO.getBdNum()));
		
//	    게시판 글 수정 페이지로 이동
	    return "./board/board_update";
	}
	
//	게시판 글 수정
	@PostMapping("/BoardUpdate")
	public String update(BoardDTO boardDTO) throws Exception {
	    logger.info("update");
	    
//	    boardDTO 객체를 boardService.boardUpdate(boardDTO) 메서드로 전달하여, 
//	    서비스 레이어에서 실제로 게시글 수정을 처리
		boardService.boardUpdate(boardDTO);
	    
//		게시글 수정 후 게시글 목록 페이지로 리다이렉트
		return "redirect:/BoardList";
	}
	
//	게시판 글 삭제 뷰 반환
	@GetMapping("/BoardDelete")
	public String delete(Model model, BoardDTO boardDTO) throws Exception {
	    logger.info("deleteView");
	    
//		게시글 번호 뷰 페이지로 전달
//	    게시글 번호(bdNum)를 바탕으로 게시글 정보를 조회하고, 그 결과를 boardDTO라는 이름으로 뷰에 전달
		model.addAttribute("boardDTO", boardService.boardSelect(boardDTO.getBdNum()));
		
//	    게시판 글 삭제 페이지로 이동
	    return "./board/board_delete";
	}
	
//	게시판 글 삭제
	@PostMapping("/BoardDelete")
	public String delete(BoardDTO boardDTO) throws Exception {
	    logger.info("delete");
		
//	    게시글 번호를 가져오고, boardService.boardDelete(boardDTO.getBdNum())를 호출하여 
//	    서비스 레이어에서 해당 게시글을 삭제
	    boardService.boardDelete(boardDTO.getBdNum());
	    
//		게시글 삭제 후 게시판 목록 페이지로 리다이렉트
		return "redirect:/BoardList";
	}
}
