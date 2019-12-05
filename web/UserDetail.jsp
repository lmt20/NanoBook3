<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/includes/header.jsp" />
<div>
    <c:if test="${requestScope.ms != null}">
        <h4> ${requestScope.ms} </h4>
    </c:if>
    <table border="0">
        <tr>
            <td>
                <div style="width: 250px; height: 900px">
                    <table border="1" >
                        <tr>
                            <td>
                                <h3>THÔNG TIN TÀI KHOẢN:</h3>
                                <table >
                                    <form action="UserUpdate" method="post">
                                        <tr>
                                            <td> Tên Đăng Nhập: </td>
                                            <td> ${sessionScope.user.username}  </td>
                                            <td><input type="hidden" name="username" value="${sessionScope.user.username}">  </td>
                                        </tr>
                                        <tr><td><br></td></tr>
                                        <tr>
                                            <td> Mật Khẩu </td>
                                            <td> <input type='password' name='password' value="${sessionScope.user.password}"></td>
                                        </tr>
                                        <tr><td><br></td></tr>
                                        <tr>
                                            <td> Họ Tên </td>
                                            <td> <input type="text" name='name' value="${sessionScope.user.name}">  </td>
                                        </tr>
                                        <tr><td><br></td></tr>
                                        <tr>
                                            <td> Địa Chỉ </td>
                                            <td> <input type="text" name='address' value="${sessionScope.user.address}">  </td>
                                        </tr>
                                        <tr><td><br></td></tr>
                                        <tr>
                                            <td> Số điện thoại </td>
                                            <td> <input type="text" name='numberPhone' value="${sessionScope.user.numberPhone}">  </td>
                                        </tr>
                                        <tr><td><br></td></tr>
                                        <tr>

                                            <td>
                                                <input type='submit' value='Cập Nhật'>
                                    </form>
                            </td>
                            <td>
                                <form action="Logout">
                                    <input type='submit' value='Đăng Xuất'>
                                </form>
                            </td>
                        </tr>

                        <tr><td> <br><br><br><br><br><br><br><br><br><br></td></tr>
                        <tr></tr>
                        <tr></tr>
                        <tr></tr>

                    </table>
            </td>
        </tr>
    </table>
</div>
</td>
<td>
    <table border="1">
        <tr>
            <td><div style="width: 1000px; height: 876px">
                    <h3>  LỊCH SỬ ĐẶT HÀNG:</h3>
                    <table>
                        <c:if test="${fn:length(requestScope.listInvoice) gt 0}">
                            <c:forEach var="iv" items="${requestScope.listInvoice}">
                                <tr>
                                    <td>
                                        <h3> <a href="InvoiceControl?idInvoice=${iv.id}"> Đơn Hàng: ${iv.id}</a> </h3>
                                    </td>
                                    <td>
                                        <h4> &nbsp;&nbsp;&nbsp; ${fn:length(iv.cart.listLine)} sản phẩm</h4>
                                    </td>
                                    <td>
                                        <h4>  &nbsp;&nbsp;&nbsp; Số tiền: ${iv.finalPrice} vnđ </h4>
                                    </td>
                                    <td>
                                        <h4> &nbsp;&nbsp;&nbsp; Ngày đặt hàng: ${iv.dayPayment}</h4>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:if>

                        <c:if test="${requestScope.selectedInvoice != null}">
                            <table border="1">
                                <tr>
                                    <td>
                                        <h3> Đơn Hàng ${requestScope.selectedInvoice.id}<br> </h3>
                                        <hr>
                                        <table>
                                            <tr><td><b> <u> Người Nhận </u></b> </td> </tr>
                                            <tr>
                                                <td>Họ Tên: ${requestScope.selectedInvoice.userReceive.name} | Địa Chỉ: ${requestScope.selectedInvoice.userReceive.address} | Số Điện Thoại: ${requestScope.selectedInvoice.userReceive.numberPhone}</td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <b> <u> Sản Phẩm </u></b>      
                                                </td> 
                                            </tr>    
                                            <tr>
                                                <td>
                                                    <table border="1">
                                                        <tr>
                                                            <td> </td>
                                                            <td> Đơn Giá </td>
                                                            <td> Số Lượng </td>
                                                            <td> Thành Tiền </td>
                                                        </tr>
                                                        <c:forEach var="lineBook" items="${requestScope.selectedInvoice.cart.listLine}">
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
                                                <td><b> <i> Tổng cộng: ${requestScope.selectedInvoice.cart.totalPrice} VND</i></b></td>
                                            </tr>
                                            <tr>
                                                <td><i>Phương thức vận chuyển: </i> ${requestScope.selectedInvoice.transportMethod} </td>
                                            </tr>
                                            <tr>
                                                <td><i>Phí vận chuyển: </i> ${requestScope.selectedInvoice.transportFee}vnđ </td>
                                            </tr>
                                            <tr>
                                                <td><i>Mã Khuyến Mãi: </i> -${requestScope.selectedInvoice.discount}vnđ </td>
                                            </tr>
                                            <tr>
                                                <td><i>Phương thức thanh toán: </i> ${requestScope.selectedInvoice.paymentMethod} </td>
                                            </tr>
                                            <tr>
                                                <td><b> <i> TỔNG ĐƠN HÀNG: ${requestScope.selectedInvoice.finalPrice} VND</i></b></td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <hr>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td><i>Ngày Đặt Hàng: </i> ${requestScope.selectedInvoice.dayPayment} </td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                            </table>  
                        </c:if>

                    </table>

                </div>

            </td>
        </tr>
    </table>
</td>
</tr>
</table>
</div>

<c:import url="/WEB-INF/includes/footer.jsp" /> 