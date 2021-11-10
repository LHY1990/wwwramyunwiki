package wiki.ramyun.www.manufactory.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import wiki.ramyun.www.manufactory.ManufactoryVO;
import wiki.ramyun.www.manufactory.mapper.ManufactoryMapper;

@Component
public class ManufactoryDAO {
	
	@Autowired
	@Qualifier("manufactoryMapper")
	ManufactoryMapper mapper;

	
	//최근 항목 1개를 가져온다.
	public ManufactoryVO selectRecentOne() {
		return mapper.selectRecentOne();
	}

	
	//이름으로 하나 선택한다.
	public ManufactoryVO selectByName(String name) {
		return mapper.selectByName(name);
	}

	
	//업데이트한다.
	public void updateManufactory(ManufactoryVO vo) {
		System.out.println(vo.getDescription());
		System.out.println(vo.getAdress());
		System.out.println(vo.getCorporateName());
		System.out.println(vo.getIdentifyLetter());
		System.out.println(vo.getItemReportNumber());
		System.out.println(vo.getFactoryName());
		
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
		return mapper.selectRandomOne();
	}
}
