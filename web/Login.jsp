<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/includes/header.jsp" />
<div>
    <c:if test="${requestScope.errorLogin == true}">
        <h3> Tên đăng nhập hoặc mật khẩu không chính xác. Vui lòng nhập lại! </h3>
    </c:if>
    <c:if test="${session.loginToComment == true}">
        <h3> Bạn cần đăng nhập trước khi bình luận</h3>
        <c:set scope="session" var="loginToComment" value="${false}"></c:set>
    </c:if>
    <c:if test="${session.loginToRate == true}">
        <h3> Bạn cần đăng nhập trước khi bình luận</h3>
        <c:set scope="session" var="loginToRate" value="${false}"></c:set>
    </c:if>
    <table >
        <form action='Login' method='post'>
            <tr>
                <td> Tên Đăng Nhập </td>
                <td> <input type="text" name='username'> </td>
            </tr>
            <tr>
                <td> Mật Khẩu </td>
                <td> <input type='password' name='password'></td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <input type='submit' value='Đăng Nhập'>
                </td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <a href="RegisterControl"> Đăng Ký </a>
                </td>
            </tr>

        </form>
    </table>
</div>
<c:import url="/WEB-INF/includes/footer.jsp" /> 