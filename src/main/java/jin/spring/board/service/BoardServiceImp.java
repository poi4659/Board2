//BoardDAO의 메서드를 호출하여 데이터를 처리하고, 추가적인 비즈니스 로직을 수행하는 구현체
package jin.spring.board.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import jin.spring.board.controller.BoardController;
import jin.spring.board.dao.BoardDAO;
import jin.spring.board.dto.BoardDTO;
import jin.spring.board.dto.Criteria;
import lombok.RequiredArgsConstructor;

//Spring이 BoardServiceImp를 "서비스 빈"으로 자동 등록
@Service
/*
 * final이 붙은 필드를 매개변수로 받는 생성자가 자동 생성됨 Spring이 BoardDAO 객체(BoardDAOImp 구현체)를 찾아서
 * 자동 주입 결과적으로 BoardServiceImp 내부에 BoardDAO(구현체 BoardDAOImp)가 주입됨
 */
@RequiredArgsConstructor
public class BoardServiceImp implements BoardService {
	private static Logger logger = LoggerFactory.getLogger(BoardServiceImp.class);

//	생성자 주입을 통한 DAO 의존성 주입 (불변성 유지)
//	 인터페이스를 사용-> 유연한 코드 작성 가능
	private final BoardDAO boardDAO;

//	게시판 글 작성
	@Override
	public void boardInsert(BoardDTO boardDTO) throws Exception {
		try {
//			DAO를 호출하여 데이터베이스에 저장
//			실행 중 예외가 발생하면 catch 블록에서 처리
			boardDAO.insert(boardDTO);
		} catch (DataIntegrityViolationException e) {
//			무결성 제약조건 위반 시 발생하는 예외
//			예외가 발생해도 프로그램이 강제 종료되지 않고, 예외 로그만 출력됨
			e.printStackTrace();

		}
	}

//	게시판 목록 조회
//	Criteria 객체를 매개변수로 받아서 페이징 정보를 전달
	@Override
	public List<BoardDTO> boardList(Criteria criteria) throws Exception {
//		DAO 계층의 list(criteria) 메서드를 호출하여 
//		페이징된 게시글 목록을 가져옴
//		결과를 List<BoardDTO> 형태로 반환
		return boardDAO.list(criteria);
	}
	
//	게시글 총 갯수
	@Override
	public int listCount() throws Exception {
//		DAO 계층의 listCount() 메서드를 호출하여 게시글 개수를 가져옴.
//		결과를 int 타입으로 반환
		return boardDAO.listCount();
	}

//	게시판 글 상세 조회
//	bdNum을 기준으로 게시판 글을 조회하고, 그 결과를 BoardDTO 객체로 반환
	@Override
	public BoardDTO boardSelect(int bdNum) throws Exception {
		return boardDAO.select(bdNum);
	}

//	게시판 글 수정
	@Override
	public void boardUpdate(BoardDTO boardDTO) throws Exception {
		/*
		 * 수정할 게시글의 번호와 내용을 담은 BoardDTO 객체를 받아, 
		 * boardDAO.update(boardDTO)를 호출하여 데이터베이스에서 해당 게시글을 수정
		 */
		boardDAO.update(boardDTO);
	}

//	게시판 글 삭제
	@Override
	public void boardDelete(int bdNum) throws Exception {
		/*
		 * 게시글 번호(bdNum)를 받아, boardDAO.delete(bdNum)를 호출하여 
		 * 해당 게시글을 삭제
		 */
		boardDAO.delete(bdNum);
	}


}
