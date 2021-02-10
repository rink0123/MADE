package com.made.common;

/**
 * 임시 비밀번호 메일 폼 abstract class
 * 
 * @since JDK 1.8
 * @version 1.0.0.RELEASE
 * @author HbeMember_JANG JUN HEE
 */
public abstract class MailForm {

	/**
	 * 임시 비밀번호 메일 폼 Method
	 * 
	 * @param bm_id 일반회원 아이디
	 * @param bm_pw 일반회원 임시 비밀번호
	 * @return mail_form.toString();
	 */
	public static String TempPwMailForm(String bm_id, String bm_pw) {
	StringBuffer mail_form = new StringBuffer();
	
		mail_form.append("<!DOCTYPE html PUBLIC'-//W3C//DTDHTML4.01Transitional//EN''http://www.w3.org/TR/html4/loose.dtd'>");
		mail_form.append("<html>");
		mail_form.append("<head>");
		mail_form.append("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
		mail_form.append("<title>MADE 임시 비밀번호</title>");
		mail_form.append("<style media='all' type='text/css'>");
		mail_form.append("td, p, h1, h3, a { font-family: Helvetica, Arial, sans-serif; }");
		mail_form.append("</style>");
		mail_form.append("</head>");
		mail_form.append("<body LINK='#6D93B8' ALINK='#9DB7D0' VLINK='#6D93B8' TEXT='#D7D7D7' style='font-family: Helvetica, Arial, sans-serif; font-size: 14px; color: #D7D7D7;'>");
		mail_form.append("	<table style='width: 550px; background-color: #393836; border: 2pxsolid #000000;' align='center' cellspacing='0' cellpadding='0'>");
		mail_form.append("		<tr>");
		mail_form.append("			<td bgcolor='#FFFFFF'>");
		mail_form.append("				<table width='470px' border='0px' align='center' cellpadding='0px' cellspacing='0' style='padding-left: 5px; padding-right: 5px; padding-bottom: 10px;'>");
		mail_form.append("					<tr bgcolor='#FFFFFF'>");
		mail_form.append("						<td style='padding-top: 32px;'>");
		mail_form.append("							<span style='font-size: 24px; color: #444444; font-family: Arial, Helvetica, sans-serif; font-weight: bold;'>");
		mail_form.append("								안녕하세요 " + bm_id + " 님");
		mail_form.append("							</span>");
		mail_form.append("							<br/>");
		mail_form.append("						</td>");
		mail_form.append("					</tr>");
		mail_form.append("					<tr>");
		mail_form.append("						<td style='padding: 12px 0px; font-size: 14px; color: #444444; font-family: Arial, Helvetica, sans-serif; font-weight: bold;'>");
		mail_form.append("							아래 임시 비밀번호로 로그인 후 계정 비밀번호를 수정해주시기 바랍니다.");
		mail_form.append("						</td>");
		mail_form.append("					</tr>");
		mail_form.append("					<tr>");
		mail_form.append("						<td style='padding: 24px 35px; text-align: center;'>");
		mail_form.append("							<div style='padding: 14px 20px; background-color: #E60023; border-radius: 4px;'>");
		mail_form.append("								<span style='font-size: 24px; color: #FFFFFF; font-family: Arial, Helvetica, sans-serif; font-weight: bold;'>");
		mail_form.append(									bm_pw);
		mail_form.append("								</span>");
		mail_form.append("								<br/>");
		mail_form.append("							</div>");
		mail_form.append("						</td>");
		mail_form.append("					</tr>");
		mail_form.append("					<tr>");
		mail_form.append("						<td style='font-size: 12px; color: #6d7880; padding-top: 16px; padding-bottom: 60px;'>");
		mail_form.append("							MADE고객지원팀");
		mail_form.append("							<br/>");
		mail_form.append("						</td>");
		mail_form.append("					</tr>");
		mail_form.append("				</table>");
		mail_form.append("			</td>");
		mail_form.append("		</tr>");
		mail_form.append("	</table>");
		mail_form.append("</body>");
		mail_form.append("</html>");
		
		return mail_form.toString();
	}
}