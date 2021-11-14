package wiki.ramyun.www.ingredient.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import wiki.ramyun.www.ingredient.IngredientVO;
import wiki.ramyun.www.ingredient.mapper.IngredientMapper;
import wiki.ramyun.www.seconderrorhandler.SecondErrorHandler;

@Component
public class IngredientDAO {
	
	@Autowired
	@Qualifier("ingredientMapper")
	IngredientMapper mapper;
	
	@Autowired
	@Qualifier("secondErrorHandler")
	SecondErrorHandler secondErrorHandler;

	
	//최근 영양성분 하나를 보낸다.
	public IngredientVO getRecentsOne() {
		IngredientVO vo=mapper.selectRecentOne();
		
		//만약에 00초로끝나면 1초를 더해준다.
		vo.setUpdatedDate(secondErrorHandler.checkSecond(vo.getUpdatedDate()));
		return vo;
	}
	
	
	//새롭게 등록
	public void insertIngredient(String register) {
		mapper.insertIngredient(register);
	}

	
	// 무작위로 하나 가져온다
	public IngredientVO getRandomOne() {
		IngredientVO vo=mapper.selectRandomOne();
		//만약에 00초로끝나면 1초를 더해준다.
		vo.setUpdatedDate(secondErrorHandler.checkSecond(vo.getUpdatedDate()));
		
		return vo;
	}
	
	
	//이름으로 선택한 영양성분을 보낸다.
	public IngredientVO selectIngredientByName(String findname) {

		IngredientVO vo=mapper.selectIngredientByName(findname);
		//만약에 00초로끝나면 1초를 더해준다.
		vo.setUpdatedDate(secondErrorHandler.checkSecond(vo.getUpdatedDate()));
		return vo;
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


	public List<IngredientVO> selectAllFromIngredient() {
		List<IngredientVO> list=new ArrayList<IngredientVO>();
		
		for(IngredientVO vo : mapper.selectAllFromIngredient()) {
			vo.setUpdatedDate(secondErrorHandler.checkSecond(vo.getUpdatedDate()));
			list.add(vo);
		}
		
		
		return list;
		
		
		
		
		
		
	}


	public void deleteIngredientByName(String name) {
		mapper.deleteIngredientByName(name);
	}

	
	
	
	
}
