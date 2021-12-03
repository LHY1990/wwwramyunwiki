package wiki.ramyun.www.ramyunhistory.dao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import wiki.ramyun.www.ramyun.RamyunVO;
import wiki.ramyun.www.ramyunhistory.RamyunHistoryVO;
import wiki.ramyun.www.ramyunhistory.mapper.RamyunHistoryMapper;
import wiki.ramyun.www.seconderrorhandler.SecondErrorHandler;

@Component
public class RamyunHistoryDAO {

	@Autowired
	@Qualifier("ramyunHistoryMapper")
	private RamyunHistoryMapper mapper;

	@Autowired
	@Qualifier("secondErrorHandler")
	SecondErrorHandler secondErrorHandler;
	
	// 이름으로 라면 히스토리 리스트를 가져온다. 00초로 끝나는 경우 1초를 더해준다.
	public List<RamyunHistoryVO> getRamyunHistoryByName(String name) {
		
		List<RamyunHistoryVO> list=new ArrayList<RamyunHistoryVO>();
		
		for(RamyunHistoryVO vo : mapper.selectHistoryByName(name)) {
			vo.setUpdatedDate(secondErrorHandler.checkSecond(vo.getUpdatedDate()));
			list.add(vo);
		}
		
		return list;
	}
	
	
	//getRamyunHistoryByName의 오버로딩. 범위를 정해서 가져온다.
	public List<RamyunHistoryVO> getRamyunHistoryByName(String name, int startPage, int endPage) {
		List<RamyunHistoryVO> list=new ArrayList<RamyunHistoryVO>();
		
		for(RamyunHistoryVO vo : mapper.selectPageOfHistoryByName(name, startPage, endPage)) {
			vo.setUpdatedDate(secondErrorHandler.checkSecond(vo.getUpdatedDate()));
			list.add(vo);
		}
		
		
		return list;
	}
	
	
	// 라면업데이트와 동시에 라면히스토리에 같은내용+작성자 저장하기
	public void updateRamyunHistory(RamyunVO vo, String writer) {
		mapper.insertRamyunHistory(
				vo.getbrandNameKor(),
				vo.getbrandNameEng(), 
				LocalDateTime.now(), 
				vo.getCorporateName(), 
				vo.getDevelopedDate(), 
				vo.getWeight(), 
				vo.getCalorie(), 
				vo.getScovilleUnit(),
				vo.getFoodCategory(), 
				vo.getRecipe(), 
				vo.getBarcode(), 
				vo.getNoodleShape(), 
				vo.getSoupComposition(), 
				vo.getDiscontinuance(), 
				vo.getRelatedRamyun(), 
				vo.getWaterCapacityByNumber(), 
				vo.getMaterialList(), 
				vo.getItemReportNumber(), 
				vo.getExpirationDate(), 
				vo.getSoupPosition(), 
				vo.getNatrium(), 
				vo.getCarbohydrate(), 
				vo.getSugars(), 
				vo.getFat(), 
				vo.getTransfat(), 
				vo.getSaturatedfat(), 
				vo.getCholesterol(), 
				vo.getProtein(), 
				vo.getCalcium(), 
				vo.getImage(), 
				vo.getUserEditedContents(), 
				writer
				);
	}


	//아이디로 히스토리를 선택해서 가져온다
	public RamyunHistoryVO getRamyunHistoryById(String id) {
		RamyunHistoryVO vo=mapper.selectHistoryById(id);
		
		vo.setUpdatedDate(secondErrorHandler.checkSecond(vo.getUpdatedDate()));
		
		return vo;
	}


	public List<RamyunHistoryVO> selectAllFromRamyunHistoryDB() {
		return mapper.selectAllFromRamyunHistoryDB();
	}


	public void deleteRamyunHistoryById(String id) {
		mapper.deleteRamyunHistoryById(id);
		
	}

	
	public int getRamyunHistoryCount() {
		return mapper.getRamyunHistoryCount();
	}
	
	//오버로딩
	public int getRamyunHistoryCount(String name) {
		return mapper.countAllRamyunHistory(name);
	}


	public List<RamyunHistoryVO> selectRamyunHistoryByRange(int startList, int listSize) {
		List<RamyunHistoryVO> list=new ArrayList<RamyunHistoryVO>();
		
		for(RamyunHistoryVO vo : mapper.selectRamyunHistoryByRange(startList, listSize)) {
			vo.setUpdatedDate(secondErrorHandler.checkSecond(vo.getUpdatedDate()));
			list.add(vo);
		}
		
		return list;
	}


	public int getContributionCountByNickname(String nickname) {
		return mapper.getContributionCountByNickname(nickname);
	}


	//닉네임으로 검색해서 가져오는데 이때 초단위가 0이면 1로 변경해서 문제를 없앤다.	
	public List<RamyunHistoryVO> getHistoryByNickname(String memberNickName, int startList, int listSize) {
		List<RamyunHistoryVO> list=new ArrayList<RamyunHistoryVO>();
		
		for(RamyunHistoryVO vo : mapper.getHistoryByNickname(memberNickName, startList, listSize)) {
			vo.setUpdatedDate(secondErrorHandler.checkSecond(vo.getUpdatedDate()));
			list.add(vo);
		}
		
		return list;
		
	}


	


	
	
}
