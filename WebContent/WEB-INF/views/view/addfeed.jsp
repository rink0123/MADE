<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css"
	href="resources/css/feedview/addfeed.css" />
<%-- 메인 게시글 추가_CSS --%>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-latest.min.js"></script>
<%-- jQuery CDN --%>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<%-- sweetalert 알림창 CDN --%>
<script type="text/javascript" src="https://sdk.amazonaws.com/js/aws-sdk-2.806.0.min.js"></script> <%-- S3 CDN --%>
<%-- S3 CDN --%>
<script type="text/javascript" src="resources/js/feedview/feedS3File.js"></script>
<%-- S3 파일 업로드 --%>
<script type="text/javascript"
	src="resources/js/feedview/feedViewSelect.js"></script>
<%-- 메인 게시글 상세보기_조회 --%>
<script type="text/javascript"
	src="resources/js/feedview/feedViewInsert.js"></script>
<%-- 메인 게시글 상세보기_삽입 --%>

<script type="text/javascript">
$(document).ready(function() {
	<%-- 프로필 사진 혹은 본인 닉네임 클릭 시 프로필 정보 페이지로 이동 --%>
	$("#addfeed_photo, #addfeed_nick").click(function() {
		$("#addfeed_form").attr("action", "profile");
		$("#addfeed_form").submit();
	});
	
	<%-- 섹션 선택 시 option value값 form에 넣기 --%>
	$("#addfeed_feedcat").change(function() {
		var option = $("#addfeed_feedcat option:selected").attr("value");
		
		$("#addfeed_form input[name='feed_type_cd']").val(option);
	});
	
	<%-- 제목 입력란 입력 시 자동 높이 조절 --%>
	$("#addfeed_title_txt").on("keydown keyup", function(event) {
		$(this).height(1).height($(this).prop("scrollHeight"));
	
		<%-- 제목 입력란 작성 중 엔터키 클릭 시 적용 불가 --%>
		if(event.which == 13) {
			event.preventDefault();
		}
	});
	
	<%-- 내용 입력란 입력 시 자동 높이 조절 --%>
	$("#addfeed_content_txt").on("keydown keyup", function(event) {
		$(this).height(1).height($(this).prop("scrollHeight"));
	
		<%-- 내용 입력란 작성 중 엔터키 클릭 시 적용 불가 --%>
		if(event.which == 13) {
			event.preventDefault();
		}
	});
	
<%---------------------------------- 사진 추가 ----------------------------------%>
	<%-- 사진 추가 클릭 시 window 파일 선택 창 활성화 --%>
	$("#addfeed_addphoto").click(function() {
		$("#addfeed_fileupload").click();
	});
	
	<%-- 파일 선택 시 미리보기 --%>
	$("#addfeed_fileupload").change(function() {
		var file = $(this)[0].files[0];
		
		<%--  파일 타입이 image/jpeg이 아닐 경우 파일업로드 불가 --%>
		if(file.type != "image/jpeg") {
			swal("사진을 올릴 수 없습니다.", ".jfif, .pjpeg, .jpeg, .pjp, .jpg인 파일 확장자만 허용됩니다.", "warning");
		
		<%--  파일 용량이 20MB 초과일 경우 --%>
		} else if(file.size > (20 * 1024 * 1024)) {
			swal("사진을 올릴 수 없습니다.", "20MB 초과인 사진은 올릴 수 없습니다.", "warning");
			
		<%-- 모든 조건 만족 시 이미지 파일 미리보기 가능 --%>
		} else {
			if($(this)[0].files && $(this)[0].files[0]) {
				var reader = new FileReader(); <%-- 파일을 읽기 위한 FileReader객체 생성 --%>
				
				<%-- 파일 읽어들이기를 성공했을때 호출되는 이벤트 핸들러 --%>
				reader.onload = function(e) {
					<%-- 이미지 Tag의 SRC속성에 읽어들인 File내용을 지정 --%>
					$('#addfeed_addphoto').attr("src", e.target.result);
				}
				<%-- File내용을 읽어 dataURL형식의 문자열로 저장 --%>
				reader.readAsDataURL($(this)[0].files[0]);
			}
		}
	});
	
<%---------------------------------- 글 저장 ----------------------------------%>
	<%-- 처장 클릭 시 이벤트 --%>
	$("#addfeed_insertbtn").click(function() {
		var addfeed_title_txt = $("#addfeed_title_txt");
		var addfeed_content_txt = $("#addfeed_content_txt");
		var feed_type_cd = $("#addfeed_form input[name='feed_type_cd']");
		
		<%-- 제목이 빈 칸일 경우 --%>
		if($.trim(addfeed_title_txt.val()) == "") {
			addfeed_title_txt.attr("placeholder", "제목을 입력해주세요.");
			$("#addfeed_title_line").css("border-color", "rgba(0, 132, 255, 0.5)");
			addfeed_title_txt.focus();
			
		} else if(feed_type_cd.val() == "") {
			swal("오른쪽 상단에 섹션을 선택해주세요.", "", "warning");
			
		} else if($("#addfeed_fileupload").val() == "") {
			swal("사진을 올려주세요.", "", "warning");
			
		} else {
			$("#addfeed_form input[name='feed_title']").val(addfeed_title_txt.val());
			$("#addfeed_form input[name='feed_content']").val(addfeed_content_txt.val());
			
			addfeed_title_txt.attr("placeholder", "제목 추가, 100자이내");
			$("#addfeed_title_line").css("border-color", "rgba(142, 142, 142, 0.5);");
			
			addfeedPhoto($("#addfeed_fileupload").attr("id"), "mainimage"); <%-- S3 파일업로드(단일) function --%>
			
			swal({
			    title: '',
			    text: '저장하는 중입니다.',
			    icon: 'info',
			    timer: 60000, /* 최대 1분으로 설정 */
			    buttons: false,
			    closeModal: false,
			    closeOnClickOutside: false,
			    closeOnEsc: false,
			});
		}
	});
});
</script>

