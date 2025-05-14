package jin.spring.board.member.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import jin.spring.board.member.dto.MemberDTO;
import jin.spring.board.member.service.MemberService;
import jin.spring.board.member.service.MemberServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberServiceImp memberServiceImp;
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	private final MemberService memberService;
	
//	회원가입 뷰 GET
	@GetMapping("/MemberRegister")
	public String register() {
		logger.info("회원가입 폼 뷰");
		
//		회원가입 뷰 반환 
		return "./member/member_register";
	}
	
//	회원가입 POST
	@PostMapping("/MemberRegister")
	public String register(MemberDTO memberDTO) throws Exception {
		logger.info("회원가입");
		
		memberService.memberRegister(memberDTO);
		
//		절대 경로로 지정하여 중복 경로가 발생하지 않도록 함->수정 필요
		return "redirect:/MemberRegister";
	}
	
//	login 뷰 GET
	@GetMapping("/MemberLogin")
	public String login() {
		logger.info("로그인 폼 뷰");
		
//		회원가입 뷰 반환 
		return "./member/member_login";
	}
	
//	login POST
//	MemberDTO: 로그인 폼에서 전송된 mbId, mbPw 값을 Spring이 자동으로 DTO에 바인딩
//	HttpServletRequest req: 세션을 얻기 위해 사용
//	RedirectAttributes rttr: 리다이렉트 시 한 번만 보여줄 데이터를 전달할 때 사용
	@PostMapping("/MemberLogin")
	public String login(MemberDTO memberDTO, HttpServletRequest req, RedirectAttributes rttr) throws Exception {
		logger.info("login");
		
//		HttpServletRequest로부터 세션 객체를 얻음 
		HttpSession session = req.getSession();
		
//		로그인 폼에서 전송한 ID/PW 정보가 memberDTO에 자동 바인딩됨 -> 이 값을 서비스로 넘김 
		MemberDTO login = memberService.memberLogin(memberDTO);
		
//		로그인이 안된 상태라면 
		if (login == null) {
//			세션에 null 저장 
			session.setAttribute("member", null);
//			실패 메시지 전달->jsp에서 사용 가능 
			rttr.addFlashAttribute("msg", false);
		} else {
//			로그인 성공이라면 로그인된 MemberDTO 객체 전체를 세션에 저장
			session.setAttribute("member", login);
		}
		
//		로그인 성공/실패 후 게시판 목록 페이지로 이동 
		return "redirect:/BoardList";
	}
	
	
	
//	logout
//	HttpSession 객체는 현재 사용자의 세션을 나타냄 
//	이 메서드에서 이 세션을 끊는 작업을 함 
	@GetMapping("/MemberLogout")
	public String logout(HttpSession session) throws Exception{
//		세션에 저장되어 있는 모든 정보 사라짐 -> 로그아웃 상태가 됨 
		session.invalidate();
		
		
//		로그아웃 후 게시판 목록으로 이동
		return "redirect:/BoardList";
	}
	
}
