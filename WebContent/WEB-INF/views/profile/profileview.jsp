<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet" type="text/css" href="resources/css/profile/profileview.css"/>
<script type="text/javascript" src="https://code.jquery.com/jquery-latest.min.js"></script> <%-- jQuery CDN --%>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script> <%-- sweetalert 알림창 CDN --%>
<script type="text/javascript" src="resources/js/profile/profileSelect.js"></script>
<script type="text/javascript" src="resources/js/profile/profileInsert.js"></script>
<script type="text/javascript" src="resources/js/profile/profileDelete.js"></script>

<title>MADE | Profile</title>

<script type="text/javascript">
<%--
	페이지 첫 로드 시 HTML 만들기도 전에 JS 에서 접근해서 생기는 문제로 인해 페이지 로드 후 데이터 뿌림.
	Cannot read property '변수명' of null
--%>
$(window).load(function() {
	profileSelect_Follow("${profile_select_member.bm_no}", "${session_bm_no}"); <%-- 팔로우_조회 function --%>
	profileSelect_Followers("${profile_select_member.bm_no}", "${session_bm_no}"); <%-- 팔로워 팝업창 목록_조회 function --%>
	profileSelect_Following("${profile_select_member.bm_no}", "${session_bm_no}"); <%-- 팔로우 팝업창 목록_조회 function --%>
});

