package wiki.ramyun.www.member.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import wiki.ramyun.www.member.MemberVO;

@Mapper
public interface MemberMapper {
	
	
	@Insert("insert into member(member_number, member_id, member_password, join_date, nickname) values(#{memberNumber},#{memberId},#{memberPassword},#{joinDate},#{nickname})")
	public void insertMember(MemberVO member);
	
	@Delete("delete from member where member_number =${memberNumber}")
	public void deleteMeber(@Param("memberNumber") int memberNumber);

	@Select("select count(*) from member where member_id=\"${memberId}\" and member_password=\"${memberPassword}\"")
	public int validateMember(@Param("memberId")String memberId,@Param("memberPassword") String memberPassword);
}
