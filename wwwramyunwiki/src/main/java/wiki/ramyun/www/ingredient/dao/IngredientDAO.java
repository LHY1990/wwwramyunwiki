package wiki.ramyun.www.ingredient.dao;

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

	
	//최근 영양성분 하나를 보낸다.
	public IngredientVO getRecentsOne() {
		IngredientVO vo=mapper.selectRecentOne();
		
		return vo;
	}
	
	
	//새롭게 등록
	public void insertIngredient(String register) {
		mapper.insertIngredient(register);
	}

	
	// 무작위로 하나 가져온다
	public IngredientVO getRandomOne() {
		IngredientVO vo=mapper.selectRandomOne();
		System.out.println(vo.getUpdatedDate());
		//여기에 00초일때를 가정하면된다
		여기서부터 이어서 작업
		vo.setUpdatedDate(vo.getUpdatedDate().plusSeconds(1));
		System.out.println(vo.getUpdatedDate().getSecond());
		return vo;
	}
	
	
	//이름으로 선택한 영양성분을 보낸다.
	public IngredientVO selectIngredientByName(String findname) {
		return mapper.selectIngredientByName(findname);
	}

	
	// vo를 업데이트한다.
	public void updateIngredient(IngredientVO vo) {
		mapper.updateIngredient(vo.getType(), vo.getDescription(), vo.getName());
	}

	
	// 중복 등록을 막기위해 기존 디비에 연결할수있나 확인한다.
	public boolean isUnique(String register) {
		int count=mapper.countOf(register);
		
		if(count==0) {
			return true;
		}else if(count==1){
			return false;
		}else {
			System.out.println("0도 아니고 1도 아니다 에러찾기");
			return false;
		}
	}

	
	
	
	
}
