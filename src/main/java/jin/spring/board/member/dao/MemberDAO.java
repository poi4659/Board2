package jin.spring.board.member.dao;

import jin.spring.board.member.dto.MemberDTO;

public interface MemberDAO {
//  회원가입
	public void register(MemberDTO memberDTO) throws Exception;

//	로그인
	public MemberDTO login(MemberDTO memberDTO) throws Exception;
	
}
