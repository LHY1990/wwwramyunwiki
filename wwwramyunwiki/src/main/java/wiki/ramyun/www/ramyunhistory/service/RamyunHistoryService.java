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

	public List<RamyunHistoryVO> getHistoryByName(String name) {
		//이름으로 라면의 히스토리 가져오기
		return dao.getRamyunHistoryByName(name);
	}

	public void updateRamyunHistoryToDB(RamyunVO vo, String writer) {
		dao.updateRamyunHistory(vo, writer);
		
	}
	
	

}
