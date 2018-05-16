package com.example.android.exercise4;

import android.os.SystemClock;

public class SimpleAsyncTaskImpl extends SimpleAsyncTask<Integer> {
    private final IAsyncTaskEvents _eventHandler;

    public SimpleAsyncTaskImpl(IAsyncTaskEvents eventHandler) {
        _eventHandler = eventHandler;
    }

    @Override
    public void onPreExecute() {
        _eventHandler.onPreExecute();
    }

    @Override
    public Integer doInBackground(Integer... params) {
        SystemClock.sleep(500);
        int startNumber = params[0];
        int iterationCount = params[1];
        for (int i = startNumber; i < iterationCount; i++) {
            if (isCancelled()) break;

            publishProgress(i + 1);
            SystemClock.sleep(500);
        }
        return iterationCount;
    }

    @Override
    public void onPostExecute() {
        _eventHandler.onPostExecute();
    }

    @Override
    public void onProgressUpdate(Integer... params) {
        _eventHandler.onProgressChanged(params[0]);
    }

    @Override
    protected void onCancelled() {
        _eventHandler.onCancelled();
    }
}
