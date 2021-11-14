package wiki.ramyun.www.ramyun.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wiki.ramyun.www.ramyun.RamyunVO;
import wiki.ramyun.www.ramyun.dao.RamyunDAO;
import wiki.ramyun.www.wikistringresolver.WikiStringResolver;

@Service
public class RamyunService {

	@Autowired
	private RamyunDAO dao;
	
	public String insertRamyunToDB(String newRamyunName) {
		//새라면을 만든다.기존 라면에 중복이 있는지 확인하자
		if(dao.isUnique(newRamyunName)){
			dao.insertRamyun(newRamyunName);
			return "success";
		}
		return "fail";
	}
	
	
	//기본적인 회원이 라면 정보를 업데이트 하는경우
	public void updateRamyunToDB(RamyunVO vo) {
		dao.updateRamyun(vo);
	}

	
	public RamyunVO getRamyunData(String searchBoxInput){
		RamyunVO vo=dao.selectRamyun(searchBoxInput);
		vo.setUserEditedContents(WikiStringResolver.encodeContents(vo.getUserEditedContents()));
		return vo;
	}
	
	
	public RamyunVO getRamyunDataForEdit(String name) {
		return dao.selectRamyun(name);
	}
	
	
	public List<RamyunVO> getRecentsUpdateListFromDB() {
		return dao.getRecentsUpdateList();
	}

	
	public List<RamyunVO> getRecentsUpdateListFromDBWhole() {
		return dao.getRecentsUpdateListWhole();
	}

	
	public int getRamyunCount() {
		return dao.getRamyunCount();
	}


	
}
