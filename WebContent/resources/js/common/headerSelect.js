/**
 * 헤더 조회
 */

// 헤더_알림 수 조회 long polling function.
function headerSelectNoticeCnt() {
	$.ajax({
		type : "post",
		url : "headerselect_noticecnt",
		dataType : "json",
		cache : false,
		success : function(data) {
			// 헤더_알림 수가 null이 아닐 경우.
			if(data.result == 1) {
				var notice_cnt = (data.header_select_noticecnt.notice_cnt) * 1;
				var before_noticecnt = ($("#header_form input[name='before_noticecnt']").val() * 1) + notice_cnt;
				var current_noticecnt = $("#header_form input[name='current_noticecnt']").val() * 1;
				var abs_noticecnt = Math.abs(before_noticecnt - current_noticecnt);
				
				// 헤더_알림 수가 0초과이고 100미만일 경우.
				if(abs_noticecnt > 0 && abs_noticecnt < 100) {
					$("#header_notice_cnt").text(abs_noticecnt);
					$("#header_notice_cnt").show();
					
				// 헤더_알림 수가 100이상일 경우.
				} else if(abs_noticecnt >= 100) {
					$("#header_notice_cnt").text("99+");
					$("#header_notice_cnt").show();
					
				// 헤더_알림 수 0이하일 경우.
				} else {
					$("#header_notice_cnt").hide();
					$("#header_notice_cnt").text("");
				}
				
				$("#header_form input[name='header_interval_clear']").val(data.result); // 정상 리턴값.
				
			// 헤더_알림 수가 null일 경우.
			} else if(data.result == 0) {
				$("#header_notice_cnt").show();
				$("#header_notice_cnt").text("#");
				
				$("#header_form input[name='before_noticecnt']").val(0);
				$("#header_form input[name='current_noticecnt']").val(0);
				
				$("#header_form input[name='header_interval_clear']").val(data.result); // 비정상 리턴값.
				
			// 세션 만료인 경우.
			} else {
				$("#header_notice_cnt").show();
				$("#header_notice_cnt").text("#");
				
				$("#header_form input[name='before_noticecnt']").val(0);
				$("#header_form input[name='current_noticecnt']").val(0);
				
				$("#header_form input[name='header_interval_clear']").val(data.result); // 비정상 리턴값.
			}
		},
		error : function(request, status, error) {
			$("#header_notice_cnt").show();
			$("#header_notice_cnt").text("#");
			
			$("#header_form input[name='before_noticecnt']").val(0);
			$("#header_form input[name='current_noticecnt']").val(0);
				
			$("#header_form input[name='header_interval_clear']").val(-2); // 비정상 리턴값.
		},
		/*dataType : "json",
		timeOut : 120000,
		complete : setTimeout(function() { headerSelectNoticeCnt(); }, 5000)*/
	});
}

// 헤더_알림 내용 조회 function.
function headerSelectNoticeContent() {
	$.ajax({
		type : "post",
		url : "headerselect_noticecontent",
		dataType : "json",
		cache : false,
		success : function(data) {
			var header_ctm_html = "";
			
			// 헤더_알람 내용가 null이 아니고 값이 비어있지 않을 경우
			if(data.result == 1) {
				for(var i = 0 ; i < data.header_select_noticecontent.length ; i++) {
					var notice_index = data.header_select_noticecontent[i].notice_index;
					var notice_no = data.header_select_noticecontent[i].notice_no;
					
					// 팔로우 알람일 경우.
					if(notice_index.toUpperCase().indexOf("F2".toUpperCase()) != -1) {
						var bm_nick = data.header_select_noticecontent[i].bm_nick + "님이 팔로우 하였습니다.";
						
					// 좋아요 알람일 경우.
					} else if(notice_index.toUpperCase().indexOf("FL2".toUpperCase()) != -1) {
						var bm_nick = data.header_select_noticecontent[i].bm_nick + "님이 회원님의 게시글에 좋아요 표시를 했습니다.";
						
					// 댓글 알람일 경우.
					} else if(notice_index.toUpperCase().indexOf("CMT2".toUpperCase()) != -1) {
						var bm_nick = data.header_select_noticecontent[i].bm_nick + "님이 회원님의 게시글에 댓글을 달았습니다.";
						
					// 답글 알람일 경우.
					} else if(notice_index.toUpperCase().indexOf("CMTANS2".toUpperCase()) != -1) {
						var bm_nick = data.header_select_noticecontent[i].bm_nick + "님이 회원님의 댓글에 답글을 달았습니다.";
						
					} else {}
					
					var notice_date = data.header_select_noticecontent[i].notice_date;
					
					header_ctm_html += "<div id=" + notice_no + " class=\"header_ctm_content\" style=\"font-weight: 400;\">" + bm_nick;
					header_ctm_html += "	<div style=\"font-size: 14px; font-weight: 400; color: #767676;\">" + notice_date + "</div>";
					header_ctm_html += "</div>";
				}
				
				$("#header_ctm_popup").html(header_ctm_html);
				
			} else if(data.result == 0) {
				header_ctm_html += "<div class=\"header_ctm_content\" style=\"font-weight: 700; text-align: center;\">알림이 없습니다.</div>";
				
				$("#header_ctm_popup").html(header_ctm_html);
				
			} else if(data.result == -1) {
				header_ctm_html += "<div class=\"header_ctm_content\" style=\"font-weight: 700; text-align: center;\">";
				header_ctm_html += "	로그아웃 되어 알림을 받을 수 없습니다.";
				header_ctm_html += "	<div style=\"font-size: 14px; font-weight: 400; text-align: left;\">다시 로그인 해주시기 바랍니다.</div>";
				header_ctm_html += "</div>";
				
				$("#header_ctm_popup").html(header_ctm_html);
				
			} else {
				header_ctm_html += "<div class=\"header_ctm_content\" style=\"font-weight: 700; text-align: center;\">";
				header_ctm_html += "	알림에 문제가 발생하였습니다.";
				header_ctm_html += "	<div style=\"font-size: 14px; font-weight: 400; text-align: left;\">오른쪽 하단에 ? 클릭하여 관리자에게 문의해주시기 바랍니다.</div>";
				header_ctm_html += "</div>";
				
				$("#header_ctm_popup").html(header_ctm_html);
			}
			
		},
		error : function(request, status, error) {
			var header_ctm_html = "";
			
			header_ctm_html += "<div class=\"header_ctm_content\" style=\"font-weight: 700; text-align: center;\">";
			header_ctm_html += "	알림에 문제가 발생하였습니다.";
			header_ctm_html += "	<div style=\"font-size: 14px; font-weight: 400; text-align: left;\">오른쪽 하단에 ? 클릭하여 관리자에게 문의해주시기 바랍니다.</div>";
			header_ctm_html += "</div>";
			
			$("#header_ctm_popup").html(header_ctm_html);
		}
	});
}