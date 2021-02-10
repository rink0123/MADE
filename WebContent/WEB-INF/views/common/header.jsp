<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" type="text/css" href="resources/css/common/header.css">
<script type="text/javascript" src="resources/js/common/headerSelect.js"></script>
<script type="text/javascript" src="resources/js/common/headerInsert.js"></script>

<script type="text/javascript">
$(window).load(function() {
	headerSelectNoticeCnt(); <%-- 헤더_알림 수 조회 long polling function --%>
});

$(document).ready(function() {
<%--================================= 헤더_검색 =================================--%>
	<%-- 헤더_검색 입력란에 키보드 입력 시 이벤트 --%>
	$("#header_search_txt").on("keydown keyup", function() {
		<%-- 헤더_검색 입력란에 값이 있을 시 헤더_검색 취소 버튼 활성화 --%>
		if($(this).val() != "") {
			$("#header_search_canclebtn").show();
			
		<%-- 헤더_검색 입력란에 값이 없을 시 헤더_검색 취소 버튼 비활성화 --%>
		} else {
			$("#header_search_canclebtn").hide();
		}
	});
	
	<%-- 헤더_검색에 focus 시 CSS효과 활성화 --%>
	$("#header_search_txt").focus(function() {
		$("#header_search").css("box-shadow", "0 0 0 4px rgba(0, 132, 255, 0.5)");
		
	<%-- 헤더_검색에 blur 시 CSS효과 비활성화 --%>
	}).blur(function() {
		$("#header_search").css("box-shadow", "none");
	});
	
	<%-- 헤더_검색 버튼 클릭 시 feed 페이지로 이동, 검색 내용만 나옴 --%>
	$("#header_searchbtn").click(function() {
		$("#header_form input[name='feed_search']").val($.trim($("#header_search_txt").val()));
		
		$("#header_form").attr("action", "feed");
		$("#header_form").submit();
	});
	
	<%-- 헤더_검색 입력란에 엔터키 클릭 시 feed 페이지로 이동, 검색 내용만 나옴 --%>
	$("#header_search_txt").on("keydown keyup", function(event) {
		<%-- 엔터 클릭 시 검색 --%>
		if(event.which == 13) {
			$("#header_searchbtn").click();
		}
	});
	
	<%-- 헤더_검색 취소 버튼 클릭 시 헤더_검색 입력란 초기화 --%>
	$("#header_search_canclebtn").click(function() {
		$("#header_search_txt").val("");
		
		$("#header_search").css("box-shadow", "none");
		$(this).hide();
	});
	
<%--================================= 헤더_프로필 사진 =================================--%>
	<%-- 헤더_프로필 사진 클릭 시 프로필 정보 페이지로 이동 --%>
	$("#header_profile").click(function() {
		$("#header_form input[name='bm_no']").val("${session_bm_no}");
		
		$("#header_form").attr("action", "profile");
		$("#header_form").submit();
	});
	
<%--================================= 헤더_알림 =================================--%>
	<%-- 헤더_알림 클릭 시 이벤트 --%>
	$("#header_notice").click(function() {
		<%-- 헤더_알림 하위 메뉴 숨기기 --%>
		if($("#header_notice > #header_contextmenu").css("display") != "none") {
			$("#header_form input[name='current_noticecnt']").val($("#header_form input[name='before_noticecnt']").val());
			
			$(this).css("background-color", "");
			$(this).css("box-shadow", "");
			$("#header_notice > #header_contextmenu").hide();
			
			$("#header_opt").css("background-color", "");
			$("#header_opt").css("box-shadow", "");
			$("#header_opt > #header_contextmenu").hide();
			
		<%-- 헤더_알림 하위 메뉴 보이기 --%>
		} else {
			headerSelectNoticeContent(); <%-- 헤더_알림 내용 조회 function --%>
			
			$("#header_form input[name='current_noticecnt']").val($("#header_form input[name='before_noticecnt']").val());
			
			$(this).css("background-color", "#EFEFEF");
			$(this).css("box-shadow", "0 0 0 4px rgba(0, 132, 255, 0.5)");
			$("#header_notice > #header_contextmenu").show();
			
			$("#header_opt").css("background-color", "");
			$("#header_opt").css("box-shadow", "");
			$("#header_opt > #header_contextmenu").hide();
		}
	});
	
	<%-- 헤더_알림 내용 클릭 시 페이지 이동 --%>
	$("#header_ctm_popup").on("click", ".header_ctm_content", function() {
		var notice_no = $(this).attr("id");
		
		<%-- 헤더_알림 내용의 id값이 있을 경우 --%>
		if(typeof notice_no != "undefined" && notice_no != null && notice_no != "") {
			<%-- 팔로우 알람 내용일 경우 상대 프로필 정보 페이지로 이동 --%>
			if(notice_no.toUpperCase().indexOf("BM2".toUpperCase()) != -1) {
				$("#header_form input[name='bm_no']").val(notice_no);
				
				$("#header_form").attr("action", "profile");
				$("#header_form").submit();
				
			<%-- 좋아요 알람 내용일 경우 해당 본인 메인 게시글 상세 페이지로 이동 --%>
			} else if(notice_no.toUpperCase().indexOf("FEED2".toUpperCase()) != -1) {
				$("#header_form input[name='feed_no']").val(notice_no);
				
				$("#header_form").attr("action", "feedview");
				$("#header_form").submit();
				
			<%-- 댓글 알람 내용일 경우 해당 본인 메인 게시글 상세 페이지로 이동 --%>
			} else if(notice_no.toUpperCase().indexOf("FEED2".toUpperCase()) != -1) {
				$("#header_form input[name='feed_no']").val(notice_no);
				
				$("#header_form").attr("action", "feedview");
				$("#header_form").submit();
				
			<%-- 답글 알람 내용일 경우 해당 본인 메인 게시글 상세 페이지로 이동 --%>
			} else if(notice_no.toUpperCase().indexOf("FEED2".toUpperCase()) != -1) {
				$("#header_form input[name='feed_no']").val(notice_no);
				
				$("#header_form").attr("action", "feedview");
				$("#header_form").submit();
				
			<%-- 알람이 없을 경우 값 초기화 --%>
			} else {
				$("#header_form input[name='bm_no']").val("");
				$("#header_form input[name='feed_no']").val("");
				
				$("#header_form").attr("action", "#");
			}
			
		<%-- 헤더_알림 내용의 id값이 없을 경우 --%>
		} else {
			$("#header_form input[name='bm_no']").val("");
			$("#header_form input[name='feed_no']").val("");
			
			$("#header_form").attr("action", "#");
		}
	});
	
<%--================================= 헤더_프로필 옵션 =================================--%>
	<%-- 헤더_프로필 옵션 클릭 시 이벤트 --%>
	$("#header_opt").click(function() {
		<%-- 헤더_프로필 옵션 하위태그(div)가 show상태이면 hide로 변경 --%>
		if($("#header_opt > #header_contextmenu").css("display") != "none") {
			$(this).css("background-color", "");
			$(this).css("box-shadow", "");
			$("#header_opt > #header_contextmenu").hide();
			
			$("#header_notice").css("background-color", "");
			$("#header_notice").css("box-shadow", "");
			$("#header_notice > #header_contextmenu").hide();
			
		<%-- 헤더_프로필 옵션 하위태그(div)가 hide상태이면 show로 변경 --%>
		} else {
			$(this).css("background-color", "#EFEFEF");
			$(this).css("box-shadow", "0 0 0 4px rgba(0, 132, 255, 0.5)");
			$("#header_opt > #header_contextmenu").show();
			
			$("#header_notice").css("background-color", "");
			$("#header_notice").css("box-shadow", "");
			$("#header_notice > #header_contextmenu").hide();
		}
	});
	
<%--================================= 헤더_1:1문의 =================================--%>
	<%-- ? 버튼 클릭 시 1:1문의 팝업창 활성화 --%>
	$("#header_addcomplain").click(function() {
		var header_complain_html = "";
		
		header_complain_html += "<div id=\"header_complain_popup\" class=\"header_complain_popup\">";
		header_complain_html += "	<h1 style=\"text-align: center; padding: 16px 0px 32px 0px; margin: 0px; font-size: 28px; font-weight: bold;\">1:1 문의</h1>";
		header_complain_html += "	<div style=\"display: flex; padding: 12px 16px; border-top: 1px solid #DDDDDD;\">";
		header_complain_html += "		<div class=\"header_complain_label\">제목</div>";
		header_complain_html += "		<textarea id=\"header_cp_title\" class=\"header_complain_txt\" spellcheck=\"false\" maxlength=\"100\" placeholder=\"100자 이내\"></textarea>";
		header_complain_html += "	</div>";
		header_complain_html += "	<div style=\"display: flex; padding: 12px 16px; border-top: 1px solid #DDDDDD;\">";
		header_complain_html += "		<div class=\"header_complain_label\">내용</div>";
		header_complain_html += "		<textarea id=\"header_cp_content\" class=\"header_complain_txt\" spellcheck=\"false\" maxlength=\"300\" placeholder=\"300자 이내\"></textarea>";
		header_complain_html += "	</div>";
		header_complain_html += "	<div style=\"display: flex; padding: 32px; flex-direction: row; justify-content: flex-end; width: 100%;\">";
		header_complain_html += "		<div id=\"header_complain_cancelbtn\" class=\"header_complain_btn\">취소</div>";
		header_complain_html += "		<div id=\"header_complain_insertbtn\" class=\"header_complain_btn\">등록</div>";
		header_complain_html += "	</div>";
		header_complain_html += "</div>";
		
		$("#header_complain").html(header_complain_html);
		
		$("#header_overlay").show();
		$("body").css("overflow", "hidden");
		
		<%-- 헤더_1:1문의 팝업창 활성화 시 헤더_1:1문의 입력란 사이즈 초기화 --%>
		$(".header_complain_txt").each(function() {
			$(this).height(1).height($(this).prop("scrollHeight") - 17);
		});
	});
	
	<%-- 헤더_1:1문의 입력란 작성 시 자동 리사이즈 --%>
	$("#header_complain").on("keydown keyup", ".header_complain_txt", function(event) {
		$(this).height(1).height($(this).prop("scrollHeight") - 17);
		
		<%-- 엔터키 클릭 시 적용불가. --%>
		if(event.which == 13) {
			event.preventDefault();
			$("#header_complain #header_complain_insertbtn").click();
		}
	});
	
	<%-- 헤더_1:1문의 취소 버튼 클릭 시 1:1문의 팝업창 비활성화 --%>
	$("#header_complain").on("click", "#header_complain_cancelbtn", function() {
		$("#header_complain").empty();
		
		$("#header_overlay").hide();
		$("body").css("overflow", "auto");
	});
	
	<%-- 헤더_header_overlay 클릭 시 1:1문의 팝업창 비활성화 --%>
	$("#header_overlay").click(function() {
		$("#header_complain #header_complain_cancelbtn").click();
	});
	
	<%-- 헤더_1:1문의 등록 버튼 클릭 시 이벤트 --%>
	$("#header_complain").on("click", "#header_complain_insertbtn", function() {
		var header_cp_title = $("#header_cp_title");
		var header_cp_content = $("#header_cp_content");
		
		var result = 0;
		
		<%-- 헤더_1:1문의 제목이 빈 칸일 경우 --%>
		if($.trim(header_cp_title.val()) == "") {
			header_cp_title.val("");
			header_cp_title.attr("placeholder", "제목을 입력해야 1:1문의 등록이 가능합니다");
			header_cp_title.css("box-shadow", "0 0 0 4px rgba(0, 132, 255, 0.5)");
			header_cp_title.focus();
			
		<%-- 헤더_1:1문의 내용이 빈 칸일 경우 --%>
		} else if($.trim(header_cp_content.val()) == "") {
			header_cp_content.val("");
			header_cp_content.attr("placeholder", "내용을 입력해야 1:1문의 등록이 가능합니다");
			header_cp_content.css("box-shadow", "0 0 0 4px rgba(0, 132, 255, 0.5)");
			header_cp_content.focus();
			
		<%-- 모든 조건이 만족할 경우 --%>
		} else {
			$("#header_form input[name='cp_title']").val(header_cp_title.val());
			$("#header_form input[name='cp_content']").val(header_cp_content.val());
			$("#header_form input[name='bm_no']").val("${session_bm_no}");
			
			result = headerInsert_Complain() <%-- 헤더_1:1문의 삽입 function --%>
			
			if(result == 1) {
				swal("1:1문의가 등록되었습니다.", "", "success");
				
				$("#header_form input[name='cp_title']").val("");
				$("#header_form input[name='cp_content']").val("");
				$("#header_form input[name='bm_no']").val("");
				
				$("#header_complain #header_complain_cancelbtn").click();
				
			} else if(result == 0) {
				swal("1:1문의 등록이 안됐습니다.", "", "warning");
				
				$("#header_form input[name='cp_title']").val("");
				$("#header_form input[name='cp_content']").val("");
				$("#header_form input[name='bm_no']").val("");
				
			} else {
				swal("1:1문의 등록에 문제가 발생하였습니다.", "", "error");
				
				$("#header_form input[name='cp_title']").val("");
				$("#header_form input[name='cp_content']").val("");
				$("#header_form input[name='bm_no']").val("");
			}
		}
	});
});
</script>
<%--================================== 헤더_폼 ==================================--%>
	<form action="#" id="header_form" method="post">
		<%-- 헤더_알림 --%>
		<input type="hidden" name="before_noticecnt" value="0"/> <%-- 이전 헤더_알림 수 --%>
		<input type="hidden" name="current_noticecnt" value="0"/> <%-- 현재 헤더_알림 수 --%>
		<input type="hidden" name="header_interval_clear" value="0"/> <%-- 현재 헤더_알림 수 --%>
		
		<%-- 헤더_프로필 --%>
		<input type="hidden" name="bm_no"/> <%-- 일반회원 번호 --%>
		
		<%-- 헤더_메인 게시글 상세 --%>
		<input type="hidden" name="feed_no"/> <%-- 메인 게시글 번호 --%>
		<input type="hidden" name="feed_search" value="${param.feed_search}" /> <%-- 메인 게시글 검색 --%>
		
		<%-- 헤더_1:1문의 --%>
		<input type="hidden" name="cp_title"/>   <%-- 1:1문의 제목 --%>
		<input type="hidden" name="cp_content"/> <%-- 1:1문의 내용 --%>
	</form>
	
