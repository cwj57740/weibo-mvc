<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<title>主页</title>
	<meta charset="UTF-8">
	<script src="js/jquery-1.11.2.min.js" type="text/javascript"></script>
	<script type="text/javascript"></script>
</head>
<body>
<form action="">
	标题：<input type="text" name="title" size="120" /><br/>
	正文：<br/><textarea name="text" id="text" cols="100" rows="10"></textarea><br/>
	<input type="button" value="发布"/>
</form>

<p>好友列表</p>
<input type="hidden" name="friendpagenum" id="friendpagenum"/>
<input type="hidden" name="weibopagenum" id="weibopagenum"/>
<table class="table table-bordered table-striped" id="table1">
	<thead>
		<tr>
			<th>用户id</th>
			<th>用户名</th>
		</tr>
	</thead>
	<tbody>
       <c:forEach var="" items="">
		<tr>
			<td id="bid"></td>
			<td></td>
			<td>
                <button class="btn btn-info" onclick="">
                    <i class="icon-white icon-eye-open"></i>
                    	查看微博
                </button>
            </td>
		</tr>
		</c:forEach>
	</tbody>
</table>
<div class="fg-toolbar ui-toolbar ui-widget-header ui-corner-bl ui-corner-br ui-helper-clearfix">
	<div id="pagecut" class="dataTables_paginate fg-buttonset ui-buttonset fg-buttonset-multi ui-buttonset-multi paging_full_numbers">
		<c:choose>
			<c:when test="${friendpagenum == 1 }">
				首页
				上一页
			</c:when>
			<c:otherwise>
				<a tabindex="0" href=‘"javascript:"+method+"(1)"’ page="1" class="first ui-corner-tl ui-corner-bl fg-button ui-button ui-state-default">首页</a>
				<a tabindex="0" href="javascript:void(0);" page="${friendpagenum-1}" class="previous fg-button ui-button ui-state-default">上一页</a>
			</c:otherwise>
		</c:choose>

		<a tabindex="0" href="#" class="fg-button ui-button ui-state-default">${friendpagenum}</a>
		<!-- <a tabindex="0" class="fg-button ui-button ui-state-default ui-state-disabled">2
		</a>
		<a tabindex="0" href="#" class="fg-button ui-button ui-state-default">3</a> -->
		<c:choose>
			<c:when test="${friendpagenum==maxpage}">
				下一页
				尾页
			</c:when>
			<c:otherwise>
				<a tabindex="0" href="javascript:void(0);" page="${friendpagenum+1 }" class="previous fg-button ui-button ui-state-default">下一页</a>
				<a tabindex="0" href="javascript:void(0);" page="${friendmaxpage }" class="last ui-corner-tr ui-corner-br fg-button ui-button ui-state-default">尾页</a>
			</c:otherwise>
		</c:choose>
		共计 ${friendtotal } 条数据
	</div>
</div>

<input type="button" value="查看所有微博">
<input type="button" value="查看我的微博">

<!-- 微博内容列表 -->
<table class="table table-bordered table-striped" id="table2">
	<thead>
		<tr>
			<th>编号</th>
			<th>用户名</th>
			<th>标题</th>
			<th>正文</th>
			<th>点击量</th>
		</tr>
	</thead>
	<tbody>
       <c:forEach var="" items="">
		<tr>
			<td id="bid"></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			
			<td>
                <button class="btn btn-info" onclick="">
                    <i class="icon-white icon-eye-open"></i>
                    	查看
                </button>
                <button class="btn btn-warning" onclick="">
                    <i class="icon-white icon-refresh"></i>
                    	回复
                </button>
                <button class="btn btn-success" onclick="">
                    <i class="icon-white icon-share"></i>
                    	删除
                </button> 
                <button class="btn btn-success" onclick="">
                    <i class="icon-white icon-share"></i>
                    	将该用户加为好友
                </button> 
            </td>
		</tr>	
		</c:forEach> 
	</tbody>
</table>
<div class="fg-toolbar ui-toolbar ui-widget-header ui-corner-bl ui-corner-br ui-helper-clearfix">
	<div id="pagecut" class="dataTables_paginate fg-buttonset ui-buttonset fg-buttonset-multi ui-buttonset-multi paging_full_numbers">
		<c:choose>
			<c:when test="${weibopagenum == 1 }">
				首页
				上一页
			</c:when>
			<c:otherwise>
				<a tabindex="0" href=‘"javascript:"+method+"(1)"’ page="1" class="first ui-corner-tl ui-corner-bl fg-button ui-button ui-state-default">首页</a>
				<a tabindex="0" href="javascript:void(0);" page="${weibopagenum-1}" class="previous fg-button ui-button ui-state-default">上一页</a>
			</c:otherwise>
		</c:choose>

		<a tabindex="0" href="#" class="fg-button ui-button ui-state-default">${weibopagenum}</a>
		<!-- <a tabindex="0" class="fg-button ui-button ui-state-default ui-state-disabled">2
		</a>
		<a tabindex="0" href="#" class="fg-button ui-button ui-state-default">3</a> -->
		<c:choose>
			<c:when test="${weibopagenum==maxpage}">
				下一页
				尾页
			</c:when>
			<c:otherwise>
				<a tabindex="0" href="javascript:void(0);" page="${weibopagenum+1 }" class="previous fg-button ui-button ui-state-default">下一页</a>
				<a tabindex="0" href="javascript:void(0);" page="${weibomaxpage }" class="last ui-corner-tr ui-corner-br fg-button ui-button ui-state-default">尾页</a>
			</c:otherwise>
		</c:choose>
		共计 ${weibototal } 条数据
	</div>
</div>
</body>
</html>