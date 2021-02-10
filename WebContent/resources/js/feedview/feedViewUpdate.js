/**
 * 메인 게시글 상세보기_갱신 function
 */

// ============================= 메인 게시글_상세보기 글내용 수정 function =============================
function feedViewUpdate_Content() {
	var feedview_form = $("#feedview_form").serialize();
	
	$.ajax({
		type : "post",
		url : "feedviewupdate_content",
		dataType : "json",
		data : feedview_form,
		cache : false,
		success : function(data) {
			// 글내용 수정 성공 시.
			if(data.feedview_update_content != 0) {
				swal("정상 수정되었습니다.", "", "success");
				
				feedViewSelect_Content(); // 글내용 조회 function
				
			// 글내용 수정 실패 시.
			} else {
				swal("글 수정이 안됐습니다.", "오른쪽 하단에 ? 클릭하여 관리자에게 문의해주시기 바랍니다.", "warning"); 
			}
		},
		error : function(request, status, error) {
			swal("글 수정에 문제가 발생하였습니다.", "오른쪽 하단에 ? 클릭하여 관리자에게 문의해주시기 바랍니다.", "warning");
		}
	});
}

// ============================= 메인 게시글_상세보기 댓글 수정 function =============================
function feedViewUpdate_Comments() {
	var feedview_form = $("#feedview_form").serialize();
	
	$.ajax({
		type : "post",
		url : "feedviewupdate_comments",
		dataType : "json",
		data : feedview_form,
		cache : false,
		success : function(data) {
			// 댓글 수정 성공 시.
			if(data.feedview_update_comments == 1) {
				swal("정상 수정되었습니다.", "", "success");
				
				feedViewSelect_Comments(); // 댓글 조회 function
				
			// 댓글 수정 실패 시.
			} else if(data.feedview_update_comments == 0) {
				swal("댓글 수정이 안됐습니다.", "오른쪽 하단에 ? 클릭하여 관리자에게 문의해주시기 바랍니다.", "warning"); 
			}
		},
		error : function(request, status, error) {
			swal("댓글 수정에 문제가 발생하였습니다.", "오른쪽 하단에 ? 클릭하여 관리자에게 문의해주시기 바랍니다.", "warning");
		}
	});
}

// ============================= 메인 게시글_상세보기 댓글-답글 수정 function =============================
function feedViewUpdate_CmtAnswer() {
	var feedview_form = $("#feedview_form").serialize();
	
	$.ajax({
		type : "post",
		url : "feedviewupdate_cmtanswer",
		dataType : "json",
		data : feedview_form,
		cache : false,
		success : function(data) {
			// 댓글-답글 수정 성공 시.
			if(data.feedview_update_cmtanswer == 1) {
				swal("정상 수정되었습니다.", "", "success");
				
				feedViewSelect_Comments(); // 댓글 조회 function
				
			// 댓글-답글 수정 실패 시.
			} else if(data.feedview_update_cmtanswer == 0) {
				swal("답글 수정이 안됐습니다.", "오른쪽 하단에 ? 클릭하여 관리자에게 문의해주시기 바랍니다.", "warning"); 
			}
		},
		error : function(request, status, error) {
			swal("답글 수정에 문제가 발생하였습니다.", "오른쪽 하단에 ? 클릭하여 관리자에게 문의해주시기 바랍니다.", "warning");
		}
	});
}