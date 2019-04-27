package me.telegram.android;

import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

import me.telegram.android.network.RetrofitAPI;
import me.telegram.android.network.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConnectionManager {

    private static final ConnectionManager instance = new ConnectionManager();

    private ConnectionManager() {
    }

    public static ConnectionManager getInstance() {
        return instance;
    }

    public ArrayList<Post> loadPosts() {

        RetrofitAPI client = RetrofitClient.getClient().create(RetrofitAPI.class);
        Call<ArrayList<Post>> call = client.getPosts();
        ArrayList<Post> posts =new ArrayList<>();
        try {
           posts = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return posts;

    }

    public ArrayList<Comment> loadComments(Long postId) {

        RetrofitAPI client = RetrofitClient.getClient().create(RetrofitAPI.class);
        Call<ArrayList<Comment>> call = client.getComments(postId);
        ArrayList<Comment> comments =new ArrayList<>();
        try {
            comments = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return comments;
    }

}
