package wiki.ramyun.www;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import wiki.ramyun.www.ingredient.IngredientVO;
import wiki.ramyun.www.ingredient.service.IngredientService;
import wiki.ramyun.www.manufactory.ManufactoryVO;
import wiki.ramyun.www.manufactory.service.ManufactoryService;
import wiki.ramyun.www.pagenation.Pagenation;
import wiki.ramyun.www.ramyun.RamyunVO;
import wiki.ramyun.www.ramyun.service.RamyunService;
import wiki.ramyun.www.ramyunhistory.RamyunHistoryVO;
import wiki.ramyun.www.ramyunhistory.service.RamyunHistoryService;
import wiki.ramyun.www.search.SearchVO;
import wiki.ramyun.www.search.service.SearchService;

//라면, 라면로그, 공장, 영양성분에 접근하여 CRUD 
@Controller
public class SearchingController {
	
	File file;
	
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
	@Qualifier("pagenation")
	private Pagenation pagenation;

	// 검색 값 받아오기. 일단 신라면으로 테스트
	@GetMapping("/findramyun")
	public ModelAndView getSearchKeyword(ModelAndView mav, String name) {
		//이건 10개만 가져와서 오른쪽에 뿌리는것. 
		mav.addObject("ramyunList", ramyunService.getRecentsUpdateListFromDB());
		//우측탭 라면 이미지 리스트 가져오기
		mav.addObject("randomRamyunImageList",ramyunService.getTodaysRamyunImageList());

		// 앞뒤 공백을 없앰
		name = name.trim();
		name = name.replace(" ", "");

		try {
			RamyunVO vo = ramyunService.getRamyunData(name);
			if (vo != null) {
				// 만약에 널이 아니면 라면을 반환
				mav.addObject("ramyun", vo);
				mav.setViewName("ramyun");
				return mav;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			IngredientVO ingredient = ingredientService.selectIngredientByName(name);

			if (ingredient != null) {
				// 서비스 위키스트링리졸버로 안의 컨텐츠를 변환한다
				mav.addObject("ingredient", ingredient);
				mav.setViewName("nutrient");
				return mav;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			ManufactoryVO factory = manufactoryService.selectFactoryByName(name);
			if (factory != null) {
				// 서비스 위키스트링리졸버로 안의 컨텐츠를 변환한다
				mav.addObject("manufactory", factory);
				mav.setViewName("manufactory");
				return mav;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 정말아무것도 없다면 다음처럼된다. 비슷한 검색어를 권해보자
		List<String> similarList = searchService.getSimilars(name);
		mav.addObject("similarList", similarList);
		mav.addObject("message", name);
		mav.setViewName("notfounding");
		return mav;
	}
	
	
	//검색창 왼쪽의 셔플 버튼을 눌렀을때, 랜덤 라면으로 링크될것
	@GetMapping("/getrandomramyun")
	public ModelAndView getRandomRamyun(ModelAndView mav) {
		//이건 10개만 가져와서 오른쪽에 뿌리는것. 
		mav.addObject("ramyunList", ramyunService.getRecentsUpdateListFromDB());
		//우측탭 라면 이미지 리스트 가져오기
		mav.addObject("randomRamyunImageList",ramyunService.getTodaysRamyunImageList());
		try {
			RamyunVO vo = ramyunService.getRandomRamyun();
			if (vo != null) {
				// 만약에 널이 아니면 라면을 반환
				mav.addObject("ramyun", vo);
				mav.setViewName("ramyun");
				return mav;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mav;
	}
	

	// 검색어가 없다면 등록하러가기
	@GetMapping("/registration")
	public ModelAndView registration(ModelAndView mav) {
		//이건 10개만 가져와서 오른쪽에 뿌리는것. 
		mav.addObject("ramyunList", ramyunService.getRecentsUpdateListFromDB());
		//우측탭 라면 이미지 리스트 가져오기
		mav.addObject("randomRamyunImageList",ramyunService.getTodaysRamyunImageList());

		// 여기까지가 우측탭 정보
		mav.setViewName("registration");
		return mav;
	}

	
	@PostMapping("/regist")
	public String registNew(ModelAndView mav, String register, String type) throws UnsupportedEncodingException {
		// 빈공간을 없앤다.
		register = register.trim();
		register = register.replace(" ", "");

		if (type.equals("ramyun")) {
			ramyunService.insertRamyunToDB(register);
		} else if (type.equals("ingredient")) {
			ingredientService.insertIngredientToDB(register);
		} else if (type.equals("manufactory")) {
			manufactoryService.insertManufactoryToDB(register);
		}
		
		//한글 인코딩을 해준다. 예외를 던진다.
		return "redirect:findramyun?name="+URLEncoder.encode(register,StandardCharsets.UTF_8.toString());
	}

	@PostMapping("/findramyun")
	public ModelAndView postSearchKeyword(ModelAndView mav, String searchBoxInput) {
		//이건 10개만 가져와서 오른쪽에 뿌리는것. 
		mav.addObject("ramyunList", ramyunService.getRecentsUpdateListFromDB());
		//우측탭 라면 이미지 리스트 가져오기
		mav.addObject("randomRamyunImageList",ramyunService.getTodaysRamyunImageList());

		// 앞뒤 공백을 없앰
		searchBoxInput = searchBoxInput.trim();
		searchBoxInput = searchBoxInput.replace(" ", "");
		String name = searchBoxInput;

		try {
			RamyunVO vo = ramyunService.getRamyunData(name);
			if (vo != null) {
				// 만약에 널이 아니면 라면을 반환
				mav.addObject("ramyun", vo);
				mav.setViewName("ramyun");
				return mav;
			}
		} catch (Exception e) {
			System.out.println("라면에서 검색안됌");
		}
		try {
			IngredientVO ingredient = ingredientService.selectIngredientByName(name);
			if (ingredient != null) {
				mav.addObject("ingredient", ingredient);
				mav.setViewName("nutrient");
				return mav;
			}
		} catch (Exception e) {
			System.out.println("영양성분에서 검색안됌");
		}
		try {
			ManufactoryVO factory = manufactoryService.selectFactoryByName(name);
			if (factory != null) {
				mav.addObject("manufactory", factory);
				mav.setViewName("manufactory");
				return mav;
			}
		} catch (Exception e) {
			System.out.println("공장에서 검색안됌");
		}

		// 정말아무것도 없다면 다음처럼된다. 비슷한 검색어를 권해보자
		List<String> similarList = searchService.getSimilars(name);
		mav.addObject("similarList", similarList);
		mav.addObject("message", name);
		mav.setViewName("notfounding");

		return mav;
	}

	@GetMapping("/editramyun")
	public ModelAndView getEditRamyun(ModelAndView mav, String name) {
		//이건 10개만 가져와서 오른쪽에 뿌리는것. 
		mav.addObject("ramyunList", ramyunService.getRecentsUpdateListFromDB());
		//우측탭 라면 이미지 리스트 가져오기
		mav.addObject("randomRamyunImageList",ramyunService.getTodaysRamyunImageList());

		// 편집용 데이터를 가져온다.
		try {
			RamyunVO vo = ramyunService.getRamyunDataForEdit(name);
			mav.addObject("ramyun", vo);
			mav.setViewName("editramyun");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}

	@PostMapping("/edit")
	public ModelAndView afterRamyunEdited(ModelAndView mav, RamyunVO vo,
			@RequestParam("uploadedimage") MultipartFile uploadedimage, HttpServletRequest request,
			HttpSession session) {

		if (!uploadedimage.isEmpty()) {
			String originalFileName = uploadedimage.getOriginalFilename(); // 업로드된 파일이미지의 이름
			String originalFiletype = originalFileName.substring(originalFileName.lastIndexOf("."));// 업로드된 파일이미지의 확장자
			System.out.println(originalFileName);
			System.out.println(originalFiletype);

			// 저장경로 이거 작동하는데 실제서버용
			String savePath = request.getSession().getServletContext().getRealPath("/") + "resources/images";

			// 슬래시가 역슬래시로 변경되야해서
			savePath.replace("\\", "/");

			// 임시로 이미지를 강제로 png로 바꿔본다.->잘된다
			originalFiletype = ".png";

			// 아래는 이미지 경로 변경
			vo.setImage(savePath + "/" + vo.getbrandNameKor() + originalFiletype);
			// vo.setImage(savePath);

			file = new File(savePath, vo.getbrandNameKor() + originalFiletype);
			System.out.println(file.getAbsolutePath());
			if (!file.exists()) {
				file.mkdirs();
			} else {
				System.out.println("파일위치 이미있음");
			}

			try {
				uploadedimage.transferTo(file);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("~~~~업로드된파일이없음~~~~~");
		}

		//이건 10개만 가져와서 오른쪽에 뿌리는것. 
		mav.addObject("ramyunList", ramyunService.getRecentsUpdateListFromDB());
		//우측탭 라면 이미지 리스트 가져오기
		mav.addObject("randomRamyunImageList",ramyunService.getTodaysRamyunImageList());

		// 여기가 실제로 라면을 업데이트하는곳
		ramyunService.updateRamyunToDB(vo);
		// 라면 히스토리도 같이 업데이트한다. 세션에서 작성자의 이름을 받아온다.
		String writer = (String) session.getAttribute("memberNickname");
		String writerMemberNumber = String.valueOf(session.getAttribute("memberNumber"));
		ramyunHistoryService.updateRamyunHistoryToDB(vo, writer, writerMemberNumber);

		RamyunVO ramyun = ramyunService.getRamyunData(vo.getbrandNameKor());
		mav.addObject("ramyun", ramyun);
		mav.setViewName("ramyun");

		return mav;
	}

	@GetMapping("/recentupdating")
	public ModelAndView getRecentupdating(ModelAndView mav) {
		//이건 10개만 가져와서 오른쪽에 뿌리는것. 
		mav.addObject("ramyunList", ramyunService.getRecentsUpdateListFromDB());
		//우측탭 라면 이미지 리스트 가져오기
		mav.addObject("randomRamyunImageList",ramyunService.getTodaysRamyunImageList());

		// 일단 라면만 다 뿌리자 28개
		List<RamyunVO> ramyunUpdatedList = ramyunService.getRecentsUpdateListFromDBWhole();
		mav.addObject("ramyunListWhole", ramyunUpdatedList);

		mav.setViewName("recentupdating");

		return mav;
	}

	@GetMapping("/nutrient")
	public ModelAndView getNutrient(ModelAndView mav) {
		//이건 10개만 가져와서 오른쪽에 뿌리는것. 
		mav.addObject("ramyunList", ramyunService.getRecentsUpdateListFromDB());
		//우측탭 라면 이미지 리스트 가져오기
		mav.addObject("randomRamyunImageList",ramyunService.getTodaysRamyunImageList());

		IngredientVO ingredient;

		// 임의로 한개의 영양정보를 가져온다.ingredient는 데이터베이스 이름,nutrient는 페이지이름
		ingredient = ingredientService.getRandomOne();
		mav.addObject("ingredient", ingredient);
		mav.setViewName("nutrient");

		return mav;
	}

	@GetMapping("/editingredient")
	public ModelAndView editIngredient(IngredientVO ingredient, String findname, ModelAndView mav) {// 얘만 findname을 인자로
																									// 받는다. 변수명이 겹쳐서
		//이건 10개만 가져와서 오른쪽에 뿌리는것. 
		mav.addObject("ramyunList", ramyunService.getRecentsUpdateListFromDB());
		//우측탭 라면 이미지 리스트 가져오기
		mav.addObject("randomRamyunImageList",ramyunService.getTodaysRamyunImageList());

		// 아래는 편집탭으로 넘어가면서 같은 이름으로 가져오기
		ingredient = ingredientService.selectIngredientByNameForEdit(findname);
		mav.addObject("ingredient", ingredient);
		mav.setViewName("editnutrient");

		return mav;
	}

	@PostMapping("/editingredient")
	public ModelAndView afterEditIngredient(IngredientVO vo, ModelAndView mav) {
		// 성분 수정뒤에 이 컨트롤러로 온다.
		//이건 10개만 가져와서 오른쪽에 뿌리는것. 
		mav.addObject("ramyunList", ramyunService.getRecentsUpdateListFromDB());
		//우측탭 라면 이미지 리스트 가져오기
		mav.addObject("randomRamyunImageList",ramyunService.getTodaysRamyunImageList());

		ingredientService.updateIngredient(vo);
		IngredientVO ingredient = ingredientService.selectIngredientByName(vo.getName());
		mav.addObject("ingredient", ingredient);
		mav.setViewName("nutrient");

		return mav;
	}

	// 랜덤으로 하나뿌림
	@GetMapping("/manufactory")
	public ModelAndView getManufactory(ModelAndView mav, ManufactoryVO vo) {
		//이건 10개만 가져와서 오른쪽에 뿌리는것. 
		mav.addObject("ramyunList", ramyunService.getRecentsUpdateListFromDB());
		//우측탭 라면 이미지 리스트 가져오기
		mav.addObject("randomRamyunImageList",ramyunService.getTodaysRamyunImageList());

		// 랜덤으로 하나 가져오기
		vo = manufactoryService.getRandomOne();

		mav.addObject("manufactory", vo);
		mav.setViewName("manufactory");

		return mav;
	}

	// 공장 작성후 post로 보내면 여기서 받는다.
	@PostMapping("/updatemanufactory")
	public ModelAndView updateManufactory(ModelAndView mav, ManufactoryVO vo) {
		//이건 10개만 가져와서 오른쪽에 뿌리는것. 
		mav.addObject("ramyunList", ramyunService.getRecentsUpdateListFromDB());
		//우측탭 라면 이미지 리스트 가져오기
		mav.addObject("randomRamyunImageList",ramyunService.getTodaysRamyunImageList());

		// 받은 vo이름으로 업데이트
		manufactoryService.updateManufactory(vo);
		vo = manufactoryService.selectFactoryByName(vo.getFactoryName());

		mav.addObject("manufactory", vo);
		// 일단 홈으로 보냄
		mav.setViewName("manufactory");

		return mav;
	}

	// 공장 정보 편집
	@GetMapping("/editmanufactory")
	public ModelAndView getEditManufactory(ModelAndView mav, String findname) {
		//이건 10개만 가져와서 오른쪽에 뿌리는것. 
		mav.addObject("ramyunList", ramyunService.getRecentsUpdateListFromDB());
		//우측탭 라면 이미지 리스트 가져오기
		mav.addObject("randomRamyunImageList",ramyunService.getTodaysRamyunImageList());

		ManufactoryVO vo = manufactoryService.selectFactoryByNameForEdit(findname);
		mav.addObject("manufactory", vo);
		mav.setViewName("editmanufactory");

		return mav;
	}

	// 태그 탭에 접근
	@GetMapping("/tag")
	public ModelAndView getTag(ModelAndView mav, int page) {
		//이건 10개만 가져와서 오른쪽에 뿌리는것. 
		mav.addObject("ramyunList", ramyunService.getRecentsUpdateListFromDB());
		//우측탭 라면 이미지 리스트 가져오기
		mav.addObject("randomRamyunImageList",ramyunService.getTodaysRamyunImageList());

		// 페이지 넘버를 받아서 해당 vo를 가져온다.
		List<SearchVO> voList = searchService.searchTagPage(page);
		// 해당페이지에 몇개의 페이지 링크가 필요한지 구한다.
		int totalPageCount = searchService.getTotalPageCount();

		// 페이지의 프리뷰와 넥스트가 필요한지 본다.
		boolean hasPrev = searchService.hasPrev(page);
		boolean hasNext = searchService.hasNext(page);
		int currentPageRange = ((page - 1) / 10) * 10;
		// 위는 프레임 레인지를 정한다.
		mav.addObject("totalPageCount", totalPageCount);
		mav.addObject("searchList", voList);
		// 프리뷰 버튼 넣을지 말지 정함
		mav.addObject("currentPageRange", currentPageRange);
		mav.addObject("hasPrev", hasPrev);
		mav.addObject("hasNext", hasNext);
		mav.setViewName("tag");

		return mav;
	}

	
	// 역사버튼 구현
	@GetMapping("/ramyunhistory")
	public ModelAndView getRamyunHistoryByName(ModelAndView mav, String name,int page, int range) {
		//이건 10개만 가져와서 오른쪽에 뿌리는것. 
		mav.addObject("ramyunList", ramyunService.getRecentsUpdateListFromDB());
		//우측탭 라면 이미지 리스트 가져오기
		mav.addObject("randomRamyunImageList",ramyunService.getTodaysRamyunImageList());

		//처음엔 페이지1, 레인지 1로 가져온다.총 갯수도. 나중엔 그 값을 변경하고
		pagenation.setListSize(60);//페이지당 리스트 갯수를 정한다. 필요한 페이지마다 정해줄것
		pagenation.pageInfo(page, range, ramyunHistoryService.getRamyunHistroyCount(name));
		
		
		//제대로 작동한다. 가져올 히스토리 갯수를 정확하게 해야 제대로 작동한다.
		List<RamyunHistoryVO> voList = ramyunHistoryService.getHistoryByName(name, pagenation.getStartList(), pagenation.getListSize());//시작번호부터 끝까지.
		
		mav.addObject("ramyunName", name);//주소용으로 하나 추가한것
		mav.addObject("pagenation", pagenation);
		mav.addObject("ramyunHistoryList", voList);
		mav.setViewName("ramyunhistory");
		return mav;
	}
	
	
	//멤버가 기여한 리스트 가져오기, 닉네임,회원번호로 검색
	@GetMapping("/member/contributions/{memberNumber}/{membernickname}")
	public ModelAndView getContributionListByMemberNumber(ModelAndView mav,int page, int range,@PathVariable("memberNumber")String memberNumber, @PathVariable("membernickname")String memberNickName, HttpSession session) {
		//이건 10개만 가져와서 오른쪽에 뿌리는것. 
		mav.addObject("ramyunList", ramyunService.getRecentsUpdateListFromDB());
		//우측탭 라면 이미지 리스트 가져오기
		mav.addObject("randomRamyunImageList",ramyunService.getTodaysRamyunImageList());
		
		
		//처음엔 페이지1, 레인지 1로 가져온다.총 갯수도. 나중엔 그 값을 변경하고
		pagenation.setListSize(60);//페이지당 리스트 갯수를 정한다. 필요한 페이지마다 정해줄것
		pagenation.pageInfo(page, range, ramyunHistoryService.getContributionCountByWriterMemberNumber(memberNumber));
		System.out.println(ramyunHistoryService.getContributionCountByWriterMemberNumber(memberNumber));
		
		List<RamyunHistoryVO> voList = ramyunHistoryService.getHistoryByWriterMemberNumber(memberNumber, pagenation.getStartList(), pagenation.getListSize());//시작번호부터 끝까지.
		mav.addObject("memberNickname", session.getAttribute("memberNickname"));//주소용으로 추가
		mav.addObject("pagenation", pagenation);
		mav.addObject("ramyunHistoryList", voList);
		mav.setViewName("contributionbynickname");
		return mav;
	}
	
	

	
	// 역사탭에서 보기구현
	@GetMapping("/ramyunlog")
	public ModelAndView getRamyunHistoryById(ModelAndView mav, String id) {
		//이건 10개만 가져와서 오른쪽에 뿌리는것. 
		mav.addObject("ramyunList", ramyunService.getRecentsUpdateListFromDB());
		//우측탭 라면 이미지 리스트 가져오기
		mav.addObject("randomRamyunImageList",ramyunService.getTodaysRamyunImageList());

		RamyunHistoryVO vo = ramyunHistoryService.getHistoryById(id);
		mav.addObject("ramyun", vo);
		mav.setViewName("ramyunlogview");
		return mav;
	}
	
	
	// 역사탭에서 보기구현 원문으로 보기
	@GetMapping("/ramyunlograw")
	public ModelAndView getRamyunHistoryRawById(ModelAndView mav, String id) {
		//이건 10개만 가져와서 오른쪽에 뿌리는것. 
		mav.addObject("ramyunList", ramyunService.getRecentsUpdateListFromDB());
		//우측탭 라면 이미지 리스트 가져오기
		mav.addObject("randomRamyunImageList",ramyunService.getTodaysRamyunImageList());

		RamyunHistoryVO vo = ramyunHistoryService.getHistoryByIdAsRaw(id);
		mav.addObject("ramyun", vo);
		mav.setViewName("ramyunlogview");
		return mav;
	}
}
