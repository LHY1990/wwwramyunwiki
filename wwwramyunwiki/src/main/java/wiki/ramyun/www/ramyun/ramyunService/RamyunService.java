package wiki.ramyun.www.ramyun.ramyunService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wiki.ramyun.www.ramyun.RamyunVO;
import wiki.ramyun.www.ramyun.serviceImplement.RamyunDAO;

@Service
public class RamyunService {

	@Autowired
	private RamyunDAO dao;

	
	
	
	//회원등급 5등급 이상일때 가능하다
	public void insertRamyunToDB(String newRamyunName) {
		
		//새라면을 만든다.기존 라면에 중복이 있는지 확인하자
		
		dao.insertRamyun(newRamyunName);
	}
	
	//기본적인 회원이 라면 정보를 업데이트 하는경우
	public void updateRamyunFromDB(RamyunVO vo) {
		dao.updateRamyun(vo);
	}

	public RamyunVO getRamyunData(String searchBoxInput){
		RamyunVO vo=dao.selectRamyun(searchBoxInput);
		
		return vo;
		
	}

	public List<RamyunVO> getRecentsUpdateListFromDB() {
		
		return dao.getRecentsUpdateList();
	}

	public List<RamyunVO> getRecentsUpdateListFromDBWhole() {
		// TODO Auto-generated method stub
		return dao.getRecentsUpdateListWhole();
	}
}
