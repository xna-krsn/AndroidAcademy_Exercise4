package com.example.android.exercise4;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ThreadsActivity extends AppCompatActivity implements IAsyncTaskEvents {
    private TextView _progress;
    private SimpleAsyncTaskImpl _task;
    private int _startIndex;
    private int _endIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threads);

        _progress = findViewById(R.id.progress);
        _endIndex = getResources().getInteger(R.integer.iteration_count);

        if (savedInstanceState != null) {
            _startIndex = savedInstanceState.getInt(getString(R.string.current_index));
            if (_startIndex > 0) {
                _task = new SimpleAsyncTaskImpl(this);
                _task.execute(_startIndex, _endIndex);
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(getString(R.string.current_index), _startIndex);
        if (_task != null) {
            _task.cancel();
            _task = null;
        }
    }

    public void onThreadCreate(View view) {
        _task = new SimpleAsyncTaskImpl(this);
        _progress.setText(R.string.created);
        _startIndex = 0;
    }

    public void onThreadStart(View view) {
        if (_task == null) return;

        _task.execute(_startIndex, _endIndex);
    }

    public void onThreadCancel(View view) {
        if (_task == null) return;

        _task.cancel();
    }

    @Override
    public void onPreExecute() {
        if (_startIndex == 0)
            _progress.setText(R.string.started);
    }

    @Override
    public void onProgressChanged(int progress) {
        _progress.setText(String.valueOf(progress));
        _startIndex = progress;
    }

    @Override
    public void onPostExecute() {
        _progress.setText(R.string.done);
        _task = null;
        _startIndex = 0;
    }

    @Override
    public void onCancelled() {
        _task = null;
        _startIndex = 0;
    }
}
