package wiki.ramyun.www.ramyun.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import wiki.ramyun.www.ramyun.RamyunVO;
import wiki.ramyun.www.ramyun.mapper.RamyunMapper;

@Component
public class RamyunDAO {
	
	@Autowired
	@Qualifier("ramyunMapper")
	RamyunMapper mapper;
	
	
	//새로운 라면넣기 5레벨이상
	public void insertRamyun(String newRamyun) {
		System.out.println("라면 디비에 넣기");
//		중복라면있으면 에러나니까 있는지 확인할것
		mapper.insertRamyun(newRamyun);
	}
	
	
	//기존 라면업데이트 하기
	public void updateRamyun(RamyunVO vo) {
		System.out.println("라면 업데이트 시작함");
		mapper.updateRamyun(vo.getbrandNameEng(), 
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
				vo.getbrandNameKor());
		System.out.println("라면 업데이트 끝남");
		//마지막에 한국이름 명을 넣어야 where절이 완성되는 구조
	}


	public RamyunVO selectRamyun(String searchBoxInput){
		RamyunVO vo=null;
		try {
			vo=mapper.selectRamyunFromDB(searchBoxInput);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return vo;
		
		
	}


	public List<RamyunVO> getRecentsUpdateList() {
		List<RamyunVO> list=mapper.getRecentsUpdateList();
		return list;
	}


	public List<RamyunVO> getRecentsUpdateListWhole() {
		List<RamyunVO> list=mapper.getRecentsUpdateListWhole();
		return list;
	}


	public int getRamyunCount() {
		// TODO Auto-generated method stub
		return mapper.getRamyunCount();
	}
}
