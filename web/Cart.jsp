<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/includes/header.jsp" />
<div>
    <h1> GIỎ HÀNG </h1> <br>
</div>
<div>
    <c:if test="${sessionScope.errorNoItemsInCartToPayment == true}">
        <h3> Vui lòng thêm ít nhất một sản phẩm vào giỏ hàng trước khi tiến hành đặt hàng! </h3> <br>    
        <c:set scope="session" var="errorNoItemsInCartToPayment" value="false" ></c:set>
    </c:if>
    <c:if test="${sessionScope.isEmpty == true}">
        <h3> Không có sản phẩm nào trong giỏ hàng của bạn </h3> <br>
    </c:if>
    <c:if test="${exeptionNumEdit == true}">
        <h3> Bạn đã nhập số lượng không hợp lệ. Vui lòng nhập lại </h3>
    </c:if>
    <c:if test="${sessionScope.isEmpty != true}">
        <table cellspacing="20" border="0">
            <tr>
            
                <td></td>
                <td> </td>
                <td> Số Lượng </td>
                <td> Giá </td>
                <td> Thay Đổi Số Lượng </td>
            </tr>
            <c:forEach var="lineBook" items="${cart.listLine}">
                <tr>
                    <td><img src="${lineBook.book.image}" width="75" height="110" alt="${lineBook.book.image}"/></td>
                    <td> ${lineBook.book.name} <br> ${lineBook.book.price} </td>
                    <td> x${lineBook.numBook} </td>
                    <td> ${lineBook.lineBookPrice} </td>
                <form action="CartControl" method="post"> 
                    <input type="hidden" name="idBook" value="${lineBook.book.id}">
                    <input type="hidden" name="typeControl" value="edit">
                    <td> <input type="text" name="numBook"></td>
                    <td> <input type="submit" value="CẬP NHẬT SỐ LƯỢNG"</td>
                </form>

                <form action="CartControl" method="post">
                    <input type="hidden" name="idBook" value="${lineBook.book.id}">
                    <input type="hidden" name="typeControl" value="delete">
                    <td> <input type="submit" value="XÓA"> </td>
                </form>
                </tr>
            </c:forEach>
        </table>

        <h3> TỔNG CỘNG: ${cart.totalPrice} VND </h3>
    </c:if>
    <form action="CartControl" method="post">
        <input type="submit" value="THANH TOÁN">
    </form>
    <a href="index.jsp"> Tiếp Tục Mua Hàng</a>
</div>
<c:import url="/WEB-INF/includes/footer.jsp" /> 