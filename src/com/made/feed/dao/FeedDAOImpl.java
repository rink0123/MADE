package com.made.feed.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.made.feed.vo.FeedImageVO;

/**
 * 메인 게시글_목록 DAO class
 * 
 * @since JDK 1.8
 * @version 1.0.0.RELEASE
 * @author HbeMember_SHIN MIN GYU
 */
public class FeedDAOImpl extends SqlSessionDaoSupport implements FeedDAO {
	private static Logger log = Logger.getLogger(FeedDAOImpl.class);
	
	private final String PACKAGE_PATH = "mybatis.query.";

// ======================================= 메인 게시글_목록 =======================================
	// 메인 게시글_목록 조회
	@Override
	public List<FeedImageVO> feedSelectList(FeedImageVO feed_param) {
		log.info("FeedDAOImpl - feedSelect(FeedImageVO) request :: " + feed_param.toString());
		
		List<FeedImageVO> feedSelect = getSqlSession().selectList(PACKAGE_PATH + "feedSelectList", feed_param);
		
		return feedSelect;
	}
}