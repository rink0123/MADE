package com.made.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.made.admin.service.AdminService;
import com.made.admin.vo.FeedVO;
import com.made.admin.vo.MemberVO;

/**
 * 관리자 페이지 Controller
 * 
 * @since JDK 1.8
 * @version 1.0.0.RELEASE
 * @author HbeMember_SONG SEUNG HYUN
 */
@Controller
public class AdminController {
	private static Logger log = Logger.getLogger(AdminController.class);
	
	@Autowired
	private AdminService adminService;
	
// ======================================= 관리자 페이지 =======================================
	@RequestMapping(value = "/admin")
	public ModelAndView adminSelect(ModelAndView modelAndView) throws Exception {
		log.info("AdminController - adminSelect(ModelAndView) request :: 진입(start)");
		
		modelAndView.setViewName("admin/admin");
		
		return modelAndView;
	}
	
// ======================================= 회원 관리 =======================================
	// 회원 관리_목록 조회.
	@RequestMapping(value = "/adminmember_selectlist")
	public @ResponseBody ResponseEntity<String> adminMemberSelectList(ModelAndView modelAndView, @ModelAttribute MemberVO admin_params) throws Exception {
		log.info("AdminController - adminMemberSelectList(MemberVO) request :: " + admin_params.toString());
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		try {
			List<MemberVO> adminmember_selectlist = adminService.adminMemberSelectList(admin_params);
			
			if(adminmember_selectlist != null && !adminmember_selectlist.isEmpty()) {
				modelMap.put("adminmember_selectlist", adminmember_selectlist);
				
				log.info("AdminController - adminMemberSelectList(MemberVO) response :: " + adminmember_selectlist);
				
			} else {
				modelMap.put("adminmember_selectlist", adminmember_selectlist);
				
				log.info("AdminController - adminMemberSelectList(MemberVO) response :: " + adminmember_selectlist);
			}
			
		} catch (Exception e) {
			log.info("AdminController - adminMemberSelectList(MemberVO) response :: RESULT_ERROR");
		}
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/json; charset=UTF-8");
		
		return new ResponseEntity<String>(mapper.writeValueAsString(modelMap), responseHeaders, HttpStatus.CREATED);
	}
	
// ======================================= 게시판 관리 =======================================
	// 게시판 관리_목록 조회.
	@RequestMapping(value = "/adminfeed_selectlist")
	public @ResponseBody ResponseEntity<String> adminFeedSelectList(ModelAndView modelAndView, @ModelAttribute FeedVO admin_params) throws Exception {
		log.info("AdminController - adminFeedSelectList(MemberVO) request :: " + admin_params.toString());
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		try {
			List<FeedVO> adminfeed_selectlist = adminService.adminFeedSelectList(admin_params);
			
			if(adminfeed_selectlist != null && !adminfeed_selectlist.isEmpty()) {
				modelMap.put("adminfeed_selectlist", adminfeed_selectlist);
				
				log.info("AdminController - adminFeedSelectList(MemberVO) response :: " + adminfeed_selectlist);
				
			} else {
				modelMap.put("adminfeed_selectlist", adminfeed_selectlist);
				
				log.info("AdminController - adminFeedSelectList(MemberVO) response :: " + adminfeed_selectlist);
			}
			
		} catch (Exception e) {
			log.info("AdminController - adminFeedSelectList(MemberVO) response :: RESULT_ERROR");
		}
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/json; charset=UTF-8");
		
		return new ResponseEntity<String>(mapper.writeValueAsString(modelMap), responseHeaders, HttpStatus.CREATED);
	}
}