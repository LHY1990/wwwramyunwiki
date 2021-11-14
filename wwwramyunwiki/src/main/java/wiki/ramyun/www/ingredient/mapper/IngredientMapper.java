package wiki.ramyun.www.ingredient.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import wiki.ramyun.www.ingredient.IngredientVO;

@Mapper
public interface IngredientMapper {
	
	@Update("update ingredient set type=#{type}, description=#{description} where name=#{name}")
	public void updateIngredient(@Param("type")String type, @Param("description") String description, @Param("name") String name);

	
	@Select("select name, type, description, updated_date from ingredient where name=#{name}")
	public IngredientVO selectIngredientByName(@Param("name")String name);

	
	@Select("select count(*) from ingredient where name=#{register}")
	public int countOf(@Param("register")String register);

	
	@Insert("insert into ingredient(name) values(#{register})")
	public void insertIngredient(@Param("register")String register);

	
	@Select("select name, type, description, updated_date from ingredient order by updated_date desc limit 1 ")
	public IngredientVO selectRecentOne();
	
	
	@Select("select name, type, description, updated_date from ingredient order by rand() limit 1 ")
	public IngredientVO selectRandomOne();

	
	@Select("select * from ingredient order by name")
	public List<IngredientVO> selectAllFromIngredient();

	@Delete("delete from ingredient where name=#{name}")
	public void deleteIngredientByName(@Param("name")String name);
	
	
}
