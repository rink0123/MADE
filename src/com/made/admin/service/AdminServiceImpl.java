package com.made.admin.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.made.admin.dao.AdminDAO;
import com.made.admin.vo.FeedVO;
import com.made.admin.vo.MemberVO;

/**
 * 관리자 페이지 Service class
 * 
 * @since JDK 1.8
 * @version 1.0.0.RELEASE
 * @author HbeMember_SONG SEUNG HYUN
 */
@Service
public class AdminServiceImpl implements AdminService {
	private static Logger log = Logger.getLogger(AdminServiceImpl.class);

	@Autowired
	private AdminDAO adminDAO;

// ======================================= 회원 관리 =======================================
	// 회원 관리_목록 조회
	@Override
	public List<MemberVO> adminMemberSelectList(MemberVO admin_params) {
		log.info("AdminController - adminMemberSelectList(MemberVO) request :: " + admin_params.toString());
		
		return adminDAO.adminMemberSelectList(admin_params);
	}
	
// ======================================= 회원 관리 =======================================
	// 회원 관리_목록 조회
	@Override
	public List<FeedVO> adminFeedSelectList(FeedVO admin_params) {
		log.info("AdminController - adminFeedSelectList(FeedVO) request :: " + admin_params.toString());
		
		return adminDAO.adminFeedSelectList(admin_params);
	}
}