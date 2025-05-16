package jin.spring.board.member.dao;

import jin.spring.board.member.dto.MemberDTO;

public interface MemberDAO {
//  회원가입
	public void register(MemberDTO memberDTO) throws Exception;

//	로그인
	public MemberDTO login(MemberDTO memberDTO) throws Exception;
	
//	회원정보 수정
	public void update(MemberDTO memberDTO) throws Exception;
	
//	회원탈퇴 
	public void delete(MemberDTO memberDTO) throws Exception;
	
//	아이디 중복체크 
	public int idChk(MemberDTO memberDTO) throws Exception;

}
