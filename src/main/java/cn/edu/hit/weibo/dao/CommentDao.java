package cn.edu.hit.weibo.dao;

import cn.edu.hit.weibo.model.Comment;

import java.util.List;

public class CommentDao {
    private Dao<Comment> dao = new Dao<>(Comment.class);

    public int addComment(Comment comment){
        String sql = "insert into comment (bid, uid, content) values (?, ?, ?)";
        return dao.addT(sql,comment.getBid(),comment.getUid(),comment.getContent());
    }

    public List<Comment> getCommentListByBid(int bid,int index,int num){
        String sql = "select * from comment where bid = ? limit ?, ?";
        return dao.getTListByParams(sql,bid,index,num);
    }
}
