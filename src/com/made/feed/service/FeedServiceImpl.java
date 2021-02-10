package com.made.feed.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.made.feed.dao.FeedDAO;
import com.made.feed.vo.FeedImageVO;

/**
 * 메인 게시글_목록 Service class
 * 
 * @since JDK 1.8
 * @version 1.0.0.RELEASE
 * @author HbeMember_SHIN MIN GYU
 */
@Service
public class FeedServiceImpl implements FeedService {
	private static Logger log = Logger.getLogger(FeedServiceImpl.class);

	@Autowired
	private FeedDAO feedDAO;

// ======================================= 메인 게시글_목록 =======================================
	// 메인 게시글_목록 조회
	@Override
	public List<FeedImageVO> feedSelectList(FeedImageVO feed_param) {
		log.info("FeedServiceImpl - feedSelect(FeedImageVO) request :: " + feed_param.toString());
		
		return feedDAO.feedSelectList(feed_param);
	}
}