$(document).ready(function() {
<%-- ========================================= 프로필 일반회원 정보 ========================================= --%>
	<%-- 팔로우 버튼 클릭 시 이벤트 --%>
	$(".profile_follow_btn").on("click", function() {
		<%-- 팔로우 상태일 경우 팔로잉으로 변경 --%>
		if($(this).attr("id") == "0") {
			$("input[name='bm_no']").val("${session_bm_no}");
			$("input[name='reg_bm_no']").val("${profile_select_member.bm_no}");
			
			var result = profileInsert_Follow(); <%-- 팔로우 삽입 function --%>
			
			<%-- 삽입 성공 시 팔로우 버튼 변경 --%>
			if(result == 1) {
				profileSelect_Follow("${profile_select_member.bm_no}", "${session_bm_no}"); <%-- 팔로우_조회 function --%>
				profileSelect_Followers("${profile_select_member.bm_no}", "${session_bm_no}"); <%-- 팔로워 팝업창 목록_조회 function --%>
				
				$("#profile_followers_cnt").text(($("#profile_followers_cnt").text() * 1) + 1); <%-- 팔로워 통계 + 1 --%>
			}
			
		<%-- 팔로잉 상태일 경우 팔로우로 변경 --%>
		} else if($(this).attr("id") == "1") {
			$("input[name='bm_no']").val("${session_bm_no}");
			$("input[name='reg_bm_no']").val("${profile_select_member.bm_no}");
			
			var result = profileDelete_Follow(); <%-- 팔로우 삭제 function --%>
			
			<%-- 삭제 성공 시 팔로우 버튼 변경 --%>
			if(result == 1) {
				profileSelect_Follow("${profile_select_member.bm_no}", "${session_bm_no}"); <%-- 팔로우_조회 function --%>
				profileSelect_Followers("${profile_select_member.bm_no}", "${session_bm_no}"); <%-- 팔로워 팝업창 목록_조회 function --%>
				
				$("#profile_followers_cnt").text(($("#profile_followers_cnt").text() * 1) - 1); <%-- 팔로워 통계 - 1 --%>
			}
		}
	});
	
	<%-- 프로필 편집 버튼 클릭 시 프로필 편집 페이지로 이동 --%>
	$("#profile_edit_btn").on("click", function() {
		$("input[name='bm_no']").val("${session_bm_no}");
		
		$("#profile_form").attr("action", "edit");
		$("#profile_form").submit();
	});
	
	<%-- 메인 게시글 클릭 시 메인 게시글 상세페이지로 이동 --%>
	$(".profile_feed").on("click", ".profile_photo", function() {
		$("input[name='feed_no']").val($(this).attr("id"));
		
		$("#profile_form").attr("action", "feedview");
		$("#profile_form").submit();
	});
	
<%-- ========================================= 팔로워 팝업창 ========================================= --%>
	<%-- 팔로워 클릭 시 팝업창 활성화 --%>
	$("#profile_followers").on("click", function() {
		$("body").css("overflow", "hidden");
		$("#profile_overlay").css("display", "block");
		$("#followers_modal").css("display", "block");
	});
	
	<%-- 팔로워 팝업창 닫기 버튼 클릭 시 팝업창 비활성화 --%>
	$("#followers_modal_closebtn").on("click", function() {
		$("body").css("overflow", "auto");
		$(".followers_modal_list").scrollTop(0);
		$("#profile_overlay").css("display", "none");
		$("#followers_modal").css("display", "none");
		$("#following_modal").css("display", "none");
	});
	
	<%-- overlay 클릭 시 overlay, 팔로워, 팔로우 팝업창 비활성화 --%>
	$("#profile_overlay").on("click", function() {
		$("body").css("overflow", "auto");
		$(".followers_modal_list").scrollTop(0);
		$("#profile_overlay").css("display", "none");
		$("#followers_modal").css("display", "none");
		$("#following_modal").css("display", "none");
	});
	
	<%-- 팔로워 리스트에 프로필 사진 클릭 시 해당 프로필 정보로 이동 --%>
	$(".followers_modal_list").on("click", ".profile_photo", function() {
		var followers_modal_profile = $(this).parent(".followers_modal_profile");
		
		$("input[name='bm_no']").val(followers_modal_profile.attr("id"));
		
		$("#profile_form").attr("action", "profile");
		$("#profile_form").submit();
	});
	
	<%-- 팔로워 리스트에 닉네임 클릭 시 해당 프로필 정보로 이동 --%>
	$(".followers_modal_list").on("click", ".followers_modal_usernick", function() {
		var followers_modal_profile = $(this).parent(".followers_modal_user").parent(".followers_modal_profile");
		
		$("input[name='bm_no']").val(followers_modal_profile.attr("id"));
		
		$("#profile_form").attr("action", "profile");
		$("#profile_form").submit();
	});
	
	<%-- 팔로워 리스트 스크롤 페이징 --%>
	$(".followers_modal_list").scroll(function() {
		var innerHeight = $(this).innerHeight();        <%-- 패딩padding 포함 높이 (마진margin과 테두리border는 포함이 되지 않음) --%>
		var scroll = $(this).scrollTop() + innerHeight; <%-- 선택 태그의 Y축 높이(=스크롤 높이) --%>
		var height = $(this)[0].scrollHeight;           <%-- 스크롤 시키지 않았을 때의 전체 높이 --%>
		
		<%-- 팔로워 리스트 스크롤바가 끝에 닿았을 때 다음페이지 불러오기 --%>
		if(scroll >= height) {
			profileSelect_Followers("${profile_select_member.bm_no}", "${session_bm_no}"); <%-- 팔로워 팝업창 목록_조회 function --%>
		}
	});
	
	<%-- 팔로워 리스트에 팔로우/팔로잉 버튼 클릭 시 이벤트 --%>
	$(".followers_modal_list").on("click", ".followers_modal_followbtn", function() {
		var followers_modal_profile = $(this).parent(".followers_modal_profile");
		
		<%-- 팔로우 상태일 경우 팔로잉으로 변경 --%>
		if($(this).attr("id") == "0") {
			$("input[name='bm_no']").val("${session_bm_no}");
			$("input[name='reg_bm_no']").val(followers_modal_profile.attr("id"));
			
			var result = profileInsert_Follow(); <%-- 팔로우 삽입 function --%>
			
			<%-- 삽입 성공 시 팔로우 버튼 변경 --%>
			if(result == 1) {
				$(this).text("팔로잉");
				$(this).attr("id", "1");
				$(this).css("background-color", "#FFFFFF");
				$(this).css("border", "1px solid #DBDBDB");
				$(this).css("color", "#111111");
			}
			
		<%-- 팔로잉 상태일 경우 팔로우로 변경 --%>
		} else if($(this).attr("id") == "1") {
			$("input[name='bm_no']").val("${session_bm_no}");
			$("input[name='reg_bm_no']").val(followers_modal_profile.attr("id"));
			
			var result = profileDelete_Follow(); <%-- 팔로우 삭제 function --%>
			
			<%-- 삭제 성공 시 팔로우 버튼 변경 --%>
			if(result == 1) {
				$(this).text("팔로우");
				$(this).attr("id", "0");
				$(this).css("background-color", "#0095F6");
				$(this).css("border", "1px solid #0095F6");
				$(this).css("color", "#FFFFFF");
			}
		}
	});
	
<%-- ========================================= 팔로우 팝업창 ========================================= --%>
	<%-- 팔로우 클릭 시 팝업창 활성화 --%>
	$("#profile_following").on("click", function() {
		$("body").css("overflow", "hidden");
		$("#profile_overlay").css("display", "block");
		$("#following_modal").css("display", "block");
	});
	
	<%-- 팔로우 팝업창 닫기 버튼 클릭 시 팝업창 비활성화 --%>
	$("#following_modal_closebtn").on("click", function() {
		$("body").css("overflow", "auto");
		$(".following_modal_list").scrollTop(0);
		$("#profile_overlay").css("display", "none");
		$("#followers_modal").css("display", "none");
		$("#following_modal").css("display", "none");
	});
	
	<%-- overlay 클릭 시 overlay, 팔로워, 팔로우 팝업창 비활성화 --%>
	$("#profile_overlay").on("click", function() {
		$("body").css("overflow", "auto");
		$(".following_modal_list").scrollTop(0);
		$("#profile_overlay").css("display", "none");
		$("#followers_modal").css("display", "none");
		$("#following_modal").css("display", "none");
	});
	
	<%-- 팔로우 리스트에 프로필 사진 클릭 시 해당 프로필 정보로 이동 --%>
	$(".following_modal_list").on("click", ".profile_photo", function() {
		var following_modal_profile = $(this).parent(".following_modal_profile");
		
		$("input[name='bm_no']").val(following_modal_profile.attr("id"));
		
		$("#profile_form").attr("action", "profile");
		$("#profile_form").submit();
	});
	
	<%-- 팔로우 리스트에 닉네임 클릭 시 해당 프로필 정보로 이동 --%>
	$(".following_modal_list").on("click", ".following_modal_usernick", function() {
		var following_modal_profile = $(this).parent(".following_modal_user").parent(".following_modal_profile");
		
		$("input[name='bm_no']").val(following_modal_profile.attr("id"));
		
		$("#profile_form").attr("action", "profile");
		$("#profile_form").submit();
	});
	
	<%-- 팔로우 리스트 스크롤 페이징 --%>
	$(".following_modal_list").scroll(function() {
		var innerHeight = $(this).innerHeight();        <%-- 패딩padding 포함 높이 (마진margin과 테두리border는 포함이 되지 않음) --%>
		var scroll = $(this).scrollTop() + innerHeight; <%-- 선택 태그의 Y축 높이(=스크롤 높이) --%>
		var height = $(this)[0].scrollHeight;           <%-- 스크롤 시키지 않았을 때의 전체 높이 --%>
		
		<%-- 팔로우 리스트 스크롤바가 끝에 닿았을 때 다음페이지 불러오기 --%>
		if(scroll >= height) {
			profileSelect_Following("${profile_select_member.bm_no}", "${session_bm_no}"); <%-- 팔로우 목록_조회 function --%>
		}
	});
	
	<%-- 팔로우 리스트에 팔로우/팔로잉 버튼 클릭 시 이벤트 --%>
	$(".following_modal_list").on("click", ".following_modal_followbtn", function() {
		var following_modal_profile = $(this).parent(".following_modal_profile");
		
		<%-- 팔로우 상태일 경우 팔로잉으로 변경 --%>
		if($(this).attr("id") == "0") {
			$("input[name='bm_no']").val("${session_bm_no}");
			$("input[name='reg_bm_no']").val(following_modal_profile.attr("id"));
			
			var result = profileInsert_Follow(); <%-- 팔로우 삽입 function --%>
			
			<%-- 삽입 성공 시 팔로우 버튼 변경 --%>
			if(result == 1) {
				$(this).text("팔로잉");
				$(this).attr("id", "1");
				$(this).css("background-color", "#FFFFFF");
				$(this).css("border", "1px solid #DBDBDB");
				$(this).css("color", "#111111");
			}
			
		<%-- 팔로잉 상태일 경우 팔로우로 변경 --%>
		} else if($(this).attr("id") == "1") {
			$("input[name='bm_no']").val("${session_bm_no}");
			$("input[name='reg_bm_no']").val(following_modal_profile.attr("id"));
			
			var result = profileDelete_Follow(); <%-- 팔로우 삭제 function --%>
			
			<%-- 삭제 성공 시 팔로우 버튼 변경 --%>
			if(result == 1) {
				$(this).text("팔로우");
				$(this).attr("id", "0");
				$(this).css("background-color", "#0095F6");
				$(this).css("border", "1px solid #0095F6");
				$(this).css("color", "#FFFFFF");
			}
		}
	});
});
</script>

