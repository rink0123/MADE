package com.made.feed.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.made.feed.service.FeedService;
import com.made.feed.vo.FeedImageVO;

/**
 * 메인 게시글_목록 Controller
 * 
 * @since JDK 1.8
 * @version 1.0.0.RELEASE
 * @author HbeMember_SHIN MIN GYU
 */
@Controller
public class FeedController {
	private static Logger log = Logger.getLogger(FeedController.class);
	
	@Autowired
	private FeedService feedService;
	
// ======================================= 메인 게시글_목록 =======================================
	@RequestMapping(value = "/feed")
	public ModelAndView feedSelect(ModelAndView modelAndView, HttpSession session) throws Exception {
		log.info("FeedController - feedSelect(ModelAndView) request :: 진입(start)");
		
		modelAndView.setViewName("feed/feedlist");
		
		return modelAndView;
	}
	
// ======================================= 메인 게시글_목록 조회 =======================================
	@RequestMapping(value = "/feedselect_list")
	public @ResponseBody ResponseEntity<String> feedSelectList(ModelAndView modelAndView, @ModelAttribute FeedImageVO feed_param) throws Exception {
		log.info("FeedController - feedSelect(FeedImageVO) request :: " + feed_param.toString());
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		// 성공=1 & 실패=0 체크.
		int result = 0;
		
		try {
			List<FeedImageVO> feedselect_list = feedService.feedSelectList(feed_param);
			
			// 메인 게시글_목록이 null이 아니고 값이 비어있지 않은 경우.
			if(feedselect_list != null && !feedselect_list.isEmpty()) {
				result = 1;
				
				modelMap.put("result", result);
				modelMap.put("feedselect_list", feedselect_list);
				
				log.info("FeedController - feedSelectList(FeedImageVO) response :: " + feedselect_list.toString());
				
			// 메인 게시글_목록이 null이고 값이 비어있는 경우.
			} else {
				modelMap.put("result", result);
				log.info("FeedController - feedSelectList(FeedImageVO) response :: " + feedselect_list.toString());
			}
			
		} catch (Exception e) {
			log.info("FeedController - feedSelectList(FeedImageVO) response :: RESULT_ERROR");
		}
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/json; charset=UTF-8");
		
		return new ResponseEntity<String>(mapper.writeValueAsString(modelMap), responseHeaders, HttpStatus.CREATED);
	}
}