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


//	회원정보 수정
//	서비스에서 보낸 파라미터들을 update(MemberDTO memberDTO)에 담음 
	@Override
	public void update(MemberDTO memberDTO) throws Exception {
//		memberDTO에 있는 회원 정보를 기준으로 DB에 있는 데이터를 수정
		sqlSessionTemplate.update("jin.spring.member.update", memberDTO);
	}

//	회원탈퇴 
//	서비스에서 전달한 MemberDTO를 기준으로 DB에서 해당 회원 데이터를 삭제
	@Override
	public void delete(MemberDTO memberDTO) throws Exception {
		sqlSessionTemplate.delete("jin.spring.member.delete", memberDTO);
	}

}
