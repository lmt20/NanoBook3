<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/includes/header.jsp" />

        <div>
            <table border="1">
                <tr>
                    <td>
                        <h3> Đơn Hàng ${invoice.id} </h3>
                        <hr>
                        <table cellspace="15">
                            <tr> <td><b> <u> Người Nhận </u></b> </td></tr>
                            <tr>
                                <td>Họ Tên: ${invoice.userReceive.name} | Địa Chỉ: ${invoice.userReceive.address} | Số Điện Thoại: ${invoice.userReceive.numberPhone}</td>
                            </tr>
                            <tr>
                                <td>
                                    <b> <u> Sản Phẩm </u></b>      
                                </td>
                            </tr>    
                            <tr>
                                <td>
                                    <table border="1">
                                        <tr><td></td>
                                            <td> </td>
                                            <td> Đơn Giá </td>
                                            <td> Số Lượng </td>
                                            <td> Thành Tiền </td>
                                        </tr>
                                        <c:forEach var="lineBook" items="${invoice.cart.listLine}">
                                            <tr>
                                                <td style="width: 100px;text-align: center"><img src="${lineBook.book.image}" width="60" height="85" alt="${lineBook.book.image}"/></td>
                                                <td style="width: 250px;text-align: center"> ${lineBook.book.name} </td>
                                                <td> ${lineBook.book.price}</td>
                                                <td style="text-align: center"> x${lineBook.numBook} </td>
                                                <td> ${lineBook.lineBookPrice} vnđ </td>
                                            </tr>
                                        </c:forEach>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td><b> <i> Tổng cộng: ${invoice.cart.totalPrice} VND</i></b></td>
                            </tr>

                            <tr>
                                <td><i>Phương thức vận chuyển: </i> ${invoice.transportMethod} </td>
                            </tr>
                            <tr>
                                <td><i>Phí vận chuyển: </i> ${invoice.transportFee}vnđ </td>
                            </tr>
                            <tr>
                                <td><i>Mã Khuyến Mãi: </i> -${invoice.discount}vnđ </td>
                            </tr>
                            <tr>
                                <td><i>Phương thức thanh toán: </i> ${invoice.paymentMethod} </td>
                            </tr>
                            <tr>
                                <td><b> <i> TỔNG ĐƠN HÀNG: ${invoice.finalPrice} VND</i></b></td>
                            </tr>
                            <tr>
                                <td>
                                    <hr>
                                </td>
                            </tr>
                            <tr>
                                <td><i>Ngày Đặt Hàng: </i> ${invoice.dayPayment} </td>
                            </tr>
                        </table>
                    </td>
                </tr>

            </table>
            <br>
            <form action="InvoiceControl" method="post">
                <input type="submit" value="Hoàn Tất Thanh Toán">
                <input type="hidden" name="completeInvoice" value="1">
            </form>
        </div>
    </body>
</html>
<c:import url="/WEB-INF/includes/footer.jsp" /> 