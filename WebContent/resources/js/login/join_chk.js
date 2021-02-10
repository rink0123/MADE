/**
 * 회원가입_체크(ajax) function
 */

// 아이디_유효성 검사 function.
function joinId_chk(bm_id_txt) {
	// 아이디 영문 대소문자 or 숫자 && 글자수 6~20 사이가 아닐 경우.
	if(!/^[a-zA-Z0-9]{6,20}$/.test(bm_id_txt.val())) {
		// 아이디 입력란 초기화 및 효과 활성화.
		bm_id_txt.val("");
		bm_id_txt.attr("placeholder", "6~20자 이내로 작성해주시기 바랍니다.");
		bm_id_txt.css("box-shadow", "0px 0px 0px 4px rgba(0, 132, 255, 0.5)");
	}
	
	// 아이디 영문  or 숫자가 하나라도 없을 경우.
	if(bm_id_txt.val().search(/[0-9]/g) < 0 || bm_id_txt.val().search(/[a-z]/ig) < 0) {
		// 아이디 입력란 초기화 및 효과 활성화.
		bm_id_txt.val("");
		bm_id_txt.attr("placeholder", "영문과 숫자가 포함되어 있어야 합니다.");
		bm_id_txt.css("box-shadow", "0px 0px 0px 4px rgba(0, 132, 255, 0.5)");

	// 모든 조건 만족할 경우 통과.
	} else {
		$("[name='bm_id']").val(bm_id_txt.val());

		var login_form = $("#login_form").serialize();

		$.ajax({
			type: "post",
			url: "idchk",
			dataType: "json",
			data: login_form,
			cache : false,
			success: function(data) {
				// 아이디 중복 시.
				if (data.bm_idchk > 0) {
					// 아이디 입력란 초기화 및 효과 활성화.
					bm_id_txt.val("");
					bm_id_txt.attr("placeholder", "중복되는 아이디 입니다.");
					bm_id_txt.css("box-shadow", "0px 0px 0px 4px rgba(0, 132, 255, 0.5)");

				// 아이디 중복 안될 시.
				} else {
					// 아이디 입력란 효과 비활성화.
					bm_id_txt.attr("placeholder", "필수, 6~20자 사이");
					bm_id_txt.css("box-shadow", "");
				}
			},
			error: function(request, status, error) {
				swal("아이디 중복 확인 중 문제가 발생하였습니다.", "오른쪽 하단에 ? 클릭하여 관리자에게 문의해주시기 바랍니다.", "error");
			}
		});
	}
}

// 비밀번호_유효성 검사 function.
function joinPw_chk(bm_id_txt, bm_pw_txt) {
	//비밀번호_영문 대소문자 or 숫자 && 글자수 6~20 사이가 아닐 경우.
	if (!/^[a-zA-Z0-9]{6,20}$/.test(bm_pw_txt.val())) {
		// 비밀번호 입력란 초기화 및 효과 활성화.
		bm_pw_txt.val("");
		bm_pw_txt.attr("placeholder", "6~20자 아닙니다. 6~20자 이내로 작성해주시기 바랍니다.");
		bm_pw_txt.css("box-shadow", "0px 0px 0px 4px rgba(0, 132, 255, 0.5)");
	}
	
	//비밀번호_숫자  or 영문 대소문자가 한 자도 없을 경우.
	if (bm_pw_txt.val().search(/[0-9]/g) < 0 || bm_pw_txt.val().search(/[a-z]/ig) < 0) {
		// 아이디 입력란 초기화 및 효과 활성화.
		bm_pw_txt.val("");
		bm_pw_txt.attr("placeholder", "영문, 숫자가 포함되어 있어야 합니다.");
		bm_pw_txt.css("box-shadow", "0px 0px 0px 4px rgba(0, 132, 255, 0.5)");
	}
	
	//비밀번호_아이디와 동일하게 입력했을 경우.
	if (bm_id_txt.val().search(bm_pw_txt.val()) > -1) {
		bm_pw_txt.val("");
		bm_pw_txt.attr("placeholder", "아이디가 포함된 비밀번호는 사용하실 수 없습니다.");
		bm_pw_txt.css("box-shadow", "0px 0px 0px 4px rgba(0, 132, 255, 0.5)");

	//비밀번호_모든 조건 만족 시.
	} else {
		bm_pw_txt.attr("placeholder", "필수, 6~20자 사이");
		bm_pw_txt.css("box-shadow", "");
	}
}

