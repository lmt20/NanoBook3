<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>NanoBook</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <div>
            <a href="HomePageControl"><h1><b> NanoBook</b></h1> </a>
            <table>
                <tr>
                    <td>
                        <form action="Login">
                            <input type="submit" value="${sessionScope.user.name != null ? sessionScope.user.name : "Đăng Nhập"}">
                        </form>
                    </td>
                    <td>
                        <form action="CartControl" >
                            <input type="submit" value="Giỏ Hàng">
                        </form>
                    </td>
                </tr>
            </table>

        </div>
        <hr>
        <div>
            <c:forEach var="catagory" items="${sessionScope.listCatagory}">
                <a href='<c:url value="Catagory?catagory=${catagory}"/>'> ${catagory} </a> |
                </c:forEach>
        </div>
        <hr>