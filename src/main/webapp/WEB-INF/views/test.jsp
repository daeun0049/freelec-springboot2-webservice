<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>View Test Page</title>
</head>
<body>
    author : <input type="text" id="author">
    title  : <input type="text" id="title">
    content : <input type="text" id="content">
    <button onclick="goSubmit()"> 전송 </button>
<br>
    author : <input type="text" id="author2">
    title  : <input type="text" id="title2">
    content : <input type="text" id="content2">
    <button onclick="doList()"> 조회 </button>


<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
    function goSubmit() {
        $.ajax({
            url: 'enroll',
            type: 'post',
            data: {
                "author" : $("#author").val(),
                "title" : $("#title").val(),
                "content" : $("#content").val()
            },
            dataType: 'json',
            success: function(response) {
                doList(response);
            },
            error:function(request,status,error){
                console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
            }

        });
    }

    function doList(userId) {
        $.ajax({
            url: 'list',
            type: 'post',
            data: {
                "id" : userId
            },
            dataType: 'json',
            success: function(response) {
                console.log("list ->>> "+ response);
                $("#author2").val(response.author);
                $("#title2").val(response.title);
                $("#content2").val(response.content);
            },
            error:function(request,status,error){
                console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
            }
        });
    }
</script>
</body>
</html>
