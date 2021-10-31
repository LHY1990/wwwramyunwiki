package wiki.ramyun.www;

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

import wiki.ramyun.www.ingredient.IngredientVO;
import wiki.ramyun.www.ingredient.service.IngredientService;
import wiki.ramyun.www.manufactory.ManufactoryVO;
import wiki.ramyun.www.manufactory.service.ManufactoryService;
import wiki.ramyun.www.member.MemberVO;
import wiki.ramyun.www.member.dao.MemberDAO;
import wiki.ramyun.www.member.service.MemberService;
import wiki.ramyun.www.ramyun.RamyunVO;
import wiki.ramyun.www.ramyun.service.RamyunService;
import wiki.ramyun.www.search.SearchVO;
import wiki.ramyun.www.search.service.SearchService;

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
	
	@Autowired
	@Qualifier("ingredientService")
	private IngredientService ingredientService;
	
	@Autowired
	@Qualifier("manufactoryService")
	private ManufactoryService manufactoryService;
	
	@Autowired
	@Qualifier("searchService")
	private SearchService searchService;
	
	
	//@RequestMapping(value = "/", method = RequestMethod.GET)
	@GetMapping("home")
	public String home(Locale locale, Model model) {
		ramyunRecentUpdatedList=ramyunService.getRecentsUpdateListFromDB();
		//옆에 추가분 올라가는것
		//몇개의 라면 몇명의 회원인지 찾는것
		int ramyunCount=ramyunService.getRamyunCount();
		int memberCount=memberService.getMemberCount();
		
		
		model.addAttribute("ramyunCount", ramyunCount);
		model.addAttribute("memberCount", memberCount);
		model.addAttribute("ramyunList", ramyunRecentUpdatedList);
		
		
		return "home";
	}
	@PostMapping("home")
	public String home(MemberVO vo, HttpSession session) {
		
		
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
		
		
		return "login";
	}
	@PostMapping("login")
	public ModelAndView login(ModelAndView loginMav, MemberVO vo) {
		memberService.checkMember(vo);
		
		
		return loginMav;
	}
	
	@GetMapping("join")
	public String join(ModelAndView mav) {
		
		
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
		//이건 10개만 가져와서 오른쪽에 뿌리는것. 스프링으로 빼야할듯
		ramyunRecentUpdatedList=ramyunService.getRecentsUpdateListFromDB();
		mav.addObject("ramyunList", ramyunRecentUpdatedList);
		//여기까지가 우측탭 정보
		
		//앞뒤 공백을 없앰
		name=name.trim();
		
		System.out.println(name);
		
		try {
			RamyunVO vo=ramyunService.getRamyunData(name);
			if(vo!=null) {
				//만약에 널이 아니면 라면을 반환
				mav.addObject("ramyun", vo);
				mav.setViewName("ramyun");
				return mav;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			IngredientVO ingredient=ingredientService.selectIngredientByName(name);

			if(ingredient!=null) {
				mav.addObject("ingredient", ingredient);
				mav.setViewName("nutrient");
				return mav;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		try {
			ManufactoryVO factory=manufactoryService.selectFactoryByName(name);
			if(factory!=null) {
				mav.addObject("factory", factory);
				mav.setViewName("manufactory");
				return mav;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		//정말아무것도 없다면 다음처럼된다. 비슷한 검색어를 권해보자
		List<String> similarList=searchService.getSimilars(name);
		mav.addObject("similarList", similarList);
		mav.addObject("message", name);
		mav.setViewName("notfounding");
		return mav;
	}
	
	
	@PostMapping("findramyun.do")
	public ModelAndView postSearchKeyword(ModelAndView mav, String searchBoxInput) {
		//이건 10개만 가져와서 오른쪽에 뿌리는것. 스프링으로 빼야할듯
				ramyunRecentUpdatedList=ramyunService.getRecentsUpdateListFromDB();
				mav.addObject("ramyunList", ramyunRecentUpdatedList);
				//여기까지가 우측탭 정보
				
				//앞뒤 공백을 없앰
				String name=searchBoxInput.trim();
				
				
				try {
					RamyunVO vo=ramyunService.getRamyunData(name);
					if(vo!=null) {
						//만약에 널이 아니면 라면을 반환
						mav.addObject("ramyun", vo);
						mav.setViewName("ramyun");
						return mav;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					IngredientVO ingredient=ingredientService.selectIngredientByName(name);

					if(ingredient!=null) {
						mav.addObject("ingredient", ingredient);
						mav.setViewName("nutrient");
						return mav;
					}
				}catch (Exception e) {
					e.printStackTrace();
				}
				try {
					ManufactoryVO factory=manufactoryService.selectFactoryByName(name);
					if(factory!=null) {
						mav.addObject("factory", factory);
						mav.setViewName("manufactory");
						return mav;
					}
				}catch (Exception e) {
					e.printStackTrace();
				}
				
				//정말아무것도 없다면 다음처럼된다. 비슷한 검색어를 권해보자
				List<String> similarList=searchService.getSimilars(name);
				mav.addObject("similarList", similarList);
				mav.addObject("message", name);
				mav.setViewName("notfounding");
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
		//이건 10개만 가져와서 오른쪽에 뿌리는것. 스프링으로 빼야할듯
		ramyunRecentUpdatedList=ramyunService.getRecentsUpdateListFromDB();
		mav.addObject("ramyunList", ramyunRecentUpdatedList);
		//여기까지가 우측탭 정보
		
		return mav;
		
		
	}
	@PostMapping("edit.do")
	public ModelAndView afterRamyunEdited(ModelAndView mav, RamyunVO vo,@RequestParam("uploadedimage") MultipartFile uploadedimage, HttpServletRequest request) {
		
		
		if(!uploadedimage.isEmpty()) {
			System.out.println("~~~~업로드된파일이있음~~~~~");
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
		}else {
			System.out.println("~~~~업로드된파일이없음~~~~~");
		}
		
		
		
		//이건 10개만 가져와서 오른쪽에 뿌리는것. 스프링으로 빼야할듯
		ramyunRecentUpdatedList=ramyunService.getRecentsUpdateListFromDB();
		mav.addObject("ramyunList", ramyunRecentUpdatedList);
		//여기까지가 우측탭 정보
		
		
		
		
		
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
		
		
		
		mav.setViewName("recentupdating");
		return mav;
	}
	
	@GetMapping("nutrient")
	public ModelAndView getNutrient(ModelAndView mav) {
		//이건 10개만 가져와서 오른쪽에 뿌리는것
		ramyunRecentUpdatedList=ramyunService.getRecentsUpdateListFromDB();
		mav.addObject("ramyunList", ramyunRecentUpdatedList);
		//여기까지가 우측탭 정보
		
		IngredientVO ingredient;
		
		//임의로 한개의 영양정보를 가져온다.ingredient는 데이터베이스 이름,nutrient는 페이지이름
		ingredient=ingredientService.getRecentOne();
		mav.addObject("ingredient", ingredient);
		mav.setViewName("nutrient");
		
		
		return mav;
	}
	
	@GetMapping("editingredient.do")
	public ModelAndView editIngredient(IngredientVO ingredient, String findname, ModelAndView mav) {//얘만 findname을 인자로 받는다. 변수명이 겹쳐서
		//이건 10개만 가져와서 오른쪽에 뿌리는것
		ramyunRecentUpdatedList=ramyunService.getRecentsUpdateListFromDB();
		mav.addObject("ramyunList", ramyunRecentUpdatedList);
		//여기까지가 우측탭 정보
		
		//아래는 편집탭으로 넘어가면서 같은 이름으로 가져오기
		ingredient=ingredientService.selectIngredientByName(findname);
		mav.addObject("ingredient", ingredient);
		mav.setViewName("editnutrient");
		return mav;
	}
	@PostMapping("editingredient.do")
	public ModelAndView afterEditIngredient(IngredientVO vo, ModelAndView mav) {
		//성분 수정뒤에 이 컨트롤러로 온다.
		
		ingredientService.updateIngredient(vo);
		mav.addObject("ingredient",ingredientService.selectIngredientByName(vo.getName()));
		mav.setViewName("nutrient");
		return mav;
	}
	
	@GetMapping("manufactory")
	public ModelAndView getManufactory(ModelAndView mav) {
		//이건 10개만 가져와서 오른쪽에 뿌리는것
		ramyunRecentUpdatedList=ramyunService.getRecentsUpdateListFromDB();
		mav.addObject("ramyunList", ramyunRecentUpdatedList);
		//여기까지가 우측탭 정보
		ManufactoryVO vo=manufactoryService.getRecentOne();
		
		
		mav.addObject("manufactory", vo);
		
		mav.setViewName("manufactory");
		return mav;
	}
	@GetMapping("tag")
	public ModelAndView getTag(ModelAndView mav, int page) {
		//이건 10개만 가져와서 오른쪽에 뿌리는것
		ramyunRecentUpdatedList=ramyunService.getRecentsUpdateListFromDB();
		mav.addObject("ramyunList", ramyunRecentUpdatedList);
		//여기까지가 우측탭 정보
		
		//이건 기존방법
		//List<SearchVO> voList=searchService.searchRecentUpdated();
		
		//페이지 넘버를 받아서 해당 vo를 가져온다.
		List<SearchVO> voList=searchService.searchTagPage(page);
		//해당페이지에 몇개의 페이지 링크가 필요한지 구한다.
		int totalPageCount= searchService.getTotalPageCount();
		
		//페이지의 프리뷰와 넥스트가 필요한지 본다.
		boolean hasPrev=searchService.hasPrev(page);
		boolean hasNext=searchService.hasNext(page);
		int currentPageRange=((page-1)/10)*10;	
		//위는 프레임 레인지를 정한다.
		
		
		
		mav.addObject("totalPageCount",totalPageCount);
		mav.addObject("searchList", voList);
		//프리뷰 버튼 넣을지 말지 정함
		mav.addObject("currentPageRange", currentPageRange);
		mav.addObject("hasPrev", hasPrev);
		mav.addObject("hasNext", hasNext);
		mav.setViewName("tag");
		return mav;
	}
	
	@PostMapping("searchintime.do")
	public List<String> searchInTime(HttpServletRequest request){
		System.out.println(request.getParameter("msg"));
		
		return null;
	}
	
}
