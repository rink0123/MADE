package com.made.profile.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.made.profile.vo.ComplainVO;
import com.made.profile.vo.FeedImageVO;
import com.made.profile.vo.FollowVO;
import com.made.profile.vo.MemberVO;

/**
 * 프로필 정보 DAO class
 * 
 * @since JDK 1.8
 * @version 1.0.0.RELEASE
 * @author HbeMember_KIM YUNG JIN, KIM HYUNG SEOP
 */
public class ProfileDAOImpl extends SqlSessionDaoSupport implements ProfileDAO {
	private Logger log = Logger.getLogger(ProfileDAOImpl.class);
	
	private final String PACKAGE_PATH = "mybatis.query.";
	
// ======================================= 프로필 정보 =======================================
	// 프로필 정보_일반회원 정보 조회
	@Override
	public MemberVO profileSelectMember(MemberVO memberVO) {
		log.info("ProfileDAOImpl - profileSelect(MemberVO) request :: " + memberVO.toString());
		
		return (MemberVO) getSqlSession().selectOne(PACKAGE_PATH + "profileSelectMember", memberVO);
	}

	// 프로필 정보_메인 게시글 조회
	@Override
	public List<FeedImageVO> profileSelectFeed(MemberVO memberVO) {
		log.info("ProfileDAOImpl - profileSelect(MemberVO) request :: " + memberVO.toString());
		
		List<FeedImageVO> profileSelectFeed = getSqlSession().selectList(PACKAGE_PATH + "profileSelectFeed", memberVO);
		
		return profileSelectFeed;
	}

	// 프로필 정보_팔로우 조회(AJAX)
	@Override
	public FollowVO profileSelectFollow(MemberVO memberVO) {
		log.info("ProfileDAOImpl - profileSelectFollow(MemberVO) request :: " + memberVO.toString());
		
		return (FollowVO) getSqlSession().selectOne(PACKAGE_PATH + "profileSelectFollow", memberVO);
	}
	
	// 프로필 정보_팔로워 팝업창 목록 조회(AJAX)
	@Override
	public List<FollowVO> profileSelectFollowing(MemberVO memberVO) {
		log.info("ProfileDAOImpl - profileSelectFollowing(MemberVO) request :: " + memberVO.toString());
		
		List<FollowVO> profileSelectFollowing = getSqlSession().selectList(PACKAGE_PATH + "profileSelectFollowing", memberVO);
		
		return profileSelectFollowing;
	}

	// 프로필 정보_팔로우 팝업창 목록 조회(AJAX)
	@Override
	public List<FollowVO> profileSelectFollowers(MemberVO memberVO) {
		log.info("ProfileDAOImpl - profileSelectFollower(MemberVO) request :: " + memberVO.toString());
		
		List<FollowVO> profileSelectFollower = getSqlSession().selectList(PACKAGE_PATH + "profileSelectFollowers", memberVO);
		
		return profileSelectFollower;
	}

	// 프로필 정보_팔로우 삽입(AJAX)
	@Override
	public int ProfileInsertFollow(FollowVO followVO) {
		log.info("ProfileDAOImpl - ProfileInsertFollow(FollowVO) request :: " + followVO.toString());
		
		return (int) getSqlSession().insert(PACKAGE_PATH + "ProfileInsertFollow", followVO);
	}

	// 프로필 정보_팔로우 삭제(AJAX)
	@Override
	public int ProfileDeleteFollow(FollowVO followVO) {
		log.info("ProfileDAOImpl - ProfileDeleteFollow(FollowVO) request :: " + followVO.toString());
		
		return (int) getSqlSession().delete(PACKAGE_PATH + "ProfileDeleteFollow", followVO);
	}

// ======================================= 프로필 편집 =======================================
	// 프로필 편집_프로필 편집 조회(AJAX)
	@Override
	public MemberVO editSelectMember(MemberVO memberVO) {
		log.info("ProfileDAOImpl - editSelectMember(MemberVO) request :: " + memberVO.toString());
		
		return (MemberVO) getSqlSession().selectOne(PACKAGE_PATH + "editSelectMember", memberVO);
	}
	
	// 프로필 편집_프로필 편집 갱신(AJAX)
	@Override
	public int editUpdateMember(MemberVO memberVO) {
		log.info("ProfileDAOImpl - editUpdateMember(MemberVO) request :: " + memberVO.toString());
		
		return (int) getSqlSession().update(PACKAGE_PATH + "editUpdateMember", memberVO);
	}
	
	// 프로필 편집_프로필 사진 갱신(AJAX)
	@Override
	public int editUpdateProfileImg(MemberVO memberVO) {
		log.info("ProfileDAOImpl - editUpdateProfileImg(MemberVO) request :: " + memberVO.toString());
		
		return (int) getSqlSession().update(PACKAGE_PATH + "editUpdateProfileImg", memberVO);
	}
	
	// 프로필 편집_프로필 사진 삭제(AJAX)
	@Override
	public int editDeleteProfileImg(MemberVO memberVO) {
		log.info("ProfileDAOImpl - editDeleteProfileImg(MemberVO) request :: " + memberVO.toString());
		
		return (int) getSqlSession().update(PACKAGE_PATH + "editDeleteProfileImg", memberVO);
	}
	
	// 프로필 편집_비밀번호 변경(AJAX)
	@Override
	public int editUpdatePw(MemberVO memberVO) {
		log.info("ProfileDAOImpl - editUpdatePw(MemberVO) request :: " + memberVO.toString());
		
		return (int) getSqlSession().update(PACKAGE_PATH + "editUpdatePw", memberVO);
	}
	
	// 프로필 편집_계정 삭제(AJAX)
	@Override
	public int editDeleteMember(MemberVO memberVO) {
		log.info("ProfileDAOImpl - editDeleteMember(MemberVO) request :: " + memberVO.toString());
		
		return (int) getSqlSession().update(PACKAGE_PATH + "editDeleteMember", memberVO);
	}
	
	// 프로필 편집_1:1문의 목록 조회(AJAX)
	@Override
	public List<ComplainVO> editSelectListComplain(ComplainVO complainVO) {
		log.info("ProfileDAOImpl - editSelectListComplain(ComplainVO) request :: " + complainVO.toString());
		
		List<ComplainVO> eidt_select_complain = getSqlSession().selectList(PACKAGE_PATH + "editSelectListComplain", complainVO);
		
		return eidt_select_complain;
	}
	
	// 프로필 편집_1:1문의 상세 조회(AJAX)
	@Override
	public ComplainVO editSelectViewComplain(ComplainVO complainVO) {
		log.info("ProfileDAOImpl - editSelectViewComplain(ComplainVO) request :: " + complainVO.toString());
		
		return (ComplainVO) getSqlSession().selectOne(PACKAGE_PATH + "editSelectViewComplain", complainVO);
	}

	// 프로필 편집_1:1문의 갱신(AJAX)
	@Override
	public int editUpdateComplain(ComplainVO complainVO) {
		log.info("ProfileDAOImpl - editUpdateComplain(ComplainVO) request :: " + complainVO.toString());
		
		return (int) getSqlSession().update(PACKAGE_PATH + "editUpdateComplain", complainVO);
	}
	
	// 프로필 편집_1:1문의 삭제(AJAX)
	@Override
	public int editDeleteComplain(ComplainVO complainVO) {
		log.info("ProfileDAOImpl - editDeleteComplain(ComplainVO) request :: " + complainVO.toString());
		
		return (int) getSqlSession().update(PACKAGE_PATH + "editDeleteComplain", complainVO);
	}
}