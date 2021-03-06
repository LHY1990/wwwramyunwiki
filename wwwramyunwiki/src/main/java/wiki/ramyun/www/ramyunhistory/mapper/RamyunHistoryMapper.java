package wiki.ramyun.www.ramyunhistory.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import wiki.ramyun.www.ramyunhistory.RamyunHistoryVO;

@Mapper
public interface RamyunHistoryMapper {

	@Select("Select * from ramyun_history where brand_name_kor=#{name} order by id desc")
	List<RamyunHistoryVO> selectHistoryByName(@Param("name")String name);
	
	@Select("Select * from ramyun_history where brand_name_kor=#{name} order by id desc limit #{startPage},#{listSize}")
	List<RamyunHistoryVO> selectPageOfHistoryByName(@Param("name")String name, @Param("startPage")int startPage, @Param("listSize")int listSize);
	
	//주의 테이블 이름은 ramyun_history이다
	@Insert("insert into ramyun_history(brand_name_kor, brand_name_eng, updated_date, corporate_name, developed_date, weight, calorie, scoville_unit, food_category, recipe, barcode, noodle_shape, soup_composition, discontinuance,"
			+ " related_ramyun, water_capacity_by_number, material_list, item_report_number, expiration_date, soup_position, natrium, carbohydrate, sugars, fat, transfat, saturatedfat, cholesterol, protein, calcium, image, user_edited_contents,"
			+ " writer, writer_member_number)"
			+ " values(#{brandNameKor}, #{brandNameEng}, #{updatedDate}, #{corporateName}, #{developedDate}, #{weight}, #{calorie}, #{scovilleUnit}, #{foodCategory}, #{recipe}, #{barcode}, #{noodleShape}, #{soupComposition}, #{discontinuance},"
			+ " #{relatedRamyun}, #{waterCapacityByNumber}, #{materialList}, #{itemReportNumber}, #{expirationDate}, #{soupPosition}, #{natrium}, #{carbohydrate}, #{sugars}, #{fat}, #{transfat}, #{saturatedfat}, #{cholesterol}, #{protein},"
			+ " #{calcium}, #{image}, #{userEditedContents}, #{writer}, #{writerMemberNumber} "
			+ " )")
	public void insertRamyunHistory(@Param("brandNameKor") String brandNameKor, @Param("brandNameEng") String brandNameEng,@Param("updatedDate") LocalDateTime updatedDate,
			@Param("corporateName") String corporateName, @Param("developedDate") String developedDate, @Param("weight") String weight, @Param("calorie") String calorie,
			@Param("scovilleUnit") String scovilleUnit, @Param("foodCategory") String foodCategory, @Param("recipe") String recipe, @Param("barcode") String barcode,
			@Param("noodleShape") String noodleShape, @Param("soupComposition") String soupComposition, @Param("discontinuance") String discontinuance,
			@Param("relatedRamyun") String relatedRamyun, @Param("waterCapacityByNumber") String waterCapacityByNumber, @Param("materialList") String materialList,
			@Param("itemReportNumber") String itemReportNumber, @Param("expirationDate") String expirationDate, @Param("soupPosition") String soupPosition,
			@Param("natrium") String natrium, @Param("carbohydrate") String carbohydrate, @Param("sugars") String sugars, @Param("fat") String fat, 
			@Param("transfat") String transfat, @Param("saturatedfat") String saturatedfat, @Param("cholesterol") String cholesterol, @Param("protein") String protein,
			@Param("calcium") String calcium, @Param("image") String image, @Param("userEditedContents") String userEditedContents,@Param("writer") String writer,@Param("writerMemberNumber") String writerMemberNumber
			);

	
	@Select("select * from ramyun_history where id=#{id}")
	public RamyunHistoryVO selectHistoryById(@Param("id") String id);

	@Select("select brand_name_kor, id, writer from ramyun_history order by id")
	List<RamyunHistoryVO> selectAllFromRamyunHistoryDB();

	@Delete("delete from ramyun_history where id=#{id}")
	void deleteRamyunHistoryById(@Param("id")String id);

	@Select("select count(*) from ramyun_history where brand_name_kor=#{name}")
	int countAllRamyunHistory(@Param("name")String name);

	@Select("select * from ramyun_history order by id limit #{startPage},#{listSize}")
	List<RamyunHistoryVO> selectRamyunHistoryByRange(@Param("startPage") int startList, @Param("listSize") int listSize);

	@Select("select count(*) from ramyun_history")
	int getRamyunHistoryCount();

	@Select("select count(*) from ramyun_history where writer = #{nickname}")
	int getContributionCountByNickname(@Param("nickname") String nickname);
	
	@Select("select count(*) from ramyun_history where writer_member_number = #{writerMemberNumber}")
	int getContributionCountByWriterMemberNumber(@Param("writerMemberNumber")String writerMemberNumber);

	@Select("select * from ramyun_history where writer=#{memberNickName} order by id desc limit #{startPage},#{listSize}")
	List<RamyunHistoryVO> getHistoryByNickname(@Param("memberNickName")String memberNickName, @Param("startPage")int startList, @Param("listSize")int listSize);

	@Select("select * from ramyun_history where writer_member_number=#{writerMemberNumber} order by id desc limit #{startPage},#{listSize}")
	List<RamyunHistoryVO> getHistoryByWriterMemberNumber(@Param("writerMemberNumber")String writerMemberNumber,@Param("startPage") int startList,@Param("listSize") int listSize);

	
	

}
