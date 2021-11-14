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

@Controller
@RequestMapping("/")
public class HomeController {
	
//	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
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
		
		//멤버인지 체크하고 멤버면 정보를 보낸다.
		boolean loginOK=memberService.checkMember(vo);
		if(loginOK) {
			vo=memberService.getMemberById(vo.getMemberId());
			
			session.setAttribute("memberNumber", vo.getMemberNumber());
			session.setAttribute("memberId", vo.getMemberId());
			session.setAttribute("memberEmail", vo.getMemberEmail());
			session.setAttribute("memberJoinDate", vo.getJoinDate());
			session.setAttribute("memberNickname", vo.getNickname());
			session.setAttribute("isMember", "true");
			return "redirect:home";
		}else {
			session.setAttribute("isMember", "false");
			return "login";
		}
		
	
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

	
	//유저가 설정창을 눌렀을때
	@GetMapping("userinfo")
	public ModelAndView getUserInfo(ModelAndView mav, HttpSession session) {
		//이건 10개만 가져와서 오른쪽에 뿌리는것. 
		mav.addObject("ramyunList", ramyunService.getRecentsUpdateListFromDB());
		
		mav.setViewName("userinfo");
		return mav;
	}
	
	
	//유저정보 변경
	@GetMapping("changeuserinfo.do")
	public ModelAndView changeuserinfo(ModelAndView mav) {
		//이건 10개만 가져와서 오른쪽에 뿌리는것. 
		mav.addObject("ramyunList", ramyunService.getRecentsUpdateListFromDB());
		
		mav.setViewName("changeuserinfo");
		return mav;
	}
	
	
	@PostMapping("changinguser.do")
	public ModelAndView changinguser(ModelAndView mav, MemberVO vo,HttpSession session) {
		//이건 10개만 가져와서 오른쪽에 뿌리는것. 
		mav.addObject("ramyunList", ramyunService.getRecentsUpdateListFromDB());
		//여기까지가 우측탭 정보
		
		
		//변경할 닉네임, 이메일과 멤버 넘버를 받아서 값을 변경한다.
		String memberNumber=session.getAttribute("memberNumber").toString();
		memberService.changeNickname(vo.getNickname(), memberNumber);
		memberService.changeMemberEmail(vo.getMemberEmail(), memberNumber);
		
		session.setAttribute("memberNickname", vo.getNickname());
		mav.setViewName("userinfo");
		return mav;
	}
	
	
	@GetMapping("login")
	public ModelAndView login(ModelAndView mav) {
		//이건 10개만 가져와서 오른쪽에 뿌리는것. 스프링으로 빼야할듯
		ramyunRecentUpdatedList=ramyunService.getRecentsUpdateListFromDB();
		mav.addObject("ramyunList", ramyunRecentUpdatedList);
		//여기까지가 우측탭 정보
		
		mav.setViewName("login");
		return mav;
	}
	
	
	@PostMapping("login")
	public ModelAndView login(ModelAndView mav, MemberVO vo) {
		//이건 10개만 가져와서 오른쪽에 뿌리는것. 스프링으로 빼야할듯
		ramyunRecentUpdatedList=ramyunService.getRecentsUpdateListFromDB();
		mav.addObject("ramyunList", ramyunRecentUpdatedList);
		//여기까지가 우측탭 정보
		memberService.checkMember(vo);
		
		return mav;
	}
	
	
	@GetMapping("join")
	public String join(ModelAndView mav) {
		//처음창은 이쪽으로 보내서 값을 넣게한다.
		System.out.println("조인 겟에 접근중");
		mailCode = String.valueOf((int)(Math.random()*1000000));
		
		return "join";
	}
	
	@PostMapping("join")
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
	
