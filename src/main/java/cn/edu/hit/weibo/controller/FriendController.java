package cn.edu.hit.weibo.controller;

import cn.edu.hit.weibo.model.Friend;
import cn.edu.hit.weibo.service.FriendService;
import cn.edu.hit.weibo.service.UserService;
import cn.edu.hit.weibo.util.ResponseUtil;
import cn.edu.hit.weibo.util.PageUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class FriendController {
    private static UserService userService = new UserService();
    private static FriendService friendService = new FriendService();
    private static Friend friend = new Friend();

    public static void showFriendList(HttpServletRequest request, HttpServletResponse response){
        String username = request.getParameter("username");
        int friendpagenum = Integer.parseInt(request.getParameter("friendpagenum"));

        int uid = userService.getUidByUsername(username);
        int total = friendService.getFriendListCount(uid);
        int maxpage = PageUtil.getMaxPageNum(total);
        int length = PageUtil.EVERY_PAGE_ITEMS;
        Map<String,String> friendmap = friendService.getFriendList(uid,friendpagenum,length);

        try {
            ResponseUtil.sendJsonResponse(response,friendmap,total,maxpage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addFriend(HttpServletRequest request, HttpServletResponse response) {

        int fid = Integer.parseInt(request.getParameter("fid"));
        int uid = (int) request.getSession().getAttribute("uid");
        friend.setUid(uid);
        friend.setFriendid(fid);
        boolean flag = friendService.addFriend(friend);
        ResponseUtil.sendBooleanResponse(response,flag);
    }
}
