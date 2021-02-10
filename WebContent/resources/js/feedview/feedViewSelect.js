/**
 * 메인 게시글 상세보기_조회 function
 */

// ============================= 메인 게시글_상세보기_이미지 조회 function =============================
function feedViewSelect_Image() {
	var feedview_form = $("#feedview_form").serialize();
	
	$.ajax({
		type : "post",
		url : "feedviewselect_image",
		dataType : "json",
		data : feedview_form,
		cache : false,
		success : function(data) {
			var feedview_imgs_html = "";
			
			// 메인 게시글 이미지 데이터가 있을 경우. 
			if(data.result = 1) {
				for(var i = 0 ; i < data.feedview_select_image.length ; i++) {
					var fi_no = data.feedview_select_image[i].fi_no;
					var fi_path = data.feedview_select_image[i].fi_path;
					var fi_file = data.feedview_select_image[i].fi_file;
					
					var fi_file_decode = decodeURIComponent(fi_file);
					var fi_file_replace = fi_file_decode.replace(" ", "+");
					
					feedview_imgs_html += "<div id=\"feedview_imgs_box\" style=\"max-width: 500px; overflow: hidden;\">";
					feedview_imgs_html += "	<img id=" + fi_no + " style=\"max-width: 500px;\" alt=" + fi_file_replace + " src=" + fi_path + fi_file + " />";
					feedview_imgs_html += "</div>";
				}
				
				$("#feedview_imgs_container").removeClass("slick-initialized slick-slider slick-dotted");
				$("#feedview_imgs_container").empty();
				$("#feedview_imgs_container").html(feedview_imgs_html);
				
				$("#feedview_imgs_container").slick({
					slide: "div",       //슬라이드 되어야 할 태그 ex) div, li.
					infinite : true,    //무한 반복 옵션.
					slidesToShow : 1,   // 한 화면에 보여질 컨텐츠 개수.
					slidesToScroll : 1, //스크롤 한번에 움직일 컨텐츠 개수.
					speed : 200,        // 다음 버튼 누르고 다음 화면 뜨는데까지 걸리는 시간(ms).
					dots : true,        // 스크롤바 아래 점으로 페이지네이션 여부.
					autoplay : false,   // 자동 스크롤 사용 여부.
					// autoplaySpeed : 10000,   // 자동 스크롤 시 다음으로 넘어가는데 걸리는 시간 (ms).
					pauseOnHover : true,   // 슬라이드 이동 시 마우스 호버하면 슬라이더 멈추게 설정.
					vertical : false,   // 세로 방향 슬라이드 옵션
					arrows : false,   // 옆으로 이동하는 화살표 표시 여부.
					// prevArrow : "<button type='button' class='slick-prev'>Previous</button>",   // 이전 화살표 모양 설정.
					// nextArrow : "<button type='button' class='slick-next'>Next</button>",   // 다음 화살표 모양 설정.
					dotsClass : "slick-dots",   //아래 나오는 페이지네이션(점) css class 지정.
					draggable : true,   //드래그 가능 여부.	
				});
				
				// 비동기로 작동하기에 slick 선언 후 레이아웃 구성 중 배치해버리는 문제를 해결하기 위해 setInterval() 사용.
				var interval = setInterval(function() {
					// slick 레이아웃 구성이 완료되었을 경우 setInterval() 중지.
					if($("#feedview_imgs_container .slick-track").width() != 0) {
						clearInterval(interval);
						
					// slick 레이아웃 구성이 미완료되었을 경우 setInterval() 실행.
					} else {
						$("#feedview_imgs_container").slick("refresh");
					}
				}, 100);
				
			// 메인 게시글 이미지 데이터가 없을 경우. 
			} else {
				swal("사진을 불러오지 못했습니다.", "오른쪽 하단에 ? 클릭하여 관리자에게 문의해주시기 바랍니다.", "warning");
			}
		},
		error : function(request, status, error) {
			swal("사진을 불러오는 과정에서 문제가 발생하였습니다.", "오른쪽 하단에 ? 클릭하여 관리자에게 문의해주시기 바랍니다.", "error");
		}
	});
}

