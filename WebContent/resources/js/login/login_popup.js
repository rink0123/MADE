/**
 * 로그인, 아이디 찾기, 비밀번호 찾기_popup function
 */

// 로그인 popup
function basicLogin_popup() {
	var login_popup = "";
	
	login_popup += "<div class=\"login_popup\">";
	login_popup += "	<p><br/></p>";
	login_popup += "	<div class=\"form-input\">"; // 아이디 & 비밀번호 입력란
	login_popup += "		<input type=\"text\"  id=\"bm_id_txt\" placeholder=\"ID\" />";
	login_popup += "	</div>";
	login_popup += "	<div class=\"form-input\">";
	login_popup += "		<input type=\"password\" id=\"bm_pw_txt\" placeholder=\"Password\" />";
	login_popup += "	</div>";
	login_popup += "	<div class=\"redcard\" style=\"display: none; font-size: 12px; font-weight: bold; color: red;\"></div>"; // 아이디가 존재하지 않을 시
	login_popup += "	<div class=\"form-input\">"; // 로그인 버튼";
	login_popup += "		<input type=\"button\" id=\"login_btn\" value=\"login\" />";
	login_popup += "	</div>";
	login_popup += "	<a id=\"singin\" class=\"signin\" style=\"text-decoration: underline;\">회원가입</a>"; // 회원가입 버튼";
	login_popup += "	<a id=\"forget\" class=\"forget\" style=\"text-decoration: underline;\">아이디/비밀번호를 잊으셨나요?</a>";
	login_popup += "	<br/>";
	login_popup += "	<p>";
	login_popup += "		<a id=\"kakao-login-btn\"></a>";
	login_popup += "		<a href=\"http://developers.kakao.com/logout\"></a>";
	login_popup += "		<script type=\"text/javascript\">";
	login_popup += "			Kakao.cleanup();"; // 카카오 init 초기화.
	login_popup += "			Kakao.init('480c7bd2e3dc65ecd032beb286d13bab');"; // 사용할 앱의 JavaScript 키를 설정해 주세요., 여기서 아까 발급받은 키 중 javascript키를 사용해준다.
	login_popup += "			Kakao.Auth.createLoginButton({"; // 카카오 로그인 버튼을 생성합니다.
	login_popup += "				container: '#kakao-login-btn',";
	login_popup += "				success: function(authObj) {";
	login_popup += "					alert(JSON.stringify(authObj));";
	login_popup += "					Kakao.API.request({";
	login_popup += "						url: '/v2/user/me',";
	login_popup += "						success: function(res) {";
	login_popup += "							alert(JSON.stringify(res));"; //<---- kakao.api.request 에서 불러온 결과값 json형태로 출력.
	login_popup += "							alert(JSON.stringify(authObj));"; //<----Kakao.Auth.createLoginButton에서 불러온 결과값 json형태로 출력
	login_popup += "							console.log(res.id);"; //<---- 콘솔 로그에 id 정보 출력(id는 res안에 있기 때문에  res.id 로 불러온다)
	login_popup += "							console.log(res.kaccount_email);"; //<---- 콘솔 로그에 email 정보 출력 (어딨는지 알겠죠?)
	login_popup += "							console.log(authObj.access_token);"; //<---- 콘솔 로그에 토큰값 출력
	login_popup += "							Kakao.Auth.authorize({";
	login_popup += "								redirectUri: 'http://localhost:8088/TMProject_admin/main.jsp'";
	login_popup += "							});";
	login_popup += "						}";
	login_popup += "					});";
	login_popup += "				},";
	login_popup += "				fail: function(err) {";
	login_popup += "					alert(JSON.stringify(err));";
	login_popup += "				}";
	login_popup += "			});";
	login_popup += "		</script>";
	login_popup += "	</p>";
	login_popup += "	<div id=\"naverIdLogin\"></div>"; // 네이버 아이디로 로그인 버튼 노출 영역.
	login_popup += "	<script type=\"text/javascript\">"; // 네이버 아이디로 로그인 초기화 Script
	login_popup += "		var naverLogin = new naver.LoginWithNaverId({";
	login_popup += "			clientId: \"BkriWRmD6lVkAn1NDpmf\",";
	login_popup += "			callbackUrl: \"http://localhost:8088/TMProject_admin/main.jsp\",";
	login_popup += "			isPopup: false, /* 팝업을 통한 연동처리 여부 */";
	login_popup += "			loginButton: {color: \"green\", type: 3, height: 48} /* 로그인 버튼의 타입을 지정 */";
	login_popup += "		});";
	login_popup += "		naverLogin.init();"; // 설정정보를 초기화하고 연동을 준비.
	login_popup += "	</script>";
	login_popup += "</div>";
	
	// 로그인 팝업창 활성화.
	$(".login-form .popup").empty(); // 로그인, 회원가입 팝업창 초기화.
	$(".login-form .popup").html(login_popup);
	
	// 아이디 찾기 팝업창 활성화 시 이름 입력란에 focus.
	$("#bm_id_txt").focus();
	
	// 로그인 버튼 클릭 시 로그인
	$("#login_btn").on("click", function() {
		var bm_id_txt = $("#bm_id_txt");
		var bm_pw_txt = $("#bm_pw_txt");
		
		// 로그인 체크(ajax) function
		basicLogin_chk(bm_id_txt, bm_pw_txt);
	});
	
	// 회원가입 버튼 클릭 시 이벤트.
	$("#singin").on("click", function() {
		// 회원가입 popup.
		basicJoin_popup();
	});
	
	// 아이디 입력란 KeyBoard 이벤트
	$("#bm_pw_txt").on("keydown", function(event) {
		// 아이디 입력란 엔터키 적용
		if(event.which == 13) {
			$("#bm_pw_txt").focus();
		}
	});
	
	// 비밀번호 입력란 KeyBoard 이벤트
	$("#bm_pw_txt").on("keydown", function(event) {
		// 비밀번호 Ctrl + C, V 막기
		if (event.ctrlKey == true && (event.which == "118" || event.which == "86")) {
			event.preventDefault();
			
		// 비밀번호 입력란 엔터키 적용
		} else if(event.which == 13) {
			$("#login_btn").focus();
			$("#login_btn").focusout();
		}
	});
	
	// 아이디/비밀번호 찾기 버튼 클릭 시 이벤트
	$("#forget").on("click", function() {
		// 아이디 찾기 popup
		basicFindId_popup();
	});
}

