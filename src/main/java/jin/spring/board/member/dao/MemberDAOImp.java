package jin.spring.board.member.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import jin.spring.board.member.dto.MemberDTO;
import lombok.RequiredArgsConstructor;

/*	
 * 	@Repository: Spring의 컴포넌트 스캔에 의해 이 클래스가 DAO 계층의 Bean으로 등록됨
 * 	->"memberDAOImp" 이름을 가진 빈으로 등록
 */
@Repository 
// final 필드를 파라미터로 받는 생성자를 자동으로 생성
@RequiredArgsConstructor
public class MemberDAOImp implements MemberDAO{
//	생성자를 통해 주입 (불변성 유지)
	private final SqlSessionTemplate sqlSessionTemplate;

//	회원가입 
	@Override
	public void register(MemberDTO memberDTO) throws Exception {
//		namespace: jin.spring.member, id: register
//		memberDTO 안에 담긴 데이터를 #{mbId}, #{mbPw}, #{mbName}에 바인딩해 INSERT 실행
		sqlSessionTemplate.insert("jin.spring.member.register", memberDTO);
	}

	
//	login
	@Override
	public MemberDTO login(MemberDTO memberDTO) throws Exception {
//		DB에서 조회한 결과를 MemberDTO 객체로 매핑한 값 리턴
		return sqlSessionTemplate.selectOne("jin.spring.member.login", memberDTO);
	}

}
