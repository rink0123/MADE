/**
 * 메인 게시글 상세보기_삽입 function
 */

// ============================= 메인 게시글_상세보기 신고 삽입 function =============================
function feedViewInsert_Report() {
	var feedview_form = $("#feedview_form").serialize();
	
	$.ajax({
		type : "post",
		url : "feedviewinsert_report",
		dataType : "json",
		data : feedview_form,
		cache : false,
		success : function(data) {
			// 신고 삽입 성공 시
			if(data.feedView_insert_report == 1) {
				swal("신고 접수되었습니다.", "", "success");
				
			// 신고 삽입 실패 시
			} else if(data.feedView_insert_report == 0) {
				swal("신고 접수가 안됐습니다.", "오른쪽 하단에 ? 클릭하여 관리자에게 문의해주시기 바랍니다.", "warning"); 
			}
		},
		error : function(request, status, error) {
			swal("신고 접수에 문제가 발생하였습니다.", "오른쪽 하단에 ? 클릭하여 관리자에게 문의해주시기 바랍니다.", "error");
		}
	});
}

// ============================= 메인 게시글_상세보기 좋아요 삽입 function =============================
function feedViewInsert_Like() {
	var feedview_form = $("#feedview_form").serialize();
	
	$.ajax({
		type : "post",
		url : "feedviewinsert_like",
		dataType : "json",
		data : feedview_form,
		cache : false,
		success : function(data) {
			// 좋아요 삽입 성공 시
			if(data.feedview_insert_like == 1) {
				feedViewSelect_Like(); // 좋아요 조회 function
				
			// 좋아요 삽입 실패 시
			} else if(data.feedview_insert_like == 0) {
				swal("좋아요가 되지 않았습니다.", "오른쪽 하단에 ? 클릭하여 관리자에게 문의해주시기 바랍니다.", "warning"); 
			}
		},
		error : function(request, status, error) {
			swal("좋아요에 문제가 발생하였습니다.", "오른쪽 하단에 ? 클릭하여 관리자에게 문의해주시기 바랍니다.", "error");
		}
	});
}

// ============================= 메인 게시글_상세보기 팔로우 삽입 function =============================
function feedViewInsert_Follow() {
	var feedview_form = $("#feedview_form").serialize();
	
	$.ajax({
		type : "post",
		url : "feedviewinsert_follow",
		dataType : "json",
		data : feedview_form,
		cache : false,
		success : function(data) {
			// 팔로우 삽입 성공 시
			if(data.feedview_insert_follow == 1) {
				feedViewSelect_Follow(); // 팔로우 조회 function
				
			// 팔로우 삽입 실패 시
			} else if(data.feedview_insert_follow == 0) {
				swal("팔로잉이 되지 않았습니다.", "오른쪽 하단에 ? 클릭하여 관리자에게 문의해주시기 바랍니다.", "warning");
			}
		},
		error : function(request, status, error) {
			swal("팔로우에 문제가 발생하였습니다.", "오른쪽 하단에 ? 클릭하여 관리자에게 문의해주시기 바랍니다.", "error");
		}
	});
}

// ============================= 메인 게시글_상세보기 사진 삽입 function =============================
function feedInsert_Image(fileName, dateformat, fileType) {
	var fileName = encodeURIComponent(fileName);
	var fileType = fileType;
	var dateformat = dateformat;
	
	var fi_img = fileName + dateformat + fileType;
	$("#feedview_form input[name='fi_file']").val(fi_img);
	
	var feedview_form = $("#feedview_form").serialize();
	
	$.ajax({
		type : "post",
		url : "feedviewinsert_image",
		dataType : "json",
		data : feedview_form,
		cache : false,
		success : function(data) {
			swal.close();
			
			// 삽입 성공 시
			if(data.feedview_insert_image == 1) {
				swal("사진이 저장되었습니다.", "", "success");
				$("#feedview_img_fileupload").val("");
				
				feedViewSelect_Image(); // 메인 게시글_상세보기_이미지 조회 function.
				
			// 삽입 실패 시
			} else if(data.feedview_insert_image == 0) {
				$("#feedview_img_fileupload").val("");
				
				swal("사진 추가가 안되었습니다.", "오른쪽 하단에 ? 클릭하여 관리자에게 문의해주시기 바랍니다.", "error");
			}
		},
		error : function(request, status, error) {
			swal.close();
			
			$("#feedview_img_fileupload").val("");
			
			swal("사진 추가에 문제가 발생하였습니다.", "오른쪽 하단에 ? 클릭하여 관리자에게 문의해주시기 바랍니다.", "error");
		}
	});
}

