package com.example.myapplication;

import android.content.Context;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import cn.hnshangyu.testgreendao.greendao.DaoSession;
import cn.hnshangyu.testgreendao.greendao.UserDao;

public class StudentDaoUtil {

    private static final boolean DUBUG = true;
    //private DaoManager manager;
    private UserDao studentDao;
    private DaoSession daoSession;

    public StudentDaoUtil(Context context) {

        daoSession = MyApp.getDaoSession();
        //manager.setDebug(DUBUG);
    }

    /**
     * 添加数据，如果有重复则覆盖
     */
    public void insertStudent(User student) {
        MyApp.getDaoSession().insertOrReplace(student);
    }

    /**
     * 添加多条数据，需要开辟新的线程
     */
    public void insertMultStudent(final List<User> students) {
        MyApp.getDaoSession().runInTx(new Runnable() {
            @Override
            public void run() {
                for (User student : students) {
                    MyApp.getDaoSession().insertOrReplace(student);
                }
            }
        });
    }


    /**
     * 删除数据
     */
    public void deleteStudent(User student) {
        MyApp.getDaoSession().delete(student);
    }

    /**
     * 删除全部数据
     */
    public void deleteAll(Class cls) {
        MyApp.getDaoSession().deleteAll(cls);

    }

    /**
     * 更新数据
     */
    public void updateStudent(User student) {
        MyApp.getDaoSession().update(student);
    }

    /**
     * 按照主键返回单条数据
     */
    public User listOneStudent(long key) {
        return MyApp.getDaoSession().load(User.class, key);
    }

    /**
     * 根据指定条件查询数据
     */
    public List<User> queryStudent() {
        //查询构建器
        QueryBuilder<User> builder = MyApp.getDaoSession().queryBuilder(User.class);
        List<User> list = builder.where(UserDao.Properties.Id.ge(1)).where(UserDao.Properties.Name.like("张三")).list();
        return list;
    }

    /**
     * 查询全部数据
     */
    public List<User> queryAll() {
        return MyApp.getDaoSession().loadAll(User.class);
    }

}
