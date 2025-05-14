package jin.spring.board.member.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberDTO {
//	아이디 
	private String mbId;
//	비밀번호 
	private String mbPw;
//	이름 
	private String mbName;
//	가입일자
	private Date mbDate;
}
