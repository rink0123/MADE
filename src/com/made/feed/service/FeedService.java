package com.made.feed.service;

import java.util.List;

import com.made.feed.vo.FeedImageVO;

/**
 * 메인 게시글_목록 Service interface
 * 
 * @since JDK 1.8
 * @version 1.0.0.RELEASE
 * @author HbeMember_SHIN MIN GYU
 */
public interface FeedService {
// ======================================= 메인 게시글_목록 =======================================
	// 메인 게시글_목록 조회
	public List<FeedImageVO> feedSelectList(FeedImageVO feed_param);
}