// ============================= 메인 게시글_추가 사진 삽입 function =============================
function addFeedInsert_Image() {
	var addfeed_form = $("#addfeed_form").serialize();
	
	$.ajax({
		type : "post",
		url : "feedviewinsert_image",
		dataType : "json",
		data : addfeed_form,
		cache : false,
		success : function(data) {
			swal.close();
			
			// 삽입 성공 시.
			if(data.feedview_insert_image == 1) {
				swal("저장되었습니다.", "", "success");
				$("#feedview_img_fileupload").val("");
				
				$("#addfeed_form").attr("action", "profile");
				$("#addfeed_form").submit();
				
			// 삽입 실패 시.
			} else if(data.feedview_insert_image == 0) {
				$("#feedview_img_fileupload").val("");
				
				swal("저장하는 중 문제가 발생하였습니다.", "오른쪽 하단에 ? 클릭하여 관리자에게 문의해주시기 바랍니다.", "error");
			}
		},
		error : function(request, status, error) {
			$("#feedview_img_fileupload").val("");
			
			swal.close();
			swal("저장하는 중 문제가 발생하였습니다.", "오른쪽 하단에 ? 클릭하여 관리자에게 문의해주시기 바랍니다.", "error");
		}
	});
}

// ============================= 메인 게시글_추가 글내용 삽입 function =============================
function addFeedInsert_Content(fileName, dateformat, fileType) {
	var fileName = encodeURIComponent(fileName);
	var fileType = fileType;
	var dateformat = dateformat;
	var fi_file = fileName + dateformat + fileType;
	
	var addfeed_form = $("#addfeed_form").serialize();
	
	$.ajax({
		type : "post",
		url : "feedviewinsert_content",
		dataType : "json",
		data : addfeed_form,
		cache : false,
		success : function(data) {
			// 삽입 성공 시.
			if(data.feedview_insert_content == 1) {
				// 체크값이 null이 아닐 경우.
				if(data.feedview_content_chk != null) {
					var feed_no = data.feedview_content_chk.feed_no;
					
					$("#addfeed_form input[name='feed_no']").val(feed_no);
					$("#addfeed_form input[name='fi_file']").val(fi_file);
					
					addFeedInsert_Image(); // 메인 게시글_추가 사진 삽입 function.
					
				// 체크값이 null일 경우.
				} else {
					swal("저장하는 중 문제가 발생하였습니다.", "오른쪽 하단에 ? 클릭하여 관리자에게 문의해주시기 바랍니다.", "error");
				}
				
			// 삽입 실패 시.
			} else if(data.feedview_insert_content == 0) {
				swal("저장하는 중 문제가 발생하였습니다.", "오른쪽 하단에 ? 클릭하여 관리자에게 문의해주시기 바랍니다.", "error");
			}
		},
		error : function(request, status, error) {
			swal("저장하는 중 문제가 발생하였습니다.", "오른쪽 하단에 ? 클릭하여 관리자에게 문의해주시기 바랍니다.", "error");
		}
	});
}

// ============================= 메인 게시글_상세보기 댓글 삽입 function =============================
function feedViewInsert_Comments() {
	var feedview_form = $("#feedview_form").serialize();
	
	$.ajax({
		type : "post",
		url : "feedviewinsert_comments",
		dataType : "json",
		data : feedview_form,
		cache : false,
		success : function(data) {
			// 댓글 삽입 성공 시.
			if(data.feedview_insert_comments == 1) {
				swal("댓글이 등록되었습니다.", "", "success");
				
				feedViewSelect_Comments(); // 댓글 조회 function
				
			// 댓글 삽입 실패 시.
			} else if(data.feedview_insert_comments == 0) {
				swal("댓글이 등록되지 않았습니다.", "오른쪽 하단에 ? 클릭하여 관리자에게 문의해주시기 바랍니다.", "warning");
			}
		},
		error : function(request, status, error) {
			swal("댓글 등록에 문제가 발생하였습니다.", "오른쪽 하단에 ? 클릭하여 관리자에게 문의해주시기 바랍니다.", "error");
		}
	});
}

// ============================= 메인 게시글_상세보기 댓글-답글 삽입 function =============================
function feedViewInsert_CmtAnswer() {
	var feedview_form = $("#feedview_form").serialize();
	
	$.ajax({
		type : "post",
		url : "feedviewinsert_cmtanswer",
		dataType : "json",
		data : feedview_form,
		cache : false,
		success : function(data) {
			// 댓글-답글 삽입 성공 시.
			if(data.feedview_insert_cmtanswer == 1) {
				swal("답글이 등록되었습니다.", "", "success");
				
				feedViewSelect_Comments(); // 댓글 조회 function.
				
			// 댓글-답글 삽입 실패 시.
			} else if(data.feedview_insert_cmtanswer == 0) {
				swal("답글이 등록되지 않았습니다.", "오른쪽 하단에 ? 클릭하여 관리자에게 문의해주시기 바랍니다.", "warning");
			}
		},
		error : function(request, status, error) {
			swal("답글 등록에 문제가 발생하였습니다.", "오른쪽 하단에 ? 클릭하여 관리자에게 문의해주시기 바랍니다.", "error");
		}
	});
}