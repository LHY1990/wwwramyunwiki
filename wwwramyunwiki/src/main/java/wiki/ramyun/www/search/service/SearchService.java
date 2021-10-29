package wiki.ramyun.www.search.service;

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
}
