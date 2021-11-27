package wiki.ramyun.www;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import wiki.ramyun.www.ingredient.IngredientVO;
import wiki.ramyun.www.ingredient.service.IngredientService;
import wiki.ramyun.www.manufactory.ManufactoryVO;
import wiki.ramyun.www.manufactory.service.ManufactoryService;
import wiki.ramyun.www.member.MemberVO;
import wiki.ramyun.www.member.service.MemberService;
import wiki.ramyun.www.metadata.service.MetadataService;
import wiki.ramyun.www.ramyun.RamyunVO;
import wiki.ramyun.www.ramyun.service.RamyunService;
import wiki.ramyun.www.ramyunhistory.RamyunHistoryVO;
import wiki.ramyun.www.ramyunhistory.service.RamyunHistoryService;
import wiki.ramyun.www.search.SearchVO;
import wiki.ramyun.www.search.service.SearchService;
import wiki.ramyun.www.wikistringresolver.WikiStringResolver;


//홈화면 접근 및 회원 정보 접근
@Controller
public class HomeController {
	
	String mailCode="";
	List<RamyunVO> ramyunRecentUpdatedList;
	
	@Autowired
	@Qualifier("memberService")
	private MemberService memberService;
	
	@Autowired
	@Qualifier("ramyunService")
	private RamyunService ramyunService;
	
	@Autowired
	@Qualifier("ramyunHistoryService")
	private RamyunHistoryService ramyunHistoryService;
	
	@Autowired
	@Qualifier("ingredientService")
	private IngredientService ingredientService;
	
	@Autowired
	@Qualifier("manufactoryService")
	private ManufactoryService manufactoryService;
	
	@Autowired
	@Qualifier("searchService")
	private SearchService searchService;
	
	@Autowired
	@Qualifier("metadataService")
	private MetadataService metadataService;
	
	@Autowired
	@Qualifier("bcryptPasswordEncoder")
	private BCryptPasswordEncoder passwordEncoder;
	
	
	//ramyun.wiki 로 접근해도 홈화면으로 보낸다.
	@RequestMapping("/")
	public String ramyunwiki() {
		return "redirect:/home";
	}
	
	
	//에러페이지 접근
	@GetMapping("/errorpage")
	public String error404() {
		return "errorpage";
	}
	
	
	@GetMapping("/home")
	public String home(Locale locale, Model model) {
		//우측에 뿌리는 10개 등록
		ramyunRecentUpdatedList=ramyunService.getRecentsUpdateListFromDB();
		//랜덤 라면 가져오기. 이미지로 넣는다. 
		model.addAttribute("randomRamyunImage",ramyunService.getTodaysRamyunImage());
		
		//몇개의 라면 몇명의 회원인지 찾는것
		int ramyunCount=ramyunService.getRamyunCount();
		int memberCount=memberService.getMemberCount();
		
		model.addAttribute("ramyunCount", ramyunCount);
		model.addAttribute("memberCount", memberCount);
		model.addAttribute("ramyunList", ramyunRecentUpdatedList);
		
		return "home";
	}
	
	
	@PostMapping("/home")
	public String home(MemberVO vo, HttpSession session) {
		
		//멤버인지 체크하고 멤버면 정보를 보낸다.
		boolean loginOK=memberService.checkMember(vo);
		if(loginOK) {
			vo=memberService.getMemberById(vo.getMemberId());
			
			session.setAttribute("memberNumber", vo.getMemberNumber());
			session.setAttribute("memberId", vo.getMemberId());
			session.setAttribute("memberEmail", vo.getMemberEmail());
			session.setAttribute("memberJoinDate", vo.getJoinDate());
			session.setAttribute("memberNickname", vo.getNickname());
			session.setAttribute("memberLevel", vo.getLevel());
			session.setAttribute("isMember", "true");
			return "redirect:home";
		}else {
			session.setAttribute("isMember", "false");
			return "login";
		}
		
	
	}
	
	
	//로그아웃
	@GetMapping("/logout.do")
	public String logout(HttpSession session) {
		
		session.invalidate();
		return "redirect:home";
	}
	

