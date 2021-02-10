package com.made.common.dao;

import java.util.List;

import com.made.common.vo.ComplainVO;
import com.made.common.vo.HeaderVO;

/**
 * 헤더 DAO interface
 * 
 * @since JDK 1.8
 * @version 1.0.0.RELEASE
 * @author HbeMember_Common
 */
public interface HeaderDAO {
// ======================================= 헤더_알림 =======================================
	// 헤더_알림 수 조회.
	public HeaderVO headerSelectNoticeCnt(String session_bm_no);

	// 헤더_알림 내용 조회.
	public List<HeaderVO> headerSelectNoticeContent(String session_bm_no);
	
// ======================================= 헤더_1:1문의 삽입 =======================================
	public int headerInsertComplain(ComplainVO complain_param);
}