<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jsp</title>
</head>
<body>
<h1>/WEB-INF/jsp2/error1.jsp</h1>
<p>상태코드: ${requestScope.getAttribute["javax.servlet.error.status_code"]}</p>
<p>에러메시지: ${requestScope.getAttribute["javax.servlet.error.message"]}</p>
</body>
</html>