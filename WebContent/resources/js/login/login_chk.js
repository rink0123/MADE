/**
 * 로그인 Check function.
 */

// 로그인 체크(ajax) function.
function basicLogin_chk(bm_id_txt, bm_pw_txt) {
	// 아이디가 공백이면.
	if($.trim(bm_id_txt.val()) == "") {
		bm_id_txt.focus();
		
		$(".redcard").show();
		$(".redcard").text("ID를 입력해주세요.");
		
		// 아이디 입력란 효과 활성화.
		bm_id_txt.css("box-shadow", "0px 0px 0px 4px rgba(0, 132, 255, 0.5)");
		
	// 비밀빈호가 공백이면.
	} else if($.trim(bm_pw_txt.val()) == "") {
		bm_pw_txt.focus();
		
		$(".redcard").show();
		$(".redcard").text("비밀번호를 입력해주세요.");
		
		// 아이디 입력란 효과 비활성화, 비밀번호 입력란 활성화.
		bm_id_txt.css("box-shadow", "");
		bm_pw_txt.css("box-shadow", "0px 0px 0px 4px rgba(0, 132, 255, 0.5)");
		
	// 아이디 입력란, 비밀번호 입력란이 공백이 아니면.
	} else {
		$(".feedview_back_index").css("display", "block");
		
		$("[name='bm_id']").val(bm_id_txt.val());
		$("[name='bm_pw']").val(bm_pw_txt.val());
		
		var login_form = $("#login_form").serialize();
		
		$.ajax({
			type : "post",
			url : "basiclogin",
			dataType : "json",
			data : login_form,
			cache : false,
			success : function(data) {
				// 로그인 성공 시 메인 목록으로 이동.
				if (data.result > 0) {
					$("#login_form").attr("action", "feed");				
					$("#login_form").submit();
					
				// 로그인 실패 시 redcard 활성화.
				} else {
					$(".feedview_back_index").css("display", "none");
					
					$(".redcard").show();
					$(".redcard").text("유효하지 않은 아이디입니다.");
					
					// 아아디, 비밀번호 초기화 및 효과 활성화.
					bm_id_txt.val("");
					bm_pw_txt.val("");
					bm_id_txt.css("box-shadow", "0px 0px 0px 4px rgba(0, 132, 255, 0.5)");
					bm_pw_txt.css("box-shadow", "0px 0px 0px 4px rgba(0, 132, 255, 0.5)");
				}
			},
			error : function(request, status, error) {
				swal("로그인 중 문제가 발생하였습니다.", "", "error");
			}
		});
	}
}