<%--================================== 헤더_더미 ==================================--%>
	<div style="margin-top: 120px;"></div>
	
<%--================================== 헤더 ==================================--%>
	<div class="header_wrap">
		<%-- 헤더_메인 로고 --%>
		<a href="feed" id="header_logo" class="header_menu" style="min-width: auto;">
			<img alt="logo" src="resources/images/etc/madeblack.png" height="24"/>
		</a>
		
		<%-- 헤더_검색 --%>
		<div id="header_search" class="header_search">
			<%-- 헤더_검색 버튼 --%>
			<div id="header_searchbtn" class="header_menu" style="min-width: 48px;">
				<img alt="serach" src="resources/images/etc/search.png" height="16"/>
			</div>
			
			<%-- 헤더_검색 입력란 --%>
			<input type="text" id="header_search_txt" class="header_search_txt" placeholder="검색"/>
			
			<%-- 헤더_검색 취소 버튼 --%>
			<div id="header_search_canclebtn" class="header_menu" style="display: none; min-width: 48px;">
				<img alt="cancle" src="resources/images/etc/search_cancle.png" height="20"/>
			</div>
		</div>
		
		<%-- 로그인 상태이면 --%>
		<c:if test="${not empty session_bm_id}">
			<%-- 헤더_알림 --%>
			<div id="header_notice" class="header_menu" style="position: relative;">
				<img alt="notice" src="resources/images/etc/notice.png" width="24" height="24"/>
				<div id="header_notice_cnt" class="header_notice_cnt" style="display: none;"></div> <%-- 헤더_알림 수 --%>
				<div id="header_contextmenu" class="header_contextmenu" style="display: none; top: 64px; right: -84px; width: 360px;">
					<div id="header_ctm_popup" style="overflow: auto; max-height: 360px;"></div> <%-- 헤더_알림 내용 --%>
				</div>
			</div>
			
			<%-- 헤더_프로필 사진 --%>
			<div id="header_profile" class="header_menu">
				<div style="position: relative; height: 24px; width: 24px;">
					<c:if test="${empty session_bm_img}">
						<img id="header_profile_img" class="header_profile_img" src="resources/images/etc/non_profile.png"/>
					</c:if>
					<c:if test="${not empty session_bm_img}">
						<img id="header_profile_img" class="header_profile_img" src="${session_bm_img}"/>
					</c:if>
				</div>
			</div>
			
			<%-- 헤더_프로필 옵션 --%>
			<div id="header_opt" class="header_menu" style="position: relative; min-width: 24px; min-height: 24px;">
				<img alt="option" src="resources/images/etc/arrow_bottom.png" height="14"/>
				<%-- 헤더_컨텍스트 메뉴 --%>
				<div id="header_contextmenu" class="header_contextmenu" style="display: none; top: 30px; right: -12px; min-width: 140px;">
					<a class="header_ctm_content" href="edit" style="font-weight: 700;">프로필 편집</a>
					<a class="header_ctm_content" href="logout" style="font-weight: 700;">로그아웃</a>
				</div>
			</div>
		</c:if>
		
		<%-- 로그아웃 상태이면 --%>
		<c:if test="${empty session_bm_id}">
			<%-- 헤더_로그인 버튼 --%>
			<a class="header_login" href="login">로그인</a>
		</c:if>
	</div>
	
