package com.made.view.vo;

/**
 * 신고-답변 VO
 * 
 * @since JDK 1.8
 * @version 1.0.0.RELEASE
 * @author HbeMember_KANG MIN SUNG
 */
public class RpAnswerVO {
	// 신고-답변 VO 변수.
	private String rpans_no;
	private String rpans_title;
	private String rpans_content;
	
	/**
	 * 신고-답변 VO 기본 생성자
	 */
	public RpAnswerVO() {
	}

	/**
	 * 신고-답변 VO 매개변수 있는 생성자_ALL
	 * 
	 * @param rpans_no
	 * @param rpans_title
	 * @param rpans_content
	 */
	public RpAnswerVO(String rpans_no, String rpans_title, String rpans_content) {
		this.rpans_no = rpans_no;
		this.rpans_title = rpans_title;
		this.rpans_content = rpans_content;
	}

	// 신고-답변 VO Getter & Setter.
	public String getRpans_no() {
		return rpans_no;
	}

	public void setRpans_no(String rpans_no) {
		this.rpans_no = rpans_no;
	}

	public String getRpans_title() {
		return rpans_title;
	}

	public void setRpans_title(String rpans_title) {
		this.rpans_title = rpans_title;
	}

	public String getRpans_content() {
		return rpans_content;
	}

	public void setRpans_content(String rpans_content) {
		this.rpans_content = rpans_content;
	}

	@Override
	public String toString() {
		return "RpAnswerVO [rpans_no=" + rpans_no + ", rpans_title=" + rpans_title + ", rpans_content=" + rpans_content
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
}