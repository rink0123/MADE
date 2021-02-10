<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- <link rel="stylesheet" type="text/css" href="resources/slick/slick.css"/> --> <%-- slick 디자인 --%>
<!-- <link rel="stylesheet" type="text/css" href="resources/slick/slick-theme.css"/> --> <%-- slick 디자인 테마 --%>
<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.css"/> <%-- slick 디자인 CDN --%>
<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick-theme.min.css"/> <%-- slick 디자인 테마 CDN --%>
<link rel="stylesheet" type="text/css" href="resources/css/feedview/feedview.css"/> <%-- 메인 게시글 상세보기_CSS --%>
<script type="text/javascript" src="https://code.jquery.com/jquery-latest.min.js"></script> <%-- jQuery CDN --%>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script> <%-- sweetalert 알림창 CDN --%>
<%-- jQuery 뒤에 slick.min.js를 추가합니다(jQuery 1.7 이상 필요) --%>
<!-- <script type="text/javascript" src="resources/slick/slick.min.js"></script> --> <%-- slick --%>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script> <%-- slick CDN --%>
<script type="text/javascript" src="https://sdk.amazonaws.com/js/aws-sdk-2.806.0.min.js"></script> <%-- S3 CDN --%>
<script type="text/javascript" src="resources/js/feedview/feedS3File.js"></script> <%-- S3 파일 업로드 --%>
<script type="text/javascript" src="resources/js/feedview/feedViewSelect.js"></script> <%-- 메인 게시글 상세보기_조회 --%>
<script type="text/javascript" src="resources/js/feedview/feedViewInsert.js"></script> <%-- 메인 게시글 상세보기_삽입 --%>
<script type="text/javascript" src="resources/js/feedview/feedViewUpdate.js"></script> <%-- 메인 게시글 상세보기_갱신 --%>
<script type="text/javascript" src="resources/js/feedview/feedViewDelete.js"></script> <%-- 메인 게시글 상세보기_삭제 --%>

<title>MADE | VIEW</title>

<script type="text/javascript">
<%--
	페이지 첫 로드 시 HTML 만들기도 전에 JS에서 접근해서 생기는 문제로 인해 페이지 로드 후 데이터 뿌림.
	Cannot read property '변수명' of null
--%>
$(window).load(function() {
	feedViewSelect_Content(); <%-- 글내용 조회 function --%>
	feedViewSelect_Like();     <%-- 좋아요 조회 function --%>
	feedViewSelect_Comments(); <%-- 댓글/댓글-답글 조회 function --%>
	feedViewSelect_Image();    <%-- 사진 조회 function --%>
	
	<%-- 글내용 조회 function에서 bm_no 값 생성 후 팔로우 조회가 가능하기에 0.3초 후 실행하도록 함 --%>
	setTimeout(function() {
		feedViewSelect_Follow();   <%-- 팔로우 조회 function --%>
	}, 300);
	
});

