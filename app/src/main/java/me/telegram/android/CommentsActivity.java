package me.telegram.android;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Observable;
import java.util.Observer;

public class CommentsActivity extends AppCompatActivity implements Observer {
    private Long postId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        NotificationCenter.getInstance().getCommentsLoadedEvent().addObserver(this);

        setContentView(R.layout.activity_comments);

        postId = getIntent().getExtras().getLong("post_id");
        MessageController.getInstance(CommentsActivity.this).fetchComments(postId);

        TextView postDetails = findViewById(R.id.postDetails);
        postDetails.setText(getApplicationContext().getString(R.string.postDetails, postId));

        findViewById(R.id.teamMembersBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TeamMembersDialog.show(v.getContext());
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        NotificationCenter.getInstance().getCommentsLoadedEvent().deleteObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        final LinearLayout list = findViewById(R.id.list);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                updateListOfComments(list);
            }
        });
    }

    private void updateListOfComments(LinearLayout list) {
        list.removeAllViews();
        for (Comment comment: MessageController.getInstance(CommentsActivity.this).comments) {
            TextView textView = new TextView(CommentsActivity.this);
            textView.setText(comment.getBody());
            list.addView(textView);
        }
    }


}
