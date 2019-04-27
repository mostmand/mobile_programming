package me.telegram.android;
import android.content.Context;
import java.util.ArrayList;

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

    public Post loadPost(Long postId) {
        DaoSession daoSession = ((App)this.context.getApplicationContext()).getDaoSession();
        PostDao postDao = daoSession.getPostDao();
        return postDao.queryBuilder().where(CommentDao.Properties.PostId.eq(postId)).unique();
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
