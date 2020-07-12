<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>main</title>
    <style>
        body {
            text-align: center;
        }
    </style>
</head>
<body>
<h1><a href="http://localhost:8080/">전국낚시터 정보</a></h1>

<form method="post" action="/search">
    <input type="text" name="search">
    <input type="submit" value="search">
</form>
<table border="1" align="center">
    <tr>
        <td>낚시터 이름</td>
        <td>낚시터 주소</td>
    </tr>
    <c:forEach var="i" items="${list}">
        <tr>
            <td><a href="/board/?id=${i.getId()}">${i.getName()}</a></td>
            <td>${i.getAddress()}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
