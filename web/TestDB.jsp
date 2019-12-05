<%-- 
    Document   : TestDB
    Created on : Dec 5, 2019, 6:08:16 PM
    Author     : Asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <a href="TestDB" method="get"> Kick off </a> <br>
        ${requestScope.rq} <br>
        
        <c:forEach var="book" items="${listBook}">
            ${book.name} <br>
        </c:forEach>
        
    </body>
</html>
