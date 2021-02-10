package com.made.common.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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

import com.made.common.service.HeaderService;
import com.made.common.vo.ComplainVO;
import com.made.common.vo.HeaderVO;

/**
 * 헤더 Controller
 * 
 * @since JDK 1.8
 * @version 1.0.0.RELEASE
 * @author HbeMember_Common
 */
@Controller
public class HeaderController {
	private static Logger log = Logger.getLogger(HeaderController.class);
	
	@Autowired
	private HeaderService headerService; 
	
// ======================================= 헤더 =======================================
	@RequestMapping(value = "/header")
	public ModelAndView header(ModelAndView modelAndView) throws Exception {
		log.info("HeaderController - header(ModelAndView) request :: 진입(start)");
		
		modelAndView.setViewName("common/header");
		
		return modelAndView;
	}
	
// ======================================= 헤더_알림 수 조회 =======================================
	@RequestMapping(value = "/headerselect_noticecnt")
	public @ResponseBody ResponseEntity<String> headerSelectNoticeCnt(HttpServletRequest request, HttpSession session) throws Exception {
//		log.info("HeaderController - headerSelectNoticeCnt(HttpSession) request :: " + session.getAttribute("session_bm_no"));
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		// 성공&실패&세션 만료 체크.
		int result = 0;
		
		try {
			session = request.getSession(false);
			
			// session 값이 null이 아니거나 요청한 세션 ID가 여전히 유효한 경우.
			if (session != null || request.isRequestedSessionIdValid()) {
				String session_bm_no = (String) session.getAttribute("session_bm_no");
				
				HeaderVO header_select_noticecnt = headerService.headerSelectNoticeCnt(session_bm_no);
				
				// 헤더_알람 수가 null이 아닐 경우.
				if(header_select_noticecnt != null) {
					result = 1; // 성공.
					
					modelMap.put("result", result);
					modelMap.put("header_select_noticecnt", header_select_noticecnt.getNotice_cnt());
					log.info("HeaderController - headerSelectNoticeCnt(HttpSession) response :: " + header_select_noticecnt.toString());
					
				// 헤더_알람 수가 null일 경우.
				} else {
					modelMap.put("result", result);
					modelMap.put("header_select_noticecnt", 0);
					log.info("HeaderController - headerSelectNoticeCnt(HttpSession) response :: " + header_select_noticecnt);
				}
				
			} else {
				result = -1; // 세션 만료.
				
				modelMap.put("result", result);
				log.info("HeaderController - headerSelectNoticeCnt(HttpSession) response :: 세션 만료");
			}
			
		} catch (Exception e) {
			log.info("HeaderController - headerSelectNoticeCnt(HttpSession) response :: RESULT_ERROR");
		}
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/json; charset=UTF-8");
		
		return new ResponseEntity<String>(mapper.writeValueAsString(modelMap), responseHeaders, HttpStatus.CREATED);
	}
	
// ======================================= 헤더_알림 내용 조회 =======================================
	@RequestMapping(value = "/headerselect_noticecontent")
	public @ResponseBody ResponseEntity<String> headerSelectNoticeContent(HttpServletRequest request, HttpSession session) throws Exception {
		log.info("HeaderController - headerSelectNoticeContent(HttpSession) request :: " + session.getAttribute("session_bm_no"));
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		// 성공&실패&세션 만료 체크.
		int result = 0;
		
		try {
			session = request.getSession(false);
			
			// session 값이 null이 아니거나 요청한 세션 ID가 여전히 유효한 경우.
			if (session != null || request.isRequestedSessionIdValid()) {
				String session_bm_no = (String) session.getAttribute("session_bm_no");
				
				List<HeaderVO> header_select_noticecontent = headerService.headerSelectNoticeContent(session_bm_no);
				
				// 헤더_알람 내용가 null이 아니고 값이 비어있지 않을 경우.
				if(header_select_noticecontent != null && !header_select_noticecontent.isEmpty()) {
					result = 1; // 성공.
					
					modelMap.put("result", result);
					modelMap.put("header_select_noticecontent", header_select_noticecontent);
					log.info("HeaderController - headerSelectNoticeContent(HttpSession) response :: " + header_select_noticecontent);
					
					// 헤더_알람 내용가 null일 경우.
				} else {
					modelMap.put("result", result);
					modelMap.put("header_select_noticecontent", header_select_noticecontent);
					log.info("HeaderController - headerSelectNoticeContent(HttpSession) response :: " + header_select_noticecontent);
				}
				
			} else {
				result = -1; // 세션 만료.
				
				modelMap.put("result", result);
				log.info("HeaderController - headerSelectNoticeContent(HttpSession) response :: 세션 만료");
			}
			
		} catch (Exception e) {
			log.info("HeaderController - headerSelectNoticeContent(HttpSession) response :: RESULT_ERROR");
		}
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/json; charset=UTF-8");
		
		return new ResponseEntity<String>(mapper.writeValueAsString(modelMap), responseHeaders, HttpStatus.CREATED);
	}

// ======================================= 헤더_1:1문의 삽입 =======================================
	@RequestMapping(value = "/headerinsert_complain")
	public @ResponseBody ResponseEntity<String> headerInsertComplain(ModelAndView modelAndView, @ModelAttribute ComplainVO complain_param) throws Exception {
		log.info("HeaderController - headerInsertComplain(RpAnswerVO) request :: " + complain_param.toString());
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		try {
			int header_insert_complain = headerService.headerInsertComplain(complain_param);
			
			// 1:1문의 삽입 성공 시.
			if(header_insert_complain != 0) {
				modelMap.put("header_insert_complain", header_insert_complain);
				
				log.info("HeaderController - headerInsertComplain(ComplainVO) response :: " + header_insert_complain);
				
			// 1:1문의 삽입 실패 시.
			} else {
				modelMap.put("header_insert_complain", header_insert_complain);
				
				log.info("HeaderController - headerInsertComplain(ComplainVO) response :: " + header_insert_complain);
			}
			
		} catch (Exception e) {
			log.info("HeaderController - headerInsertComplain(ComplainVO) response :: RESULT_ERROR");
		}
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/json; charset=UTF-8");
		
		return new ResponseEntity<String>(mapper.writeValueAsString(modelMap), responseHeaders, HttpStatus.CREATED);
	}
}