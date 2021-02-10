package com.made.view.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.made.view.vo.CmtAnswerVO;
import com.made.view.vo.CommentsVO;
import com.made.view.vo.FeedImageVO;
import com.made.view.vo.FeedLikeVO;
import com.made.view.vo.FeedVO;
import com.made.view.vo.FollowVO;
import com.made.view.vo.ReportVO;
import com.made.view.vo.RpAnswerVO;

/**
 * 상세보기 DAO class
 * 
 * @since JDK 1.8
 * @version 1.0.0.RELEASE
 * @author HbeMember_KANG MIN SUNG
 */
public class FeedViewDAOImpl extends SqlSessionDaoSupport implements FeedViewDAO {
	private static Logger log = Logger.getLogger(FeedViewDAOImpl.class);
	
	private final String PACKAGE_PATH = "mybatis.query.";

// ======================================= 메인 게시글_상세보기_신고 =======================================
	// 신고-답변 조회
	@Override
	public List<RpAnswerVO> feedViewSelectRpAnswer() {
		log.info("FeedViewDAOImpl - feedViewSelectRpAnser() request :: 진입(Start)");
		
		List<RpAnswerVO> feedViewSelectRpAnser = getSqlSession().selectList(PACKAGE_PATH + "feedViewSelectRpAnswer");
		
		return feedViewSelectRpAnser; 
	}
	
	// 신고 삽입
	@Override
	public int feedViewInsertReport(ReportVO feed_param) {
		log.info("FeedViewDAOImpl - feedViewInsertReport(ReportVO) request :: " + feed_param.toString());
		
		return (int) getSqlSession().insert(PACKAGE_PATH + "feedViewInsertReport", feed_param);
	}
	
// ======================================= 메인 게시글_상세보기_이미지 =======================================
	// 이미지 조회
	@Override
	public List<FeedImageVO> feedViewSelectImage(FeedVO feed_param) {
		log.info("FeedViewDAOImpl - feedViewSelectImage(FeedVO) request :: " + feed_param.toString());
		
		List<FeedImageVO> feedViewSelectImage = getSqlSession().selectList(PACKAGE_PATH + "feedViewSelectImage", feed_param);
		
		return feedViewSelectImage;
	}
	
	// 이미지 삽입
	@Override
	public int feedViewInsertImage(FeedImageVO feed_param) {
		log.info("FeedViewDAOImpl - feedViewInsertImage(FeedImageVO) request :: " + feed_param.toString());
		
		return (int) getSqlSession().insert(PACKAGE_PATH + "feedViewInsertImage", feed_param);
	}
	
	// 이미지 삭제
	@Override
	public int feedViewDeleteImage(FeedImageVO feed_param) {
		log.info("FeedViewDAOImpl - feedViewDeleteImage(FeedImageVO) request :: " + feed_param.toString());
		
		return (int) getSqlSession().delete(PACKAGE_PATH + "feedViewDeleteImage", feed_param);
	}

// ======================================= 메인 게시글_상세보기_글내용 =======================================
	// 글내용 조회
	@Override
	public FeedVO feedViewSelectContent(FeedVO feed_param) {
		log.info("FeedViewDAOImpl - feedViewSelectContent(FeedVO) request :: " + feed_param.toString());
		
		return (FeedVO) getSqlSession().selectOne(PACKAGE_PATH + "feedViewSelectContent", feed_param);
	}
	
	// 글내용 삽입
	@Override
	public int feedViewInsertContent(FeedVO feed_param) {
		log.info("FeedViewDAOImpl - feedViewInsertContent(FeedVO) request :: " + feed_param.toString());
		
		return (int) getSqlSession().insert(PACKAGE_PATH + "feedViewInsertContent", feed_param);
	}
	
	// 글내용 삽입 체크
	@Override
	public FeedVO feedViewContentChk(FeedVO feed_param) {
		log.info("FeedViewDAOImpl - feedViewContentChk(FeedVO) request :: " + feed_param.toString());
		
		return (FeedVO) getSqlSession().selectOne(PACKAGE_PATH + "feedViewContentChk", feed_param);
	}
	
	// 글내용 수정
	@Override
	public int feedViewUpdateContent(FeedVO feed_param) {
		log.info("FeedViewDAOImpl - feedViewUpdateContent(FeedVO) request :: " + feed_param.toString());
		
		return (int) getSqlSession().update(PACKAGE_PATH + "feedViewUpdateContent", feed_param);
	}

	// 글내용 삭제
	@Override
	public int feedViewDeleteContent(FeedVO feed_param) {
		log.info("FeedViewDAOImpl - feedViewDeleteContent(FeedVO) request :: " + feed_param.toString());
		
		return (int) getSqlSession().update(PACKAGE_PATH + "feedViewDeleteContent", feed_param);
	}

// ======================================= 메인 게시글_상세보기_좋아요 =======================================
	// 좋아요 조회
	@Override
	public FeedLikeVO feedViewSelectLike(FeedLikeVO feed_param) {
		log.info("FeedViewDAOImpl - feedViewSelectLike(FeedLikeVO) request :: " + feed_param.toString());
		
		return (FeedLikeVO) getSqlSession().selectOne(PACKAGE_PATH + "feedViewSelectLike", feed_param);
	}
	
