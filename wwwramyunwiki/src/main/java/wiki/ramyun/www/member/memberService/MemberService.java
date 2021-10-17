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

	public boolean checkMember(MemberVO vo) {
		// 로그인 정보로 들어온 아이디와 패스워드로 회원인지 확인
		boolean result=false;
		
		System.out.println("내부확인"+vo.getMemberId()+" "+vo.getMemberPassword());
		result=dao.memberValidationCheck(vo.getMemberId(), vo.getMemberPassword());
		
		
		
		if(result) {
			System.out.println(vo.getMemberId()+"는 회원입니다.");
		}else {
			System.out.println(vo.getMemberId()+"는 회원이 아닙니다.");
		}
		
		
		
		
		return result;
		
	}
}
