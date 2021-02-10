package com.made.view.dao;

import java.util.List;

import com.made.view.vo.CmtAnswerVO;
import com.made.view.vo.CommentsVO;
import com.made.view.vo.FeedImageVO;
import com.made.view.vo.FeedLikeVO;
import com.made.view.vo.FeedVO;
import com.made.view.vo.FollowVO;
import com.made.view.vo.ReportVO;
import com.made.view.vo.RpAnswerVO;

/**
 * 메인 게시글_상세보기 DAO interface
 * 
 * @since JDK 1.8
 * @version 1.0.0.RELEASE
 * @author HbeMember_KANG MIN SUNG
 */
public interface FeedViewDAO {
// ======================================= 메인 게시글_상세보기_신고-답변 =======================================
	// 신고-답변 조회
	public List<RpAnswerVO> feedViewSelectRpAnswer();
	
	// 신고 삽입
	public int feedViewInsertReport(ReportVO feed_param);
	
// ======================================= 메인 게시글_상세보기_이미지 =======================================
	// 이미지 조회
	public List<FeedImageVO> feedViewSelectImage(FeedVO feed_param);
	
	// 이미지 삽입
	public int feedViewInsertImage(FeedImageVO feed_param);
	
	// 이미지 삭제
	public int feedViewDeleteImage(FeedImageVO feed_param);
	
// ======================================= 메인 게시글_상세보기_글내용 =======================================
	// 글내용 조회
	public FeedVO feedViewSelectContent(FeedVO feed_param);

	// 글내용 삽입
	public int feedViewInsertContent(FeedVO feed_param);
	
	// 글내용 삽입 체크
	public FeedVO feedViewContentChk(FeedVO feed_param);
	
	// 글내용 수정
	public int feedViewUpdateContent(FeedVO feed_param);

	// 글내용 삭제
	public int feedViewDeleteContent(FeedVO feed_param);
	
// ======================================= 메인 게시글_상세보기_좋아요 =======================================
	// 좋아요 조회
	public FeedLikeVO feedViewSelectLike(FeedLikeVO feed_param);
	
	// 좋아요 삽입
	public int feedViewInsertLike(FeedLikeVO feed_param);
	
	// 좋아요 삭제
	public int feedViewDeleteLike(FeedLikeVO feed_param);
	
// ======================================= 메인 게시글_상세보기_팔로우 =======================================
	// 팔로우 조회
	public FollowVO feedViewSelectFollow(FollowVO feed_param);

	// 팔로우 삽입
	public int feedViewInsertFollow(FollowVO feed_param);

	// 팔로우 삭제
	public int feedViewDeleteFollow(FollowVO feed_param);
	
// ======================================= 메인 게시글_상세보기_댓글 =======================================
	// 댓글 조회
	public List<CommentsVO> feedViewSelectComments(FeedVO feed_param);
	
	// 댓글 삽입
	public int feedViewInsertComments(CommentsVO feed_param);
	
	// 댓글 수정
	public int feedViewUpdateComments(CommentsVO feed_param);
	
	// 댓글 삭제
	public int feedViewDeleteComments(CommentsVO feed_param);
	
// ======================================= 메인 게시글_상세보기_댓글-답글 =======================================
	// 댓글-답글 조회
	public List<CmtAnswerVO> feedViewSelectCmtAnswer();
	
	// 댓글-답글 삽입
	public int feedViewInsertCmtAnswer(CmtAnswerVO feed_param);
	
	// 댓글-답글 수정
	public int feedViewUpdateCmtAnswer(CmtAnswerVO feed_param);
	
	// 댓글-답글 삭제
	public int feedViewDeleteCmtAnswer(CmtAnswerVO feed_param);
}