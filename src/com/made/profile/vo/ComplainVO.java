package com.made.profile.vo;

/**
 * 1:1문의 VO
 * 
 * @since JDK 1.8
 * @version 1.0.0.RELEASE
 * @author HbeMember_PARK DAE BEOM
 */
public class ComplainVO {
	private String cp_no;         // 1:1문의 번호
	private String cp_title;      // 1:1문의 제목
	private String bm_no;         // 일반회원 번호(작성자)
	private String cp_content;    // 1:1문의 내용
	private String cp_updatedate; // 1:1문의 수정일
	
	private String cpans_content; // 1:1문의-답변 내용
	
	private String cp_curpage; // 현재 페이지
	
	/**
	 * 1:1문의 VO 기본 생성자.
	 */
	public ComplainVO() {
	}

	/**
	 * 1:1문의 VO 매개변수 있는 생성자.
	 * 
	 * @param cp_no         1:1문의 번호     
	 * @param cp_title      1:1문의 제목     
	 * @param bm_no         일반회원 번호(작성자) 
	 * @param cp_content    1:1문의 내용     
	 * @param cp_updatedate 1:1문의 수정일    
	 * @param cpans_content 1:1문의-답변 내용
	 * @param cp_curpage       현재 페이지
	 */
	public ComplainVO(String cp_no, String cp_title, String bm_no, String cp_content, String cp_updatedate, String cpans_content,
			String cp_curpage) {
		this.cp_no = cp_no;
		this.cp_title = cp_title;
		this.bm_no = bm_no;
		this.cp_content = cp_content;
		this.cp_updatedate = cp_updatedate;
		this.cpans_content = cpans_content;
		this.cp_curpage = cp_curpage;
	}

	// 1:1문의 VO Getter & Setter.
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

	public String getCp_updatedate() {
		return cp_updatedate;
	}

	public void setCp_updatedate(String cp_updatedate) {
		this.cp_updatedate = cp_updatedate;
	}

	public String getCpans_content() {
		return cpans_content;
	}

	public void setCpans_content(String cpans_content) {
		this.cpans_content = cpans_content;
	}

	public String getcp_curpage() {
		return cp_curpage;
	}

	public void setcp_curpage(String cp_curpage) {
		this.cp_curpage = cp_curpage;
	}

	@Override
	public String toString() {
		return "ComplainVO [cp_no=" + cp_no + ", cp_title=" + cp_title + ", bm_no=" + bm_no + ", cp_content="
				+ cp_content + ", cp_updatedate=" + cp_updatedate + ", cpans_content=" + cpans_content + ", cp_curpage="
				+ cp_curpage + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
}