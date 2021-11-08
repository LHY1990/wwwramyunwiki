package wiki.ramyun.www.ramyunhistory.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import wiki.ramyun.www.ramyun.RamyunVO;
import wiki.ramyun.www.ramyunhistory.RamyunHistoryVO;
import wiki.ramyun.www.ramyunhistory.mapper.RamyunHistoryMapper;

@Component
public class RamyunHistoryDAO {

	
	@Autowired
	@Qualifier("ramyunHistoryMapper")
	private RamyunHistoryMapper mapper;

	public List<RamyunHistoryVO> getRamyunHistoryByName(String name) {
		// 이름으로 라면 히스토리 리스트를 가져온다
		return mapper.selectHistoryByName(name);
	}

	
	public void updateRamyunHistory(RamyunVO vo, String writer) {
		// 라면업데이트와 동시에 라면히스토리에 같은내용+작성자 저장하기
		System.out.println("라면 히스토리 업데이트 시작함");
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
		System.out.println("라면 업데이트 끝남");
		
		
	}
	
}
