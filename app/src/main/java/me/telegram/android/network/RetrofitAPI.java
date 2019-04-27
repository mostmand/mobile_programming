package me.telegram.android.network;
import java.util.ArrayList;

import me.telegram.android.Comment;
import me.telegram.android.Post;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitAPI {

    @GET("/posts/")
    Call<ArrayList<Post>> getPosts();

    @GET("/comments")
    Call<ArrayList<Comment>> getComments(@Query("postId") Long id);




}