// ============================= 메인 게시글_상세보기_좋아요 조회 function =============================
function feedViewSelect_Like() {
	var feedview_form = $("#feedview_form").serialize();
	
	$.ajax({
		type : "post",
		url : "feedviewselect_like",
		dataType : "json",
		data : feedview_form,
		cache : false,
		success : function(data) {
			
			if(data.result == 1) {
				// 좋아요 되어 있는 상태면.
				if(data.feedview_select_like.fl_chk != 0) {
					$("#feedview_like_img").attr("src", "resources/images/etc/feedview_onlike.png");
					$("#feedview_like_img").attr("alt", "onlike");
					
					$("#feedview_form input[name='fl_no']").val(data.feedview_select_like.fl_no);
					
				// 좋아요 안 되어 있는 상태면.
				} else {
					$("#feedview_like_img").attr("src", "resources/images/etc/feedview_offlike.png");
					$("#feedview_like_img").attr("alt", "offlike");
				}
				
			} else if(data.result == 0) {
				swal("좋아요를 불러오지 못했습니다.", "오른쪽 하단에 ? 클릭하여 관리자에게 문의해주시기 바랍니다.", "warning");
			}
		},
		error : function(request, status, error) {
			swal("좋아요를 불러오는 과정에서 문제가 발생하였습니다.", "오른쪽 하단에 ? 클릭하여 관리자에게 문의해주시기 바랍니다.", "error");
		}
	});
}

// ============================= 메인 게시글_상세보기 글내용 조회 function =============================
function feedViewSelect_Content() {
	
	var feedview_form = $("#feedview_form").serialize();
	$.ajax({
		type : "post",
		url : "feedviewselect_content",
		dataType : "json",
		data : feedview_form,
		cache : false,
		success : function(data) {
			// 메인 게시글 글내용 데이터가 있을 경우.
			if(data.result = 1) {
				var feed_type_cd = data.feedview_select_content.feed_type_cd;
				var feed_title = data.feedview_select_content.feed_title;
				var bm_no = data.feedview_select_content.bm_no;
				var feed_content = data.feedview_select_content.feed_content;
				var bm_img = encodeURIComponent(data.feedview_select_content.bm_img);
				var bm_nick = data.feedview_select_content.bm_nick;
				$("#feedview_form input[name='feed_type_cd']").val(feed_type_cd); // 메인 타입
				$("#feedview_form input[name='bm_no']").val(bm_no); // 메인 게시글 작성자(회원번호)
				$("#feedview_profile .feedview_photo").attr("id", bm_no); // 메인 게시글 프로필 사진
				
				$("#feedview_right_title").text(feed_title); // 메인 게시글 제목
				$(".feedview_nick_txt").text(bm_nick); // 프로필 닉네임
				$(".feedview_nick_txt").attr("id", bm_no); // 프로필 닉네임
					
				// 메인 게시글 내용이 null이 아니거나 길이가 0 초과일 때.
				if(feed_content != null && feed_content.length > 0) {
					$("#feedview_right_content").text(feed_content); // 메인 게시글 내용
				
				// 메인 게시글 내용이 null이고 길이가 0 미만일 때.
				} else {
					$("#feedview_right_content").text(""); // 메인 게시글 내용
				}
				
				// 프로필 사진이 있을 경우 해당 사진 표시.
				if(data.feedview_select_content.bm_img != null) {
					$("#feedview_feedview_img").attr("attr", bm_img);
				
				// 프로필 사진이 없을 경우 non_profile 사진 표시.	
				} else {
					$("#feedview_feedview_img").attr("attr", "resources/images/etc/non_profile.png");
				}
				
				// 작성자(회원번호)와 session 회원 번호가 일치하지 않을경우.
				if($("#feedview_form input[name='session_bm_no']").val() != bm_no) {
					$("#feedview_update_btn").remove();
				}
				
				// 자기글일 경우 사진 사진 추가 버튼, 삭제 버튼 활성화
				if($("#feedview_form input[name='session_bm_no']").val() == bm_no) {
					// 사진 삭제 버튼
					var feedview_deleteimg_html = "";
					
					feedview_deleteimg_html += "<div id=\"feedview_img_deletebtn\" class=\"feedview_img_deletebtn\">";
					feedview_deleteimg_html += "	<img src=\"resources/images/etc/feedview_delete.png\" width=\"20px\" />";
					feedview_deleteimg_html += "</div>";
					
					$("#feedview_img_deletebtn_box").html(feedview_deleteimg_html);
					
					// 사진 추가 버튼
					var feedview_insertimg_html = "";
					
					feedview_insertimg_html += "<div style=\"display: flex; flex-direction: row; padding: 16px 0 32px 0;\">";
					feedview_insertimg_html += "	<div style=\"text-align: left; margin-right: 4px; font-size: 14px; font-weight: 400; color: #111111; width: 80%;\">";
					feedview_insertimg_html += "		사진을 추가하여 시도해 본 경험을 공유해 주세요.<br/>권장사양: 20MB 이하 고화질 .jfif, .pjpeg, .jpeg, .pjp, .jpg 파일";
					feedview_insertimg_html += "	</div>";
					feedview_insertimg_html += "	<div>";
					feedview_insertimg_html += "		<input type=\"button\" id=\"feedview_addphoto\" class=\"feedview_addphoto\" value=\"사진 추가\">";
					feedview_insertimg_html += "		<input type=\"file\" accept=\"image/jpeg\" size=\"20971520\" id=\"feedview_img_fileupload\" style=\"display: none;\"/>";
					feedview_insertimg_html += "	</div>";
					feedview_insertimg_html += "</div>";
					
					$("#feedview_addphoto_box").html(feedview_insertimg_html);
					
				// 자기글이 아닐 경우 사진 삭제 버튼, 추가 버튼 비활성화
				} else {
					$("#feedview_img_deletebtn_box").empty();
					$("#feedview_addphoto_box").empty();
				}
				
			// 메인 게시글 이미지 데이터가 없을 경우.
			} else {
				swal("글 내용을 불러오지 못했습니다.", "오른쪽 하단에 ? 클릭하여 관리자에게 문의해주시기 바랍니다.", "warning");
			}
		},
		error : function(request, status, error) {
			swal("글 내용을 불러오는 과정에서 문제가 발생하였습니다.", "오른쪽 하단에 ? 클릭하여 관리자에게 문의해주시기 바랍니다.", "error");
		}
	});
}

