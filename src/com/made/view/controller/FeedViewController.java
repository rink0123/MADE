package com.made.view.controller;

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

import com.made.view.service.FeedViewService;
import com.made.view.vo.CmtAnswerVO;
import com.made.view.vo.CommentsVO;
import com.made.view.vo.FeedImageVO;
import com.made.view.vo.FeedLikeVO;
import com.made.view.vo.FeedVO;
import com.made.view.vo.FollowVO;
import com.made.view.vo.ReportVO;
import com.made.view.vo.RpAnswerVO;

/**
 * 메인 게시글_상세보기 Controller
 * 
 * @since JDK 1.8
 * @version 1.0.0.RELEASE
 * @author HbeMember_KANG MIN SUNG
 */
@Controller
public class FeedViewController {
	private static Logger log = Logger.getLogger(FeedViewController.class);
	
	@Autowired
	private FeedViewService feedViewService;
	
// ======================================= 메인 게시글_상세보기 조회 =======================================
	@RequestMapping(value = "/feedview")
	public ModelAndView feedViewSelect(ModelAndView modelAndView, HttpSession session, @ModelAttribute FeedVO feed_param) throws Exception {
		log.info("FeedViewController - feedViewSelect(FeedVO) request :: " + feed_param.toString());
		
		// 메인 게시글_상세보기 신고-답변 조회
		List<RpAnswerVO> feedView_select_rpanswer = feedViewService.feedViewSelectRpAnswer();
		
		modelAndView.addObject("feed_no", feed_param.getFeed_no());
		modelAndView.addObject("feedView_select_rpanswer", feedView_select_rpanswer);
		
		modelAndView.setViewName("view/feedview");
		
		log.info("MainViewController - feedViewSelect Response(ModelAndView) :: " + modelAndView);
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/addfeed")
	public ModelAndView addFeedSelect(ModelAndView modelAndView, HttpSession session) throws Exception {
		log.info("FeedController - addFeedSelect(ModelAndView) request :: 진입(start)");
		
		modelAndView.setViewName("view/addfeed");
		
		return modelAndView;
	}
	
// ======================================= 메인 게시글_상세보기 신고 삽입(AJAX) =======================================
	@RequestMapping(value = "/feedviewinsert_report")
	public @ResponseBody ResponseEntity<String> feedViewInsertReport(ModelAndView modelAndView, @ModelAttribute ReportVO feed_param) throws Exception {
		log.info("FeedViewController - feedViewInsertReport(RpAnswerVO) request :: " + feed_param.toString());
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		try {
			int feedView_insert_report = feedViewService.feedViewInsertReport(feed_param);
			
			// 산고 성공 시.
			if(feedView_insert_report != 0) {
				modelMap.put("feedView_insert_report", feedView_insert_report);
				
				log.info("FeedViewController - feedView_insert_report(ReportVO) response :: " + feedView_insert_report);
				
			// 신고 실패 시.
			} else {
				modelMap.put("feedView_insert_report", feedView_insert_report);
				
				log.info("FeedViewController - feedView_insert_report(ReportVO) response :: " + feedView_insert_report);
			}
			
		} catch (Exception e) {
			log.info("FeedViewController - feedView_insert_report(ReportVO) response :: RESULT_ERROR");
		}
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/json; charset=UTF-8");
		
		return new ResponseEntity<String>(mapper.writeValueAsString(modelMap), responseHeaders, HttpStatus.CREATED);
	}

// ======================================= 메인 게시글_상세보기 좋아요 조회(AJAX) =======================================
	@RequestMapping(value = "/feedviewselect_like")
	public @ResponseBody ResponseEntity<String> feedViewSelectLike(ModelAndView modelAndView, @ModelAttribute FeedLikeVO feed_param) throws Exception {
		log.info("FeedViewController - feedViewSelectLike(FeedLikeVO) request :: " + feed_param.toString());
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		// 성공&실패&에러 체크.
		int result = 0;
		
		try {
			FeedLikeVO feedview_select_like = feedViewService.feedViewSelectLike(feed_param);
			
			if(feedview_select_like != null) {
				result = 1;
				
				modelMap.put("result", result);
				modelMap.put("feedview_select_like", feedview_select_like);
				
				log.info("FeedViewController - feedViewSelectLike(FeedLikeVO) response :: " + feedview_select_like.toString());
			} else {
				modelMap.put("result", result);
				log.info("FeedViewController - feedViewSelectLike(FeedLikeVO) response :: " + feedview_select_like);
			}
			
		} catch (Exception e) {
			log.info("FeedViewController - feedViewSelectLike(FeedLikeVO) response :: RESULT_ERROR");
		}
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/json; charset=UTF-8");
		
		return new ResponseEntity<String>(mapper.writeValueAsString(modelMap), responseHeaders, HttpStatus.CREATED);
	}
	
// ======================================= 메인 게시글_상세보기 좋아요 삽입(AJAX) =======================================
	@RequestMapping(value = "/feedviewinsert_like")
	public @ResponseBody ResponseEntity<String> feedViewInsertLike(ModelAndView modelAndView, @ModelAttribute FeedLikeVO feed_param) throws Exception {
		log.info("FeedViewController - feedViewInsertLike(FeedLikeVO) request :: " + feed_param.toString());
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		try {
			int feedview_insert_like = feedViewService.feedViewInsertLike(feed_param);
			
			if(feedview_insert_like >= 0) {
				modelMap.put("feedview_insert_like", feedview_insert_like);
				
				log.info("FeedViewController - feedViewInsertLike(FeedLikeVO) response :: " + feedview_insert_like);
			} else {
				log.info("FeedViewController - feedViewInsertLike(FeedLikeVO) response :: " + feedview_insert_like);
			}
			
		} catch (Exception e) {
			log.info("FeedViewController - feedViewInsertLike(FeedLikeVO) response :: RESULT_ERROR");
		}
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/json; charset=UTF-8");
		
		return new ResponseEntity<String>(mapper.writeValueAsString(modelMap), responseHeaders, HttpStatus.CREATED);
	}
	
// ======================================= 메인 게시글_상세보기 좋아요 삭제(AJAX) =======================================
	@RequestMapping(value = "/feedviewdelete_like")
	public @ResponseBody ResponseEntity<String> feedViewDeleteLike(ModelAndView modelAndView, @ModelAttribute FeedLikeVO feed_param) throws Exception {
		log.info("FeedViewController - feedViewDeleteLike(FeedLikeVO) request :: " + feed_param.toString());
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		try {
			int feedview_delete_like = feedViewService.feedViewDeleteLike(feed_param);
			
			if(feedview_delete_like > 0) {
				modelMap.put("feedview_delete_like", feedview_delete_like);
				
				log.info("FeedViewController - feedViewDeleteLike(FeedLikeVO) response :: " + feedview_delete_like);
			} else {
				log.info("FeedViewController - feedViewDeleteLike(FeedLikeVO) response :: " + feedview_delete_like);
			}
			
		} catch (Exception e) {
			log.info("FeedViewController - feedViewDeleteLike(FeedLikeVO) response :: RESULT_ERROR");
		}
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/json; charset=UTF-8");
		
		return new ResponseEntity<String>(mapper.writeValueAsString(modelMap), responseHeaders, HttpStatus.CREATED);
	}
	
// ======================================= 메인 게시글_상세보기 이미지 조회(AJAX) =======================================
	@RequestMapping(value = "/feedviewselect_image")
	public @ResponseBody ResponseEntity<String> feedViewSelectImage(ModelAndView modelAndView, @ModelAttribute FeedVO feed_param) throws Exception {
		log.info("FeedViewController - feedViewSelectImage(FeedVO) request :: " + feed_param.toString());

		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		// 성공&실패&에러 체크.
		int result = 0;
		
		try {
			List<FeedImageVO> feedview_select_image = feedViewService.feedViewSelectImage(feed_param);
			
			if(feedview_select_image != null && !feedview_select_image.isEmpty()) {
				result = 1;
				
				modelMap.put("result", result);
				modelMap.put("feedview_select_image", feedview_select_image);
				
				log.info("FeedViewController - feedViewSelectImage(FeedVO) response :: " + feedview_select_image.toString());
			} else {
				modelMap.put("result", result);
				log.info("FeedViewController - feedViewSelectImage(FeedVO) response :: " + feedview_select_image.toString());
			}
			
		} catch (Exception e) {
			log.info("FeedViewController - feedViewSelectImage(FeedVO) response :: RESULT_ERROR");
		}

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/json; charset=UTF-8");

		return new ResponseEntity<String>(mapper.writeValueAsString(modelMap), responseHeaders, HttpStatus.CREATED);
	}
	
// ======================================= 메인 게시글_상세보기 이미지 삽입(AJAX) =======================================
	@RequestMapping(value = "/feedviewinsert_image")
	public @ResponseBody ResponseEntity<String> feedViewInsertImage(ModelAndView mav ,@ModelAttribute FeedImageVO feedImageVO) throws Exception {
		log.info("FeedViewController - feedViewInsertImage(FeedImageVO) request :: " + feedImageVO.toString());
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		try {
			int feedview_insert_image = feedViewService.feedViewInsertImage(feedImageVO);
			
			// 삽입 완료 시.
			if(feedview_insert_image == 1) {
				modelMap.put("feedview_insert_image", feedview_insert_image);
				log.info("FeedViewController - feedViewInsertImage(FeedImageVO) response :: " + feedview_insert_image);
				
			// 삽입 실패 시.
			} else if(feedview_insert_image == 0) {
				modelMap.put("feedview_insert_image", feedview_insert_image);
				log.info("FeedViewController - feedViewInsertImage(FeedImageVO) response :: " + feedview_insert_image);
			}
		} catch (Exception e) {
			log.info("FeedViewController - feedViewInsertImage(FeedImageVO) response :: RESULT_ERROR");
		}
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/json; charset=UTF-8");
		
		return new ResponseEntity<String>(mapper.writeValueAsString(modelMap), responseHeaders, HttpStatus.CREATED);
	}
	
// ======================================= 메인 게시글_상세보기 이미지 삭제(AJAX) =======================================
	@RequestMapping(value = "/feedviewdelete_image")
	public @ResponseBody ResponseEntity<String> feedViewDeleteImage(ModelAndView mav ,@ModelAttribute FeedImageVO feedImageVO) throws Exception {
		log.info("FeedViewController - feedViewDeleteImage(FeedImageVO) request :: " + feedImageVO.toString());
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		try {
			int feedview_delete_image = feedViewService.feedViewDeleteImage(feedImageVO);
			
			// 삭제 완료 시.
			if(feedview_delete_image == 1) {
				modelMap.put("feedview_delete_image", feedview_delete_image);
				log.info("FeedViewController - feedViewDeleteImage(FeedImageVO) response :: " + feedview_delete_image);
				
			// 삭제 실패 시.
			} else if(feedview_delete_image == 0) {
				modelMap.put("feedview_delete_image", feedview_delete_image);
				log.info("FeedViewController - feedViewDeleteImage(FeedImageVO) response :: " + feedview_delete_image);
			}
		} catch (Exception e) {
			log.info("FeedViewController - feedViewDeleteImage(FeedImageVO) response :: RESULT_ERROR");
		}
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/json; charset=UTF-8");
		
		return new ResponseEntity<String>(mapper.writeValueAsString(modelMap), responseHeaders, HttpStatus.CREATED);
	}
	
// ======================================= 메인 게시글_상세보기 글내용 조회(AJAX) =======================================
	@RequestMapping(value = "/feedviewselect_content")
	public @ResponseBody ResponseEntity<String> feedViewSelectContent(ModelAndView modelAndView, @ModelAttribute FeedVO feed_param) throws Exception {
		log.info("FeedViewController - feedViewSelectContent(FeedVO) request :: " + feed_param.toString());
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		// 성공&실패&에러 체크.
		int result = 0;
		
		try {
			FeedVO feedview_select_content = feedViewService.feedViewSelectContent(feed_param);
			
			if(feedview_select_content != null && !feedview_select_content.getFeed_no().isEmpty()) {
				result = 1;
				
				modelMap.put("result", result);
				modelMap.put("feedview_select_content", feedview_select_content);
				
				log.info("FeedViewController - feedViewSelectContent(FeedVO) response :: " + feedview_select_content.toString());
			} else {
				modelMap.put("result", result);
				log.info("FeedViewController - feedViewSelectContent(FeedVO) response :: " + feedview_select_content.toString());
			}
			
		} catch (Exception e) {
			log.info("FeedViewController - feedViewSelectContent(FeedVO) response :: RESULT_ERROR");
		}
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/json; charset=UTF-8");
		
		return new ResponseEntity<String>(mapper.writeValueAsString(modelMap), responseHeaders, HttpStatus.CREATED);
	}
	
// ======================================= 메인 게시글_상세보기 글내용 삽입(AJAX) =======================================
	@RequestMapping(value = "/feedviewinsert_content")
	public @ResponseBody ResponseEntity<String> feedViewInsertContent(ModelAndView modelAndView, @ModelAttribute FeedVO feed_param) throws Exception {
		log.info("FeedViewController - feedViewInsertContent(FeedVO) request :: " + feed_param.toString());
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		try {
			int feedview_insert_content = feedViewService.feedViewInsertContent(feed_param);
			
			if(feedview_insert_content >= 0) {
				modelMap.put("feedview_insert_content", feedview_insert_content);
				log.info("FeedViewController - feedViewInsertContent(FeedVO) response :: " + feedview_insert_content);
				
				FeedVO feedview_content_chk = feedViewService.feedViewContentChk(feed_param);
				
				// 글 추가 확인용, true일 경우 사진 추가를 위해 클라이언트로 값을 보냄.
				if(feedview_content_chk != null) {
					modelMap.put("feedview_content_chk", feedview_content_chk);
					log.info("FeedViewController - feedViewInsertContent(FeedVO) response :: " + feedview_content_chk.getFeed_no());
					
				} else {
					modelMap.put("feedview_content_chk", feedview_content_chk);
					log.info("FeedViewController - feedViewInsertContent(FeedVO) response :: NULL");
				}
				
			} else {
				modelMap.put("feedview_insert_content", feedview_insert_content);
				log.info("FeedViewController - feedViewInsertContent(FeedVO) response :: " + feedview_insert_content);
			}
			
		} catch (Exception e) {
			log.info("FeedViewController - feedViewInsertContent(FeedVO) response :: RESULT_ERROR");
		}
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/json; charset=UTF-8");
		
		return new ResponseEntity<String>(mapper.writeValueAsString(modelMap), responseHeaders, HttpStatus.CREATED);
	}
	
// ======================================= 메인 게시글_상세보기 글내용 수정(AJAX) =======================================
	@RequestMapping(value = "/feedviewupdate_content")
	public @ResponseBody ResponseEntity<String> feedViewUpdateContent(ModelAndView modelAndView, @ModelAttribute FeedVO feed_param) throws Exception {
		log.info("FeedViewController - feedViewUpdateContent(FeedVO) request :: " + feed_param.toString());
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		try {
			int feedview_update_content = feedViewService.feedViewUpdateContent(feed_param);
			
			if(feedview_update_content >= 0) {
				modelMap.put("feedview_update_content", feedview_update_content);
				
				log.info("FeedViewController - feedViewUpdateContent(FeedVO) response :: " + feedview_update_content);
			} else {
				log.info("FeedViewController - feedViewUpdateContent(FeedVO) response :: " + feedview_update_content);
			}
			
		} catch (Exception e) {
			log.info("FeedViewController - feedViewUpdateContent(FeedVO) response :: RESULT_ERROR");
		}
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/json; charset=UTF-8");
		
		return new ResponseEntity<String>(mapper.writeValueAsString(modelMap), responseHeaders, HttpStatus.CREATED);
	}
	
// ======================================= 메인 게시글_상세보기 글내용 삭제(AJAX) =======================================
	@RequestMapping(value = "/feedviewdelete_content")
	public @ResponseBody ResponseEntity<String> feedViewDeleteContent(ModelAndView modelAndView, @ModelAttribute FeedVO feed_param) throws Exception {
		log.info("FeedViewController - feedViewDeleteContent(FeedVO) request :: " + feed_param.toString());
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		try {
			int feedview_delete_content = feedViewService.feedViewDeleteContent(feed_param);
			
			if(feedview_delete_content == -1) {
				modelMap.put("feedview_delete_content", feedview_delete_content);
				
				log.info("FeedViewController - feedViewDeleteContent(FeedVO) response :: " + feedview_delete_content);
			} else {
				log.info("FeedViewController - feedViewDeleteContent(FeedVO) response :: " + feedview_delete_content);
			}
			
		} catch (Exception e) {
			log.info("FeedViewController - feedViewDeleteContent(FeedVO) response :: RESULT_ERROR");
		}
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/json; charset=UTF-8");
		
		return new ResponseEntity<String>(mapper.writeValueAsString(modelMap), responseHeaders, HttpStatus.CREATED);
	}
	
// ======================================= 메인 게시글_상세보기 팔로우 조회(AJAX) =======================================
	@RequestMapping(value = "/feedviewselect_follow")
	public @ResponseBody ResponseEntity<String> feedViewSelectFollow(ModelAndView modelAndView, @ModelAttribute FollowVO feed_param) throws Exception {
		log.info("FeedViewController - feedViewSelectFollow(FollowVO) request :: " + feed_param.toString());
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		// 성공&실패 체크.
		int result = 0;
		
		try {
			FollowVO feedview_select_follow = feedViewService.feedViewSelectFollow(feed_param);
			
			if(feedview_select_follow != null) {
				result = 1;
				
				modelMap.put("result", result);
				modelMap.put("feedview_select_follow", feedview_select_follow);
				
				log.info("FeedViewController - feedViewSelectFollow(FollowVO) response :: " + feedview_select_follow.toString());
			} else {
				modelMap.put("result", result);
			}
			
		} catch (Exception e) {
			log.info("FeedViewController - feedViewSelectFollow(FollowVO) response :: RESULT_ERROR");
		}
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/json; charset=UTF-8");
		
		return new ResponseEntity<String>(mapper.writeValueAsString(modelMap), responseHeaders, HttpStatus.CREATED);
	}
	
// ======================================= 메인 게시글_상세보기 팔로우 삽입(AJAX) =======================================
	@RequestMapping(value = "/feedviewinsert_follow")
	public @ResponseBody ResponseEntity<String> feedViewInsertFollow(ModelAndView modelAndView, @ModelAttribute FollowVO feed_param) throws Exception {
		log.info("FeedViewController - feedViewInsertFollow(FollowVO) request :: " + feed_param.toString());
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		try {
			int feedview_insert_follow = feedViewService.feedViewInsertFollow(feed_param);
			
			if(feedview_insert_follow >= 0) {
				modelMap.put("feedview_insert_follow", feedview_insert_follow);
				
				log.info("FeedViewController - feedViewInsertFollow(FollowVO) response :: " + feedview_insert_follow);
			} else {
				log.info("FeedViewController - feedViewInsertFollow(FollowVO) response :: " + feedview_insert_follow);
			}
			
		} catch (Exception e) {
			log.info("FeedViewController - feedViewInsertFollow(FollowVO) response :: RESULT_ERROR");
		}
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/json; charset=UTF-8");
		
		return new ResponseEntity<String>(mapper.writeValueAsString(modelMap), responseHeaders, HttpStatus.CREATED);
	}
	
// ======================================= 메인 게시글_상세보기 팔로우 삭제(AJAX) =======================================
	@RequestMapping(value = "/feedviewdelete_follow")
	public @ResponseBody ResponseEntity<String> feedViewDeleteFollow(ModelAndView modelAndView, @ModelAttribute FollowVO feed_param) throws Exception {
		log.info("FeedViewController - feedViewDeleteFollow(FollowVO) request :: " + feed_param.toString());
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		try {
			int feedview_delete_follow = feedViewService.feedViewDeleteFollow(feed_param);
			
			if(feedview_delete_follow >= 0) {
				modelMap.put("feedview_delete_follow", feedview_delete_follow);
				
				log.info("FeedViewController - feedViewDeleteFollow(FollowVO) response :: " + feedview_delete_follow);
			} else {
				log.info("FeedViewController - feedViewDeleteFollow(FollowVO) response :: " + feedview_delete_follow);
			}
			
		} catch (Exception e) {
			log.info("FeedViewController - feedViewDeleteFollow(FollowVO) response :: RESULT_ERROR");
		}
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/json; charset=UTF-8");
		
		return new ResponseEntity<String>(mapper.writeValueAsString(modelMap), responseHeaders, HttpStatus.CREATED);
	}

// ======================================= 메인 게시글_상세보기 댓글, 댓글-답글 조회(AJAX) =======================================
	@RequestMapping(value = "/feedviewselect_comments")
	public @ResponseBody ResponseEntity<String> feedViewSelectComments(ModelAndView modelAndView, @ModelAttribute FeedVO feed_param) throws Exception {
		log.info("FeedViewController - feedViewSelectComments(FeedVO) request :: " + feed_param.toString());
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		// 댓글 조회_성공&실패&에러 체크.
		int cmt_result = 0;
		
		// 댓글 조회_성공&실패&에러 체크.
		int cmtans_result = 0;
		
		try {
			// 댓글.
			List<CommentsVO> feedView_select_comments = feedViewService.feedViewSelectComments(feed_param);
			
			// 댓글 null이 아니고 값이 비어있자 않은 경우.
			if(feedView_select_comments != null && !feedView_select_comments.isEmpty()) {
				cmt_result = 1;
				
				modelMap.put("cmt_result", cmt_result);
				modelMap.put("feedView_select_comments", feedView_select_comments);
				
				log.info("FeedViewController - feedViewSelectComments(FeedVO) response :: " + feedView_select_comments.toString());
				
			// 댓글 null이고 값이 비어있는 경우.
			} else {
				modelMap.put("cmt_result", cmt_result);
				log.info("FeedViewController - feedViewSelectComments(FeedVO) response :: " + feedView_select_comments.toString());
			}
			
			// 댓글-답변.
			List<CmtAnswerVO> feedView_select_cmtanswer = feedViewService.feedViewSelectCmtAnswer();
			
			// 댓글-답변 null이 아니고 값이 비어있자 않은 경우.
			if (feedView_select_cmtanswer != null && !feedView_select_cmtanswer.isEmpty()) {
				cmtans_result = 1;
				
				modelMap.put("cmtans_result", cmtans_result);
				modelMap.put("feedView_select_cmtanswer", feedView_select_cmtanswer);

				log.info("FeedViewController - feedViewSelectComments(FeedVO) response :: " + feedView_select_cmtanswer.toString());
				
			// 댓글-답변 null이고 값이 비어있는 경우.
			} else {
				modelMap.put("cmtans_result", cmtans_result);
				log.info("FeedViewController - feedViewSelectComments(FeedVO) response :: " + feedView_select_cmtanswer.toString());
			}
			
		} catch (Exception e) {
			log.info("FeedViewController - feedViewSelectComments(FeedVO) response :: RESULT_ERROR");
		}
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/json; charset=UTF-8");
		
		return new ResponseEntity<String>(mapper.writeValueAsString(modelMap), responseHeaders, HttpStatus.CREATED);
	}
	
// ======================================= 메인 게시글_상세보기 댓글 삽입(AJAX) =======================================
	@RequestMapping(value = "/feedviewinsert_comments")
	public @ResponseBody ResponseEntity<String> feedViewInsertComments(ModelAndView modelAndView, @ModelAttribute CommentsVO feed_param) throws Exception {
		log.info("FeedViewController - feedViewInsertComments(CommentsVO) request :: " + feed_param.toString());
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		try {
			int feedview_insert_comments = feedViewService.feedViewInsertComments(feed_param);
			
			if(feedview_insert_comments >= 0) {
				modelMap.put("feedview_insert_comments", feedview_insert_comments);
				
				log.info("FeedViewController - feedViewInsertComments(CommentsVO) response :: " + feedview_insert_comments);
			} else {
				log.info("FeedViewController - feedViewInsertComments(CommentsVO) response :: " + feedview_insert_comments);
			}
			
		} catch (Exception e) {
			log.info("FeedViewController - feedViewInsertComments(CommentsVO) response :: RESULT_ERROR");
		}
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/json; charset=UTF-8");
		
		return new ResponseEntity<String>(mapper.writeValueAsString(modelMap), responseHeaders, HttpStatus.CREATED);
	}

// ======================================= 메인 게시글_상세보기 댓글 수정(AJAX) =======================================
	@RequestMapping(value = "/feedviewupdate_comments")
	public @ResponseBody ResponseEntity<String> feedViewUpdateComments(ModelAndView modelAndView, @ModelAttribute CommentsVO feed_param) throws Exception {
		log.info("FeedViewController - feedViewUpdateComments(CommentsVO) request :: " + feed_param.toString());
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		try {
			int feedview_update_comments = feedViewService.feedViewUpdateComments(feed_param);
			
			if(feedview_update_comments >= 0) {
				modelMap.put("feedview_update_comments", feedview_update_comments);
				
				log.info("FeedViewController - feedViewUpdateComments(CommentsVO) response :: " + feedview_update_comments);
			} else {
				log.info("FeedViewController - feedViewUpdateComments(CommentsVO) response :: " + feedview_update_comments);
			}
			
		} catch (Exception e) {
			log.info("FeedViewController - feedViewUpdateComments(CommentsVO) response :: RESULT_ERROR");
		}
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/json; charset=UTF-8");
		
		return new ResponseEntity<String>(mapper.writeValueAsString(modelMap), responseHeaders, HttpStatus.CREATED);
	}
	
// ======================================= 메인 게시글_상세보기 댓글 삭제(AJAX) =======================================
	@RequestMapping(value = "/feedviewdelete_comments")
	public @ResponseBody ResponseEntity<String> feedViewDeleteComments(ModelAndView modelAndView, @ModelAttribute CommentsVO feed_param) throws Exception {
		log.info("FeedViewController - feedViewDeleteComments(CommentsVO) request :: " + feed_param.toString());
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		try {
			int feedview_delete_comments = feedViewService.feedViewDeleteComments(feed_param);
			
			if(feedview_delete_comments >= 0) {
				modelMap.put("feedview_delete_comments", feedview_delete_comments);
				
				log.info("FeedViewController - feedViewDeleteComments(CommentsVO) response :: " + feedview_delete_comments);
			} else {
				log.info("FeedViewController - feedViewDeleteComments(CommentsVO) response :: " + feedview_delete_comments);
			}
			
		} catch (Exception e) {
			log.info("FeedViewController - feedViewDeleteComments(CommentsVO) response :: RESULT_ERROR");
		}
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/json; charset=UTF-8");
		
		return new ResponseEntity<String>(mapper.writeValueAsString(modelMap), responseHeaders, HttpStatus.CREATED);
	}
	
// ======================================= 메인 게시글_상세보기 댓글-답글 삽입(AJAX) =======================================
	@RequestMapping(value = "/feedviewinsert_cmtanswer")
	public @ResponseBody ResponseEntity<String> feedViewInsertCmtAnswer(ModelAndView modelAndView, @ModelAttribute CmtAnswerVO feed_param) throws Exception {
		log.info("FeedViewController - feedViewInsertCmtAnswer(CmtAnswerVO) request :: " + feed_param.toString());
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		try {
			int feedview_insert_cmtanswer = feedViewService.feedViewInsertCmtAnswer(feed_param);
			
			if(feedview_insert_cmtanswer >= 0) {
				modelMap.put("feedview_insert_cmtanswer", feedview_insert_cmtanswer);
				
				log.info("FeedViewController - feedViewInsertCmtAnswer(CmtAnswerVO) response :: " + feedview_insert_cmtanswer);
			} else {
				log.info("FeedViewController - feedViewInsertCmtAnswer(CmtAnswerVO) response :: " + feedview_insert_cmtanswer);
			}
			
		} catch (Exception e) {
			log.info("FeedViewController - feedViewInsertCmtAnswer(CmtAnswerVO) response :: RESULT_ERROR");
		}
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/json; charset=UTF-8");
		
		return new ResponseEntity<String>(mapper.writeValueAsString(modelMap), responseHeaders, HttpStatus.CREATED);
	}
	
// ======================================= 메인 게시글_상세보기 댓글-답글 수정(AJAX) =======================================
	@RequestMapping(value = "/feedviewupdate_cmtanswer")
	public @ResponseBody ResponseEntity<String> feedViewUpdateCmtAnswer(ModelAndView modelAndView, @ModelAttribute CmtAnswerVO feed_param) throws Exception {
		log.info("FeedViewController - feedViewUpdateCmtAnswer(CmtAnswerVO) request :: " + feed_param.toString());
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		try {
			int feedview_update_cmtanswer = feedViewService.feedViewUpdateCmtAnswer(feed_param);
			
			if(feedview_update_cmtanswer >= 0) {
				modelMap.put("feedview_update_cmtanswer", feedview_update_cmtanswer);
				
				log.info("FeedViewController - feedViewUpdateCmtAnswer(CmtAnswerVO) response :: " + feedview_update_cmtanswer);
			} else {
				log.info("FeedViewController - feedViewUpdateCmtAnswer(CmtAnswerVO) response :: " + feedview_update_cmtanswer);
			}
			
		} catch (Exception e) {
			log.info("FeedViewController - feedViewUpdateCmtAnswer(CmtAnswerVO) response :: RESULT_ERROR");
		}
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/json; charset=UTF-8");
		
		return new ResponseEntity<String>(mapper.writeValueAsString(modelMap), responseHeaders, HttpStatus.CREATED);
	}
	
// ======================================= 메인 게시글_상세보기 댓글-답글 삭제(AJAX) =======================================
	@RequestMapping(value = "/feedviewdelete_cmtanswer")
	public @ResponseBody ResponseEntity<String> feedViewDeleteCmtAnswer(ModelAndView modelAndView, @ModelAttribute CmtAnswerVO feed_param) throws Exception {
		log.info("FeedViewController - feedViewDeleteCmtAnswer(CmtAnswerVO) request :: " + feed_param.toString());
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		try {
			int feedview_delete_cmtanswer = feedViewService.feedViewDeleteCmtAnswer(feed_param);
			
			if(feedview_delete_cmtanswer >= 0) {
				modelMap.put("feedview_delete_cmtanswer", feedview_delete_cmtanswer);
				
				log.info("FeedViewController - feedViewDeleteCmtAnswer(CmtAnswerVO) response :: " + feedview_delete_cmtanswer);
			} else {
				log.info("FeedViewController - feedViewDeleteCmtAnswer(CmtAnswerVO) response :: " + feedview_delete_cmtanswer);
			}
			
		} catch (Exception e) {
			log.info("FeedViewController - feedViewDeleteCmtAnswer(CmtAnswerVO) response :: RESULT_ERROR");
		}
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/json; charset=UTF-8");
		
		return new ResponseEntity<String>(mapper.writeValueAsString(modelMap), responseHeaders, HttpStatus.CREATED);
	}
}