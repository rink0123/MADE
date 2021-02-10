/**
 * 헤더_삽입 function.
 */

// 헤더_1:1문의 삽입 function.
function headerInsert_Complain() {
	var header_form = $("#header_form").serialize();
	
	var result = 0;
	
	$.ajax({
		type : "post",
		url : "headerinsert_complain",
		dataType : "json",
		data : header_form,
		async : false,
		cache : false,
		success : function(data) {
			// 1:1문의 삽입 성공 시.
			if(data.header_insert_complain == 1) {
				result = 1;
				
			// 1:1문의 삽입 실패 시.
			} else if(data.header_insert_complain == 0) {
				result = 0;
			}
		},
		error : function(request, status, error) {
			result = -1;
		}
	});
	
	return result;
}