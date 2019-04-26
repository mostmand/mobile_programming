package me.telegram.android;

import android.app.Application;

import org.greenrobot.greendao.database.Database;

public class App extends Application {
    private DaoSession mDaoSession;

    @Override
    public void onCreate(){
        super.onCreate();
        mDaoSession = new DaoMaster(
                new DaoMaster.DevOpenHelper(this, "greendao_app.db").getWritableDb()).newSession();


    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }
}