	//검색 값 받아오기. 일단 신라면으로 테스트
	@GetMapping("findramyun.do")
	public ModelAndView getSearchKeyword(ModelAndView mav, String name) {
		//이건 10개만 가져와서 오른쪽에 뿌리는것. 스프링으로 빼야할듯
		mav.addObject("ramyunList", ramyunService.getRecentsUpdateListFromDB());
		
		//앞뒤 공백을 없앰
		name=name.trim();
		name=name.replace(" ","");
		
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
				//서비스 위키스트링리졸버로 안의 컨텐츠를 변환한다
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
				//서비스 위키스트링리졸버로 안의 컨텐츠를 변환한다
				mav.addObject("manufactory", factory);
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
	//검색어가 없다면 등록하러가기
	@GetMapping("registration")
	public ModelAndView registration(ModelAndView mav) {
		//이건 10개만 가져와서 오른쪽에 뿌리는것. 스프링으로 빼야할듯
		mav.addObject("ramyunList", ramyunService.getRecentsUpdateListFromDB());
		//여기까지가 우측탭 정보
		mav.setViewName("registration");
		return mav;
	}
	@PostMapping("regist.do")
	public ModelAndView registNew(ModelAndView mav, String register, String type) {
		//이건 10개만 가져와서 오른쪽에 뿌리는것. 스프링으로 빼야할듯
		mav.addObject("ramyunList", ramyunService.getRecentsUpdateListFromDB());
		//여기까지가 우측탭 정보
		
		//빈공간을 없앤다.		
		register=register.trim();
		register=register.replace(" ","");		
		
		if(type.equals("ramyun")) {
			System.out.println("라면등록중");
			String outPrint=ramyunService.insertRamyunToDB(register);
			System.out.println(outPrint);
		}else if(type.equals("ingredient")) {
			System.out.println("원재료명등록중");
			String outPrint=ingredientService.insertIngredientToDB(register);
			System.out.println(outPrint);
		}else if(type.equals("manufactory")) {
			System.out.println("공장정보등록");
			String outPrint=manufactoryService.insertManufactoryToDB(register);
			System.out.println(outPrint);
		}
		
		mav.setViewName("home");
		return mav;
	}
	
	
	@PostMapping("findramyun.do")
	public ModelAndView postSearchKeyword(ModelAndView mav, String searchBoxInput) {
		//화면에 10개를 뿌린다.
		mav.addObject("ramyunList", ramyunService.getRecentsUpdateListFromDB());
		
		//앞뒤 공백을 없앰
		searchBoxInput=searchBoxInput.trim();
		searchBoxInput=searchBoxInput.replace(" ", "");
		String name=searchBoxInput;
		
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
				mav.addObject("manufactory", factory);
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
		//화면에 10개를 뿌린다.
		mav.addObject("ramyunList", ramyunService.getRecentsUpdateListFromDB());
		
		//편집용 데이터를 가져온다.
		try {
			RamyunVO vo=ramyunService.getRamyunDataForEdit(name);
			mav.addObject("ramyun", vo);
			mav.setViewName("editramyun");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}
	
	
	@PostMapping("edit.do")
	public ModelAndView afterRamyunEdited(ModelAndView mav, 
										RamyunVO vo,
										@RequestParam("uploadedimage") MultipartFile uploadedimage, 
										HttpServletRequest request, 
										HttpSession session) {
		
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
		
		
		//화면에 10개를 뿌린다.
		mav.addObject("ramyunList", ramyunService.getRecentsUpdateListFromDB());
		
		//여기가 실제로 라면을 업데이트하는곳
		ramyunService.updateRamyunToDB(vo);
		//라면 히스토리도 같이 업데이트한다. 세션에서 작성자의 이름을 받아온다.
		String writer = (String) session.getAttribute("memberNickname");
		System.out.println("작성자이름"+writer);
		System.out.println(vo.getbrandNameKor());
		ramyunHistoryService.updateRamyunHistoryToDB(vo, writer);
		
		RamyunVO ramyun=ramyunService.getRamyunData(vo.getbrandNameKor());
		mav.addObject("ramyun", ramyun);
		mav.setViewName("ramyun");
		
		return mav;
	}
	
	
	@GetMapping("recentupdating")
	public ModelAndView getRecentupdating(ModelAndView mav) {
		
		//화면에 10개를 뿌린다.
		mav.addObject("ramyunList", ramyunService.getRecentsUpdateListFromDB());
		
		//일단 라면만 다 뿌리자 28개
		List<RamyunVO> ramyunUpdatedList = ramyunService.getRecentsUpdateListFromDBWhole();
		mav.addObject("ramyunListWhole", ramyunUpdatedList);
		
		mav.setViewName("recentupdating");
		
		return mav;
	}
	
	
	@GetMapping("nutrient")
	public ModelAndView getNutrient(ModelAndView mav) {
		//화면에 10개를 뿌린다.
		mav.addObject("ramyunList", ramyunService.getRecentsUpdateListFromDB());
		
		IngredientVO ingredient;
		
		//임의로 한개의 영양정보를 가져온다.ingredient는 데이터베이스 이름,nutrient는 페이지이름
		ingredient=ingredientService.getRandomOne();
		mav.addObject("ingredient", ingredient);
		mav.setViewName("nutrient");
		
		return mav;
	}
	
	
	@GetMapping("editingredient.do")
	public ModelAndView editIngredient(IngredientVO ingredient, String findname, ModelAndView mav) {//얘만 findname을 인자로 받는다. 변수명이 겹쳐서
		//화면에 10개를 뿌린다.
		mav.addObject("ramyunList", ramyunService.getRecentsUpdateListFromDB());
		
		//아래는 편집탭으로 넘어가면서 같은 이름으로 가져오기
		ingredient=ingredientService.selectIngredientByNameForEdit(findname);
		mav.addObject("ingredient", ingredient);
		mav.setViewName("editnutrient");
		
		return mav;
	}
	
	
	@PostMapping("editingredient.do")
	public ModelAndView afterEditIngredient(IngredientVO vo, ModelAndView mav) {
		//성분 수정뒤에 이 컨트롤러로 온다.
		//화면에 10개를 뿌린다.
		mav.addObject("ramyunList", ramyunService.getRecentsUpdateListFromDB());
		
		ingredientService.updateIngredient(vo);
		IngredientVO ingredient = ingredientService.selectIngredientByName(vo.getName());
		mav.addObject("ingredient",ingredient);
		mav.setViewName("nutrient");
		
		return mav;
	}
	
	
	//랜덤으로 하나뿌림
	@GetMapping("manufactory")
	public ModelAndView getManufactory(ModelAndView mav,ManufactoryVO vo) {
		//화면에 10개를 뿌린다.
		mav.addObject("ramyunList", ramyunService.getRecentsUpdateListFromDB());
		
		//랜덤으로 하나 가져오기
		vo=manufactoryService.getRandomOne();
		
		mav.addObject("manufactory", vo);
		mav.setViewName("manufactory");
		
		return mav;
	}
	
	
	//	공장 작성후 post로 보내면 여기서 받는다.
	@PostMapping("updatemanufactory.do")
	public ModelAndView updateManufactory(ModelAndView mav, ManufactoryVO vo) {
		//화면에 10개를 뿌린다.
		mav.addObject("ramyunList", ramyunService.getRecentsUpdateListFromDB());
		
		//받은 vo이름으로 업데이트
		manufactoryService.updateManufactory(vo);
		vo=manufactoryService.selectFactoryByName(vo.getFactoryName());
		
		mav.addObject("manufactory", vo);
		//일단 홈으로 보냄
		mav.setViewName("manufactory");
		
		return mav;
	}
	
	
	@GetMapping("editmanufactory.do")
	public ModelAndView getEditManufactory(ModelAndView mav,String findname) {
		//화면에 10개를 뿌린다.
		mav.addObject("ramyunList", ramyunService.getRecentsUpdateListFromDB());
		
		ManufactoryVO vo=manufactoryService.selectFactoryByNameForEdit(findname);
		mav.addObject("manufactory", vo);
		mav.setViewName("editmanufactory");
		
		return mav;
	}
	
	
	@GetMapping("tag")
	public ModelAndView getTag(ModelAndView mav, int page) {
		//화면에 10개를 뿌린다.
		mav.addObject("ramyunList", ramyunService.getRecentsUpdateListFromDB());
		
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
	
	
	//역사버튼 구현
	@GetMapping("ramyunhistory.do")
	public ModelAndView getRamyunHistoryByName(ModelAndView mav,String name) {
		//화면에 10개를 뿌린다.
		mav.addObject("ramyunList", ramyunService.getRecentsUpdateListFromDB());
		
		//일단 이것들은 입력이 되어야 출력이 되니까 입력부터 구현하고 돌아온다. 입력은 라면 업데이트부터
		List<RamyunHistoryVO> voList=ramyunHistoryService.getHistoryByName(name);
		
		mav.addObject("ramyunHistoryList", voList);
		mav.setViewName("ramyunhistory");
		return mav;
	}
	
	
	// 역사탭에서 보기구현
	@GetMapping("ramyunlog.do")
	public ModelAndView getRamyunHistoryById(ModelAndView mav, String id) {
		//화면에 10개를 뿌린다.
		mav.addObject("ramyunList", ramyunService.getRecentsUpdateListFromDB());
		
		RamyunHistoryVO vo=ramyunHistoryService.getHistoryById(id);
		mav.addObject("ramyun", vo);
		mav.setViewName("ramyunlogview");
		return mav;
	}
	
	
	//아래는 AJAX탭
	
	
	//리스폰스 바디로 받아 비동기로 처리
	@PostMapping("searchintime.do")
	public @ResponseBody Object searchInTime(HttpServletRequest request){
		List<String> searchList=new ArrayList<String>();
		String noSpaceString=request.getParameter("msg").replace(" ","");
		
		searchList=searchService.searchInTime(noSpaceString);
		//아래는 실시간으로 출력해보기
		if(noSpaceString.equals("")) {
			List<String> noValues=new ArrayList<String>();
			noValues.add("");
			noValues.add("");
			noValues.add("");
			noValues.add("");
			noValues.add("");
			return noValues;
		}
		
		return searchList;
		
	}
	
	
	//라면 좋아요  구현
	//리스폰스 바디로 받아 비동기로 처리
	@PostMapping("likeramyun.do")
	public @ResponseBody Object postLikeRamyun(HttpSession session, String ramyunName) {
		
		//회원의 이름을 가져온다.
		String memberId=(String) session.getAttribute("memberId");
		//회원아이디와 라면이름이 제대로 들어오나 확인한다.
		
		int likesCounts=metadataService.addLikeRamyun(memberId, ramyunName);
		
		List<String> likeList=new ArrayList<String>();
		
		likeList.add(String.valueOf(likesCounts));
		
		return likeList;
	}
	
	
	//라면 신고 구현
	@PostMapping("reportramyun.do")
	public @ResponseBody Object postReportRamyun(HttpSession session, String ramyunName) {
		//회원의 이름을 가져온다.
		String memberId=(String) session.getAttribute("memberId");
		//회원아이디와 라면이름이 제대로 들어오나 확인한다.
		int reportCounts=metadataService.addReportRamyun(memberId, ramyunName);
		
		List<String> reportList=new ArrayList<String>();
		
		reportList.add(String.valueOf(reportCounts));
		
		return reportList;
	}
	
	
	//영양성분 좋아요  구현
	//리스폰스 바디로 받아 비동기로 처리
	@PostMapping("likeingredient.do")
	public @ResponseBody Object postLikeIngredient(HttpSession session, String ingredientName) {
		
		//회원의 이름을 가져온다.
		String memberId=(String) session.getAttribute("memberId");
		
		int likesCounts=metadataService.addLikeIngredient(memberId, ingredientName);
		
		List<String> likeList=new ArrayList<String>();
		
		likeList.add(String.valueOf(likesCounts));
		
		return likeList;
	}
	
	
	//영양성분 신고버튼 ajax처리
	@PostMapping("reportingredient.do")
	public @ResponseBody Object postReportIngredient(HttpSession session, String ingredientName) {
		//회원의 이름을 가져온다.
		String memberId=(String) session.getAttribute("memberId");
		//회원아이디와 라면이름이 제대로 들어오나 확인한다.
		int reportCounts=metadataService.addReportIngredient(memberId, ingredientName);
		
		List<String> reportList=new ArrayList<String>();
		
		reportList.add(String.valueOf(reportCounts));
		
		return reportList;
	}
	
	
	
	//공장정보 좋아요  구현
	//리스폰스 바디로 받아 비동기로 처리
	@PostMapping("likemanufactory.do")
	public @ResponseBody Object postLikeManufactory(HttpSession session, String manufactoryName) {
		
		//회원의 이름을 가져온다.
		String memberId=(String) session.getAttribute("memberId");
		
		int likesCounts=metadataService.addLikeManufactory(memberId, manufactoryName);
		
		List<String> likeList=new ArrayList<String>();
		
		likeList.add(String.valueOf(likesCounts));
		
		return likeList;
	}
	
	
	//공장정보 신고버튼 ajax처리
	@PostMapping("reportmanufactory.do")
	public @ResponseBody Object postReportManufactory(HttpSession session, String manufactoryName) {
		//회원의 이름을 가져온다.
		String memberId=(String) session.getAttribute("memberId");
		//회원아이디와 라면이름이 제대로 들어오나 확인한다.
		int reportCounts=metadataService.addReportManufactory(memberId, manufactoryName);
		
		List<String> reportList=new ArrayList<String>();
		
		reportList.add(String.valueOf(reportCounts));
		
		return reportList;
	}
	
	
	//여기는 home>작성방법
	@GetMapping("howto.do")
	public ModelAndView howToMakeText(ModelAndView mav) {
		//화면에 10개를 뿌린다.
		mav.addObject("ramyunList", ramyunService.getRecentsUpdateListFromDB());
		mav.setViewName("howto");
		return mav;
	}
}
