package jin.spring.board.member.service;

import org.springframework.stereotype.Service;

import jin.spring.board.member.dao.MemberDAO;
import jin.spring.board.member.dto.MemberDTO;
import lombok.RequiredArgsConstructor;

// 서비스 계층의 스프링 빈이라는 것 명시 
@Service
// final 필드를 초기화해주는 생성자를 자동으로 생성
@RequiredArgsConstructor
public class MemberServiceImp implements MemberService{
//	의존성 주입받을 DAO 계층 객체, 불변성 보장 
	private final MemberDAO memberDAO;
	
//	회원가입 
//	컨트롤러에서 넘겨준 MemberDTO 객체에 회원 정보가 담겨 있음
//	그 객체를 DAO 계층에 넘겨 DB에 회원 정보를 INSERT함
	@Override
	public void memberRegister(MemberDTO memberDTO) throws Exception{
//		회원 정보를 담고 있는 MemberDTO 객체를 DAO에 넘겨 등록 처리
		memberDAO.register(memberDTO);
	}

//  로그인 
//	memberDTO 객체에 사용자가 입력한 ID/PW가 들어 있음
	@Override
	public MemberDTO memberLogin(MemberDTO memberDTO) throws Exception {
//		DAO의 login() 메서드로 넘겨서 DB 조회
		return memberDAO.login(memberDTO);
	}

//	회원정보 수정 
	@Override
	public void memberUpdate(MemberDTO memberDTO) throws Exception {
//		수정할 회원 정보를 담은 DTO를 DAO로 넘겨 DB에서 업데이트
		memberDAO.update(memberDTO);
	}

}
