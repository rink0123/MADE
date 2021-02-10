package com.made.view.vo;

/**
 * 메인 게시글 좋아요 VO
 * 
 * @since JDK 1.8
 * @version 1.0.0.RELEASE
 * @author HbeMember_KANG MIN SUNG
 */
public class FeedLikeVO {
	// 메인 게시글 좋아요 VO 변수.
	private String fl_no;   // 좋아요 번호
	private String feed_no; // 좋아요 메인 게시글 번호
	private String bm_no;   // 좋아요 회원번호
	private String fl_chk;  // 좋아요 체크 
	
	private String session_bm_no; // session 회원 번호
	
	/**
	 * 메인 게시글 좋아요 VO 기본 생성자
	 */
	public FeedLikeVO() {
	}

	/**
	 * 메인 게시글 좋아요 VO 매개변수 있는 생성자_ALL
	 * 
	 * @param fl_no   좋아요 번호         
	 * @param feed_no 좋아요 메인 게시글 번호  
	 * @param bm_no   좋아요 회원번호       
	 * @param fl_chk  좋아요 체크         
	 */
	public FeedLikeVO(String fl_no, String feed_no, String bm_no, String fl_chk) {
		this.fl_no = fl_no;
		this.feed_no = feed_no;
		this.bm_no = bm_no;
		this.fl_chk = fl_chk;
	}

	// 메인 게시글 좋아요 VO Getter & Setter.
	public String getFl_no() {
		return fl_no;
	}

	public void setFl_no(String fl_no) {
		this.fl_no = fl_no;
	}

	public String getFeed_no() {
		return feed_no;
	}

	public void setFeed_no(String feed_no) {
		this.feed_no = feed_no;
	}

	public String getBm_no() {
		return bm_no;
	}

	public void setBm_no(String bm_no) {
		this.bm_no = bm_no;
	}

	public String getFl_chk() {
		return fl_chk;
	}

	public void setFl_chk(String fl_chk) {
		this.fl_chk = fl_chk;
	}
	
	public String getSession_bm_no() {
		return session_bm_no;
	}

	public void setSession_bm_no(String session_bm_no) {
		this.session_bm_no = session_bm_no;
	}

	@Override
	public String toString() {
		return "FeedLikeVO [fl_no=" + fl_no + ", feed_no=" + feed_no + ", bm_no=" + bm_no + ", fl_chk=" + fl_chk
				+ ", session_bm_no=" + session_bm_no + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
}