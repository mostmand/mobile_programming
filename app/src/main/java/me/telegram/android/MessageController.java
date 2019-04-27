package me.telegram.android;

import java.util.Calendar;

import android.content.Context;
import android.content.SharedPreferences;

import java.net.ContentHandler;
import java.util.ArrayList;

public class MessageController {

    private static final MessageController instance = new MessageController();

    private static SharedPreferences sharedPref;

    private long lastUpdate;

    private Context context;

    private MessageController() {
    }

    public static MessageController getInstance(Context context) {
        instance.context = context;
        sharedPref = context.getSharedPreferences("tmpShared", Context.MODE_PRIVATE);
        return instance;
    }

    public ArrayList<Post> posts = new ArrayList<>();

    public ArrayList<Comment> comments = new ArrayList<>();

    private void setPosts(ArrayList<Post> posts) {
        this.posts = new ArrayList<>(posts);
        NotificationCenter.getInstance().dataLoaded();
    }

    public void fetchPosts() {
        boolean fromCache = isFromCache();
        if (fromCache) {
            Thread storage = new Thread(new Runnable() {
                @Override
                public void run() {
                    ArrayList<Post> res = StorageManager.getInstance(context).loadPosts();
                    setPosts(res);
                }
            });
            storage.start();
        } else {
            Thread network = new Thread(new Runnable() {
                @Override
                public void run() {
                    ArrayList<Post> res = ConnectionManager.getInstance().load(1);
                    StorageManager.getInstance(context).savePosts(res);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putLong(context.getString(R.string.current_time), System.currentTimeMillis());
                    editor.commit();
                    setPosts(res);
                }
            });
            network.start();
        }

    }

    private boolean isFromCache() {
        long lastUpdated = sharedPref.getLong(context.getString(R.string.current_time), System.currentTimeMillis());
        if (System.currentTimeMillis() - lastUpdated > 5 * 60 * 1000L)
            return false;
        return true;
    }
}