</head>
<body style="margin: 0px; padding: 0px;">
<%-- ======================================== form tag ======================================== --%>
	<form action="#" method="post" id="profile_form">
		<input type="hidden" name="feed_no" value="" /> <%-- 메인 게시글 번호 --%>
		
		<input type="hidden" name="session_bm_no" value="" /> <%-- 로그인 한 회원 번호 --%>
		<input type="hidden" name="bm_no" value="" />         <%-- 일반회원 번호(본인) --%>
		<input type="hidden" name="reg_bm_no" value="" />     <%-- 일반회원 번호(상대) --%>
		
		<input type="hidden" name="followers_curpage" value="1" /> <%-- 팔로워 목록 현재 페이지 --%>
		<input type="hidden" name="following_curpage" value="1" /> <%-- 팔로잉 목록 현재 페이지 --%>
	</form>
	
	<c:import url="/header"></c:import>
	
	<%-- 프로필_wrap --%>
	<div class="profile">
<%-- ========================================= 프로필_일반회원 정보 ========================================= --%>
		<div style="text-align: left;">
			<%-- 프로필_일반회원 response 값이 1이고 프로필 사진이 있을 경우 --%>
			<c:if test="${member_result eq 1 and not empty profile_select_member.bm_img}">
				<div class="profile_photo" style="width: 150px; height: 150px; margin: 0px 80px;"> <%-- 프로필_사진 박스 --%>
					<img class="profile_img" src="${profile_select_member.bm_img}" style="border-radius: 50%;" /> <%-- 프로필_사진 --%>
				</div>
			</c:if>
			
			<%-- 프로필_일반회원 response 값이 1이고 프로필 사진이 없을 경우 --%>
			<c:if test="${member_result eq 1 and empty profile_select_member.bm_img}">
				<div class="profile_photo" style="width: 150px; height: 150px; margin: 0px 80px;"> <%-- 프로필_사진 박스 --%>
					<img class="profile_img" src="resources/images/etc/non_profile.png" style="border-radius: 50%;" /> <%-- 프로필_사진 --%>
				</div>
			</c:if>
			
			<%-- 프로필_일반회원 정보 헤더 --%>
			<div class="profile_column">
				<div style="padding-bottom: 20px;">
					<div class="profile_usernick">${profile_select_member.bm_nick}</div> <%-- 프로필_일반회원 닉네임 --%>
					
					<%-- session 회원 번호가 null이 아니고 프로필 회원 번호가 session 회원 번호와 같지 않을 경우 --%>
					<c:if test="${session_bm_no ne null and profile_select_member.bm_no ne session_bm_no}">
						<div class="profile_follow_btn"></div> <%-- 프로필_팔로우 버튼 --%>
					</c:if>
					
					<%-- session 회원 번호가 null이 아니고 프로필 회원 번호가 session 회원 번호와 같을 경우 --%>
					<c:if test="${session_bm_no ne null and profile_select_member.bm_no eq session_bm_no}">
						<div id="profile_edit_btn" class="profile_edit_btn">프로필 편집</div> <%-- 프로필_편집 버튼 --%>
					</c:if>
				</div>
	
				<div style="font-size: 16px; padding-bottom: 20px;">
					<%-- 메인 게시물 갯수의 값이 비어있거나 없을 경우 --%>
					<c:if test="${empty profile_feed_cnt}">
						<%-- 프로필_게시물 통계 --%>
						<div class="profile_stat" style="margin: 0px; cursor: auto;">
							게시물 <span style="font-weight: 600;">0</span>
						</div>
					</c:if>
					
					<%-- 메인 게시물 갯수의 값이 비어있지 않을 경우 --%>
					<c:if test="${!empty profile_feed_cnt}">
						<%-- 프로필_게시물 통계 --%>
						<div class="profile_stat" style="margin: 0px; cursor: auto;">
							게시물 <span style="font-weight: 600;">${profile_feed_cnt}</span>
						</div>
					</c:if>
					
					<%-- 프로필_팔로워 통계 --%>
					<div id="profile_followers" class="profile_stat">
						팔로워 <span id="profile_followers_cnt" style="font-weight: 600;">0</span>
					</div>
					
					<%-- 프로필_팔로우 통계 --%>
					<div id="profile_following" class="profile_stat">
						팔로우 <span id="profile_following_cnt" style="font-weight: 600;">0</span>
					</div>
				</div>
				
				<div class="profile_username">${profile_select_member.bm_name}</div> <%-- 프로필_일반회원 이름 --%>
			</div>
		</div>

		<hr style="margin: 45px 15px 30px 15px;"/>
		
		<%-- 프로필_메인 게시글 --%>
		<div class="profile_feed" style="text-align: left;">
			<c:forEach var="profile_select_feed" items="${profile_select_feed}">
				<div id="${profile_select_feed.feed_no}" class="profile_photo" style="margin: 15px; width: 295px; height: 295px;">
					<img class="profile_img" src="${profile_select_feed.fi_path}${profile_select_feed.fi_file}" />
				</div>
			</c:forEach>
		</div>
		
	</div>
	
