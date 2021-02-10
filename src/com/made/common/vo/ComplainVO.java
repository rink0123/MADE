package com.made.common.vo;

/**
 * 1:1문의 VO
 * 
 * @since JDK 1.8
 * @version 1.0.0.RELEASE
 * @author HbeMember_PARK DAE BEOM
 */
public class ComplainVO {
	private String cp_no;      // 1:1문의 번호
	private String cp_title;   // 1:1문의 제목
	private String bm_no;      // 일반회원 번호(작성자)
	private String cp_content; // 1:1문의 내용
	
	/**
	 * 1:1문의 VO 기본 생성자.
	 */
	public ComplainVO() {
	}
	
	/**
	 * 1:1문의 VO 매개변수 있는 생성자.
	 * @param cp_no      1:1문의 번호
	 * @param cp_title   1:1문의 제목
	 * @param bm_no      일반회원 번호(작성자)
	 * @param cp_content 1:1문의 내용
	 */
	public ComplainVO(String cp_no, String cp_title, String bm_no, String cp_content) {
		this.cp_no = cp_no;
		this.cp_title = cp_title;
		this.bm_no = bm_no;
		this.cp_content = cp_content;
	}

	// 1:1문의 Getter & Setter.
	public String getCp_no() {
		return cp_no;
	}

	public void setCp_no(String cp_no) {
		this.cp_no = cp_no;
	}

	public String getCp_title() {
		return cp_title;
	}

	public void setCp_title(String cp_title) {
		this.cp_title = cp_title;
	}

	public String getBm_no() {
		return bm_no;
	}

	public void setBm_no(String bm_no) {
		this.bm_no = bm_no;
	}

	public String getCp_content() {
		return cp_content;
	}

	public void setCp_content(String cp_content) {
		this.cp_content = cp_content;
	}

	@Override
	public String toString() {
		return "ComplainVO [cp_no=" + cp_no + ", cp_title=" + cp_title + ", bm_no=" + bm_no + ", cp_content="
				+ cp_content + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
}