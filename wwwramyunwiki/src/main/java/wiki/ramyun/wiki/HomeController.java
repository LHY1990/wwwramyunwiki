package wiki.ramyun.wiki;

import java.sql.Connection;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
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
	
	@GetMapping("join")
	public String join(ModelAndView mav) {
		System.out.println("갯으로 받음");
		
		
		
		
		return "join";
		
	}
	
	@PostMapping("join")
	public ModelAndView joinFirst(MemberVO vo,ModelAndView mav, String memberEmailCode) {
		
		
		
		
		if(memberEmailCode.equals("")) {		
			//인증코드가 없는 상태로 넘어온 경우

			memberService.sendMailCode(vo);

			System.out.println(vo.getMemberId()+" "+vo.getMemberEmail()+" "+vo.getMemberPassword()+" "+memberEmailCode);
		}
		if(memberEmailCode!=null) {
			//인증코드가 있는 상태로 넘어온경우
			System.out.println("");
		}
		
		mav.setViewName("join");
		mav.addObject("memberId",vo.getMemberId());
		mav.addObject("memberPassword",vo.getMemberPassword());
		mav.addObject("memberEmail",vo.getMemberEmail());
		
		if(memberEmailCode.equals("123123")) {
			
			mav.setViewName("home");
			//가입 환영 페이지 만들고 데이터 베이스에 등록하자
		}
		
		return mav;
	}
	
	
	
}
