<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <title>Detail</title>
    <style>
        body{
            text-align: center;
        }
        #map{
            margin : 10px;
            top : 21%;
            left: 50%;
            transform: translate(-50%,-50%);
        }
    </style>
    <script>
        var str ="${str}";
        if ( str.length > 1 ){
            alert(str);
        }
    </script>
</head>
<body>
<h1><a href="/">전국낚시터 정보</a></h1>
<div id="map"style="width:500px;height:400px;"></div>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=114864cacc0f8e3d5daf86f9122b9777"></script>
<script>
    var latitude = "${data.getLatitude()}";
    var longitude = "${data.getLongitude()}";
    var container = document.getElementById('map');
    var options = { //지도를 생성할 때 필요한 기본 옵션
        center: new kakao.maps.LatLng(latitude, longitude),
        level: 3 //지도의 레벨(확대, 축소 정도)
    };
    var map = new kakao.maps.Map(container, options);
</script>
<table border="1" align="center">
    <tr>
        <td>낚시터 이름</td>
        <td>낚시터 주소</td>
        <td>낚시터 유형</td>
        <td>낚시터 위도</td>
        <td>낚시터 경도</td>
        <td>주요 어종</td>
        <td>데이터 기준 일자</td>
    </tr>
    <tr>
        <td>${data.getName()}</td>
        <td>${data.getAddress()}</td>
        <td>${data.getCategory()}}</td>
        <td>${data.getLatitude()}</td>
        <td>${data.getLongitude()}</td>
        <td>${data.getFish_species()}</td>
        <td>${data.getDate()}</td>
    </tr>
</table>
<hr>
<table align="center" border="1" bgcolor="aqua">
    <tr>
        <td>게시글 번호</td>
        <td>작성자</td>
        <td>제목</td>
        <td>내용</td>
        <td>생성 날짜</td>
        <td>업데이트 날짜</td>
    </tr>
    <c:forEach var="i" items="${list}">
        <tr>
            <td>${i.getId()}</td>
            <td>${i.getName()}</td>
            <td>${i.getTitle()}</td>
            <td>${i.getContent()}</td>
            <td>${i.getCreated_at()}</td>
            <td>${i.getUpdate_at()}</td>
            <td>
                <form method="post" action="/board">
                    <input type="hidden" value="PUT" name="_method">
                    제목<input type="text" name="title">
                    내용<input type="text" name="content">
                    비밀번호<input type="password" name="password">
                    <input type="submit" value="수정">
                    <input type="hidden" name="id" value="${i.getId()}">
                    <input type="hidden" name="fish_id" value="${i.getFish_id()}">
                </form>
            </td>
            <td>
                <form method="post" action="/board">
                    <input type="hidden" value="DELETE" name="_method">
                    비밀번호<input type="password" name="password">
                    <input type="submit" value="삭제">
                    <input type="hidden" name="id" value="${i.getId()}">
                    <input type="hidden" name="fish_id" value="${i.getFish_id()}">
                </form></td>
        </tr>
    </c:forEach>
</table>
<hr>
<form method="post" action="/board">
    작성자<input type="text" name="name">
    제목<input type="text" name="title">
    내용<input type="text" name="content" width="200" height="200">
    비밀번호<input type="password" name="password">
    <input type="hidden" name="fish_id" value="${data.getId()}">
    <input type="submit" value="작성">
</form>
</body>
</html>
