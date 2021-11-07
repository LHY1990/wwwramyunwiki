package wiki.ramyun.www.metadata.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface MetadataMapper {

	

	//라면의 중복방지를 위해 이미 있는지 확인한다
//	@Select("select count(*) from metadata where name =#{ramyunName}")
//	int selectCountByName(@Param("ramyunName") String ramyunName);

	//없다면 라면 테이블을 만든다.
//	@Insert("insert into metadata(name) values(#{ramyunName})")
//	void insertRecord(@Param("ramyunName") String ramyunName);

	

	
	//좋아요와 유저이름을 등록한다. 더하기 방식이 아니다.
	@Insert("insert into metadata(name, username, likes) values(#{ramyunName},#{memberId}, 1)")
	void addLike(@Param("ramyunName") String ramyunName, @Param("memberId") String memberId);
	
	//좋아요와 유저이름을 등록한다. 더하기 방식이 아니다.
	@Insert("insert into metadata(name, username, reporting) values(#{ramyunName},#{memberId}, 1)")
	void addReport(@Param("ramyunName") String ramyunName, @Param("memberId") String memberId);

	
	
	
	// 이름과 멤버아이디를 비교해서 이미 등록된건지 확인한다. 라이크 확인
	@Select("select count(likes) from metadata where username=#{memeberId} and name=#{ramyunName} and likes=1")
	int hasPrevLikes(@Param("memeberId") String memeberId, @Param("ramyunName") String ramyunName);
	
	// 이름과 멤버아이디를 비교해서 이미 등록된건지 확인한다. 리포팅 확인
	@Select("select count(reporting) from metadata where username=#{memeberId} and name=#{ramyunName} and reporting=1")
	int hasPrevReport(@Param("memeberId") String memeberId, @Param("ramyunName") String ramyunName);

	
	
	
	
	//라면이름을 가지고 좋아요가 몇개인지 얻는다.
	@Select("select count(name) from metadata where name =#{ramyunName} and likes=1")
	int getLikes(@Param("ramyunName") String ramyunName);
	
	//라면이름을 가지고 신고가 몇개인지 얻는다.
	@Select("select count(name) from metadata where name =#{ramyunName} and reporting=1")
	int getReport(@Param("ramyunName") String ramyunName);

	
	//신고갯수를 확인한다.
	@Select("select count(name) from metadata where name=#{ramyunName} and reporting=1")
	int reportingCount(@Param("ramyunName") String ramyunName);
	
	//좋아하는갯수를 확인한다.
	@Select("select count(name) from metadata where name=#{ramyunName} and likes=1")
	int likesCount(@Param("ramyunName") String ramyunName);
	
	//이건 아주 특수한 경우
	//라면에 넣지 않고 일부러 여기넣어 삭제를 관리한다.
	@Delete("delete from ramyun where brand_name_kor=#{ramyunName}")
	void removeRamyun(@Param("ramyunName") String ramyunName);


}
