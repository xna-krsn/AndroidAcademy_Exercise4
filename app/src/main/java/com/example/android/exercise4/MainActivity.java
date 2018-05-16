package com.example.android.exercise4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onAsyncTaskActivityClick(View view) {
        startActivity(new Intent(this, AsyncTaskActivity.class));
    }

    public void onThreadsActivityClick(View view) {
        startActivity(new Intent(view.getContext(), ThreadsActivity.class));
    }
}
