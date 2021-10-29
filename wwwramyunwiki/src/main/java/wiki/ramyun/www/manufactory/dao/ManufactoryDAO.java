package wiki.ramyun.www.manufactory.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import wiki.ramyun.www.manufactory.ManufactoryVO;
import wiki.ramyun.www.manufactory.mapper.ManufactoryMapper;
import wiki.ramyun.www.member.mapper.MemberMapper;

@Component
public class ManufactoryDAO {
	
	@Autowired
	@Qualifier("manufactoryMapper")
	ManufactoryMapper mapper;

	public ManufactoryVO selectRecentOne() {
		// TODO Auto-generated method stub
		return mapper.selectRecentOne();
	}
}
