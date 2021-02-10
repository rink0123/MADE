package com.made.admin.dao;

import java.util.List;

import com.made.admin.vo.FeedVO;
import com.made.admin.vo.MemberVO;

/**
 * 관리자 페이지 DAO interface
 * 
 * @since JDK 1.8
 * @version 1.0.0.RELEASE
 * @author HbeMember_SONG SEUNG HYUN
 */
public interface AdminDAO {
// ======================================= 회원 관리 =======================================
	// 회원 관리_목록 조회
	public List<MemberVO> adminMemberSelectList(MemberVO admin_params);
	
// ======================================= 게시판 관리 =======================================
	// 게시판 관리_목록 조회
	public List<FeedVO> adminFeedSelectList(FeedVO admin_params);
}