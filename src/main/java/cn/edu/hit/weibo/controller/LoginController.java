package cn.edu.hit.weibo.controller;

import cn.edu.hit.weibo.model.User;
import cn.edu.hit.weibo.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginController {
    private UserService us = new UserService();
    public void login(HttpServletRequest request, HttpServletResponse response){
        PrintWriter out = null;
        try {
            out = response.getWriter();
            response.setContentType("text/html;charset=UTF-8");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            int uid = us.getUidByUsername(username);
            //测试用
//		System.out.println("用户名："+username);
//		System.out.println("密码："+password);

            User user = us.login(username,password);
            if(user != null){
                //测试用
//			System.out.println("找到了用户");

                Cookie cookie = new Cookie("username",username);
                response.addCookie(cookie);
                HttpSession session = request.getSession();
                session.setAttribute("username",username);
                session.setAttribute("uid",uid);
                out.write("true");
                out.close();
            }else {
                out.write("false");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
