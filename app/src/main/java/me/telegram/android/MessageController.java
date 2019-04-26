package me.telegram.android;

import java.util.Calendar;

import android.content.Context;

import java.net.ContentHandler;
import java.util.ArrayList;

public class MessageController {

    private static final MessageController instance = new MessageController();

    private long lastUpdate;

    private Context context;

    private MessageController() {
    }

    public static MessageController getInstance(Context context) {
        instance.context = context;
        return instance;
    }

    public ArrayList<Post> posts = new ArrayList<>();

    public ArrayList<Comment> comments = new ArrayList<>();

    private void setPosts(ArrayList<Post> posts) {
        this.posts = new ArrayList<>(posts);
        NotificationCenter.getInstance().dataLoaded();
    }

    public void fetchPosts() {
        if (true) {
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
                    setPosts(res);
                }
            });
            network.start();
        }

    }
}
