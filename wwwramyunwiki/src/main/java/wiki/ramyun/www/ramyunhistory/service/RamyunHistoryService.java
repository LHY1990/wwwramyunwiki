package wiki.ramyun.www.ramyunhistory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wiki.ramyun.www.ramyun.RamyunVO;
import wiki.ramyun.www.ramyunhistory.RamyunHistoryVO;
import wiki.ramyun.www.ramyunhistory.dao.RamyunHistoryDAO;

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
		return dao.getRamyunHistoryById(id);
	}
	
	

}
