/**
 * S3 파일 업로드, 파일 삭제 function.
 */

/*
 * S3 setting
 * 
 * Amazon Cognito 자격 증명 풀 ID를 제공하는 CognitoIdentityCredentials 메서드를 호출하여 SDK를 구성하는 데 필요한 자격 증명을 획득합니다.
 * 다음에는, AWS.S3 서비스 객체를 생성합니다.
 */
var albumBucketName = "made-s3"; // S3 Bucket 이름
var bucketRegion = "ap-northeast-2"; // 해당 Bucket의 리전
var IdentityPoolId = "ap-northeast-2:fea52fb1-3235-495e-a7f2-0b1984a8eb2f"; // 자격 증명 풀의 IdentityPoolId

AWS.config.update({
	region: bucketRegion,
	credentials: new AWS.CognitoIdentityCredentials({
		IdentityPoolId: IdentityPoolId
	})
});
var s3 = new AWS.S3({
	apiVersion: '2006-03-01',
	params: { Bucket: albumBucketName }
});

// S3 파일업로드(단일) function.
function addPhoto(inputfile_id, albumName) {
	// // jQuery 식.
	// console.log($(this));
	// console.log($(this)[0].files); // 파일.
	// console.log($(this)[0].files[0].name); // 파일명.파일확장자.
	// console.log($(this)[0].files.length); // 파일 배열 길이.
	// console.log($(this)[0].files[0].type); // 파일 타입.
	// console.log($(this)[0].files[0].size); // 파일 크기.
	
	// 파일 업로드 변수 설정.
	var files = document.getElementById(inputfile_id).files;
	var file = files[0]; // 파일
	var albumPhotosKey = encodeURIComponent(albumName) + "/"; // 파일 업로드 경로.
	var fileFullName = file.name; // 파일명.파일확장자.
	var fileName = fileFullName.substring(0, fileFullName.lastIndexOf(".")); // 파일명.
	var fileType = fileFullName.substring(fileFullName.lastIndexOf(".")); // 파일확장자.
	
	// 파일명 중복허용 변수 설정.
	var date = new Date();
	var yyyymmdd = date.getFullYear() + ('0'+(date.getMonth()+1)).slice(-2) + ('0'+(date.getDate())).slice(-2);
	var hhmissms = ('0'+(date.getHours())).slice(-2) + ('0'+(date.getMinutes())).slice(-2) + ('0'+(date.getSeconds())).slice(-2) + ("00"+(date.getMilliseconds())).slice(-3);
	var dateformat= "-" + yyyymmdd + hhmissms;

	// 파일업로드경로/ + 파일명 + -yyyymmddhhmissms + .파일확장자.
	var photoKey = albumPhotosKey + fileName + dateformat + fileType;

	// 다중 업로드를 지원하는 S3 ManagedUpload 클래스 사용.
	var upload = new AWS.S3.ManagedUpload({
		params: {
			Bucket: albumBucketName, // S3 버킷.
			Key: photoKey, // 파일 키.
			Body: file, // 파일.
			ACL: "public-read" // 파일 권한.
		}
	});

	/* 
	 * AWS.Request.promise 메서드는 서비스 작업을 호출하고 콜백을 사용하지 않고 비동기식 흐름을 관리하는 방법을 제공합니다.
	 * Node.js 및 브라우저 스크립트에서 AWS.Request 객체는 콜백 함수 없이 서비스 작업이 호출된 경우 반환됩니다.
	 * 요청의 send 메서드를 호출하여 서비스를 호출할 수 있습니다.
	 */
	var promise = upload.promise();
	
	/* 
	 * AWS.Request.promise는 서비스 호출을 즉시 시작하고 응답 data 속성을 사용하여 이행되었거나
	 * 응답 error 속성을 사용하여 거부된 promise를 반환합니다.
	 * promise를 사용하는 경우 단일 콜백이 오류 탐지를 담당하지 않습니다.
	 * 대신 요청 성공 또는 실패를 바탕으로 올바른 콜백이 호출됩니다.
	 */
	promise.then(
		function(data) {
			feedInsert_Image(fileName, dateformat, fileType); // 메인 게시글_상세보기 사진 삽입 function.
		},
		function(err) {
			swal("클라우드에 문제가 발생하였습니다.", "오른쪽 하단에 ? 클릭하여 관리자에게 문의해주시기 바랍니다.", "error");
		}
	);
}

