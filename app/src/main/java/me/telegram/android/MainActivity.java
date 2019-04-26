package me.telegram.android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import me.telegram.android.network.RetrofitAPI;
import me.telegram.android.network.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements Observer {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RetrofitAPI client = RetrofitClient.getClient().create(RetrofitAPI.class);
        Call<ArrayList<HashMap<Object, Object>>> call2 = client.getPosts();
        call2.enqueue(new Callback<ArrayList<HashMap<Object, Object>>>() {
            @Override
            public void onResponse( Call<ArrayList<HashMap<Object, Object>>> call,  Response<ArrayList<HashMap<Object, Object>>> response) {
                Log.e("fas",""+response.body().size());
//                if (response.isSuccessful()) {
//                    ProductHandler.getInstance().setItems(getApplicationContext(), response.body());
//                    productsReceived = true;
//                    goToMainActivity();
//                } else {
//                    retry();
//                }

            }

            @Override
            public void onFailure(Call<ArrayList<HashMap<Object, Object>>> call, Throwable t) {
//                retry();
            }
        });


        NotificationCenter.getInstance().getDataLoadedEvent().addObserver(this);

        setContentView(R.layout.activity_main);
        final LinearLayout list = (LinearLayout) findViewById(R.id.list);
        findViewById(R.id.clearBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.removeAllViews();
            }
        });

        findViewById(R.id.refreshBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MessageController.getInstance(MainActivity.this).fetchPosts(true);
            }
        });

        findViewById(R.id.getBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MessageController.getInstance(MainActivity.this).fetchPosts(false);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        NotificationCenter.getInstance().getDataLoadedEvent().deleteObserver(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);

        outState.putInt("file", StorageManager.getInstance(this).getIdx());
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);

        if (savedInstanceState != null)
            StorageManager.getInstance(this).save(savedInstanceState.getInt("file"));
    }

    @Override
    public void update(Observable o, Object arg) {
        final LinearLayout list = (LinearLayout) findViewById(R.id.list);

//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                list.removeAllViews();
//                for (Integer message: MessageController.getInstance(MainActivity.this).posts) {
//                    TextView textView = new TextView(MainActivity.this);
//                    textView.setText(message + "");
//                    list.addView(textView);
//                }
//            }
//        });

    }


}
