<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>

<head>
	<title>主页</title>
	<meta charset="UTF-8">
	<meta name="description" content="">
	<meta name="author" content="">
	<script src="js/jquery-1.11.2.min.js" type="text/javascript"></script>

</head>

<body>

<h2>添加微博</h2>
<form action="">
	标题：<input id="add-weibo-title" type="text" name="title" size="120" /><br/> 正文：
	<br/><textarea id="add-weibo-content" name="text" id="text" cols="100" rows="10"></textarea><br/>
	<input id="add-weibo-button" type="button" value="发布" />
</form>

<h2>好友列表</h2>
<p>第 <b id="friend-page-index">1</b> 页</p>
<p>共 <b id="friend-page-count">1</b> 页</p>
<table id="friend-table" border="1">
	<tr>
		<th>好友 ID</th>
		<th>用户名</th>
		<th>操作</th>
	</tr>
</table>
<button onclick="friend_page_jump(0);">上一页</button>
<button onclick="friend_page_jump(1);">下一页</button>

<h2>别人的微博列表</h2>
<p>第 <b id="other-weibo-page-index">1</b> 页</p>
<p>共 <b id="other-weibo-page-count">1</b> 页</p>
<table id="other-weibo-table" border="1">
	<tr>
		<th>编号</th>
		<th>用户 ID</th>
		<th>用户名</th>
		<th>标题</th>
		<th>点击量</th>
		<th>添加好友</th>
		<th>查看微博</th>
	</tr>
</table>

<button onclick="other_weibo_page_jump(0);">上一页</button>
<button onclick="other_weibo_page_jump(1);">下一页</button>


<h2>自己的微博列表</h2>
<p>第 <b id="self-weibo-page-index">1</b> 页</p>
<p>共 <b id="self-weibo-page-count">1</b> 页</p>
<table id="self-weibo-table" border="1">
	<tr>
		<th>编号</th>
		<th>标题</th>
		<th>点击量</th>
		<th>查看微博</th>
	</tr>
</table>

<button onclick="self_weibo_page_jump(0);">上一页</button>
<button onclick="self_weibo_page_jump(1)">下一页</button>

<h2>微博查看</h2>
<h3 id="current-weibo-no" hidden>weibo no</h3>
<h3>标题：</h3>
<p id="weibo-title"></p>
<h3>内容：</h3>
<p id="weibo-content"></p>
<h3>评论列表：</h3>
<p>第 <b id="comment-page-index">1</b> 页</p>
<p>共 <b id="comment-page-count">1</b> 页</p>
<table id="comment-table" border="1">
	<tr>
		<th>用户 ID</th>
		<th>内容</th>
	</tr>
</table>
<button onclick="comment_page_jump(0);">上一页</button>
<button onclick="comment_page_jump(1);">下一页</button>