	// 좋아요 삽입
	@Override
	public int feedViewInsertLike(FeedLikeVO feed_param) {
		log.info("FeedViewDAOImpl - feedViewInsertLike(FeedLikeVO) request :: " + feed_param.toString());
		
		return (int) getSqlSession().insert(PACKAGE_PATH + "feedViewInsertLike", feed_param);
	}
	
	// 좋아요 삭제
	@Override
	public int feedViewDeleteLike(FeedLikeVO feed_param) {
		log.info("FeedViewDAOImpl - feedViewDeleteLike(FeedLikeVO) request :: " + feed_param.toString());
		
		return (int) getSqlSession().delete(PACKAGE_PATH + "feedViewDeleteLike", feed_param);
	}

// ======================================= 메인 게시글_상세보기_팔로우 =======================================
	// 팔로우 조회
	@Override
	public FollowVO feedViewSelectFollow(FollowVO feed_param) {
		log.info("FeedViewDAOImpl - feedViewSelectFollow(FollowVO) request :: " + feed_param.toString());
		
		return (FollowVO) getSqlSession().selectOne(PACKAGE_PATH + "feedViewSelectFollow", feed_param);
	}
	
	// 팔로우 삽입
	@Override
	public int feedViewInsertFollow(FollowVO feed_param) {
		log.info("FeedViewDAOImpl - feedViewInsertFollow(FollowVO) request :: " + feed_param.toString());
		
		return (int) getSqlSession().insert(PACKAGE_PATH + "feedViewInsertFollow", feed_param);
	}
	
	// 팔로우 삭제
	@Override
	public int feedViewDeleteFollow(FollowVO feed_param) {
		log.info("FeedViewDAOImpl - feedViewDeleteFollow(FollowVO) request :: " + feed_param.toString());
		
		return (int) getSqlSession().delete(PACKAGE_PATH + "feedViewDeleteFollow", feed_param);
	}
	
// ======================================= 메인 게시글_상세보기_댓글 =======================================
	// 댓글 조회
	@Override
	public List<CommentsVO> feedViewSelectComments(FeedVO feed_param) {
		log.info("FeedViewDAOImpl - feedViewSelectComments(FeedVO) request :: " + feed_param.toString());
		
		List<CommentsVO> feedViewSelectComments = getSqlSession().selectList(PACKAGE_PATH + "feedViewSelectComments", feed_param);
		
		return feedViewSelectComments;
	}
	
	// 댓글 삽입
	@Override
	public int feedViewInsertComments(CommentsVO feed_param) {
		log.info("FeedViewDAOImpl - feedViewInsertComments(CommentsVOㄴ) request :: " + feed_param.toString());
		
		return (int) getSqlSession().insert(PACKAGE_PATH + "feedViewInsertComments", feed_param);
	}

	// 댓글 수정
	@Override
	public int feedViewUpdateComments(CommentsVO feed_param) {
		log.info("FeedViewDAOImpl - feedViewUpdateComments(CommentsVO) request :: " + feed_param.toString());
		
		return (int) getSqlSession().update(PACKAGE_PATH + "feedViewUpdateComments", feed_param);
	}

	// 댓글 삭제
	@Override
	public int feedViewDeleteComments(CommentsVO feed_param) {
		log.info("FeedViewDAOImpl - feedViewDeleteComments(CommentsVO) request :: " + feed_param.toString());
		
		return (int) getSqlSession().update(PACKAGE_PATH + "feedViewDeleteComments", feed_param);
	}
	
// ======================================= 메인 게시글_상세보기_댓글-답글 =======================================
	// 댓글-답글 조회
	@Override
	public List<CmtAnswerVO> feedViewSelectCmtAnswer() {
		log.info("FeedViewDAOImpl - feedViewSelectCmtAnswer() request :: 진입(Start)");
		
		List<CmtAnswerVO> feedViewSelectCmtAnswer = getSqlSession().selectList(PACKAGE_PATH + "feedViewSelectCmtAnswer");
		
		return feedViewSelectCmtAnswer;
	}

	// 댓글-답글 삽입
	@Override
	public int feedViewInsertCmtAnswer(CmtAnswerVO feed_param) {
		log.info("FeedViewDAOImpl - feedViewSelectCmtAnswer() request :: " + feed_param.toString());
		
		return (int) getSqlSession().update(PACKAGE_PATH + "feedViewInsertCmtAnswer", feed_param);
	}

	// 댓글-답글 수정
	@Override
	public int feedViewUpdateCmtAnswer(CmtAnswerVO feed_param) {
		log.info("FeedViewDAOImpl - feedViewUpdateCmtAnswer(CmtAnswerVO) request :: " + feed_param.toString());
		
		return (int) getSqlSession().update(PACKAGE_PATH + "feedViewUpdateCmtAnswer", feed_param);
	}

	// 댓글-답글 삭제
	@Override
	public int feedViewDeleteCmtAnswer(CmtAnswerVO feed_param) {
		log.info("FeedViewDAOImpl - feedViewDeleteCmtAnswer(CmtAnswerVO) request :: " + feed_param.toString());
		
		return (int) getSqlSession().update(PACKAGE_PATH + "feedViewDeleteCmtAnswer", feed_param);
	}
}