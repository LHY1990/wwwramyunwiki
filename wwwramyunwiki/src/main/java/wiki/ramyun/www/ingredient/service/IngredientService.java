package wiki.ramyun.www.ingredient.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wiki.ramyun.www.ingredient.IngredientVO;
import wiki.ramyun.www.ingredient.dao.IngredientDAO;
import wiki.ramyun.www.wikistringresolver.WikiStringResolver;

@Service
public class IngredientService {

	
	@Autowired
	private IngredientDAO dao;


	// 이름으로 하나 선택해서 가져온다
	public IngredientVO selectIngredientByName(String findname) {
		IngredientVO vo =dao.selectIngredientByName(findname);
		vo.setDescription(WikiStringResolver.encodeContents(vo.getDescription()));
		return vo;
	}
	
	
	//편집을 위해 가져온경우 위키리졸버를 쓰지않는다.
	public IngredientVO selectIngredientByNameForEdit(String findname) {
		return dao.selectIngredientByName(findname);
	}
	
	// 영양정보를 업데이트한다.
	public void updateIngredient(IngredientVO vo) {
		dao.updateIngredient(vo);
		
	}

	
	// 값이 중복이면 넣지않고 중복이 아니면 넣는다.
	public String insertIngredientToDB(String register) {
		if(dao.isUnique(register)){
			dao.insertIngredient(register);
			return "success";
		}
		return "fail";
	}
	
	
	// 가장 최근에 수정된 영양성분을 가져온다.
	public IngredientVO getRecentOne() {
		IngredientVO vo =dao.getRecentsOne();
		vo.setDescription(WikiStringResolver.encodeContents(vo.getDescription()));
		return vo;
	}
	
	
	//무작위로 하나 가져온다
	public IngredientVO getRandomOne() {
		IngredientVO vo =dao.getRandomOne();
		vo.setDescription(WikiStringResolver.encodeContents(vo.getDescription()));
		return vo;
	}


	
	
	
}
