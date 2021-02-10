package com.made.view.vo;

/**
 * 신고 VO
 * 
 * @since JDK 1.8
 * @version 1.0.0.RELEASE
 * @author HbeMember_KANG MIN SUNG
 */
public class ReportVO {
	// 신고 VO 변수.
	private String rp_no;         // 신고 번호
	private String bm_no;         // 신고자(회원번호)
	private String rp_bno;        // 신고한 글(모든글번호)
	private String rpans_content; // 신고-답변 내용
	private String rp_insertdate; // 신고 등록일
	private String rp_replydate;  // 신고 답변일
	
	/**
	 * 신고 VO 기본 생성자.
	 */
	public ReportVO() {
	}

	/**
	 * 신고 VO 매개변수 있는 생성자.
	 * 
	 * @param rp_no         신고 번호         
	 * @param bm_no         신고자(회원번호)     
	 * @param rp_bno        신고한 글(모든글번호)  
	 * @param rpans_content 신고-답변 내용      
	 * @param rp_insertdate 신고 등록일        
	 * @param rp_replydate  신고 답변일        
	 */
	public ReportVO(String rp_no, String bm_no, String rp_bno, String rpans_content, String rp_insertdate,
			String rp_replydate) {
		this.rp_no = rp_no;
		this.bm_no = bm_no;
		this.rp_bno = rp_bno;
		this.rpans_content = rpans_content;
		this.rp_insertdate = rp_insertdate;
		this.rp_replydate = rp_replydate;
	}

	// 신고 VO Getter & Setter.
	public String getRp_no() {
		return rp_no;
	}

	public void setRp_no(String rp_no) {
		this.rp_no = rp_no;
	}

	public String getBm_no() {
		return bm_no;
	}

	public void setBm_no(String bm_no) {
		this.bm_no = bm_no;
	}

	public String getRp_bno() {
		return rp_bno;
	}

	public void setRp_bno(String rp_bno) {
		this.rp_bno = rp_bno;
	}

	public String getRpans_content() {
		return rpans_content;
	}

	public void setRpans_content(String rpans_content) {
		this.rpans_content = rpans_content;
	}

	public String getRp_insertdate() {
		return rp_insertdate;
	}

	public void setRp_insertdate(String rp_insertdate) {
		this.rp_insertdate = rp_insertdate;
	}

	public String getRp_replydate() {
		return rp_replydate;
	}

	public void setRp_replydate(String rp_replydate) {
		this.rp_replydate = rp_replydate;
	}

	@Override
	public String toString() {
		return "ReportVO [rp_no=" + rp_no + ", bm_no=" + bm_no + ", rp_bno=" + rp_bno + ", rpans_content="
				+ rpans_content + ", rp_insertdate=" + rp_insertdate + ", rp_replydate=" + rp_replydate
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
}