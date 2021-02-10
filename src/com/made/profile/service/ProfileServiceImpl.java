package com.made.profile.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.made.profile.dao.ProfileDAO;
import com.made.profile.vo.ComplainVO;
import com.made.profile.vo.FeedImageVO;
import com.made.profile.vo.FollowVO;
import com.made.profile.vo.MemberVO;

/**
 * 프로필 정보 Service class
 * 
 * @since JDK 1.8
 * @version 1.0.0.RELEASE
 * @author HbeMember_KIM YUNG JIN, KIM HYUNG SEOP
 */
@Service
public class ProfileServiceImpl implements ProfileService {
	private Logger log = Logger.getLogger(ProfileServiceImpl.class);
	
	@Autowired private ProfileDAO profileDAO;

// ======================================= 프로필 정보 =======================================
	// 프로필 정보_일반회원 정보 조회.
	@Override
	public MemberVO profileSelectMember(MemberVO memberVO) {
		log.info("ProfileServiceImpl - profileSelectMember(MemberVO) request :: " + memberVO.toString());
		
		return profileDAO.profileSelectMember(memberVO);
	}

	// 프로필 정보_메인 게시글 조회.
	@Override
	public List<FeedImageVO> profileSelectFeed(MemberVO memberVO) {
		log.info("ProfileServiceImpl - profileSelectMember(MemberVO) request :: " + memberVO.toString());
		
		return profileDAO.profileSelectFeed(memberVO);
	}
	
	// 프로필 정보_팔로우 조회(AJAX)
	@Override
	public FollowVO profileSelectFollow(MemberVO memberVO) {
		log.info("ProfileServiceImpl - profileSelectFollow(MemberVO) request :: " + memberVO.toString());
		
		return profileDAO.profileSelectFollow(memberVO);
	}

	// 프로필 정보_팔로워 팝업창 목록 조회(AJAX)
	@Override
	public List<FollowVO> profileSelectFollowing(MemberVO memberVO) {
		log.info("ProfileServiceImpl - profileSelectFollowing(MemberVO) request :: " + memberVO.toString());
		
		return profileDAO.profileSelectFollowing(memberVO);
	}

	// 프로필 정보_팔로우 팝업창 목록 조회(AJAX)
	@Override
	public List<FollowVO> profileSelectFollowers(MemberVO memberVO) {
		log.info("ProfileServiceImpl - profileSelectFollower(MemberVO) request :: " + memberVO.toString());
		
		return profileDAO.profileSelectFollowers(memberVO);
	}

	// 프로필 정보_팔로우 삽입(AJAX)
	@Override
	public int ProfileInsertFollow(FollowVO followVO) {
		log.info("ProfileServiceImpl - ProfileInsertFollow(FollowVO) request :: " + followVO.toString());
		
		return profileDAO.ProfileInsertFollow(followVO);
	}

	// 프로필 정보_팔로우 삭제(AJAX)
	@Override
	public int ProfileDeleteFollow(FollowVO followVO) {
		log.info("ProfileServiceImpl - ProfileDeleteFollow(FollowVO) request :: " + followVO.toString());
		
		return profileDAO.ProfileDeleteFollow(followVO);
	}

// ======================================= 프로필 편집 =======================================
	// 프로필 편집_프로필 편집 조회(AJAX)
	@Override
	public MemberVO editSelectMember(MemberVO memberVO) {
		log.info("ProfileServiceImpl - editSelectMember(MemberVO) request :: " + memberVO.toString());
		
		return profileDAO.editSelectMember(memberVO);
	}
	
	// 프로필 편집_프로필 편집 갱신(AJAX)
	@Override
	public int editUpdateMember(MemberVO memberVO) {
		log.info("ProfileServiceImpl - editUpdateMember(MemberVO) request :: " + memberVO.toString());
		
		return profileDAO.editUpdateMember(memberVO);
	}
	
	// 프로필 편집_프로필 사진 갱신(AJAX)
	@Override
	public int editUpdateProfileImg(MemberVO memberVO) {
		log.info("ProfileServiceImpl - editUpdateProfileImg(MemberVO) request :: " + memberVO.toString());
		
		return profileDAO.editUpdateProfileImg(memberVO);
	}
	
	// 프로필 편집_프로필 사진 삭제(AJAX)
	@Override
	public int editDeleteProfileImg(MemberVO memberVO) {
		log.info("ProfileServiceImpl - editDeleteProfileImg(MemberVO) request :: " + memberVO.toString());
		
		return profileDAO.editDeleteProfileImg(memberVO);
	}
	
	// 프로필 편집_비밀번호 변경(AJAX)
	@Override
	public int editUpdatePw(MemberVO memberVO) {
		log.info("ProfileServiceImpl - editUpdatePw(MemberVO) request :: " + memberVO.toString());
		
		return profileDAO.editUpdatePw(memberVO);
	}
	
	// 프로필 편집_계정 삭제(AJAX)
	@Override
	public int editDeleteMember(MemberVO memberVO) {
		log.info("ProfileServiceImpl - editDeleteMember(MemberVO) request :: " + memberVO.toString());
		
		return profileDAO.editDeleteMember(memberVO);
	}
	
	// 프로필 편집_1:1문의 목록 조회(AJAX)
	@Override
	public List<ComplainVO> editSelectListComplain(ComplainVO complainVO) {
		log.info("ProfileServiceImpl - editSelectListComplain(ComplainVO) request :: " + complainVO.toString());
		
		return profileDAO.editSelectListComplain(complainVO);
	}
	
	// 프로필 편집_1:1문의 상세 조회(AJAX)
	@Override
	public ComplainVO editSelectViewComplain(ComplainVO complainVO) {
		log.info("ProfileServiceImpl - editSelectViewComplain(ComplainVO) request :: " + complainVO.toString());
		
		return profileDAO.editSelectViewComplain(complainVO);
	}

	// 프로필 편집_1:1문의 갱신(AJAX)
	@Override
	public int editUpdateComplain(ComplainVO complainVO) {
		log.info("ProfileServiceImpl - editUpdateComplain(ComplainVO) request :: " + complainVO.toString());
		
		return profileDAO.editUpdateComplain(complainVO);
	}
	
	// 프로필 편집_1:1문의 삭제(AJAX)
	@Override
	public int editDeleteComplain(ComplainVO complainVO) {
		log.info("ProfileServiceImpl - editDeleteComplain(ComplainVO) request :: " + complainVO.toString());
		
		return profileDAO.editDeleteComplain(complainVO);
	}
}