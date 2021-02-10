<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script type="text/javascript" src="https://code.jquery.com/jquery-latest.min.js"></script> <%-- jQuery CDN --%>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script> <%-- sweetalert 알림창 CDN --%>

<title>MADE | 관리자</title>

<script type="text/javascript">
$(document).ready(function() {
	
});
</script>
</head>
<body>
	<form action="#" id="adminForm" method="post">
		<input type="hidden" name="" value=""/>
	</form>
	
	<div class="adminWrap">
		<div class="adminTable">
			<div class="adminHead">권한(일반/관리자), ID, 가입일, 계정상태(-/탈퇴/차단/해제).
				<div class="adminH">권한</div>
				<div class="adminH">아이디</div>
				<div class="adminH">가입일</div>
				<div class="adminH">계정상태</div>
			</div>
			<div class="adminBody">
				<div></div>
			</div>
		</div>
	</div>
</body>
</html>