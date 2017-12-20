package cn.edu.hit.weibo.controller;

import cn.edu.hit.weibo.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class RegisterController {
    private UserService us = new UserService();
    public void register(HttpServletRequest request, HttpServletResponse response){
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        response.setContentType("text/html;charset=UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("用户名："+username);
        System.out.println("密码："+password);
        boolean flag = us.register(username,password);
        if(flag){
            System.out.println("成功注册");
            out.write("true");
        }else {
            out.write("false");
        }
    }
}
