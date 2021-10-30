package wiki.ramyun.www.search.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import wiki.ramyun.www.search.SearchVO;
import wiki.ramyun.www.search.mapper.SearchMapper;

@Service
public class SearchService {
	
	@Autowired
	@Qualifier("searchMapper")
	SearchMapper mapper;
	
	public List<SearchVO> searchRecentUpdated(){
		return mapper.searchRecentUpdated();
	}

	public List<SearchVO> searchTagPage(int page) {
		
		
		//전체 게시글을 받는다.
		int totalContentsCount=mapper.getTotalCountOfAll();
		System.out.println(totalContentsCount);

		//1페이지면 0부터 150까지 받고
		//2페이지면 150부터 300까지 받는다.
		int startPage = (page-1)*150;
		int endPage = page*150; 
		
		//지정된 페이지에 맞춰서 해당되는 정보를 넘긴다
		List<SearchVO> searchList= mapper.selectLimitedSearchs(startPage, endPage);
	
		
		
		return searchList;
	}

	public int getTotalPageCount() {
		
		//전체 페이지 갯수를 가져온다.
		int totalContentsCount=mapper.getTotalCountOfAll();
		System.out.println(totalContentsCount);
		//링크페이지를 몇개나 보낼지 정한다.
		//한페이지에 150개를 보여주니 totalContentsCount/150 그리고 + 1이다.
		int totalPageCount=(totalContentsCount/150)+1;
		
		
		
		
		return totalPageCount;
	}

	public boolean hasPrev(int page) {
		//한번에 보여주는 페이지가 10개일때
		//10보다 현재 페이지가 크면 프리뷰가 필요하다.
		
		
		if(page>10) {
			return true;
		}
		
		return false;
	}

	public boolean hasNext(int page) {
		//현재페이지보다 전체 페이지가 크면 다음이 필요하다.
		
		//전체 페이지 갯수를 가져온다.
		int totalContentsCount=mapper.getTotalCountOfAll();
		//링크페이지를 몇개나 보낼지 정한다.
		//한페이지에 150개를 보여주니 totalContentsCount/150 그리고 + 1이다.
		int totalPageCount=(totalContentsCount/150)+1;
		
		//현재 있는 단위 12이면 10~20이고 4이면 1~10인데 33이면 30~40이지
		
		int tempPage=((int)(page/10))*10+10;		
		if(tempPage<=totalPageCount) {
			return true;
		}
		
		return false;
	}

	public List<String> getSimilars(String name) {
		
		List<String> similarList=new ArrayList<String>();
		List<SearchVO> searchList= mapper.searchRecentUpdated();
		
		for(SearchVO temp : searchList) {
			if(temp.getName().contains(name)) {
				similarList.add(temp.getName());
			}
			
		}
		
		
		
		
		return similarList;
	}

	
}
