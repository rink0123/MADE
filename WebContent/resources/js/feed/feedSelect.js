/**
 * 메인 게시글 목록
 */

// 메인 게시글_목록 조회 function.
function feedSelect_List() {
	var feed_form = $("#feed_form").serialize();
	
	$.ajax({
		type : "post",
		url : "feedselect_list",
		dataType : "json",
		data : feed_form,
		cache : false,
		success : function(data) {
			// 메인 게시글_목록이 null이 아니고 값이 비어있지 않은 경우.
			if(data.result == 1) {
				var feed_card = "";

				var feed_length = data.feedselect_list.length;
				
				// 메인 게시글_목록 row수가 30초과일 경우 30으로 조정.
				if(feed_length > 30) {
					feed_length = 30;
					
				// 메인 게시글_목록 row수가 30이상일 경우 유지.
				} else {
					feed_length = feed_length;
				}
				
				for(var i = 0 ; i < feed_length ; i++) {
					var fi_no = data.feedselect_list[i].fi_no;
					var feed_no = data.feedselect_list[i].feed_no;
					var fi_path = data.feedselect_list[i].fi_path;
					var fi_file = data.feedselect_list[i].fi_file;
					
					var decode = decodeURIComponent(fi_file);
					var replace = decode.replace(" ", "_");
					
					// 중복제거
					var feed_no_chk = $(".feed_card_grid").find("#" + feed_no).attr("id");

					// Client에 활성화 된 메인 게시글 번호와 불러온 메인 게시글 번호가 일치하지 않을 경우
					if(feed_no_chk != feed_no) {
						feed_card += "<div id=" + feed_no + " class=\"feed_card\">";
						feed_card += "<img id=" + fi_no + " alt=" + decodeURIComponent(fi_file) + " src=" + fi_path + fi_file + " />";
						feed_card += "</div>";
					}
				}
				
				$(".feed_card_grid").append(feed_card);
				
			// 메인 게시글_목록이 null이고 값이 비어있는 경우 검색 내용 초기화 된 상태로 다시 호출.	
			} else {
				$("#feed_form input[name='feed_search']").val("");
				$("#header_form input[name='feed_search']").val("");
				
				feedSelect_List();
			}
		},
		error : function(request, status, error) {
			swal("게시글들을 불러오는 과정에서 문제가 발생하였습니다.", "오른쪽 하단에 ? 클릭하여 관리자에게 문의해주시기 바랍니다.", "error");
		}
	});
}