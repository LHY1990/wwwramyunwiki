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

import wiki.ramyun.www.member.MemberVO;
import wiki.ramyun.www.member.service.MemberService;
import wiki.ramyun.www.metadata.service.MetadataService;
import wiki.ramyun.www.search.service.SearchService;
import wiki.ramyun.www.wikistringresolver.WikiStringResolver;

@Controller
public class AjaxController {
	// AJAX 컨트롤러
	@Autowired
	@Qualifier("memberService")
	private MemberService memberService;
	
	@Autowired
	@Qualifier("searchService")
	private SearchService searchService;

	@Autowired
	@Qualifier("metadataService")
	private MetadataService metadataService;

	
	// 리스폰스 바디로 받아 비동기로 처리
	@PostMapping("/searchintime")
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
		System.out.println(searchList);
		
		return searchList;
	}
	
	
	// 회원가입시 아이디 중복확인
	@PostMapping("/isunique")
	public @ResponseBody Object isUniqueIdCheck(HttpServletRequest request) {
		List<String> list=new ArrayList<String>();
		MemberVO vo = new MemberVO();
		vo.setMemberId(request.getParameter("input"));
		
		//만약에 중복아이디가 없다면 초록글씨를 중복이라면 적색 글자를 내보낸다.
		if(request.getParameter("input").length()==0) {
			list.add("아이디를 입력해 주세요.");
			return list;
		}else if(request.getParameter("input").length()<=3) {
			list.add("네 글자 이상 입력해주세요");
			return list;
		}else if(memberService.isUnique(vo)) {
			list.add("사용 가능한 아이디 입니다.");
			return list;
		}else {
			list.add("이미 사용중인 아이디 입니다.");
			return list;
		}
		
	}

	
	// 라면 좋아요 구현
	// 리스폰스 바디로 받아 비동기로 처리
	@PostMapping("/likeramyun")
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
	@PostMapping("/reportramyun")
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
	@PostMapping("/likeingredient")
	public @ResponseBody Object postLikeIngredient(HttpSession session, String ingredientName) {

		// 회원의 이름을 가져온다.
		String memberId = (String) session.getAttribute("memberId");

		int likesCounts = metadataService.addLikeIngredient(memberId, ingredientName);

		List<String> likeList = new ArrayList<String>();

		likeList.add(String.valueOf(likesCounts));

		return likeList;
	}

	
	// 영양성분 신고버튼 ajax처리
	@PostMapping("/reportingredient")
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
	@PostMapping("/likemanufactory")
	public @ResponseBody Object postLikeManufactory(HttpSession session, String manufactoryName) {

		// 회원의 이름을 가져온다.
		String memberId = (String) session.getAttribute("memberId");

		int likesCounts = metadataService.addLikeManufactory(memberId, manufactoryName);

		List<String> likeList = new ArrayList<String>();

		likeList.add(String.valueOf(likesCounts));

		return likeList;
	}

	
	// 공장정보 신고버튼 ajax처리
	@PostMapping("/reportmanufactory")
	public @ResponseBody Object postReportManufactory(HttpSession session, String manufactoryName) {
		// 회원의 이름을 가져온다.
		String memberId = (String) session.getAttribute("memberId");
		// 회원아이디와 라면이름이 제대로 들어오나 확인한다.
		int reportCounts = metadataService.addReportManufactory(memberId, manufactoryName);

		List<String> reportList = new ArrayList<String>();

		reportList.add(String.valueOf(reportCounts));

		return reportList;
	}
	
	
	//라면,공장,영양성분 편집시에 문제가 있으면 실시간으로 보낸다.
	@PostMapping("/grammercheck")
	public @ResponseBody Object doEditingGoesRight(String contents) {
		
		List<String> result = new ArrayList<String>();
		//예외 발생시 특수 문자열을 반환한다.
		try {
			WikiStringResolver.encodeContents(contents);
		}catch(Exception e) {
			//WikiStringResolver에서 던진 예외 메세지를 받아서 리스트로 view에 던진다.
			result.add(e.getMessage());
		}
		
		return result;
	}
	
	
	
}
