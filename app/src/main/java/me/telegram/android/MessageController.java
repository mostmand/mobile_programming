package me.telegram.android;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.widget.Toast;

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
        NotificationCenter.getInstance().postsLoaded();
    }

    public void fetchPosts() {
        boolean fromCache = shouldPostsLoadFromCache();
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
                    ArrayList<Post> res = ConnectionManager.getInstance().loadPosts();
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

    private void setComments(ArrayList<Comment> comments) {
        this.comments = new ArrayList<>(comments);
        NotificationCenter.getInstance().commentsLoaded();
    }

    public void fetchComments(final Long postId) {
        boolean fromCache = shouldCommentsLoadFromCache(postId);
        if (fromCache) {
            Thread storage = new Thread(new Runnable() {
                @Override
                public void run() {
                    ArrayList<Comment> res = StorageManager.getInstance(context).loadComments(postId);
                    setComments(res);
                }
            });
            storage.start();
        } else {
            Thread network = new Thread(new Runnable() {
                @Override
                public void run() {
                    ArrayList<Comment> res = ConnectionManager.getInstance().loadComments(postId);
                    StorageManager.getInstance(context).saveComments(res, postId);
                    setComments(res);
                }
            });
            network.start();
        }

    }

    private boolean shouldPostsLoadFromCache() {
        long lastUpdated = sharedPref.getLong(context.getString(R.string.current_time), 0);
        boolean isNetworkNotConnected = !isNetworkConnected();
        boolean isDataValid = (System.currentTimeMillis() - lastUpdated < 5 * 60 * 1000L);
        if (!isDataValid && isNetworkNotConnected)
            Toast.makeText(this.context, "couldn't load posts:(", Toast.LENGTH_LONG).show();
        return isDataValid || isNetworkNotConnected;
    }


    private boolean shouldCommentsLoadFromCache(Long postId) {
        Post post = StorageManager.getInstance(context).loadPost(postId);
        long lastUpdated = 0;
        if (post != null && post.getCommentUpdated() != null)
            lastUpdated = post.getCommentUpdated();

        boolean isNetworkNotConnected = !isNetworkConnected();
        boolean isDataValid = (System.currentTimeMillis() - lastUpdated < 5 * 60 * 1000L);
        if (!isDataValid && isNetworkNotConnected)
            Toast.makeText(this.context, "couldn't load comments:(", Toast.LENGTH_LONG).show();
        return isDataValid || isNetworkNotConnected;
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) this.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm != null && cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();

    }

}