// ============================= 메인 게시글_상세보기 팔로우 조회 function =============================
function feedViewSelect_Follow() {
	var feedview_form = $("#feedview_form").serialize();
	
	$.ajax({
		type : "post",
		url : "feedviewselect_follow",
		dataType : "json",
		data : feedview_form,
		cache : false,
		success : function(data) {
			if(data.result == 1) {
				var follow_chk = data.feedview_select_follow.follow_chk;
				var session_bm_no = $("#feedview_form input[name='session_bm_no']");
				var bm_no = $("#feedview_form input[name='bm_no']");
				
				if(session_bm_no.val() == bm_no.val()) {
					$("#feedview_follow_btn").remove();
					
				} else if(follow_chk == 1) {
					$("#feedview_form input[name='follow_no']").val(data.feedview_select_follow.follow_no);
					
					$("#feedview_follow_btn").text("팔로잉");
					$("#feedview_follow_btn").show();
					
				} else if(follow_chk == 0) {
					$("#feedview_form input[name='follow_no']").val("");
					
					$("#feedview_follow_btn").text("팔로우");
					$("#feedview_follow_btn").show();
				}
				
				$("#feedview_follower_cnt").text($.trim(data.feedview_select_follow.follower_cnt) + "명");
				
			} else if(data.result == 0) {
				swal("팔로워 및 팔로우를 불러오지 못했습니다.", "오른쪽 하단에 ? 클릭하여 관리자에게 문의해주시기 바랍니다.", "warning");
			}
		},
		error : function(request, status, error) {
			swal("팔로워 및 팔로우를 불러오는 과정에서 문제가 발생하였습니다.", "오른쪽 하단에 ? 클릭하여 관리자에게 문의해주시기 바랍니다.", "error");
		}
	});
}

