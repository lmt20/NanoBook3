<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<sql:query var="rs" dataSource="jdbc/LMTruong">
    select idUser, firstName from user;
</sql:query>

<html>
    <head>
        <title>DB Test</title>
    </head>
    <body>

        <h2>Results</h2>

        <c:forEach var="row" items="${rs.rows}">
            Foo ${row.idUser}<br/>
            Bar ${row.firstName}<br/>
        </c:forEach>
        <a href="Test" method="get"> Kick off </a>
        <c:if test="${requestScope.user == null}">
            <h3>
                NULLL
            </h3>
        </c:if>
        <c:if test="${requestScope.user != null}">
            <h3>
                ${requestScope.user.name} <br>
                ${requestScope.user.address} <br>
            </h3>
        </c:if>
    </body>
</html>