$(document).ready(function() {
	<%-- onload 시 window size 조정 --%>
	<%-- if($(window).width() <= 1060) {
		$(".feedview_container").css("width", "540px");
		$(".feedview_container").css("min-width", "");
	} else {
		$(".feedview_container").css("width", "");
		$(".feedview_container").css("min-width", "540px");
	} --%>
	
	<%-- 실시간 window size 조정 --%>
	<%-- $(window).resize(function() {
		if($(window).width() <= 1060) {
			$(".feedview_container").css("width", "540px");
			$(".feedview_container").css("min-width", "");
		} else {
			$(".feedview_container").css("width", "");
			$(".feedview_container").css("min-width", "540px");
		}
	}); --%>
	
	<%-- 팝업창_background 클릭 시 팝업창들 모두 종료 --%>
	$("#feedview_back_index").on("click", function() {
		background_popup(); <%-- 팝업창_background function --%>
	});
	
<%---------------------------------- 메인 게시글 상세보기_왼쪽(이미지)_전체 컨테이너 ----------------------------------%>
	<%-- 이미지 삭제 버튼 클릭 시 해당 이미지 삭제 --%>
	$("#feedview_img_deletebtn_box").on("click", "#feedview_img_deletebtn", function() {
		var fi_no = $("#feedview_imgs_container .slick-current").children("img").attr("id");
		var slick_cnt = $("#feedview_imgs_container").children(".slick-dots").find("li").length;
		
		<%-- 사진이 하나 남아있을 경우 --%>
		if(slick_cnt < 2) {
			swal("사진을 더 이상 삭제할 수 없습니다.", "더 삭제하시려면 글을 지워야 합니다.", "warning");
			$("#feedview_form input[name='fi_no']").val("");
			
		<%-- 사진이 하나 이상 남아있을 경우 --%>
		} else {
			swal({
				title: "정말 삭제하시겠어요?",
				text: "삭제한 후에는 이 작업을 실행 취소할 수 없습니다.",
				icon: "warning",
				buttons: ["취소", "삭제"],
				dangerMode: true,
			}).then((result) => {
				if (result) {
					var fileFullName = $("#feedview_imgs_container .slick-current").children("img").attr("src");
					var fileName = fileFullName.substring(fileFullName.indexOf("mainimage"), fileFullName.length); <%-- mainimage/파일명.파일확장자 --%>
					var photoKey = decodeURIComponent(fileName.replace("+", " ")); <%-- S3 파일업로드 및 삭제 시 키값을 기준으로 작업하기에 데이터를 decode 시 +(공백을 치환한 것)를 다시 공백으로 치환해야한다 --%>
					
					swal({
					    title: '',
					    text: '사진을 삭제하는 중입니다.',
					    icon: 'info',
					    timer: 60000, /* 최대 1분으로 설정 */
					    buttons: false,
					    closeModal: false,
					    closeOnClickOutside: false,
					    closeOnEsc: false,
					});
					
					deletePhoto(photoKey, fi_no); <%-- S3 파일 삭제(단일) function --%>
					
				} else {
					swal("삭제 취소되었습니다.");
				}
			});
		}
	});
	
	<%-- 사진 추가 버튼 클릭 시 window 파일 선택 창 활성화 --%>
	$("#feedview_addphoto_box").on("click", "#feedview_addphoto", function() {
		var slick_cnt = $("#feedview_imgs_container").children(".slick-dots").find("li").length;
		
		<%-- 사진이 10개 이상일 경우 --%>
		if(slick_cnt >= 10) {
			swal("사진을 더 이상 추가할 수 없습니다.", "추가하시려면 사진을 지워야 합니다.", "warning");
			
		<%-- 사진이 10개 이하일 경우 --%>
		} else {
			$("#feedview_img_fileupload").val("");
			$("#feedview_img_fileupload").click();
		}
	});
	
	<%-- 사진 추가 파일 업로드 value값이 바뀌었을 시 이벤트 --%>
	$("#feedview_addphoto_box").on("change", "#feedview_img_fileupload", function() {
		var file = $(this)[0].files[0];
		
		<%--  파일 타입이 image/jpeg이 아닐 경우 파일업로드 불가 --%>
		if(file.type != "image/jpeg") {
			swal("사진을 올릴 수 없습니다.", ".jfif, .pjpeg, .jpeg, .pjp, .jpg인 파일 확장자만 허용됩니다.", "warning");
		
		<%--  파일 용량이 20MB 초과일 경우 --%>
		} else if(file.size > (20 * 1024 * 1024)) {
			swal("사진을 올릴 수 없습니다.", "20MB 초과인 사진은 올릴 수 없습니다.", "warning");
			
		<%-- 모든 조건 만족 시 파일 업로드 가능 --%>
		} else {
			addPhoto($(this).attr("id"), "mainimage"); <%-- S3 파일업로드(단일) function --%>
			
			swal({
			    title: '',
			    text: '사진을 올리는 중입니다.',
			    icon: 'info',
			    timer: 60000, /* 최대 1분으로 설정 */
			    buttons: false,
			    closeModal: false,
			    closeOnClickOutside: false,
			    closeOnEsc: false,
			});
		}
	});
	
<%---------------------------------------------------------- 신고 popup ----------------------------------------------------------%>
	<%-- 글 신고 버튼 클릭 시 신고 팝업창 활성화 --%>
	$("#feedview_report_btn").on("click", function() {
		$("#feedview_back_index").css("display", "block");
		$("#feedview_rpanswer_popup").css("display", "block");
	});
	
	<%-- 신고-답변_내용 라인 클릭 시 radio:checked --%>
	$(".feedview_rpanswer_line").on("click", function() {
		$(this).find("input[name='feedview_rpanswer_radio']").prop("checked", true);
	});
	
	<%-- 신고-답변_취소 버튼 클릭 시 신고 팝업창 비활성화 --%>
	$("#feedview_rpanswer_cancelbtn").on("click", function() {
		background_popup(); <%-- 팝업창_background function --%>
		
		$("input[name='rpans_content']").val("");
	});
	
	<%-- 신고-답변_접수 버튼 클릭 시 이벤트 --%>
	$("#feedview_rpanswer_reportbtn").on("click", function() {
		<%-- 신고-답변_내용 체크된 것이 있을 경우 --%>
		if($("input:radio[name='feedview_rpanswer_radio']").is(":checked")) {
			<%-- 신고-답변_내용 체크 확인 --%>
			$("input[name='feedview_rpanswer_radio']:checked").each(function() {
				var feedview_rpanswer_content = $(this).siblings().children("#feedview_rpanswer_content");
				
				$("input[name='rpans_content']").val(feedview_rpanswer_content.text()); <%-- form-data 값 셋팅 --%>
				
				feedViewInsert_Report(); <%-- 신고 삽입 function --%>
				
				background_popup(); <%-- 팝업창_background function --%>
			});
			
		<%-- 신고-답변_내용 체크된 것이 없을 경우 --%>
		} else {
			swal("", "신고 내용을 선택해주셔야 접수가 가능합니다.", "info");
		}
		
		$("input[name='rpans_content']").val(""); <%-- form-data 값 초기화 --%>
	});
	
<%---------------------------------------------------------- 좋아요 ----------------------------------------------------------%>
	<%-- 좋아요 버튼 클릭 시 이벤트 --%>
	$("#feedview_like_btn").on("click", function() {
		<%-- 좋아요 off 상태이면 --%>
		if($("#feedview_like_img").attr("alt") == "offlike") {
			feedViewInsert_Like(); <%-- 좋아요 삽입 function --%>
			
		<%-- 좋아요 on 상태이면 --%>
		} else if($("#feedview_like_img").attr("alt") == "onlike") {
			feedViewDelete_Like(); <%-- 좋아요 삭제 function --%>
		}
	});

<%---------------------------------------------------------- 팔로우 ----------------------------------------------------------%>
<%-- 팔로우 버튼 클릭 시 이벤트 --%>
$("#feedview_follow_btn").on("click", function() {
	if($("#feedview_follow_btn").text() == "팔로우") {
		feedViewInsert_Follow(); <%-- 팔로우 삽입 function --%>
		
	} else {
		feedViewDelete_Follow(); <%-- 팔로우 삭제 function --%>
	}
});

<%---------------------------------------------------------- 글내용 ----------------------------------------------------------%>
	<%-- 메인 게시글 프로필 사진 클릭 시 프로필 정보 페이지로 이동 --%>
	$("#feedview_profile").on("click", ".feedview_photo", function() {
		$("input[name='bm_no']").val($(this).attr("id"));
		
		$("#feedview_form").attr("action", "profile");
		$("#feedview_form").submit();
	});
	
	<%-- 메인 게시글 닉네임 클릭 시 프로필 정보 페이지로 이동 --%>
	$("#feedview_profile").on("click", ".feedview_nick_txt", function() {
		$("input[name='bm_no']").val($(this).attr("id"));
		
		$("#feedview_form").attr("action", "profile");
		$("#feedview_form").submit();
	});
	
	<%-- 글 수정 버튼 클릭 시 글 수정_팝업창 활성화 --%>
	$("#feedview_update_btn").on("click", function() {
		var feedview_right_title = $("#feedview_right_title").text();
		var feedview_right_content = $("#feedview_right_content").text();
		
		var feedview_content_popup = "";
		
		feedview_content_popup += "<div id=\"feedview_content_popup\" class=\"feedview_content_popup\">";
		feedview_content_popup += "	<h1 style=\"text-align: center; padding: 16px 0px 32px 0px; margin: 0px; font-size: 28px; font-weight: bold; color: #111111;\">이 보드 수정하기</h1>";
		feedview_content_popup += "	<div style=\"padding: 12px 16px; border-top: 1px solid #DDDDDD;\">";
		feedview_content_popup += "		<div class=\"feedview_content_label\">섹션</div>";
		feedview_content_popup += "		<select class=\"feedview_content_select\">";
		feedview_content_popup += "			<option value=\"FEEDCAT0001\">의류</option>";
		feedview_content_popup += "			<option value=\"FEEDCAT0002\">잡화</option>";
		feedview_content_popup += "			<option value=\"FEEDCAT0003\">코스메틱</option>";
		feedview_content_popup += "			<option value=\"FEEDCAT0004\">액세서리</option>";
		feedview_content_popup += "		</select>";
		feedview_content_popup += "	</div>";
		feedview_content_popup += "	<div style=\"padding: 12px 16px; border-top: 1px solid #DDDDDD;\">";
		feedview_content_popup += "		<div class=\"feedview_content_label\">제목</div>";
		feedview_content_popup += "		<textarea id=\"feedview_content_title\" class=\"feedview_content_txt\" spellcheck=\"false\" maxlength=\"100\" placeholder=\"필수 입력, 100자 이내\">" + feedview_right_title + "</textarea>";
		feedview_content_popup += "	</div>";
		feedview_content_popup += "	<div style=\"padding: 12px 16px; border-top: 1px solid #DDDDDD;\">";
		feedview_content_popup += "		<div class=\"feedview_content_label\">설명</div>";
		feedview_content_popup += "		<textarea id=\"feedview_content_description\" class=\"feedview_content_txt\" spellcheck=\"false\" maxlength=\"300\" placeholder=\"300자 이내\">" + feedview_right_content + "</textarea>";
		feedview_content_popup += "	</div>";
		feedview_content_popup += "	<div style=\"padding: 32px; text-align: left;\">";
		feedview_content_popup += "		<div id=\"feedview_content_deletebtn\" class=\"feedview_content_btn\">삭제</div>";
		feedview_content_popup += "		<div style=\"display: inline-block; float: right;\">";
		feedview_content_popup += "			<div id=\"feedview_content_cancelbtn\" class=\"feedview_content_btn\">취소</div>";
		feedview_content_popup += "			<div id=\"feedview_content_updatebtn\" class=\"feedview_content_btn\">저장</div>";
		feedview_content_popup += "		</div>";
		feedview_content_popup += "	</div>";
		feedview_content_popup += "</div>";
		
		$("#feedview_back_index").css("display", "block");
		$("#feedview_content_update").html(feedview_content_popup);
		
		$(".feedview_content_select").val($("input[name='feed_type_cd']").val());
		
		$(".feedview_content_txt").each(function() {
			$(this).height(1).height($(this).prop("scrollHeight") - 17);
		});
		
		$(".feedview_content_txt").on("keydown keyup", function(event) {
			$(this).height(1).height($(this).prop("scrollHeight") - 17);
			
			<%-- 답글 작성 중 엔터키 클릭 시 적용불가. --%>
			if(event.which == 13) {
				event.preventDefault();
			}
		});
	});
	
	<%-- 글 수정_삭제 버튼 클릭 시 이벤트 --%>
	$("#feedview_content_update").on("click", "#feedview_content_deletebtn", function() {
		swal({
			title: "정말 삭제하시겠어요?",
			text: "글을 삭제한 후에는 이 작업을 실행 취소할 수 없습니다.",
			icon: "warning",
			buttons: ["취소", "삭제"],
			dangerMode: true,
			})
			.then((result) => {
				if (result) {
					var fi_no = $("#feedview_imgs_container .slick-track").find("[id^=slick-slide]").find("[id^=FI]");
					var photoKey = new Array();
					
					fi_no.each(function(index) {
						<%-- 해당 이미지의 src가 null이 아니거나 공백이 아닌 경우 배열에 S3 photoKey 넣기 --%>
						if($(this).attr("src") != null || $.trim($(this).attr("src")) != "") {
							var fileFullName = decodeURIComponent($(this).attr("src").substring($(this).attr("src").indexOf("mainimage")));
							var fileName = fileFullName.replace("+", " ");<%-- S3 파일업로드 및 삭제 시 키값을 기준으로 작업하기에 데이터를 decode 시 +(공백을 치환한 것)를 다시 공백으로 치환해야한다 --%>
							
							photoKey[index] = decodeURIComponent(fileName);
						}
						
						<%-- S3 photoKey 배열 길이와 메인 게시글 사진 수와 같을 경우 해당 게시글의 S3 사진 삭제 --%>
						if(photoKey.length == fi_no.length) {
							swal({
							    title: '',
							    text: '글을 삭제하는 중입니다.',
							    icon: 'info',
							    timer: 60000,
							    buttons: false,
							    closeModal: false,
							    closeOnClickOutside: false,
							    closeOnEsc: false,
							});
							
							deleteAllPhoto(photoKey); <%-- S3 파일 삭제(복수) function --%>
						}
					});
					
				} else {
					swal("삭제 취소되었습니다.");
				}
			});
	});
	
	<%-- 글 수정_취소 버튼 클릭 시 글 수정_팝업창 비활성화 --%>
	$("#feedview_content_update").on("click", "#feedview_content_cancelbtn", function() {
		background_popup(); <%-- 팝업창_background function --%>
	});
	
	<%-- 글 수정_저장 버튼 클릭 시 이벤트 --%>
	$("#feedview_content_update").on("click", "#feedview_content_updatebtn", function() {
		var feedview_content_title = $("#feedview_content_title");
		var feedview_content_description = $("#feedview_content_description");
		
		<%-- 수정할 제목이 빈 칸이 아닐경우 --%>
		if($.trim(feedview_content_title.val()) != "") {
			$("input[name='feed_type_cd']").val($(".feedview_content_select").val());
			$("input[name='feed_title']").val(feedview_content_title.val());
			$("input[name='feed_content']").val($.trim(feedview_content_description.val()));
			
			feedViewUpdate_Content(); <%-- 글내용 수정 function --%>
			
			background_popup(); <%-- 팝업창_background function --%>
			
			feedview_content_title.attr("placeholder", "필수 입력, 100자 이내");
			feedview_content_title.css("box-shadow", "");
			
		<%-- 수정할 제목이 빈 칸일 경우 --%>
		} else {
			feedview_content_title.focus();
			feedview_content_title.attr("placeholder", "내용을 입력해야 댓글 등록이 가능합니다.");
			feedview_content_title.css("box-shadow", "0px 0px 0px 4px rgba(0, 132, 255, 0.5)");
		}
	});
	
<%---------------------------------------------------------- 댓글 ----------------------------------------------------------%>
	<%-- 댓글 입력란 프로필 사진 클릭 시 프로필 정보 페이지로 이동 --%>
	$("#feedview_comments_insertbox").on("click", ".feedview_photo", function() {
		$("input[name='bm_no']").val($("input[name='session_bm_no']").val());
		
		$("#feedview_form").attr("action", "profile");
		$("#feedview_form").submit();
	});
	
	<%-- 댓글 입력란 입력 시 자동 높이 조절 --%>
	$(".feedview_comments_write").on("keydown keyup", function(event) {
		$(this).height(1).height($(this).prop("scrollHeight") - 33);
	
		<%-- 댓글 작성 중 엔터키 클릭 시 적용 불가 --%>
		if(event.which == 13) {
			event.preventDefault();
		}
	});
	
	<%-- 댓글 입력란 focus 시 이벤트 --%>
	$(".feedview_comments_write").focus(function() {
		<%-- 댓글 입력란 CSS효과 활성화 --%>
		$(this).css("border-radius", "16px");
		$(this).attr("maxlength", "300");
		
		$("#feedview_comments_btnbox").css("display", "block"); <%-- 댓글 취소&등록_버튼 활성화 --%>
		
		<%-- 댓글 입력란 focusout 시 이벤트 --%>
		$(this).focusout(function() {
			<%-- 댓글 입력란 CSS효과 비활성화 --%>
			$(this).css("border-radius", "32px");
			$(this).attr("maxlength", "");
		});
	});
	
	<%-- 댓글 등록 버튼 클릭 시 이벤트 --%>
	$("#feedview_comments_insertbtn").on("click", function() {
		<%-- 댓글 입력란 내용이 null이 아니고 비어있지 않을 경우 --%>
		if($("#feedview_comments_write").val() != null && $.trim($("#feedview_comments_write").val()) != "") {
			$("input[name='cmt_content']").val($("#feedview_comments_write").val()); <%-- form-data 값 셋팅 --%>
			
			feedViewInsert_Comments(); <%-- 댓글 삽입 function --%>
			
			$("input[name='cmt_content']").val(""); <%-- form-data 값 초기화 --%>
			
			<%-- 댓글 입력란 내용 초기화 및 CSS효과 비활성화 --%>
			$("#feedview_comments_write").attr("placeholder", "300자 이내");
			$("#feedview_comments_write").val("");
			$("#feedview_comments_write").css("box-shadow", "");
			$("#feedview_comments_write").css("height", "46px");
			$("#feedview_comments_btnbox").css("display", "none");
			
		<%-- 댓글 입력란 내용이 null이고 비어있을 경우 --%>
		} else {
			$("#feedview_comments_write").focus();
			$("#feedview_comments_write").attr("placeholder", "내용을 입력해야 댓글 등록이 가능합니다.");
			
			$("#feedview_comments_write").css("box-shadow", "0px 0px 0px 4px rgba(0, 132, 255, 0.5)");
		}
	});
	
	<%-- 댓글 취소 버튼 클릭 시 이벤트 --%>
	$("#feedview_comments_canclebtn").on("click", function() {
		$("#feedview_comments_write").val("");
		$("#feedview_comments_write").attr("placeholder", "300자 이내");
		
		<%-- 댓글 입력란 CSS효과 비활성화 --%>
		$("#feedview_comments_btnbox").css("display", "none");
		$("#feedview_comments_write").css("height", "46px");
		$("#feedview_comments_write").css("box-shadow", "");
		
	});
	
	<%-- 등록된 댓글 내용, 답글 프로필 사진 클릭 시 이벤트 --%>
	$("#feedview_right_footer").on("click", ".feedview_photo", function() {
		$("input[name='bm_no']").val($(this).attr("id"));
		
		$("#feedview_form").attr("action", "profile");
		$("#feedview_form").submit();
	});
	
	<%-- 등록된 댓글 내용, 답글 닉네임 클릭 시 이벤트 --%>
	$("#feedview_right_footer").on("click", ".feedview_comments_writer", function() {
		$("input[name='bm_no']").val($(this).attr("id"));
		
		$("#feedview_form").attr("action", "profile");
		$("#feedview_form").submit();
	});
	
	<%-- 등록된 댓글 내용_답글 버튼 클릭 시 이벤트 --%>
	$("#feedview_right_footer").on("click", ".feedview_comments_replybtn", function() {
		var feedview_comments_box = $(this).parent(".feedview_comments_imgbtnbox").parent(".feedview_comments_box");
		var feedview_comments_replybtn = $(this);
		var feedview_reply_box = $(this).parent(".feedview_comments_imgbtnbox").parent(".feedview_comments_box").children(".feedview_comments_box");
		var bm_img = "${session_bm_img}";

		<%-- 프로필 사진이 없을 경우 --%>
		if(bm_img == null || bm_img == "") {
			bm_img = "resources/images/etc/non_profile.png";
		}
		
		var feedview_comments_html ="";
		
		feedview_comments_html += "<div id=" + feedview_comments_box.attr("id") + " class=\"feedview_comments_box\" style=\"margin-bottom: 8px; padding-left: 48px; text-align: left;\">";
		feedview_comments_html += "	<img class=\"feedview_comments_profileimg\" src=" + bm_img + " style=\"vertical-align: top; border-radius: 50%; margin-right: 10px; width: 32px; height: 32px;\" />";
		feedview_comments_html += "	<div class=\"feedview_comments_txtbox\" style=\"width: 326px;\">";
		feedview_comments_html += "		<textarea class=\"feedview_comments_txt\" spellcheck=\"false\" maxlength=\"300\" placeholder=\"300자 이내\" style=\"height: 12px;\"></textarea>";
		feedview_comments_html += "	</div>";
		feedview_comments_html += "	<div id=\"feedview_comments_btnbox\" style=\"text-align: right; padding: 10px;\">";
		feedview_comments_html += "		<div id=\"feedview_comments_canclebtn\" class=\"feedview_comments_btn\">취소</div>";
		feedview_comments_html += "		<div id=\"feedview_comments_insertbtn\" class=\"feedview_comments_btn\">등록</div>";
		feedview_comments_html += "	</div>";
		feedview_comments_html += "</div>";
		
		<%-- 답글 중복 생성 방지 --%>
		if(feedview_comments_box.attr("id") != feedview_reply_box.attr("id")) {
			feedview_comments_box.append(feedview_comments_html);
			
			<%-- 등록된 댓글 내용_답글 버튼 CSS효과 활성화. --%>
			feedview_comments_replybtn.css("border-radius", "50%");
			feedview_comments_replybtn.css("background-color", "#EFEFEF");
			
			<%-- 답글 내용 입력 시 이벤트 --%>
			$(".feedview_comments_txt").on("keydown keyup", function(event) {
				$(this).height(1).height($(this).prop("scrollHeight")); <%-- 답글 내용 자동 resize. --%>
				
				<%-- 답글 작성 중 엔터키 클릭 시 적용불가. --%>
				if(event.which == 13) {
					event.preventDefault();
				}
			});
		}
	});
	
	<%-- 답글 작성 중 취소 버튼 클릭 시 이벤트 --%>
	$("#feedview_right_footer").on("click", "#feedview_comments_canclebtn", function() {
		var feedview_comments_replybtn = $(this).parent("#feedview_comments_btnbox").parent(".feedview_comments_box").siblings(".feedview_comments_imgbtnbox").children(".feedview_comments_replybtn");
		var feedview_comments_canclebtn = $(this).parent("#feedview_comments_btnbox").parent(".feedview_comments_box");
		
		<%-- 등록된 댓글 내용_답글 버튼 CSS효과 비활성화. --%>
		feedview_comments_replybtn.css("border-radius", "");
		feedview_comments_replybtn.css("background-color", "");
		
		feedview_comments_canclebtn.remove(); <%-- 답글 작성폼 삭제 --%>
	});
	
	<%-- 답글 작성 중 등록 버튼 클릭 시 이벤트 --%>
	$("#feedview_right_footer").on("click", "#feedview_comments_insertbtn", function() {
		var feedview_comments_box = $(this).parent("#feedview_comments_btnbox").parent(".feedview_comments_box");
		var feedview_comments_txtbox = $(this).parent("#feedview_comments_btnbox").siblings(".feedview_comments_txtbox");
		var feedview_comments_txt = $(this).parent("#feedview_comments_btnbox").siblings(".feedview_comments_txtbox").children(".feedview_comments_txt");

		<%-- 답글 내용이 비어있을 경우 --%>
		if($.trim(feedview_comments_txt.val()) == "") {
			feedview_comments_txt.focus();
			feedview_comments_txt.attr("placeholder", "내용을 입력해야 댓글 등록이 가능합니다.");
			feedview_comments_txtbox.css("box-shadow", "0px 0px 0px 4px rgba(0, 132, 255, 0.5)");
		
		<%-- 답글 내용이 비어있지 않을 경우 --%>
		} else {
			$("input[name='cmtans_cmt_no']").val(feedview_comments_box.attr("id"));
			$("input[name='cmtans_content']").val(feedview_comments_txt.val());
			
			feedViewInsert_CmtAnswer(); <%-- 댓글-답글 삽입 function --%>
		}
	});
	
	<%-- 등록된 댓글&댓글-답글 내용_수정 버튼 클릭 시 이벤트 --%>
	$("#feedview_right_footer").on("click", ".feedview_comments_updatebtn", function() {
		var feedview_comments_box = $(this).parent(".feedview_comments_imgbtnbox").parent(".feedview_comments_box");
		var feedview_comments_txtbox = $(this).parent(".feedview_comments_imgbtnbox").siblings(".feedview_comments_txtbox");
		var feedview_comments_txt = $(this).parent(".feedview_comments_imgbtnbox").siblings(".feedview_comments_txtbox").find(".feedview_comments_txt");
		
		<%-- 등록된 댓글&댓글-답글 내용 입력란 readonly=true 일 경우. --%>
		if(feedview_comments_txt.prop("readonly") == true) {
			feedview_comments_txt.prop("readonly", false); <%-- 등록된 댓글&댓글-답글 내용 입력란 readonly=true => false. --%>

			<%-- 등록된 댓글 내용 박스 및 수정 버튼 CSS효과 활성화. --%>
			feedview_comments_txtbox.css("box-shadow", "rgba(0, 132, 255, 0.5) 0px 0px 0px 4px");
			$(this).css("border-radius", "50%");
			$(this).css("background-color", "#EFEFEF");
			
			<%-- 등록된 댓글 내용 마지막 글자에 focus. --%>
			feedview_comments_txt.focus();
			feedview_comments_txt[0].setSelectionRange(feedview_comments_txt.val().length, feedview_comments_txt.val().length);
				
			<%-- 등록된 댓글 내용 입력 시 이벤트 --%>
			feedview_comments_txt.on("keydown keyup", function(event) {
				$(this).height(1).height($(this).prop("scrollHeight")); <%-- 등록된 댓글 내용 자동 resize. --%>
				
				<%-- 등록된 댓글 작성 중 엔터키 클릭 시 적용불가. --%>
				if(event.which == 13) {
					event.preventDefault();
				}
			});
			
		<%-- 등록된 댓글&댓글-답글 내용 입력란 readonly=false 일 경우. --%>
		} else {
			swal({
				title: "댓글을 수정하시겠어요?",
				text: "",
				icon: "info",
				buttons: ["취소", "수정"],
				dangerMode: true,
				})
				.then((result) => {
					<%-- 등록된 댓글 내용 수정 버튼 클릭 시. --%>
					if (result) {
						if($.trim(feedview_comments_txt.val()) == "") {
							feedview_comments_txt.focus();
							
							swal("", "내용을 입력해주셔야 수정이 가능합니다.", "info");
							
						} else {
							<%-- 수정할 내용이 댓글-답글일 경우 --%>
							if(feedview_comments_box.attr("id").toLowerCase().indexOf("cmtans") >= 0) {
								$("input[name='cmtans_no']").val(feedview_comments_box.attr("id"));
								$("input[name='cmtans_content']").val(feedview_comments_txt.val());
								
								feedViewUpdate_CmtAnswer(); <%-- 댓글-답글 수정 function --%>
								
								$("input[name='cmtans_no']").val("");
								$("input[name='cmtans_content']").val("");
								
							<%-- 수정할 내용이 댓글일 경우 --%>
							} else {
								$("input[name='cmt_no']").val(feedview_comments_box.attr("id"));
								$("input[name='cmt_content']").val(feedview_comments_txt.val());
								
								feedViewUpdate_Comments(); <%-- 댓글 수정 function --%>
								
								$("input[name='cmt_no']").val("");
								$("input[name='cmt_content']").val("");
							}
							
							feedview_comments_txt.siblings("#cmt_content_save").text(feedview_comments_txt.val()); <%-- 수정 후 내용 save. --%>
							feedview_comments_txt.prop("readonly", true); <%-- 등록된 댓글&댓글-답글 내용 입력란 readonly=false => true. --%>
							
							<%-- 등록된 댓글 내용 박스 및 수정 버튼 CSS효과 비활성화. --%>
							feedview_comments_txt.height(0).height(feedview_comments_txt.prop("scrollHeight")); <%-- 등록된 댓글 내용 자동 resize. --%>
							feedview_comments_txtbox.css("box-shadow", "");
							$(this).css("border-radius", "");
							$(this).css("background-color", "");
						}
						
					<%-- 등록된 댓글 내용 취소 버튼 클릭 시. --%>
					} else {
						feedview_comments_txt.val(feedview_comments_txt.siblings("#cmt_content_save").text()); <%-- 수정 전 내용 불러오기. --%>
						
						feedview_comments_txt.prop("readonly", true); <%-- 등록된 댓글&댓글-답글 내용 입력란 readonly=false => true. --%>
						
						<%-- 등록된 댓글 내용 박스 및 수정 버튼 CSS효과 비활성화. --%>
						feedview_comments_txt.height(0).height(feedview_comments_txt.prop("scrollHeight")); <%-- 등록된 댓글 내용 자동 resize. --%>
						feedview_comments_txtbox.css("box-shadow", "");
						$(this).css("border-radius", "");
						$(this).css("background-color", "");
					}
				});
		}
	});
	
	<%-- 등록된 댓글&댓글-답글&댓글-답글 내용_삭제 버튼 클릭 시 이벤트 --%>
	$("#feedview_right_footer").on("click", ".feedview_comments_deletebtn", function() {
		var feedview_comments_box = $(this).parent(".feedview_comments_imgbtnbox").parent(".feedview_comments_box");
		
		swal({
			title: "정말 삭제하시겠어요?",
			text: "댓글을 삭제한 후에는 이 작업을 실행 취소할 수 없습니다.",
			icon: "warning",
			buttons: ["취소", "삭제"],
			dangerMode: true,
			})
			.then((result) => {
				if (result) {
					<%-- 삭제할 내용이 댓글-답글일 경우 --%>
					if(feedview_comments_box.attr("id").toLowerCase().indexOf("cmtans") >= 0) {
						$("input[name='cmtans_no']").val(feedview_comments_box.attr("id"));
						
						feedViewDelete_CmtAnswer(); <%-- 댓글-답글 삭제 function. --%>
						
						$("input[name='cmtans_no']").val("");
						
					<%-- 삭제할 내용이 댓글일 경우 --%>
					} else {
						$("input[name='cmt_no']").val(feedview_comments_box.attr("id"));
						
						feedViewDelete_Comments(); <%-- 댓글 삭제 function. --%>
						
						$("input[name='cmt_no']").val("");
					}
					
				} else {
					swal("삭제 취소되었습니다.");
				}
			});
	});
});

