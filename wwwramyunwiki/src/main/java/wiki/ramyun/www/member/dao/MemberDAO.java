package wiki.ramyun.www.member.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import wiki.ramyun.www.member.MemberVO;
import wiki.ramyun.www.member.mapper.MemberMapper;

@Component
public class MemberDAO {

	@Autowired
	@Qualifier("memberMapper")
	MemberMapper mapper;
	
	@Autowired
	@Qualifier("bcryptPasswordEncoder")
	private BCryptPasswordEncoder passwordEncoder;
	

	//입력으로 들어온 비밀번호는 암호화 이전 비밀번호
	public boolean memberValidationCheck(String memberId, String memberPassword) {

		
		//기본적으로 실패
		boolean result=false;
		//암호화된 비밀번호와 입력된 비밀번호를 비교한다.
		String encodedPassword=mapper.selectMemberPasswordById(memberId);
		result=passwordEncoder.matches(memberPassword, encodedPassword);
			
		
		return result;
	}
	

	public void insertMember(MemberVO vo) {
		mapper.insertMember(vo);
	}


	public void deleteMember(MemberVO vo) {
		mapper.deleteMeber(vo.getMemberNumber());
	}


	public int isUniqueMember(MemberVO vo) {
		return mapper.isUniqueMember(vo.getMemberId());
	}

	
	// 가입한 멤버수를 가져온다.
	public int getMemberCount() {
		return mapper.getMemberCount();
	}

	
	public MemberVO getMemberById(String memberId) {
		return mapper.getMemberById(memberId);
	}

	
	public void changeNicknameByMemberNumber(String nickname, String memberNumber) {
		mapper.updateNicknameByMemberNumber(nickname, memberNumber);
		
	}

	public void changeEmailByMemberNumber(String memberEmail, String memberNumber) {
		mapper.updateMemberEmailByMemberNumber(memberEmail, memberNumber);
		
	}

	

}