// 아이디 찾기 popup
function basicFindId_popup() {
	var findid_popup = "";
	
	findid_popup += "<div class=\"findid_popup\">";
	findid_popup += "<h3 style=\"margin-top: 50px; text-align: center;\">아이디 찾기</h3>";
	findid_popup += "	<div class=\"form-input\">";
	findid_popup += "		<h5>이름</h5>";
	findid_popup += "		<input type=\"text\" id=\"bm_name_txt\" placeholder=\"이름\">";
	findid_popup += "	</div>";
	findid_popup += "	<div class=\"form-input\">";
	findid_popup += "		<h5>이메일</h5>";
	findid_popup += "		<input type=\"text\" id=\"bm_email_txt\" placeholder=\"Email\">";
	findid_popup += "	</div>";
	findid_popup += "	<div class=\"redcard\" style=\"display: none; font-size: 12px; font-weight: bold; color: red;\"></div>";
	findid_popup += "	<a id=\"forget\" class=\"forget\" style=\"text-decoration: underline;\">비밀번호를 잊으셨나요?</a>";
	findid_popup += "	<div class=\"form-input\">";
	findid_popup += "		<input type=\"button\" id=\"findid_btn\" value=\"아이디 찾기\">";
	findid_popup += "	</div>";
	findid_popup += "	<div class=\"form-input\">";
	findid_popup += "		<input type=\"button\" id=\"find_cancle_btn\" value=\"취소\" style=\"background: #0090FF; color: #FFFFFF;\">";
	findid_popup += "	</div>";
	findid_popup += "</div>";
	
	// 로그인 폼 초기화 후 아이디 찾기 팝업창 활성화.
	$(".login-form .popup").empty();
	$(".login-form .popup").html(findid_popup);
	
	// 아이디 찾기 팝업창 활성화 시 이름 입력란에 focus.
	$("#bm_name_txt").focus();
	
	// 비밀번호 찾기 버튼 클릭 시 이벤트 발생.
	$("#forget").on("click", function() {
		// 비밀번호 찾기 popup.
		basicFindPw_popup();
	});
	
	// 아이디 찾기 버튼 클릭 시 이벤트 발생.
	$("#findid_btn").on("click", function() {
		var bm_name_txt = $("#bm_name_txt");
		var bm_email_txt = $("#bm_email_txt");
		
		// 아이디 찾기 체크(ajax) function.
		basicFindId_chk(bm_name_txt, bm_email_txt);
	});
	
	// 이메일 입력란 KeyBoard 이벤트.
	$("#bm_email_txt").on("keydown", function(event) {
		// 비밀번호 입력란 엔터키 적용
		if(event.which == 13) {
			$("#findid_btn").click();
		}
	});
	
	// 취소 버튼 클릭 시 로그인 팝업창 활성화.
	$("#find_cancle_btn").on("click", function() {
		// 로그인 popup.
		basicLogin_popup();
	});
}

