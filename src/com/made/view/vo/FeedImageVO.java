package com.made.view.vo;

/**
 * 메인 게시글 이미지 VO.
 * 
 * @since JDK 1.8
 * @version 1.0.0.RELEASE
 * @author HbeMember_KANG MIN SUNG
 */
public class FeedImageVO {
	// 메인 게시글 이미지 VO 변수.
	private String fi_no;   // 메인 게시글 이미지 번호
	private String fi_path; // 메인 게시글 이미지 파일경로
	private String fi_file; // 메인 게시글 이미지 파일명
	private String feed_no; // 메인 게시글 번호

	/**
	 * 메인 게시글 이미지 VO 기본 생성자.
	 */
	public FeedImageVO() {
	}


	/**
	 * 메인 게시글 이미지 VO 매개변수 있는 생성자_ALL.
	 * 
	 * @param fi_no   메인 게시글 이미지 번호   
	 * @param fi_path 메인 게시글 이미지 파일경로 
	 * @param fi_file 메인 게시글 이미지 파일명  
	 * @param feed_no 메인 게시글 번호       
	 */
	public FeedImageVO(String fi_no, String fi_path, String fi_file, String feed_no) {
		this.fi_no = fi_no;
		this.fi_path = fi_path;
		this.fi_file = fi_file;
		this.feed_no = feed_no;
	}

	// 메인 게시글 이미지 VO Getter & Setter.
	public String getFi_no() {
		return fi_no;
	}

	public void setFi_no(String fi_no) {
		this.fi_no = fi_no;
	}

	public String getFi_path() {
		return fi_path;
	}

	public void setFi_path(String fi_path) {
		this.fi_path = fi_path;
	}

	public String getFi_file() {
		return fi_file;
	}

	public void setFi_file(String fi_file) {
		this.fi_file = fi_file;
	}

	public String getFeed_no() {
		return feed_no;
	}

	public void setFeed_no(String feed_no) {
		this.feed_no = feed_no;
	}

	@Override
	public String toString() {
		return "FeedImageVO [fi_no=" + fi_no + ", fi_path=" + fi_path + ", fi_file=" + fi_file + ", feed_no=" + feed_no
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
}