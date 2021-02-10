package com.made.common.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.made.common.dao.HeaderDAO;
import com.made.common.vo.ComplainVO;
import com.made.common.vo.HeaderVO;

/**
 * 헤더 Service class
 * 
 * @since JDK 1.8
 * @version 1.0.0.RELEASE
 * @author HbeMember_JANG JUN HEE
 */
@Service
public class HeaderServiceImpl implements HeaderService {
	@Autowired private HeaderDAO headerDAO;
	
	private static Logger log = Logger.getLogger(HeaderServiceImpl.class);

// ======================================= 헤더_알림 =======================================
	// 헤더_알림 수 조회.
	@Override
	public HeaderVO headerSelectNoticeCnt(String session_bm_no) {
//		log.info("HeaderServiceImpl - headerSelectNoticeCnt(HttpSession) request :: " + session_bm_no);
		
		return headerDAO.headerSelectNoticeCnt(session_bm_no);
	}

	// 헤더_알림 내용 조회.
	@Override
	public List<HeaderVO> headerSelectNoticeContent(String session_bm_no) {
		log.info("HeaderServiceImpl - headerSelectNoticeContent(HttpSession) request :: " + session_bm_no);
		
		return headerDAO.headerSelectNoticeContent(session_bm_no);
	}
	
// ======================================= 헤더_1:1문의 삽입 =======================================
	@Override
	public int headerInsertComplain(ComplainVO complain_param) {
		log.info("HeaderServiceImpl - headerInsertComplain(ComplainVO) request :: " + complain_param);
		
		return headerDAO.headerInsertComplain(complain_param);
	}
}