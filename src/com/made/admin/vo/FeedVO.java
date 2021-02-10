package com.made.admin.vo;

/**
 * 메인 게시글 VO.
 * 
 * @since JDK 1.8
 * @version 1.0.0.RELEASE
 * @author HbeMember_SONG SEUNG HYUN
 */
public class FeedVO {
	private String feed_no;         // 메인 게시글 번호
	private String bm_id;           // 일반회원 아이디(작성자)
	private String feed_type_cd;    // 메인 게시글 메뉴 구분 코드
	private String feed_title;      // 메인 게시글 제목
	private String feed_content;    // 메인 게시글 내용
	private String feed_insertdate; // 메인 게시글 등록일
	private String feed_updatedate; // 메인 게시글 수정일
	private String feed_deletedate; // 메인 게시글 삭제일
	private String photo_cnt;       // 메인 게시글 사진 수
	private String curpage;         // 현재 페이지
	private String totalpage;       // 총 페이지
	
	/**
	 * 메인 게시글 VO 기본 생성자.
	 */
	public FeedVO() {
	}

	/**
	 * 메인 게시글 VO 매개변수 있는 생성자 ALL.
	 * 
	 * @param feed_no         메인 게시글 번호
	 * @param bm_id           일반회원 아이디(작성자)
	 * @param feed_type_cd    메인 게시글 메뉴 구분 코드
	 * @param feed_title      메인 게시글 제목
	 * @param feed_content    메인 게시글 내용
	 * @param feed_insertdate 메인 게시글 등록일
	 * @param feed_updatedate 메인 게시글 수정일
	 * @param feed_deletedate 메인 게시글 삭제일
	 * @param photo_cnt       메인 게시글 사진 수
	 * @param curpage         현재 페이지
	 * @param totalpage       총 페이지
	 */
	public FeedVO(String feed_no, String bm_id, String feed_type_cd, String feed_title, String feed_content,
			String feed_insertdate, String feed_updatedate, String feed_deletedate, String photo_cnt, String curpage,
			String totalpage) {
		this.feed_no = feed_no;
		this.bm_id = bm_id;
		this.feed_type_cd = feed_type_cd;
		this.feed_title = feed_title;
		this.feed_content = feed_content;
		this.feed_insertdate = feed_insertdate;
		this.feed_updatedate = feed_updatedate;
		this.feed_deletedate = feed_deletedate;
		this.photo_cnt = photo_cnt;
		this.curpage = curpage;
		this.totalpage = totalpage;
	}

	// 메인 게시글 VO Getter & Setter.
	public String getFeed_no() {
		return feed_no;
	}

	public void setFeed_no(String feed_no) {
		this.feed_no = feed_no;
	}

	public String getBm_id() {
		return bm_id;
	}

	public void setBm_id(String bm_id) {
		this.bm_id = bm_id;
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

	public String getFeed_content() {
		return feed_content;
	}

	public void setFeed_content(String feed_content) {
		this.feed_content = feed_content;
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

	public String getPhoto_cnt() {
		return photo_cnt;
	}

	public void setPhoto_cnt(String photo_cnt) {
		this.photo_cnt = photo_cnt;
	}

	public String getCurpage() {
		return curpage;
	}

	public void setCurpage(String curpage) {
		this.curpage = curpage;
	}

	public String getTotalpage() {
		return totalpage;
	}

	public void setTotalpage(String totalpage) {
		this.totalpage = totalpage;
	}

	@Override
	public String toString() {
		return "FeedVO [feed_no=" + feed_no + ", bm_id=" + bm_id + ", feed_type_cd=" + feed_type_cd + ", feed_title="
				+ feed_title + ", feed_content=" + feed_content + ", feed_insertdate=" + feed_insertdate
				+ ", feed_updatedate=" + feed_updatedate + ", feed_deletedate=" + feed_deletedate + ", photo_cnt="
				+ photo_cnt + ", curpage=" + curpage + ", totalpage=" + totalpage + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
}