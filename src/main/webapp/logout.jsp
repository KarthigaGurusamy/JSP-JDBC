<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<title>Todo</title>
<body>
<%
    session.invalidate();
    response.sendRedirect(request.getContextPath());
%>
</body>
</html>