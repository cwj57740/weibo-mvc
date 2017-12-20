package cn.edu.hit.weibo.dao;

import cn.edu.hit.weibo.model.Comment;

import java.util.List;

public class CommentDao {
    private Dao<Comment> dao = new Dao<>(Comment.class);

    private int addComment(Comment comment){
        String sql = "insert into comment (bid, uid, content) values (?, ?, ?)";
        return dao.addT(sql,comment.getBid(),comment.getUid(),comment.getContent());
    }

//    private List<Comment> getCommentList(int bid){
//        String sql = "select *"
//    }

}
