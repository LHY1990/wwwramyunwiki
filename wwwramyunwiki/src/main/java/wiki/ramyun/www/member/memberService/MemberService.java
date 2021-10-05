package wiki.ramyun.www.member.memberService;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import wiki.ramyun.www.member.MemberVO;
import wiki.ramyun.www.member.serviceImplement.MemberDAO;

@Service
public class MemberService {
	
	@Autowired
	private MemberDAO dao;
	
	public void inserMemberToDB() {
		System.out.println("서비스 시작");
		
		
		MemberVO vo = new MemberVO();
		
		vo.setMemberNumber(4);
		vo.setMemberId("4영훈");
		vo.setMemberPassword("4비밀번호1");
		vo.setJoinDate(LocalDateTime.now());
		vo.setNickname("4닉넴2");
		dao.insertMember(vo);
		
		
		/*
		MemberVO vo=new MemberVO();
		vo.setMemberNumber(2);
		dao.deleteMember(vo);
		*/
	}
}
