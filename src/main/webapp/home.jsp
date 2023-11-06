<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head><title> Todo </title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>

</head>
<body>

<div class="container">

<h2 class="fs-1 ms-5"> Welcome!!! <c:out value="${name}"/> </h2>
 <%
        if(session.getAttribute("id") == null){
             response.sendRedirect(request.getContextPath());
        }
    %>

<form action="logout.jsp" method="link" class="ms-5">
<input type="submit" value="Logout" style="background-color: red;color: #fff;  border: none;  border-radius: 5px; padding: 10px 20px; cursor: pointer; margin-top:5px"/>
</form>

<form action="home" method="POST" class="ms-5">
        <p>Enter item: <input type="text" name="item" />
        <input type="submit" value="Add" style="background-color: #007bff;color: #fff;  border: none;  border-radius: 5px; padding: 10px 20px; cursor: pointer; margin-top:5px" />
</form>
</div>

<div class="container">
 <c:if test="${items.size() eq 0}">
        <p style="color:blue;">OOPS!!! No Items to display</p>
    </c:if>

    <c:if test="${items.size() gt 0}">
        <table class="table table-bordered">
                <thead>
                    <tr>
                        <th class="col-3">ID</th>
                        <th class="col-3">Todo</th>
                        <th class="col-3">Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="item" items="${items}">
                        <tr>
                            <td class="col-3">
                                <c:out value="${item.id}" />
                            </td>
                            <td class="col-3">
                                <c:out value="${item.item}" />
                            </td>
                            <td class="col-3"><a href="home?id=<c:out value='${item.id}' />">Delete</a></td>
                        </tr>
                    </c:forEach>
                </tbody>

            </table>
    </c:if>

</div>


</body>
</html>