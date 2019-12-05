<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/includes/header.jsp" />
<script type="text/javascript">
    function callServlet() {
        document.forms["addRating"].submit();
    }
</script>
<div>
    <table>
        <tr>

            <td style="text-align: center">
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <img src="${sessionScope.selectingBook.image}" width="150" height="220" alt="${sessionScope.selectingBook.image}"/> <br>
            </td>
            <td> 
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
            <td>
                <div>
                    <c:if test="${requestScope.ex == true}">
                        <h5> Số lượng sản phẩm không hợp lệ. Vui lòng nhập lại! </h5>    
                    </c:if>
                    <h2>
                        ${sessionScope.selectingBook.name}
                    </h2>
                    <h3>
                        Đánh giá: ${sessionScope.selectingBook.finalRating} (${fn:length(sessionScope.selectingBook.listUserRating)})<br>
                        Tác Giả: ${sessionScope.selectingBook.author}<br>
                        Giá Bán: ${sessionScope.selectingBook.price} VNĐ <br>
                    </h3>
                    <form action="BookDetail" method='post'>
                        <h2>Lựa Chọn Số Lượng</h2> 
                        <input style="width: 60px" type="text" name="numBook">
                        <input type="submit" value="Chọn Mua">
                    </form>
                    <br><br>
                </div>
            </td>
        </tr>
    </table>
    <p>
        Mô Tả: ${sessionScope.selectingBook.description} <br>
    </p>
</div>
<hr>
<div>
    <h3> Đánh Giá Bình Luận </h3>
    <h4> 
        <form action="AddRatingBook" method="post" id="addRating">
            Đánh Giá Của Bạn
            <input type="radio" name="ratingInput" value="1" onclick="callServlet();"/>1
            <input type="radio" name="ratingInput" value="2" onclick="callServlet();"/>2
            <input type="radio" name="ratingInput" value="3" onclick="callServlet();"/>3
            <input type="radio" name="ratingInput" value="4" onclick="callServlet();"/>4
            <input type="radio" name="ratingInput" value="5" onclick="callServlet();"/>5 
            <input type="submit" value="Đánh Giá" hidden>
        </form>
        <form action="AddCommentBook" method="post">
            <br>
            Nhập Bình Luận
            <input style="width: 400px" type="text" name="commentInput">
            <input type="submit" value="Bình Luận">
        </form>
    </h4>

    <c:forEach var="userComment" items="${sessionScope.selectingBook.listUserComment}">
        <table>
            <tr>

                <td>
                    <h4 style="color:cornflowerblue">${userComment.user.name}&nbsp;&nbsp;</td></h4>
                    <c:forEach var="userRating" items="${sessionScope.selectingBook.listUserRating}">
                        <c:if test="${userComment.user.username eq userRating.user.username}">
                        <td> <p style="color: gold">Đánh giá ${userRating.rate}*</p> </td>
                    </c:if>
                </c:forEach>

            </tr>
        </table>
        <p>${userComment.comment}</p>
    </c:forEach>
</div>

<c:import url="/WEB-INF/includes/footer.jsp" />     