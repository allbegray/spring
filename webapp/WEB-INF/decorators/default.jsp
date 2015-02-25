<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title><sitemesh:write property='title'/></title>

    <link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/lib/jquery-ui/themes/redmond/jquery-ui.min.css"/>" />
    <link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/lib/jqGrid/css/ui.jqgrid.css"/>" />

    <script type="text/javascript" src="<c:url value="/lib/jquery/jquery.min.js"/>" ></script>
    <script type="text/javascript" src="<c:url value="/lib/jquery-ui/jquery-ui.min.js"/>" ></script>
    <script type="text/javascript" src="<c:url value="/lib/jqGrid/grid.locale-kr.js"/>" ></script>
    <script type="text/javascript" src="<c:url value="/lib/jqGrid/jquery.jqGrid.min.js"/>" ></script>
    
    <script type="text/javascript" src="<c:url value="/js/jquery-common.js"/>" ></script>

    <sitemesh:write property='head'/>
</head>
<body>
<sitemesh:write property='body'/>
</body>
</html>