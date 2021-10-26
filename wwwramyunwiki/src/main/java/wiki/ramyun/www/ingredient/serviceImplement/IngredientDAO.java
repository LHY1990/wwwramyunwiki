package wiki.ramyun.www.ingredient.serviceImplement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import wiki.ramyun.www.ingredient.mapper.IngredientMapper;

@Component
public class IngredientDAO {
	
	@Autowired
	@Qualifier("ingredientMapper")
	IngredientMapper mapper;
	
	
}
