package cn.edu.hit.weibo.controller;

import cn.edu.hit.weibo.util.XMLParser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

@WebServlet("*.do")
public class MainServlet extends HttpServlet {
    private XMLParser xmlParser;
    public MainServlet() {
        xmlParser = new XMLParser();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        distribute(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        distribute(req,resp);
    }

    private void distribute(HttpServletRequest req, HttpServletResponse resp){
        String path = req.getServletPath().split("/")[1].split("\\.")[0];
        System.out.println(path);
        Method method = xmlParser.getMethod(path);
        try {
            method.invoke(null,req,resp);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
