package com.made.view.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.made.view.dao.FeedViewDAO;
import com.made.view.vo.CmtAnswerVO;
import com.made.view.vo.CommentsVO;
import com.made.view.vo.FeedImageVO;
import com.made.view.vo.FeedLikeVO;
import com.made.view.vo.FeedVO;
import com.made.view.vo.FollowVO;
import com.made.view.vo.ReportVO;
import com.made.view.vo.RpAnswerVO;

/**
 * 메인 게시글_상세보기 Service class
 * 
 * @since JDK 1.8
 * @version 1.0.0.RELEASE
 * @author HbeMember_KANG MIN SUNG
 */
@Service
public class FeedViewServiceImpl implements FeedViewService {
	private static Logger log = Logger.getLogger(FeedViewServiceImpl.class);

	@Autowired
	private FeedViewDAO feedViewDAO;

// ======================================= 메인 게시글_상세보기_신고-답변 =======================================
	// 신고-답변 조회
	@Override
	public List<RpAnswerVO> feedViewSelectRpAnswer() {
		log.info("FeedViewServiceImpl - feedViewSelectRpAnswer() request :: 진입(Start)");
		
		return feedViewDAO.feedViewSelectRpAnswer();
	}
	
	// 신고 삽입
	@Override
	public int feedViewInsertReport(ReportVO feed_param) {
		log.info("FeedViewServiceImpl - feedViewInsertReport(ReportVO) request :: " + feed_param.toString());
		
		return feedViewDAO.feedViewInsertReport(feed_param);
	}
	
// ======================================= 메인 게시글_상세보기_이미지 =======================================
	// 이미지 조회
	@Override
	public List<FeedImageVO> feedViewSelectImage(FeedVO feed_param) {
		log.info("FeedViewServiceImpl - feedViewSelectImage(FeedVO) request :: " + feed_param.toString());

		return feedViewDAO.feedViewSelectImage(feed_param);
	}
	
	// 이미지 삽입
	@Override
	public int feedViewInsertImage(FeedImageVO feed_param) {
		log.info("FeedViewServiceImpl - feedViewInsertImage(FeedImageVO) request :: " + feed_param.toString());
		
		return feedViewDAO.feedViewInsertImage(feed_param);
	}
	
	// 이미지 삭제
	@Override
	public int feedViewDeleteImage(FeedImageVO feed_param) {
		log.info("FeedViewServiceImpl - feedViewDeleteImage(FeedImageVO) request :: " + feed_param.toString());
		
		return feedViewDAO.feedViewDeleteImage(feed_param);
	}
	
// ======================================= 메인 게시글_상세보기_글내용 =======================================
	// 글내용 조회
	@Override
	public FeedVO feedViewSelectContent(FeedVO feed_param) {
		log.info("FeedViewServiceImpl - feedViewSelectContent(FeedVO) request :: " + feed_param.toString());
		
		return feedViewDAO.feedViewSelectContent(feed_param);
	}
	
	// 글내용 삽입
	@Override
	public int feedViewInsertContent(FeedVO feed_param) {
		log.info("FeedViewServiceImpl - feedViewInsertContent(FeedVO) request :: " + feed_param.toString());
		
		return feedViewDAO.feedViewInsertContent(feed_param);
	}
	
	// 글내용 삽입 체크
	@Override
	public FeedVO feedViewContentChk(FeedVO feed_param) {
		log.info("FeedViewServiceImpl - feedViewContentChk(FeedVO) request :: " + feed_param.toString());
		
		return feedViewDAO.feedViewContentChk(feed_param);
	}
	
	// 글내용 수정
	@Override
	public int feedViewUpdateContent(FeedVO feed_param) {
		log.info("FeedViewServiceImpl - feedViewUpdateContent(FeedVO) request :: " + feed_param.toString());
		
		return feedViewDAO.feedViewUpdateContent(feed_param);
	}

	// 글내용 삭제
	@Override
	public int feedViewDeleteContent(FeedVO feed_param) {
		log.info("FeedViewServiceImpl - feedViewDeleteContent(FeedVO) request :: " + feed_param.toString());
		
		return feedViewDAO.feedViewDeleteContent(feed_param);
	}
	
// ======================================= 메인 게시글_상세보기_좋아요 =======================================
	// 좋아요 조회
	@Override
	public FeedLikeVO feedViewSelectLike(FeedLikeVO feed_param) {
		log.info("FeedViewServiceImpl - feedViewSelectLike(FeedLikeVO) request :: " + feed_param.toString());
		
		return feedViewDAO.feedViewSelectLike(feed_param);
	}

