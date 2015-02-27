<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title><sitemesh:write property='title'/></title>

    <link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/lib/jquery-ui/themes/redmond/jquery-ui.min.css"/>" />
    <link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/lib/jqgrid/css/ui.jqgrid.css"/>" />
	<!-- jquery -->
    <script type="text/javascript" src="<c:url value="/lib/jquery/jquery.min.js"/>" ></script>
    <!-- jquery-ui -->
    <script type="text/javascript" src="<c:url value="/lib/jquery-ui/jquery-ui.min.js"/>" ></script>
    <!-- jqGrid -->
    <script type="text/javascript" src="<c:url value="/lib/jqgrid/js/i18n/grid.locale-kr.js"/>" ></script>
    <script type="text/javascript" src="<c:url value="/lib/jqgrid/jquery.jqGrid.min.js"/>" ></script>
    <!-- common -->
    <script type="text/javascript" src="<c:url value="/js/jquery-common.js"/>" ></script>

    <sitemesh:write property='head'/>
</head>
<body>
<sitemesh:write property='body'/>
</body>
</html>