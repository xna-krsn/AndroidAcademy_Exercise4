package com.example.android.exercise4;

public interface IAsyncTaskEvents {
    void onPreExecute();
    void onProgressChanged(int progress);
    void onPostExecute();
    void onCancelled();
}
