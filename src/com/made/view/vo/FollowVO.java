package com.made.view.vo;

/**
 * 팔로우 VO
 * 
 * @since JDK 1.8
 * @version 1.0.0.RELEASE
 * @author HbeMember_KANG MIN SUNG
 */
public class FollowVO {
	// 팔로우 VO 변수.
	private String follow_no;    // 팔로우 번호
	private String bm_no;        // 팔로우 회원번호
	private String reg_bm_no;    // 팔로워 회원번호
	
	// 팔로우 VO 카운트 변수.
	private String follow_chk;   // 팔로우 체크
	private String follower_cnt; // 팔로워 수
	
	private String session_bm_no; // session 회원 번호
	
	/**
	 * 팔로우 VO 기본 생성자.
	 */
	public FollowVO() {
	}

	/**
	 * 팔로우 VO 매개변수 있는 생성자_ALL.
	 * 
	 * @param follow_no    팔로우 번호
	 * @param bm_no        팔로우 회원번호
	 * @param reg_bm_no    팔로워 회원번호
	 * @param follow_chk   팔로우 체크
	 * @param follower_cnt 팔로워 수
	 */
	public FollowVO(String follow_no, String bm_no, String reg_bm_no, String follow_chk, String follower_cnt) {
		this.follow_no = follow_no;
		this.bm_no = bm_no;
		this.reg_bm_no = reg_bm_no;
		this.follow_chk = follow_chk;
		this.follower_cnt = follower_cnt;
	}

	// 팔로우 VO Getter & Setter.
	public String getFollow_no() {
		return follow_no;
	}

	public void setFollow_no(String follow_no) {
		this.follow_no = follow_no;
	}

	public String getBm_no() {
		return bm_no;
	}

	public void setBm_no(String bm_no) {
		this.bm_no = bm_no;
	}

	public String getReg_bm_no() {
		return reg_bm_no;
	}

	public void setReg_bm_no(String reg_bm_no) {
		this.reg_bm_no = reg_bm_no;
	}

	public String getfollow_chk() {
		return follow_chk;
	}

	public void setfollow_chk(String follow_chk) {
		this.follow_chk = follow_chk;
	}

	public String getFollower_cnt() {
		return follower_cnt;
	}

	public void setFollower_cnt(String follower_cnt) {
		this.follower_cnt = follower_cnt;
	}

	public String getSession_bm_no() {
		return session_bm_no;
	}

	public void setSession_bm_no(String session_bm_no) {
		this.session_bm_no = session_bm_no;
	}

	@Override
	public String toString() {
		return "FollowVO [follow_no=" + follow_no + ", bm_no=" + bm_no + ", reg_bm_no=" + reg_bm_no + ", follow_chk="
				+ follow_chk + ", follower_cnt=" + follower_cnt + ", session_bm_no=" + session_bm_no + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
}