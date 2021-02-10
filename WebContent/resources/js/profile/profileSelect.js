/**
 * 프로필 정보_조회 function.
 * 프로필 편집_조회 function.
 */

// ========================================= 프로필 정보 =========================================
// 팔로우_조회 function.
function profileSelect_Follow(bm_no, session_bm_no) {
	$("#profile_form input[name='bm_no']").val(bm_no);
	$("#profile_form input[name='session_bm_no']").val(session_bm_no);
	
	var profile_form = $("#profile_form").serialize();
	
	$.ajax({
		type : "post",
		url : "profileselect_follow",
		dataType : "json",
		data : profile_form,
		cache : false,
		success : function(data) {
			var follow_chk = data.profile_select_follow.follow_chk;
			
			// 팔로잉 상태일 경우.
			if(follow_chk == 1) {
				$(".profile_follow_btn").text("팔로잉");
				$(".profile_follow_btn").attr("id", follow_chk);
				$(".profile_follow_btn").css("background-color", "#FFFFFF");
				$(".profile_follow_btn").css("border", "1px solid #DBDBDB");
				$(".profile_follow_btn").css("color", "#111111");
				
			// 팔로우 상태일 경우.
			} else if(follow_chk == 0) {
				$(".profile_follow_btn").text("팔로우");
				$(".profile_follow_btn").attr("id", follow_chk);
				$(".profile_follow_btn").css("background-color", "#0095F6");
				$(".profile_follow_btn").css("border", "1px solid #0095F6");
				$(".profile_follow_btn").css("color", "#FFFFFF");
				
			// 팔로우, 팔로잉 상태가 아닐 경우.
			} else {
				swal("Follow를 불러오는 과정에서 문제가 발생하였습니다.", "오른쪽 하단에 ? 클릭하여 관리자에게 문의해주시기 바랍니다.", "error");
			}
		},
		error : function(request, status, error) {
			swal("Follow를 불러오는 과정에서 문제가 발생하였습니다.", "오른쪽 하단에 ? 클릭하여 관리자에게 문의해주시기 바랍니다.", "error");
		}
	});
}

// 팔로워 팝업창 목록_조회 function.
function profileSelect_Followers(bm_no, session_bm_no) {
	$("#profile_form input[name='bm_no']").val(bm_no);
	$("#profile_form input[name='session_bm_no']").val(session_bm_no);
	
	var profile_form = $("#profile_form").serialize();
	
	$.ajax({
		type : "post",
		url : "profileselect_followers",
		dataType : "json",
		data : profile_form,
		cache : false,
		success : function(data) {
			var profile_followers_html = "";
			
			// response 값이 1일 경우.
			if(data.result == 1) {
				for(var i = 0 ; i < data.profile_select_followers.length ; i++) {
					var bm_no = data.profile_select_followers[i].bm_no;
					var bm_nick = data.profile_select_followers[i].bm_nick;
					var bm_name = data.profile_select_followers[i].bm_name;
					var follow_chk = data.profile_select_followers[i].follow_chk;
					
					// 팔로워 팝업창_일반회원 프로필 사진이 null이 아닐 경우.
					if(data.profile_select_followers[i].bm_img != null) {
						var bm_img = encodeURIComponent(data.profile_select_followers[i].bm_img);
						
					// 팔로워 팝업창_일반회원 프로필 사진이 null일 경우.
					} else {
						var bm_img = "resources/images/etc/non_profile.png";
					}
					
					// 중복 제거.
					var followers_bm_no_chk = $(".followers_modal_list").find("#" + bm_no);
					
					// 현재 있는 팔로워_일반회원 번호와 새로 불러온 팔로워 일반회원 번호가 같지 않을 경우.
					if(followers_bm_no_chk.attr("id") != bm_no) {
						profile_followers_html += "<div id=" + bm_no + " class=\"followers_modal_profile\" style=\"padding: 8px 16px;\">";
													// 팔로워 팝업창_프로필 사진.
						profile_followers_html += "	<div class=\"profile_photo\" style=\"width: 30px; height: 30px; margin: 3px 0px; vertical-align: top;\">";
						profile_followers_html += "		<img class=\"profile_img\" src=" + bm_img + " style=\"border-radius: 50%;\"/>";
						profile_followers_html += "	</div>";
						profile_followers_html += "	<div class=\"followers_modal_user\">";
														// 팔로워 팝업창_일반회원 닉네임.
						profile_followers_html += "		<div class=\"followers_modal_usernick\">" + bm_nick + "</div>";
														// 팔로워 팝업창_일반회원 이름.
						profile_followers_html += "		<div class=\"followers_modal_username\">" + bm_name + "</div>";
						profile_followers_html += "	</div>";
						
						// 팔로잉 상태이고 session_일반회원 번호와 프로필_일반회원 번호가 같이 않을 경우.
						if(follow_chk == 1 && $("#profile_form input[name='session_bm_no']").val() != bm_no) {
							// 팔로워 팝업창_팔로우 버튼.
							profile_followers_html += "	<div id=" + follow_chk + " class=\"followers_modal_followbtn\" style=\"background-color: #FFFFFF; border: 1px solid #DBDBDB; color: #111111;\">팔로잉</div>";
							
						// 팔로우 상태이고 session_일반회원 번호와 프로필_일반회원 번호가 같이 않을 경우.
						} else if(follow_chk == 0 && $("#profile_form input[name='session_bm_no']").val() != bm_no) {
							// 팔로워 팝업창_팔로우 버튼.
							profile_followers_html += "	<div id=" + follow_chk + " class=\"followers_modal_followbtn\" style=\"background-color: #0095F6; border: 1px solid #0095F6; color: #FFFFFF;\">팔로우</div>";
						}
						
						profile_followers_html += "</div>";
					}
				}
				
				// 팔로워 팝업창_현재 페이지 1증가.
				var followers_nextpage = ($("#profile_form input[name='followers_curpage']").val() * 1) + 1;
				$("#profile_form input[name='followers_curpage']").val(followers_nextpage);
				
				$(".followers_modal_list").append(profile_followers_html);
				$("#profile_followers_cnt").text(data.profile_select_followers[0].follow_cnt);
				
			// response 값이 0이고 팔로워 팝업창_현재 페이지가 1 초과일 경우 이전 페이지 유지.
			} else if(data.result == 0 && $("#profile_form input[name='followers_curpage']").val() > 1) {
				$("#profile_form input[name='followers_curpage']").val($("#profile_form input[name='followers_curpage']").val());
			
			// response 값이 0이고 팔로워 팝업창_현재 페이지가 1인 경우.
			} else if(data.result == 0 && $("#profile_form input[name='followers_curpage']").val() == 1) {
				profile_followers_html += "<div style=\"text-align: center; padding: 20px 0px; font-size: 22px; font-weight: 300; color: #111111\">회원님을 팔로우하는 사람</div>";
				profile_followers_html += "<div style=\"text-align: center; padding-bottom: 20px; font-size: 14px; font-weight: 400; color: #111111\">회원님을 팔로우하는 사람들이 여기에 표시됩니다.</div>";
				
				$(".followers_modal_list").empty();
				$(".followers_modal_list").html(profile_followers_html);
				$("#profile_followers_cnt").text(0);
				
			// response 값이 0 또는 1이 아닐 경우. 
			} else {
				swal("Followers을 불러오는 과정에서 문제가 발생하였습니다.", "오른쪽 하단에 ? 클릭하여 관리자에게 문의해주시기 바랍니다.", "error");
			}
		},
		error : function(request, status, error) {
			swal("Followers을 불러오는 과정에서 문제가 발생하였습니다.", "오른쪽 하단에 ? 클릭하여 관리자에게 문의해주시기 바랍니다.", "error");
		}
	});
}

