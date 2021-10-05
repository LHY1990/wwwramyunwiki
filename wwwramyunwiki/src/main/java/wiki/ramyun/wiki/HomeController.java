package wiki.ramyun.wiki;

import java.sql.Connection;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	
	//@Inject
	//private DataSource dataSource;
	
	//@Autowired
	//private MemberDAO dao;
	
	@Autowired
	private MemberService memberService;
	
	

	//@RequestMapping(value = "/", method = RequestMethod.GET)
	@GetMapping("home")
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		memberService.inserMemberToDB();
		
		/*
		MemberVO vo = new MemberVO();
		
		vo.setMemberNumber(0);
		vo.setMemberId("2영훈");
		vo.setMemberPassword("2비밀번호1");
		vo.setJoinDate(LocalDateTime.now());
		vo.setNickname("2닉넴2");
		dao.insertMember(vo);
		*/
		
		/*
		MemberVO vo=new MemberVO();
		vo.setMemberNumber(2);
		dao.deleteMember(vo);
		*/
		
		
		return "home";
	}
	
	
}
