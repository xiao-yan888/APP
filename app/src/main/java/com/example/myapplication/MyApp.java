package com.example.myapplication;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import cn.hnshangyu.testgreendao.greendao.DaoMaster;
import cn.hnshangyu.testgreendao.greendao.DaoSession;
import cn.hnshangyu.testgreendao.greendao.UserDao;

public class MyApp extends Application {
    private static DaoSession daoSession;
    @Override
    public void onCreate() {
        super.onCreate();
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this, "shuju.db");
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        daoSession = daoMaster.newSession();


        //实现创建
        SQLiteDatabase database = new DaoMaster.DevOpenHelper(this, "stui.db").getWritableDatabase();

        //连接
        DaoMaster master = new DaoMaster(database);

        //操作类
        DaoSession daoSession = master.newSession();
        UserDao userDao = daoSession.getUserDao();


    }
    public static DaoSession getDaoSession(){
        return daoSession;
    }
}