// 팔로우 팝업창 목록_조회 function.
function profileSelect_Following(bm_no, session_bm_no) {
	$("#profile_form input[name='bm_no']").val(bm_no);
	$("#profile_form input[name='session_bm_no']").val(session_bm_no);
	
	var profile_form = $("#profile_form").serialize();
	
	$.ajax({
		type : "post",
		url : "profileselect_following",
		dataType : "json",
		data : profile_form,
		cache : false,
		success : function(data) {
			var profile_following_html = "";
			
			// response 값이 1일 경우.
			if(data.result == 1) {
				for(var i = 0 ; i < data.profile_select_following.length ; i++) {
					var reg_bm_no = data.profile_select_following[i].reg_bm_no;
					var bm_nick = data.profile_select_following[i].bm_nick;
					var bm_name = data.profile_select_following[i].bm_name;
					var follow_chk = data.profile_select_following[i].follow_chk;
					
					// 팔로우 팝업창_일반회원 프로필 사진이 null이 아닐 경우.
					if(data.profile_select_following[i].bm_img != null) {
						var bm_img = encodeURIComponent(data.profile_select_following[i].bm_img);
						
					// 팔로우 팝업창_일반회원 프로필 사진이 null일 경우.
					} else {
						var bm_img = "resources/images/etc/non_profile.png";
					}
					
					// 중복 제거.
					var following_bm_no_chk = $(".following_modal_list").find("#" + bm_no);
					
					// 현재 있는 팔로우_일반회원 번호와 새로 불러온 팔로우 일반회원 번호가 같지 않을 경우.
					if(following_bm_no_chk.attr("id") != bm_no) {
						profile_following_html += "<div id=" + reg_bm_no + " class=\"following_modal_profile\" style=\"padding: 8px 16px;\">";
													// 팔로우 팝업창_프로필 사진.
						profile_following_html += "	<div class=\"profile_photo\" style=\"width: 30px; height: 30px; margin: 3px 0px; vertical-align: top;\">";
						profile_following_html += "		<img class=\"profile_img\" src=" + bm_img + " style=\"border-radius: 50%;\"/>";
						profile_following_html += "	</div>";
						profile_following_html += "	<div class=\"following_modal_user\">";
														// 팔로우 팝업창_일반회원 닉네임.
						profile_following_html += "		<div class=\"following_modal_usernick\">" + bm_nick + "</div>";
														// 팔로우 팝업창_일반회원 이름.
						profile_following_html += "		<div class=\"following_modal_username\">" + bm_name + "</div>";
						profile_following_html += "	</div>";
						
						// 팔로잉 상태이고 session_일반회원 번호와 프로필_일반회원 번호가 같이 않을 경우.
						if(follow_chk == 1 && $("#profile_form input[name='session_bm_no']").val() != reg_bm_no) {
							// 팔로우 팝업창_팔로우 버튼.
							profile_following_html += "	<div id=" + follow_chk + " class=\"following_modal_followbtn\" style=\"background-color: #FFFFFF; border: 1px solid #DBDBDB; color: #111111;\">팔로잉</div>";
							
						// 팔로우 상태이고 session_일반회원 번호와 프로필_일반회원 번호가 같이 않을 경우.
						} else if(follow_chk == 0 && $("#profile_form input[name='session_bm_no']").val() != reg_bm_no) {
							// 팔로우 팝업창_팔로우 버튼.
							profile_following_html += "	<div id=" + follow_chk + " class=\"following_modal_followbtn\" style=\"background-color: #0095F6; border: 1px solid #0095F6; color: #FFFFFF;\">팔로우</div>";
						}
						profile_following_html += "</div>";
					}
				}
				
				// 팔로우 팝업창_현재 페이지 1증가.
				var following_nextpage = ($("#profile_form input[name='following_curpage']").val() * 1) + 1;
				$("#profile_form input[name='following_curpage']").val(following_nextpage);
				
				$(".following_modal_list").append(profile_following_html);
				$("#profile_following_cnt").text(data.profile_select_following[0].follow_cnt);
				
			// response 값이 0이고 팔로우 팝업창_현재 페이지가 1 초과일 경우 이전 페이지 유지.
			} else if(data.result == 0 && $("#profile_form input[name='following_curpage']").val() > 1) {
				$("#profile_form input[name='following_curpage']").val($("#profile_form input[name='following_curpage']").val());
			
			// response 값이 0이고 팔로우 팝업창_현재 페이지가 1인 경우.
			} else if(data.result == 0 && $("#profile_form input[name='following_curpage']").val() == 1) {
				profile_following_html += "<div style=\"text-align: center; padding: 20px 0px; font-size: 22px; font-weight: 300; color: #111111\">회원님이 팔로우하는 사람</div>";
				profile_following_html += "<div style=\"text-align: center; padding-bottom: 20px; font-size: 14px; font-weight: 400; color: #111111\">회원님이 팔로우하는 사람들이 여기에 표시됩니다.</div>";
				
				$(".following_modal_list").empty();
				$(".following_modal_list").html(profile_following_html);
				$("#profile_following_cnt").text(0);
			
			// response 값이 0 또는 1이 아닐 경우.	
			} else {
				swal("Following을 불러오는 과정에서 문제가 발생하였습니다.", "오른쪽 하단에 ? 클릭하여 관리자에게 문의해주시기 바랍니다.", "error");
			}
		},
		error : function(request, status, error) {
			swal("Following을 불러오는 과정에서 문제가 발생하였습니다.", "오른쪽 하단에 ? 클릭하여 관리자에게 문의해주시기 바랍니다.", "error");
		}
	});
}

