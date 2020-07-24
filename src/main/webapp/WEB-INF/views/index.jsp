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
<a href="/login">로그인</a> <button onclick="logOut()" type="button">로그아웃</button><br>
<input type="text" name="search" id="search">
<button onclick="search()" type="button">search</button>

<table border="1" align="center">
</table>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script>
    $( document ).ready(function(){
        $.ajax({
            url: "<c:url value="/display" />",
            type: "get",
            dataType: "json",
            contentType: "application/json",
            success: function(list) {
                var str = '<tr> + <td>낚시터 이름</td><td>낚시터 주소</td> </tr>';
                for( var i=0; i<list.length; i++) {
                    str += '<tr>' + '<td>'+'<a href="/detail/?id=' + list[i].id + '">' + list[i].name +'</a>'+ '</td>' + '<td>' + list[i].address + '</td>' + '</tr>';
                }
                $('table').append(str);
            },
            error: function(errorThrown) {
                alert(errorThrown.statusText);
            }
        });
    });
</script>
<script>
    function search(){
        var search = $("#search").val();
        $.ajax({
            url: "/search?search="+encodeURI(search),
            type: "get",
            dataType: "json",
            contentType: "application/json",
            success: function(list) {
                var str = '<tr> + <td>낚시터 이름</td><td>낚시터 주소</td> </tr>';
                $('table').empty();
                for( var i=0; i<list.length; i++) {
                    str += '<tr>' + '<td>'+'<a href="/detail/?id=' + list[i].id + '">' + list[i].name +'</a>'+ '</td>' + '<td>' + list[i].address + '</td>' + '</tr>';
            }
                $('table').append(str);
            },
            error: function(errorThrown) {
                alert(errorThrown.statusText);
            }
        });
    };
</script>
<script>
    function logOut(){
        $.ajax({
            url: "<c:url value="/logOut" />",
            type: "post",
            success: function(data) {
                alert("logout success");
            },
            error: function(errorThrown) {
                alert(errorThrown.statusText);
            }
        });
    };
</script>
</body>
</html>
