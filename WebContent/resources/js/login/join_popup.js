/**
 * 회원가입_popup function
 */

// 회원가입 popup.
function basicJoin_popup() {
	var join_popup = "";
	
	join_popup += "<div class=\"join_popup\">";
	join_popup += "	<h3 style=\"margin-top: 50px; text-align: center;\">회원가입</h3>";
	join_popup += "	<div class=\"form-input\">";
	join_popup += "		<h5 style=\"margin: 0px 0px 10px 0px;\">아이디*</h5>";
	join_popup += "		<input type=\"text\" id=\"bm_id_txt\" maxlength=\"20\" placeholder=\"필수, 6~20자 사이\" required />";
	join_popup += "	</div>";
	join_popup += "	<div class=\"form-input\">";
	join_popup += "		<h5 style=\"margin: 0px 0px 10px 0px;\">비밀번호*</h5>";
	join_popup += "		<input type=\"password\" id=\"bm_pw_txt\" maxlength=\"20\" placeholder=\"필수, 6~20자 사이\" required />";
	join_popup += "	</div>";
	join_popup += "	<div class=\"form-input\">";
	join_popup += "		<h5 style=\"margin: 0px 0px 10px 0px;\">이름*</h5>";
	join_popup += "		<input type=\"text\" id=\"bm_name_txt\" maxlength=\"20\" placeholder=\"필수, 1~20자 사이\" required />";
	join_popup += "	</div>";
	join_popup += "	<div class=\"form-input\">";
	join_popup += "		<h5 style=\"margin: 0px 0px 10px 0px;\">사용자 이름*</h5>";
	join_popup += "		<input type=\"text\" id=\"bm_nick_txt\" maxlength=\"8\" placeholder=\"필수, 1~20자 사이\" required />";
	join_popup += "	</div>";
	join_popup += "	<div class=\"form-input\">";
	join_popup += "		<h5 style=\"margin: 0px 0px 10px 0px;\">이메일*</h5>";
	join_popup += "		<input type=\"text\" id=\"bm_email_txt\" placeholder=\"필수, ID@Domain\" required />";
	join_popup += "	</div>";
	join_popup += "	<div class=\"form-input\">";
	join_popup += "		<h5 style=\"margin: 0px 0px 10px 0px;\">연락처</h5>";
	join_popup += "		<input type=\"text\" id=\"bm_phone_txt\" placeholder=\"xxx-xxxx-xxxx\">";
	join_popup += "	</div>";
	join_popup += "	<div class=\"form-input\">";
	join_popup += "		<input type=\"button\" id=\"join_btn\" value=\"가입하기\" />";
	join_popup += "	</div>";
	join_popup += "	<div class=\"form-input\">";
	join_popup += "		<input type=\"button\" id=\"join_cancle_btn\" value=\"취소\" style=\"background: #0090FF; color: #FFFFFF;\" />";
	join_popup += "</div>";
	join_popup += "</div>";
	
	// 회원가입_popup function 활성화.
	$(".login-form .popup").empty(); // 팝업창 초기화.
	$(".login-form .popup").html(join_popup); // 회원가입_popup function 활성화.
	
	// 비밀번호 입력란 KeyBoard 이벤트
	$("#bm_pw_txt").on("keydown", function(event) {
		// 비밀번호 Ctrl + C, V 막기
		if (event.ctrlKey == true && (event.which == 118 || event.which == 86)) {
			event.preventDefault();
		}
	});
	
	// 입력란 KeyBoard 이벤트.
	$("#input[type='text']").on("keydown", function(event) {
		// 비밀번호 입력란 엔터키 적용
		if(event.which == 13) {
			$("#join_btn").click();
		}
	});
	
	// 아이디_유효성 검사.
	$("#bm_id_txt").focus(function() {
		$("#bm_id_txt").blur(function() {
			joinId_chk($("#bm_id_txt")); // 아이디_유효성 검사 function.
		});
	});
	
	// 비밀번호_유효성 검사.
	$("#bm_pw_txt").focus(function() {
		$("#bm_pw_txt").blur(function() {
			joinPw_chk($("#bm_id_txt"), $("#bm_pw_txt")); // 비밀번호_유효성 검사 function.
		});
	});
	
	// 이름_유효성 검사.
	$("#bm_name_txt").focus(function() {
		$("#bm_name_txt").blur(function() {
			joinName_chk($("#bm_name_txt")); // 이름_유효성 검사 function.
		});
	});
	
	// 핸드폰_유효성 검사.
	$("#bm_phone_txt").focus(function() {
		$("#bm_phone_txt").blur(function() {
			joinPhone_chk($("#bm_phone_txt")); // 핸드폰 번호_유효성 검사 function.
		});
	});
	
	// 이메일_유효성 검사.
	$("#bm_email_txt").focus(function() {
		$("#bm_email_txt").blur(function() {
			joinEmail_chk($("#bm_email_txt")); // 이메일_유효성 검사 function.
		});
	});
	
	// 닉네임_유효성 검사.
	$("#bm_nick_txt").focus(function() {
		$("#bm_nick_txt").blur(function() {
			joinNick_chk($("#bm_nick_txt")); // 닉네임_유효성 검사 function.
		});
	});
	
	// 가입하기 버튼 클릭 시 이벤트 발생.
	$("#join_btn").on("click", function() {
		var bm_id_txt = $("#bm_id_txt");
		var bm_pw_txt = $("#bm_pw_txt");
		var bm_name_txt = $("#bm_name_txt");
		var bm_phone_txt = $("#bm_phone_txt");
		var bm_email_txt = $("#bm_email_txt");
		var bm_nick_txt = $("#bm_nick_txt");
		
		// 가입하기_체크(ajax) function.
		basicJoin(bm_id_txt, bm_pw_txt, bm_name_txt, bm_phone_txt, bm_email_txt, bm_nick_txt);
	});
	
	// 취소 버튼 클릭 시 로그인 팝업창 활성화.
	$("#join_cancle_btn").on("click", function() {
		basicLogin_popup(); // 로그인_popup function.
	});
}