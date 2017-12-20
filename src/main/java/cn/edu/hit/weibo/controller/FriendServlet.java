package cn.edu.hit.weibo.controller;

import cn.edu.hit.weibo.model.Friend;
import cn.edu.hit.weibo.model.User;
import cn.edu.hit.weibo.service.FriendService;
import cn.edu.hit.weibo.service.UserService;
import cn.edu.hit.weibo.util.PageUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/Friend.do")
public class FriendServlet extends HttpServlet {
    private UserService userService = new UserService();
    private FriendService friendService = new FriendService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        int friendpagenum = Integer.parseInt(request.getParameter("friendpagenum"));
        int uid = userService.getUidByUsername(username);
        int total = friendService.getFriendListCount(uid);
        int maxpage = PageUtil.getMaxPageNum(total);
        int length = PageUtil.EVERY_PAGE_ITEMS;
        Map<String,String> friendmap = friendService.getFriendList(uid,friendpagenum,length);
        ServletContext servletContext = request.getServletContext();
        servletContext.setAttribute("friendlist",friendmap);
        servletContext.setAttribute("total",total);
        servletContext.setAttribute("maxpage",maxpage);
        request.getRequestDispatcher("/main.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
