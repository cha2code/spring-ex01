<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>ex02 page</h1>
<!-- camel case로 작성 / el의 내장 객체 - param -->
${sampleDTO }<br>
${param.name } / ${param.age }
</body>
</html>