// 이름_유효성 검사 function.
function joinName_chk(bm_name_txt) {
	//이름_영문 대소문자, 한글, 1~20자 이내 입력하지 않을 경우.
	if (!/^[a-zA-Z가-힣]{1,20}$/.test(bm_name_txt.val())) {
		bm_name_txt.val("");
		bm_name_txt.attr("placeholder", "1~20자 이내, 영문, 한글이여야 합니다.");
		bm_name_txt.css("box-shadow", "0px 0px 0px 4px rgba(0, 132, 255, 0.5)");

		//이름_모든 조건 만족 시.
	} else {
		bm_name_txt.attr("placeholder", "필수, 1~20자 사이");
		bm_name_txt.css("box-shadow", "");
	}
}

// 핸드폰 번호_유효성 검사 function.
function joinPhone_chk(bm_phone_txt) {
	// 핸드폰 번호 XXX-XXXX-XXXX 형식에 맞지 않을 경우.
	if (!/(01[0|1|6|9|7])[-](\d{3}|\d{4})[-](\d{4}$)/g.test(bm_phone_txt.val())) {
		bm_phone_txt.val("");
		bm_phone_txt.attr("placeholder", "형식에 맞지 않습니다. XXX-XXXX-XXXX");
		bm_phone_txt.css("box-shadow", "0px 0px 0px 4px rgba(0, 132, 255, 0.5)");

	// 핸드폰 번호 조건 만족 시.
	} else {
		bm_phone_txt.attr("placeholder", "XXX-XXXX-XXXX");
		bm_phone_txt.css("box-shadow", "");
	}
}

// 이메일_유효성 검사 function.
function joinEmail_chk(bm_email_txt) {
	var email_chk = /([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
	// 이메일_형식에 맞지 않을 경우.
	if (!email_chk.test(bm_email_txt.val())) {
		bm_email_txt.val("");
		bm_email_txt.attr("placeholder", "형식에 맞지 않습니다. ID@Domain");
		bm_email_txt.css("box-shadow", "0px 0px 0px 4px rgba(0, 132, 255, 0.5)");

	// 이메일 번호 조건 만족 시.
	} else {
		bm_email_txt.attr("placeholder", "필수, ID@Domain");
		bm_email_txt.css("box-shadow", "");
	}
}

// 닉네임_유효성 검사 function.
function joinNick_chk(bm_nick_txt) {
	//이름_영문 대소문자, 한글, 1~20자 이내 입력하지 않을 경우.
	if (!/^[a-zA-Z가-힣0-9]{1,20}$/.test(bm_nick_txt.val())) {
		bm_nick_txt.val("");
		bm_nick_txt.attr("placeholder", "1~20자 이내, 특수문자는 포함할 수 없습니다.");
		bm_nick_txt.css("box-shadow", "0px 0px 0px 4px rgba(0, 132, 255, 0.5)");

		//이름_모든 조건 만족 시.
	} else {
		bm_nick_txt.attr("placeholder", "필수, 1~20자 사이");
		bm_nick_txt.css("box-shadow", "");
	}
}

// 가입하기_체크(ajax) function.
function basicJoin(bm_id_txt, bm_pw_txt, bm_name_txt, bm_phone_txt, bm_email_txt, bm_nick_txt) {
	// 아이디 입력란이 빈 칸이면.
	if($.trim(bm_id_txt.val()) == "") {
		bm_id_txt.focus();
	
	// 비밀번호 입력란이 빈 칸이면.	
	} else if($.trim(bm_pw_txt.val()) == "") {
		bm_pw_txt.focus();
		
	// 이름 입력란이 빈 칸이면.
	} else if($.trim(bm_name_txt.val()) == "") {
		bm_name_txt.focus();
		
	// 이메일 입력란이 빈 칸이면.
	} else if($.trim(bm_email_txt.val()) == "") {
		bm_email_txt.focus();
		
	// 닉네임 입력란이 빈 칸이면.
	} else if($.trim(bm_nick_txt.val()) == "") {
		bm_nick_txt.focus();
		
	// 모든 조건 만족 시.
	} else {
		$("input[name='bm_id']").val(bm_id_txt.val());
		$("input[name='bm_pw']").val(bm_pw_txt.val());
		$("input[name='bm_name']").val(bm_name_txt.val());
		$("input[name='bm_phone']").val(bm_phone_txt.val());
		$("input[name='bm_email']").val(bm_email_txt.val());
		$("input[name='bm_nick']").val(bm_nick_txt.val());
		
		var login_form = $("#login_form").serialize();
		
		$.ajax({
			type: "post",
			url: "basicjoin",
			dataType: "json",
			data: login_form,
			cache : false,
			success: function(data) {
				if (data.basicjoin > 0) {
					swal("가입 완료", "가입해주셔서 감사합니다", "success");
					
					basicLogin_popup(); // 로그인_popup function.
				} else {
					swal("가입 실패", "", "warning");
				}
			},
			error: function(request, status, error) {
				swal("회원 가입을 진행하는 과정에서 문제가 발생하였습니다.", "", "error");
			}
		});
	}
}