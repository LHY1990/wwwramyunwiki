package wiki.ramyun.www.manufactory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wiki.ramyun.www.manufactory.ManufactoryVO;
import wiki.ramyun.www.manufactory.dao.ManufactoryDAO;

@Service
public class ManufactoryService {

	@Autowired
	private ManufactoryDAO dao;

	public ManufactoryVO getRecentOne() {
		// 최근 업데이트 분 가져오기
		return dao.selectRecentOne();
	}
}
