package wiki.ramyun.www.manufactory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wiki.ramyun.www.manufactory.ManufactoryVO;
import wiki.ramyun.www.manufactory.dao.ManufactoryDAO;

@Service
public class ManufactoryService {

	@Autowired
	private ManufactoryDAO dao;

	public ManufactoryVO getRecentOne() {
		// 최근 업데이트 분 가져오기
		return dao.selectRecentOne();
	}

	public ManufactoryVO selectFactoryByName(String name) {
		// 이름으로 하나 선택하기
		return dao.selectByName(name);
	}

	public void updateManufactory(ManufactoryVO vo) {
		// 업데이트한다. 
		dao.updateManufactory(vo);
		
	}

	public String insertManufactoryToDB(String register) {
		// 중복체크 후 넣는다.
		if(dao.isUnique(register)){
			dao.insertManufactory(register);
			return "success";
		}
		return "fail";
	}
}
