package wiki.ramyun.www.member.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import wiki.ramyun.www.member.MemberVO;
import wiki.ramyun.www.member.mapper.MemberMapper;

@Component
public class MemberDAO {

	@Autowired
	@Qualifier("memberMapper")
	MemberMapper mapper;
	

	public boolean memberValidationCheck(String memberId, String memberPassword) {
		int memberCount=0;
		boolean result=false;
		
		memberCount=mapper.validateMember(memberId, memberPassword);
		if(memberCount==1) {
			//나온게 한명이면 정상
			result=true;
		}else if(memberCount==0) {
			result=false;
		}
		return result;
	}
	

	public void insertMember(MemberVO vo) {
		System.out.println("dao 접근");
		System.out.println(vo.getMemberEmail());
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
