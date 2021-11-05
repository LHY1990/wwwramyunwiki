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

	public boolean isUnique(String register) {
		// 중복 등록을 막기위해 기존 디비에 연결할수있나확인
		int count=0;
		count=mapper.countOf(register);
		if(count==0) {
			return true;
		}else if(count==1){
			return false;
		}else {
			System.out.println("0도 아니고 1도 아니다 에러찾기");
			return false;
		}
	}

	public void insertIngredient(String register) {
		// 등록
		mapper.insertIngredient(register);
	}
	
	
}
