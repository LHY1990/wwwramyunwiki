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

	public ManufactoryVO selectByName(String name) {
		// TODO Auto-generated method stub
		return mapper.selectByName(name);
	}

	public void updateManufactory(ManufactoryVO vo) {
		// TODO Auto-generated method stub
		System.out.println("dao까지옴");
		System.out.println(vo.getDescription());
		System.out.println(vo.getAdress());
		System.out.println(vo.getCorporateName());
		System.out.println(vo.getIdentifyLetter());
		System.out.println(vo.getItemReportNumber());
		System.out.println(vo.getFactoryName());
		
		mapper.updateManufactory(vo.getCorporateName(), vo.getAdress(), vo.getIdentifyLetter(),vo.getItemReportNumber(), vo.getDescription(), vo.getFactoryName());
	}
}
