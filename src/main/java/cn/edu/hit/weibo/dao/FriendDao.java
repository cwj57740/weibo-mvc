package cn.edu.hit.weibo.dao;

import cn.edu.hit.weibo.model.Friend;
import cn.edu.hit.weibo.model.User;

import java.util.List;

public class FriendDao {
    private Dao<Friend> dao = new Dao<>(Friend.class);

    public int addFriend(Friend friend){
        String sql = "insert into Friend (uid, friendid) values (?, ?)";
        return dao.addT(sql,friend.getUid(),friend.getFriendid());
    }

    public List<Friend> getFriendList(int uid,int start,int length){
        String sql = "select * from Friend where uid = ? order by id ASC limit ?, ?";
        return dao.getTListByParams(sql,uid,start,length);
    }

    public int getFriendListCount(int uid){
        String sql = "select count(friendid) from friend where uid = ?";
        return Integer.parseInt(dao.getColumnByParams(sql,"count(friendid)",uid).toString());
    }
}
