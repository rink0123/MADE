package com.made.view.vo;

/**
 * 댓글 VO
 * 
 * @since JDK 1.8
 * @version 1.0.0.RELEASE
 * @author HbeMember_KANG MIN SUNG
 */
public class CommentsVO {
	// 댓글 VO 댓글 변수.
	private String cmt_no;         // 댓글 번호
	private String cmt_feed_no;    // 메인 게시글 번호
	private String cmt_bm_no;      // 댓글 작성자(회원번호)
	private String cmt_bm_nick;    // 댓글 작성자(닉네임)
	private String cmt_bm_img;     // 댓글 작성자(프로필 사진)
	private String cmt_content;    // 댓글 내용
	private String cmt_deleteyn;   // 댓글 삭제여부
	private String cmt_insertdate; // 댓글 등록일
	private String cmt_updatedate; // 댓글 수정일
	private String cmt_deletedate; // 댓글 삭제일
	private String cmt_cnt;        // 댓글 수
	
	/**
	 * 댓글 VO 기본 생성자
	 */
	public CommentsVO() {
	}

	/**
	 * 댓글 VO 매개변수 있는 생성자_ALL
	 * 
	 * @param cmt_no         댓글 번호            
	 * @param cmt_feed_no    메인 게시글 번호        
	 * @param cmt_bm_no      댓글 작성자(회원번호)     
	 * @param cmt_bm_nick    댓글 작성자(닉네임)      
	 * @param cmt_bm_img     댓글 작성자(프로필 사진)   
	 * @param cmt_content    댓글 내용            
	 * @param cmt_deleteyn   댓글 삭제여부          
	 * @param cmt_insertdate 댓글 등록일           
	 * @param cmt_updatedate 댓글 수정일           
	 * @param cmt_deletedate 댓글 삭제일           
	 * @param cmt_cnt        댓글 수             
	 */
	public CommentsVO(String cmt_no, String cmt_feed_no, String cmt_bm_no, String cmt_bm_nick, String cmt_bm_img,
			String cmt_content, String cmt_deleteyn, String cmt_insertdate, String cmt_updatedate,
			String cmt_deletedate, String cmt_cnt) {
		this.cmt_no = cmt_no;
		this.cmt_feed_no = cmt_feed_no;
		this.cmt_bm_no = cmt_bm_no;
		this.cmt_bm_nick = cmt_bm_nick;
		this.cmt_bm_img = cmt_bm_img;
		this.cmt_content = cmt_content;
		this.cmt_deleteyn = cmt_deleteyn;
		this.cmt_insertdate = cmt_insertdate;
		this.cmt_updatedate = cmt_updatedate;
		this.cmt_deletedate = cmt_deletedate;
		this.cmt_cnt = cmt_cnt;
	}

	// 댓글 VO Getter & Setter.
	public String getCmt_no() {
		return cmt_no;
	}

	public void setCmt_no(String cmt_no) {
		this.cmt_no = cmt_no;
	}

	public String getCmt_feed_no() {
		return cmt_feed_no;
	}

	public void setCmt_feed_no(String cmt_feed_no) {
		this.cmt_feed_no = cmt_feed_no;
	}

	public String getCmt_bm_no() {
		return cmt_bm_no;
	}

	public void setCmt_bm_no(String cmt_bm_no) {
		this.cmt_bm_no = cmt_bm_no;
	}

	public String getCmt_bm_nick() {
		return cmt_bm_nick;
	}

	public void setCmt_bm_nick(String cmt_bm_nick) {
		this.cmt_bm_nick = cmt_bm_nick;
	}

	public String getCmt_bm_img() {
		return cmt_bm_img;
	}

	public void setCmt_bm_img(String cmt_bm_img) {
		this.cmt_bm_img = cmt_bm_img;
	}

	public String getCmt_content() {
		return cmt_content;
	}

	public void setCmt_content(String cmt_content) {
		this.cmt_content = cmt_content;
	}

	public String getCmt_deleteyn() {
		return cmt_deleteyn;
	}

	public void setCmt_deleteyn(String cmt_deleteyn) {
		this.cmt_deleteyn = cmt_deleteyn;
	}

	public String getCmt_insertdate() {
		return cmt_insertdate;
	}

	public void setCmt_insertdate(String cmt_insertdate) {
		this.cmt_insertdate = cmt_insertdate;
	}

	public String getCmt_updatedate() {
		return cmt_updatedate;
	}

	public void setCmt_updatedate(String cmt_updatedate) {
		this.cmt_updatedate = cmt_updatedate;
	}

	public String getCmt_deletedate() {
		return cmt_deletedate;
	}

	public void setCmt_deletedate(String cmt_deletedate) {
		this.cmt_deletedate = cmt_deletedate;
	}

	public String getCmt_cnt() {
		return cmt_cnt;
	}

	public void setCmt_cnt(String cmt_cnt) {
		this.cmt_cnt = cmt_cnt;
	}

	@Override
	public String toString() {
		return "CommentsVO [cmt_no=" + cmt_no + ", cmt_feed_no=" + cmt_feed_no + ", cmt_bm_no=" + cmt_bm_no
				+ ", cmt_bm_nick=" + cmt_bm_nick + ", cmt_bm_img=" + cmt_bm_img + ", cmt_content=" + cmt_content
				+ ", cmt_deleteyn=" + cmt_deleteyn + ", cmt_insertdate=" + cmt_insertdate + ", cmt_updatedate="
				+ cmt_updatedate + ", cmt_deletedate=" + cmt_deletedate + ", cmt_cnt=" + cmt_cnt + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
}