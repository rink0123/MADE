/**
 * 프로필 정보_삽입 function.
 */

// ========================================= 프로필 정보 =========================================
// 팔로우 삽입 function.
function profileInsert_Follow() {
	var profile_form = $("#profile_form").serialize();
	var result = 0;
	
	$.ajax({
		type : "post",
		url : "profileinsert_follow",
		dataType : "json",
		data : profile_form,
		async : false,
		cache : false,
		success : function(data) {
			// 팔로우 삽입 성공 시.
			if(data.profile_insert_follow == 1) {
				result = 1;
				
			// 팔로우 삽입 실패 시.
			} else if(data.profile_insert_follow == 0) {
				swal("팔로우가 되지 않았습니다.", "오른쪽 하단에 ? 클릭하여 관리자에게 문의해주시기 바랍니다.", "warning");
			}
		},
		error : function(request, status, error) {
			swal("팔로우에 문제가 발생하였습니다.", "오른쪽 하단에 ? 클릭하여 관리자에게 문의해주시기 바랍니다.", "warning");
		}
	});
	
	return result;
}