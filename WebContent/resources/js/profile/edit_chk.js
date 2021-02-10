/**
 * 프로필 편집_체크(ajax) function
 */

// ========================================= 프로필 편집 =========================================
// 이름_유효성 검사 function.
function editName_Chk(bm_name_txt) {
	var bm_name_chk = /^[a-zA-Z가-힣]{1,20}$/;
	
	//이름_영문 대소문자, 한글, 1~20자 이내 입력하지 않을 경우.
	if (!bm_name_chk.test(bm_name_txt.val())) {
		bm_name_txt.val("");
		bm_name_txt.attr("placeholder", "1~20자 이내, 영문, 한글이여야 합니다.");
		bm_name_txt.css("box-shadow", "0px 0px 0px 4px rgba(0, 132, 255, 0.5)");

	//이름_모든 조건 만족 시.
	} else {
		bm_name_txt.attr("placeholder", "필수, 1~20자 사이");
		bm_name_txt.css("box-shadow", "");
	}
}

// 닉네임_유효성 검사 function.
function editNick_Chk(bm_nick_txt) {
	var bm_nick_chk = /^[a-zA-Z가-힣0-9]{1,20}$/;
	
	//이름_영문 대소문자, 한글, 1~20자 이내 입력하지 않을 경우.
	if (!bm_nick_chk.test(bm_nick_txt.val())) {
		bm_nick_txt.val("");
		bm_nick_txt.attr("placeholder", "1~20자 이내, 특수문자는 포함할 수 없습니다.");
		bm_nick_txt.css("box-shadow", "0px 0px 0px 4px rgba(0, 132, 255, 0.5)");

		//이름_모든 조건 만족 시.
	} else {
		bm_nick_txt.attr("placeholder", "필수, 1~20자 사이");
		bm_nick_txt.css("box-shadow", "");
	}
}

