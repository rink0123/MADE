<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet" type="text/css" href="resources/css/profile/profile_edit.css"/>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script> <%-- sweetalert 알림창 CDN --%>
<script type="text/javascript" src="https://code.jquery.com/jquery-latest.min.js"></script> <%-- jQuery CDN --%>
<script type="text/javascript" src="https://sdk.amazonaws.com/js/aws-sdk-2.806.0.min.js"></script> <%-- S3 CDN --%>
<script type="text/javascript" src="resources/js/profile/edit_chk.js"></script>
<script type="text/javascript" src="resources/js/profile/editS3File.js"></script>
<script type="text/javascript" src="resources/js/profile/profileSelect.js"></script>
<script type="text/javascript" src="resources/js/profile/profileUpdate.js"></script>
<script type="text/javascript" src="resources/js/profile/profileDelete.js"></script>

<title>MADE | Profile</title>

<script type="text/javascript">
$(document).ready(function() {
<%-- ======================================== 프로필 편집_메뉴 ======================================== --%>
	<%-- 프로필 편집_메뉴 클릭 시 해당 내용 활성화 --%>
	$("#profile_menu").on("click", "div", function() {
		$(this).siblings().css("border-left-color", "transparent");
		$(this).siblings().css("font-weight", "400");
		$(this).css("border-left-color", "#111111");
		
		if($(this).attr("id") == "menu_profile_edit" && $(this).css("font-weight") == "400") {
			editSelect_profileEdit(); <%-- 프로필 편집_프로필 편집_조회 function --%>
			
			$(this).css("font-weight", "600");
			
		} else if($(this).attr("id") == "menu_password_update" && $(this).css("font-weight") == "400") {
			editSelect_PwUpdate(); <%-- 프로필 편집_비밀번호 변경_조회 function --%>
			
			$(this).css("font-weight", "600");
			
		} else if($(this).attr("id") == "menu_member_withdrawal" && $(this).css("font-weight") == "400") {
			editSelect_MemWithdrawal(); <%-- 프로필 편집_계정 삭제_조회 function --%>
			
			$(this).css("font-weight", "600");
			
		} else if($(this).attr("id") == "menu_complain" && $(this).css("font-weight") == "400") {
			$("#profile_menu_content").empty();
			$("#edit_form input[name='cp_curpage']").val(1);
			
			editSelectList_Complain(); <%-- 프로필 편집_1:1문의 목록_조회 function --%>
			
			$(this).css("font-weight", "600");
		}
	});
	$("#menu_profile_edit").click();
	
<%-- ======================================== 프로필 편집 ======================================== --%>
	<%-- 이름_유효성 검사 --%>
	$("#profile_menu_content").on("focus", "#bm_name_txt", function() {
		$(".profile_menu_content").on("blur", "#bm_name_txt", function() {
			editName_Chk($(this)); <%-- 이름_유효성 검사 function --%>
		});
	});
	
	<%-- 사용자 이름_유효성 검사 --%>
	$("#profile_menu_content").on("focus", "#bm_nick_txt", function() {
		$("#profile_menu_content").on("blur", "#bm_nick_txt", function() {
			editNick_Chk($(this)); <%-- 사용자 이름_유효성 검사 function --%>
		});
	});
	
	<%-- 이메일_유효성 검사 --%>
	$("#profile_menu_content").on("focus", "#bm_email_txt", function() {
		$("#profile_menu_content").on("blur", "#bm_email_txt", function() {
			editEmail_Chk($(this)); <%-- 이메일_유효성 검사 function --%>
		});
	});
	
	<%-- 연락처_유효성 검사 --%>
	$("#profile_menu_content").on("focus", "#bm_phone_txt", function() {
		$("#profile_menu_content").on("blur", "#bm_phone_txt", function() {
			editPhone_Chk($(this)); <%-- 연락처_유효성 검사 function --%>
		});
	});
	
	<%-- 수정 버튼 클릭 시 일반회원 정보 수정 --%>
	$("#profile_menu_content").on("click", "#edit_profile_updatebtn", function() {
		var bm_name_txt = $("#profile_menu_content #bm_name_txt");
		var bm_nick_txt = $("#profile_menu_content #bm_nick_txt");
		var bm_email_txt = $("#profile_menu_content #bm_email_txt");
		var bm_phone_txt = $("#profile_menu_content #bm_phone_txt");
		
		<%-- 이름 입력란이 빈 칸이면 --%>
		if($.trim(bm_name_txt.val()) == "") {
			bm_name_txt.focus();
			
		<%-- 이메일 입력란이 빈 칸이면 --%>
		} else if($.trim(bm_email_txt.val()) == "") {
			bm_email_txt.focus();
			
		<%-- 닉네임 입력란이 빈 칸이면 --%>
		} else if($.trim(bm_nick_txt.val()) == "") {
			bm_nick_txt.focus();
			
		<%-- 연락처 입력란이 빈 칸이면 --%>
		} else if($.trim(bm_phone_txt.val()) == "") {
			bm_phone_txt.focus();
		
		<%-- 모든 조건 만족 시 --%>
		} else {
			editUpdate_ProfileEdit(bm_name_txt, bm_nick_txt, bm_email_txt, bm_phone_txt); <%-- 프로필 편집_프로필 편집_수정 function --%>
		}
	});
	
<%-- ======================================== 프로필 사진_수정 ======================================== --%>
	<%-- 프로필 사진 바꾸기 버튼 클릭 시 이벤트 --%>
	$("#profile_menu_content").on("click", "#profile_edit_imgbtn, #profile_photo", function() {
		var profile_img = $("#profile_menu_content #profile_img").attr("src");
		
		<%-- 프로필 사진이 있는 경우 프로필 사진 바꾸기 popup 활성화 --%>
		if(profile_img != "resources/images/etc/non_profile.png") {
			editFile_popup(); <%-- 프로필 사진 바꾸기 popup --%>
			$("#profileedit_overlay").show();
			
		<%-- 프로필 사진이 없는 경우 --%>
		} else {
			$("#edit_img_fileupload").click();
		}
	});
	
	<%-- 파일 업로드 value값이 바뀌었을 시 이벤트 --%>
	$("#edit_img_fileupload").change(function() {
		var file = $(this)[0].files[0];
		
		<%--  파일 타입이 image/jpeg이 아닐 경우 파일업로드 불가 --%>
		if(file.type != "image/jpeg") {
			swal("사진을 올릴 수 없습니다.", ".jfif, .pjpeg, .jpeg, .pjp, .jpg인 파일 확장자만 허용됩니다.", "warning");
		
		<%--  파일 용량이 20MB 초과일 경우 --%>
		} else if(file.size > (20 * 1024 * 1024)) {
			swal("사진을 올릴 수 없습니다.", "20MB 초과인 사진은 올릴 수 없습니다.", "warning");
		
		<%-- 모든 조건 만족 시 파일업로드 가능 --%>
		} else {
			addPhoto($(this).attr("id"), "profileimage"); <%-- S3 파일업로드(단일) function --%>
			
			swal({
			    title: '',
			    text: '프로필 사진을 올리는 중입니다.',
			    icon: 'info',
			    timer: 60000, /* 최대 1분으로 설정 */
			    buttons: false,
			    closeModal: false,
			    closeOnClickOutside: false,
			    closeOnEsc: false,
			});
		}
	});
	
	<%-- 프로필 사진 바꾸기 popup_삭제 버튼 클릭 시 이벤트 --%>
	$("#edit_file_popup").on("click", "#edit_fileupload_deletebtn", function() {
		var fileFullName = decodeURIComponent($("#profile_menu_content #profile_img").attr("src"));
		var fileName = fileFullName.substring(fileFullName.indexOf("profileimage"), fileFullName.length); <%-- profileimage/파일명.파일확장자 --%>
		
		<%--
			S3 파일업로드 및 삭제 시 키값을 기준으로 작업하기에
			데이터를 decode 시 +(공백을 치환한 것)를 다시 공백으로 치환해야한다. 
	 	--%>
		var fileNameReplace = fileName.replace("+", " ");
		
		deletePhoto(fileNameReplace); <%-- S3 파일 삭제(단일) function --%>
	});
	
	<%-- 프로필 사진 바꾸기 popup_취소 버튼 클릭 시 팝업창 비활성화 --%>
	$("#edit_file_popup").on("click", "#edit_fileupload_canclebtn", function() {
		$("#edit_file_popup").empty();
		$("#profileedit_overlay").hide();
	});

<%-- ======================================== 비밀번호 변경 ======================================== --%>
	<%-- 모든 비밀번호 입력란 KeyBoard 이벤트 --%>
	$("#profile_menu_content").on("keydown", "#bm_pw_txt, #new_bm_pw_txt, #chk_bm_pw_txt", function(event) {
		<%-- Ctrl + C, V 막기 --%>
		if (event.ctrlKey == true && (event.which == 118 || event.which == 86)) {
			event.preventDefault();
		}
	});
	
	<%-- 비밀번호 변경 버튼 클릭 시 비밀번호 수정 --%>
	$("#profile_menu_content").on("click", "#edit_pw_updatebtn", function() {
		var bm_pw_txt = $("#profile_menu_content #bm_pw_txt");
		var new_bm_pw_txt = $("#profile_menu_content #new_bm_pw_txt");
		var chk_bm_pw_txt = $("#profile_menu_content #chk_bm_pw_txt");
		
		var newpw_result = 0;
		var chkpw_result = 0;
		var updatepw_result = 0;
		
		<%-- 이전 비밀번호_유효성 검사 --%>
		if(true) {
			newpw_result = editPw_Chk(bm_pw_txt); <%-- 비밀번호_유효성 검사 function --%>
		}
		
		<%-- 새 비밀번호_유효성 검사 --%>
		if(newpw_result != 0) {
			chkpw_result = editPw_Chk(new_bm_pw_txt); <%-- 비밀번호_유효성 검사 function --%>
			
			if(chkpw_result != 0) {
				chkpw_result = editNewPw_Chk(bm_pw_txt, new_bm_pw_txt); <%-- 새 비밀번호_유효성 검사 function --%>
			}
		}
		
		<%-- 새 비밀번호 확인_유효성 검사 --%>
		if(chkpw_result != 0) {
			updatepw_result = editPw_Chk(chk_bm_pw_txt); <%-- 비밀번호_유효성 검사 function --%>
			
			if(updatepw_result != 0) {
				updatepw_result = editChkPw_Chk(new_bm_pw_txt, chk_bm_pw_txt); <%-- 새 비밀번호 확인_유효성 검사 function --%>
			}
		}
		
		<%-- 모든 조건 만족 시 --%>
		if(updatepw_result != 0) {
			editUpdate_PwUpdate(bm_pw_txt, chk_bm_pw_txt); <%-- 프로필 편집_비밀번호 변경_수정 function --%>
		}
	});

<%-- ======================================== 계정 삭제 ======================================== --%>
	<%-- 계정 삭제 사유_option 선택 시 이벤트 --%>
	$("#profile_menu_content").on("change", "#profile_edit_opt", function() {
		var option_val = $("#" + $(this).attr("id") + " option:selected").val();
		var withdrawal_progress = $("#profile_menu_content #withdrawal_progress");
		
		<%-- 선택한 계정 삭제 사유_option 값이 hide가 아닐 경우 --%>
		if(option_val != "hide") {
			withdrawal_progress.show(); <%-- 계정 삭제 진행 --%>
			
		<%-- 선택한 계정 삭제 사유_option 값이 hide일 경우 --%>
		} else {
			withdrawal_progress.hide(); <%-- 계정 삭제 중단 --%>
		}
	});
	
	<%-- 내 계정 삭제 버튼 클릭 시 비밀번호 수정 --%>
	$("#profile_menu_content").on("click", "#edit_pw_deletebtn", function() {
		var bm_pw_txt = $("#profile_menu_content #bm_pw_txt");
		var result = editPw_Chk(bm_pw_txt); <%-- 비밀번호_유효성 검사 function --%>
		
		<%-- 비밀번호_유효성 검사 --%>
		if(result != 0) {
			editDelete_PwDelete(bm_pw_txt); <%-- 프로필 편집_계정 삭제 function --%>
		}
	});
	
<%-- ======================================== 1:1문의 ======================================== --%>
	<%-- 1:1문의 게시글 클릭 시 이벤트 --%>
	$("#profile_menu_content").on("click", ".edit_complain_line > .edit_complain_tr", function() {
		var edit_complain_qna = $(this).siblings("#edit_complain_qna");

		<%-- 1:1문의 내용이 hide상태일 경우 show로 변경 --%>
		if(edit_complain_qna.css("display") != "none") {
			edit_complain_qna.hide();
			
		<%-- 1:1문의 내용이 show상태일 경우 hide로 변경 --%>
		} else {
			edit_complain_qna.show();
		}
	});
	
	<%-- 1:1문의 수정 버튼 클릭 시 1:1문의_상세 팝업창 활성화 --%>
	$("#profile_menu_content").on("click", ".edit_complain_updatebtn", function() {
		$("#edit_form input[name='cp_no']").val($(this).attr("id"));
		
		editSelectView_Complain() <%-- 프로필 편집_1:1문의 상세_조회 function --%>
	});
	
	<%-- 1:1문의_상세 팝업창 삭제 버튼 클릭 시 이벤트 --%>
	$("#profile_menu_content").on("click", ".edit_complain_deletebtn", function() {
		var edit_complain_deletebtn = 0;
		
		swal({
			title: "정말 삭제하시겠어요?",
			text: "삭제한 후에는 이 작업을 실행 취소할 수 없습니다.",
			icon: "warning",
			buttons: ["취소", "삭제"],
			dangerMode: true,
			})
			.then((result) => {
				if (result) {
					$("#edit_form input[name='cp_no']").val($(this).attr("id"));
					
					edit_complain_deletebtn = editDelete_Complain() <%-- 프로필 편집_1:1문의_삭제 function --%>
					
					<%-- 1:1문의 삭제 성공 시 --%>
					if(edit_complain_deletebtn > 0) {
						swal("정상 삭제되었습니다.", "", "success");
						
						$("#edit_form input[name='cp_no']").val("");
						$("#edit_form input[name='cp_title']").val("");
						$("#edit_form input[name='cp_content']").val("");
						
					<%-- 1:1문의 삭제 실패 시 --%>
					} else if(edit_complain_deletebtn == 0) {
						swal("삭제가 안됐습니다.", "", "warning");
						
						$("#edit_form input[name='cp_no']").val("");
						$("#edit_form input[name='cp_title']").val("");
						$("#edit_form input[name='cp_content']").val("");
						
					<%-- 1:1문의 서버 문제 시 --%>
					} else {
						swal("삭제에 문제가 발생하였습니다.", "", "error");
						
						$("#edit_form input[name='cp_no']").val("");
						$("#edit_form input[name='cp_title']").val("");
						$("#edit_form input[name='cp_content']").val("");
					}
				
				} else {
					swal("삭제 취소되었습니다.");
				}
			});
	});
	
	<%-- 1:1문의_상세 팝업창 입력란 작성 시 자동 리사이즈 --%>
	$("#edit_complainview_popup").on("keydown keyup", ".edit_complain_txt", function(event) {
		$(this).height(1).height($(this).prop("scrollHeight") - 17);
		
		<%-- 엔터키 클릭 시 적용불가. --%>
		if(event.which == 13) {
			event.preventDefault();
			$("#edit_complainview_popup .edit_complainview_updatebtn").click();
		}
	});
	
	<%-- 1:1문의_상세 팝업창 취소 버튼 클릭 시 1:1문의_상세 팝업창 비활성화 --%>
	$("#edit_complainview_popup").on("click", "#edit_complainview_cancelbtn", function() {
		$("#edit_complainview_popup").empty();
		
		$("#profileedit_overlay").hide();
		$("body").css("overflow", "auto");
	});
	
	<%-- 1:1문의_상세 팝업창 수정 버튼 클릭 시 이벤트 --%>
	$("#edit_complainview_popup").on("click", ".edit_complainview_updatebtn", function() {
		var edit_cp_title = $("#edit_cp_title");
		var edit_cp_content = $("#edit_cp_content");
		
		var result = 0;
		
		<%-- 1:1문의_상세 팝업창 제목이 빈 칸일 경우 --%>
		if($.trim(edit_cp_title.val()) == "") {
			edit_cp_title.val("");
			edit_cp_title.attr("placeholder", "제목을 입력해야 1:1문의 수정이 가능합니다");
			edit_cp_title.css("box-shadow", "0 0 0 4px rgba(0, 132, 255, 0.5)");
			edit_cp_title.focus();
			
		<%-- 1:1문의_상세 팝업창 내용이 빈 칸일 경우 --%>
		} else if($.trim(edit_cp_content.val()) == "") {
			edit_cp_content.val("");
			edit_cp_content.attr("placeholder", "내용을 입력해야 1:1문의 수정이 가능합니다");
			edit_cp_content.css("box-shadow", "0 0 0 4px rgba(0, 132, 255, 0.5)");
			edit_cp_content.focus();
			
		<%-- 모든 조건이 만족할 경우 --%>
		} else {
			$("#edit_form input[name='cp_no']").val($(this).attr("id"));
			$("#edit_form input[name='cp_title']").val(edit_cp_title.val());
			$("#edit_form input[name='cp_content']").val(edit_cp_content.val());
			
			result = editUpdate_Complain() <%-- 프로필 편집_1:1문의_수정 function --%>
			
			if(result == 1) {
				swal("1:1문의가 수정되었습니다.", "", "success");
				$("#edit_complainview_popup #edit_complainview_cancelbtn").click();
				
				$("#edit_form input[name='cp_no']").val("");
				$("#edit_form input[name='cp_title']").val("");
				$("#edit_form input[name='cp_content']").val("");
				
			} else if(result == 0) {
				swal("1:1문의 수정이 안됐습니다.", "", "warning");
				
				$("#edit_form input[name='cp_no']").val("");
				$("#edit_form input[name='cp_title']").val("");
				$("#edit_form input[name='cp_content']").val("");
				
			} else {
				swal("1:1문의 수정에 문제가 발생하였습니다.", "", "error");
				
				$("#edit_form input[name='cp_no']").val("");
				$("#edit_form input[name='cp_title']").val("");
				$("#edit_form input[name='cp_content']").val("");
			}
		}
	});
	
<%-- ======================================== 비밀번호 찾기 ======================================== --%>
	<%-- 비밀번호를 잊으셨나요? 클릭 시 비밀번호 찾기_팝업창 활성화 --%>
	$("#profile_menu_content").on("click", "#profile_edit_findpw", function() {
		basicFindPw_popup(); <%-- 비밀번호 찾기 popup --%>
		$("#profileedit_overlay").show();
	});
	
	<%-- 비밀번호 찾기 버튼 클릭 시 이벤트 --%>
	$("#edit_findpw_popup").on("click", "#findpw_btn", function() {
		var bm_id_txt = $("#bm_id_txt");
		var bm_email_txt = $("#bm_email_txt");
		
		basicFindPw_chk(bm_id_txt, bm_email_txt); <%-- 비밀번호 찾기 체크 function --%>
	});
	
	<%-- 이메일 입력란 KeyBoard 이벤트 --%>
	$("#edit_findpw_popup").on("keydown", "#bm_email_txt", function(event) {
		<%-- 비밀번호 입력란 엔터키 적용 --%>
		if(event.which == 13) {
			$("#findpw_btn").click();
		}
	});
	
	<%-- 취소 버튼 클릭 시 비밀번호 찾기_팝업창 비활성화 --%>
	$("#edit_findpw_popup").on("click", "#find_cancle_btn", function() {
		$("#edit_findpw_popup").empty();
		$("#profileedit_overlay").hide();
	});
	
	<%-- profileedit_overlay 클릭 시 프로필 편집_모든 팝업창 비활성화 --%>
	$("#profileedit_overlay").click(function() {
		$("#edit_file_popup").empty();
		$("#edit_findpw_popup").empty();
		$("#edit_complainview_popup").empty();
		$("#profileedit_overlay").hide();
	});
});
</script>
</head>
<body style="margin: 0px; padding: 0px;">
<%-- ======================================== form tag ======================================== --%>
	<form action="#" method="post" id="edit_form">
		<%-- 프로필 --%>
		<input type="hidden" name="bm_no" value="${session_bm_no}"/>
		<input type="hidden" name="bm_img" value=""/>
		<input type="hidden" name="bm_nick" value=""/>
		<input type="hidden" name="bm_name" value=""/>
		<input type="hidden" name="bm_email" value=""/>
		<input type="hidden" name="bm_phone" value=""/>
		<input type="hidden" name="bm_id" value=""/>
		<input type="hidden" name="bm_pw" value=""/>
		<input type="hidden" name="new_bm_pw" value=""/>
		
		<%-- 1:1문의 --%>
		<input type="hidden" name="cp_no" value=""/>
		<input type="hidden" name="cp_title" value=""/>
		<input type="hidden" name="cp_content" value=""/>
		<input type="hidden" name="cp_curpage" value="1"/>
	</form>
	
	<%--
		단일업로드
		사용자 지정 파일 : *.jfif, *.pjpeg, *.jpeg, *.pjp, *.jpg
		파일 사이즈 : 1024 * 1024 * 20
	--%>
	<input type="file" accept="image/jpeg" size="20971520" id="edit_img_fileupload" style="display: none;"/>
	
	<c:import url="/header"></c:import>
	
<%-- ======================================== 프로필 편집_메뉴 ======================================== --%>
	<div class="profile_edit">
		<%-- 프로필 편집_메뉴 --%>
		<div id="profile_menu" class="profile_menu">
			<div id="menu_profile_edit">프로필 편집</div>
			<div id="menu_password_update">비밀번호 변경</div>
			<div id="menu_member_withdrawal">계정 삭제</div>
			<div id="menu_complain">1:1문의</div>
		</div>
		
		<%-- 프로필 편집_내용 --%>
		<div id="profile_menu_content" class="profile_menu_content"></div>
	</div>
	
<%-- ======================================== 파일업로드_팝업창 ======================================== --%>
	<div id="edit_file_popup"></div>
	
<%-- ======================================== 비밀번호 찾기_팝업창 ======================================== --%>
	<div id="edit_findpw_popup"></div>
	
<%-- ======================================== 1:1문의 상세_팝업창 ======================================== --%>
	<div id="edit_complainview_popup"></div>
	
<%-- ======================================== profileedit_overlay ======================================== --%>
	<div id="profileedit_overlay" class="profileedit_overlay" style="display: none;"></div>
</body>
</html>