// ============================= 메인 게시글_상세보기 댓글, 댓글-답변 조회 function =============================
function feedViewSelect_Comments() {
	var feedview_form = $("#feedview_form").serialize();
	
	$.ajax({
		type : "post",
		url : "feedviewselect_comments",
		dataType : "json",
		data : feedview_form,
		cache : false,
		success : function(data) {
			var feedview_comments_html = "";
			
			// 메인 게시글 댓글, 댓글-답변 데이터가 있을 경우.
			if(data.cmt_result == 1) {
				$("#feedview_comments_cnt").text("댓글 " + data.feedView_select_comments[0].cmt_cnt + "개");
				
				// 댓글 추가.
				for(var i = 0 ; i < data.feedView_select_comments.length ; i++) {
					var cmt_no = data.feedView_select_comments[i].cmt_no;
					var cmt_bm_no = data.feedView_select_comments[i].cmt_bm_no;
					var cmt_bm_nick = data.feedView_select_comments[i].cmt_bm_nick;
					
					if(data.feedView_select_comments[i].cmt_bm_img != null) {
						var cmt_bm_img = data.feedView_select_comments[i].cmt_bm_img;
					} else {
						var cmt_bm_img = "resources/images/etc/non_profile.png";
					}
					
					var cmt_content = data.feedView_select_comments[i].cmt_content;
					var cmt_updatedate = data.feedView_select_comments[i].cmt_updatedate;
					
					feedview_comments_html += "<div id=" + cmt_no + " class=\"feedview_comments_box\" style=\"margin-bottom: 8px; text-align: left;\">";
												// 프로필 사진
					feedview_comments_html += "	<div id=" + cmt_bm_no + " class=\"feedview_photo\" style=\"vertical-align: top; margin-right: 10px; width: 48px; height: 48px; cursor: pointer;\">";
					feedview_comments_html += "		<img class=\"feedview_img\" src=" + cmt_bm_img + " style=\"border-radius: 50%;\"/>";
					feedview_comments_html += "	</div>";
												// 댓글 내용_박스
					feedview_comments_html += "	<div class=\"feedview_comments_txtbox\">";
					feedview_comments_html += "		<div style=\"text-align: left;\">";
														// 댓글 내용_작성자 닉네임
					feedview_comments_html += "			<div id=" + cmt_bm_no + " class=\"feedview_comments_writer\" style=\"font-weight: 700; color: #111111;\">" + cmt_bm_nick + "</div>";
														// 댓글 내용_작성자 수정일
					feedview_comments_html += "			<div class=\"feedview_comments_writer\" style=\"font-weight: 400; color: #767676; padding-left: 8px;\">" + cmt_updatedate + "</div>";
														// 등록한 댓글 내용
					feedview_comments_html += "			<textarea class=\"feedview_comments_txt\" spellcheck=\"false\" maxlength=\"300\" placeholder=\"300자 이내\" readonly=\"readonly\">" + cmt_content + "</textarea>";
					feedview_comments_html += "			<div id=\"cmt_content_save\" style=\"display: none;\">" + cmt_content + "</div>";
					feedview_comments_html += "		</div>";
					feedview_comments_html += "	</div>";
					feedview_comments_html += "	<div class=\"feedview_comments_imgbtnbox\" style=\"padding: 5px 5px 5px 58px;\">";
					
					if($("#feedview_form input[name='session_bm_no']").val() != "") {
														// 등록한 댓글 내용_답글 버튼
						feedview_comments_html += "		<img class=\"feedview_comments_replybtn\" width=\"16px;\" height=\"16px\" style=\"padding: 8px;\" src=\"resources/images/etc/feedview_reply.png\" />";
					}
					
					if($("#feedview_form input[name='session_bm_no']").val() != "" && $("#feedview_form input[name='session_bm_no']").val() == cmt_bm_no) {
														// 등록한 댓글 내용_수정 버튼
						feedview_comments_html += "		<img class=\"feedview_comments_updatebtn\" width=\"16px;\" height=\"16px\" style=\"padding: 8px;\" src=\"resources/images/etc/feedview_update.png\" />";
														// 등록한 댓글 내용_삭제 버튼
						feedview_comments_html += "		<img class=\"feedview_comments_deletebtn\" width=\"16px;\" height=\"16px\" style=\"padding: 8px;\" src=\"resources/images/etc/feedview_delete.png\" />";
					}
					
					feedview_comments_html += "	</div>";
					feedview_comments_html += "</div>";
					
					for(var j = 0 ; j < data.feedView_select_cmtanswer.length ; j++) {
						var cmtans_cmt_no = data.feedView_select_cmtanswer[j].cmtans_cmt_no;
						var cmtans_no = data.feedView_select_cmtanswer[j].cmtans_no;
						var cmtans_bm_no = data.feedView_select_cmtanswer[j].cmtans_bm_no;
						var cmtans_bm_nick = data.feedView_select_cmtanswer[j].cmtans_bm_nick;
						if(data.feedView_select_cmtanswer[j].cmtans_bm_img != null) {
							var cmtans_bm_img = data.feedView_select_cmtanswer[j].cmtans_bm_img;
						} else {
							var cmtans_bm_img = "resources/images/etc/non_profile.png";
						}
						var cmtans_content = data.feedView_select_cmtanswer[j].cmtans_content;
						var cmtans_updatedate = data.feedView_select_cmtanswer[j].cmtans_updatedate;
						
						if(data.cmtans_result == 1 && cmtans_cmt_no == cmt_no) {
							feedview_comments_html += "<div id=" + cmtans_no + " class=\"feedview_comments_box\" style=\"margin-bottom: 8px; padding-left: 48px; text-align: left;\">";
														// 프로필 사진
							//feedview_comments_html += "	<img id=" + cmtans_bm_no + " class=\"feedview_comments_profileimg\" src=" + cmtans_bm_img + " style=\"vertical-align: top; border-radius: 50%; margin-right: 10px; width: 32px; height: 32px;\" />";
							feedview_comments_html += "	<div id=" + cmtans_bm_no + " class=\"feedview_photo\" style=\"vertical-align: top; margin-right: 10px; width: 32px; height: 32px; cursor: pointer;\">";
							feedview_comments_html += "		<img class=\"feedview_img\" src=" + cmtans_bm_img + " style=\"border-radius: 50%;\"/>";
							feedview_comments_html += "	</div>";
														// 댓글 내용_박스
							feedview_comments_html += "	<div class=\"feedview_comments_txtbox\" style=\"width: 326px;\">";
							feedview_comments_html += "		<div style=\"text-align: left;\">";
																// 댓글 내용_작성자 닉네임
							feedview_comments_html += "			<div id=" + cmtans_bm_no + " class=\"feedview_comments_writer\" style=\"font-weight: 700; color: #111111;\">" + cmtans_bm_nick + "</div>";
																// 댓글 내용_작성자 수정일
							feedview_comments_html += "			<div class=\"feedview_comments_writer\" style=\"font-weight: 400; color: #767676; padding-left: 8px;\">" + cmtans_updatedate + "</div>";
																// 등록한 댓글 내용
							feedview_comments_html += "			<textarea class=\"feedview_comments_txt\" spellcheck=\"false\" maxlength=\"300\" placeholder=\"300자 이내\" readonly=\"readonly\">" + cmtans_content + "</textarea>";
							feedview_comments_html += "			<div id=\"cmt_content_save\" style=\"display: none;\">" + cmtans_content + "</div>";
							feedview_comments_html += "		</div>";
							feedview_comments_html += "	</div>";
							feedview_comments_html += "	<div class=\"feedview_comments_imgbtnbox\" style=\"padding: 5px 5px 5px 58px;\">";
							
							if($("#feedview_form input[name='session_bm_no']").val() != "" && $("#feedview_form input[name='session_bm_no']").val() == cmt_bm_no) {
																// 등록한 댓글 내용_수정 버튼
								feedview_comments_html += "		<img class=\"feedview_comments_updatebtn\" width=\"16px;\" height=\"16px\" style=\"padding: 8px;\" src=\"resources/images/etc/feedview_update.png\" />";
																// 등록한 댓글 내용_삭제 버튼
								feedview_comments_html += "		<img class=\"feedview_comments_deletebtn\" width=\"16px;\" height=\"16px\" style=\"padding: 8px;\" src=\"resources/images/etc/feedview_delete.png\" />";
							}
							
							feedview_comments_html += "	</div>";
							feedview_comments_html += "</div>";
							
						} else if(data.cmtans_result == 0) {
							// No data.
						}
					}
				}
				
				$("#feedview_right_footer").html(feedview_comments_html);
				
			// 메인 게시글 댓글, 댓글-답변 데이터가 없을 경우.
			} else if(data.cmt_result == 0) {
				$("#feedview_comments_cnt").text("댓글 0개");
				
				$("#feedview_right_footer").html(feedview_comments_html);
			}
			
			// load 시 등록된 댓글 내용 textarea auto resize.
			$(".feedview_comments_txt").each(function() {
	            $(this).height(1).height($(this).prop("scrollHeight"));
	        });
		},
		error : function(request, status, error) {
			swal("댓글을 불러오는 과정에서 문제가 발생하였습니다.", "오른쪽 하단에 ? 클릭하여 관리자에게 문의해주시기 바랍니다.", "error");
		}
	});
}