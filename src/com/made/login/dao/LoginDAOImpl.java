package com.made.login.dao;

import org.apache.log4j.Logger;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.made.login.vo.MemberVO;

/**
 * 로그인 DAO class
 * 
 * @since JDK 1.8
 * @version 1.0.0.RELEASE
 * @author HbeMember_JANG JUN HEE
 */
public class LoginDAOImpl extends SqlSessionDaoSupport implements LoginDAO {
	private static Logger log = Logger.getLogger(LoginDAOImpl.class);
	
	private final String PACKAGE_PATH = "mybatis.query.";

// ================================= 일반 로그인 =================================
	@Override
	public MemberVO basicLogin(MemberVO member_param) throws Exception {
		log.info("LoginDAOImpl - basicLogin(MemberVO) request :: " + member_param.toString());

		return (MemberVO) getSqlSession().selectOne(PACKAGE_PATH + "basicLogin", member_param);
	}

// ================================= 아이디 찾기 =================================
	@Override
	public MemberVO basicFindId(MemberVO member_param) throws Exception {
		log.info("LoginDAOImpl - basicFindId(MemberVO) request :: " + member_param.toString());

		return (MemberVO) getSqlSession().selectOne(PACKAGE_PATH + "basicFindId", member_param);
	}
	
// ================================= 비밀번호 찾기_임시 비밀번호로 수정 =================================
	@Override
	public int basicFindPwUpdate(MemberVO member_param) throws Exception {
		log.info("LoginDAOImpl - basicFindPwUpdate(MemberVO) request :: " + member_param.toString());

		return (int) getSqlSession().update(PACKAGE_PATH + "basicFindPwUpdate", member_param);
	}
	
// ================================= 비밀번호 찾기_임시 비밀번호 보내기 =================================
	@Override
	public MemberVO basicFindPwSend(MemberVO member_param) throws Exception {
		log.info("LoginDAOImpl - basicFindPwSend(MemberVO) request :: " + member_param.toString());
		
		return (MemberVO) getSqlSession().selectOne(PACKAGE_PATH + "basicFindPwSend", member_param);
	}
	
// ================================= 회원가입 =================================
	@Override
	public int basicJoin(MemberVO member_param) throws Exception {
		log.info("LoginDAOImpl - basicJoin(MemberVO) request :: " + member_param.toString());

		return (int) getSqlSession().insert(PACKAGE_PATH + "basicJoin", member_param);
	}


// ================================= 회원가입_아이디 중복체크 =================================
	@Override
	public int basicJoinIdChk(MemberVO member_param) throws Exception {
		log.info("LoginDAOImpl - basicJoinIdChk(MemberVO) request :: " + member_param.toString());

		return (int) getSqlSession().selectOne(PACKAGE_PATH + "basicJoinIdChk", member_param);
	}
}