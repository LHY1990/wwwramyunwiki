package wiki.ramyun.www.member.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import wiki.ramyun.www.member.MemberVO;

@Mapper
public interface MemberMapper {
	
		
//	@Insert("insert into member(member_number, member_id, member_password, join_date, nickname, member_email) values(0,#{memberId},#{memberPassword},now(),#{memberId},#{memberEmail})")
//	public void insertMember(@Param("memberId")String memberId,@Param("memberPassword") String memberPassword,@Param("memberEmail") String memberEmail);
	
	
	//회원가입시 멤버의 정보를 넣는다.
	@Insert("insert into member(member_number, member_id, member_password, join_date, nickname, member_email) values(#{memberNumber},#{memberId},#{memberPassword},#{joinDate},#{nickname},#{memberEmail})")
	public void insertMember(MemberVO member);
	
	
	//멤버의 넘버로 회원을 지운다. 이걸로만 접근가능
	@Delete("delete from member where member_number =${memberNumber}")
	public void deleteMeber(@Param("memberNumber") int memberNumber);

	@Select("select count(*) from member where member_id=\"${memberId}\" and member_password=\"${memberPassword}\"")
	public int validateMember(@Param("memberId")String memberId,@Param("memberPassword") String memberPassword);
	
	//아이디가 같은 명수를 구한다.
	@Select("select count(*) from member where member_id=\"${memberId}\"")
	public int isUniqueMember(@Param("memberId")String memberId);

	//회원의 명수를 구한다.
	@Select("select count(member_id) from member")
	public int getMemberCount();

	@Select("select * from member where member_id=#{memberId}")
	public MemberVO getMemberById(@Param("memberId")String memberId);

	@Update("update member set nickname=#{nickname} where member_number =#{memberNumber}")
	public void updateNicknameByMemberNumber(@Param("nickname")String nickname,@Param("memberNumber") String memberNumber);

	
	
	
}
