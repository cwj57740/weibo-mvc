package cn.edu.hit.weibo.component;

import cn.edu.hit.weibo.model.Blog;
import cn.edu.hit.weibo.redis.BlogRedis;
import cn.edu.hit.weibo.service.WeiboService;

import java.util.Observable;
import java.util.Observer;

import static cn.edu.hit.weibo.service.WeiboService.event.Delete;
import static cn.edu.hit.weibo.service.WeiboService.event.Update;

public class Cache implements Observer{
    private WeiboService weiboService = new WeiboService();
    private BlogRedis blogRedis = new BlogRedis();
    public Cache(Observable o){
        o.addObserver(this);
        if(o instanceof WeiboService) {
            weiboService = (WeiboService) o;
        }
    }
    @Override
    public void update(Observable o, Object arg) {

        Blog blog = weiboService.getBlog();
        WeiboService.event e = (WeiboService.event)arg;
        if(Update.toString().equals(e.toString())){
            blogRedis.updateSingle(blog);
        }
        if(Delete.toString().equals(e.toString())){
            blogRedis.deleteSingle(blog);
        }


    }
}