// ========================================= 프로필 편집 =========================================
// 프로필 편집_프로필 편집_조회 function.
function editSelect_profileEdit() {
	var edit_form = $("#edit_form").serialize();
	
	$.ajax({
		type : "post",
		url : "editselect_member",
		dataType : "json",
		data : edit_form,
		cache : false,
		success : function(data) {
			var profile_edit_html = "";
		
			// response 값이 1일 경우.
			if(data.result == 1) {
				var bm_img = "";
				// 일반회원 프로필 사진이 null이 아닐 경우.
				if(data.edit_select_member.bm_img != null) {
					bm_img = data.edit_select_member.bm_img;
					
				// 일반회원 프로필 사진이 null일 경우.
				} else {
					bm_img = "resources/images/etc/non_profile.png";
				}
				var bm_nick = data.edit_select_member.bm_nick;
				var bm_name = data.edit_select_member.bm_name;
				var bm_email = data.edit_select_member.bm_email;
				var bm_phone = data.edit_select_member.bm_phone;
				
				profile_edit_html += "<div style=\"margin-bottom: 16px;\">";
				profile_edit_html += "	<div id=\"profile_photo\" class=\"profile_photo\" style=\"width: 38px; height: 38px;\">";
				profile_edit_html += "		<img id=\"profile_img\" class=\"profile_img\" src=" + bm_img + " />";
				profile_edit_html += "	</div>";
				profile_edit_html += "	<div style=\"display:inline-block; width: 100%; max-width: 355px;\">";
				profile_edit_html += "		<div class=\"profile_nick\" style=\"line-height: 22px;\">" + bm_nick + "</div>";
				profile_edit_html += "		<div id=\"profile_edit_imgbtn\" class=\"profile_edit_imgbtn\">프로필 사진 바꾸기</div>";
				profile_edit_html += "	</div>";
				profile_edit_html += "</div>";
				profile_edit_html += "<div style=\"margin-bottom: 16px;\">";
				profile_edit_html += "	<div class=\"profile_edit_labal\">";
				profile_edit_html += "		<span style=\"color: #E60023;\">*</span>이름";
				profile_edit_html += "	</div>";
				profile_edit_html += "	<div style=\"display:inline-block; width: 100%; max-width: 355px;\">";
				profile_edit_html += "		<input type=\"text\" id=\"bm_name_txt\" class=\"profile_edit_txt\" maxlength=\"20\" placeholder=\"필수, 6~20자\" value=" + bm_name + " />";
				profile_edit_html += "		<div class=\"profile_edit_description\">";
				profile_edit_html += "			사람들이 이름, 별명 또는 비즈니스 이름 등 회원님의 알려진 이름을 사용하여 회원님의 계정을 찾을 수 있도록 도와주세요.";
				profile_edit_html += "		</div>";
				profile_edit_html += "	</div>";
				profile_edit_html += "</div>";
				profile_edit_html += "<div style=\"margin-bottom: 16px;\">";
				profile_edit_html += "	<div class=\"profile_edit_labal\">";
				profile_edit_html += "		<span style=\"color: #E60023;\">*</span>사용자 이름";
				profile_edit_html += "	</div>";
				profile_edit_html += "	<div style=\"display:inline-block; width: 100%; max-width: 355px;\">";
				profile_edit_html += "		<input type=\"text\" id=\"bm_nick_txt\" class=\"profile_edit_txt\" maxlength=\"20\" placeholder=\"필수, 1~20자\" value=" + bm_nick + " />";
				profile_edit_html += "	</div>";
				profile_edit_html += "</div>";
				profile_edit_html += "<div style=\"margin: 32px 0px 16px 0px;\">";
				profile_edit_html += "	<div class=\"profile_edit_labal\"></div>";
				profile_edit_html += "	<div style=\"display:inline-block; width: 100%; max-width: 355px;\">";
				profile_edit_html += "		<div class=\"profile_edit_description\">";
				profile_edit_html += "			<span style=\"font-size: 16px; font-weight: 600;\">개인 정보</span>";
				profile_edit_html += "			<br/>";
				profile_edit_html += "			비즈니스나 반려동물 등에 사용된 계정인 경우에도 회원님의 개인 정보를 입력하세요.";
				profile_edit_html += "		</div>";
				profile_edit_html += "	</div>";
				profile_edit_html += "</div>";
				profile_edit_html += "<div style=\"margin-bottom: 16px;\">";
				profile_edit_html += "	<div class=\"profile_edit_labal\">";
				profile_edit_html += "		<span style=\"color: #E60023;\">*</span>이메일";
				profile_edit_html += "	</div>";
				profile_edit_html += "	<div style=\"display:inline-block; width: 100%; max-width: 355px;\">";
				profile_edit_html += "		<input type=\"text\" id=\"bm_email_txt\" class=\"profile_edit_txt\" placeholder=\"ID@Domain\" value=" + bm_email +" />";
				profile_edit_html += "	</div>";
				profile_edit_html += "</div>";
				profile_edit_html += "<div style=\"margin-bottom: 16px;\">";
				profile_edit_html += "	<div class=\"profile_edit_labal\">연락처</div>";
				profile_edit_html += "	<div style=\"display:inline-block; width: 100%; max-width: 355px;\">";
				profile_edit_html += "		<input type=\"text\" id=\"bm_phone_txt\" class=\"profile_edit_txt\" placeholder=\"xxx-xxxx-xxxx\" value=" + bm_phone + " />";
				profile_edit_html += "	</div>";
				profile_edit_html += "</div>";
				profile_edit_html += "<div style=\"margin-bottom: 16px;\">";
				profile_edit_html += "	<div class=\"profile_edit_labal\"></div>";
				profile_edit_html += "	<div id=\"edit_profile_updatebtn\" class=\"profile_edit_btn\">수정</div>";
				profile_edit_html += "</div>";
				
				$("#header_profile_img").attr("src", bm_img);
				
				$("#profile_menu_content").empty();
				$("#profile_menu_content").html(profile_edit_html);
				
			// response 값이 0일 경우.
			} else if(data.result == 0) {
				profile_edit_html += "<div style=\"font-size: 24px; font-weight: 600; color: #111111; padding: 20px 0px; text-align: center;\">";
				profile_edit_html += "	죄송합니다. 페이지를 사용할 수 없습니다.";
				profile_edit_html += "</div>";
				profile_edit_html += "<div style=\"font-size: 16px; line-height: 24px; color: #111111; padding: 16px 0; text-align: center;\">";
				profile_edit_html += "	클릭하신 링크가 잘못되었거나 페이지가 삭제되었습니다.";
				profile_edit_html += "</div>";
				
				$(".profile_edit").empty();
				$(".profile_edit").css("border", "none");
				$(".profile_edit").html(profile_edit_html);
			}
		},
		error : function(request, status, error) {
			profile_edit_html += "<div style=\"font-size: 24px; font-weight: 600; color: #111111; margin: 20px 0px; text-align: center;\">";
			profile_edit_html += "	죄송합니다. 페이지를 사용할 수 없습니다.";
			profile_edit_html += "</div>";
			profile_edit_html += "<div style=\"font-size: 16px; line-height: 24px; color: #111111; margin: 16px 0; text-align: center;\">";
			profile_edit_html += "	클릭하신 링크가 잘못되었거나 페이지가 삭제되었습니다.";
			profile_edit_html += "	&nbsp;<span id=\"edit_backbtn\" style=\"color: #00376B; cursor: pointer;\">되돌아가기.</span>";
			profile_edit_html += "</div>";
			
			$(".profile_edit").empty();
			$(".profile_edit").css("border", "none");
			$(".profile_edit").html(profile_edit_html);
		}
	});
}