	//이용약관
	@PostMapping("/rules")
	public String rulesPost() {
		return "rules";
	}
	
	
	@GetMapping("/rules")
	public String rulesGet() {
		return "rules";
	}

	
	//유저가 설정창을 눌렀을때
	@GetMapping("/userinfo")
	public ModelAndView getUserInfo(ModelAndView mav, HttpSession session) {
		//이건 10개만 가져와서 오른쪽에 뿌리는것. 
		mav.addObject("ramyunList", ramyunService.getRecentsUpdateListFromDB());
		mav.addObject("randomRamyunImage",ramyunService.getTodaysRamyunImage());
		
		mav.setViewName("userinfo");
		return mav;
	}
	
	
	//유저정보 변경
	@GetMapping("/changeuserinfo.do")
	public ModelAndView changeuserinfo(ModelAndView mav) {
		//이건 10개만 가져와서 오른쪽에 뿌리는것. 
		mav.addObject("ramyunList", ramyunService.getRecentsUpdateListFromDB());
		mav.addObject("randomRamyunImage",ramyunService.getTodaysRamyunImage());
		
		mav.setViewName("changeuserinfo");
		return mav;
	}
	
	
	@PostMapping("/changinguser.do")
	public ModelAndView changinguser(ModelAndView mav, MemberVO vo,HttpSession session) {
		//이건 10개만 가져와서 오른쪽에 뿌리는것. 
		mav.addObject("ramyunList", ramyunService.getRecentsUpdateListFromDB());
		mav.addObject("randomRamyunImage",ramyunService.getTodaysRamyunImage());
		
		//변경할 닉네임, 이메일과 멤버 넘버를 받아서 값을 변경한다.
		String memberNumber=session.getAttribute("memberNumber").toString();
		memberService.changeNickname(vo.getNickname(), memberNumber);
		memberService.changeMemberEmail(vo.getMemberEmail(), memberNumber);
		
		session.setAttribute("memberNickname", vo.getNickname());
		mav.setViewName("userinfo");
		return mav;
	}
	
	
	//패스워드 변경시 이쪽으로 넘어온다.
	@GetMapping("changeuserpassword.do")
	public ModelAndView getChangeingPassword(ModelAndView mav, MemberVO vo, HttpSession session ) {
		//이건 10개만 가져와서 오른쪽에 뿌리는것. 
		mav.addObject("ramyunList", ramyunService.getRecentsUpdateListFromDB());
		mav.addObject("randomRamyunImage",ramyunService.getTodaysRamyunImage());
		
		mav.setViewName("changeuserpassword");
		return mav;
	}
	
	
	//비밀번호 변경 화면
	@PostMapping("changingPassword.do")
	public ModelAndView postChangeingPassword(ModelAndView mav, MemberVO vo, HttpSession session, String oldPassword, String newPassword ) {
		//이건 10개만 가져와서 오른쪽에 뿌리는것. 
		mav.addObject("ramyunList", ramyunService.getRecentsUpdateListFromDB());
		mav.addObject("randomRamyunImage",ramyunService.getTodaysRamyunImage());
		
		String userMemberId=(String) session.getAttribute("memberId");
		//패스워드를 암호화해서 보낸다. 서비스에서 하도록 옮길것
		newPassword=passwordEncoder.encode(newPassword);
		String result=memberService.changePassword(userMemberId,oldPassword, newPassword);
		System.out.println("변경결과"+result);
		
		//아래 문자열을 바탕으로 변경 결과를 알린다.
		mav.addObject("passwordChangingResult", result);
		mav.setViewName("userinfo");
		return mav;
	}
	
	
	//회원탈퇴 요청
	@GetMapping("withdraw.do")
	public ModelAndView getWithdraw(ModelAndView mav, MemberVO vo, HttpSession session) {
		//이건 10개만 가져와서 오른쪽에 뿌리는것. 
		mav.addObject("ramyunList", ramyunService.getRecentsUpdateListFromDB());
		mav.addObject("randomRamyunImage",ramyunService.getTodaysRamyunImage());
		
		mav.setViewName("stopmembership");
		return mav;
	}
	
	
	//회원탈퇴 처리 후 감사창으로 이동
	@PostMapping("withdraw.do")
	public ModelAndView postWithdraw(ModelAndView mav, HttpSession session, String withdrawPassword) {
		
		//세션으로 회원 아이디를 받는다.
		String memberId=(String) session.getAttribute("memberId");
		//결과를 받는다. 쓰지는 않는다.
		String result = memberService.withdrawMember(memberId, withdrawPassword);
		if(result=="withdrawMemberSuccess") {
			session.invalidate();
			mav.setViewName("thankyouforvisitus");
		}else if(result=="withdrawMemberFaild") {
			//세션으로 처리한다.
			session.setAttribute("isMemberWithdrawDone", "withdrawMemberFaild");
			mav.setViewName("stopmembership");
		}
		
		return mav;
	}
	
	
	//계정정보 찾기 처음 접근
	@GetMapping("findid.do")
	public ModelAndView getFindId(ModelAndView mav) {
		//이건 10개만 가져와서 오른쪽에 뿌리는것. 
		mav.addObject("ramyunList", ramyunService.getRecentsUpdateListFromDB());
		mav.addObject("randomRamyunImage",ramyunService.getTodaysRamyunImage());
		
		
		
		mav.setViewName("findid");
		return mav;
		
	}
	
	
	//계정정보 찾기, 메일로 정보를 보내어 회원으로 하여금 비밀번호를 바꾸게한다.
	@PostMapping("findid.do")
	public ModelAndView postFindId(ModelAndView mav, String findbyemail) {
		//이건 10개만 가져와서 오른쪽에 뿌리는것. 
		mav.addObject("ramyunList", ramyunService.getRecentsUpdateListFromDB());
		mav.addObject("randomRamyunImage",ramyunService.getTodaysRamyunImage());
		
		
		String result = memberService.isMemberEmail(findbyemail);
		
		mav.addObject("findIdResult", result);
		mav.setViewName("findid");
		return mav;
		
	}
	
	
	@GetMapping("/login")
	public ModelAndView login(ModelAndView mav) {
		//이건 10개만 가져와서 오른쪽에 뿌리는것. 스프링으로 빼야할듯
		mav.addObject("ramyunList", ramyunService.getRecentsUpdateListFromDB());
		mav.addObject("randomRamyunImage",ramyunService.getTodaysRamyunImage());
		
		mav.setViewName("login");
		return mav;
	}
	
	
	@PostMapping("/login")
	public ModelAndView login(ModelAndView mav, MemberVO vo) {
		//이건 10개만 가져와서 오른쪽에 뿌리는것. 스프링으로 빼야할듯
		mav.addObject("ramyunList", ramyunService.getRecentsUpdateListFromDB());
		mav.addObject("randomRamyunImage",ramyunService.getTodaysRamyunImage());
		//여기까지가 우측탭 정보
		memberService.checkMember(vo);
		
		return mav;
	}
	
	
	
