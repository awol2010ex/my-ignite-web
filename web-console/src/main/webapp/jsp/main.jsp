<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta  http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body style="width:95%;height:100%;">
        <div id="app" style="width:100%;height:100%;">
            <router-view></router-view>
        </div>
        <script src="./static/js/dist/main.bundle.js?i=<%=new java.util.Date().getTime()%>"></script>
</body>
</html>