// 프로필 편집_비밀번호 변경_조회 function.
function editSelect_PwUpdate() {
	var edit_form = $("#edit_form").serialize();
	
	$.ajax({
		type : "post",
		url : "editselect_member",
		dataType : "json",
		data : edit_form,
		cache : false,
		success : function(data) {
			var profile_edit_html = "";
		
			// response 값이 1일 경우.
			if(data.result == 1) {
				// 일반회원 프로필 사진이 null이 아닐 경우.
				if(data.edit_select_member.bm_img != null) {
					var bm_img = data.edit_select_member.bm_img;
					
				// 일반회원 프로필 사진이 null일 경우.
				} else {
					var bm_img = "resources/images/etc/non_profile.png";
				}
				var bm_nick = data.edit_select_member.bm_nick;
				profile_edit_html += "<div style=\"margin-bottom: 16px;\">";
				profile_edit_html += "	<div id=\"profile_photo\" class=\"profile_photo\" style=\"width: 38px; height: 38px; cursor: auto;\">";
				profile_edit_html += "		<img id=\"profile_img\" class=\"profile_img\" src=" + bm_img + " /> 프로필_사진";
				profile_edit_html += "	</div>";
				profile_edit_html += "	<div style=\"display:inline-block; width: 100%; max-width: 355px;\">";
				profile_edit_html += "		<div class=\"profile_nick\" style=\"font-size: 24px; font-weight: 400; line-height: 38px;\">" + bm_nick + "</div>";
				profile_edit_html += "	</div>";
				profile_edit_html += "</div>";
				profile_edit_html += "<div style=\"margin-bottom: 16px;\">";
				profile_edit_html += "	<div class=\"profile_edit_labal\">이전 비밀번호</div>";
				profile_edit_html += "	<div style=\"display:inline-block; width: 100%; max-width: 355px;\">";
				profile_edit_html += "		<input type=\"password\" id=\"bm_pw_txt\" class=\"profile_edit_txt\" maxlength=\"20\" placeholder=\"필수, 6~20자 사이\"/>";
				profile_edit_html += "	</div>";
				profile_edit_html += "</div>";
				profile_edit_html += "<div style=\"margin-bottom: 16px;\">";
				profile_edit_html += "	<div class=\"profile_edit_labal\">새 비밀번호</div>";
				profile_edit_html += "	<div style=\"display:inline-block; width: 100%; max-width: 355px;\">";
				profile_edit_html += "		<input type=\"password\" id=\"new_bm_pw_txt\" class=\"profile_edit_txt\" maxlength=\"20\" placeholder=\"필수, 6~20자 사이\"/>";
				profile_edit_html += "	</div>";
				profile_edit_html += "</div>";
				profile_edit_html += "<div style=\"margin-bottom: 16px;\">";
				profile_edit_html += "	<div class=\"profile_edit_labal\">새 비밀번호 확인</div>";
				profile_edit_html += "	<div style=\"display:inline-block; width: 100%; max-width: 355px;\">";
				profile_edit_html += "		<input type=\"password\" id=\"chk_bm_pw_txt\" class=\"profile_edit_txt\" maxlength=\"20\" placeholder=\"필수, 6~20자 사이\"/>";
				profile_edit_html += "	</div>";
				profile_edit_html += "</div>";
				profile_edit_html += "<div style=\"margin-bottom: 16px;\">";
				profile_edit_html += "	<div class=\"profile_edit_labal\"></div>";
				profile_edit_html += "	<div style=\"display:inline-block; width: 100%; max-width: 355px;\">";
				profile_edit_html += "		<div id=\"edit_pw_updatebtn\" class=\"profile_edit_btn\">비밀번호 변경</div>";
				profile_edit_html += "		<div style=\"margin-top: 25px;\">";
				profile_edit_html += "			<span id=\"profile_edit_findpw\" class=\"profile_edit_findpw\">비밀번호를 잊으셨나요?</span>";
				profile_edit_html += "		</div>";
				profile_edit_html += "	</div>";
				profile_edit_html += "</div>";
				
				$("#profile_menu_content").empty();
				$("#profile_menu_content").html(profile_edit_html);
				
			// response 값이 0일 경우.
			} else if(data.result == 0) {
				profile_edit_html += "<div style=\"font-size: 24px; font-weight: 600; color: #111111; margin: 20px 0px; text-align: center;\">";
				profile_edit_html += "	죄송합니다. 페이지를 사용할 수 없습니다.";
				profile_edit_html += "</div>";
				profile_edit_html += "<div style=\"font-size: 16px; line-height: 24px; color: #111111; margin: 16px 0; text-align: center;\">";
				profile_edit_html += "	클릭하신 링크가 잘못되었거나 페이지가 삭제되었습니다.";
				profile_edit_html += "	&nbsp;<span id=\"edit_backbtn\" style=\"color: #00376B; cursor: pointer;\">되돌아가기.</span>";
				profile_edit_html += "</div>";
				
				$(".profile_edit").empty();
				$(".profile_edit").css("border", "none");
				$(".profile_edit").html(profile_edit_html);
			}
		},
		error : function(request, status, error) {
			profile_edit_html += "<div style=\"font-size: 24px; font-weight: 600; color: #111111; margin: 20px 0px; text-align: center;\">";
			profile_edit_html += "	죄송합니다. 페이지를 사용할 수 없습니다.";
			profile_edit_html += "</div>";
			profile_edit_html += "<div style=\"font-size: 16px; line-height: 24px; color: #111111; margin: 16px 0; text-align: center;\">";
			profile_edit_html += "	클릭하신 링크가 잘못되었거나 페이지가 삭제되었습니다.";
			profile_edit_html += "	&nbsp;<span id=\"edit_backbtn\" style=\"color: #00376B; cursor: pointer;\">되돌아가기.</span>";
			profile_edit_html += "</div>";
			
			$(".profile_edit").empty();
			$(".profile_edit").css("border", "none");
			$(".profile_edit").html(profile_edit_html);
		}
	});
}

