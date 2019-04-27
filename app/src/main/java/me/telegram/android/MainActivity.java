package me.telegram.android;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity implements Observer {
    private boolean isListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        isListView = true;

        NotificationCenter.getInstance().getPostsLoadedEvent().addObserver(this);

        setContentView(R.layout.activity_main);

        findViewById(R.id.changeViewBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.toggleView();
            }
        });

        findViewById(R.id.teamMembersBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TeamMembersDialog.show(v.getContext());
            }
        });

        MessageController.getInstance(MainActivity.this).fetchPosts();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        NotificationCenter.getInstance().getPostsLoadedEvent().deleteObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (isListView) {
            updateListView();
        }
        else{
            updateGridView();
        }
    }

    private void updateGridView() {
        final LinearLayout grid = findViewById(R.id.grid);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                grid.removeAllViews();
                for (final Post post : MessageController.getInstance(MainActivity.this).posts) {
                    PostView postView = new PostView(MainActivity.this);

                    postView.setTitle(post.getTitle());
                    postView.setBody(post.getBody());

                    postView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent commentsIntent = new Intent(v.getContext(), CommentsActivity.class);
                            commentsIntent.putExtra("post_id", post.getId());
                            startActivity(commentsIntent);
                        }
                    });

                    grid.addView(postView);
                }
            }
        });
    }

    private void updateListView() {
        final LinearLayout list = findViewById(R.id.list);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                list.removeAllViews();
                for (final Post post : MessageController.getInstance(MainActivity.this).posts) {
                    PostView postView = new PostView(MainActivity.this);
                    postView.setTitle(post.getTitle());
                    postView.setBody(post.getBody());

                    postView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent commentsIntent = new Intent(v.getContext(), CommentsActivity.class);
                            commentsIntent.putExtra("post_id", post.getId());
                            startActivity(commentsIntent);
                        }
                    });

                    list.addView(postView);
                }
            }
        });
    }

    private void toggleView(){
        this.isListView = !isListView;
        if (isListView){
            updateListView();
            findViewById(R.id.grid_scroll).setVisibility(View.GONE);
            findViewById(R.id.list_scroll).setVisibility(View.VISIBLE);
        }
        else{
            updateGridView();
            findViewById(R.id.list_scroll).setVisibility(View.GONE);
            findViewById(R.id.grid_scroll).setVisibility(View.VISIBLE);
        }
    }
}