<%-- ========================================= 팔로워 팝업창 ========================================= --%>
	<div id="followers_modal" class="followers_modal" style="display: none;">
		<div style="max-height: 42px; border-bottom: 1px solid #DBDBDB;">
			<div style="display:inline-block; width: 42px; height: 42px;"></div>
			<div class="followers_modal_title">팔로워</div> <%-- 팔로워 팝업창_제목 --%>
			<img id="followers_modal_closebtn" src="resources/images/etc/profile_close.png"/> <%-- 팔로워 팝업창_닫기 버튼 --%>
		</div>
		
		<%-- 팔로워 팝업창_목록 --%>
		<div class="followers_modal_list"></div>
	</div>
	
<%-- ========================================= 팔로우 팝업창 ========================================= --%>
	<div id="following_modal" class="following_modal" style="display: none;">
		<div class="following_modal_header">
			<div style="display:inline-block; width: 42px; height: 42px;"></div>
			<div class="following_modal_title">팔로잉</div> <%-- 팔로우 팝업창_제목 --%>
			<img id="following_modal_closebtn" src="resources/images/etc/profile_close.png"/> <%-- 팔로우 팝업창_닫기 버튼 --%>
		</div>
		
		<%-- 팔로우 팝업창_목록 --%>
		<div class="following_modal_list"></div>
	</div>
	
<%-- ========================================= 프로필_overlay ========================================= --%>
	<div id="profile_overlay" class="profile_overlay" style="display: none;"></div>
</body>
</html>