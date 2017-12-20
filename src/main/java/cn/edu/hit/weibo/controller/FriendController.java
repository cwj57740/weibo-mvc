package cn.edu.hit.weibo.controller;

import cn.edu.hit.weibo.service.FriendService;
import cn.edu.hit.weibo.service.UserService;
import cn.edu.hit.weibo.util.ModelUtil;
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

        try {
            ModelUtil.sendJsonResponse(response,friendmap,total,maxpage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
