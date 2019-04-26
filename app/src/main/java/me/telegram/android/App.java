package me.telegram.android;

import android.app.Application;

public class App extends Application {
    private DaoSession mDaoSession;

    @Override
    public void onCreate(){
        super.onCreate();
        mDaoSession = new DaoMaster(
                new DaoMaster.DevOpenHelper(this, "telegram.db").getWritableDb()).newSession();


    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }
}
