package com.made.admin.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.made.admin.vo.FeedVO;
import com.made.admin.vo.MemberVO;

/**
 * 관리자 페이지 DAO class
 * 
 * @since JDK 1.8
 * @version 1.0.0.RELEASE
 * @author HbeMember_SONG SEUNG HYUN
 */
public class AdminDAOImpl extends SqlSessionDaoSupport implements AdminDAO {
	private static Logger log = Logger.getLogger(AdminDAOImpl.class);
	
	private final String PACKAGE_PATH = "mybatis.query.";

// ======================================= 회원 관리 =======================================
	// 회원 관리_목록 조회
	@Override
	public List<MemberVO> adminMemberSelectList(MemberVO admin_params) {
		log.info("AdminDAOImpl - adminMemberSelectList(MemberVO) request :: " + admin_params.toString());
		
		List<MemberVO> adminmember_selectlist = getSqlSession().selectList(PACKAGE_PATH + "adminMemberSelectList", admin_params);
		
		return adminmember_selectlist;
	}
	
// ======================================= 회원 관리 =======================================
	@Override
	public List<FeedVO> adminFeedSelectList(FeedVO admin_params) {
		log.info("AdminDAOImpl - adminFeedSelectList(FeedVO) request :: " + admin_params.toString());
		
		List<FeedVO> adminfeed_selectlist = getSqlSession().selectList(PACKAGE_PATH + "adminFeedSelectList", admin_params);
		
		return adminfeed_selectlist;
	}
}