	@GetMapping("/join")
	public String join(ModelAndView mav) {
		//처음창은 이쪽으로 보내서 값을 넣게한다.
		System.out.println("조인 겟에 접근중");
		mailCode = String.valueOf((int)(Math.random()*1000000));
		
		return "join";
	}
	
	
	@PostMapping("/join")
	public ModelAndView joinFirst(MemberVO vo,ModelAndView mav,@RequestParam(defaultValue = "") String memberEmailCode) {
		
		if(memberEmailCode.equals("")) {		
			//인증코드가 없는 상태로 넘어온 경우
			System.out.println("인증번호없이 방문");
			
			mav.setViewName("join");
			
			//아이디가 고유한가
			if( memberService.isUnique(vo)) {
				mav.addObject("memberId",vo.getMemberId());
				mav.addObject("memberPassword",vo.getMemberPassword());
				mav.addObject("memberEmail",vo.getMemberEmail());
				memberService.sendMailCode(vo,mailCode);
				mav.addObject("memberAlert", "codeSended");
				return mav;
			}
			else {
				System.out.println("아이디 중복됌");
				mav.addObject("memberAlert", "error");
				return mav;
			}
			
		}
		if(memberEmailCode!=null) {
			//인증코드가 있는 상태로 넘어온경우
			System.out.println("인증번호 가지고 방문");
			
			if(memberEmailCode.equals(mailCode)) {
				//비밀번호를 암호화한다.
				String encodedPassword=passwordEncoder.encode(vo.getMemberPassword());
				vo.setMemberPassword(encodedPassword);
				
				memberService.inserMemberToDB(vo);
				mav.addObject("newMember", vo.getMemberId());
				mav.setViewName("welcome");
				//가입 환영 페이지 만들고 데이터 베이스에 등록하자
			}else{
				mav.addObject("memberId",vo.getMemberId());
				mav.addObject("memberPassword",vo.getMemberPassword());
				mav.addObject("memberEmail",vo.getMemberEmail());
				mav.setViewName("join");
				mav.addObject("memberAlert", "codeIsNotSame");
				return mav;
			}
		}
		
		return mav;
	}
	

	//여기는 home>작성방법
	@GetMapping("/howto.do")
	public ModelAndView howToMakeText(ModelAndView mav) {
		//화면에 10개를 뿌린다.
		mav.addObject("ramyunList", ramyunService.getRecentsUpdateListFromDB());
		mav.addObject("randomRamyunImage",ramyunService.getTodaysRamyunImage());
		
		mav.setViewName("howto");
		return mav;
	}
	
	
	
}
