package me.telegram.android.network;
import java.util.ArrayList;

import me.telegram.android.Comment;
import me.telegram.android.Post;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitAPI {

    @GET("/posts/")
    Call<ArrayList<Post>> getPosts();

    @GET("/posts/{code}/comments/")
    Call<ArrayList<Comment>> getComments(@Path("id") Long id);




}
