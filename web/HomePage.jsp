
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/includes/header.jsp" />
<div>
    <table>
        <tr>
            <c:forEach var="book" items="${listBooks}" varStatus="status">
                <td style="text-align: center; align-content: center; width: 250px">
                    <img src="${book.image}" width="150" height="220" alt="${book.image}"/> <br>
                    <a href="<c:url value='BookDetail?bookId=${book.id}' />"> ${book.name}</a> <br>
                </td>
                <c:if test="${status.count%6 ==0}">
                </tr>
                <tr>
                </c:if>
            </c:forEach>
        </tr>
    </table>
</div>
<c:import url="/WEB-INF/includes/footer.jsp" />            
