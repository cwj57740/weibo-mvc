package cn.edu.hit.weibo.dao;

import cn.edu.hit.weibo.util.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.util.List;

/**
 * The type Dao.
 *
 * @param <T> the type parameter
 */
public class Dao<T> {
    /**
     * Instantiates a new Dao.
     *
     * @param entityClass the entity class
     */
    Dao(Class <T> entityClass){
        this.entityClass = entityClass;
    }

    /**
     * The Entity class.
     */
    Class <T> entityClass;

    /**
     * Update t boolean.
     *
     * @param sql    the sql
     * @param params the params
     * @return the boolean
     */
    public boolean updateT(String sql,Object ... params){
        try {
            QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
            qr.update(sql,params);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Add t int.
     *
     * @param sql    the sql
     * @param params the params
     * @return the int
     */
    public int addT(String sql,Object ... params){
        try {
            QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
            Long id =  (Long) qr.insert(sql, new ScalarHandler<Long>(), params);
            return id.intValue();
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }


    /**
     * Get t by params t.
     *
     * @param sql    the sql
     * @param params the params
     * @return the t
     */
    public T getTByParams(String sql,Object ... params){
        QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
        try {
            return qr.query(sql, new BeanHandler<T>(getClazz()),params);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Get t list by params list.
     *
     * @param sql    the sql
     * @param params the params
     * @return the list
     */
    public List<T> getTListByParams(String sql, Object ... params){
        QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
        try {
            return qr.query(sql, new BeanListHandler<>(getClazz()),params);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    private Class<T> getClazz(){
        return entityClass;
    }

    /**
     * 通过反射,获得定义Class时声明的父类的范型参数的类型.
     * 如public BookManager extends GenricManager<Book>
     *
     * @param clazz clazz The class to introspect
     * @param index the Index of the generic ddeclaration,start from 0.
     */

}
