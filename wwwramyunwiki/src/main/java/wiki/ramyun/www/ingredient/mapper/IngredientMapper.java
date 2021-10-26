package wiki.ramyun.www.ingredient.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import wiki.ramyun.www.ingredient.IngredientVO;

@Mapper
public interface IngredientMapper {
	
	@Update("update ingredient set type=#{type}, description=#{description} where name=#{name}")
	public void updateIngredient(@Param("type")String type, @Param("description") String description, @Param("name") String name);
}
