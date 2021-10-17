package wiki.ramyun.wiki;

import java.sql.Connection;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import wiki.ramyun.www.member.MemberVO;
import wiki.ramyun.www.member.memberService.MemberService;
import wiki.ramyun.www.member.serviceImplement.MemberDAO;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/")
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private MemberService memberService;
	
	//@RequestMapping(value = "/", method = RequestMethod.GET)
	@GetMapping("home")
	public String home(Locale locale, Model model) {
		//logger.info("Welcome home! The client locale is {}.", locale);

		System.out.println("홈에접근중");
		
		return "home";
	}
	@PostMapping("home")
	public String home(MemberVO vo, HttpSession session) {
		System.out.println(vo.getMemberId());
		System.out.println(vo.getMemberPassword());
		
		
		boolean loginOK=memberService.checkMember(vo);
		if(loginOK) {
			session.setAttribute("memberId", vo.getMemberId());
			session.setAttribute("isMember", "true");
			return "redirect:home";
			
		}
		
		return "login";
	
	}
	@GetMapping("logout.do")
	public String logout(HttpSession session) {
		
		
		session.invalidate();
		
		return "redirect:home";
	}
	//이용약관
	@PostMapping("rules")
	public String rulesPost() {
		return "rules";
	}
	@GetMapping("rules")
	public String rulesGet() {
		return "rules";
	}
	@GetMapping("join")
	public String joinGet() {
		return "join";
	}
	@PostMapping("join")
	public String joinPost() {
		return "join";
	}
	
	
	
	@GetMapping("login")
	public String login() {
		System.out.println("로그인에 접근중");
		
		
		return "login";
	}
	@PostMapping("login")
	public ModelAndView login(ModelAndView loginMav, MemberVO vo) {
		memberService.checkMember(vo);
		
		
		return loginMav;
	}
	
	
	/*
	@PostMapping("member/join")
	public String join(ModelAndView model, HttpServletRequest request, MemberVO vo, String passwordCheck, String email) {
//		여기서 막힘 이거를 순서를 잘 정해야할듯
		//값이 넘어올때 변수이름이랑 같아야 값이 넘어온다. dto에 있으면 같은걸로 되는듯
		System.out.println("아이디"+vo.getMemberId());
		System.out.println("비밀번호"+vo.getMemberPassword());
		System.out.println("패스워드 체크"+passwordCheck);
		System.out.println("이메일"+vo.getMemberEmail());

		
		return "home";
		
	}
	*/
	
}
