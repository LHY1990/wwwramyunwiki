package wiki.ramyun.www.ramyun.mapper;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import wiki.ramyun.www.ramyun.RamyunVO;

@Mapper
public interface RamyunMapper {
	
	
	//기존 라면디비에 라면정보 추가할때, 이경우 반드시 같은 라면이 있는지 체크가 필요
	@Insert("insert into ramyun(brand_name_kor) values(#{newRamyun})")
	public void insertRamyun(@Param("newRamyun")String newRamyun);
	
	
	//회원등급 5등급 이상일때 새로운 라면을 등록할때
	@Insert("insert into ramyun(brand_name_kor) values(#{brandNameKor})")
	public void registerNewRamyun(@Param("brandNameKor") String ramyunBrandNameKor);

	
	//라면이름을 키로 받아서 작업할것, 마지막에 브랜드 이름이 들어간다.
	@Update("update ramyun set brand_name_eng=#{brandNameEng}, updated_date=#{updatedDate},"
			+ " corporate_name=#{corporateName}, developed_date=#{developedDate}, weight=#{weight}, calorie=#{calorie},"
			+ " scoville_unit=#{scovilleUnit}, food_category=#{foodCategory}, recipe=#{recipe}, barcode=#{barcode},"
			+ " noodle_shape=#{noodleShape}, soup_composition=#{soupComposition}, discontinuance=#{discontinuance},"
			+ " related_ramyun=#{relatedRamyun}, water_capacity_by_number=#{waterCapacityByNumber}, material_list=#{materialList},"
			+ " item_report_number=#{itemReportNumber}, expiration_date=#{expirationDate}, soup_position=#{soupPosition},"
			+ " natrium=#{natrium}, carbohydrate=#{carbohydrate}, sugars=#{sugars}, fat=#{fat},"
			+ " transfat=#{transfat}, saturatedfat=#{saturatedfat}, cholesterol=#{cholesterol}, protein=#{protein},"
			+ " calcium=#{calcium}, image=#{image}, user_edited_contents=#{userEditedContents}"
			+ " where brand_name_kor=\'${brandNameKor}\'")
	public void updateRamyun(@Param("brandNameEng") String brandNameEng,@Param("updatedDate") LocalDateTime updatedDate,
			@Param("corporateName") String corporateName, @Param("developedDate") String developedDate, @Param("weight") String weight, @Param("calorie") String calorie,
			@Param("scovilleUnit") String scovilleUnit, @Param("foodCategory") String foodCategory, @Param("recipe") String recipe, @Param("barcode") String barcode,
			@Param("noodleShape") String noodleShape, @Param("soupComposition") String soupComposition, @Param("discontinuance") String discontinuance,
			@Param("relatedRamyun") String relatedRamyun, @Param("waterCapacityByNumber") String waterCapacityByNumber, @Param("materialList") String materialList,
			@Param("itemReportNumber") String itemReportNumber, @Param("expirationDate") String expirationDate, @Param("soupPosition") String soupPosition,
			@Param("natrium") String natrium, @Param("carbohydrate") String carbohydrate, @Param("sugars") String sugars, @Param("fat") String fat, 
			@Param("transfat") String transfat, @Param("saturatedfat") String saturatedfat, @Param("cholesterol") String cholesterol, @Param("protein") String protein,
			@Param("calcium") String calcium, @Param("image") String image, @Param("userEditedContents") String userEditedContents, @Param("brandNameKor") String brandNameKor);

	
	//검색창에서 라면이름 검색할때
	@Select("select * from ramyun where brand_name_kor = \"${searchBoxInput}\"")
	public RamyunVO selectRamyunFromDB(@Param("searchBoxInput")String searchBoxInput);

	
	//메인페이지 우측 탭에서 리스트 가져올때 10개 가져옴
	@Select("select brand_name_kor, updated_date from ramyun order by updated_date desc limit 10")
	public List<RamyunVO> getRecentsUpdateList();

	
	@Select("select brand_name_kor, updated_date, user_edited_contents from ramyun order by updated_date desc limit 28")
	public List<RamyunVO> getRecentsUpdateListWhole();

	
	@Select("select count(brand_name_kor) from ramyun")
	public int getRamyunCount();

	
	@Select("select count(*) from ramyun where brand_name_kor=#{newRamyunName}")
	public int countOf(@Param("newRamyunName")String newRamyunName);

	@Select("select * from ramyun order by brand_name_kor")
	public List<RamyunVO> selectAllFromRamyun();
	
	@Select("select * from ramyun order by rand()")
	public List<RamyunVO> selectAllFromRamyunByRandom();

	@Delete("delete from ramyun where brand_name_kor=#{name} ")
	public void deleteRamyunByName(@Param("name")String name);

	@Select("select * from ramyun order by rand() limit 1")
	public RamyunVO selectRandomRamyun();

}
