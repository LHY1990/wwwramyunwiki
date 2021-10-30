package wiki.ramyun.www.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import wiki.ramyun.www.member.MemberVO;
import wiki.ramyun.www.member.mapper.MemberMapper;
import wiki.ramyun.www.member.service.MemberService;

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
		mapper.insertMember(vo);

	}

	public void updateMember(MemberVO vo) {
		// TODO Auto-generated method stub
		
	}

	public void deleteMember(MemberVO vo) {
		// TODO Auto-generated method stub
		mapper.deleteMeber(vo.getMemberNumber());
	}

	public MemberVO getMember(MemberVO vo) {
		// TODO Auto-generated method stub
		return null;

	}

	public List<MemberVO> getMemberList(MemberVO vo) {
		// TODO Auto-generated method stub
		return null;

	}

	public int isUniqueMember(MemberVO vo) {
		
		
		return mapper.isUniqueMember(vo.getMemberId());
	}

	public int getMemberCount() {
		// 가입한 멤버수를 가져온다.
		return mapper.getMemberCount();
	}

}