// 글추가용 S3 파일업로드(단일) function.
function addfeedPhoto(inputfile_id, albumName) {
	// // jQuery 식.
	// console.log($(this));
	// console.log($(this)[0].files); // 파일.
	// console.log($(this)[0].files[0].name); // 파일명.파일확장자.
	// console.log($(this)[0].files.length); // 파일 배열 길이.
	// console.log($(this)[0].files[0].type); // 파일 타입.
	// console.log($(this)[0].files[0].size); // 파일 크기.
	
	// 파일 업로드 변수 설정.
	var files = document.getElementById(inputfile_id).files;
	var file = files[0]; // 파일
	var albumPhotosKey = encodeURIComponent(albumName) + "/"; // 파일 업로드 경로.
	var fileFullName = file.name; // 파일명.파일확장자.
	var fileName = fileFullName.substring(0, fileFullName.lastIndexOf(".")); // 파일명.
	var fileType = fileFullName.substring(fileFullName.lastIndexOf(".")); // 파일확장자.
	
	// 파일명 중복허용 변수 설정.
	var date = new Date();
	var yyyymmdd = date.getFullYear() + ('0'+(date.getMonth()+1)).slice(-2) + ('0'+(date.getDate())).slice(-2);
	var hhmissms = ('0'+(date.getHours())).slice(-2) + ('0'+(date.getMinutes())).slice(-2) + ('0'+(date.getSeconds())).slice(-2) + ("00"+(date.getMilliseconds())).slice(-3);
	var dateformat= "-" + yyyymmdd + hhmissms;

	// 파일업로드경로/ + 파일명 + -yyyymmddhhmissms + .파일확장자.
	var photoKey = albumPhotosKey + fileName + dateformat + fileType;

	// 다중 업로드를 지원하는 S3 ManagedUpload 클래스 사용.
	var upload = new AWS.S3.ManagedUpload({
		params: {
			Bucket: albumBucketName, // S3 버킷.
			Key: photoKey, // 파일 키.
			Body: file, // 파일.
			ACL: "public-read" // 파일 권한.
		}
	});

	/* 
	 * AWS.Request.promise 메서드는 서비스 작업을 호출하고 콜백을 사용하지 않고 비동기식 흐름을 관리하는 방법을 제공합니다.
	 * Node.js 및 브라우저 스크립트에서 AWS.Request 객체는 콜백 함수 없이 서비스 작업이 호출된 경우 반환됩니다.
	 * 요청의 send 메서드를 호출하여 서비스를 호출할 수 있습니다.
	 */
	var promise = upload.promise();
	
	/* 
	 * AWS.Request.promise는 서비스 호출을 즉시 시작하고 응답 data 속성을 사용하여 이행되었거나
	 * 응답 error 속성을 사용하여 거부된 promise를 반환합니다.
	 * promise를 사용하는 경우 단일 콜백이 오류 탐지를 담당하지 않습니다.
	 * 대신 요청 성공 또는 실패를 바탕으로 올바른 콜백이 호출됩니다.
	 */
	promise.then(
		function(data) {
			addFeedInsert_Content(fileName, dateformat, fileType); // 메인 게시글_추가 글내용 삽입 function.
		},
		function(err) {
			swal("클라우드에 문제가 발생하였습니다.", "오른쪽 하단에 ? 클릭하여 관리자에게 문의해주시기 바랍니다.", "error");
		}
	);
}

// S3 파일 삭제(단일) function.
function deletePhoto(photoKey, fi_no) {
	s3.deleteObject({
		Key: photoKey
	}, function(err, data) {
		if(err) {
			return swal("클라우드에 문제가 발생하였습니다.", "오른쪽 하단에 ? 클릭하여 관리자에게 문의해주시기 바랍니다.", "error");
			
		} else {
			feedViewDelete_Image(fi_no); // 메인 게시글_상세보기 이미지 삭제 function.
		}
	});
}

// S3 파일 삭제(복수) function.
function deleteAllPhoto(photoKey) {
	var photoKeyArr = photoKey;
	
	for(var i = 0 ; i < photoKeyArr.length ; i++) {
		s3.deleteObject({Key: photoKeyArr[i]}, function(err, data) {
			if(err) {
				return swal("클라우드에 문제가 발생하였습니다.", "오른쪽 하단에 ? 클릭하여 관리자에게 문의해주시기 바랍니다.", "error");
			}
		});
		
		// 정상적으로 S3 사진 삭제가 다 끝났을 경우.
		if(i == (photoKeyArr.length - 1)) {
			feedViewDelete_Content(); // 메인 게시글_상세보기 글내용 삭제 function
		}
	}
}