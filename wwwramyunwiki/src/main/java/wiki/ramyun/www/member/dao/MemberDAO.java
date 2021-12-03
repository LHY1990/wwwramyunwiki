package wiki.ramyun.www.member.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import wiki.ramyun.www.member.MemberVO;
import wiki.ramyun.www.member.mapper.MemberMapper;
import wiki.ramyun.www.seconderrorhandler.SecondErrorHandler;

@Component
public class MemberDAO {

	@Autowired
	@Qualifier("memberMapper")
	MemberMapper mapper;
	
	@Autowired
	@Qualifier("bcryptPasswordEncoder")
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	@Qualifier("secondErrorHandler")
	SecondErrorHandler secondErrorHandler;
	

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
		MemberVO vo=mapper.getMemberById(memberId);
		vo.setJoinDate(secondErrorHandler.checkSecond(vo.getJoinDate()));
		return vo;
	}

	
	public void changeNicknameByMemberNumber(String nickname, String memberNumber) {
		mapper.updateNicknameByMemberNumber(nickname, memberNumber);
		
	}

	
	public void changeEmailByMemberNumber(String memberEmail, String memberNumber) {
		mapper.updateMemberEmailByMemberNumber(memberEmail, memberNumber);
		
	}


	//관리자 접근 모든 회원을 가져온다.날짜 에러도 수정한다.
	public List<MemberVO> selectAllFromMember() {
		List<MemberVO> list=new ArrayList<MemberVO>();
		
		for(MemberVO vo : mapper.selectAllFromMember()) {
			vo.setJoinDate(secondErrorHandler.checkSecond(vo.getJoinDate()));
			list.add(vo);
		}
		
		return list;
	}


	public void deleteMemberByNumber(String number) {
		mapper.deleteMemberByNumber(number);
	}


	//변경된 비밀번호와 아이디로 비밀번호를 업데이트한다.
	public void changeMemberPassword(String userMemberId, String newPassword) {
		mapper.updateMemberPasswordByMemberId(userMemberId,newPassword);
	}


	//입력된 아이디로 회원을 삭제한다.
	public void withdrawMemberById(String memberId) {
		
		mapper.deleteMemberById(memberId);
	}


	//해당 이메일로 가입된 멤버수를 찾는다 
	//0이면 미가입이거나 오입력일것이고 
	//1이면 1회가입에 올바른입력, 
	//2이상인경우 다중가입일 것이다. 이경우 마지막 1개만 계정을 찾도록 돕는다
	public int getCountByEmail(String findbyemail) {
		return mapper.getMemberCountByEmail(findbyemail);
	}


	public String getLatestMemberByEmail(String findbyemail) {
		
		return mapper.selectLatestMemberByEmail(findbyemail);
	}


	public List<MemberVO> selectMemberByRange(int startList, int listSize) {
		List<MemberVO> list=new ArrayList<MemberVO>();
		
		for(MemberVO vo : mapper.selectMemberByRange(startList, listSize)) {
			vo.setJoinDate(secondErrorHandler.checkSecond(vo.getJoinDate()));
			list.add(vo);
		}
		
		return list;
	}

	

}
