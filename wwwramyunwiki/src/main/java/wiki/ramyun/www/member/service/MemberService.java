package wiki.ramyun.www.member.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import wiki.ramyun.www.member.MemberVO;
import wiki.ramyun.www.member.dao.MemberDAO;

@Service
public class MemberService {
	
	@Autowired
	private MemberDAO dao;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	@Qualifier("bcryptPasswordEncoder")
	private BCryptPasswordEncoder passwordEncoder;
	
	
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
	//해당 이메일로 가입된 멤버수를 찾는다 
	//0이면 미가입이거나 오입력일것이고 
	//1이면 1회가입에 올바른입력, 
	//2이상인경우 다중가입일 것이다. 이경우 마지막 1개만 계정을 찾도록 돕는다
	public String isMemberEmail(String findbyemail) {
		
		
		int emailCount=dao.getCountByEmail(findbyemail);
		
		if(emailCount==0) {
			return "notFound";
		}else if(emailCount>0){
			//0개보다 크다면 1개라면 1개를 2개 이상이면 가장 최근의 것을 찾아 이메일을 보낸다.
			String memeberId=dao.getLatestMemberByEmail(findbyemail);
			
			//임시비밀번호를 생성한다. 10글자. 원본은 메일로 보낸다. 
			String tempPassword=RandomStringUtils.random(10, 65, 122, true, true);
			
			//임시 비밀번호를 암호화해서 디비에 저장한다.
			String encodedTempPassword=passwordEncoder.encode(tempPassword);
			dao.changeMemberPassword(memeberId, encodedTempPassword);
			
			//회원으로 등록된 이메일이 입력되면 해당 메일로 회원의 아이디와 비밀번호 설정창을 보낸다.
			String setfrom = "ramyunwiki@gmail.com";
			String tomail=findbyemail;
			String title ="[라면위키]"+memeberId+"님의 아이디/비밀번호 찾기 메일 입니다.";
			
			
			String content ="안녕하세요 라면위키입니다."+System.getProperty("line.separator")
							+memeberId+"님의 아이디/비밀번호 찾기 메일입니다. "+System.getProperty("line.separator")
							+"임시 비밀번호는 다음과 같습니다."+System.getProperty("line.separator")
							+System.getProperty("line.separator")+tempPassword;
			
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
			//메일보내기끝
			
			return "found";
		}
		
		return "error!";
	}


	public List<MemberVO> selectMemberByRange(int startList, int listSize) {
		return dao.selectMemberByRange(startList, listSize);
	}

	

}
