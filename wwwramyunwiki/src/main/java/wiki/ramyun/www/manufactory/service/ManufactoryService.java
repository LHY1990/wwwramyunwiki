package wiki.ramyun.www.manufactory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wiki.ramyun.www.manufactory.ManufactoryVO;
import wiki.ramyun.www.manufactory.dao.ManufactoryDAO;
import wiki.ramyun.www.wikistringresolver.WikiStringResolver;

@Service
public class ManufactoryService {

	
	@Autowired
	private ManufactoryDAO dao;


	// 이름으로 하나 선택하기 서비스 레벨에서 조회할때 위키 내용을 변환한다.
	public ManufactoryVO selectFactoryByName(String name) {
		ManufactoryVO vo=dao.selectByName(name);
		vo.setDescription(WikiStringResolver.encodeContents(vo.getDescription()));
		return vo;
	}
	
	
	// 이름으로 하나 선택하되 작성용으로 한다. 이땐 위키 리졸버를 쓰지 않는다.
	public ManufactoryVO selectFactoryByNameForEdit(String name) {
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
		ManufactoryVO vo=dao.selectRecentOne();
		//서비스 레벨에서 조회할때 위키 내용을 변환한다.
		vo.setDescription(WikiStringResolver.encodeContents(vo.getDescription()));
		return vo;
	}
	
	//랜덤으로 하나 불러온다. 홈탭에서 제조공장 선택시 작용
	public ManufactoryVO getRandomOne() {
		ManufactoryVO vo=dao.getRandomOne();
		//서비스 레벨에서 조회할때 위키 내용을 변환한다.
		vo.setDescription(WikiStringResolver.encodeContents(vo.getDescription()));
		return vo;
	}
}