// 프로필 편집_계정 삭제_조회 function.
function editSelect_MemWithdrawal() {
	var edit_form = $("#edit_form").serialize();
	
	$.ajax({
		type : "post",
		url : "editselect_member",
		dataType : "json",
		data : edit_form,
		cache : false,
		success : function(data) {
			var profile_edit_html = "";
			
			// response 값이 1일 경우.
			if(data.result == 1) {
				var bm_nick = data.edit_select_member.bm_nick;
			
				profile_edit_html += "<div style=\"display:inline-block; width: 100%; max-width: 355px;\">";
				profile_edit_html += "	<div class=\"profile_edit_description\" style=\"font-size: 14px; color: #111111;\">";
				profile_edit_html += "		<span style=\"font-size: 14px; font-weight: 600;\">" + bm_nick + "</span>님, 안녕하세요!";
				profile_edit_html += "	</div>";
				profile_edit_html += "	<div class=\"profile_edit_description\" style=\"font-size: 14px; color: #111111;\">계정을 삭제하려고 하신다니 아쉽습니다.</div>";
				profile_edit_html += "</div>";
				profile_edit_html += "<hr style=\"margin: 16px 0px 32px 0px;\"/>";
				profile_edit_html += "<div style=\"margin-bottom: 16px;\">";
				profile_edit_html += "	<div class=\"profile_edit_labal\" style=\"font-size: 14px; max-width: 290px; padding-right: 42px;\">계정을 삭제하시는 이유가 무엇인가요?</div>";
				profile_edit_html += "	<div style=\"display:inline-block; width: 100%; max-width: 345px;\">";
				profile_edit_html += "		<select id=\"profile_edit_opt\" class=\"profile_edit_txt\">";
				profile_edit_html += "			<option value=\"hide\">---</option>";
				profile_edit_html += "			<option value=\"show\">시작할 때 문제가 있음</option>";
				profile_edit_html += "			<option value=\"show\">삭제하고 싶은 내용이 있음</option>";
				profile_edit_html += "			<option value=\"show\">개인 정보 보호 문제</option>";
				profile_edit_html += "			<option value=\"show\">팔로우 할 사람을 찾을 수 없음</option>";
				profile_edit_html += "			<option value=\"show\">너무 내용이 많고 산만함</option>";
				profile_edit_html += "			<option value=\"show\">기타</option>";
				profile_edit_html += "		</select>";
				profile_edit_html += "	</div>";
				profile_edit_html += "</div>";
				profile_edit_html += "<div id=\"withdrawal_progress\" style=\"display: none;\">";
				profile_edit_html += "	<div style=\"margin-bottom: 16px;\">";
				profile_edit_html += "		<div class=\"profile_edit_labal\" style=\"font-size: 14px; max-width: 290px;\">계속하려면 비밀번호를 다시 입력하세요.</div>";
				profile_edit_html += "		<div style=\"display:inline-block; width: 100%; max-width: 345px;\">";
				profile_edit_html += "			<input type=\"password\" id=\"bm_pw_txt\" class=\"profile_edit_txt\" maxlength=\"20\" placeholder=\"필수, 6~20자 사이\"/>";
				profile_edit_html += "		</div>";
				profile_edit_html += "		<div style=\"margin-top: 16px;\">";
				profile_edit_html += "			<span id=\"profile_edit_findpw\" class=\"profile_edit_findpw\">비밀번호를 잊으셨나요?</span>";
				profile_edit_html += "		</div>";
				profile_edit_html += "		<div class=\"profile_edit_description\" style=\"margin-top: 40px; font-size: 14px; color: #111111;\">";
				profile_edit_html += "			아래 버튼을 누르면 사진, 댓글, 좋아요, 친구 관계를 포함한 모든 데이터가 영구적으로 삭제되어 복구할 수 없게 됩니다.";
				profile_edit_html += "		</div>";
				profile_edit_html += "	</div>";
				profile_edit_html += "	<hr style=\"margin: 32px 0px 16px 0px;\"/>";
				profile_edit_html += "	<div style=\"margin-bottom: 16px;\">";
				profile_edit_html += "		<div style=\"display:inline-block; width: 100%; max-width: 355px;\">";
				profile_edit_html += "			<div id=\"edit_pw_deletebtn\" class=\"profile_edit_btn\" style=\"background-color: #E60023; border: 1px solid #E60023;\">내 계정 삭제</div>";
				profile_edit_html += "		</div>";
				profile_edit_html += "	</div>";
				profile_edit_html += "</div>";
				
				$("#profile_menu_content").empty();
				$("#profile_menu_content").html(profile_edit_html);
				
				// response 값이 0일 경우.
			} else if(data.result == 0) {
				profile_edit_html += "<div style=\"font-size: 24px; font-weight: 600; color: #111111; margin: 20px 0px; text-align: center;\">";
				profile_edit_html += "	죄송합니다. 페이지를 사용할 수 없습니다.";
				profile_edit_html += "</div>";
				profile_edit_html += "<div style=\"font-size: 16px; line-height: 24px; color: #111111; margin: 16px 0; text-align: center;\">";
				profile_edit_html += "	클릭하신 링크가 잘못되었거나 페이지가 삭제되었습니다.";
				profile_edit_html += "	&nbsp;<span id=\"edit_backbtn\" style=\"color: #00376B; cursor: pointer;\">되돌아가기.</span>";
				profile_edit_html += "</div>";
				
				$(".profile_edit").empty();
				$(".profile_edit").css("border", "none");
				$(".profile_edit").html(profile_edit_html);
			}
		},
		error : function(request, status, error) {
			profile_edit_html += "<div style=\"font-size: 24px; font-weight: 600; color: #111111; margin: 20px 0px; text-align: center;\">";
			profile_edit_html += "	죄송합니다. 페이지를 사용할 수 없습니다.";
			profile_edit_html += "</div>";
			profile_edit_html += "<div style=\"font-size: 16px; line-height: 24px; color: #111111; margin: 16px 0; text-align: center;\">";
			profile_edit_html += "	클릭하신 링크가 잘못되었거나 페이지가 삭제되었습니다.";
			profile_edit_html += "	&nbsp;<span id=\"edit_backbtn\" style=\"color: #00376B; cursor: pointer;\">되돌아가기.</span>";
			profile_edit_html += "</div>";
			
			$(".profile_edit").empty();
			$(".profile_edit").css("border", "none");
			$(".profile_edit").html(profile_edit_html);
		}
	});
}

