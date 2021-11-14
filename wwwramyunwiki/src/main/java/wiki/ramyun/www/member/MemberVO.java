package wiki.ramyun.www.member;

import java.time.LocalDateTime;


public class MemberVO {

	private int memberNumber;
	private String memberId;
	private String memberPassword;
	private LocalDateTime joinDate;
	private String nickname;
	private String memberEmail;
	private String level;

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
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}


	@Override
	public String toString() {
		return "MemberVO [memberNumber=" + memberNumber + ", memberId=" + memberId + ", memberPassword="
				+ memberPassword + ", joinDate=" + joinDate + ", nickname=" + nickname + ", memberEmail=" + memberEmail
				+ "]";
	}

}