<%-- 팝업창_background function --%>
function background_popup() {
	$("#feedview_back_index").css("display", "none");
	$("#feedview_rpanswer_popup").css("display", "none");
	$("#feedview_content_update").empty();
}
</script>

</head>
<body>
<%---------------------------------- form data setting ----------------------------------%>
	<form action="#" id="feedview_form" method="post">
		<%-- 일반 회원 --%>
		<input type="hidden" name="session_bm_no" value="${session_bm_no}" /> <%-- sessionScope.bm_no 회원번호 --%>
		
		<%-- 메인 게시글 --%>
		<input type="hidden" name="feed_no" value="${feed_no}" /> <%-- 메인 게시글_번호 --%>
		<input type="hidden" name="feed_type_cd" value="" />      <%-- 메인 게시글_메뉴 구분 코드--%>
		<input type="hidden" name="bm_no" value="" />             <%-- 메인 게시글_작성자(회원번호) --%>
		<input type="hidden" name="feed_title" value="" />        <%-- 메인 게시글_제목 --%>
		<input type="hidden" name="feed_content" value="" />      <%-- 메인 게시글_내용 --%>
		
		<%-- 좋아요 --%>
		<input type="hidden" name="fl_no" value=""/> <%-- 좋아요 번호 --%>
		
		<%-- 신고, 신고-답변 --%>
		<input type="hidden" name="rp_bno" value="${feed_no}" /> <%-- 신고한 글(메인 게시글_번호) --%>
		<input type="hidden" name="rpans_content" value="" />    <%-- 신고-답변 내용 --%>
		
		<%-- 이미지 --%>
		<input type="hidden" name="fi_no" value=""/>   <%-- 이미지 번호 --%>
		<input type="hidden" name="fi_file" value=""/> <%-- 이미지 파일명 --%>
		
		<%-- 팔로우 --%>
		<input type="hidden" name="follow_no" value="" />                 <%-- 팔로우 번호 --%>
		<input type="hidden" name="reg_bm_no" value="${session_bm_no}" /> <%-- 팔로워 회원번호 --%>
		
		<%-- 댓글 --%>
		<input type="hidden" name="cmt_no" value="" />                    <%-- 댓글 번호 --%>
		<input type="hidden" name="cmt_feed_no" value="${feed_no}" />     <%-- 메인 게시글_번호 --%>
		<input type="hidden" name="cmt_bm_no" value="${session_bm_no}" /> <%-- 댓글 작성자(회원번호) --%>
		<input type="hidden" name="cmt_content" value="" />               <%-- 댓글 내용 --%>
		
		<%-- 댓글-답글 --%>
		<input type="hidden" name="cmtans_no" value="" />                    <%-- 댓글-답글 번호 --%>
		<input type="hidden" name="cmtans_cmt_no" value="" />                <%-- 댓글 번호 --%>
		<input type="hidden" name="cmtans_bm_no" value="${session_bm_no}" /> <%-- 댓글-답글 작성자(회원번호) --%>
		<input type="hidden" name="cmtans_content" value="" />               <%-- 댓글-답글 내용 --%>
	</form>
	
	<c:import url="/header"></c:import>
	
	<div class="feedview_wrap">
