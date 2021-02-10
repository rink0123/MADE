package com.made.view.vo;

/**
 * 메인 게시글 VO
 * 
 * @since JDK 1.8
 * @version 1.0.0.RELEASE
 * @author HbeMember_KANG MIN SUNG
 */
public class FeedVO {
	// 메인 게시글 VO 변수.
	private String feed_no;         // 메인 게시글 번호
	private String feed_type_cd;    // 메인 게시글 메뉴 구분 코드
	private String feed_title;      // 메인 게시글 제목
	private String bm_no;           // 메인 게시글 작성자(회원번호)
	private String feed_content;    // 메인 게시글 내용
	private String feed_deleteyn;   // 메인 게시글 삭제여부
	private String feed_insertdate; // 메인 게시글 등록일
	private String feed_updatedate; // 메인 게시글 수정일
	private String feed_deletedate; // 메인 게시글 삭제일
	
	// 메인 게시글 VO 상세보기 변수.
	private String bm_nick; // 회원 닉네임
	private String bm_img;  // 회원 프로필 사진
	
	/**
	 * 메인 게시글_상세보기 VO 기본 생성자
	 */
	public FeedVO() {
	}
	
	/**
	 * 메인 게시글_상세보기 VO 매개변수 있는 생성자_ALL
	 * 
	 * @param feed_no         메인 게시글 번호         
	 * @param feed_type_cd    메인 게시글 메뉴 구분 코드   
	 * @param feed_title      메인 게시글 제목         
	 * @param bm_no           메인 게시글 작성자(회원번호)  
	 * @param feed_content    메인 게시글 내용         
	 * @param feed_deleteyn   메인 게시글 삭제여부       
	 * @param feed_insertdate 메인 게시글 등록일        
	 * @param feed_updatedate 메인 게시글 수정일        
	 * @param feed_deletedate 메인 게시글 삭제일        
	 * @param bm_nick         회원 닉네임   
	 * @param bm_img          회원 프로필 사진
	 */
	public FeedVO(String feed_no, String feed_type_cd, String feed_title, String bm_no, String feed_content,
			String feed_deleteyn, String feed_insertdate, String feed_updatedate, String feed_deletedate,
			String bm_nick, String bm_img) {
		this.feed_no = feed_no;
		this.feed_type_cd = feed_type_cd;
		this.feed_title = feed_title;
		this.bm_no = bm_no;
		this.feed_content = feed_content;
		this.feed_deleteyn = feed_deleteyn;
		this.feed_insertdate = feed_insertdate;
		this.feed_updatedate = feed_updatedate;
		this.feed_deletedate = feed_deletedate;
		this.bm_nick = bm_nick;
		this.bm_img = bm_img;
	}

	// 메인 게시글_상세보기 VO(게시글) Getter & Setter.
	public String getFeed_no() {
		return feed_no;
	}

	public void setFeed_no(String feed_no) {
		this.feed_no = feed_no;
	}

	public String getFeed_type_cd() {
		return feed_type_cd;
	}

	public void setFeed_type_cd(String feed_type_cd) {
		this.feed_type_cd = feed_type_cd;
	}

	public String getFeed_title() {
		return feed_title;
	}

	public void setFeed_title(String feed_title) {
		this.feed_title = feed_title;
	}

	public String getBm_no() {
		return bm_no;
	}

	public void setBm_no(String bm_no) {
		this.bm_no = bm_no;
	}

	public String getFeed_content() {
		return feed_content;
	}

	public void setFeed_content(String feed_content) {
		this.feed_content = feed_content;
	}

	public String getFeed_deleteyn() {
		return feed_deleteyn;
	}

	public void setFeed_deleteyn(String feed_deleteyn) {
		this.feed_deleteyn = feed_deleteyn;
	}

	public String getFeed_insertdate() {
		return feed_insertdate;
	}

	public void setFeed_insertdate(String feed_insertdate) {
		this.feed_insertdate = feed_insertdate;
	}

	public String getFeed_updatedate() {
		return feed_updatedate;
	}

	public void setFeed_updatedate(String feed_updatedate) {
		this.feed_updatedate = feed_updatedate;
	}

	public String getFeed_deletedate() {
		return feed_deletedate;
	}

	public void setFeed_deletedate(String feed_deletedate) {
		this.feed_deletedate = feed_deletedate;
	}

	// 메인 게시글_상세보기(회원) VO Getter & Setter.
	public String getBm_nick() {
		return bm_nick;
	}

	public void setBm_nick(String bm_nick) {
		this.bm_nick = bm_nick;
	}

	public String getBm_img() {
		return bm_img;
	}

	public void setBm_img(String bm_img) {
		this.bm_img = bm_img;
	}

	@Override
	public String toString() {
		return "FeedVO [feed_no=" + feed_no + ", feed_type_cd=" + feed_type_cd + ", feed_title=" + feed_title
				+ ", bm_no=" + bm_no + ", feed_content=" + feed_content + ", feed_deleteyn=" + feed_deleteyn
				+ ", feed_insertdate=" + feed_insertdate + ", feed_updatedate=" + feed_updatedate + ", feed_deletedate="
				+ feed_deletedate + ", bm_nick=" + bm_nick + ", bm_img=" + bm_img + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
}