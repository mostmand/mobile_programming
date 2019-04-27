package me.telegram.android;
import android.content.Context;

import org.greenrobot.greendao.query.DeleteQuery;

import java.util.ArrayList;
import java.util.List;

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
            DeleteQuery<Post> postsDeleteQuery = postDao.queryBuilder().buildDelete();
            postsDeleteQuery.executeDeleteWithoutDetachingEntities();
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

    public int saveComments(ArrayList<Comment> comments, Long postId){
        DaoSession daoSession = ((App)this.context.getApplicationContext()).getDaoSession();
        CommentDao commentDao = daoSession.getCommentDao();
        PostDao postDao = daoSession.getPostDao();
        try {
            DeleteQuery<Comment> commentsDeleteQuery = commentDao.queryBuilder().where(CommentDao.Properties.PostId.eq(postId)).buildDelete();
            commentsDeleteQuery.executeDeleteWithoutDetachingEntities();
            for (Comment comment:comments) {
                commentDao.insert(comment);
            }
        }
        catch (Exception e){
            throw new RuntimeException("ریدی!");
        }
        finally {

            List<Post> posts = postDao.queryBuilder()
                    .where(PostDao.Properties.Id.eq(postId))
                    .list();
            Post post = posts.get(0);
            post.setCommentUpdated(System.currentTimeMillis());
            postDao.update(post);
            daoSession.clear();
            return 1;
        }
    }
}
