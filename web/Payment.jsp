<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/includes/header.jsp" />
<SCRIPT LANGUAGE="JavaScript">
    function changeInforUser() {
        var o = document.getElementById("tempMessage");
        o.style.display = "block";
        o.innerHTML = "Cập nhật thành công!";
        setTimeout(function () {
            o.style.display = "none";
        }, 1500);
    }

    function radioTransport(value)
    {
        var transportFee = document.getElementById("transportFreeInvoice");
        transportFee.innerHTML = value;
        var finalPriceInvoice = document.getElementById("finalPriceInvoice");
        var totalPrice = document.getElementById("totalPriceInvoice").innerHTML;
        var discount = document.getElementById("discountInvoice").innerHTML;
        finalPriceInvoice.innerHTML = parseInt(totalPrice) + parseInt(value) - parseInt(discount);

//                lastForm.transportFee.value = value;
//                lastForm.finalPrice.value = parseInt(value) + parseInt(lastForm.totalPrice.value) - parseInt(lastForm.discount.value);
    }
    function discountChange() {
        var object = document.getElementById("idDiscount");
        var content_current = object.value;
        var b = "LTW2019";
        if (content_current == b) {
            var discount = "25000";
            var message = "Mã chính xác, bạn được giảm " + discount + " vnđ";
            document.getElementById("idDiscountValidate").value = message;

            var discountInvoice = document.getElementById("discountInvoice");
            discountInvoice.innerHTML = discount;

            var finalPriceInvoice = document.getElementById("finalPriceInvoice");
            var totalPrice = document.getElementById("totalPriceInvoice").innerHTML;
            var transportFee = document.getElementById("transportFreeInvoice").innerHTML;
            finalPriceInvoice.innerHTML = parseInt(totalPrice) + parseInt(transportFee) - parseInt(discount);
//                    Info.discount.value = discount;
            var discountValue = document.getElementById("idDiscountValue");
            discountValue.value = discount;

//                    lastForm.discount.value = discount;
//                    lastForm.finalPrice.value = parseInt(lastForm.transportFee.value) + parseInt(lastForm.totalPrice.value) - parseInt(discount);
        } else {
            var message = "Mã giảm giá không chính xác";
            document.getElementById("idDiscountValidate").value = message;
            var discount = "0";

            var discountInvoice = document.getElementById("discountInvoice");
            discountInvoice.innerHTML = discount;
//                    Info.discount.value = discount;
            var discountValue = document.getElementById("idDiscountValue");
            discountValue.value = discount;

            var finalPriceInvoice = document.getElementById("finalPriceInvoice");
            var totalPrice = document.getElementById("totalPriceInvoice").innerHTML;
            var transportFee = document.getElementById("transportFreeInvoice").innerHTML;
            finalPriceInvoice.innerHTML = parseInt(totalPrice) + parseInt(transportFee) - parseInt(discount);

//                    lastForm.discount.value = discount;
//                    lastForm.finalPrice.value = parseInt(lastForm.transportFee.value) + parseInt(lastForm.totalPrice.value) - parseInt(discount);
        }
    }
</SCRIPT>
<div>
    <h3> Thanh Toán </h3>
    <hr>
    <div>
        <h3> THÔNG TIN NGƯỜI NHẬN </h3>
        <table cellspacing="15" border="0">
            <tr>
            <form action="Payment" method="post" id="idInvoice" >
                <td> Họ Tên </td>
                <td><input type="text" name="name" value="${user.name}"></td>
                <td> Số Điện Thoại </td>
                <td><input type="text" name="numberPhone" value="${user.numberPhone}"></td>
                <td> Địa Chỉ </td>
                <td><input type="text" name="address" value="${user.address}"></td>
                <td></td>
                <td> <i> Mặc <br> Định </i></td>
                <td> <input type="button" value="THAY ĐỔI" onclick="changeInforUser()"> </td>
                <td><div id="tempMessage"></div></td>
            </form>
            </tr>
        </table>
    </div>
    <hr>
    <h4> Sản Phẩm </h4>
    <table border="0">
        <tr>
            <td></td>
            <td> </td>
            <td style="text-align: center"> Đơn Giá </td>
            <td style="text-align: center"> Số Lượng </td>
            <td style="text-align: center"> Thành Tiền </td>

        </tr>
        <c:forEach var="lineBook" items="${cart.listLine}">
            <tr>
                <td style="width: 100px;text-align: center"><img src="${lineBook.book.image}" width="60" height="85" alt="${lineBook.book.image}"/></td>
                <td style="width: 250px;text-align: center"> ${lineBook.book.name} </td>
                <td> ${lineBook.book.price}</td>
                <td style="text-align: center"> x${lineBook.numBook}</td>
                <td style="text-align: center"> ${lineBook.lineBookPrice} vnđ </td>
            </tr>
        </c:forEach>
    </table>
    <h4> TỔNG CỘNG: ${cart.totalPrice} VND </h4>
</div>
<hr>
<div>
    <table>

        <tr><td>Phương Thức Vận Chuyển: </td>
            <td>
                <form name="Info" >
                    <input type="radio" name="transportMethod" value="1" checked onClick="radioTransport(20000);" form="idInvoice"> Giao Hàng Tiêu Chuẩn (Phí Vận Chuyển: 20.000 vnđ)
                    <input type="radio" name="transportMethod" value="2" onClick="radioTransport(30000);" form="idInvoice"> Giao Hàng Nhanh (Phí Vận Chuyển: 30.000 vnđ)
                </form>
            </td>
        </tr>
        <tr><td>Phương Thức Thanh Toán: </td>
            <td>
                <form >
                    <input type="radio" name="paymentMethod" value="1" checked form="idInvoice"> Thanh Toán Khi Nhận Hàng
                    <input type="radio" name="paymentMethod" value="2" form="idInvoice"> Ví Điện Tử
                    <input type="radio" name="paymentMethod" value="3" form="idInvoice"> Thẻ Tín Dụng/Ghi Nợ <br>

                </form>
            </td>
        </tr>
        <tr>
            <td>Mã Giảm Giá: </td>
            <td>
                <input type="text" name="discount" value="0" id="idDiscount" onkeypress="discountChange()">
                <input type="button" name="btDiscount" value="Nhập" onclick="discountChange()"> 
                <input style="border: 0px; width: 500px " type="label" name="discountValidate" id="idDiscountValidate" >
            </td>
        <form >
            <td>
                <input type="hidden" name="discount" value="0" form="idInvoice" id="idDiscountValue" >
            </td>
        </form>
        </tr>
    </table>
</div>
<hr>
<div>
    <h4> Đơn Hàng </h4>
    <table cellspacing="15" border="1" >
        <tr>
            <td>

                <table>
                    <tr>
                        <td> Tổng Tiền Hàng </td>
                        <td> <div id="totalPriceInvoice"> ${cart.totalPrice}</div></td> <td>vnđ</td>
                    </tr>
                    <tr>
                        <td> Phí Vận Chuyển </td>
                        <td><div id="transportFreeInvoice" >20000</div></td> <td>vnđ</td>
                    </tr>
                    <tr>
                        <td> Giảm giá </td>
                        <td><div id="discountInvoice">0</div> </td><td>vnđ</td>

                    </tr>
                    <tr>
                        <td> Tổng Thanh Toán </td>
                        <td><div id="finalPriceInvoice"> ${cart.totalPrice + 20000}</div></td><td>vnđ</td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>
                            <input type="submit" value="Đặt Hàng" form="idInvoice">
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</div>

<br>
<br>
<br>
<c:import url="/WEB-INF/includes/footer.jsp" /> 
