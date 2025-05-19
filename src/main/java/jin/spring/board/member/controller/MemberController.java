package jin.spring.board.member.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import jin.spring.board.controller.BoardController;
import jin.spring.board.member.dto.MemberDTO;
import jin.spring.board.member.service.MemberService;
import jin.spring.board.member.service.MemberServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	private final MemberService memberService;

	private final BCryptPasswordEncoder pwdEncoder;

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

		int result = memberService.memberIdChk(memberDTO);

		try {
//			1이면 아이디가 중복된 것이기에 다시 회원가입 폼 뷰 보냄 
			if (result == 1) {
				return "./member/member_register";
			} else if (result == 0) {
//				결과가 0이면 중복된 아이디가 없기에 회원가입 실행
				String inputPass = memberDTO.getMbPw();
				String pwd = pwdEncoder.encode(inputPass);
				memberDTO.setMbPw(pwd);

				memberService.memberRegister(memberDTO);
			}
		} catch (Exception e) {
			throw new RuntimeException();
		}

//		게시판 목록으로 이동
		return "redirect:/BoardList";
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
//	회원가입 비밀번호를 암호화함->입력 비밀번호랑 조회한 비밀번호가 맞아야 함
	@PostMapping("/MemberLogin")
	public String login(MemberDTO memberDTO, HttpServletRequest req, RedirectAttributes rttr) throws Exception {
		logger.info("login");

//		HttpServletRequest로부터 세션 객체를 얻음 
		HttpSession session = req.getSession();

//		로그인 폼에서 전송한 ID/PW 정보가 memberDTO에 자동 바인딩됨 -> 이 값을 서비스로 넘김 
		MemberDTO login = memberService.memberLogin(memberDTO);

		if (login != null) {
			String dbPw = login.getMbPw();
			String inputPw = memberDTO.getMbPw();

			boolean pwdMatch = false;
			
//			DB 비밀번호가 bcrypt 형식인지 확인
//			bcrypt 암호화 문자열 특징($2a$로 시작 
			if (dbPw != null && dbPw.startsWith("$2a$")) {
//				평문 입력(inputPw)과 암호화된 비밀번호(dbPw) 비교
				pwdMatch = pwdEncoder.matches(inputPw, dbPw);
			} else {
//				암호화 안 된 상태면 평문 비교 (보안상 권장X, 임시 조치)
		        pwdMatch = dbPw.equals(inputPw);
			}
			
//			아이디가 존재하고 비밀번호가 일치할 경우
			if (login != null && pwdMatch == true) {
//				로그인 성공이라면 로그인된 MemberDTO 객체 전체를 세션에 저장
				session.setAttribute("member", login);
			} else {
//				로그인이 안된 상태라면 세션에 null 저장 
				session.setAttribute("member", null);
//				실패 메시지 전달->jsp에서 사용 가능 
				rttr.addFlashAttribute("msg", false);
			}
		}
//		로그인 성공/실패 후 게시판 목록 페이지로 이동 
		return "redirect:/BoardList";
	}

//	logout
//	HttpSession 객체는 현재 사용자의 세션을 나타냄 
//	이 메서드에서 이 세션을 끊는 작업을 함 
	@GetMapping("/MemberLogout")
	public String logout(HttpSession session) throws Exception {
//		세션에 저장되어 있는 모든 정보 사라짐 -> 로그아웃 상태가 됨 
		session.invalidate();

//		로그아웃 후 게시판 목록으로 이동
		return "redirect:/BoardList";
	}

//	마이페이지 뷰 GET
//	로그인하면 회원 정보가 session에 있어서 쓸 수 있음 
	@GetMapping("/MemberMypage")
	public String mypage() {
		logger.info("마이페이지 폼 뷰");

//		마이페이지 뷰 반환 
		return "./member/member_mypage";
	}

