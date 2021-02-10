/**
 * 메인 게시글 상세보기_삭제 function
 */

// ============================= 메인 게시글_상세보기 이미지 삭제(단일) function =============================
function feedViewDelete_Image(fi_no) {
	$("#feedview_form input[name='fi_no']").val(fi_no);
	
	var feedview_form = $("#feedview_form").serialize();
	
	$.ajax({
		type: "post",
		url: "feedviewdelete_image",
		dataType: "json",
		data: feedview_form,
		cache : false,
		success: function(data) {
			// 메인 이미지_삭제 성공 시.
			if(data.feedview_delete_image > 0) {
				swal("사진이 삭제되었습니다.", "", "success");
				
				feedViewSelect_Image(); // 사진 조회 function.
				
			// 메인 이미지_삭제 실패 시.
			} else {
				swal("사진을 삭제하는 중 문제가 발생하였습니다.", "오른쪽 하단에 ? 클릭하여 관리자에게 문의해주시기 바랍니다.", "error");
				$("#feedview_form input[name='fi_no']").val("");
			}
		},
		error: function(request, status, error) {
			swal("서버에 문제가 발생하였습니다.", "오른쪽 하단에 ? 클릭하여 관리자에게 문의해주시기 바랍니다.", "error");
		},
		complete : function() {
			$("#feedview_form input[name='fi_no']").val("");
		}
	});
}

// ============================= 메인 게시글_상세보기 좋아요 삭제 function =============================
function feedViewDelete_Like() {
	var feedview_form = $("#feedview_form").serialize();
	
	$.ajax({
		type : "post",
		url : "feedviewdelete_like",
		dataType : "json",
		data : feedview_form,
		cache : false,
		success : function(data) {
			// 좋아요 삭제 성공 시
			if(data.feedview_delete_like != 0) {
				feedViewSelect_Like(); // 좋아요 조회 function
				
			// 좋아요 삭제 실패 시
			} else {
				swal("좋아요 해제가 안됐습니다.", "오른쪽 하단에 ? 클릭하여 관리자에게 문의해주시기 바랍니다.", "warning");
			}
		},
		error : function(request, status, error) {
			swal("좋아요 해제에 문제가 발생했습니다.", "오른쪽 하단에 ? 클릭하여 관리자에게 문의해주시기 바랍니다.", "error");
		}
	});
}

// ============================= 메인 게시글_상세보기 글내용 삭제 function =============================
function feedViewDelete_Content() {
	var feedview_form = $("#feedview_form").serialize();
	
	$.ajax({
		type : "post",
		url : "feedviewdelete_content",
		dataType : "json",
		data : feedview_form,
		cache : false,
		success : function(data) {
			// 글내용 삭제 성공 시.
			if(data.feedview_delete_content == -1) {
				swal("정상적으로 삭제되었습니다.", "", "success");
				
				$("#feedview_form").attr("action", "profile");
				$("#feedview_form").submit();
				
			// 글내용 삭제 실패 시.
			} else {
				swal("글 내용 삭제가 안됐습니다.", "오른쪽 하단에 ? 클릭하여 관리자에게 문의해주시기 바랍니다.", "warning");
			}
		},
		error : function(request, status, error) {
			swal("글 내용 삭제에 문제가 발생했습니다.", "오른쪽 하단에 ? 클릭하여 관리자에게 문의해주시기 바랍니다.", "error");
		}
	});
}

// ============================= 메인 게시글_상세보기 팔로우 삭제 function =============================
function feedViewDelete_Follow() {
	var feedview_form = $("#feedview_form").serialize();
	
	$.ajax({
		type : "post",
		url : "feedviewdelete_follow",
		dataType : "json",
		data : feedview_form,
		cache : false,
		success : function(data) {
			// 팔로우 삭제 성공 시
			if(data.feedview_delete_follow == 1) {
				feedViewSelect_Follow(); // 팔로우 조회 function
				
			// 팔로우 삭제 실패 시
			} else if(data.feedview_delete_follow == 0) {
				swal("팔로우로 되지 않았습니다.", "오른쪽 하단에 ? 클릭하여 관리자에게 문의해주시기 바랍니다.", "warning");
			}
		},
		error : function(request, status, error) {
			swal("팔로잉에 문제가 발생하였습니다.", "오른쪽 하단에 ? 클릭하여 관리자에게 문의해주시기 바랍니다.", "error");
		}
	});
}

// ============================= 메인 게시글_상세보기 댓글 삭제 function =============================
function feedViewDelete_Comments() {
	var feedview_form = $("#feedview_form").serialize();
	
	$.ajax({
		type : "post",
		url : "feedviewdelete_comments",
		dataType : "json",
		data : feedview_form,
		cache : false,
		success : function(data) {
			// 댓글 삭제 성공 시
			if(data.feedview_delete_comments == 1) {
				swal("정상 삭제되었습니다.", "", "success");
				
				feedViewSelect_Comments(); // 댓글&댓글-답글 조회 function
				
			// 댓글 삭제 실패 시
			} else if(data.feedview_delete_comments == 0) {
				swal("댓글 삭제가 안됐습니다.", "오른쪽 하단에 ? 클릭하여 관리자에게 문의해주시기 바랍니다.", "warning");
			}
		},
		error : function(request, status, error) {
			swal("댓글 삭제에 문제가 발생하였습니다.", "오른쪽 하단에 ? 클릭하여 관리자에게 문의해주시기 바랍니다.", "warning");
		}
	});
}

// ============================= 메인 게시글_상세보기 댓글-답글 삭제 function =============================
function feedViewDelete_CmtAnswer() {
	var feedview_form = $("#feedview_form").serialize();
	
	$.ajax({
		type : "post",
		url : "feedviewdelete_cmtanswer",
		dataType : "json",
		data : feedview_form,
		cache : false,
		success : function(data) {
			// 댓글 삭제 성공 시
			if(data.feedview_delete_cmtanswer == 1) {
				swal("정상 삭제되었습니다.", "", "success");
				
				feedViewSelect_Comments(); // 댓글&댓글-답글 조회 function
				
			// 댓글 삭제 실패 시
			} else if(data.feedview_delete_cmtanswer == 0) {
				swal("답글 삭제가 안됐습니다.", "오른쪽 하단에 ? 클릭하여 관리자에게 문의해주시기 바랍니다.", "warning");
			}
		},
		error : function(request, status, error) {
			swal("답글 삭제에 문제가 발생하였습니다.", "오른쪽 하단에 ? 클릭하여 관리자에게 문의해주시기 바랍니다.", "warning");
		}
	});
}