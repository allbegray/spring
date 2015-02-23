<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
</head>
<body>
	<h2>일반적인 에러</h2>
	
    Exception= ${exception.message}<br><br>
 
	<strong>Exception Stack Trace</strong><br>
	<c:forEach items="${exception.stackTrace}" var="ste">
	    ${ste}
	</c:forEach>
</body>
</html>
