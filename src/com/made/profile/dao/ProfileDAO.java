package com.made.profile.dao;

import java.util.List;

import com.made.profile.vo.ComplainVO;
import com.made.profile.vo.FeedImageVO;
import com.made.profile.vo.FollowVO;
import com.made.profile.vo.MemberVO;

/**
 * 프로필 정보 DAO interface
 * 
 * @since JDK 1.8
 * @version 1.0.0.RELEASE
 * @author HbeMember_KIM YUNG JIN, KIM HYUNG SEOP
 */
public interface ProfileDAO {
// ======================================= 프로필 정보 =======================================
	// 프로필 정보_일반회원 정보 조회
	public MemberVO profileSelectMember(MemberVO memberVO);
	
	// 프로필 정보_메인 게시글 조회
	public List<FeedImageVO> profileSelectFeed(MemberVO memberVO);
	
	// 프로필 정보_팔로우 조회(AJAX)
	public FollowVO profileSelectFollow(MemberVO memberVO);
	
	// 프로필 정보_팔로워 팝업창 목록 조회(AJAX)
	public List<FollowVO> profileSelectFollowing(MemberVO memberVO);
	
	// 프로필 정보_팔로우 팝업창 목록 조회(AJAX)
	public List<FollowVO> profileSelectFollowers(MemberVO memberVO);
	
	// 프로필 정보_팔로우 삽입(AJAX)
	public int ProfileInsertFollow(FollowVO followVO);
	
	// 프로필 정보_팔로우 삭제(AJAX)
	public int ProfileDeleteFollow(FollowVO followVO);
	
// ======================================= 프로필 편집 =======================================
	// 프로필 편집_프로필 편집 조회(AJAX)
	public MemberVO editSelectMember(MemberVO memberVO);
	
	// 프로필 편집_프로필 편집 갱신(AJAX)
	public int editUpdateMember(MemberVO memberVO);
	
	// 프로필 편집_프로필 사진 갱신(AJAX)
	public int editUpdateProfileImg(MemberVO memberVO);
	
	// 프로필 편집_프로필 사진 삭제(AJAX)
	public int editDeleteProfileImg(MemberVO memberVO);
	
	// 프로필 편집_비밀번호 변경(AJAX)
	public int editUpdatePw(MemberVO memberVO);
	
	// 프로필 편집_계정 삭제(AJAX)
	public int editDeleteMember(MemberVO memberVO);
	
	// 프로필 편집_1:1문의 목록 조회(AJAX)
	public List<ComplainVO> editSelectListComplain(ComplainVO complainVO);
	
	// 프로필 편집_1:1문의 상세 조회(AJAX)
	public ComplainVO editSelectViewComplain(ComplainVO complainVO);
	
	// 프로필 편집_1:1문의 갱신(AJAX)
	public int editUpdateComplain(ComplainVO complainVO);
	
	// 프로필 편집_1:1문의 삭제(AJAX)
	public int editDeleteComplain(ComplainVO complainVO);
}