//	회원정보 수정 뷰 GET	
//	로그인하면 회원 정보가 session에 있어서 쓸 수 있음 
	@GetMapping("/MemberUpdate")
	public String update() {
		logger.info("회원정보 수정 폼 뷰");

//		회원정보 수정 뷰 반환 
		return "./member/member_update";
	}

//	회원정보 수정 POST
//	pwChk()에서 비밀번호 비교하기 때문에 서비스만 실행 
	@PostMapping("/MemberUpdate")
	public String update(MemberDTO memberDTO, HttpSession session) throws Exception {
//		파라미터를 서비스로 보내줌 
		memberService.memberUpdate(memberDTO);

//		회원정보 수정 후에 세션 끊음 
		session.invalidate();

//		로그인 페이지로 리다이렉트함  
		return "redirect:/MemberLogin";
	}

//	회원탈퇴 GET
	@GetMapping("/MemberDelete")
	public String delete() {
		logger.info("회원탈퇴 폼 뷰");

//		회원탈퇴 뷰 반환 
		return "./member/member_delete";
	}

//	회원탈퇴 POST
	@PostMapping("/MemberDelete")
	public String delete(MemberDTO memberDTO, HttpSession session, RedirectAttributes rttr) throws Exception {
		logger.info("회원탈퇴");

		/*
		 * pwChk()에서 비밀번호 비교하기 때문에 서비스만 실행 // 세션에서 로그인된 회원 정보 가져옴 MemberDTO
		 * sessionMember = (MemberDTO) session.getAttribute("member");
		 * 
		 * // 세션에서 가져온 실제 비밀번호 String sessionPw = sessionMember.getMbPw();
		 * 
		 * // 탈퇴 폼에서 입력한 비밀번호 String memberDTOPw = memberDTO.getMbPw();
		 * 
		 * // 세션에 있는 비밀번호와 DTO에 담기는 비밀번호 일치해야 회원 탈퇴 // 일치하지 않으면 msg에 false 값 담아서 뷰로 전달
		 * // 비밀번호 불일치 시 if (!sessionPw.equals(memberDTOPw)) {
		 * rttr.addFlashAttribute("pwError", false); // 에러 표시용 플래시 속성 return
		 * "redirect:/MemberDelete"; // 탈퇴 폼으로 이동 }
		 */

//		비밀번호가 일치하면 탈퇴 처리 진행 
		memberService.memberDelete(memberDTO);

//		세션 종료 
		session.invalidate();

//		게시판 목록으로 이동
		return "redirect:/BoardList";
	}

//	아이디 중복 체크
//	AJAX 요청으로 이 메서드가 호출되면, 서버가 즉시 중복 여부 결과(1 or 0)를 반환
	@ResponseBody
	@PostMapping("/MemberIdChk")
	public int idChk(MemberDTO memberDTO) throws Exception {
//		전달받은 MemberDTO 객체에서 아이디 값을 추출
		int result = memberService.memberIdChk(memberDTO);
//		결과(중복 여부)를 int로 반환
		return result;
	}

//	패스워드 체크
//	회원 수정, 탈퇴에서 입력한 비밀번호가 현재와 일치하는지 체크하는 용도로 사용 
	@ResponseBody
	@PostMapping("/MemberPwdChk")
	public boolean pwChk(MemberDTO memberDTO) throws Exception {
//		DB에서 해당 ID를 가진 사용자 정보를 가져옴
		MemberDTO login = memberService.memberLogin(memberDTO);

//		비밀번호 일치 여부 확인
//		memberDTO.getMbPw(): 사용자가 로그인 폼에 입력한 평문 비밀번호
//		login.getMbPw(): DB에 저장되어 있는 암호화된 비밀번호
//		matches(): 평문과 암호화된 문자열을 비교해서 일치 여부를 반환함
		boolean pwdChk = pwdEncoder.matches(memberDTO.getMbPw(), login.getMbPw());

//		일치 여부 반환
		return pwdChk;
	}

}
