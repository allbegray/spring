<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>
	<h1>Single File Upload</h1>
	<form method="post" enctype="multipart/form-data" action="<c:url value="/file/singleSave" />">
		Upload File: <input type="file" name="file">
		<br /><br />
		Description: <input type="text" name="desc"/>
		<br/><br/><input type="submit" value="Upload"> 
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	</form>
</body>
</html> 