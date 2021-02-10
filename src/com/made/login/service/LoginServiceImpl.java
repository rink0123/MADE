package com.made.login.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.made.login.dao.LoginDAO;
import com.made.login.vo.MemberVO;

/**
 * 로그인 Service class
 * 
 * @since JDK 1.8
 * @version 1.0.0.RELEASE
 * @author HbeMember_JANG JUN HEE
 */
@Service
public class LoginServiceImpl implements LoginService {
	@Autowired private LoginDAO loginDAO;
	
	private static Logger log = Logger.getLogger(LoginServiceImpl.class);
	
// ================================= 일반 로그인 =================================
	@Override
	public MemberVO basicLogin(MemberVO member_param) throws Exception {
		log.info("LoginServiceImpl - basicLogin(MemberVO) request :: " + member_param.toString());
		
		return loginDAO.basicLogin(member_param);
	}

// ================================= 아이디 찾기 =================================
	@Override
	public MemberVO basicFindId(MemberVO member_param) throws Exception {
		log.info("LoginServiceImpl - basicFindId(MemberVO) request :: " + member_param.toString());
		
		return loginDAO.basicFindId(member_param);
	}

// ================================= 비밀번호 찾기_임시 비밀번호로 수정 =================================
	@Override
	public int basicFindPwUpdate(MemberVO member_param) throws Exception {
		log.info("LoginServiceImpl - basicFindPwUpdate(MemberVO) request :: " + member_param.toString());
		
		return loginDAO.basicFindPwUpdate(member_param);
	}
	
// ================================= 비밀번호 찾기_임시 비밀번호 보내기 =================================
	@Override
	public MemberVO basicFindPwSend(MemberVO member_param) throws Exception {
		log.info("LoginServiceImpl - basicFindPwSend(MemberVO) request :: " + member_param.toString());
		
		return loginDAO.basicFindPwSend(member_param);
	}

// ================================= 회원가입 =================================	
	@Override
	public int basicJoin(MemberVO member_param) throws Exception {
		log.info("LoginServiceImpl - basicJoin(MemberVO) request :: " + member_param.toString());
		
		return loginDAO.basicJoin(member_param);
	}

// ================================= 회원가입_아이디 중복체크 =================================
	@Override
	public int basicJoinIdChk(MemberVO member_param) throws Exception {
		log.info("LoginServiceImpl - basicJoinIdChk(MemberVO) request :: " + member_param.toString());
		
		return loginDAO.basicJoinIdChk(member_param);
	}
}