/**
 * 프로필 편집_수정 function.
 */

// 프로필 편집_프로필 편집_수정 function.
function editUpdate_ProfileEdit(bm_name_txt, bm_nick_txt, bm_email_txt, bm_phone_txt) {
	$("#edit_form input[name='bm_name']").val(bm_name_txt.val());
	$("#edit_form input[name='bm_nick']").val(bm_nick_txt.val());
	$("#edit_form input[name='bm_email']").val(bm_email_txt.val());
	$("#edit_form input[name='bm_phone']").val(bm_phone_txt.val());
	
	var edit_form = $("#edit_form").serialize();
	
	$.ajax({
		type: "post",
		url: "editupdate_member",
		dataType: "json",
		data: edit_form,
		cache : false,
		success: function(data) {
			// 프로필 편집_수정 성공 시.
			if (data.edit_update_member > 0) {
				swal("프로필이 저장되었습니다.", "", "success");
				
				editSelect_profileEdit(); // 메뉴_프로필 편집_조회 function.
				
			// 프로필 편집_수정 실패 시.
			} else {
				swal("프로필 편집이 안되었습니다.", "오른쪽 하단에 ? 클릭하여 관리자에게 문의해주시기 바랍니다.", "error");
			}
		},
		error: function(request, status, error) {
			swal("프로필 편집에 문제가 발생하였습니다.", "오른쪽 하단에 ? 클릭하여 관리자에게 문의해주시기 바랍니다.", "error");
		}
	});
}

// 프로필 편집_프로필 사진_수정 function.
function editUpdate_ProfileImg(albumPhotosKey, fileName, fileType, dateformat) {
	var url = "https://made-s3.s3.ap-northeast-2.amazonaws.com/";
	var albumPhotosKey = albumPhotosKey;
	var fileName = encodeURIComponent(fileName);
	var fileType = fileType;
	var dateformat = dateformat;
	
	var bm_img = url + albumPhotosKey + fileName + dateformat + fileType;
	$("#edit_form input[name='bm_img']").val(bm_img);
	
	var edit_form = $("#edit_form").serialize();
	
	$.ajax({
		type: "post",
		url: "editupdate_prorileimg",
		dataType: "json",
		data: edit_form,
		cache : false,
		success: function(data) {
			swal.close();
			
			var edit_update_prorileimg = data.edit_update_prorileimg;
			
			// 프로필 사진_수정 성공 시.
			if(edit_update_prorileimg > 0) {
				swal("프로필 사진이 수정되었습니다.", "", "success");
				
				editSelect_profileEdit(); // 프로필 편집_프로필 편집_조회 function.
				
			// 프로필 사진_수정 실패 시.
			} else if(edit_update_prorileimg == 0) {
				swal("사진을 올리는 중 문제가 발생하였습니다.", "오른쪽 하단에 ? 클릭하여 관리자에게 문의해주시기 바랍니다.", "error");
				
			// 프로필 사진_세션 만료 시.
			} else {
				swal("다시 로그인해주시기 바랍니다.", "로그아웃 되어 사진을 올릴 수 없습니다.", "warning");
			}
		},
		error: function(request, status, error) {
			swal.close();
			
			swal("서버에 문제가 발생하였습니다.", "오른쪽 하단에 ? 클릭하여 관리자에게 문의해주시기 바랍니다.", "error");
		}
	});
}

// 프로필 편집_1:1문의_수정 function.
function editUpdate_Complain() {
	var edit_form = $("#edit_form").serialize();
	
	var result = 0;
	
	$.ajax({
		type: "post",
		url: "editupdate_complain",
		dataType: "json",
		data: edit_form,
		async : false,
		cache : false,
		success: function(data) {
			// 프로필 편집_1:1문의_수정 성공 시.
			if (data.edit_update_complain > 0) {
				result = 1;
				
			// 프로필 편집_1:1문의_수정 실패 시.
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

// 프로필 편집_비밀번호 변경_수정 function.
function editUpdate_PwUpdate(bm_pw_txt, chk_bm_pw_txt) {
	$("#edit_form input[name='bm_pw']").val(bm_pw_txt.val());
	$("#edit_form input[name='new_bm_pw']").val(chk_bm_pw_txt.val());
	
	var edit_form = $("#edit_form").serialize();
	
	$.ajax({
		type: "post",
		url: "editupdate_pw",
		dataType: "json",
		data: edit_form,
		cache : false,
		success: function(data) {
			// 비밀번호 변경_수정 성공 시.
			if (data.edit_update_pw > 0) {
				swal("비밀번호가 변경되었습니다.", "", "success");
				
				editSelect_PwUpdate(); // 프로필 편집_비밀번호 변경_조회 function.
				
			// 비밀번호 변경_수정 실패 시.
			} else {
				swal("이전 비밀번호가 잘못 입력되었습니다. 다시 입력해주세요.", "", "warning");
			}
		},
		error: function(request, status, error) {
			swal("비밀번호 변경에 문제가 발생하였습니다.", "오른쪽 하단에 ? 클릭하여 관리자에게 문의해주시기 바랍니다.", "error");
		}
	});
}