<%--================================== 헤더_뒤로가기 ==================================--%>
	<%-- 헤더_뒤로가기 --%>
	<a id="header_backbtn" class="header_menu" href="javascript:history.back()">
		<img alt="뒤로가기" src="resources/images/etc/backarrow.png" height="20" width="20"/>
	</a>
	
<%--================================== 헤더_추가 ==================================--%>
	<%-- 로그인 상태이면 --%>
	<c:if test="${not empty session_bm_id}">
		<div class="header_add">
			<%-- 로그인 상태이고 권한이 관리자(1) 일 경우 --%>
			<c:if test="${not empty session_bm_no and session_bm_auth eq 1}">
				<%-- 헤더_관리자 페이지 --%>
				<a class="header_addbtn" href="adminlist" style="font-size: 16px; font-weight: 700; color: #111111; text-decoration: none;">A</a>
			</c:if>
			
			<%-- 헤더_글 추가 --%>
			<a id="header_addfeed" class="header_addbtn" href="addfeed">
				<img alt="글추가" src="resources/images/etc/addfeed.png" height="16" width="16"/>
			</a>
			
			<%-- 헤더_1:1문의 추가 --%>
			<div id="header_addcomplain" class="header_addbtn">
				<img alt="1:1문의" src="resources/images/etc/complain.png" height="16" width="16"/>
			</div>
		</div>
	</c:if>
	
<%--================================== 1:1문의 ==================================--%>
	<div id="header_complain"></div>
	
<%--================================== header_overlay ==================================--%>
	<div id="header_overlay" class="header_overlay"></div>