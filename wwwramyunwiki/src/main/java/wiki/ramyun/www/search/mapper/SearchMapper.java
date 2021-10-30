package wiki.ramyun.www.search.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import wiki.ramyun.www.search.SearchVO;

@Mapper
public interface SearchMapper {
	
	@Select("(select factory_name as name, updated_date as updated_date from manufactory) "
			+ "union "
			+ "(select brand_name_kor, updated_date from ramyun orber) "
			+ "union "
			+ "(select name, updated_date from ingredient) "
			+ "union "
			+ "(select name, name from search) "
			+ "order by name")
	public List<SearchVO> searchRecentUpdated();
	
	
}
