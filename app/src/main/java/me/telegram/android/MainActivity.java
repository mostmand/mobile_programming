package me.telegram.android;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity implements Observer {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        NotificationCenter.getInstance().getDataLoadedEvent().addObserver(this);

        setContentView(R.layout.activity_main);
        final LinearLayout list = (LinearLayout) findViewById(R.id.list);
        findViewById(R.id.clearBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.removeAllViews();
            }
        });
        MessageController.getInstance(MainActivity.this).fetchPosts();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        NotificationCenter.getInstance().getDataLoadedEvent().deleteObserver(this);
    }

//    @Override
//    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
//        super.onSaveInstanceState(outState, outPersistentState);
//
//        outState.putInt("file", StorageManager.getInstance(this).getIdx());
//    }
//
//    @Override
//    public void onRestoreInstanceState(Bundle savedInstanceState, PersistableBundle persistentState) {
//        super.onRestoreInstanceState(savedInstanceState, persistentState);
//
//        if (savedInstanceState != null)
//            StorageManager.getInstance(this).save(savedInstanceState.getInt("file"));
//    }

    @Override
    public void update(Observable o, Object arg) {
        final LinearLayout list = (LinearLayout) findViewById(R.id.list);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                list.removeAllViews();
                for (Post post: MessageController.getInstance(MainActivity.this).posts) {
                    TextView textView = new TextView(MainActivity.this);
                    textView.setText(post.getBody());
                    list.addView(textView);
                }
            }
        });

    }


}
