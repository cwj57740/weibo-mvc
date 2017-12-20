package cn.edu.hit.weibo.controller;

import cn.edu.hit.weibo.service.FriendService;
import cn.edu.hit.weibo.service.UserService;
import cn.edu.hit.weibo.util.PageUtil;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

public class FriendController {
    UserService userService = new UserService();
    FriendService friendService = new FriendService();
    public void showFriendList(HttpServletRequest request, HttpServletResponse response){
        String username = request.getParameter("username");
        int friendpagenum = Integer.parseInt(request.getParameter("friendpagenum"));

        int uid = userService.getUidByUsername(username);
        int total = friendService.getFriendListCount(uid);
        int maxpage = PageUtil.getMaxPageNum(total);
        int length = PageUtil.EVERY_PAGE_ITEMS;
        Map<String,String> friendmap = friendService.getFriendList(uid,friendpagenum,length);

        Map<String,Object> resultMap = new LinkedHashMap<>();
        resultMap.put("friendlist",friendmap);
        resultMap.put("total",total);
        resultMap.put("maxpage",maxpage);
        Gson gson = new Gson();
        String json = gson.toJson(resultMap);

        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.write(json);
    }
}
