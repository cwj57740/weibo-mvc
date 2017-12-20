<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<title>登录</title>
	<meta charset="UTF-8"/>
    <script src="js/jquery-1.11.2.min.js" type="text/javascript"></script>
	<script type="text/javascript">
        /* 登录按钮操作 */
        function login(){
            var username = $("#username").val();
            var password = $("#password").val();

            $.ajax({
                type:"POST",
                url:"/Login.do",
                data:{"username":username,"password":password},
                dataType:"json",
                success:function (data){
                    if(data){
                        window.location.href='/main.jsp';
                    }else{
                        alert("用户名或密码有误！");
                    }
                },
                error:function(){
                    alert("有错误");
                }
            });
        }

    </script>
</head>
<body>
<form id="loginform" class="form-vertical">
	<p id="msg">请输入用户名和密码进行登录</p>
    <div class="control-group">
        <div class="controls">
            <div class="input-prepend">
                <span class="add-on" title="用户名"><i class="icon-user"></i></span>
                <input type="text" id="username" placeholder="请输入用户名" />
                <div class="logincheck">
                	<span id="checkdlname"></span>
                </div>
            </div>
        </div>
    </div>
    <div class="control-group">
        <div class="controls">
            <div class="input-prepend">
                <span class="add-on" title="密码"><i class="icon-lock"></i></span>
                <input type="password" id="password" placeholder="请输入密码" />
                <div class="logincheck">
                	<span id="checkdlpass"></span>
                </div>
            </div>
        </div>
    </div>
    <div class="form-actions" >
        <span class="pull-left"><a href="regist.jsp" class="flip-link" id="to-recover">注册</a></span>
        <span class="pull-right"><input type="button" id="btn_login" class="btn btn-inverse" value="登录" onclick="login()"/></span>
    </div>
</form>
</body>
</html>