// 비밀번호 찾기 popup.
function basicFindPw_popup() {
	var findpw_popup = "";
	
	findpw_popup += "<div class=\"findpw_popup\">";
	findpw_popup += "<h3 style=\"margin-top: 50px; text-align: center;\">비밀번호 찾기</h3>";
	findpw_popup += "	<div class=\"form-input\">";
	findpw_popup += "		<h5>ID</h5>";
	findpw_popup += "		<input type=\"text\" id=\"bm_id_txt\" placeholder=\"ID\">";
	findpw_popup += "	</div>";
	findpw_popup += "	<div class=\"form-input\">";
	findpw_popup += "		<h5>이메일</h5>";
	findpw_popup += "		<input type=\"text\" id=\"bm_email_txt\" placeholder=\"Email\">";
	findpw_popup += "	</div>";
	findpw_popup += "	<div class=\"redcard\" style=\"display: none; font-size: 12px; font-weight: bold; color: red;\"></div>";
	findpw_popup += "	<a id=\"forget\" class=\"forget\" style=\"text-decoration: underline;\">아이디를 잊으셨나요?</a>";
	findpw_popup += "	<div class=\"form-input\">";
	findpw_popup += "		<input type=\"button\" id=\"findpw_btn\" value=\"비밀번호 찾기\">";
	findpw_popup += "	</div>";
	findpw_popup += "	<div class=\"form-input\">";
	findpw_popup += "		<input type=\"button\" id=\"find_cancle_btn\" value=\"취소\" style=\"background: #0090FF; color: #FFFFFF;\">";
	findpw_popup += "	</div>";
	findpw_popup += "</div>";
	
	// 로그인 폼 초기화 후 비밀번호 찾기 팝업창 활성화.
	$(".login-form .popup").empty();
	$(".login-form .popup").html(findpw_popup);
	
	// 비밀번호 찾기 팝업창 활성화 시 아이디 입력란에 focus.
	$("#bm_id_txt").focus();
	
	// 아이디 찾기 버튼 클릭 시 이벤트 발생.
	$("#forget").on("click", function() {
		// 아이디 찾기 popup.
		basicFindId_popup();
	});
	
	// 비밀번호 찾기 버튼 클릭 시 이벤트 발생.
	$("#findpw_btn").on("click", function() {
		var bm_id_txt = $("#bm_id_txt");
		var bm_email_txt = $("#bm_email_txt");
		
		basicFindPw_chk(bm_id_txt, bm_email_txt); // 비밀번호 찾기 체크(ajax) function.
	});
	
	// 이메일 입력란 KeyBoard 이벤트.
	$("#bm_email_txt").on("keydown", function(event) {
		// 비밀번호 입력란 엔터키 적용
		if(event.which == 13) {
			$("#findpw_btn").click();
		}
	});
	
	// 취소 버튼 클릭 시 로그인 팝업창 활성화.
	$("#find_cancle_btn").on("click", function() {
		// 로그인 popup.
		basicLogin_popup();
	});
}