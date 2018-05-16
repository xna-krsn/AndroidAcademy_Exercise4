package com.example.android.exercise4;

import android.os.Handler;
import android.os.Looper;

public abstract class SimpleAsyncTask<T> {
    private boolean _isCancelled;
    private Thread _thread;

    public void execute(final T... params) {
        runOnUiThread(
                new Runnable() {
                    @Override
                    public void run() {
                        onPreExecute();
                        _thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                doInBackground(params);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        onPostExecute();
                                    }
                                });
                            }
                        });
                        _thread.start();
                    }
                }
        );
    }

    public void cancel() {
        _isCancelled = true;
        _thread.interrupt();

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                onCancelled();
            }
        });
    }

    public boolean isCancelled() {
        return _isCancelled;
    }

    protected abstract void onPreExecute();
    protected abstract T doInBackground(T... params);
    protected abstract void onPostExecute();
    protected abstract void onProgressUpdate(T... params);
    protected abstract void onCancelled();

    protected void publishProgress(final T... params) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                onProgressUpdate(params);
            }
        });
    }

    private void runOnUiThread(Runnable runnable){
        new Handler(Looper.getMainLooper()).post(runnable);
    }
}
