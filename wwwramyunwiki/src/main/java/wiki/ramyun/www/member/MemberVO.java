package wiki.ramyun.www.member;

import java.sql.Date;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

public class MemberVO {

	private int memberNumber;
	private String memberId;
	private String memberPassword;
	private LocalDateTime joinDate;
	private String nickname;
	private String memberEmail;

	public MemberVO() {
		super();
	}

	public MemberVO(int memberNumber, String memberId, String memberPassword, LocalDateTime joinDate, String nickname,
			String memberEmail) {
		super();
		this.memberNumber = memberNumber;
		this.memberId = memberId;
		this.memberPassword = memberPassword;
		this.joinDate = joinDate;
		this.nickname = nickname;
		this.memberEmail = memberEmail;
	}

	public int getMemberNumber() {
		return memberNumber;
	}

	public void setMemberNumber(int memberNumber) {
		this.memberNumber = memberNumber;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberPassword() {
		return memberPassword;
	}

	public void setMemberPassword(String memberPassword) {
		this.memberPassword = memberPassword;
	}

	public LocalDateTime getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(LocalDateTime joinDate) {
		this.joinDate = joinDate;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getMemberEmail() {
		return memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	@Override
	public String toString() {
		return "MemberVO [memberNumber=" + memberNumber + ", memberId=" + memberId + ", memberPassword="
				+ memberPassword + ", joinDate=" + joinDate + ", nickname=" + nickname + ", memberEmail=" + memberEmail
				+ "]";
	}

}
