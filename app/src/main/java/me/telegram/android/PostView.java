package me.telegram.android;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PostView extends LinearLayout {
    public PostView(Context context){
        super(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.post_view, this);
    }

    public void setTitle(String title) {
        TextView titleView = findViewById(R.id.title);
        titleView.setText(title);
        invalidate();
        requestLayout();
    }

    public void setBody(String body) {
        TextView bodyView = findViewById(R.id.body);
        bodyView.setText(body);
        invalidate();
        requestLayout();
    }
}