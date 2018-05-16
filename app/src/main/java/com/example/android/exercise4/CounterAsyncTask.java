package com.example.android.exercise4;

import android.os.AsyncTask;
import android.os.SystemClock;

public class CounterAsyncTask extends AsyncTask<Integer, Integer, Integer> {
    private final IAsyncTaskEvents _eventHandler;

    public CounterAsyncTask(IAsyncTaskEvents eventHandler) {
        _eventHandler = eventHandler;
    }

    @Override
    protected Integer doInBackground(Integer... integers) {
        SystemClock.sleep(500);
        int startNumber = integers[0];
        int iterationCount = integers[1];
        for (int i = startNumber; i < iterationCount; i++) {
            if (isCancelled()) break;

            publishProgress(i + 1);
            SystemClock.sleep(500);
        }
        return iterationCount;
    }

    @Override
    protected void onPreExecute() {
        _eventHandler.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        _eventHandler.onProgressChanged(values[0]);
    }

    @Override
    protected void onPostExecute(Integer integer) {
        _eventHandler.onPostExecute();
    }

    @Override
    protected void onCancelled() {
        _eventHandler.onCancelled();
    }
}
