package wiki.ramyun.www.ingredient.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wiki.ramyun.www.ingredient.IngredientVO;
import wiki.ramyun.www.ingredient.dao.IngredientDAO;

@Service
public class IngredientService {

	
	@Autowired
	private IngredientDAO dao;

	

	public IngredientVO selectIngredientByName(String findname) {
		// 이름으로 하나 선택해서 가져온다
		return dao.selectIngredientByName(findname);
	}

	public void updateIngredient(IngredientVO vo) {
		// 영양정보를 업데이트한다.
		dao.updateIngredient(vo);
		
	}

	public String insertIngredientToDB(String register) {
		// 값이 중복이면 넣지않고 중복이 아니면 넣는다.
		if(dao.isUnique(register)){
			dao.insertIngredient(register);
			return "success";
		}
		return "fail";
	}
	public IngredientVO getRecentOne() {
		// 가장 최근에 수정된 영양성분을 가져온다.
		return dao.getRecentsOne();
	}
	public IngredientVO getRandomOne() {
		// TODO Auto-generated method stub
		return dao.getRandomOne();
	}
	
	
}
