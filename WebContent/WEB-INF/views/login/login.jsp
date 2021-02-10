<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>MAKE YOUR MADE</title>

<link rel="stylesheet" type="text/css" href="resources/css/login/login.css">
<script src="http://developers.kakao.com/sdk/js/kakao.min.js"></script> <%-- 카카오 cdn --%>
<script type="text/javascript" src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.0.js" charset="UTF-8"></script> <%-- 네이버 cdn --%>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script> <%-- jQuery CDN(배포용) --%>
<%-- <script src="https://code.jquery.com/jquery-latest.min.js"></script> --%> <%-- jQuery CDN(개발용) --%>
<script type="text/javascript" src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script> <%-- 알림창 디자인 및 기능(배포용) --%>
<script type="text/javascript" src="resources/js/login/jquery.vide.js"></script> <%-- 반드시 jQuery CDN 아래에 명시할 것 --%>
<script type="text/javascript" src="resources/js/login/login_chk.js"></script> <%-- 로그인, 아아디/비밀번호 찾기, 회원가입_체크 function --%>
<script type="text/javascript" src="resources/js/login/login_popup.js"></script> <%-- 로그인, 아아디 찾기, 비밀번호 찾기_popup function --%>
<script type="text/javascript" src="resources/js/login/join_popup.js"></script> <%-- 회원가입_popup function --%>
<script type="text/javascript" src="resources/js/login/join_chk.js"></script> <%-- 회원가입_체크(ajax) function --%>

<script type="text/javascript">
$(document).ready(function() {
	<%-- 첫 화면 로그인 popup 활성화 --%>
	basicLogin_popup();
});
	
function kakaoLogout() {
	if(!Kakao.Auth.getAccessToken()) {
		alert('로그아웃 되었습니다.')
		return
	}
	Kakao.Auth.logout(function() {
		alert('logout ok\naccess token -> ' + Kakao.Auth.getAccessToken())
	});
}
</script>

</head>

<body data-vide-bg="resources/images/etc/signfix.mp4">
<%---------------------------------- form data setting ----------------------------------%>
	<form action="#" method="post" id="login_form">
		<input type="hidden" name="bm_id" value="" />
		<input type="hidden" name="bm_pw" value="" />
		<input type="hidden" name="bm_name" value="" />
		<input type="hidden" name="bm_phone" value="" />
		<input type="hidden" name="bm_email" value="" />
		<input type="hidden" name="bm_nick" value="" />
	</form>
	
<%---------------------------------- 로그인 ----------------------------------%>
	<%-- 로그인 폼 --%>
	<div class="login-form" style="box-shadow: rgba(0, 0, 0, 0.45) 0px 2px 10px; border-radius: 32px;">
		<img id="logo" alt="made" src="resources/images/etc/madeblack.png" width="220px" />
		
		<%-- 로그인, 회원가입 팝업창 --%>
		<div class="popup"></div>
	</div>
	
<%---------------------------------- z-index background ----------------------------------%>
	<div id="feedview_back_index" class="feedview_back_index" style="display: none;"></div>
</body>
</html>