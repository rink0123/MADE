package com.made.login.controller;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

import com.made.common.MailForm;
import com.made.login.service.LoginService;
import com.made.login.vo.MemberVO;

/**
 * 로그인 Controller
 * 
 * @since JDK 1.8
 * @version 1.0.0.RELEASE
 * @author HbeMember_JANG JUN HEE
 */
@Controller
public class LoginController {
	private static Logger log = Logger.getLogger(LoginController.class);
	
	@Autowired
	private LoginService loginService;
	
// ========================================= 로그인 =========================================
	@RequestMapping(value = "/login")
	public ModelAndView login(ModelAndView mav) {
		mav.setViewName("login/login");
		
		return mav;
	}
	
// ========================================= 일반 로그인(ajax) =========================================
	@RequestMapping(value = "/basiclogin")
	public @ResponseBody ResponseEntity<String> basicLogin(HttpServletRequest request, HttpServletResponse response,
			HttpSession session, @ModelAttribute MemberVO member_param) throws Exception {
		log.info("LoginController - basicLogin(MemberVO) request :: " + member_param.toString());

		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		// 로그인 성공&실패&에러 체크.
		int result = 0;
		
		try {
			MemberVO basic_login = null;
			basic_login = loginService.basicLogin(member_param);

			// 로그인 조회 값이 null 아닐 경우.
			if(basic_login != null) {
				// 요청 로그인 값과 DB 로그인 값과 같을 경우. 
				if ( member_param.getBm_id().equals(basic_login.getBm_id()) && member_param.getBm_pw().equals(basic_login.getBm_pw()) ) {
					session = request.getSession();
					
					session.setAttribute("session_bm_no", basic_login.getBm_no());
					session.setAttribute("session_bm_id", basic_login.getBm_id());
					session.setAttribute("session_bm_nick", basic_login.getBm_nick());
					session.setAttribute("session_bm_img", basic_login.getBm_img());
					session.setAttribute("session_bm_auth", basic_login.getBm_auth());
					session.setMaxInactiveInterval(60 * 60 * 24); // 최대 24시간 세션 유지.
					
					Cookie cookie = new Cookie("cookie_bm_id", URLEncoder.encode(basic_login.getBm_id(), "UTF-8"));
					response.addCookie(cookie);
					cookie.setMaxAge(60 * 60 * 24); // 최대 24시간 쿠키 유지.

					// 세션 & 쿠키 부여 확인.
					log.info("LoginController - basicLogin 세션 부여 확인 :: session_bm_no=" + session.getAttribute("session_bm_no"));
					log.info("LoginController - basicLogin 세션 부여 확인 :: session_bm_id=" + session.getAttribute("session_bm_id"));
					log.info("LoginController - basicLogin 세션 부여 확인 :: session_bm_img=" + session.getAttribute("session_bm_img"));
					log.info("LoginController - basicLogin 세션 부여 확인 :: session_bm_auth=" + session.getAttribute("session_bm_auth"));
					log.info("LoginController - basicLogin 세션 부여 확인 :: session.getId()=" + session.getAttribute(session.getId()));
					log.info("LoginController - basicLogin 세션 부여 확인 :: session.getMaxInactiveInterval()=" + session.getMaxInactiveInterval());
					log.info("LoginController - basicLogin 쿠키 부여 확인 :: cookie.getName()=" + cookie.getName());
					log.info("LoginController - basicLogin 쿠키 부여 확인 :: cookie.getValue()=" + cookie.getValue());
					log.info("LoginController - basicLogin 쿠키 부여 확인 :: cookie.getMaxAge()=" + cookie.getMaxAge());
					
					result = 1;
					
					modelMap.put("result", result);
					log.info("LoginController - basicLogin(MemberVO) response :: " + modelMap.get("result"));
					
				// 요청 로그인 값과 DB 로그인 값과 같을 않을 경우. 
				} else {
					modelMap.put("result", result);
					log.info("LoginController - basicLogin(MemberVO) response :: 아이디, 비밀번호 틀림");
				}
				
			// 로그인 조회 값이 null일 경우.
			} else {
				modelMap.put("result", result);
				log.info("LoginController - basicLogin(MemberVO) response :: 존재하지 않는 아이디");
			}
			
		// 예외 발생.
		} catch (Exception e) {
			e.printStackTrace();
			log.info("LoginController - basicLogin(MemberVO) response :: RESULT_ERROR");
		}

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/json; charset=UTF-8");

		return new ResponseEntity<String>(mapper.writeValueAsString(modelMap), responseHeaders, HttpStatus.CREATED);
	}
	
// ========================================= 로그아웃 =========================================
	@RequestMapping(value = "/logout")
	public ModelAndView basicLogout(HttpServletRequest request, HttpServletResponse response, HttpSession session, ModelAndView mav) throws Exception {
		log.info("LoginController - basicLogout(HttpSession) request :: " + session.getAttribute("session_bm_no"));
		
		session = request.getSession(false);
		
		// session 값이 null이 아니거나 요청한 세션 ID가 여전히 유효한 경우 session, cookie null 초기화.
		if (session != null || request.isRequestedSessionIdValid()) {
			session.removeAttribute("session_bm_no");
			session.removeAttribute("session_bm_id");
			session.removeAttribute("session_bm_img");
			session.removeAttribute("session_bm_auth");
			session.setMaxInactiveInterval(0); // 세션 유지시간 초기화.
			session.invalidate();

			Cookie cookie = new Cookie("cookie_bm_id", null);
			cookie.setMaxAge(0); // 쿠키 유지시간 초기화.
			response.addCookie(cookie);
			
			// 세션 & 쿠키 초기화 확인.
			log.info("LoginController - basicLogout 세션 초기화 성공 :: " + session.getId());
			log.info("LoginController - basicLogout 쿠키 초기화 성공 :: " + cookie.getName() + " : " + cookie.getValue());
			
			mav.setViewName("redirect:login"); // 로그인 페이지로 이동.
		}
	
		return mav;
	}
	
// ========================================= 아이디 찾기(ajax) =========================================
	@RequestMapping(value = "/findid")
	public @ResponseBody ResponseEntity<String> basicFindId(@ModelAttribute MemberVO member_param, ModelAndView mav) throws Exception {
		log.info("LoginController - basicFindId(HttpSession) request :: " + member_param.toString());
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		// 아이디 찾기 성공&실패 체크.
		int result = 0;

		try {
			MemberVO basic_findid = loginService.basicFindId(member_param);
			
			// 찾는 아이디가 있을 경우.
			if(basic_findid != null) {
				result = 1;
				
				modelMap.put("result", result);
				modelMap.put("basic_findid", basic_findid);
				
				log.info("LoginController - basicFindId(MemberVO) response :: " + member_param.getBm_id());
				
			// 찾는 아이디가 없을 경우.
			} else {
				modelMap.put("result", result);
				log.info("LoginController - basicFindId(MemberVO) response :: 잘못 입력했거나 존재하지 않는 아이디");
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.info("LoginController - basicFindId response :: RESULT_ERROR");
		}

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/json; charset=UTF-8");

		return new ResponseEntity<String>(mapper.writeValueAsString(modelMap), responseHeaders, HttpStatus.CREATED);
	}
	
// ========================================= 비밀번호 찾기(ajax) =========================================
	@RequestMapping(value = "/findpw")
	public @ResponseBody ResponseEntity<String> basicFindPw(ModelAndView mav, @ModelAttribute MemberVO member_param) throws Exception {
		log.info("LoginController - basicFindPw(MemberVO) request :: " + member_param.toString());

		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> modelMap = new HashMap<String, Object>();

		try {
			/* 
			 * Gmail SMPT 사용 시 설정법(필수).
			 * 
			 * 1단계: IMAP이 켜져 있는지 확인 -> 구글 Gmail 열기 -> 우측 상단 환경설정 설정(톱니바퀴모양) 클릭 ->
			 * 모든 설정 보기 클릭 -> 전달 및 POP/IMAP 탭을 클릭 -> IMAP 액세스-상태: IMAP 사용 선택 -> 변경사항 저장 클릭.
			 * 
			 * 2단계: Gmail 발신 메일(SMTP) port 확인.
			 * 방법 1 : URL에 https://support.google.com/mail/answer/7126229?hl=ko 입력 후 엔터 -> 발신메일(SMTP) 서버에 TLS/STARTTLS용 포트 확인.
			 * 방법 2 : 구글 'Gmail 고객센터' 검색 -> 'Gmail 고객센터' 클릭 -> 검색창에 'IMAP을 사용하여 다른 이메일 클라이언트에서 Gmail 메일 읽기' 입력 후 엔터 ->
			 * 'IMAP을 사용하여 다른 이메일 클라이언트에서 Gmail 메일 읽기' 클릭 ->  발신 메일(SMTP) 서버에 TLS/STARTTLS용 포트 확인.
			 * 
			 * 3단계: Gmail 웹 및 앱 보안 설정.
			 * 우측 상단 메뉴에 Google 계정 클릭 -> 보안 탭 클릭 -> 보안 수준이 낮은 앱 액세스 클릭 -> 보안 수준이 낮은 앱 허용: 사용으로 변경.
			 * 
			 * JavaMail API 사용하기 위한 필수 jar파일 
			 * "WebContent/WEB-INF/lib/activation.jar", "WebContent/WEB-INF/lib/mail.jar"
			 * 위 두 jar파일이 있어야 java 내에서 메일 전송이 가능함.
			 */
			
			/*
			 * 보낸 사람 아이디&비밀번호 기재, 본인 구글아이디로 고정.
			 * 본인 구글 계정으로 접속하여 Gmail을 보내기 위해선 본인 아이디&비밀번호가 필요.
			 */
			final String username = "madeteam2020@gmail.com"; // 발신자 google id.
			final String password = "kosmo2020"; // 발신자 google 비밀번호.
			
			// 아이디 & 비밀번호 일치 시 임시 비밀번호로 수정.
			int basic_findpw_update = loginService.basicFindPwUpdate(member_param);
			
			// 임시 비밀번호로 수정되었을 경우.
			if(basic_findpw_update > 0) {
				// 임시 비밀번호로 수정된 수신자 셋팅 값 조회.
				MemberVO basic_findpw = loginService.basicFindPwSend(member_param);
				
				// 수신자 셋팅 값이 null이 아닐 경우.
				if(basic_findpw != null) {
					String bm_id = member_param.getBm_id();       // 수신자 셋팅, 메일 내용에 참조용.
					String bm_email = member_param.getBm_email(); // 수신자 셋팅, 메일 수신메일 참조용.
					String bm_pw = basic_findpw.getBm_pw();       // 임시 비밀번호 전송, 메일 내용에 참조용.
					
					Properties props = new Properties(); //정보를 담기 위한 객체 생성.
					// SMTP 서버 정보 설정(SSL용 port를 사용하는 경우)
					// props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP Host
					// props.put("mail.smtp.auth", "true"); // Enabling SMTP Authentication
					// props.put("mail.smtp.port", "465"); // SMTP Port
					// props.put("mail.smtp.socketFactory.port", "465"); // SSL Port
					// props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); //SSL Factory Class

					// SMTP 서버 정보 설정(TLS용 port를 사용하는 경우)
					props.put("mail.smtp.auth", "true"); // SMTP 서버 사용인증 설정, 본인 구글계정접속을 위해 무조건 "true"로 설정.
					/*
					 * 이메일 클라이언트 상에서 SMTP 서버 지정하고 아이디/패스워드 입력해서 인증에 성공하면
					 * SMTP 서버에서는 이메일 클라이언트로 부터 받은 메일을 발송해 주는 것.
					 */
					props.put("mail.smtp.starttls.enable", "true"); // SMTP 서버 TLS/STARTTLS용 포트 사용 설정, 본인 Gmail로 보내려면 무조건 "true"로 설정.
					/* 
					 * TCP/IP를 통해 SMTP 전송에 대한 SSL 보안을 제공하기 위해 IBM(R) Lotus(R) Domino(TM)는 조정된 SSL 사용을 지원.
					 * 조정된 SSL 스키마에서 송수신 호스트는 각각 RFC 2487에서 정의한 SMTP STARTTLS 확장을 사용하여 SSL 연결을 조정할 준비가 되었음을 알림.
					 * 수신 서버는 송신 서버의 EHLO 명령에 응답하여 STARTTLS 키워드를 표시 후 발신 서버는 STARTTLS 명령을 내려 안전한 연결 작성을 요청.
					 * 초기 TLS handshake가 성공적으로 완료된 후, 두 서버는 계속해서 둘 사이에 SSL 채널을 설정, 송수신 서버는 모두 SSL 인증서를 소유해야 함.
					 */
					props.put("mail.smtp.host", "smtp.gmail.com"); // 구글 SMTP서버 주소(개발용).
					props.put("mail.smtp.port", "587"); // 구글 SMTP TLS/STARTTLS용 포트.
	
					// 메일 Session 객체 생성, props에 설정된 정보로 Gmail로 접속.
					Session session = Session.getInstance(props, new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(username, password);
						}
					});
					Message message = new MimeMessage(session); // MimeMessage 객체 생성, 메일 정보를 담아 전송준비.
					message.setFrom(new InternetAddress(username)); // 발신자 셋팅, 발신자 이메일(=이메일 풀 주소) 기입.
					message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(bm_email)); // 수신자 셋팅, 수신자 이메일(=이메일 풀 주소) 기입 .TO외에 .CC(참조) .BCC(숨은참조)도 있음.
					message.setSubject("MADE 임시 비밀번호 입니다."); // 메일 제목 셋팅, 보낼 제목 기입.
					message.setContent(MailForm.TempPwMailForm(bm_id, bm_pw), "text/html; charset=UTF-8"); // 메일 내용 셋팅, 보낼 내용 기입.
					Transport.send(message); // javax.mail.Transport.send() 이용하여 메일 보내기.
					
					modelMap.put("basic_findpw_update", basic_findpw_update);
					log.info("LoginController - basicFindPw(MemberVO) response 메일 전송 성공 :: ");
					
				// 수신자 셋팅 값이 null일 경우
				} else {
					modelMap.put("basic_findpw_update", basic_findpw_update);
					log.info("LoginController - basicFindPw(MemberVO) response RESULT_ERROR :: " + basic_findpw + basic_findpw_update);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("LoginController - basicFindPw(MemberVO) response :: RESULT_ERROR");
		}

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/json; charset=UTF-8");

		return new ResponseEntity<String>(mapper.writeValueAsString(modelMap), responseHeaders, HttpStatus.CREATED);
	}
	
// ========================================= 회원가입(ajax) =========================================
	@RequestMapping(value = "/basicjoin")
	public @ResponseBody ResponseEntity<String> basicJoin(ModelAndView mav, @ModelAttribute MemberVO member_param) throws Exception {
		log.info("LoginController - basicJoin(MemberVO) request :: " + member_param.toString());
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> modelMap = new HashMap<String, Object>();

		// 회원가입 성공&실패 체크.
		int basicjoin = 0;
		
		try {
			basicjoin = loginService.basicJoin(member_param);
			
			// 회원가입 성공 시.
			if(basicjoin > 0) {
				basicjoin = 1;
				
				modelMap.put("basicjoin", basicjoin);
				log.info("LoginController - basicJoin(MemberVO) response 회원가입 성공 :: " + member_param.toString());
				
			// 회원가입 실패 시.
			} else {
				modelMap.put("basicjoin", basicjoin);
				log.info("LoginController - basicJoin(MemberVO) response 회원가입 실패 :: " + member_param.toString());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("LoginController - basicJoin(MemberVO) response :: RESULT_ERROR");
		}

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/json; charset=UTF-8");

		return new ResponseEntity<String>(mapper.writeValueAsString(modelMap), responseHeaders, HttpStatus.CREATED);
	}
	
// ========================================= 회원가입_아이디 중복체크(ajax) =========================================
	@RequestMapping(value = "/idchk")
	public @ResponseBody ResponseEntity<String> basicJoinIdChk(@ModelAttribute MemberVO member_param, ModelAndView mav) throws Exception {
		log.info("LoginController - basicJoinIdChk(MemberVO) request :: " + member_param.toString());

		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> modelMap = new HashMap<String, Object>();

		try {
			int bm_idchk = loginService.basicJoinIdChk(member_param);
			
			// 중복되는 아이디가 있을 경우.
			if(bm_idchk > 0) {
				modelMap.put("bm_idchk", bm_idchk);
				log.info("LoginController - basicJoinIdChk(MemberVO) response 아이디 중복 있음 :: " + member_param.toString());
			
			// 중복되는 아이디가 없을 경우.
			} else {
				modelMap.put("bm_idchk", bm_idchk);
				log.info("LoginController - basicJoinIdChk(MemberVO) response 아이디 중복 없음 :: " + member_param.toString());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("LoginController - basicJoinIdChk(MemberVO) response :: RESULT_ERROR");
		}

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/json; charset=UTF-8");

		return new ResponseEntity<String>(mapper.writeValueAsString(modelMap), responseHeaders, HttpStatus.CREATED);
	}
}