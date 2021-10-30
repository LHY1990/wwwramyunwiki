package wiki.ramyun.www.manufactory.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import wiki.ramyun.www.manufactory.ManufactoryVO;

@Mapper
public interface ManufactoryMapper {

	@Select("select factory_name, item_report_number, adress, identify_letter, corporate_name, description, updated_date from manufactory order by updated_date limit 1")
	ManufactoryVO selectRecentOne();

	@Select("select factory_name, item_report_number, adress, identify_letter, corporate_name, description, updated_date from manufactory where factory_name=#{name}")
	ManufactoryVO selectByName(@Param("name")String name);
	
	
}
