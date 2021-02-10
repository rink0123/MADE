package com.made.common.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.made.common.vo.ComplainVO;
import com.made.common.vo.HeaderVO;

/**
 * 헤더 DAO class
 * 
 * @since JDK 1.8
 * @version 1.0.0.RELEASE
 * @author HbeMember_Common
 */
public class HeaderDAOImpl extends SqlSessionDaoSupport implements HeaderDAO {
	private static Logger log = Logger.getLogger(HeaderDAOImpl.class);
	
	private final String PACKAGE_PATH = "mybatis.query.";

// ======================================= 헤더_알림 =======================================
	// 헤더_알림 수 조회.
	@Override
	public HeaderVO headerSelectNoticeCnt(String session_bm_no) {
		log.info("HeaderDAOImpl - headerSelectNoticeCnt(HttpSession) request :: " + session_bm_no);
		
		return (HeaderVO) getSqlSession().selectOne(PACKAGE_PATH + "headerSelectNoticeCnt", session_bm_no);
	}

	// 헤더_알림 내용 조회.
	@Override
	public List<HeaderVO> headerSelectNoticeContent(String session_bm_no) {
		log.info("HeaderDAOImpl - headerSelectNoticeContent(HttpSession) request :: " + session_bm_no);
		
		List<HeaderVO> headerSelectNoticeContent = getSqlSession().selectList(PACKAGE_PATH + "headerSelectNoticeContent", session_bm_no);
		
		return headerSelectNoticeContent;
	}
	
// ======================================= 헤더_1:1문의 삽입 =======================================
	@Override
	public int headerInsertComplain(ComplainVO complain_param) {
		log.info("HeaderDAOImpl - headerInsertComplain(ComplainVO) request :: " + complain_param);
		
		return (int) getSqlSession().insert(PACKAGE_PATH + "headerInsertComplain", complain_param);
	}
}