<title>MADE | AddFeed</title>
</head>
<body>
	<%---------------------------------- form-data ----------------------------------%>
	<form action="#" id="addfeed_form" method="post">
		<%-- 메인 게시글 --%>
		<input type="hidden" name="bm_no" value="${session_bm_no}" />
		<input type="hidden" name="feed_type_cd" value="" />
		<input type="hidden" name="feed_title" value="" />
		<input type="hidden" name="feed_content" value="" />

		<%-- 메인 게시글 이미지 --%>
		<input type="hidden" name="fi_file" value="" />
		<input type="hidden" name="feed_no" value="" />
	</form>
	<input type="file" id="addfeed_fileupload" size="20971520" accept="image/jpeg" style="display: none;">

	<c:import url="/header"></c:import>
	
	<div class="addfeed_wrap">
		<%---------------------------------- 메인 게시글 추가 ----------------------------------%>
		<div class="addfeed_container">
			<div style="text-align: right; margin-bottom: 20px;">
				<select id="addfeed_feedcat" class="addfeed_feedcat">
					<option value="">선택</option>
					<option value="FEEDCAT0001">의류</option>
					<option value="FEEDCAT0002">잡화</option>
					<option value="FEEDCAT0003">코스메틱</option>
					<option value="FEEDCAT0004">액세서리</option>
				</select>
				<div id="addfeed_insertbtn" class="addfeed_insertbtn">저장</div>
			</div>

			<%---------------------------------- 메인 게시글 추가_왼쪽(사진) ----------------------------------%>
			<div class="addfeed_leftcontainer">
				<img id="addfeed_addphoto" src="resources/images/etc/feedview_uproad.png" style="border-radius: 8px; width: 100%; cursor: pointer;" />
			</div>

			<%---------------------------------- 메인 게시글 추가_오른쪽(내용) ----------------------------------%>
			<div class="addfeed_rightcontainer">
				<%-- 제목 입력란 --%>
				<div style="margin-top: 32px;">
					<textarea id="addfeed_title_txt" class="addfeed_txt" spellcheck="false" maxlength="100" placeholder="제목 추가, 100자이내" style="font-size: 36px; font-weight: 700; height: 36px;"></textarea>
				</div>

				<hr id="addfeed_title_line" style="margin-top: 8px; border-color: rgba(142, 142, 142, 0.5);" />
				<div style="margin-top: 4px; min-height: 15px;"></div>

				<div style="display: flex; align-items: center; margin-top: 16px;">
					<%-- 프로필 사진 --%>
					<div id="addfeed_photo" class="addfeed_photo" style="padding: 0px 4px; width: 32px; height: 32px; cursor: pointer;">
						<%-- 세션 프로필 사진이 없을 경우 non_profile.png --%>
						<c:if test="${empty session_bm_img}">
							<img class="addfeed_img" src="resources/images/etc/non_profile.png" style="border-radius: 50%;" />
						</c:if>

						<%-- 세션 프로필 사진이 있을 경우 본인 프로필 사진 --%>
						<c:if test="${not empty session_bm_img}">
							<img class="addfeed_img" src="${session_bm_img}" style="border-radius: 50%;" />
						</c:if>
					</div>

					<%-- 본인 닉네임 --%>
					<div id="addfeed_nick" style="padding: 0px 4px; margin-left: 4px; font-size: 14px; font-weight: 700; cursor: pointer;">${session_bm_nick}</div>
				</div>

				<%-- 내용 입력란 --%>
				<div style="margin-top: 32px;">
					<textarea id="addfeed_content_txt" class="addfeed_txt" spellcheck="false" maxlength="300" placeholder="내용 추가, 300자이내" style="font-size: 16px; font-weight: 400; height: 16px;"></textarea>
				</div>

				<hr style="margin-top: 8px; border-color: rgba(142, 142, 142, 0.5);" />
				<div style="margin-top: 4px; min-height: 15px;"></div>
			</div>
		</div>
	</div>
</body>
</html>