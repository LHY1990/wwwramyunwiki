package wiki.ramyun.www.ingredient.ingredientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wiki.ramyun.www.ingredient.IngredientVO;
import wiki.ramyun.www.ingredient.serviceImplement.IngredientDAO;

@Service
public class IngredientService {

	
	@Autowired
	private IngredientDAO dao;
	
	
}
