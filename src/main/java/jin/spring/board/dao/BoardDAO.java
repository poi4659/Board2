package jin.spring.board.dao;

import java.util.List;

import jin.spring.board.dto.BoardDTO;
import jin.spring.board.dto.Criteria;

public interface BoardDAO {
//	게시판 글 작성
	public void insert(BoardDTO boardDTO) throws Exception;
	
//	게시판 목록 조회
//	페이징 처리 추가
	public List<BoardDTO> list(Criteria criteria) throws Exception;
	
//	게시글 총 갯수
	public int listCount() throws Exception;
	
//	게시판 글 상세 조회
	public BoardDTO select(int bdNum) throws Exception;
	
//	게시판 글 수정
	public void update(BoardDTO boardDTO) throws Exception;
	
//	게시판 글 삭제
	public void delete(int bdNum) throws Exception;
	
}