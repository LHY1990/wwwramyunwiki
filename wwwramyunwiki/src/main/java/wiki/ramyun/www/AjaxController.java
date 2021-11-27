package wiki.ramyun.www;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import wiki.ramyun.www.ingredient.service.IngredientService;
import wiki.ramyun.www.manufactory.service.ManufactoryService;
import wiki.ramyun.www.member.service.MemberService;
import wiki.ramyun.www.metadata.service.MetadataService;
import wiki.ramyun.www.ramyun.service.RamyunService;
import wiki.ramyun.www.ramyunhistory.service.RamyunHistoryService;
import wiki.ramyun.www.search.service.SearchService;

@Controller
public class AjaxController {
	// AJAX 컨트롤러

	@Autowired
	@Qualifier("searchService")
	private SearchService searchService;

	@Autowired
	@Qualifier("metadataService")
	private MetadataService metadataService;

	
	// 리스폰스 바디로 받아 비동기로 처리
	@PostMapping("/searchintime.do")
	public @ResponseBody Object searchInTime(HttpServletRequest request) {
		List<String> searchList = new ArrayList<String>();
		String noSpaceString = request.getParameter("msg").replace(" ", "");

		searchList = searchService.searchInTime(noSpaceString);
		// 아래는 실시간으로 출력해보기
		if (noSpaceString.equals("")) {
			List<String> noValues = new ArrayList<String>();
			noValues.add("");
			noValues.add("");
			noValues.add("");
			noValues.add("");
			noValues.add("");
			return noValues;
		}

		return searchList;

	}

	
	// 라면 좋아요 구현
	// 리스폰스 바디로 받아 비동기로 처리
	@PostMapping("/likeramyun.do")
	public @ResponseBody Object postLikeRamyun(HttpSession session, String ramyunName) {

		// 회원의 이름을 가져온다.
		String memberId = (String) session.getAttribute("memberId");
		// 회원아이디와 라면이름이 제대로 들어오나 확인한다.

		int likesCounts = metadataService.addLikeRamyun(memberId, ramyunName);

		List<String> likeList = new ArrayList<String>();

		likeList.add(String.valueOf(likesCounts));

		return likeList;
	}

	
	// 라면 신고 구현
	@PostMapping("/reportramyun.do")
	public @ResponseBody Object postReportRamyun(HttpSession session, String ramyunName) {
		// 회원의 이름을 가져온다.
		String memberId = (String) session.getAttribute("memberId");
		// 회원아이디와 라면이름이 제대로 들어오나 확인한다.
		int reportCounts = metadataService.addReportRamyun(memberId, ramyunName);

		List<String> reportList = new ArrayList<String>();

		reportList.add(String.valueOf(reportCounts));

		return reportList;
	}

	
	// 영양성분 좋아요 구현
	@PostMapping("/likeingredient.do")
	public @ResponseBody Object postLikeIngredient(HttpSession session, String ingredientName) {

		// 회원의 이름을 가져온다.
		String memberId = (String) session.getAttribute("memberId");

		int likesCounts = metadataService.addLikeIngredient(memberId, ingredientName);

		List<String> likeList = new ArrayList<String>();

		likeList.add(String.valueOf(likesCounts));

		return likeList;
	}

	
	// 영양성분 신고버튼 ajax처리
	@PostMapping("/reportingredient.do")
	public @ResponseBody Object postReportIngredient(HttpSession session, String ingredientName) {
		// 회원의 이름을 가져온다.
		String memberId = (String) session.getAttribute("memberId");
		// 회원아이디와 라면이름이 제대로 들어오나 확인한다.
		int reportCounts = metadataService.addReportIngredient(memberId, ingredientName);

		List<String> reportList = new ArrayList<String>();

		reportList.add(String.valueOf(reportCounts));

		return reportList;
	}

	
	// 공장정보 좋아요 구현
	@PostMapping("/likemanufactory.do")
	public @ResponseBody Object postLikeManufactory(HttpSession session, String manufactoryName) {

		// 회원의 이름을 가져온다.
		String memberId = (String) session.getAttribute("memberId");

		int likesCounts = metadataService.addLikeManufactory(memberId, manufactoryName);

		List<String> likeList = new ArrayList<String>();

		likeList.add(String.valueOf(likesCounts));

		return likeList;
	}

	
	// 공장정보 신고버튼 ajax처리
	@PostMapping("/reportmanufactory.do")
	public @ResponseBody Object postReportManufactory(HttpSession session, String manufactoryName) {
		// 회원의 이름을 가져온다.
		String memberId = (String) session.getAttribute("memberId");
		// 회원아이디와 라면이름이 제대로 들어오나 확인한다.
		int reportCounts = metadataService.addReportManufactory(memberId, manufactoryName);

		List<String> reportList = new ArrayList<String>();

		reportList.add(String.valueOf(reportCounts));

		return reportList;
	}
	
}
