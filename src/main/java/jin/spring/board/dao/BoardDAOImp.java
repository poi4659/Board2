//MyBatis SQL 쿼리 실행 코드를 작성하고, 실제 데이터베이스와 상호작용하는 구현체
package jin.spring.board.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import jin.spring.board.dto.BoardDTO;
import lombok.RequiredArgsConstructor;

/*	
 * 	@Repository: Spring의 컴포넌트 스캔에 의해 이 클래스가 DAO 계층의 Bean으로 등록됨
 * 	->"boardDAOImp" 이름을 가진 빈으로 등록
 */
@Repository
//final이 붙은 필드를 매개변수로 받는 생성자가 자동 생성
@RequiredArgsConstructor
public class BoardDAOImp implements BoardDAO {
//	생성자를 통해 주입 (불변성 유지)
	private final SqlSessionTemplate sqlSessionTemplate;
	
//	게시판 글 작성
	@Override
	public void insert(BoardDTO boardDTO) throws Exception {
		/*
		 * XML 매퍼 파일에서 namespace="jin.spring.board"와 id="insert"에 해당하는 
		 * SQL 쿼리를 실행 
		 */
		sqlSessionTemplate.insert("jin.spring.board.insert", boardDTO);
	}

//	게시판 목록 조회
	@Override
	public List<BoardDTO> selectAll() throws Exception {
		/*
		 * XML 매퍼 파일에서 namespace="jin.spring.board"와 id="selectAll"에 해당하는 
		 * SQL 쿼리를 실행 
		 * selectList() → 여러 개의 결과(List<BoardDTO>)를 조회할 때 사용
		 */
		return sqlSessionTemplate.selectList("jin.spring.board.selectAll");
	}

//	게시판 글 상세 조회
	@Override
	public BoardDTO select(int bdNum) throws Exception {
		/*
		 * XML 매퍼 파일에서 namespace="jin.spring.board"와 id="select"에 해당하는 
		 * SQL 쿼리를 실행 
		 * bdNum 값을 XML 매퍼의 #{bdNum}에 전달
		 * selectOne() → 단일 결과(BoardDTO)를 조회할 때 사용
		 */
		return sqlSessionTemplate.selectOne("jin.spring.board.select", bdNum);
	}

	
//	게시판 글 수정
	@Override
	public void update(BoardDTO boardDTO) throws Exception {
		/*
		 * XML 매퍼 파일에서 namespace="jin.spring.board"와 id="update"에 해당하는 
		 * SQL 쿼리를 실행 
		 *  BoardDTO 객체를 SQL 쿼리의 파라미터로 전달 
		 *  -> SQL 쿼리에서 #{bdNum}, #{bdTitle}, #{bdContent}와 같은 자리로 바인딩됨
		 */
		sqlSessionTemplate.update("jin.spring.board.update", boardDTO);
	}

//	게시판 글 삭제
	@Override
	public void delete(int bdNum) throws Exception {
		/*
		 * XML 매퍼 파일에서 namespace="jin.spring.board"와 id="delete"에 해당하는 
		 * SQL 쿼리를 실행 
		 * bdNum 값을 XML 매퍼의 #{bdNum}에 전달
		 */
		sqlSessionTemplate.delete("jin.spring.board.delete", bdNum);
	}

}