// 프로필 편집_1:1문의 목록_조회 function.
function editSelectList_Complain() {
	var edit_form = $("#edit_form").serialize();
	
	$.ajax({
		type : "post",
		url : "editselectlist_complain",
		dataType : "json",
		data : edit_form,
		cache : false,
		success : function(data) {
			var profile_edit_html = "";
			
			// response 값이 1일 경우.
			if(data.result == 1) {
				// 1:1문의 현재 페이지가 1일 경우.
				if($("#edit_form input[name='cp_curpage']").val() == 1) {
					profile_edit_html += "<div class=\"edit_complain_tr\" onmouseover=\"this.style.backgroundColor='transparent'\">";
					profile_edit_html += "	<div class=\"edit_complain_tb\" style=\"font-weight: 600; width: 60%;\">제목</div>";
					profile_edit_html += "	<div class=\"edit_complain_tb\" style=\"font-weight: 600; width: 20%;\">등록일</div>";
					profile_edit_html += "	<div class=\"edit_complain_tb\" style=\"font-weight: 600; width: 20%;\">답변</div>";
					profile_edit_html += "</div>";
					profile_edit_html += "<div id=\"edit_complain\" class=\"edit_complain\">";
				}
				for(var i = 0 ; i < data.edit_selectlist_complain.length ; i++) {
					var cp_no = data.edit_selectlist_complain[i].cp_no;
					var cp_title = data.edit_selectlist_complain[i].cp_title;
					var cp_content = data.edit_selectlist_complain[i].cp_content
					var cp_updatedate = data.edit_selectlist_complain[i].cp_updatedate
					var cpans_content = data.edit_selectlist_complain[i].cpans_content
					
					// 중복제거.
					var cp_no_chk = $("#profile_menu_content").children("#edit_complain").find("#" + cp_no);
					
					// 현재 있는 1:1문의_1:1문의 번호와 새로 불러온 1:1문의_번호가 같지 않을 경우.
					if(cp_no_chk != cp_no) {
						profile_edit_html += "	<div id=" + cp_no + " class=\"edit_complain_line\">";
						profile_edit_html += "		<div class=\"edit_complain_tr\" style=\"cursor: pointer;\">";
						profile_edit_html += "			<div class=\"edit_complain_tb\" style=\"justify-content: flex-start; font-weight: 400; width: 60%;\">" + cp_title + "</div>";
						profile_edit_html += "			<div class=\"edit_complain_tb\" style=\"font-weight: 400; width: 20%;\">" + cp_updatedate + "</div>";
						// 답변 내용이 null이 아니고 공백이 아닐 경우.
						if(cpans_content != null && cpans_content != "") {
						profile_edit_html += "			<div class=\"edit_complain_tb\" style=\"font-weight: 400; width: 20%;\">답변완료</div>";
						// 답변 내용이 null이고 공백인 경우.
						} else {
						profile_edit_html += "			<div class=\"edit_complain_tb\" style=\"font-weight: 400; width: 20%;\">답변대기</div>";
						}
						profile_edit_html += "		</div>";
						profile_edit_html += "		<div id=\"edit_complain_qna\" style=\"display: none;\">";
						profile_edit_html += "			<div class=\"edit_complain_tr\" style=\"border: none; margin: 0px;\">";
						profile_edit_html += "				<div class=\"edit_complain_tb\" style=\"justify-content: flex-start; font-weight: 600; width: 20%;\">문의</div>";
						profile_edit_html += "				<div class=\"edit_complain_tb\" style=\"justify-content: flex-start; width: 80%;\">" + cp_content + "</div>";
						profile_edit_html += "			</div>";
						profile_edit_html += "			<div class=\"edit_complain_tr\" style=\"justify-content: flex-end; border: none; margin: 0px;\">";
						// 답변 내용이 null이 아니고 공백이 아닐 경우.
						/*if(cpans_content == null && cpans_content == "") {*/
							profile_edit_html += "				<div id=" + cp_no + " class=\"edit_complain_btn edit_complain_updatebtn\">수정</div>";
						/*}*/
						profile_edit_html += "				<div id=" + cp_no + " class=\"edit_complain_btn edit_complain_deletebtn\">삭제</div>";
						profile_edit_html += "			</div>";
						// 답변 내용이 null이 아니고 공백이 아닐 경우.
						if(cpans_content != null && cpans_content != "") {
						profile_edit_html += "			<hr style=\"margin: 0 16px; border-top: none; border-color: #DBDBDB;\"/>";
						profile_edit_html += "			<div class=\"edit_complain_tr\" style=\"border: none; margin: 0 0 30px 0;\">";
						profile_edit_html += "				<div class=\"edit_complain_tb\" style=\"justify-content: flex-start; font-weight: 600; width: 20%;\">답변</div>";
						profile_edit_html += "				<div class=\"edit_complain_tb\" style=\"justify-content: flex-start; width: 80%;\">" + cpans_content + "</div>";
						profile_edit_html += "			</div>";
						}
						profile_edit_html += "		</div>";
						profile_edit_html += "	</div>";
					}
				}
				// 1:1문의 현재 페이지가 1일 경우.
				if($("#edit_form input[name='cp_curpage']").val() == 1) {
					profile_edit_html += "</div>";
					
					$(".profile_menu_content").append(profile_edit_html);
					
				// 1:1문의 현재 페이지가 1이 아닐 경우.	
				} else {
					$("#edit_complain").append(profile_edit_html);
				}
				
				var cp_nextpage = ($("#edit_form input[name='cp_curpage']").val() * 1) + 1;
				$("#edit_form input[name='cp_curpage']").val(cp_nextpage);
				
			// response 값이 0이고 1:1문의 팝업창_현재 페이지가 1 초과일 경우 이전 페이지 유지.
			} else if(data.result == 0 && $("#edit_form input[name='cp_curpage']").val() > 1) {
				$("#edit_form input[name='cp_curpage']").val($("#edit_form input[name='cp_curpage']").val());
				
			// response 값이 0이고 1:1문의 팝업창_현재 페이지가 1인 경우.
			} else if(data.result == 0 && $("#edit_form input[name='cp_curpage']").val() == 1) {
				profile_edit_html += "<div style=\"font-size: 24px; font-weight: 600; color: #111111; padding: 20px 0px; text-align: center;\">";
				profile_edit_html += "	문의하신 내역이 없습니다.";
				profile_edit_html += "</div>";
				profile_edit_html += "<div style=\"font-size: 16px; line-height: 24px; color: #111111; padding: 16px 0; text-align: center;\">";
				profile_edit_html += "	문의하실게 있을 경우 오른쪽 하단에 ? 클릭하여 관리자에게 문의해주시기 바랍니다.";
				profile_edit_html += "</div>";
				
				$("#profile_menu_content").empty();
				$("#profile_menu_content").html(profile_edit_html);
				
			// response 값이 0 또는 1이 아닐 경우. 
			} else {
				profile_edit_html += "<div style=\"font-size: 24px; font-weight: 600; color: #111111; margin: 20px 0px; text-align: center;\">";
				profile_edit_html += "	죄송합니다. 페이지를 사용할 수 없습니다.";
				profile_edit_html += "</div>";
				profile_edit_html += "<div style=\"font-size: 16px; line-height: 24px; color: #111111; margin: 16px 0; text-align: center;\">";
				profile_edit_html += "	클릭하신 링크가 잘못되었거나 페이지가 삭제되었습니다.";
				profile_edit_html += "	&nbsp;<span id=\"edit_backbtn\" style=\"color: #00376B; cursor: pointer;\">되돌아가기.</span>";
				profile_edit_html += "</div>";
				
				$(".profile_edit").empty();
				$(".profile_edit").css("border", "none");
				$(".profile_edit").html(profile_edit_html);
			}
			
			// 1:1문의 게시글 클릭 시 이벤트
			$("#profile_menu_content").children("#edit_complain").scroll(function() {
				var innerHeight = $(this).innerHeight();        // 패딩padding 포함 높이 (마진margin과 테두리border는 포함이 되지 않음) --%>
				var scroll = $(this).scrollTop() + innerHeight; // 선택 태그의 Y축 높이(=스크롤 높이) --%>
				var height = $(this)[0].scrollHeight;           // 스크롤 시키지 않았을 때의 전체 높이 --%>
		
				// 스크롤 끝.
				if(scroll >= height) {
					editSelectList_Complain(); // 프로필 편집_1:1문의 목록_조회 function.
				}
			});
		},
		error : function(request, status, error) {
			profile_edit_html += "<div style=\"font-size: 24px; font-weight: 600; color: #111111; margin: 20px 0px; text-align: center;\">";
			profile_edit_html += "	죄송합니다. 페이지를 사용할 수 없습니다.";
			profile_edit_html += "</div>";
			profile_edit_html += "<div style=\"font-size: 16px; line-height: 24px; color: #111111; margin: 16px 0; text-align: center;\">";
			profile_edit_html += "	클릭하신 링크가 잘못되었거나 페이지가 삭제되었습니다.";
			profile_edit_html += "	&nbsp;<span id=\"edit_backbtn\" style=\"color: #00376B; cursor: pointer;\">되돌아가기.</span>";
			profile_edit_html += "</div>";
			
			$(".profile_edit").empty();
			$(".profile_edit").css("border", "none");
			$(".profile_edit").html(profile_edit_html);
		}
	});
}

