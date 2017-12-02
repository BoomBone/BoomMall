package com.boombone7.orange.ec.database;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

/**
 * @author Ting
 * @date 2017/12/2.
 */

public class DatabaseManager {
    private DaoSession mDaoSession = null;
    private UserProfileDao mDao = null;

    private DatabaseManager() {
    }

    private static final class LazyHolder{
        private static final DatabaseManager INSTANCE = new DatabaseManager();
    }

    public static DatabaseManager getInstance(){
        return LazyHolder.INSTANCE;
    }

    public DatabaseManager init(Context context){
        initDao(context);
        return this;
    }

    private void initDao(Context context){
        final ReleaseOpenHelper helper = new ReleaseOpenHelper(context, "boom_ec");
        final Database db = helper.getWritableDb();
        mDaoSession = new DaoMaster(db).newSession();
        mDao = mDaoSession.getUserProfileDao();
    }

    public final UserProfileDao getDao(){
        return mDao;
    }

}
