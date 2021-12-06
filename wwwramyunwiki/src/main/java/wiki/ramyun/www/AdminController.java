package wiki.ramyun.www;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import wiki.ramyun.www.ingredient.service.IngredientService;
import wiki.ramyun.www.manufactory.service.ManufactoryService;
import wiki.ramyun.www.member.service.MemberService;
import wiki.ramyun.www.pagenation.Pagenation;
import wiki.ramyun.www.ramyun.service.RamyunService;
import wiki.ramyun.www.ramyunhistory.service.RamyunHistoryService;

//관리자 권한으로 접근 컨트롤러 
@Controller
public class AdminController {
	
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
	@Qualifier("pagenation")
	private Pagenation pagenation;
	
	
	
	// 관리자모드 입장. 더 이상쓰지않고 급하게 찾을때 쓴다.
	@Deprecated
	@GetMapping("/admin")
	public ModelAndView getAdmin(ModelAndView mav) {
		// 모든 라면,영양성분,공장, 회원 리스트를 넣는다.
		mav.addObject("ramyunList", ramyunService.selectAllFromRamyunDB());
		mav.addObject("ramyunHistoryList", ramyunHistoryService.selectAllFromRamyunHistoryDB());
		mav.addObject("ingredientList", ingredientService.selectAllFromIngredient());
		mav.addObject("manufactoryList", manufactoryService.selectAllFromManufactory());
		mav.addObject("memberList", memberService.selectAllFromMember());
		mav.setViewName("admin");
		return mav;
	}

	
	//관리자페이지 내부에서 탭이동 adminpage
	@GetMapping("/admin/management")
	public ModelAndView postAdminpage(ModelAndView mav,HttpSession session, 
			@RequestParam(value="selectedtag", defaultValue = "라면정보", required = false) String selectedtag,
			@RequestParam(value="page", defaultValue = "1", required = false) int page,
			@RequestParam(value="range", defaultValue = "1", required = false) int range) {
		
		if(session.getAttribute("memberLevel")==null || session.getAttribute("memberLevel").equals("") || session.getAttribute("memberLevel").equals("1")) {
			mav.setViewName("redirect:../home");
			return mav;
		}
		
		//이 페이지에서는 25를 기본 리스트 길이로한다.
		pagenation.setListSize(25);
		
		
		switch (selectedtag) {
		
		case "라면정보":
			pagenation.pageInfo(page, range, ramyunService.getRamyunCount());
			mav.addObject("ramyunList", ramyunService.selectRamyunByRange(pagenation.getStartList(), pagenation.getListSize()));
			break;

		case "라면로그":
			pagenation.pageInfo(page, range, ramyunHistoryService.getRamyunHistoryCount());
			mav.addObject("ramyunHistoryList", ramyunHistoryService.selectRamyunHistoryByRange(pagenation.getStartList(), pagenation.getListSize()));
			break;
		
		case "공장정보":
			pagenation.pageInfo(page, range, manufactoryService.getManufactoryCount());
			mav.addObject("manufactoryList", manufactoryService.selectManufactoryByRange(pagenation.getStartList(), pagenation.getListSize()));
			break;
		
		case "영양성분":
			pagenation.pageInfo(page, range, ingredientService.getIngredientCount());
			mav.addObject("ingredientList", ingredientService.selectIngredientByRange(pagenation.getStartList(), pagenation.getListSize()));
			break;
		
		case "멤버관리":
			pagenation.pageInfo(page, range, memberService.getMemberCount());
			mav.addObject("memberList", memberService.selectMemberByRange(pagenation.getStartList(), pagenation.getListSize()));
			break;
		
		default:
			
			break;
		}
		
		mav.addObject("pagenation", pagenation);
		mav.addObject("currunttab",selectedtag);
		mav.setViewName("adminpage");
		return mav;
	}
	
	
	// 관리자 모드에서 라면삭제
	@GetMapping("/admin/deleteramyun")
	public String deleteRamyunByName(HttpServletRequest request, ModelAndView mav, String name) {
		ramyunService.deleteRamyunByName(name);
		return "redirect:"+request.getHeader("referer");
	}

	// 관리자 모드에서 라면로그삭제
	@GetMapping("/admin/deleteramyunhistory")
	public String deleteRamyunHistoryByName(HttpServletRequest request, ModelAndView mav, String id) {
		ramyunHistoryService.deleteRamyunHistoryById(id);
		return "redirect:"+request.getHeader("referer");
	}

	// 관리자 모드에서 영양성분 삭제
	@GetMapping("/admin/deleteingredient")
	public String deleteIngredientByName(HttpServletRequest request, ModelAndView mav, String name) {
		ingredientService.deleteIngredientByName(name);
		return "redirect:"+request.getHeader("referer");
	}

	// 관리자 모드에서 공장삭제
	@GetMapping("/admin/deletemanufactory")
	public String deleteManufactoryByName(HttpServletRequest request, ModelAndView mav, String name) {
		manufactoryService.deleteManufactoryByName(name);
		return "redirect:"+request.getHeader("referer");
	}

	// 관리자 모드에서 멤버삭제
	@GetMapping("/admin/deletemember")
	public String deleteMemberById(HttpServletRequest request, ModelAndView mav, String number) {
		memberService.deleteMemberByNumber(number);
		return "redirect:"+request.getHeader("referer");
	}
}
