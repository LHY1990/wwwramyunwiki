package wiki.ramyun.www.member.memberService;

import java.time.LocalDateTime;
import java.util.List;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import wiki.ramyun.www.member.MemberVO;
import wiki.ramyun.www.member.serviceImplement.MemberDAO;

@Service
public class MemberService {
	
	@Autowired
	private MemberDAO dao;
	
	@Autowired
	private JavaMailSender mailSender;
	
	
	
	
	
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
	
	public void sendMailCode(MemberVO vo) {
		String setfrom = "ramyunwiki@gmail.com";
		String tomail=vo.getMemberEmail();
		String title ="라면위키 회원가입 인증 메일입니다.";
		String content ="안녕하세요 라면위키를 찾아주셔서 감사합니다."+System.getProperty("line.separator")+"인증번호는 다음과 같습니다."+System.getProperty("line.separator")+"123123";
		
		
		
		try {
			MimeMessage message =mailSender.createMimeMessage();
			MimeMessageHelper messageHelper=new MimeMessageHelper(message, true, "UTF-8");
			
			messageHelper.setFrom(setfrom);
			messageHelper.setTo(tomail);
			messageHelper.setSubject(title);
			messageHelper.setText(content);
			
			mailSender.send(message);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
