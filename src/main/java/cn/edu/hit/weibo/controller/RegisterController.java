package cn.edu.hit.weibo.controller;

import cn.edu.hit.weibo.service.UserService;
import cn.edu.hit.weibo.util.ResponseUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class RegisterController {
    private static UserService us = new UserService();
    public static void register(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("text/html;charset=UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

//        System.out.println("用户名："+username);
//        System.out.println("密码："+password);
        boolean flag = us.register(username,password);
        ResponseUtil.sendBooleanResponse(response,flag);

    }
}