	// 좋아요 삽입
	@Override
	public int feedViewInsertLike(FeedLikeVO feed_param) {
		log.info("FeedViewServiceImpl - feedViewInsertLike(FeedLikeVO) request :: " + feed_param.toString());
		
		return feedViewDAO.feedViewInsertLike(feed_param);
	}
	
	// 좋아요 삭제
	@Override
	public int feedViewDeleteLike(FeedLikeVO feed_param) {
		log.info("FeedViewServiceImpl - feedViewDeleteLike(FeedLikeVO) request :: " + feed_param.toString());
		
		return feedViewDAO.feedViewDeleteLike(feed_param);
	}

// ======================================= 메인 게시글_상세보기_팔로우 =======================================
	// 팔로우 조회
	@Override
	public FollowVO feedViewSelectFollow(FollowVO feed_param) {
		log.info("FeedViewServiceImpl - feedViewSelectFollow(FollowVO) request :: " + feed_param.toString());

		return feedViewDAO.feedViewSelectFollow(feed_param);
	}
	
	// 팔로우 삽입
	@Override
	public int feedViewInsertFollow(FollowVO feed_param) {
		log.info("FeedViewServiceImpl - feedViewInsertFollow(FollowVO) request :: " + feed_param.toString());
		
		return feedViewDAO.feedViewInsertFollow(feed_param);
	}

	// 팔로우 삭제
	@Override
	public int feedViewDeleteFollow(FollowVO feed_param) {
		log.info("FeedViewServiceImpl - feedViewDeleteFollow(FollowVO) request :: " + feed_param.toString());
		
		return feedViewDAO.feedViewDeleteFollow(feed_param);
	}

// ======================================= 메인 게시글_상세보기_댓글 =======================================
	// 댓글 조회
	@Override
	public List<CommentsVO> feedViewSelectComments(FeedVO feed_param) {
		log.info("FeedViewServiceImpl - feedViewSelectComments(FeedVO) request :: " + feed_param.toString());

		return feedViewDAO.feedViewSelectComments(feed_param);
	}
	
	// 댓글 삽입
	@Override
	public int feedViewInsertComments(CommentsVO feed_param) {
		log.info("FeedViewServiceImpl - feedViewInsertComments(CommentsVO) request :: " + feed_param.toString());
		
		return feedViewDAO.feedViewInsertComments(feed_param);
	}

	// 댓글 수정
	@Override
	public int feedViewUpdateComments(CommentsVO feed_param) {
		log.info("FeedViewServiceImpl - feedViewUpdateComments(CommentsVO) request :: " + feed_param.toString());
		
		return feedViewDAO.feedViewUpdateComments(feed_param);
	}

	// 댓글 삭제
	@Override
	public int feedViewDeleteComments(CommentsVO feed_param) {
		log.info("FeedViewServiceImpl - feedViewDeleteComments(CommentsVO) request :: " + feed_param.toString());
		
		return feedViewDAO.feedViewDeleteComments(feed_param);
	}
	
// ======================================= 메인 게시글_상세보기_댓글-답글 조회 =======================================
	// 댓글 조회
	@Override
	public List<CmtAnswerVO> feedViewSelectCmtAnswer() {
		log.info("FeedViewServiceImpl - feedViewSelectCmtAnswer() request :: 진입(Start)");
		
		return feedViewDAO.feedViewSelectCmtAnswer();
	}

	@Override
	public int feedViewInsertCmtAnswer(CmtAnswerVO feed_param) {
		log.info("FeedViewServiceImpl - feedViewInsertCmtAnswer(CmtAnswerVO) request :: " + feed_param.toString());
		
		return feedViewDAO.feedViewInsertCmtAnswer(feed_param);
	}

	@Override
	public int feedViewUpdateCmtAnswer(CmtAnswerVO feed_param) {
		log.info("FeedViewServiceImpl - feedViewUpdateCmtAnswer(CmtAnswerVO) request :: " + feed_param.toString());
		
		return feedViewDAO.feedViewUpdateCmtAnswer(feed_param);
	}

	@Override
	public int feedViewDeleteCmtAnswer(CmtAnswerVO feed_param) {
		log.info("FeedViewServiceImpl - feedViewDeleteCmtAnswer(CmtAnswerVO) request :: " + feed_param.toString());
		
		return feedViewDAO.feedViewDeleteCmtAnswer(feed_param);
	}
}