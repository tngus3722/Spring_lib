<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        body {
            text-align: center;
        }
    </style>
    <script src="http://code.jquery.com/jquery-latest.js"></script>
</head>
<body>
<form>
    name : <input type="text" id="name">
    password : <input type="password" id="password">
    <button onclick="signUp()" type="button">가입</button>
</form>
<script>
    function signUp(){
        var name = $("#name").val();
        var password = $("#password").val();
        var obj = {"name" : name, "password":password};
        $.ajax({
            url: "<c:url value="/signUp" />",
            type: "post",
            data: JSON.stringify(obj),
            contentType: "application/json",
            success: function(data) {
                alert("sign up success");
                document.cookie = "auth="+data +'"';
                location.href="/";
            },
            error: function(errorThrown) {
                alert(errorThrown.statusText);
            }
        });
    };
</script>
</body>
</html>
