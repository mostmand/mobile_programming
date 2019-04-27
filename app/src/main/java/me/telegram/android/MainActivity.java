package me.telegram.android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity implements Observer, OnItemClickListener {
    private boolean isListView;
    private RecyclerView recyclerView;
    private PostRecyclerViewAdapter mAdapter;
    private LinearLayoutManager linearLayoutManager;
    private GridLayoutManager gridLayoutManager;
    private ArrayList<Post> posts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        isListView = true;
        NotificationCenter.getInstance().getPostsLoadedEvent().addObserver(this);

        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.grid);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                gridLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        mAdapter = new PostRecyclerViewAdapter(getApplicationContext(), posts, this);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);

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
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                posts.clear();
                posts.addAll(MessageController.getInstance(MainActivity.this).posts);
                mAdapter.notifyDataSetChanged();
            }
        });

    }

    private void toggleView() {
        this.isListView = !isListView;
        if (isListView) {
            recyclerView.setLayoutManager(linearLayoutManager);
        } else {
            recyclerView.setLayoutManager(gridLayoutManager);
        }
    }

    @Override
    public void onItemClicked(long id) {

        Intent commentsIntent = new Intent(getApplicationContext(), CommentsActivity.class);
        commentsIntent.putExtra("post_id",id);
        startActivity(commentsIntent);

    }
}

interface OnItemClickListener {
    public void onItemClicked(long id);
}