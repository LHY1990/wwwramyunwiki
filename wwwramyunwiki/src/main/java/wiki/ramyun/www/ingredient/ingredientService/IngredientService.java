package wiki.ramyun.www.ingredient.ingredientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wiki.ramyun.www.ingredient.IngredientVO;
import wiki.ramyun.www.ingredient.serviceImplement.IngredientDAO;

@Service
public class IngredientService {

	
	@Autowired
	private IngredientDAO dao;

	public IngredientVO getRecentOne() {
		// 가장 최근에 수정된 영양성분을 가져온다.
		return dao.getRecentsOne();
	}

	public IngredientVO selectIngredientByName(String findname) {
		// 이름으로 하나 선택해서 가져온다
		return dao.selectIngredientByName(findname);
	}

	public void updateIngredient(IngredientVO vo) {
		// 영양정보를 업데이트한다.
		dao.updateIngredient(vo);
		
	}
	
	
}
