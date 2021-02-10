package com.made.profile.vo;

/**
 * 팔로우 VO
 * 
 * @since JDK 1.8
 * @version 1.0.0.RELEASE
 * @author HbeMember_KIM YUNG JIN, KIM HYUNG SEOP
 */
public class FollowVO {
	private String follow_no;  // 팔로우 번호
	private String bm_no;      // 일반회원 번호(본인)
	private String reg_bm_no;  // 일반회원 번호(상대)
	private String follow_chk; // 팔로우 체크
	private String follow_cnt; // 팔로우 수
	private String bm_img;     // 일반회원 프로필사진
	private String bm_nick;    // 일반회원 닉네임
	private String bm_name;    // 일반회원 이름
	
	/**
	 * 팔로우 VO 기본 생성자.
	 */
	public FollowVO() {
	}

	/**
	 * 팔로우 VO 매개변수 있는 생성자_ALL.
	 * 
	 * @param follow_no  팔로우 번호
	 * @param bm_no      일반회원 번호(본인)
	 * @param reg_bm_no  일반회원 번호(상대)
	 * @param follow_chk 팔로우 체크
	 * @param follow_cnt 팔로우 수
	 * @param bm_img     일반회원 프로필사진
	 * @param bm_nick    일반회원 닉네임
	 * @param bm_name    일반회원 이름
	 */
	public FollowVO(String follow_no, String bm_no, String reg_bm_no, String follow_chk, String follow_cnt,
			String bm_img, String bm_nick, String bm_name) {
		super();
		this.follow_no = follow_no;
		this.bm_no = bm_no;
		this.reg_bm_no = reg_bm_no;
		this.follow_chk = follow_chk;
		this.follow_cnt = follow_cnt;
		this.bm_img = bm_img;
		this.bm_nick = bm_nick;
		this.bm_name = bm_name;
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

	public String getFollow_chk() {
		return follow_chk;
	}

	public void setFollow_chk(String follow_chk) {
		this.follow_chk = follow_chk;
	}

	public String getFollow_cnt() {
		return follow_cnt;
	}

	public void setFollow_cnt(String follow_cnt) {
		this.follow_cnt = follow_cnt;
	}

	public String getBm_img() {
		return bm_img;
	}

	public void setBm_img(String bm_img) {
		this.bm_img = bm_img;
	}

	public String getBm_nick() {
		return bm_nick;
	}

	public void setBm_nick(String bm_nick) {
		this.bm_nick = bm_nick;
	}

	public String getBm_name() {
		return bm_name;
	}

	public void setBm_name(String bm_name) {
		this.bm_name = bm_name;
	}

	@Override
	public String toString() {
		return "FollowVO [follow_no=" + follow_no + ", bm_no=" + bm_no + ", reg_bm_no=" + reg_bm_no + ", follow_chk="
				+ follow_chk + ", follow_cnt=" + follow_cnt + ", bm_img=" + bm_img + ", bm_nick=" + bm_nick
				+ ", bm_name=" + bm_name + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
}