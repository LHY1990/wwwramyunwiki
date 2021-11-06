package wiki.ramyun.www.member.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import wiki.ramyun.www.member.MemberVO;
import wiki.ramyun.www.member.dao.MemberDAO;

@Service
public class MemberService {
	
	@Autowired
	private MemberDAO dao;
	
	@Autowired
	private JavaMailSender mailSender;
	
	
	
	
	
	public void inserMemberToDB(MemberVO joinVO) {
		System.out.println("서비스 시작");
		
		
		MemberVO vo = new MemberVO();
		
		vo.setMemberNumber(0);
		vo.setMemberId(joinVO.getMemberId());
		vo.setMemberPassword(joinVO.getMemberPassword());
		vo.setJoinDate(LocalDateTime.now());
		vo.setNickname(joinVO.getMemberId());
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
	
	public void sendMailCode(MemberVO vo, String mailCodeInput) {
		String setfrom = "ramyunwiki@gmail.com";
		String tomail=vo.getMemberEmail();
		String title ="라면위키 회원가입 인증 메일입니다.";
		String mailCode = mailCodeInput;
				
		
		String content ="안녕하세요 라면위키를 찾아주셔서 감사합니다."+System.getProperty("line.separator")+"인증번호는 다음과 같습니다."+System.getProperty("line.separator")+mailCode;
		
		
		
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

	public boolean isUnique(MemberVO vo) {
		int isUnique= dao.isUniqueMember(vo);
		
		
		if(isUnique==0) {
			return true;
		}
		
		return false;
		
		
		
	}

	public int getMemberCount() {
		// 총 회원수를 가져온다
		return dao.getMemberCount();
	}

	public MemberVO getMemberById(String memberId) {
		// TODO Auto-generated method stub
		return dao.getMemberById(memberId);
	}
}
