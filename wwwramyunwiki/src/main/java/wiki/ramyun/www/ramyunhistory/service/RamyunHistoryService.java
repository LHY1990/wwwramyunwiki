package wiki.ramyun.www.ramyunhistory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import wiki.ramyun.www.ramyun.RamyunVO;
import wiki.ramyun.www.ramyunhistory.RamyunHistoryVO;
import wiki.ramyun.www.ramyunhistory.dao.RamyunHistoryDAO;
import wiki.ramyun.www.seconderrorhandler.SecondErrorHandler;
import wiki.ramyun.www.wikistringresolver.WikiStringResolver;

@Service
public class RamyunHistoryService {
	
	@Autowired
	private RamyunHistoryDAO dao;

	@Autowired
	@Qualifier("secondErrorHandler")
	SecondErrorHandler secondErrorHandler;
	
	//이름으로 라면의 히스토리 가져오기
	public List<RamyunHistoryVO> getHistoryByName(String name) {
		return dao.getRamyunHistoryByName(name);
	}
	
	
	//오버로딩, 페이지네이션을 추가해서 가져온다.
	public List<RamyunHistoryVO> getHistoryByName(String name, int startPage, int endPage) {
		return dao.getRamyunHistoryByName(name,startPage,endPage);
	}

	
	public void updateRamyunHistoryToDB(RamyunVO vo, String writer, String writerMemberNumber) {
		dao.updateRamyunHistory(vo, writer, writerMemberNumber);
	}

	
	//아이디로 라면을 검색해서 반환한다.
	public RamyunHistoryVO getHistoryById(String id) {
		RamyunHistoryVO vo =dao.getRamyunHistoryById(id);
		
		try {
			//서비스레벨에서 조회할때 위키로 내용을 변환한다.
			vo.setUserEditedContents(WikiStringResolver.encodeContents(vo.getUserEditedContents()));
		} catch (Exception e) {
			System.out.println("RamyunHistoryService의 getHistoryById에서 WikiStringResolver에서 예외발생");
		}
		
		return vo;
	}
	
	//아이디로 라면을 검색해서 반환한다. RAW로 보기에 컨버팅은 없다
	public RamyunHistoryVO getHistoryByIdAsRaw(String id) {
		RamyunHistoryVO vo =dao.getRamyunHistoryById(id);
		
		return vo;
	}


	public List<RamyunHistoryVO> selectAllFromRamyunHistoryDB() {
		return dao.selectAllFromRamyunHistoryDB();
	}


	
	public void deleteRamyunHistoryById(String id) {
		dao.deleteRamyunHistoryById(id);
	}


	public int getRamyunHistroyCount(String name) {
		return dao.getRamyunHistoryCount(name);
	}


	public List<RamyunHistoryVO> selectRamyunHistoryByRange(int startList, int listSize) {
		return dao.selectRamyunHistoryByRange(startList, listSize);
	}


	public int getRamyunHistoryCount() {
		return dao.getRamyunHistoryCount();
	}

	//닉네임으로 기여횟수를 가져온다. 
	public int getContributionCountByNickname(String nickname) {
		return dao.getContributionCountByNickname(nickname);
	}

	//작성자 멤버 넘버로 기여횟수를 가져온다.
	public int getContributionCountByWriterMemberNumber(String writerMemberNumber) {
		return dao.getContributionCountByWriterMemberNumber(writerMemberNumber);
	}

	public List<RamyunHistoryVO> getHistoryByNickname(String memberNickName, int startList, int listSize) {
		return dao.getHistoryByNickname(memberNickName,startList, listSize);
	}


	public List<RamyunHistoryVO> getHistoryByWriterMemberNumber(String writerMemberNumber, int startList,	int listSize) {
		return dao.getHistoryByWriterMemberNumber(writerMemberNumber,startList, listSize);
	}




	
	
	

}
