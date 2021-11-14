package wiki.ramyun.www.manufactory.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import wiki.ramyun.www.manufactory.ManufactoryVO;

@Mapper
public interface ManufactoryMapper {


	//이름으로 데이터 가져오기
	@Select("select factory_name, item_report_number, adress, identify_letter, corporate_name, description, updated_date from manufactory where factory_name=#{name}")
	ManufactoryVO selectByName(@Param("name")String name);

	
	//공장정보 갱신
	@Update("update manufactory set corporate_name=#{corporateName}, adress=#{adress}, identify_letter=#{identifyLetter}, item_report_number=#{itemReportNumber}, description=#{description} where factory_name=#{factoryName}")
	void updateManufactory(@Param("corporateName") String corporateName,
						  @Param("adress") String adress, 
						  @Param("identifyLetter") String identifyLetter,
						  @Param("itemReportNumber") String itemReportNumber,
						  @Param("description") String description,
						  @Param("factoryName") String factoryName);

	
	//이미 등록된 데이터의 갯수를 센다.
	@Select("select count(*) from ingredient where name=#{register}")
	int countOf(@Param("register")String register);
	
	
	//공장 등록하기
	@Insert("insert into manufactory(factory_name) values(#{register})")
	void insertManufactory(String register);

	
	//최근것 값 가져와서 첫 화면에 뿌리기
	@Select("select factory_name, item_report_number, adress, identify_letter, corporate_name, description, updated_date from manufactory order by updated_date limit 1")
	ManufactoryVO selectRecentOne();
	
	
	//랜덤으로 가져오기
	@Select("select factory_name, item_report_number, adress, identify_letter, corporate_name, description, updated_date from manufactory order by rand() limit 1")
	ManufactoryVO selectRandomOne();

	@Select("select * from manufactory order by factory_name")
	List<ManufactoryVO> selectAllFromManufactory();

	@Delete("delete from manufactory where factory_name=#{name}")
	void deleteManufactoryByName(@Param("name")String name);
	
	
	
}