// 이메일_유효성 검사 function.
function editEmail_Chk(bm_email_txt) {
	var bm_email_chk = /([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
	
	// 이메일_형식에 맞지 않을 경우.
	if (!bm_email_chk.test(bm_email_txt.val())) {
		bm_email_txt.val("");
		bm_email_txt.attr("placeholder", "형식에 맞지 않습니다. ID@Domain");
		bm_email_txt.css("box-shadow", "0px 0px 0px 4px rgba(0, 132, 255, 0.5)");

	// 이메일 번호 조건 만족 시.
	} else {
		bm_email_txt.attr("placeholder", "필수, ID@Domain");
		bm_email_txt.css("box-shadow", "");
	}
}

// 연락처_유효성 검사 function.
function editPhone_Chk(bm_phone_txt) {
	var bm_phone_chk = /(01[0|1|6|9|7])[-](\d{3}|\d{4})[-](\d{4}$)/g;
	var bm_phone_length = $.trim(bm_phone_txt.val()).length;
	
	// 연락처 XXX-XXXX-XXXX 형식에 맞지 않을 경우.
	if (bm_phone_length > 0 && !bm_phone_chk.test(bm_phone_txt.val())) {
		bm_phone_txt.val("");
		bm_phone_txt.attr("placeholder", "형식에 맞지 않습니다. XXX-XXXX-XXXX");
		bm_phone_txt.css("box-shadow", "0px 0px 0px 4px rgba(0, 132, 255, 0.5)");

	// 연락처 조건 만족 시.
	} else {
		bm_phone_txt.attr("placeholder", "XXX-XXXX-XXXX");
		bm_phone_txt.css("box-shadow", "");
	}
}

// 비밀번호_유효성 검사 function.
function editPw_Chk(bm_pw_txt) {
	var result = 0;
	
	// 비밀번호_영문 대소문자 or 숫자 && 글자수 6~20 사이가 아닐 경우.
	if (!/^[a-zA-Z0-9]{6,20}$/.test(bm_pw_txt.val())) {
		bm_pw_txt.val("");
		bm_pw_txt.attr("placeholder", "6~20자 아닙니다. 6~20자 이내로 작성해주시기 바랍니다.");
		bm_pw_txt.css("box-shadow", "0px 0px 0px 4px rgba(0, 132, 255, 0.5)");
		
	// 비밀번호_숫자 or 영문 대소문자가 한 자도 없을 경우.
	} else if (bm_pw_txt.val().search(/[0-9]/g) < 0 || bm_pw_txt.val().search(/[a-z]/ig) < 0) {
		bm_pw_txt.val("");
		bm_pw_txt.attr("placeholder", "영문, 숫자가 포함되어 있어야 합니다.");
		bm_pw_txt.css("box-shadow", "0px 0px 0px 4px rgba(0, 132, 255, 0.5)");
		
	// 비밀번호_모든 조건 만족 시.
	} else {
		bm_pw_txt.attr("placeholder", "필수, 6~20자 사이");
		bm_pw_txt.css("box-shadow", "none");
		
		result = 1;
	}
	
	return result;
}

// 새 비밀번호_유효성 검사 function.
function editNewPw_Chk(bm_pw_txt, new_bm_pw_txt) {
	var result = 0;
	
	// 이전 비밀번호와 새 비밀번호가 동일하게 입력됐을 경우.
	if (bm_pw_txt.val().search(new_bm_pw_txt.val()) > -1) {
		// 비밀번호 입력란 초기화 및 효과 활성화.
		new_bm_pw_txt.val("");
		new_bm_pw_txt.attr("placeholder", "현재 비밀번호와 다른 새 비밀번호를 만드세요.");
		new_bm_pw_txt.css("box-shadow", "0px 0px 0px 4px rgba(0, 132, 255, 0.5)");
		
	// 비밀번호_모든 조건 만족 시.
	} else {
		new_bm_pw_txt.attr("placeholder", "필수, 6~20자 사이");
		new_bm_pw_txt.css("box-shadow", "none");
		
		result = 1;
	}
	
	return result;
}

// 새 비밀번호 확인_유효성 검사 function.
function editChkPw_Chk(new_bm_pw_txt, chk_bm_pw_txt) {
	var result = 0;
	
	// 이전 비밀번호와 새 비밀번호가 동일하게 입력됐을 경우.
	if(new_bm_pw_txt.val() != chk_bm_pw_txt.val()) {
		// 비밀번호 입력란 초기화 및 효과 활성화.
		chk_bm_pw_txt.val("");
		chk_bm_pw_txt.attr("placeholder", "새 비밀번호와 일치하지 않습니다.");
		chk_bm_pw_txt.css("box-shadow", "0px 0px 0px 4px rgba(0, 132, 255, 0.5)");
		
	// 비밀번호_모든 조건 만족 시.
	} else {
		chk_bm_pw_txt.attr("placeholder", "필수, 6~20자 사이");
		chk_bm_pw_txt.css("box-shadow", "none");
		
		result = 1;
	}
	
	return result;
}

// 비밀번호 찾기 체크 function.
function basicFindPw_chk(bm_id_txt, bm_email_txt) {
	$("#edit_form input[name='bm_id']").val(bm_id_txt.val());
	$("#edit_form input[name='bm_email']").val(bm_email_txt.val());
		
	var email_regexp = /([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
	
	// 아이디가 공백이면.
	if($.trim(bm_id_txt.val()) == "") {
		$("#redcard").show();
		$("#redcard").text("ID를 입력해주세요.");
		
		bm_id_txt.focus();
		
		// 이름 입력란 효과 활성화.
		bm_id_txt.css("box-shadow", "0px 0px 0px 4px rgba(0, 132, 255, 0.5)");
		
	// 이메일이 공백이면.
	} else if($.trim(bm_email_txt.val()) == "") {
		$("#redcard").show();
		$("#redcard").text("E-mail을 입력해주세요.");
		
		bm_email_txt.focus();
		
		// 이름 입력란 효과 비활성화, 이메일 입력란 효과 활성화.
		bm_id_txt.css("box-shadow", "");
		bm_email_txt.css("box-shadow", "0px 0px 0px 4px rgba(0, 132, 255, 0.5)");
		
	// 이메일 형식이 맞지 않을 경우.
	} else if (!email_regexp.test(bm_email_txt.val())) {
		$("#redcard").show();
		$("#redcard").text("E-mail에 맞지 않는 형식입니다.");
		
		bm_email_txt.val("");
		bm_email_txt.focus();
		
		// 이름 입력란 효과 비활성화, 이메일 입력란 효과 활성화.
		bm_id_txt.css("box-shadow", "");
		bm_email_txt.css("box-shadow", "0px 0px 0px 4px rgba(0, 132, 255, 0.5)");
		
	} else {
		$("#redcard").text("");
		$("#redcard").hide();
		
		// 아아디, 비밀번호 초기화 및 효과 활성화.
		bm_id_txt.css("box-shadow", "");
		bm_email_txt.css("box-shadow", "");
		
		swal({
		    title: '',
		    text: '회원님의 E-mail로 임시 비밀번호를 전송하는 중입니다.',
		    icon: 'info',
		    timer: 60000, /* 최대 1분으로 설정 */
		    buttons: false,
		    closeModal: false,
		    closeOnClickOutside: false,
		    closeOnEsc: false,
		});
		
		var edit_form = $("#edit_form").serialize();
		$.ajax({
			type : "post",
			url : "findpw",
			dataType : "json",
			data : edit_form,
			cache : false,
			success : function(data) {
				// 비밀번호 찾기 및 임시 비밀번호 성공 시.
				if (data.basic_findpw_update > 0) {
					swal.close();
					swal("", "회원님의 E-mail로 임시 비밀번호를 전송하였습니다.", "success");
					
				// 비밀번호 찾기 및 임시 비밀번호 실패 시.
				} else {
					swal.close();
					
					$("#redcard").show();
					$("#redcard").text("유효하지 않은 ID이거나 E-mail 입니다.");
					bm_email_txt.val("");
					
					// 이름 입력란 효과 비활성화, 이메일 입력란 효과 활성화.
					bm_id_txt.css("box-shadow", "0px 0px 0px 4px rgba(0, 132, 255, 0.5)");
					bm_email_txt.css("box-shadow", "0px 0px 0px 4px rgba(0, 132, 255, 0.5)");
				}
			},
			error : function(request, status, error) {
				swal("비밀번호를 찾는 과정에서 문제가 발생하였습니다.", "오른쪽 하단에 ? 클릭하여 관리자에게 문의해주시기 바랍니다.", "error");
			}
		});
	}
}