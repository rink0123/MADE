<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
#testdiv2 {
	display: inline-block;
	position: relative;
	width: 293px;
	height: 293px;
	overflow: hidden;
}

#test2 {
	position: absolute;
	width: 100%;
	height: 100%;
	object-fit: cover;
}
</style>

</head>
<body>
	<div>
		<div id="testdiv2">
			<img id="test2" src="https://i.imgur.com/hYQgQKQ.jpg" />
		</div>
		<div id="testdiv2">
			<img id="test2" src="http://i.stack.imgur.com/wPh0S.jpg">
		</div>
	</div>
</body>
</html>