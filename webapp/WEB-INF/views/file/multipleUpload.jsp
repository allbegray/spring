<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>
	<h1> Multiple File Upload </h1>
	<form method="post" enctype="multipart/form-data" action="<c:url value="/file/multipleSave" />">
		Upload File 1: <input type="file" name="file"> <br/>
		Upload File 2: <input type="file" name="file"> <br/>
		Upload File 3: <input type="file" name="file"> <br/>
		Upload File 4: <input type="file" name="file"> <br/>
		<br /><br /><input type="submit" value="Upload"> 
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	</form>
</body>
</html> 