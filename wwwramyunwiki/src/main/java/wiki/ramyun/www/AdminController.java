package wiki.ramyun.www;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
	

	
	// 관리자모드 입장
	@GetMapping("/admin.do")
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

	
	// 관리자 모드에서 라면삭제
	@GetMapping("/deleteramyun.do")
	public String deleteRamyunByName(ModelAndView mav, String name) {
		ramyunService.deleteRamyunByName(name);
		return "redirect:admin.do";
	}

	// 관리자 모드에서 라면로그삭제
	@GetMapping("/deleteramyunhistory.do")
	public String deleteRamyunHistoryByName(ModelAndView mav, String id) {
		ramyunHistoryService.deleteRamyunHistoryById(id);
		return "redirect:admin.do";
	}

	// 관리자 모드에서 영양성분 삭제
	@GetMapping("/deleteingredient.do")
	public String deleteIngredientByName(ModelAndView mav, String name) {
		ingredientService.deleteIngredientByName(name);
		return "redirect:admin.do";
	}

	// 관리자 모드에서 공장삭제
	@GetMapping("/deletemanufactory.do")
	public String deleteManufactoryByName(ModelAndView mav, String name) {
		manufactoryService.deleteManufactoryByName(name);
		return "redirect:admin.do";
	}

	// 관리자 모드에서 멤버삭제
	@GetMapping("/deletemember.do")
	public String deleteMemberById(ModelAndView mav, String number) {
		memberService.deleteMemberByNumber(number);
		return "redirect:admin.do";
	}
}
