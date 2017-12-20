<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<title>注册</title>
	<script src="js/jquery-1.11.2.min.js" type="text/javascript"></script>
	<script type="text/javascript">
        /* 注册按钮操作 */
        function regist(){
            var username = $("#username").val();
            var password = $("#password").val();

            $.ajax({
                type:"POST",
                url:"/Register.do",
                data:{"username":username,"password":password},
                dataType:"json",
                success:function (data){
                    if(data){
                        alert("注册成功！");
                    }else{
                        alert("用户名已存在！");
                    }
                }
            });
        }

	</script>
</head>
<body>
<form id="registform">
	<p id="msg">请输入用户名和密码进行注册</p>
	<div>
		<span class="add-on" title="用户名"><i class="icon-user"></i></span>
    	<input type="text" id="username" placeholder="请输入用户名" />
	</div>
	<div>
		<span class="add-on" title="密码"><i class="icon-lock"></i></span>
    	<input type="password" id="password" placeholder="请输入密码" />
	</div>
    <div class="form-actions" >
        <span class="pull-right"><input type="button" id="btn_regist" class="btn btn-inverse" value="注册" onclick="regist()"/></span>
    </div>
</form>

</body>
</html>