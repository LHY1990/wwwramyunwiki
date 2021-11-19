package wiki.ramyun.www.ramyun.service;

import java.util.ArrayList;
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
	
	
	//관리자 창에서 보기위해 모두 가져온다
	public List<RamyunVO> selectAllFromRamyunDB(){
		return dao.selectAllFromRamyun();
	}


	public void deleteRamyunByName(String name) {
		dao.deleteRamyunByName(name);
	}


	//임의의 라면 이미지를 가져온다.
	public String getTodaysRamyunImage() {
		List<RamyunVO> list=new ArrayList<RamyunVO>();
		
		List<RamyunVO> voList =dao.getTodaysRamyunImage();
		
		for(RamyunVO tempVO : voList) {
			if(tempVO.getImage()!=null) {
				list.add(tempVO);
			}
		}
		//3개면 크기는 3이 나올거고 인덱스는 0~2
		//추출된 리스트의 사이즈의 사이즈로 랜덤을 만들어서 그중하나를 보낸다.
		int output=(int)(Math.random()*list.size());
		
		return list.get(output).getbrandNameKor();
		
	}


	
}
