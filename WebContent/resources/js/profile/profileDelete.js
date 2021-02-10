/**
 * 프로필 정보_삭제 function
 * 프로필 편집_삭제 function
 */

// ========================================= 프로필 정보 =========================================
// 팔로우 삭제 function.
function profileDelete_Follow() {
	var profile_form = $("#profile_form").serialize();
	var result = 0;
	
	$.ajax({
		type : "post",
		url : "profiledelete_follow",
		dataType : "json",
		data : profile_form,
		async : false,
		cache : false,
		success : function(data) {
			// 팔로우 삭제 성공 시.
			if(data.profile_delete_follow == 1) {
				result = 1;
				
			// 팔로우 삭제 실패 시.
			} else if(data.profile_delete_follow == 0) {
				swal("팔로잉이 되지 않았습니다.", "오른쪽 하단에 ? 클릭하여 관리자에게 문의해주시기 바랍니다.", "warning");
			}
		},
		error : function(request, status, error) {
			swal("팔로잉에 문제가 발생하였습니다.", "오른쪽 하단에 ? 클릭하여 관리자에게 문의해주시기 바랍니다.", "warning");
		}
	});
	
	return result;
}

// ========================================= 프로필 편집 =========================================
// 프로필 편집_계정 삭제 function.
function editDelete_PwDelete(bm_pw_txt) {
	$("#edit_form input[name='bm_pw']").val(bm_pw_txt.val());
	
	var edit_form = $("#edit_form").serialize();
	
	$.ajax({
		type: "post",
		url: "editdelete_member",
		dataType: "json",
		data: edit_form,
		cache : false,
		success: function(data) {
			// 계정 삭제 성공 시.
			if (data.edit_delete_member > 0) {
				swal("계정이 삭제되었습니다.", "", "success");
				
				$("#edit_form").attr("action", "logout");
				$("#edit_form").submit();
				
			// 계정 삭제 실패 시.
			} else {
				swal("잘못된 비밀번호입니다. 다시 확인하세요.", "", "warning");
			}
		},
		error: function(request, status, error) {
			swal("비밀번호 변경에 문제가 발생하였습니다.", "오른쪽 하단에 ? 클릭하여 관리자에게 문의해주시기 바랍니다.", "error");
		}
	});
}

// 프로필 편집_프로필 사진_삭제 function.
function editDelete_ProfileImg() {
	var edit_form = $("#edit_form").serialize();
	
	$.ajax({
		type: "post",
		url: "editdelete_prorileimg",
		dataType: "json",
		data: edit_form,
		cache : false,
		success: function(data) {
			// 프로필 사진_삭제 성공 시.
			if(data.edit_delete_prorileimg > 0) {
				$("#edit_img_fileupload").val("");
				$("#header_profile_img").attr("src", "resources/images/etc/non_profile.png");
				$("#profileedit_overlay").hide();
				$("#edit_file_popup").empty();
				
				swal("프로필 사진이 삭제되었습니다.", "", "success");
				
				editSelect_profileEdit(); // 프로필 편집_프로필 편집_조회 function.
				
			// 프로필 사진_삭제 실패 시.
			} else if(data.edit_delete_prorileimg == 0) {
				$("#edit_img_fileupload").val("");
				$("#header_profile_img").attr("src", "resources/images/etc/non_profile.png");
				
				swal("사진을 삭제하는 중 문제가 발생하였습니다.", "오른쪽 하단에 ? 클릭하여 관리자에게 문의해주시기 바랍니다.", "error");
				
			// 프로필 사진_세션 만료 시.
			} else {
				$("#edit_img_fileupload").val("");
				$("#header_profile_img").attr("src", "resources/images/etc/non_profile.png");
				
				swal("다시 로그인해주시기 바랍니다.", "로그아웃 되어 사진을 삭제할 수 없습니다.", "warning");
			}
		},
		error: function(request, status, error) {
			$("#edit_img_fileupload").val("");
			$("#header_profile_img").attr("src", "resources/images/etc/non_profile.png");
			
			swal("서버에 문제가 발생하였습니다.", "오른쪽 하단에 ? 클릭하여 관리자에게 문의해주시기 바랍니다.", "error");
		}
	});
}

// 프로필 편집_1:1문의_삭제 function.
function editDelete_Complain() {
	var edit_form = $("#edit_form").serialize();
	
	var result = 0;
	
	$.ajax({
		type: "post",
		url: "editdelete_complain",
		dataType: "json",
		data: edit_form,
		async : false,
		cache : false,
		success: function(data) {
			// 프로필 편집_1:1문의_삭제 성공 시.
			if (data.edit_delete_complain > 0) {
				result = 1;
				
			// 프로필 편집_1:1문의_삭제 실패 시.
			} else {
				result = 0;
			}
		},
		error: function(request, status, error) {
			result = 1;
		}
	});
	
	return result;
}