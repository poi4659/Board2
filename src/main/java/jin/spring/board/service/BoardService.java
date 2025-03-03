package jin.spring.board.service;

import java.util.List;

import jin.spring.board.dto.BoardDTO;

public interface BoardService {
//	게시판 글 작성
	public void boardInsert(BoardDTO boardDTO) throws Exception;
	
//	게시판 글 목록 조회
	public List<BoardDTO> boardSelectAll() throws Exception;
	
//	게시판 글 상세 조회
//	bdNum을 인자로 받아 해당 bdNum에 맞는 게시판 글을 조회하여 BoardDTO 객체로 반환
	public BoardDTO boardSelect(int bdNum) throws Exception;
	
//	게시판 글 수정
	public void boardUpdate(BoardDTO boardDTO) throws Exception;
	
//	게시판 글 수정
//	bdNum을 인자로 받아 해당 bdNum에 맞는 게시판 글을 조회하여 BoardDTO 객체로 반환
	public void boardDelete(int bdNum) throws Exception;
}