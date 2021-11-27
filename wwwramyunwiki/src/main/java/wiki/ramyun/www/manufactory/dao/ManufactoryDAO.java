package wiki.ramyun.www.manufactory.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import wiki.ramyun.www.ingredient.IngredientVO;
import wiki.ramyun.www.manufactory.ManufactoryVO;
import wiki.ramyun.www.manufactory.mapper.ManufactoryMapper;
import wiki.ramyun.www.seconderrorhandler.SecondErrorHandler;

@Component
public class ManufactoryDAO {
	
	@Autowired
	@Qualifier("manufactoryMapper")
	ManufactoryMapper mapper;

	@Autowired
	@Qualifier("secondErrorHandler")
	SecondErrorHandler secondErrorHandler;
	
	//최근 항목 1개를 가져온다.
	public ManufactoryVO selectRecentOne() {
		
		ManufactoryVO vo=mapper.selectRecentOne();
		vo.setUpdatedDate(secondErrorHandler.checkSecond(vo.getUpdatedDate()));
		
		return vo;
	}

	
	//이름으로 하나 선택한다.
	public ManufactoryVO selectByName(String name) {
		
		ManufactoryVO vo=mapper.selectByName(name);
		try {
			vo.setUpdatedDate(secondErrorHandler.checkSecond(vo.getUpdatedDate()));
		} catch (Exception e) {
			System.out.println("ManufactoryDAO의 selectByName에서 예외발생. 검색어 결과 없음");
		}
		
		return vo;
	}

	
	//업데이트한다.
	public void updateManufactory(ManufactoryVO vo) {
		
		mapper.updateManufactory(vo.getCorporateName(), vo.getAdress(), vo.getIdentifyLetter(),vo.getItemReportNumber(), vo.getDescription(), vo.getFactoryName());
	}

	
	// 중복 등록을 막는다.
	public boolean isUnique(String register) {
		int count=0;
		count=mapper.countOf(register);
		if(count==0) {
			return true;
		}else if(count==1){
			return false;
		}else {
			System.out.println("0도 아니고 1도 아니다 에러찾기");
			return false;
		}
	}

	
	// 등록하기
	public void insertManufactory(String register) {
		mapper.insertManufactory(register);
		
	}

	
	//무작위로 하나 가져오기
	public ManufactoryVO getRandomOne() {
		
		ManufactoryVO vo=mapper.selectRandomOne();
		//00초로 끝나는 경우 1초를 더해준다.
		vo.setUpdatedDate(secondErrorHandler.checkSecond(vo.getUpdatedDate()));
		return vo;
	}


	//관리자 접근, 모두 불러온다.날짜도 수정해준다.
	public List<ManufactoryVO> selectAllFromManufactory() {
		List<ManufactoryVO> list=new ArrayList<ManufactoryVO>();
		
		for(ManufactoryVO vo : mapper.selectAllFromManufactory()) {
			vo.setUpdatedDate(secondErrorHandler.checkSecond(vo.getUpdatedDate()));
			list.add(vo);
		}
		
		
		return list;
	}


	public void deleteManufactoryByName(String name) {
		mapper.deleteManufactoryByName(name);
	}
}
