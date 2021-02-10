package com.made.login.dao;

import com.made.login.vo.MemberVO;

/**
 * 로그인 DAO interface
 * 
 * @since JDK 1.8
 * @version 1.0.0.RELEASE
 * @author HbeMember_JANG JUN HEE
 */
public interface LoginDAO {
// ================================= 일반 로그인 =================================
	public MemberVO basicLogin(MemberVO member_param) throws Exception;

// ================================= 아이디 찾기 =================================
	public MemberVO basicFindId(MemberVO member_param) throws Exception;
	
// ================================= 비밀번호 찾기_임시 비밀번호로 수정 =================================
	public int basicFindPwUpdate(MemberVO member_param) throws Exception;
	
// ================================= 비밀번호 찾기_임시 비밀번호 보내기 =================================
	public MemberVO basicFindPwSend(MemberVO member_param) throws Exception;
	
// ================================= 회원가입 =================================
	public int basicJoin(MemberVO member_param) throws Exception;
	
// ================================= 회원가입_아이디 중복체크 =================================
	public int basicJoinIdChk(MemberVO member_param) throws Exception;
}