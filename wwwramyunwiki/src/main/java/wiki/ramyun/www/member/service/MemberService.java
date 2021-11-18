package wiki.ramyun.www.member.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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
		MemberVO vo = new MemberVO();
		
		vo.setMemberNumber(0);
		vo.setMemberId(joinVO.getMemberId());
		vo.setMemberPassword(joinVO.getMemberPassword());
		vo.setJoinDate(LocalDateTime.now());
		vo.setNickname(joinVO.getMemberId());
		vo.setMemberEmail(joinVO.getMemberEmail());
		dao.insertMember(vo);
	}

	
	// 로그인 정보로 들어온 아이디와 패스워드로 회원인지 확인
	public boolean checkMember(MemberVO vo) {
		boolean result=false;
		
		result=dao.memberValidationCheck(vo.getMemberId(), vo.getMemberPassword());
		
		if(result) {
			System.out.println(vo.getMemberId()+"는 회원입니다.");
		}else {
			System.out.println(vo.getMemberId()+"는 회원이 아닙니다.");
		}
		
		
		return result;
		
	}
	
	
	//보내는 메일작성 코드 메일은 여기서 수정한다.
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

	
	// 총 회원수를 가져온다
	public int getMemberCount() {
		return dao.getMemberCount();
	}

	
	// 아이디로 멤버 선택
	public MemberVO getMemberById(String memberId) {
		return dao.getMemberById(memberId);
	}

	
	public void changeNickname(String nickname, String memberNumber) {
		dao.changeNicknameByMemberNumber(nickname, memberNumber);
		
	}
	
	
	//유저의 이메일을 바꾼다.
	public void changeMemberEmail(String memberEmail, String memberNumber) {
		dao.changeEmailByMemberNumber(memberEmail, memberNumber);
		
	}


	public List<MemberVO> selectAllFromMember() {
		return dao.selectAllFromMember();
	}


	public void deleteMemberByNumber(String number) {
		dao.deleteMemberByNumber(number);
	}


	//패스워드를 바꾼다. 기존 멤버인지 확인한다.
	public String changePassword(String userMemberId, String oldPassword, String newPassword) {
		
		boolean result=false;
		
		result=dao.memberValidationCheck(userMemberId, oldPassword);
		
		if(result) {
			//기존 아이디와 비밀번호가 같다면 비밀번호를 변경한다.
			dao.changeMemberPassword(userMemberId, newPassword);
			return "memberChangedSuccess";
		}else {
			System.out.println(userMemberId+"는 회원이 아닙니다.");
			return "memberChangingFaild";
		}
		
		
		
		
	}

	
	//회원탈퇴 처리
	public String withdrawMember(String memberId, String withdrawPassword) {
		boolean result=false;
		
		result=dao.memberValidationCheck(memberId, withdrawPassword);
		
		if(result) {
			//기존 아이디와 비밀번호가 같다면 비밀번호를 변경한다.
			dao.withdrawMemberById(memberId);
			return "withdrawMemberSuccess";
		}else {
			return "withdrawMemberFaild";
		}
	}

	//멤버의 이메일인지 확인한다.
	public boolean isMemberEmail(String findbyemail) {
		
		int number
		
		return ;
	}

	

}