// 아이디 찾기 체크(ajax) function.
function basicFindId_chk(bm_name_txt, bm_email_txt) {
	// 아이디가 공백이면.
	if($.trim(bm_name_txt.val()) == "") {
		$(".redcard").show();
		$(".redcard").text("이름을 입력해주세요.");
		
		bm_name_txt.focus();
		
		// 이름 입력란 효과 활성화.
		bm_name_txt.css("box-shadow", "0px 0px 0px 4px rgba(0, 132, 255, 0.5)");
		
	// 이메일이 공백이면.
	} else if($.trim(bm_email_txt.val()) == "") {
		$(".redcard").show();
		$(".redcard").text("E-mail을 입력해주세요.");
		
		bm_email_txt.focus();
		
		// 이름 입력란 효과 비활성화, 이메일 입력란 효과 활성화.
		bm_name_txt.css("box-shadow", "");
		bm_email_txt.css("box-shadow", "0px 0px 0px 4px rgba(0, 132, 255, 0.5)");
		
	// 이메일 형식이 맞지 않을 경우.
	} else if (!/([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/.test(bm_email_txt.val())) {
		$(".redcard").show();
		$(".redcard").text("E-mail에 맞지 않는 형식입니다.");
		bm_email_txt.val("");
		
		bm_email_txt.focus();
		
		// 이름 입력란 효과 비활성화, 이메일 입력란 효과 활성화.
		bm_name_txt.css("box-shadow", "");
		bm_email_txt.css("box-shadow", "0px 0px 0px 4px rgba(0, 132, 255, 0.5)");
		
	} else {
		$("[name='bm_name']").val(bm_name_txt.val());
		$("[name='bm_email']").val(bm_email_txt.val());
		
		var login_form = $("#login_form").serialize();
		
		$.ajax({
			type : "post",
			url : "findid",
			dataType : "json",
			data : login_form,
			cache : false,
			success : function(data) {
				// 로그인 성공 시 메인 목록으로 이동.
				if (data.result > 0) {
					var basic_findid = data.basic_findid.bm_id;
					
					$(".redcard").show();
					$(".redcard").text("귀하의 ID는 " + basic_findid + " 입니다.");
					
				// 로그인 실패 시 redcard 활성화.
				} else {
					$(".redcard").show();
					$(".redcard").text("유효하지 않은 아이디입니다.");
					
					// 아아디, 비밀번호 초기화 및 효과 활성화.
					bm_name_txt.css("box-shadow", "");
					bm_email_txt.css("box-shadow", "");
				}
			},
			error : function(request, status, error) {
				swal("아이디를 찾는 과정에서 문제가 발생하였습니다.", "", "error");
			}
		});
	}
}

// 비밀번호 찾기 체크(ajax) function
function basicFindPw_chk(bm_id_txt, bm_email_txt) {
	// 아이디가 공백이면.
	if($.trim(bm_id_txt.val()) == "") {
		$(".redcard").show();
		$(".redcard").text("ID를 입력해주세요.");
		
		bm_id_txt.focus();
		
		// 이름 입력란 효과 활성화.
		bm_id_txt.css("box-shadow", "0px 0px 0px 4px rgba(0, 132, 255, 0.5)");
		
	// 이메일이 공백이면.
	} else if($.trim(bm_email_txt.val()) == "") {
		$(".redcard").show();
		$(".redcard").text("E-mail을 입력해주세요.");
		
		bm_email_txt.focus();
		
		// 이름 입력란 효과 비활성화, 이메일 입력란 효과 활성화.
		bm_id_txt.css("box-shadow", "");
		bm_email_txt.css("box-shadow", "0px 0px 0px 4px rgba(0, 132, 255, 0.5)");
		
	// 이메일 형식이 맞지 않을 경우.
	} else if (!/([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/.test(bm_email_txt.val())) {
		$(".redcard").show();
		$(".redcard").text("E-mail에 맞지 않는 형식입니다.");
		bm_email_txt.val("");
		
		bm_email_txt.focus();
		
		// 이름 입력란 효과 비활성화, 이메일 입력란 효과 활성화.
		bm_id_txt.css("box-shadow", "");
		bm_email_txt.css("box-shadow", "0px 0px 0px 4px rgba(0, 132, 255, 0.5)");
		
	} else {
		$(".redcard").hide();
		$(".redcard").text("");
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
		
		$("[name='bm_id']").val(bm_id_txt.val());
		$("[name='bm_email']").val(bm_email_txt.val());
		var login_form = $("#login_form").serialize();
		$.ajax({
			type : "post",
			url : "findpw",
			dataType : "json",
			data : login_form,
			cache : false,
			success : function(data) {
				// 비밀번호 찾기 및 임시 비밀번호 성공 시.
				if (data.basic_findpw_update > 0) {
					swal.close();
					swal("", "회원님의 E-mail로 임시 비밀번호를 전송하였습니다.", "success");
					
					basicLogin_popup(); // 로그인 popup.
					
				// 비밀번호 찾기 및 임시 비밀번호 실패 시.
				} else {
					swal.close();
					
					$(".redcard").show();
					$(".redcard").text("유효하지 않은 ID이거나 E-mail 입니다.");
					bm_email_txt.val("");
					
					bm_email_txt.focus();
					
					// 이름 입력란 효과 비활성화, 이메일 입력란 효과 활성화.
					bm_id_txt.css("box-shadow", "");
					bm_email_txt.css("box-shadow", "0px 0px 0px 4px rgba(0, 132, 255, 0.5)");
				}
			},
			error : function(request, status, error) {
				swal("비밀번호를 찾는 과정에서 문제가 발생하였습니다.", "", "error");
			}
		});
	}
}