// 프로필 편집_1:1문의 상세_조회 function.
function editSelectView_Complain() {
	var edit_form = $("#edit_form").serialize();
	
	$.ajax({
		type : "post",
		url : "editselectview_complain",
		dataType : "json",
		data : edit_form,
		cache : false,
		success : function(data) {
			var profile_edit_html = "";
			
			// response 값이 1일 경우.
			if(data.result == 1) {
				var cp_no = data.edit_selectview_complain.cp_no;
				var cp_title = data.edit_selectview_complain.cp_title;
				var cp_content = data.edit_selectview_complain.cp_content;
				
				profile_edit_html += "<div id=\"edit_complain_popup\" class=\"edit_complain_popup\">";
				profile_edit_html += "	<h1 style=\"text-align: center; padding: 16px 0px 32px 0px; margin: 0px; font-size: 28px; font-weight: bold;\">1:1 문의</h1>";
				profile_edit_html += "	<div style=\"display: flex; padding: 12px 16px; border-top: 1px solid #DDDDDD;\">";
				profile_edit_html += "		<div class=\"edit_complain_label\">제목</div>";
				profile_edit_html += "		<textarea id=\"edit_cp_title\" class=\"edit_complain_txt\" spellcheck=\"false\" maxlength=\"100\" placeholder=\"100자 이내\">"+ cp_title + "</textarea>";
				profile_edit_html += "	</div>";
				profile_edit_html += "	<div style=\"display: flex; padding: 12px 16px; border-top: 1px solid #DDDDDD;\">";
				profile_edit_html += "		<div class=\"edit_complain_label\">내용</div>";
				profile_edit_html += "		<textarea id=\"edit_cp_content\" class=\"edit_complain_txt\" spellcheck=\"false\" maxlength=\"300\" placeholder=\"300자 이내\">" + cp_content + "</textarea>";
				profile_edit_html += "	</div>";
				profile_edit_html += "	<div style=\"display: flex; padding: 32px; flex-direction: row; justify-content: flex-end; width: 100%;\">";
				profile_edit_html += "		<div id=\"edit_complainview_cancelbtn\" class=\"edit_complainview_btn\">취소</div>";
				profile_edit_html += "		<div id=" + cp_no + " class=\"edit_complainview_btn edit_complainview_updatebtn\">수정</div>";
				profile_edit_html += "	</div>";
				profile_edit_html += "</div>";
				
				$("#edit_complainview_popup").html(profile_edit_html);
		
				$("#profileedit_overlay").show();
				$("body").css("overflow", "hidden");
				
				// load 시 등록된 내용 textarea auto resize.
				$(".edit_complain_txt").each(function() {
		            $(this).height(1).height($(this).prop("scrollHeight") - 17);
		        });

			// response 값이 0일 경우.
			} else {
				swal("1:1문의 상세 조회에 문제가 발생하였습니다.", "", "error");
			}
		},
		error : function(request, status, error) {
			swal("1:1문의 상세 조회에 문제가 발생하였습니다.", "", "error");
		}
	});
}

