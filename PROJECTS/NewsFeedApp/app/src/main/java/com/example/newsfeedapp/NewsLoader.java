package com.example.newsfeedapp;

import android.content.Context;
import androidx.loader.content.AsyncTaskLoader;
import java.util.List;

public class NewsLoader extends AsyncTaskLoader<List<News>> {

    /** Tag for log messages */
    private static final String LOG_TAG = NewsLoader.class.getName();

    /** Query URL */
    private String url;

    public NewsLoader(Context context, String url) {
        super(context);
        this.url = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    /**
     * This is on a background thread.
     */
    @Override
    public List<News> loadInBackground() {
        if (url == null) {
            return null;
        }
        // Perform the network request, parse the response, and extract a list of earthquakes.
        return QueryUtils.fetchNewsData(url);
    }
}