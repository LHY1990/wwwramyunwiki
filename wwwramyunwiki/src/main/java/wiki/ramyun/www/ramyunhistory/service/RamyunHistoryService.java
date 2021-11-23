package wiki.ramyun.www.ramyunhistory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wiki.ramyun.www.ramyun.RamyunVO;
import wiki.ramyun.www.ramyunhistory.RamyunHistoryVO;
import wiki.ramyun.www.ramyunhistory.dao.RamyunHistoryDAO;
import wiki.ramyun.www.wikistringresolver.WikiStringResolver;

@Service
public class RamyunHistoryService {
	
	@Autowired
	private RamyunHistoryDAO dao;

	
	//이름으로 라면의 히스토리 가져오기
	public List<RamyunHistoryVO> getHistoryByName(String name) {
		return dao.getRamyunHistoryByName(name);
	}

	
	public void updateRamyunHistoryToDB(RamyunVO vo, String writer) {
		dao.updateRamyunHistory(vo, writer);
	}

	
	//아이디로 라면을 검색해서 반환한다.
	public RamyunHistoryVO getHistoryById(String id) {
		RamyunHistoryVO vo =dao.getRamyunHistoryById(id);
		//서비스레벨에서 조회할때 위키로 내용을 변환한다.
		vo.setUserEditedContents(WikiStringResolver.encodeContents(vo.getUserEditedContents()));
		
		return vo;
	}


	public List<RamyunHistoryVO> selectAllFromRamyunHistoryDB() {
		return dao.selectAllFromRamyunHistoryDB();
	}


	
	public void deleteRamyunHistoryById(String id) {
		dao.deleteRamyunHistoryById(id);
	}
	
	

}