// 프로필 사진 바꾸기 popup.
function editFile_popup() {
	var profile_eidt_html = "";
				
	profile_eidt_html += "<div class=\"edit_fileupload\">";
	profile_eidt_html += "	<div class=\"edit_fileupload_tltle\">프로필 사진 바꾸기</div>";
	profile_eidt_html += "	<div id=\"edit_fileupload_deletebtn\" class=\"edit_fileupload_btn\" style=\"font-weight: 700; color: #ED4956;\">현재 사진 삭제</div>";
	profile_eidt_html += "	<div id=\"edit_fileupload_canclebtn\" class=\"edit_fileupload_btn\" style=\"font-weight: 400; color: #111111;\">취소</div>";
	profile_eidt_html += "</div>";
	
	$("#edit_file_popup").empty();
	$("#edit_file_popup").html(profile_eidt_html);
}

// 비밀번호 찾기 popup.
function basicFindPw_popup() {
	var findpw_popup = "";
	
	findpw_popup += "<div class=\"edit_findpw\" style=\"border-radius: 32px;\">";
	findpw_popup += "	<img class=\"logo\" alt=\"made\" src=\"resources/images/etc/madeblack.png\"/>";
	findpw_popup += "	";
	findpw_popup += "	<h3 style=\"margin-top: 50px; text-align: center;\">비밀번호 찾기</h3>";
	findpw_popup += "	<div class=\"form-input\">";
	findpw_popup += "		<h5>ID</h5>";
	findpw_popup += "		<input type=\"text\" id=\"bm_id_txt\" placeholder=\"ID\">";
	findpw_popup += "	</div>";
	findpw_popup += "	<div class=\"form-input\">";
	findpw_popup += "		<h5>이메일</h5>";
	findpw_popup += "		<input type=\"text\" id=\"bm_email_txt\" placeholder=\"Email\">";
	findpw_popup += "	</div>";
	findpw_popup += "	<div id=\"redcard\" class=\"redcard\" style=\"display: none;\"></div>";
	findpw_popup += "	<div class=\"form-input\">";
	findpw_popup += "		<input type=\"button\" id=\"findpw_btn\" value=\"비밀번호 찾기\">";
	findpw_popup += "	</div>";
	findpw_popup += "	<div class=\"form-input\">";
	findpw_popup += "		<input type=\"button\" id=\"find_cancle_btn\" value=\"취소\" style=\"background: #0090FF; color: #FFFFFF;\">";
	findpw_popup += "	</div>";
	findpw_popup += "</div>";
	
	// 로그인 폼 초기화 후 비밀번호 찾기 팝업창 활성화.
	$("#edit_findpw_popup").empty();
	$("#edit_findpw_popup").html(findpw_popup);
	
	// 비밀번호 찾기 팝업창 활성화 시 아이디 입력란에 focus.
	$("#bm_id_txt").focus();
}