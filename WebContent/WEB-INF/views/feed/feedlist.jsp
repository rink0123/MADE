<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="resources/css/feed/feed.css"/> <%-- 메인 게시글 목록 CSS --%>
<link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
<script type="text/javascript" src="https://code.jquery.com/jquery-latest.min.js"></script> <%-- jQuery CDN --%>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script> <%-- sweetalert 알림창 CDN --%>
<script type="text/javascript" src="resources/js/feed/feedSelect.js"></script>

<title>MADE | HOME</title>

<script type="text/javascript">
<%-- 페이지 새로고침 시 스크롤 초기화 --%>
window.onbeforeunload = function() {
	window.history.scrollRestoration = "manual"; <%-- [manual|auto] --%>
}

<%--
	페이지 첫 로드 시 HTML 만들기도 전에 JS 에서 접근해서 생기는 문제로 인해 페이지 로드 후 데이터 뿌림.
	Cannot read property '변수명' of null
--%>
$(window).load(function() {
	<%-- 페이지 첫 로드 시 메인 게시글_목록 조회 function --%>
	setTimeout(function() {
		feedSelect_List();
	}, 1000);
});

$(document).ready(function() {
	<%-- 스크롤 페이징 --%>
	$(window).scroll(function() {
		var scrollTop = $(window).scrollTop();
		var scrollHeight = $(document).height() - $(window).height();
	    
		<%-- 스크롤 끝 발동 --%>
	    /* if(scrollTop == scrollHeight) { */
		<%-- 스크롤 중간 발동 --%>
		if(scrollTop > (scrollHeight / 2)) {
	    	feedSelect_List();
		}
	});
	
	<%-- 메인 게시글 클릭 시 상세페이지로 이동 --%>
	$(".feed_card_grid").on("click", ".feed_card", function() {
		$("input[name='feed_no']").val($(this).attr("id"));
		
		$("#feed_form").attr("action", "feedview");
		$("#feed_form").submit();
	});
});
</script>

</head>
<body>
	<c:import url="/header"></c:import>
	
	<form action="#" method="post" id="feed_form">
		<input type="hidden" name="feed_no" value="" />
		<input type="hidden" name="feed_search" value="${param.feed_search}" />
	</form>
	
	<%-- Post Stream --%>
	<div id="feed_wrap">
		<div class="feed_card_grid"></div>
	</div>
	
	<%-- Bottom Right Buttons --%>
	<div class="bottom-right-buttons">
		<ul>
			<li id="add_pin_button">
				<i class="ion-plus-round"></i>
			</li>
			<li id="help-button">
				<i class="ion-help"></i>
			</li>
		</ul>
	</div>
</body>
</html>