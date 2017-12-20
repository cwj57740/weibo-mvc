package cn.edu.hit.weibo.controller;

import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

public class TestController {
    public static void execute(HttpServletRequest request, HttpServletResponse response){
        try {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            Map<String,String> map = new LinkedHashMap<>();
            map.put("1","hh");
            map.put("2","666");
            Gson gson = new Gson();
            String json = gson.toJson(map);
            out.print(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
