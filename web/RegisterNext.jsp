<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/includes/header.jsp" />
        <div>
            <h4> Vui lòng cung cấp thông tin cá nhân </h4>
            <table >
                <form action='RegisterControl' method='post' accept-charset="UTF-8">
                    <tr>
                        <td> Họ Tên </td>
                        <td> <input type="text" name='name'> </td>
                    </tr>
                    <tr>
                        <td> Địa Chỉ </td>
                        <td> <input type="text" name='address'> </td>
                    </tr>
                    <tr>
                        <td> Số Điện Thoại </td>
                        <td> <input type="text" name='numberPhone'> </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>
                            <input type='submit' value='Đăng Ký'>
                        </td>
                    </tr>

                </form>
            </table>
        </div>
<c:import url="/WEB-INF/includes/footer.jsp" /> 