<%---------------------------------- 메인 게시글 상세보기_컨테이너 ----------------------------------%>		
		<div class="feedview_container">
<%---------------------------------- 메인 게시글 상세보기_왼쪽(이미지)_전체 컨테이너 ----------------------------------%>
			<div class="feedview_left_container">
				<%-- 이미지 컨테이너 --%>
				<div id="feedview_imgs_container"></div>
				
				<div id="feedview_img_deletebtn_box"></div>
				<%-- 이미지 삭제 버튼 --%>
				<div id="feedview_img_deletebtn" class="feedview_img_deletebtn" style="display: none;">
					<img src="resources/images/etc/feedview_delete.png" width="20px" />
				</div>
			</div>
			
<%---------------------------------- 메인 게시글 상세보기_오른쪽(내용)_전체 컨테이너 ----------------------------------%>
			<div class="feedview_right_container">
				<%-- 메인 게시글 상세보기_오른쪽(내용)_헤더 --%>
				<div class="feedview_right_header">
					<c:if test="${!empty session_bm_id}">
						<%-- 글 신고_버튼 --%>
						<div id="feedview_report_btn" class="feedview_header_optbox" style="margin-left: 0px;">
							<img alt="글 신고" src="resources/images/etc/feedview_opt.png" width="20px" />
						</div>
					</c:if>
					
					<%-- 글 수정_버튼, hide/show 여부는 feedViewSelect_Content function에서 결정. --%>
					<div id="feedview_update_btn" class="feedview_header_optbox">
						<img alt="글 수정" src="resources/images/etc/feedview_update.png" width="20px" />
					</div>
					
					<c:if test="${!empty session_bm_id}">
						<%-- 글 좋아요_버튼 --%>
						<div id="feedview_like_btn" class="feedview_header_optbox">
							<img id="feedview_like_img" alt="offlike" src="resources/images/etc/feedview_offlike.png" width="20px" />
						</div>
					</c:if>
				</div>
				
				<%-- 메인 게시글 상세보기_오른쪽(내용)_메인 게시글 제목 --%>
				<h1 id="feedview_right_title" class="feedview_main_content" style="margin: 0px; padding-top: 20px;"></h1>
				<%-- 메인 게시글 상세보기_오른쪽(내용)_메인 게시글 내용 --%>
				<div id="feedview_right_content" class="feedview_main_content" style="padding-bottom: 20px; font-size: 14px;"></div>
				
				<%-- 메인 게시글 상세보기_오른쪽(내용)_프로필 정보 및 팔로워 수&팔로우 상태 박스 --%>
				<div id="feedview_profile" style="text-align: left; padding: 15px 0px;">
					<%-- 프로필 사진_박스 --%>
					<c:if test="${empty session_bm_img}">
						<div class="feedview_photo" style="margin: 10px 5px 10px 0px; height: 32px; width: 32px; cursor: pointer;">
							<img class="feedview_img" src="resources/images/etc/non_profile.png" style="border-radius: 50%;"/>
						</div>
					</c:if>
					
					<c:if test="${not empty session_bm_img}">
						<div class="feedview_photo" style="margin: 10px 5px 10px 0px; height: 32px; width: 32px; cursor: pointer;">
							<img class="feedview_img" src="${session_bm_img}" style="border-radius: 50%;"/>
						</div>
					</c:if>
					
					<%-- 프로필 정보 박스 --%>
					<div style="display: inline-block; width: 300px; vertical-align: top;">
						<%-- 프로필 닉네임 --%>
						<div class="feedview_nick_txt feedview_profile_info" style="padding-top: 6px;"></div>
						<%-- 팔로워 수 --%>
						<div id="feedview_follower_cnt" class="feedview_profile_info" style="padding-bottom: 6px; font-weight: 400;"></div>
					</div>
					
					<%-- 팔로우 버튼 --%>
					<div id="feedview_follow_btn" class="feedview_follow_btn" style="display: none;"></div>
				</div>
				
				<%-- 사진 추가 버튼 --%>
				<div id="feedview_addphoto_box"></div>
				
				<%-- 메인 게시글 상세보기_오른쪽(내용)_댓글 수 --%>
				<div id="feedview_comments_cnt" class="feedview_comments_cnt">댓글</div>
				
				<%-- 메인 게시글 상세보기_오른쪽(내용)_댓글 등록_박스 --%>
				<c:if test="${!empty session_bm_id}">
					<div id="feedview_comments_insertbox" style="text-align: left; padding-bottom: 20px;">
						<%-- 프로필 사진 --%>
						<c:if test="${empty session_bm_img}">
							<div class="feedview_photo" style="vertical-align: top; margin-right: 10px; width: 48px; height: 48px; cursor: pointer;">
								<img class="feedview_img" src="resources/images/etc/non_profile.png" style="border-radius: 50%;"/>
							</div>
						</c:if>
						<c:if test="${!empty session_bm_img}">
							<img src="${session_bm_img}" style="vertical-align: top; border-radius: 50%; margin-right: 10px; width: 48px; height: 48px;" />
						</c:if>
						
						<%-- 댓글 입력란 --%>
						<textarea id="feedview_comments_write" class="feedview_comments_write" spellcheck="false" placeholder="300자 이내"></textarea>
						
						<%-- 댓글 취소&등록_버튼박스 --%>
						<div id="feedview_comments_btnbox" class="feedview_comments_btnbox" style="display: none; text-align: right; padding-top: 16px;">
							<%-- 댓글 취소_버튼 --%>
							<div id="feedview_comments_canclebtn" class="feedview_comments_btn">취소</div>
							<%-- 댓글 등록_버튼 --%>
							<div id="feedview_comments_insertbtn" class="feedview_comments_btn">등록</div>
						</div>
					</div>
				</c:if>
				
				<%-- 메인 게시글 상세보기_오른쪽(내용)_댓글&댓글-답글_박스 --%>
				<div id="feedview_right_footer"></div>
			</div>
		</div>
		
