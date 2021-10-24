package wiki.ramyun.wiki;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import wiki.ramyun.wiki.ramyun.RamyunVO;
import wiki.ramyun.www.member.MemberVO;
import wiki.ramyun.www.member.memberService.MemberService;
import wiki.ramyun.www.member.serviceImplement.MemberDAO;
import wiki.ramyun.www.ramyun.ramyunService.RamyunService;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/")
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	String mailCode="";
	List<RamyunVO> ramyunRecentUpdatedList;
	File file;
	
	
	@Autowired
	@Qualifier("memberService")
	private MemberService memberService;
	
	@Autowired
	@Qualifier("ramyunService")
	private RamyunService ramyunService;
	
	//@RequestMapping(value = "/", method = RequestMethod.GET)
	@GetMapping("home")
	public String home(Locale locale, Model model) {
		//logger.info("Welcome home! The client locale is {}.", locale);
		ramyunRecentUpdatedList=ramyunService.getRecentsUpdateListFromDB();

		//리스트 잘 넘어오나 테스트
		for(RamyunVO vo : ramyunRecentUpdatedList) {
			System.out.println(vo.getbrandNameKor()+" "+vo.getUpdatedDate());
		}//테스트끝
		
		model.addAttribute("ramyunList", ramyunRecentUpdatedList);
		
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
		
		
		mailCode = String.valueOf((int)(Math.random()*1000000));
		
		return "join";
		
	}
	
	@PostMapping("join")
	public ModelAndView joinFirst(MemberVO vo,ModelAndView mav,@RequestParam(defaultValue = "") String memberEmailCode) {
		
		
		
		
		if(memberEmailCode.equals("")) {		
			//인증코드가 없는 상태로 넘어온 경우

			
			System.out.println("인증번호없이 방문");
			System.out.println(vo.getMemberId()+" "+vo.getMemberEmail()+" "+vo.getMemberPassword()+" "+memberEmailCode);
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
	
	//검색 값 받아오기. 일단 신라면으로 테스트
	@GetMapping("findramyun.do")
	public ModelAndView getSearchKeyword(ModelAndView mav, String name) {
		//앞뒤 공백을 없앰
		name=name.trim();
		
		System.out.println(name);
		
		try {
			RamyunVO vo=ramyunService.getRamyunData(name);
			mav.addObject("ramyun", vo);
			mav.setViewName("ramyun");
			if(vo==null) {
				throw new Exception();
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("message", name);
			mav.setViewName("notfounding");
			//여기에 찾을수없다는 jsp를 만들자 에러구문도
			
		}
		
		return mav;
	}
	
	
	@PostMapping("findramyun.do")
	public ModelAndView postSearchKeyword(ModelAndView mav, String searchBoxInput) {
		//앞뒤 공백을 없앰
		searchBoxInput=searchBoxInput.trim();
		
		System.out.println(searchBoxInput);
		
		try {
			RamyunVO vo=ramyunService.getRamyunData(searchBoxInput);
			mav.addObject("ramyun", vo);
			mav.setViewName("ramyun");
			if(vo==null) {
				throw new Exception();
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("message", searchBoxInput);
			mav.setViewName("notfounding");
			//여기에 찾을수없다는 jsp를 만들자 에러구문도
			
		}
		
		return mav;
	}
	@GetMapping("editramyun.do")
	public ModelAndView getEditRamyun(ModelAndView mav,String name) {
		
		
		try {
			RamyunVO vo=ramyunService.getRamyunData(name);
			mav.addObject("ramyun", vo);
			mav.setViewName("editramyun");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return mav;
		
		
	}
	@PostMapping("edit.do")
	public ModelAndView afterRamyunEdited(ModelAndView mav, RamyunVO vo,@RequestParam("uploadedimage") MultipartFile uploadedimage, HttpServletRequest request) {
		//실제 파일위치
		
		String originalFileName= uploadedimage.getOriginalFilename();                          //업로드된 파일이미지의 이름
		String originalFiletype= originalFileName.substring(originalFileName.lastIndexOf("."));//업로드된 파일이미지의 확장자
		System.out.println(originalFileName);
		System.out.println(originalFiletype);
	
		//저장경로 이거 작동하는데 실제서버용
		String savePath = request.getSession().getServletContext().getRealPath("/")+"resources\\images";
		//슬래시가 역슬래시로 변경되야해서
		savePath.replace("\\", "/");
		//아래는 이미지 경로 변경
		vo.setImage(savePath+"\\"+vo.getbrandNameKor()+originalFiletype);
		//vo.setImage(savePath);
		
		file=new File(savePath,vo.getbrandNameKor()+originalFiletype); 
		System.out.println(file.getAbsolutePath());
		if(!file.exists()) {
			file.mkdirs();
			
			
		}else {
			System.out.println("파일위치 이미있음");
		}
		
		try {
			uploadedimage.transferTo(file);
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		ramyunService.updateRamyunFromDB(vo);
		
		
		RamyunVO ramyun=ramyunService.getRamyunData(vo.getbrandNameKor());
		mav.addObject("ramyun", ramyun);
		mav.setViewName("ramyun");
		
		
		return mav;
	}
	
	@GetMapping("recentupdating")
	public ModelAndView getRecentupdating(ModelAndView mav) {
		
		//이건 10개만 가져와서 오른쪽에 뿌리는것
		ramyunRecentUpdatedList=ramyunService.getRecentsUpdateListFromDB();
		mav.addObject("ramyunList", ramyunRecentUpdatedList);
		//여기까지가 우측탭 정보
		
		//일단 라면만 다 뿌리자 28개
		List<RamyunVO> ramyunUpdatedList = ramyunService.getRecentsUpdateListFromDBWhole();
		mav.addObject("ramyunListWhole", ramyunUpdatedList);
		for(RamyunVO vo : ramyunUpdatedList) {
			System.out.println(vo.getbrandNameEng());
		}
		
		
		mav.setViewName("recentupdating");
		return mav;
	}
	

	
}
