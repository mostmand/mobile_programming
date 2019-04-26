package me.telegram.android;

import android.content.Context;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import org.greenrobot.greendao.query.Query;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class StorageManager {
    private Context context;
    private static final StorageManager instance = new StorageManager();
    private StorageManager() {
    }

    public static StorageManager getInstance(Context context) {
        instance.context = context;
        return instance;
    }

    public ArrayList<Post> loadPosts() {
        DaoSession daoSession = ((App)this.context.getApplicationContext()).getDaoSession();
        PostDao postDao = daoSession.getPostDao();
        ArrayList<Post> res = new ArrayList<>();
        res.addAll(postDao.queryBuilder()
                .orderAsc(PostDao.Properties.Id)
                .list());
        return res;
    }

    public ArrayList<Comment> loadComments(Long postId) {
        DaoSession daoSession = ((App)this.context.getApplicationContext()).getDaoSession();
        CommentDao commentDao = daoSession.getCommentDao();
        ArrayList<Comment> res = new ArrayList<>();
        res.addAll(commentDao.queryBuilder()
                .where(CommentDao.Properties.PostId.eq(postId))
                .orderAsc(CommentDao.Properties.Id)
                .list());
        return res;
    }

    public int savePosts(ArrayList<Post> posts){
        DaoSession daoSession = ((App)this.context.getApplicationContext()).getDaoSession();
        PostDao postDao = daoSession.getPostDao();
        try {
            for (Post post:posts) {
                postDao.insert(post);
            }
        }
        catch (Exception e){
            throw new RuntimeException("ریدی!");
        }
        finally {
            return 1;
        }
    }

    public int saveComments(ArrayList<Comment> comments){
        DaoSession daoSession = ((App)this.context.getApplicationContext()).getDaoSession();
        CommentDao commentDao = daoSession.getCommentDao();
        try {
            for (Comment comment:comments) {
                commentDao.insert(comment);
            }
        }
        catch (Exception e){
            throw new RuntimeException("ریدی!");
        }
        finally {
            return 1;
        }
    }
}
