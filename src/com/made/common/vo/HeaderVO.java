package com.made.common.vo;

/**
 * 헤더 VO
 * 
 * @since JDK 1.8
 * @version 1.0.0.RELEASE
 * @author HbeMember_Common
 */
public class HeaderVO {
	private String notice_cnt;   // 헤더 알림 수
	private String notice_index; // 헤더 인덱스
	private String notice_no;    // 헤더 번호
	private String bm_nick;      // 일반회원 닉네임
	private String notice_date;  // 헤더 날짜
	
	/**
	 * 헤더 VO 기본 생성자.
	 */
	public HeaderVO() {
	}

	/**
	 * 헤더 VO 매개변수 있는 생성자_ALL.
	 * 
	 * @param notice_cnt   헤더 알림 수
	 * @param notice_index 헤더 인덱스
	 * @param notice_no    헤더 번호
	 * @param bm_nick      일반회원 닉네임
	 * @param notice_date  헤더 날짜
	 */
	public HeaderVO(String notice_cnt, String notice_index, String notice_no, String bm_nick, String notice_date) {
		super();
		this.notice_cnt = notice_cnt;
		this.notice_index = notice_index;
		this.notice_no = notice_no;
		this.bm_nick = bm_nick;
		this.notice_date = notice_date;
	}

	// 헤더 VO Getter & Setter.
	public String getNotice_cnt() {
		return notice_cnt;
	}

	public void setNotice_cnt(String notice_cnt) {
		this.notice_cnt = notice_cnt;
	}

	public String getNotice_index() {
		return notice_index;
	}

	public void setNotice_index(String notice_index) {
		this.notice_index = notice_index;
	}

	public String getNotice_no() {
		return notice_no;
	}

	public void setNotice_no(String notice_no) {
		this.notice_no = notice_no;
	}

	public String getBm_nick() {
		return bm_nick;
	}

	public void setBm_nick(String bm_nick) {
		this.bm_nick = bm_nick;
	}

	public String getNotice_date() {
		return notice_date;
	}

	public void setNotice_date(String notice_date) {
		this.notice_date = notice_date;
	}

	@Override
	public String toString() {
		return "HeaderVO [notice_cnt=" + notice_cnt + ", notice_index=" + notice_index + ", notice_no=" + notice_no
				+ ", bm_nick=" + bm_nick + ", notice_date=" + notice_date + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
}
