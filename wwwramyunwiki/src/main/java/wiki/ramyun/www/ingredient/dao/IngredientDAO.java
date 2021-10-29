package wiki.ramyun.www.ingredient.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import wiki.ramyun.www.ingredient.IngredientVO;
import wiki.ramyun.www.ingredient.mapper.IngredientMapper;

@Component
public class IngredientDAO {
	
	@Autowired
	@Qualifier("ingredientMapper")
	IngredientMapper mapper;

	public IngredientVO getRecentsOne() {
		// TODO Auto-generated method stub
		IngredientVO vo=mapper.selectRecentOne();
		return vo;
	}

	public IngredientVO selectIngredientByName(String findname) {
		
		return mapper.selectIngredientByName(findname);
	}

	public void updateIngredient(IngredientVO vo) {
		// vo를 업데이트한다.
		mapper.updateIngredient(vo.getType(), vo.getDescription(), vo.getName());
		
	}
	
	
}
