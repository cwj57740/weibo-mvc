package cn.edu.hit.weibo.service;

import cn.edu.hit.weibo.dao.UserDao;
import cn.edu.hit.weibo.model.User;

public class UserService {
    private UserDao userDao = new UserDao();
    public User login(String username, String password){
        User user = userDao.getUserByUsername(username);
        if(user!=null){
            if(user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
    }
    public boolean register(String username,String password){
        User user = userDao.getUserByUsername(username);
        if(user==null){
            user = new User();
            user.setUsername(username);
            user.setPassword(password);
            boolean b = userDao.addUser(user);
            return b;
        }
        return false;
    }

    public int getUidByUsername(String username){
        User user = userDao.getUserByUsername(username);
        int uid = user.getUid();
        return uid;
    }

    public User getUserByUid(int uid){
        return userDao.getUserById(uid);
    }
}
