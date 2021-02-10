package com.made.profile.controller;

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

import com.made.profile.service.ProfileService;
import com.made.profile.vo.ComplainVO;
import com.made.profile.vo.FeedImageVO;
import com.made.profile.vo.FollowVO;
import com.made.profile.vo.MemberVO;

/**
 * 프로필 정보 Controller
 * 
 * @since JDK 1.8
 * @version 1.0.0.RELEASE
 * @author HbeMember_KIM YUNG JIN, KIM HYUNG SEOP
 */
@Controller
public class ProfileController {
	private Logger log = Logger.getLogger(ProfileController.class);
	
	@Autowired private ProfileService profileService;
	
// ======================================= 프로필 정보_조회 =======================================
	@RequestMapping(value = "/profile")
	public ModelAndView profileSelect(ModelAndView mav, @ModelAttribute MemberVO memberVO) throws Exception {
		log.info("ProfileController - profileSelect(MemberVO) request :: " + memberVO.toString());
		
		// 프로필 정보_일반회원 정보 조회.
		MemberVO profile_select_member = profileService.profileSelectMember(memberVO);
		int member_result = 0; // 성공:1, 실패:0.
		
		// MemberVO 값이 null이 아닐경우.
		if(profile_select_member != null) {
			member_result = 1; // 성공:1.
			
			mav.addObject("member_result", member_result);
			mav.addObject("profile_select_member", profile_select_member);
			
			log.info("ProfileController - profileSelectMember(MemberVO) response :: " + profile_select_member.toString());
			
		// MemberVO 값이 null일 경우.
		} else {
			// 실패:0.
			mav.addObject("member_result", member_result);
			
			log.info("ProfileController - profileSelectMember(MemberVO) response :: NULL");
		}
		
		// 프로필 정보_메인 게시글 조회.
		List<FeedImageVO> profile_select_feed = profileService.profileSelectFeed(memberVO);
		int feed_result = 0; // 성공:1, 실패:0.
		
		// List<FeedImageVO>가 null이 아니고 값이 비어있지 않을 경우.
		if(profile_select_feed != null && !profile_select_feed.isEmpty()) {
			feed_result = 1; // 성공:1.
			
			mav.addObject("feed_result", feed_result);
			mav.addObject("profile_feed_cnt", profile_select_feed.size());
			mav.addObject("profile_select_feed", profile_select_feed);
			
			log.info("ProfileController - profileSelectFeed(MemberVO) response :: " + feed_result);
			log.info("ProfileController - profileSelectFeed(MemberVO) response :: " + profile_select_feed.size());
			log.info("ProfileController - profileSelectFeed(MemberVO) response :: " + profile_select_feed);
			
		// List<FeedImageVO>가 null이고 값이 비어있을 경우.
		} else {
			// 실패:0.
			mav.addObject("feed_result", feed_result);
			mav.addObject("profile_select_feed", profile_select_feed);
			
			log.info("ProfileController - profileSelectFeed(MemberVO) response :: " + profile_select_feed);
		}
		
		mav.setViewName("profile/profileview");
		
		return mav;
	}

// ======================================= 프로필 정보_팔로우 조회(AJAX) =======================================
	@RequestMapping(value = "/profileselect_follow")
	public @ResponseBody ResponseEntity<String> profileSelectFollow(ModelAndView modelAndView, @ModelAttribute MemberVO memberVO) throws Exception {
		log.info("ProfileController - profileSelectFollow(MemberVO) request :: " + memberVO.toString());
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		try {
			FollowVO profile_select_follow = profileService.profileSelectFollow(memberVO);
			
			// FollowVO 값이 null이 아닐경우.
			if(profile_select_follow != null) {
				modelMap.put("profile_select_follow", profile_select_follow);
				log.info("ProfileController - profileSelectFollow(MemberVO) response :: " + profile_select_follow.getFollow_chk());
				
			// FollowVO 값이 null일 경우.
			} else {
				log.info("ProfileController - profileSelectFollow(MemberVO) response :: NULL");
			}
			
		} catch (Exception e) {
			log.info("ProfileController - profileSelectFollow(MemberVO) response :: RESULT_ERROR");
		}
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/json; charset=UTF-8");
		
		return new ResponseEntity<String>(mapper.writeValueAsString(modelMap), responseHeaders, HttpStatus.CREATED);
	}
	
// ================================== 프로필 정보_팔로워 팝업창 목록 조회(AJAX) ==================================
	@RequestMapping(value = "/profileselect_followers")
	public @ResponseBody ResponseEntity<String> profileSelectFollower(ModelAndView modelAndView, @ModelAttribute MemberVO memberVO) throws Exception {
		log.info("ProfileController - profileSelectFollower(MemberVO) request :: " + memberVO.toString());
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		int result = 0; // 성공:1, 실패:0.
		
		try {
			List<FollowVO> profile_select_followers = profileService.profileSelectFollowers(memberVO);
			
			// List<FollowVO>가 null이 아니고 값이 비어있지 않을 경우.
			if(profile_select_followers != null && !profile_select_followers.isEmpty()) {
				result = 1; // 성공:1.
				
				modelMap.put("result", result);
				modelMap.put("profile_select_followers", profile_select_followers);
				
				log.info("ProfileController - profileSelectFollower(MemberVO) response :: " + profile_select_followers);
				
			// List<FollowVO>가 null이고 값이 비어있을 경우.
			} else {
				// 실패:0.
				modelMap.put("result", result);
				log.info("ProfileController - profileSelectFollower(MemberVO) response :: " + profile_select_followers);
			}
			
		} catch (Exception e) {
			log.info("ProfileController - profileSelectFollower(MemberVO) response :: RESULT_ERROR");
		}
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/json; charset=UTF-8");
		
		return new ResponseEntity<String>(mapper.writeValueAsString(modelMap), responseHeaders, HttpStatus.CREATED);
	}
	
// ================================== 프로필 정보_팔로우 팝업창 목록 조회(AJAX) ==================================
	@RequestMapping(value = "/profileselect_following")
	public @ResponseBody ResponseEntity<String> profileSelectFollowing(ModelAndView modelAndView, @ModelAttribute MemberVO memberVO) throws Exception {
		log.info("ProfileController - profileSelectFollowing(MemberVO) request :: " + memberVO.toString());
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		int result = 0; // 성공:1, 실패:0.
		
		try {
			List<FollowVO> profile_select_following = profileService.profileSelectFollowing(memberVO);
			
			// List<FollowVO>가 null이 아니고 값이 비어있지 않을 경우.
			if(profile_select_following != null && !profile_select_following.isEmpty()) {
				result = 1; // 성공:1.
				
				modelMap.put("result", result);
				modelMap.put("profile_select_following", profile_select_following);
				
				log.info("ProfileController - profileSelectFollowing(MemberVO) response :: " + profile_select_following);
			
			// List<FollowVO>가 null이고 값이 비어있을 경우.
			} else {
				// 실패:0.
				modelMap.put("result", result);
				log.info("ProfileController - profileSelectFollowing(MemberVO) response :: " + profile_select_following);
			}
			
		} catch (Exception e) {
			log.info("ProfileController - profileSelectFollowing(MemberVO) response :: RESULT_ERROR");
		}
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/json; charset=UTF-8");
		
		return new ResponseEntity<String>(mapper.writeValueAsString(modelMap), responseHeaders, HttpStatus.CREATED);
	}
	
// =================================== 프로필 정보_팔로우 삽입(AJAX) ===================================
	@RequestMapping(value = "/profileinsert_follow")
	public @ResponseBody ResponseEntity<String> ProfileInsertFollow(ModelAndView modelAndView, @ModelAttribute FollowVO followVO) throws Exception {
		log.info("ProfileController - ProfileInsertFollow(FollowVO) request :: " + followVO.toString());
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		try {
			int profile_insert_follow = profileService.ProfileInsertFollow(followVO);
			
			// DB_INSERT 1행 완료 시.
			if(profile_insert_follow == 1) {
				modelMap.put("profile_insert_follow", profile_insert_follow);
				log.info("ProfileController - ProfileInsertFollow(FollowVO) response :: " + profile_insert_follow);
				
			// DB_INSERT 0행 완료 시.
			} else if(profile_insert_follow == 0) {
				modelMap.put("profile_insert_follow", profile_insert_follow);
				log.info("ProfileController - ProfileInsertFollow(FollowVO) response :: " + profile_insert_follow);
			}
			
		} catch (Exception e) {
			log.info("ProfileController - feedViewInsertFollow(FollowVO) response :: RESULT_ERROR");
		}
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/json; charset=UTF-8");
		
		return new ResponseEntity<String>(mapper.writeValueAsString(modelMap), responseHeaders, HttpStatus.CREATED);
	}
	
// =================================== 프로필 정보_팔로우 삭제(AJAX) ===================================
	@RequestMapping(value = "/profiledelete_follow")
	public @ResponseBody ResponseEntity<String> ProfileDeleteFollow(ModelAndView modelAndView, @ModelAttribute FollowVO followVO) throws Exception {
		log.info("ProfileController - ProfileDeleteFollow(FollowVO) request :: " + followVO.toString());
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		try {
			int profile_delete_follow = profileService.ProfileDeleteFollow(followVO);
			
			// DB_DELETE 1행 완료 시.
			if(profile_delete_follow == 1) {
				modelMap.put("profile_delete_follow", profile_delete_follow);
				log.info("ProfileController - ProfileDeleteFollow(FollowVO) response :: " + profile_delete_follow);
				
			// DB_DELETE 0행 완료 시.
			} else if(profile_delete_follow == 0) {
				modelMap.put("profile_delete_follow", profile_delete_follow);
				log.info("ProfileController - ProfileDeleteFollow(FollowVO) response :: " + profile_delete_follow);
			}
			
		} catch (Exception e) {
			log.info("ProfileController - ProfileDeleteFollow(FollowVO) response :: RESULT_ERROR");
		}
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/json; charset=UTF-8");
		
		return new ResponseEntity<String>(mapper.writeValueAsString(modelMap), responseHeaders, HttpStatus.CREATED);
	}
	
// =================================== 프로필 편집_조회 ===================================
	@RequestMapping(value = "/edit")
	public ModelAndView profileEdit(ModelAndView mav, @ModelAttribute MemberVO memberVO) {
		log.info("ProfileController - profileEdit(MemberVO) request :: " + memberVO.toString());
		
		mav.setViewName("profile/profile_edit");
		
		return mav;
	}
	
// =================================== 프로필 편집_프로필 편집 조회(AJAX) ===================================
	@RequestMapping(value = "/editselect_member")
	public @ResponseBody ResponseEntity<String> editSelectMember(ModelAndView modelAndView, @ModelAttribute MemberVO memberVO) throws Exception {
		log.info("ProfileController - editSelectMember(MemberVO) request :: " + memberVO.toString());
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		int result = 0; // 성공:1, 실패:0.
		
		try {
			MemberVO edit_select_member = profileService.editSelectMember(memberVO);
			
			// MemberVO가 null이 아닐 경우.
			if(edit_select_member != null) {
				result = 1; // 성공:1.
				
				modelMap.put("result", result);
				modelMap.put("edit_select_member", edit_select_member);
				
				log.info("ProfileController - editSelectMember(MemberVO) response :: " + edit_select_member.toString());
			
			// MemberVO가 null일 경우.
			} else {
				// 실패:0.
				modelMap.put("result", result);
				log.info("ProfileController - editSelectMember(MemberVO) response :: NULL");
			}
			
		} catch (Exception e) {
			log.info("ProfileController - editSelectMember(MemberVO) response :: RESULT_ERROR");
		}
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/json; charset=UTF-8");
		
		return new ResponseEntity<String>(mapper.writeValueAsString(modelMap), responseHeaders, HttpStatus.CREATED);
	}
	
//=================================== 프로필 편집_프로필 사진 갱신(AJAX) ===================================
	@RequestMapping(value = "/editupdate_prorileimg")
	public @ResponseBody ResponseEntity<String> editUpdateProfileImg(ModelAndView mav, HttpServletRequest request, HttpSession session ,@ModelAttribute MemberVO memberVO) throws Exception {
		log.info("ProfileController - editUpdateProfileImg(MemberVO) request :: " + memberVO.toString());
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		try {
			session = request.getSession(false);
			int edit_update_prorileimg = 0;
			
			// session 값이 null이 아니거나 요청한 세션 ID가 여전히 유효한 경우 session, cookie null 초기화.
			if (session != null || request.isRequestedSessionIdValid()) {
				edit_update_prorileimg = profileService.editUpdateProfileImg(memberVO);
				
				// DB_UPDATE 1행 완료 시.
				if(edit_update_prorileimg == 1) {
					session.setAttribute("session_bm_img", memberVO.getBm_img());
					
					modelMap.put("edit_update_prorileimg", edit_update_prorileimg);
					log.info("ProfileController - editUpdateProfileImg(MemberVO) response :: " + edit_update_prorileimg);
					
					// DB_UPDATE 0행 완료 시.
				} else if(edit_update_prorileimg == 0) {
					modelMap.put("edit_update_prorileimg", edit_update_prorileimg);
					log.info("ProfileController - editUpdateProfileImg(MemberVO) response :: " + edit_update_prorileimg);
				}
				
			} else {
				edit_update_prorileimg = -1;
						
				modelMap.put("edit_update_prorileimg", edit_update_prorileimg);
				log.info("ProfileController - editUpdateProfileImg(MemberVO) response 세션 만료 :: " + session);
			}
			
		} catch (Exception e) {
			log.info("ProfileController - editUpdateProfileImg(MemberVO) response :: RESULT_ERROR");
		}
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/json; charset=UTF-8");
		
		return new ResponseEntity<String>(mapper.writeValueAsString(modelMap), responseHeaders, HttpStatus.CREATED);
	}
	
//=================================== 프로필 편집_프로필 사진 삭제(AJAX) ===================================
	@RequestMapping(value = "/editdelete_prorileimg")
	public @ResponseBody ResponseEntity<String> editDeleteProfileImg(ModelAndView mav, HttpServletRequest request, HttpSession session ,@ModelAttribute MemberVO memberVO) throws Exception {
		log.info("ProfileController - editDeleteProfileImg(MemberVO) request :: " + memberVO.toString());
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		try {
			session = request.getSession(false);
			int edit_delete_prorileimg = 0;
			
			// session 값이 null이 아니거나 요청한 세션 ID가 여전히 유효한 경우 session, cookie null 초기화.
			if (session != null || request.isRequestedSessionIdValid()) {
				edit_delete_prorileimg = profileService.editDeleteProfileImg(memberVO);
				
				// DB_UPDATE 1행 완료 시.
				if(edit_delete_prorileimg == 1) {
					session.setAttribute("session_bm_img", "");
					
					modelMap.put("edit_delete_prorileimg", edit_delete_prorileimg);
					log.info("ProfileController - editDeleteProfileImg(MemberVO) response :: " + edit_delete_prorileimg);
					
				// DB_UPDATE 0행 완료 시.
				} else if(edit_delete_prorileimg == 0) {
					modelMap.put("edit_delete_prorileimg", edit_delete_prorileimg);
					log.info("ProfileController - editDeleteProfileImg(MemberVO) response :: " + edit_delete_prorileimg);
				}
				
			} else {
				edit_delete_prorileimg = -1;
				
				modelMap.put("edit_delete_prorileimg", edit_delete_prorileimg);
				log.info("ProfileController - editDeleteProfileImg(MemberVO) response 세션 만료 :: " + session);
			}
			
		} catch (Exception e) {
			log.info("ProfileController - editDeleteProfileImg(MemberVO) response :: RESULT_ERROR");
		}
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/json; charset=UTF-8");
		
		return new ResponseEntity<String>(mapper.writeValueAsString(modelMap), responseHeaders, HttpStatus.CREATED);
	}
	
//=================================== 프로필 편집_프로필 편집 갱신(AJAX) ===================================
	@RequestMapping(value = "/editupdate_member")
	public @ResponseBody ResponseEntity<String> editUpdateMember(ModelAndView modelAndView, @ModelAttribute MemberVO memberVO) throws Exception {
		log.info("ProfileController - editUpdateMember(MemberVO) request :: " + memberVO.toString());
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		try {
			int edit_update_member = profileService.editUpdateMember(memberVO);
			
			// DB_UPDATE 1행 완료 시.
			if(edit_update_member == 1) {
				modelMap.put("edit_update_member", edit_update_member);
				log.info("ProfileController - editUpdateMember(MemberVO) response :: " + edit_update_member);
				
			// DB_UPDATE 0행 완료 시.
			} else if(edit_update_member == 0) {
				modelMap.put("edit_update_member", edit_update_member);
				log.info("ProfileController - editUpdateMember(MemberVO) response :: " + edit_update_member);
			}
			
		} catch (Exception e) {
			log.info("ProfileController - editUpdateMember(MemberVO) response :: RESULT_ERROR");
		}
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/json; charset=UTF-8");
		
		return new ResponseEntity<String>(mapper.writeValueAsString(modelMap), responseHeaders, HttpStatus.CREATED);
	}
	
//=================================== 프로필 편집_비밀번호 변경(AJAX) ===================================
	@RequestMapping(value = "/editupdate_pw")
	public @ResponseBody ResponseEntity<String> editUpdatePw(ModelAndView modelAndView, @ModelAttribute MemberVO memberVO) throws Exception {
		log.info("ProfileController - editUpdatePw(MemberVO) request :: " + memberVO.toString());
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		try {
			int edit_update_pw = profileService.editUpdatePw(memberVO);
			
			// DB_UPDATE 1행 완료 시.
			if(edit_update_pw == 1) {
				modelMap.put("edit_update_pw", edit_update_pw);
				log.info("ProfileController - editUpdatePw(MemberVO) response :: " + edit_update_pw);
				
				// DB_UPDATE 0행 완료 시.
			} else if(edit_update_pw == 0) {
				modelMap.put("edit_update_pw", edit_update_pw);
				log.info("ProfileController - editUpdatePw(MemberVO) response :: " + edit_update_pw);
			}
			
		} catch (Exception e) {
			log.info("ProfileController - editUpdatePw(MemberVO) response :: RESULT_ERROR");
		}
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/json; charset=UTF-8");
		
		return new ResponseEntity<String>(mapper.writeValueAsString(modelMap), responseHeaders, HttpStatus.CREATED);
	}
	
//=================================== 프로필 편집_계정 삭제(AJAX) ===================================
	@RequestMapping(value = "/editdelete_member")
	public @ResponseBody ResponseEntity<String> editDeleteMember(ModelAndView modelAndView, @ModelAttribute MemberVO memberVO) throws Exception {
		log.info("ProfileController - editDeleteMember(MemberVO) request :: " + memberVO.toString());
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		try {
			int edit_delete_member = profileService.editDeleteMember(memberVO);
			
			// DB_UPDATE 1행 완료 시.
			if(edit_delete_member == 1) {
				modelMap.put("edit_delete_member", edit_delete_member);
				log.info("ProfileController - editDeleteMember(MemberVO) response :: " + edit_delete_member);
				
				// DB_UPDATE 0행 완료 시.
			} else if(edit_delete_member == 0) {
				modelMap.put("edit_delete_member", edit_delete_member);
				log.info("ProfileController - editDeleteMember(MemberVO) response :: " + edit_delete_member);
			}
			
		} catch (Exception e) {
			log.info("ProfileController - editDeleteMember(MemberVO) response :: RESULT_ERROR");
		}
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/json; charset=UTF-8");
		
		return new ResponseEntity<String>(mapper.writeValueAsString(modelMap), responseHeaders, HttpStatus.CREATED);
	}
	
//=================================== 프로필 편집_1:1문의 목록 조회(AJAX) ===================================
	@RequestMapping(value = "/editselectlist_complain")
	public @ResponseBody ResponseEntity<String> editSelectListComplain(ModelAndView modelAndView, @ModelAttribute ComplainVO complainVO) throws Exception {
		log.info("ProfileController - editSelectListComplain(ComplainVO) request :: " + complainVO.toString());
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		// 성공&실패 체크.
		int result = 0;
		
		try {
			List<ComplainVO> edit_selectlist_complain = profileService.editSelectListComplain(complainVO);
			
			// 1:1문의 목록 조회가 null이 아니고 값이 비어있지 않을 경우.
			if(edit_selectlist_complain != null && !edit_selectlist_complain.isEmpty()) {
				result = 1;
				
				modelMap.put("result", result);
				modelMap.put("edit_selectlist_complain", edit_selectlist_complain);
				
				log.info("ProfileController - editSelectListComplain(ComplainVO) response :: " + edit_selectlist_complain);
				
			// 1:1문의 목록 조회가 null이고 값이 비어있을 경우.
			} else {
				modelMap.put("result", result);
				modelMap.put("edit_selectlist_complain", edit_selectlist_complain);
				
				log.info("ProfileController - editSelectListComplain(ComplainVO) response :: " + edit_selectlist_complain);
			}
			
		} catch (Exception e) {
			log.info("ProfileController - editSelectListComplain(ComplainVO) response :: RESULT_ERROR");
		}
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/json; charset=UTF-8");
		
		return new ResponseEntity<String>(mapper.writeValueAsString(modelMap), responseHeaders, HttpStatus.CREATED);
	}
	
//=================================== 프로필 편집_1:1문의 상세 조회(AJAX) ===================================
	@RequestMapping(value = "/editselectview_complain")
	public @ResponseBody ResponseEntity<String> editSelectViewComplain(ModelAndView modelAndView, @ModelAttribute ComplainVO complainVO) throws Exception {
		log.info("ProfileController - editSelectViewComplain(ComplainVO) request :: " + complainVO.toString());
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		// 성공&실패 체크.
		int result = 0;
		
		try {
			ComplainVO edit_selectview_complain = profileService.editSelectViewComplain(complainVO);
			
			// 1:1문의 목록 조회가 null이 아니고 값이 비어있지 않을 경우.
			if(edit_selectview_complain != null) {
				result = 1;
				
				modelMap.put("result", result);
				modelMap.put("edit_selectview_complain", edit_selectview_complain);
				
				log.info("ProfileController - editSelectViewComplain(ComplainVO) response :: " + edit_selectview_complain);
				
				// 1:1문의 목록 조회가 null이고 값이 비어있을 경우.
			} else {
				modelMap.put("result", result);
				modelMap.put("edit_selectview_complain", edit_selectview_complain);
				
				log.info("ProfileController - editSelectViewComplain(ComplainVO) response :: " + edit_selectview_complain);
			}
			
		} catch (Exception e) {
			log.info("ProfileController - editSelectViewComplain(ComplainVO) response :: RESULT_ERROR");
		}
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/json; charset=UTF-8");
		
		return new ResponseEntity<String>(mapper.writeValueAsString(modelMap), responseHeaders, HttpStatus.CREATED);
	}
	

//=================================== 프로필 편집_1:1문의 갱신(AJAX) ===================================
	@RequestMapping(value = "/editupdate_complain")
	public @ResponseBody ResponseEntity<String> editUpdateComplain(ModelAndView modelAndView, @ModelAttribute ComplainVO complainVO) throws Exception {
		log.info("ProfileController - editUpdateComplain(ComplainVO) request :: " + complainVO.toString());
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		try {
			int edit_update_complain = profileService.editUpdateComplain(complainVO);
			
			// DB_UPDATE 1행 완료 시.
			if(edit_update_complain == 1) {
				modelMap.put("edit_update_complain", edit_update_complain);
				log.info("ProfileController - editUpdateComplain(ComplainVO) response :: " + edit_update_complain);
				
				// DB_UPDATE 0행 완료 시.
			} else if(edit_update_complain == 0) {
				modelMap.put("edit_update_complain", edit_update_complain);
				log.info("ProfileController - editUpdateComplain(ComplainVO) response :: " + edit_update_complain);
			}
			
		} catch (Exception e) {
			log.info("ProfileController - editUpdateComplain(ComplainVO) response :: RESULT_ERROR");
		}
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/json; charset=UTF-8");
		
		return new ResponseEntity<String>(mapper.writeValueAsString(modelMap), responseHeaders, HttpStatus.CREATED);
	}
	
//=================================== 프로필 편집_1:1문의 삭제(AJAX) ===================================
	@RequestMapping(value = "/editdelete_complain")
	public @ResponseBody ResponseEntity<String> editDeleteComplain(ModelAndView modelAndView, @ModelAttribute ComplainVO complainVO) throws Exception {
		log.info("ProfileController - editDeleteComplain(ComplainVO) request :: " + complainVO.toString());
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		try {
			int edit_delete_complain = profileService.editDeleteComplain(complainVO);
			
			// DB_UPDATE 1행 완료 시.
			if(edit_delete_complain == 1) {
				modelMap.put("edit_delete_complain", edit_delete_complain);
				log.info("ProfileController - editDeleteComplain(ComplainVO) response :: " + edit_delete_complain);
				
				// DB_UPDATE 0행 완료 시.
			} else if(edit_delete_complain == 0) {
				modelMap.put("edit_delete_complain", edit_delete_complain);
				log.info("ProfileController - editDeleteComplain(ComplainVO) response :: " + edit_delete_complain);
			}
			
		} catch (Exception e) {
			log.info("ProfileController - editDeleteComplain(ComplainVO) response :: RESULT_ERROR");
		}
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/json; charset=UTF-8");
		
		return new ResponseEntity<String>(mapper.writeValueAsString(modelMap), responseHeaders, HttpStatus.CREATED);
	}
}