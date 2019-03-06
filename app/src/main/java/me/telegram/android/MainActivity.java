package me.telegram.android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                for (int message:MessageController.getInstance().fetch(true)) {
                    TextView textView = new TextView(MainActivity.this);
                    textView.setText(message + "");
                    list.addView(textView);
                }
            }
        });

        findViewById(R.id.getBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int message:MessageController.getInstance().fetch(false)) {
                    TextView textView = new TextView(MainActivity.this);
                    textView.setText(message + "");
                    list.addView(textView);
                }
            }
        });
    }
}
