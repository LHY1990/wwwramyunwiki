package wiki.ramyun.www.manufactory.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import wiki.ramyun.www.manufactory.ManufactoryVO;

@Mapper
public interface ManufactoryMapper {

	@Select("select factory_name, item_report_number, adress, identify_letter, corporate_name, description, updated_date from manufactory order by updated_date limit 1")
	ManufactoryVO selectRecentOne();

	@Select("select factory_name, item_report_number, adress, identify_letter, corporate_name, description, updated_date from manufactory where factory_name=#{name}")
	ManufactoryVO selectByName(@Param("name")String name);

	
	@Update("update manufactory set corporate_name=#{corporateName}, adress=#{adress}, identify_letter=#{identifyLetter}, item_report_number=#{itemReportNumber}, description=#{description} where factory_name=#{factoryName}")
	void updateManufactory(@Param("corporateName") String corporateName,
						  @Param("adress") String adress, 
						  @Param("identifyLetter") String identifyLetter,
						  @Param("itemReportNumber") String itemReportNumber,
						  @Param("description") String description,
						  @Param("factoryName") String factoryName);
	
	
	
}
