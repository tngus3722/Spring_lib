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
</head>
<body>
<h1><a href="/fish">전국낚시터 정보</a></h1>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=114864cacc0f8e3d5daf86f9122b9777"></script>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<div id="map"style="width:500px;height:400px;"></div>
<table id="detail_table" border="1" align="center">
    <tr>
        <td>낚시터 이름</td>
        <td>낚시터 주소</td>
        <td>낚시터 유형</td>
        <td>낚시터 위도</td>
        <td>낚시터 경도</td>
        <td>주요 어종</td>
        <td>데이터 기준 일자</td>
    </tr>
</table>

<hr>

<table align="center" border="1" id="review" >
    <tr>
        <td>게시글 번호</td>
        <td>작성자</td>
        <td>제목</td>
        <td>내용</td>
        <td>생성 날짜</td>
        <td>업데이트 날짜</td>
    </tr>
</table>
<div>
    게시글 번호<input type="text" id="update_id">
    수정 제목<input type="text" id="update_title">
    수정 내용<input type="text" id="update_content">
    <button onclick="update()" type="button">수정</button>
</div>
<hr>
<div id="post_form" name ="post_form" method="post">
    제목<input type="text" name="title" id="post_title">
    내용<input type="text" name="content" width="200" height="200" id="post_content">
    <input type="hidden" name="fish_id" value="${param.id}" id="post_fish_id">
    <button onclick="post()" type="button">전송</button>
</div>

<script>
    $( document ).ready(function(){
        var param = ${param.id};
        function dateFormat(d) {
            return ((d.getMonth() + 1) + "").padStart(2, "0")
                + "/" + (d.getDate() + "").padStart(2, "0")
                + "/" + d.getFullYear();
        }
        $.ajax({
            url: "/fish/board?fish_id="+encodeURI(param),
            type: "get",
            dataType: "json",
            contentType: "application/json",
            success: function(list) {
                var str;
                for( var i=0; i<list.length; i++) {
                    str +=
                        '<tr>'
                            +'<td>' + list[i].id + '</td>'
                            +'<td>' + list[i].writer + '</td>'
                            +'<td>' + list[i].title + '</td>'
                            +'<td>' + list[i].content + '</td>'
                            +'<td>' + dateFormat(new Date(list[i].created_at)) + '</td>'
                            +'<td>' + dateFormat(new Date(list[i].update_at))  + '</td>'
                            +'<td><input type="button" value="삭제" onclick="del(' + list[i].id +')"></td>'
                        +'</tr>';
                }
                $('#review').append(str);
            },
            error: function(errorThrown) {
                alert(errorThrown.statusText);
            }
        });
    });
</script>
<script>
    $( document ).ready(function(){
        var param = ${param.id};
        $.ajax({
            url: "/fish/fish-detail?id="+encodeURI(param),
            type: "get",
            dataType: "json",
            contentType: "application/json",
            success: function(data){
                var latitude = data.latitude;
                var longitude = data.longitude;
                var container = document.getElementById('map');
                var options = { //지도를 생성할 때 필요한 기본 옵션
                    center: new kakao.maps.LatLng(latitude, longitude),
                    level: 3 //지도의 레벨(확대, 축소 정도)
                };
                var map = new kakao.maps.Map(container, options);
                function dateFormat(d) {
                    return ((d.getMonth() + 1) + "").padStart(2, "0")
                        + "/" + (d.getDate() + "").padStart(2, "0")
                        + "/" + d.getFullYear();
                }
                var str =
                    '<tr>'
                    + '<td>' + data.name +'</td>'
                    + '<td>' + data.address + '</td>'
                    +'<td>' + data.category + '</td>'
                    +'<td>'+ data.latitude+'</td>'
                    +'<td>' + data.longitude + '</td>'
                    +'<td>' + data.fish_species + '</td>'
                    +'<td>' + dateFormat(new Date(data.date)) + '</td>'
                    +'</tr>';
                $('#detail_table').append(str);
            },
            error: function(errorThrown) {
                alert(errorThrown.statusText);
            }
        });
    });
</script>
<script>
    function post() {
        var title = $("#post_title").val();
        var content = $("#post_content").val();
        var fish_id = $("#post_fish_id").val();
        var obj = { "title": title, "content" : content, "fish_id" : fish_id };
        $.ajax({
            url: "/board",
            type: "post",
            data: JSON.stringify(obj),
            contentType: "application/json",
            statusCode: {
                201:function(data) {
                    alert(data );

                },
                400:function (data) {
                    alert(JSON.stringify(data.responseText ))
                }
            }
        }).done(function(data){
        }).fail(function ( data) {
        });
    };
</script>
<script>
    function del(id) {
        var obj = { "id":id };
        $.ajax({
            url: "/fish/board",
            type: "delete",
            data: JSON.stringify(obj),
            contentType: "application/json",
            statusCode: {
                200:function(data) {
                    alert(data );
                    location.reload();
                },
                403:function (data) {
                    alert(JSON.stringify(data.responseText ))
                }
            }
        }).done(function(data){
        }).fail(function ( data) {
        });
    };
</script>
<script>
    function update() {
        var id = $("#update_id").val();
        var title = $("#update_title").val();
        var content = $("#update_content").val();
        var obj = { "id": id, "title": title, "content" : content};
        $.ajax({
            url: "/fish/board",
            type: "put",
            data: JSON.stringify(obj),
            contentType: "application/json",
            statusCode: {
                200:function(data) {
                    alert(data );
                    location.reload();
                },
                403:function (data) {
                    alert(JSON.stringify(data.responseText ));
                },
                400:function (data) {
                    alert(JSON.stringify(data.responseText));
                }
            }
        }).done(function(data){
        }).fail(function ( data) {
        });
    };
</script>
</body>
</html>