<script type="text/javascript">
    $("#add-weibo-button").click(function() {
        console.log("sdsdd");
        var title = $("#add-weibo-title").val();
        var content = $("#add-weibo-content").val();

        console.log("title : " + title);
        console.log("content : " + content);
        $.ajax({
            type: "GET",
            url: "/AddWeibo.do",
            data: {
                "title": title,
                "text": content
            },
            success: function(data) {
                console.log("success");
                console.log(data);
                alert("添加成功");
            },
            error: function(error) {
                console.log(error);
                alert("添加失败");
            }
        });
    });

    function get_friend_list(index) {
        $.ajax({
            type: "GET",
            url: "/GetFriendList.do",
            data: {
                "index": index
            },
            success: function(data) {
                console.log(data);
                var friend_list;
                clear_firent_table();
                for (var friend in friend_list) {
                    var userid = frined["userid"];
                    var username = frined["username"];

                    insert_friend_table(userid, username);
                }
            },
            error: function(error) {
                console.log(error);
                alert("服务器宕机了~~");
            }
        });
    }

    function clear_firent_table() {
        $("#friend-table tr:not(:first)").empty();
    }

    function insert_friend_table(userid, username) {
        var trhtml = "<tr><td>{1}</td><td>{2}</td></tr>";
        trhtml = trhtml.replace("{1}", userid).replace("{2}", username);
        $("#friend-table").append(trhtml);
    }

    function friend_page_jump(ope) {
        // 0 上一页 1 下一页
        console.log(ope);
        var current_index = $("#friend-page-index").html();
        var friend_page_count = $("#friend-page-count").html();
        console.log("friend page count " + friend_page_count);
        console.log("frined page index " + current_index);

        var index;
        if (ope == 0) {
            index = current_index - 1;
            if (current_index < 1) {
                return;
            }
        } else if (ope == 1) {
            index = current_index + 1;
            if (index > friend_page_count) {
                return;
            }
        } else if (ope == 2) {
            index = 1;
        } else {
            return;
        }
        get_friend_list(index);
    }

    function get_other_list(index) {
        $.ajax({
            type: "GET",
            url: "/GetWeiboList.do",
            data: {
                "index": index
            },
            success: function(data) {
                console.log(data);
                var other_weibo_list;
                for (var weibo in other_weibo_list) {
                    var weibono = weibo["weibono"];

                }
            },
            error: function(data) {
                console.log(error);
            }
        });
    }

    function clear_other_weibo_table() {
        $("#other-weibo-table tr:not(:first)").empty();
    }

    function insert_other_weibo_table(weibo_no, userid, username, title, hits) {
        var trhtml = "<tr> \
                            <td>{1}</td> \
                            <td>{2}</td> \
                            <td>{3}</td> \
                            <td>{4}</td> \
                            <td>{5}</td> \
                            <td><button onclick=\"add_friend('{6}');\">添加好友</button></td> \
                            <td><button onclick=\"view_weibo('{7}')\">查看微博</button></td> \
                        </tr>";
        trhtml = trhtml.replace("{1}", weibo_no);
        trhtml = trhtml.replace("{2}", userid);
        trhtml = trhtml.replace("{3}", username);
        trhtml = trhtml.replace("{4}", title);
        trhtml = trhtml.replace("{5}", hits);
        trhtml = trhtml.replace("{6}", userid);
        trhtml = trhtml.replace("{7}", weibo_no);
        $("#other-weibo-table").append(trhtml);
    }

    function other_weibo_page_jump(ope) {
        var current_index = $("#other-weibo-page-index").html();
        var page_count = $("#other-weibo-page-count").html();

        var index;
        if (ope == 0) {
            index = current_index - 1;
            if (current_index < 1) {
                return;
            }
        } else if (ope == 1) {
            index = current_index + 1;
            if (index > current_index) {
                return;
            }
        } else if (ope == 2) {
            index = 1;
        } else {
            return;
        }
        get_other_list(index);
    }

    function add_friend(userid) {
        console.log(userid);
        $.ajax({
            type: "POST",
            url: "/AddFriend.do",
            data: {
                "userid": userid
            },
            success: function(data) {
                console.log(data);
                location.reload()
            },
            error: function(error) {
                console.log(error);
            }
        })
    }

    function view_weibo(weibo_no) {
        console.log(weibo_no);
        $.ajax({
            type: "POST",
            url: "/GetWeibo.do",
            data: {
                "bid": weibo_no
            },
            success: function(data) {
                console.log(data);
            },
            error: function(error) {
                console.log(error);
            }
        })
    }

    function get_self_weibo_list(index) {
        $ajax({
            type: "GET",
            url: "/GetOwnWeiboList.do",
            data: {
                "data": index
            },
            success: function(data) {
                console.log(data);
            },
            error: function(error) {
                console.log(error);
            }
        });
    }

    function clear_self_weibo_list() {
        $("#self-weibo-table tr:not(:first)").empty();
    }

    function insert_self_weibo_table(weibo_no, title, hits) {
        var trhtml = "<tr> \
    <td>{1}</td> \
    <td>{2}</td> \
    <td>{3}</td> \
    <td><button onclick=\"view_weibo('{4}')\">查看微博</button></td> \
</tr>";
        trhtml = trhtml.replace("{1}", weibo_no).replace("{2}", title).replace("{3}", hits);
        trhtml = trhtml.replace("{4}", weibo_no);
        $("#self-weibo-table").append(trhtml);
    }

    function self_weibo_page_jump(ope) {
        var current_page = $("#self-weibo-page-index");
        var page_count = $("#self-weibo-page-count");

        var index;
        if (ope == o) {
            index = current_page - 1;
            if (index < 1) {
                return;
            }
        } else if (ope == 1) {
            index = current_page + 1;
            if (index > page_count) {
                return;
            }
        } else if (ope == 2) {
            index = 1;
        } else {
            return;
        }
        get_self_weibo_list(index);
    }

    function get_comment_list(weibo_no, page) {
        $.ajax({
            type: "GET",
            url: "/GetCommentList.do",
            data: {
                "bid": weibo_no,
                "page": page
            },
            success: function(data) {
                console.log(data);
            },
            error: function(error) {
                console.log(error);
            }
        });
    }

    function clear_comment_table() {
        $("#comment-table tr:not(:first)").empty();
    }

    function insert_comment_table(userid, content) {
        var trhtml = "<tr> \
            <td>{1}</td> \
            <td>{2}</td> \
        </tr>";
        trhtml = trhtml.replace("{1}", userid).replace("{2}", content);
        $("#comment-table").append(trhtml);
    }

    function comment_page_jump(ope) {
        var current_page = $("#comment-page-index").html();
        var page_count = $("#comment-page-count").html();

        var index;
        if (ope == 0) {
            index = current_page - 1;
            if (index < 1) {
                return;
            }
        } else if (ope == 1) {
            index = current_page + 1;
            if (index > page_count) {
                return;
            }
        } else if (ope == 2) {
            index = 1;
        } else {
            return;
        }
        var weibo_no = $("#current-weibo-no").html();
        get_comment_list(weibo_no, index);
    }

    $(document).ready(function() {
        // friend_page_jump(2);
        // clear_other_weibo_table();
        // insert_other_weibo_table("1212", "22", "linx", "sd", 55);
        // clear_self_weibo_list()
        // insert_self_weibo_table("sd", "title", "sd2222");
        // clear_comment_table();
        // insert_comment_table("sdsdsd", "121212121");
    });
</script>
</body>

</html>