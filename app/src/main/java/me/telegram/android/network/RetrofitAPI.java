package me.telegram.android.network;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitAPI {

    @GET("/posts/")
    Call<ArrayList<HashMap<Object,Object>>> getPosts();

    @GET("/posts/{code}/comments/")
    Call<ArrayList<HashMap<Object,Object>>> getComments(@Path("id") String id);




}