<%---------------------------------- 메인 게시글 상세보기_글 내용 수정/삭제 POPUP ----------------------------------%>
		<div id="feedview_content_update"></div>
	</div>
	
<%---------------------------------- 메인 게시글 상세보기_신고 popup ----------------------------------%>
	<%-- 메인 게시글 상세보기_신고-답변_박스 --%>
	<div id="feedview_rpanswer_popup" class="feedview_rpanswer_popup" style="display: none;">
		<h1 style="text-align: center; padding: 32px 0px; margin: 0px; font-size: 28px; color: #111111;">신고</h1>
		
		<%-- 신고-답변_내용박스 --%>
		<div class="feedview_rpanswer_body">
			<c:forEach var="feedView_select_rpanswer" items="${feedView_select_rpanswer}">
				<%-- 신고-답변_내용 라인 --%>
				<div class="feedview_rpanswer_line">
					<input type="radio" id="${feedView_select_rpanswer.rpans_no}" name="feedview_rpanswer_radio" style="margin: 10px; width: 24px; height: 24px;" />
					
					<div style="display: inline-block;">
						<%-- 신고-답변 제목 --%>
						<div class="feedview_rpanswer_content" style="font-size: 16px; font-weight: 700;">${feedView_select_rpanswer.rpans_title}</div>
						<%-- 신고-답변 내용 --%>
						<div id="feedview_rpanswer_content" class="feedview_rpanswer_content">${feedView_select_rpanswer.rpans_content}</div>
					</div>
				</div>
			</c:forEach>
		</div>
		
		<div style="text-align: right; padding: 32px;">
			<%-- 신고-답변_취소 버튼 --%>
			<div id="feedview_rpanswer_cancelbtn" class="feedview_rpanswer_btn">취소</div>
			<%-- 신고-답변_신고 버튼 --%>
			<div id="feedview_rpanswer_reportbtn" class="feedview_rpanswer_btn">접수</div>
		</div>
	</div>
	
	<%---------------------------------- z-index background ----------------------------------%>
	<div id="feedview_back_index" class="feedview_back_index" style="display: none;"></div>
</body>
</html>