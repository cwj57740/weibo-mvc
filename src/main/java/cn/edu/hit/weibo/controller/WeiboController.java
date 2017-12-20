package cn.edu.hit.weibo.controller;

import cn.edu.hit.weibo.model.Blog;
import cn.edu.hit.weibo.model.Comment;
import cn.edu.hit.weibo.model.User;
import cn.edu.hit.weibo.service.UserService;
import cn.edu.hit.weibo.service.WeiboService;
import cn.edu.hit.weibo.util.ResponseUtil;
import cn.edu.hit.weibo.util.PageUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WeiboController {
    private static WeiboService weiboService = new WeiboService();
    private static UserService userService = new UserService();
    public static void getWeiboList(HttpServletRequest request, HttpServletResponse response){
        int pagenum = Integer.parseInt(request.getParameter("pagenum"));
        int total = weiboService.getAllBlogCount();
        int maxpage = PageUtil.getMaxPageNum(total);
        int length = PageUtil.EVERY_PAGE_ITEMS;
        List<Blog> blogList = weiboService.getAllBlogList(pagenum, length);
        List<User> userList = new ArrayList<>();
        for(Blog blog : blogList){
            User user = userService.getUserByUid(blog.getUid());
            userList.add(user);
        }
        try {
            ResponseUtil.sendJsonResponse(response,maxpage,blogList,userList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void addWeibo(HttpServletRequest request, HttpServletResponse response){
        String title = request.getParameter("title");
        String text = request.getParameter("text");
        int uid = (int) request.getSession().getAttribute("uid");
        Blog blog = new Blog();
        blog.setUid(uid);
        blog.setTitle(title);
        blog.setText(text);
        boolean b = weiboService.addBlog(blog);
        ResponseUtil.sendBooleanResponse(response,b);
    }

    public static void getOwnWeiboList(HttpServletRequest request, HttpServletResponse response){
        int pagenum = Integer.parseInt(request.getParameter("pagenum"));

        int uid = (int) request.getSession().getAttribute("uid");
        User user = userService.getUserByUid(uid);
        int total = weiboService.getUserBlogListCount(user);
        int maxpage = PageUtil.getMaxPageNum(total);
        int length = PageUtil.EVERY_PAGE_ITEMS;
        List<Blog> blogList = weiboService.getUserBlogList(user, pagenum, length);
        try {
            ResponseUtil.sendJsonResponse(response,maxpage,blogList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addComment(HttpServletRequest request, HttpServletResponse response){
        int uid = (int) request.getSession().getAttribute("uid");
        int bid = Integer.parseInt(request.getParameter("bid"));
        String context = request.getParameter("context");
        Comment comment = new Comment();
        comment.setBid(bid);
        comment.setContent(context);
        boolean b = weiboService.addComment(comment);
        ResponseUtil.sendBooleanResponse(response,b);
    }

    public static void getCommentList(HttpServletRequest request, HttpServletResponse response){
        int pagenum = Integer.parseInt(request.getParameter("pagenum"));
        int bid = Integer.parseInt(request.getParameter("bid"));
        int total = weiboService.getCommentListCountByBid(bid);
        int maxpage = PageUtil.getMaxPageNum(total);
        int length = PageUtil.EVERY_PAGE_ITEMS;
        List<Comment> commentList = weiboService.getCommentListByBid(bid, pagenum, length);
        try {
            ResponseUtil.sendJsonResponse(response,maxpage,commentList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
