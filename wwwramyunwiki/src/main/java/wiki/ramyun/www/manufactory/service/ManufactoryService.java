package wiki.ramyun.www.manufactory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wiki.ramyun.www.manufactory.ManufactoryVO;
import wiki.ramyun.www.manufactory.dao.ManufactoryDAO;

@Service
public class ManufactoryService {

	
	@Autowired
	private ManufactoryDAO dao;


	// 이름으로 하나 선택하기
	public ManufactoryVO selectFactoryByName(String name) {
		return dao.selectByName(name);
	}

	
	// 업데이트한다. 
	public void updateManufactory(ManufactoryVO vo) {
		dao.updateManufactory(vo);
		
	}

	
	// 중복체크 후 넣는다.
	public String insertManufactoryToDB(String register) {
		if(dao.isUnique(register)){
			dao.insertManufactory(register);
			return "success";
		}
		return "fail";
	}

	
	// 최근 업데이트 분 가져오기
	public ManufactoryVO getRecentOne() {
		return dao.selectRecentOne();
	}
	
	
	public ManufactoryVO getRandomOne() {
		return dao.getRandomOne();
	}
}
