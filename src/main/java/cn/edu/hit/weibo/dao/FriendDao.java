package cn.edu.hit.weibo.dao;

import cn.edu.hit.weibo.model.Friend;
import cn.edu.hit.weibo.model.User;

import java.util.List;

public class FriendDao {
    private Dao<Friend> dao = new Dao<>(Friend.class);

    public boolean addFriend(Friend friend){
        String sql = "insert into Friend (uid, friendid) values (?, ?)";
        return dao.updateT(sql,friend.getUid(),friend.getFriendid());
    }

    public List<Friend> getFriendList(int uid,int start,int length){
        String sql = "select * from Friend where uid = ? order by id ASC limit (?, ?)";
        return dao.getTListByParams(sql,uid,start,length);
    }
}
