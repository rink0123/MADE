package com.made.login.vo;

/**
 * 로그인_일반회원 VO
 * 
 * @since JDK 1.8
 * @version 1.0.0.RELEASE
 * @author HbeMember_JANG JUN HEE
 */
public class MemberVO {
	private String bm_no;         // 일반회원 번호
	private String bm_id;         // 일반회원 아이디
	private String bm_pw;         // 일반회원 비밀번호
	private String bm_img;        // 일반회원 프로필사진
	private String bm_nick;       // 일반회원 닉네임
	private String bm_name;       // 일반회원 이름
	private String bm_phone;      // 일반회원 휴대폰
	private String bm_email;      // 일반회원 이메일
	private String bm_auth;       // 일반회원 권한
	private String bm_deleteyn;   // 일반회원 삭제여부
	private String bm_insertdate; // 일반회원 가입일
	private String bm_updatedate; // 일반회원 수정일
	private String bm_deletedate; // 일반회원 삭제일
	
	private String bm_idchk;     // 회원가입 아이디 중복체크

	/**
	 * 로그인_일반회원 VO 기본 생성자.
	 */
	public MemberVO() {
	}

	/**
	 * 로그인_일반회원 VO 매개변수 있는 생성자_ALL.
	 * 
	 * @param bm_no         일반회원 번호    
	 * @param bm_id         일반회원 아이디   
	 * @param bm_pw         일반회원 비밀번호  
	 * @param bm_img        일반회원 프로필사진 
	 * @param bm_nick       일반회원 닉네임   
	 * @param bm_name       일반회원 이름    
	 * @param bm_phone      일반회원 휴대폰   
	 * @param bm_email      일반회원 이메일   
	 * @param bm_auth       일반회원 권한    
	 * @param bm_deleteyn   일반회원 삭제여부  
	 * @param bm_insertdate 일반회원 가입일   
	 * @param bm_updatedate 일반회원 수정일   
	 * @param bm_deletedate 일반회원 삭제일   
	 */
	public MemberVO(String bm_no, String bm_id, String bm_pw, String bm_img, String bm_nick, String bm_name,
			String bm_phone, String bm_email, String bm_auth, String bm_deleteyn, String bm_insertdate,
			String bm_updatedate, String bm_deletedate, String bm_idchk) {
		this.bm_no = bm_no;
		this.bm_id = bm_id;
		this.bm_pw = bm_pw;
		this.bm_img = bm_img;
		this.bm_nick = bm_nick;
		this.bm_name = bm_name;
		this.bm_phone = bm_phone;
		this.bm_email = bm_email;
		this.bm_auth = bm_auth;
		this.bm_deleteyn = bm_deleteyn;
		this.bm_insertdate = bm_insertdate;
		this.bm_updatedate = bm_updatedate;
		this.bm_deletedate = bm_deletedate;
		this.bm_idchk = bm_idchk;
	}

	// 로그인_일반회원 VO Getter & Setter.
	public String getBm_no() {
		return bm_no;
	}

	public void setBm_no(String bm_no) {
		this.bm_no = bm_no;
	}

	public String getBm_id() {
		return bm_id;
	}

	public void setBm_id(String bm_id) {
		this.bm_id = bm_id;
	}

	public String getBm_pw() {
		return bm_pw;
	}

	public void setBm_pw(String bm_pw) {
		this.bm_pw = bm_pw;
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

	public String getBm_phone() {
		return bm_phone;
	}

	public void setBm_phone(String bm_phone) {
		this.bm_phone = bm_phone;
	}

	public String getBm_email() {
		return bm_email;
	}

	public void setBm_email(String bm_email) {
		this.bm_email = bm_email;
	}

	public String getBm_auth() {
		return bm_auth;
	}

	public void setBm_auth(String bm_auth) {
		this.bm_auth = bm_auth;
	}

	public String getBm_deleteyn() {
		return bm_deleteyn;
	}

	public void setBm_deleteyn(String bm_deleteyn) {
		this.bm_deleteyn = bm_deleteyn;
	}

	public String getBm_insertdate() {
		return bm_insertdate;
	}

	public void setBm_insertdate(String bm_insertdate) {
		this.bm_insertdate = bm_insertdate;
	}

	public String getBm_updatedate() {
		return bm_updatedate;
	}

	public void setBm_updatedate(String bm_updatedate) {
		this.bm_updatedate = bm_updatedate;
	}

	public String getBm_deletedate() {
		return bm_deletedate;
	}

	public void setBm_deletedate(String bm_deletedate) {
		this.bm_deletedate = bm_deletedate;
	}
	
	public String getBm_idchk() {
		return bm_idchk;
	}

	public void setBm_idchk(String bm_idchk) {
		this.bm_idchk = bm_idchk;
	}

	@Override
	public String toString() {
		return "MemberVO [bm_no=" + bm_no + ", bm_id=" + bm_id + ", bm_pw=" + bm_pw + ", bm_img=" + bm_img
				+ ", bm_nick=" + bm_nick + ", bm_name=" + bm_name + ", bm_phone=" + bm_phone + ", bm_email=" + bm_email
				+ ", bm_auth=" + bm_auth + ", bm_deleteyn=" + bm_deleteyn + ", bm_insertdate=" + bm_insertdate
				+ ", bm_updatedate=" + bm_updatedate + ", bm_deletedate=" + bm_deletedate + ", bm_idchk=" + bm_idchk
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
}