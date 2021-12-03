package wiki.ramyun.www.ramyun.dao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import wiki.ramyun.www.ramyun.RamyunVO;
import wiki.ramyun.www.ramyun.mapper.RamyunMapper;
import wiki.ramyun.www.seconderrorhandler.SecondErrorHandler;

@Component
public class RamyunDAO {
	
	@Autowired
	@Qualifier("ramyunMapper")
	private RamyunMapper mapper;
	
	@Autowired
	@Qualifier("secondErrorHandler")
	SecondErrorHandler secondErrorHandler;
	
	
	
	
	//새로운 라면넣기 
	public void insertRamyun(String newRamyun) {
		//중복라면있으면 에러나니까 있는지 확인할것
		mapper.insertRamyun(newRamyun);
	}
	
	
	//기존 라면업데이트 하기
	public void updateRamyun(RamyunVO vo) {
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
		//마지막에 한국이름 명을 넣어야 where절이 완성되는 구조
	}


	public RamyunVO selectRamyun(String searchBoxInput){
		RamyunVO vo=null;
		try {
			vo=mapper.selectRamyunFromDB(searchBoxInput);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//00초로 끝나는 경우 1초를 더해준다.
		try {
			vo.setUpdatedDate(secondErrorHandler.checkSecond(vo.getUpdatedDate()));
		}catch(Exception e){
			System.out.println("ramyundao의 selectRamyun에서 에러");
		}
		
		return vo;
	}


	//빈 리스트를 만들어서 00초로 끝나는 라면은 1초로 만들어준다.
	public List<RamyunVO> getRecentsUpdateList() {
		List<RamyunVO> list=new ArrayList<RamyunVO>();
		
		for(RamyunVO vo : mapper.getRecentsUpdateList()) {
			vo.setUpdatedDate(secondErrorHandler.checkSecond(vo.getUpdatedDate()));
			list.add(vo);
		}
		
		return list;
	}


	public List<RamyunVO> getRecentsUpdateListWhole() {
		List<RamyunVO> list=new ArrayList<RamyunVO>();
		
		for(RamyunVO vo : mapper.getRecentsUpdateListWhole()) {
			vo.setUpdatedDate(secondErrorHandler.checkSecond(vo.getUpdatedDate()));
			list.add(vo);
		}
		
		return list;
	}


	public int getRamyunCount() {
		return mapper.getRamyunCount();
	}


	public boolean isUnique(String newRamyunName) {
		// 중복 등록을 막기위해 기존 디비에 연결할수있나확인
		int count=0;
		count=mapper.countOf(newRamyunName);
		if(count==0) {
			return true;
		}else if(count==1){
			return false;
		}else {
			System.out.println("0도 아니고 1도 아니다 에러찾기");
			return false;
		}
		
	}


	//모든 라면을 불러온다. 날짜문제도 해결한다. 
	public List<RamyunVO> selectAllFromRamyun() {
		List<RamyunVO> list=new ArrayList<RamyunVO>();
		
		for(RamyunVO vo : mapper.selectAllFromRamyun()) {
			vo.setUpdatedDate(secondErrorHandler.checkSecond(vo.getUpdatedDate()));
			list.add(vo);
		}
		
		return list;
		
		
	}


	public void deleteRamyunByName(String name) {
		mapper.deleteRamyunByName(name);
	}


	//우측 탭 아래 이미지에 뿌리는 이미지 랜덤으로 가져온다
	public List<RamyunVO> getTodaysRamyunImage() {
		List<RamyunVO> list=new ArrayList<RamyunVO>();
		
		for(RamyunVO vo : mapper.selectAllFromRamyunByRandom()) {
			vo.setUpdatedDate(secondErrorHandler.checkSecond(vo.getUpdatedDate()));
			list.add(vo);
		}
		
		return list;

	}


	//랜덤으로 라면 하나를 가져온다. 날짜 처리도한다.
	public RamyunVO selectRandomRamyun() {
		RamyunVO vo=null;
		try {
			vo=mapper.selectRandomRamyun();
		} catch (Exception e) {e.printStackTrace();}
		
		//00초로 끝나는 경우 1초를 더해준다.
		try {
			vo.setUpdatedDate(secondErrorHandler.checkSecond(vo.getUpdatedDate()));
		}catch(Exception e){	e.printStackTrace();}
		
		return vo;
	}


	//범위로 가져온다.
	public List<RamyunVO> selectRamyunByRange(int startList, int listSize) {
		List<RamyunVO> list=new ArrayList<RamyunVO>();
		
		for(RamyunVO vo : mapper.selectRamyunByRange(startList, listSize)) {
			vo.setUpdatedDate(secondErrorHandler.checkSecond(vo.getUpdatedDate()));
			list.add(vo);
		}
		
		return list;
	}
}
