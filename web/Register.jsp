<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/includes/header.jsp" />
        <div>
            <h4> Nhập thông tin đăng ký tài khoản </h4>
            <c:if test="${existedUser == true}">
                <h5> User đã tồn tại. Vui lòng chọn tên đăng nhập khác! </h5>
            </c:if>
            <table >
                <form action='RegisterControl' method='post' >
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
                            <input type="hidden" name="validateUser" value="1">
                            <input type='submit' value='Tiếp Tục'>
                        </td>
                    </tr>
                </form>
            </table>
        </div>
<c:import url="/WEB-INF/includes/footer.jsp" /> 
