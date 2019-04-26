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

    private void addPosts(ArrayList<Post> messages){
        this.posts.addAll(messages);
        NotificationCenter.getInstance().dataLoaded();
    }

    private void setMessages(ArrayList<Post> posts){
        this.posts = new ArrayList<>(posts);
        NotificationCenter.getInstance().dataLoaded();
    }




    public void fetchPosts(boolean fromCache) {
        if (fromCache) {

            Thread storage = new Thread(new Runnable() {
                @Override
                public void run() {
                    ArrayList<Post> res = StorageManager.getInstance(context).loadPosts();
                    addPosts(res);
                }
            });
            storage.start();
        }

    }
}
