package wiki.ramyun.www.metadata.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface MetadataMapper {

	
	

	
	//좋아요와 유저이름을 등록한다. 더하기 방식이 아니다.
	@Insert("insert into metadata(name, username, likes) values(#{name},#{memberId}, 1)")
	void addLike(@Param("name") String name, @Param("memberId") String memberId);
	
	//좋아요와 유저이름을 등록한다. 더하기 방식이 아니다.
	@Insert("insert into metadata(name, username, reporting) values(#{name},#{memberId}, 1)")
	void addReport(@Param("name") String name, @Param("memberId") String memberId);

	
	
	
	// 이름과 멤버아이디를 비교해서 이미 등록된건지 확인한다. 라이크 확인
	@Select("select count(likes) from metadata where username=#{memeberId} and name=#{name} and likes=1")
	int hasPrevLikes(@Param("memeberId") String memeberId, @Param("name") String name);
	
	// 이름과 멤버아이디를 비교해서 이미 등록된건지 확인한다. 리포팅 확인
	@Select("select count(reporting) from metadata where username=#{memeberId} and name=#{name} and reporting=1")
	int hasPrevReport(@Param("memeberId") String memeberId, @Param("name") String name);

	
	
	
	
	//라면이름을 가지고 좋아요가 몇개인지 얻는다.
	@Select("select count(name) from metadata where name =#{name} and likes=1")
	int getLikesByName(@Param("name") String name);
	
	//라면이름을 가지고 신고가 몇개인지 얻는다.
	@Select("select count(name) from metadata where name =#{name} and reporting=1")
	int getReportByName(@Param("name") String name);

	
	//신고갯수를 확인한다.
	@Select("select count(name) from metadata where name=#{name} and reporting=1")
	int reportingCount(@Param("name") String name);
	
	//좋아하는갯수를 확인한다.
	@Select("select count(name) from metadata where name=#{name} and likes=1")
	int likesCount(@Param("name") String name);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//메타데이터 기록도 모두 날린다.
	@Delete("delete from metadata where name=#{name} and id>1")
	void removeAllMetadata(@Param("name")String name);
	
	
	//얘는 항상 맨 밑줄로 할것	
	//이건 아주 특수한 경우
	//라면에 넣지 않고 일부러 여기넣어 삭제를 관리한다.
	@Delete("delete from ramyun where brand_name_kor=#{ramyunName}")
	void removeRamyun(@Param("ramyunName") String ramyunName);

	@Delete("delete from ingredient where name=#{ingredientName}")
	void removeIngredient(@Param("ingredientName")String ingredientName);
	
	@Delete("delete from manufactory where factory_name=#{manufactory}")
	void removeManufactory(@Param("manufactory")String manufactory);
}
