package cn.edu.hit.weibo.service;

import cn.edu.hit.weibo.dao.FriendDao;
import cn.edu.hit.weibo.dao.UserDao;
import cn.edu.hit.weibo.model.Friend;
import cn.edu.hit.weibo.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FriendService {
    private FriendDao friendDao = new FriendDao();
    private UserDao userDao = new UserDao();
    private Friend friend;

    public Friend getFriend() {
        return friend;
    }

    public void setFriend(Friend friend) {
        this.friend = friend;
    }

    public Map<String,String> getFriendList(int uid, int start, int length){
        Map<String,String> map = new HashMap<>();
        List<Friend> friendList = friendDao.getFriendList(uid,start,length);
        for(Friend friend : friendList){
            User user = userDao.getUserById(friend.getFriendid());
            map.put(Integer.toString(user.getUid()),user.getUsername());
        }
        return map;
    }

    public int getFriendListCount(int uid){
        return friendDao.getFriendListCount(uid);
    }

    public boolean addFriend(Friend friend){
        try {
            int id = friendDao.addFriend(friend);
            this.friend = friend;
            this.friend.setFriendid(id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
