package com.made.view.vo;

/**
 * 댓글-답글 VO
 * 
 * @since JDK 1.8
 * @version 1.0.0.RELEASE
 * @author HbeMember_KANG MIN SUNG
 */
public class CmtAnswerVO {
	// 댓글-답글 VO 댓글-답글 변수.
	private String cmtans_no;         // 댓글-답글 번호
	private String cmtans_cmt_no;     // 댓글 번호
	private String cmtans_bm_no;      // 댓글-답글 작성자(회원번호)
	private String cmtans_bm_nick;    // 댓글-답글 작성자(닉네임)
	private String cmtans_bm_img;     // 댓글-답글 작성자(프로필 사진)
	private String cmtans_content;    // 댓글-답글 내용
	private String cmtans_deleteyn;   // 댓글-답글 삭제여부
	private String cmtans_insertdate; // 댓글-답글 등록일
	private String cmtans_updatedate; // 댓글-답글 수정일
	private String cmtans_deletedate; // 댓글-답글 삭제일
	
	/**
	 * 댓글-답글 VO 기본 생성자
	 */
	public CmtAnswerVO() {
	}

	/**
	 * 댓글-답글 VO 매개변수 있는 생성자_ALL
	 * 
	 * @param cmtans_no         댓글-답글 번호
	 * @param cmtans_cmt_no     댓글 번호
	 * @param cmtans_bm_no      댓글-답글 작성자(회원번호)
	 * @param cmtans_bm_nick    댓글-답글 작성자(닉네임)
	 * @param cmtans_bm_img     댓글-답글 작성자(프로필 사진)
	 * @param cmtans_content    댓글-답글 내용
	 * @param cmtans_deleteyn   댓글-답글 삭제여부
	 * @param cmtans_insertdate 댓글-답글 등록일
	 * @param cmtans_updatedate 댓글-답글 수정일
	 * @param cmtans_deletedate 댓글-답글 삭제일
	 */
	public CmtAnswerVO(String cmtans_no, String cmtans_cmt_no, String cmtans_bm_no, String cmtans_bm_nick,
			String cmtans_bm_img, String cmtans_content, String cmtans_deleteyn, String cmtans_insertdate,
			String cmtans_updatedate, String cmtans_deletedate) {
		this.cmtans_no = cmtans_no;
		this.cmtans_cmt_no = cmtans_cmt_no;
		this.cmtans_bm_no = cmtans_bm_no;
		this.cmtans_bm_nick = cmtans_bm_nick;
		this.cmtans_bm_img = cmtans_bm_img;
		this.cmtans_content = cmtans_content;
		this.cmtans_deleteyn = cmtans_deleteyn;
		this.cmtans_insertdate = cmtans_insertdate;
		this.cmtans_updatedate = cmtans_updatedate;
		this.cmtans_deletedate = cmtans_deletedate;
	}

	// 댓글-답글 Getter & Setter.
	public String getCmtans_no() {
		return cmtans_no;
	}

	public void setCmtans_no(String cmtans_no) {
		this.cmtans_no = cmtans_no;
	}

	public String getCmtans_cmt_no() {
		return cmtans_cmt_no;
	}

	public void setCmtans_cmt_no(String cmtans_cmt_no) {
		this.cmtans_cmt_no = cmtans_cmt_no;
	}

	public String getCmtans_bm_no() {
		return cmtans_bm_no;
	}

	public void setCmtans_bm_no(String cmtans_bm_no) {
		this.cmtans_bm_no = cmtans_bm_no;
	}

	public String getCmtans_bm_nick() {
		return cmtans_bm_nick;
	}

	public void setCmtans_bm_nick(String cmtans_bm_nick) {
		this.cmtans_bm_nick = cmtans_bm_nick;
	}

	public String getCmtans_bm_img() {
		return cmtans_bm_img;
	}

	public void setCmtans_bm_img(String cmtans_bm_img) {
		this.cmtans_bm_img = cmtans_bm_img;
	}

	public String getCmtans_content() {
		return cmtans_content;
	}

	public void setCmtans_content(String cmtans_content) {
		this.cmtans_content = cmtans_content;
	}

	public String getCmtans_deleteyn() {
		return cmtans_deleteyn;
	}

	public void setCmtans_deleteyn(String cmtans_deleteyn) {
		this.cmtans_deleteyn = cmtans_deleteyn;
	}

	public String getCmtans_insertdate() {
		return cmtans_insertdate;
	}

	public void setCmtans_insertdate(String cmtans_insertdate) {
		this.cmtans_insertdate = cmtans_insertdate;
	}

	public String getCmtans_updatedate() {
		return cmtans_updatedate;
	}

	public void setCmtans_updatedate(String cmtans_updatedate) {
		this.cmtans_updatedate = cmtans_updatedate;
	}

	public String getCmtans_deletedate() {
		return cmtans_deletedate;
	}

	public void setCmtans_deletedate(String cmtans_deletedate) {
		this.cmtans_deletedate = cmtans_deletedate;
	}

	@Override
	public String toString() {
		return "CmtAnswerVO [cmtans_no=" + cmtans_no + ", cmtans_cmt_no=" + cmtans_cmt_no + ", cmtans_bm_no="
				+ cmtans_bm_no + ", cmtans_bm_nick=" + cmtans_bm_nick + ", cmtans_bm_img=" + cmtans_bm_img
				+ ", cmtans_content=" + cmtans_content + ", cmtans_deleteyn=" + cmtans_deleteyn + ", cmtans_insertdate="
				+ cmtans_insertdate + ", cmtans_updatedate=" + cmtans_updatedate + ", cmtans_deletedate="
				+ cmtans_deletedate + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
}