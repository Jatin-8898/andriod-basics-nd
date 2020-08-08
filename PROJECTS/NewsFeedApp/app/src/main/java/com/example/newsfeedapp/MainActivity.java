package com.example.newsfeedapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>> {

    private static final String LOG_TAG = MainActivity.class.getName();

    /**
     * Constant value for the News loader ID. We can choose any integer.
     * This really only comes into play if you're using multiple loaders.
     */
    public static final int NEWS_LOADER_ID = 1;

    /** URL for earthquake data from the Guardian API **/
    private static final String BASE_URL = "https://content.guardianapis.com/search?";

    private NewsAdapter newsAdapter;
    private ProgressBar progressBar;
    private TextView emptyTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the List view by ID
        ListView newsListView = findViewById(R.id.list);

        // Get the ProgressBar
        progressBar = findViewById(R.id.progress_bar);

        // Get the emptyTextView
        emptyTextView = findViewById(R.id.empty_view);
        newsListView.setEmptyView(emptyTextView);

        // Set the NewsAdapter
        newsAdapter = new NewsAdapter(this, new ArrayList<News>());
        newsListView.setAdapter(newsAdapter);

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        if (isConnected) {
            LoaderManager loaderManager = getSupportLoaderManager();
            loaderManager.initLoader(NEWS_LOADER_ID, null, this);

        } else {
            progressBar.setVisibility(View.GONE);
            emptyTextView.setText(R.string.no_internet_connection);
        }

        newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                News currentNews = newsAdapter.getItem(position);
                assert currentNews != null;
                Uri NewsUri = Uri.parse(currentNews.getArticleURL());
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, NewsUri);
                startActivity(websiteIntent);
            }
        });
    }

    @NonNull
    @Override
    public Loader<List<News>> onCreateLoader(int id, @Nullable Bundle args) {
        Uri baseUri = Uri.parse(BASE_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();
        uriBuilder.appendQueryParameter("page-size", "10");
        uriBuilder.appendQueryParameter("show-tags", "contributor");
        uriBuilder.appendQueryParameter("show-fields", "thumbnail,byline");
        uriBuilder.appendQueryParameter("api-key", "test");

        return new NewsLoader(this, uriBuilder.toString());
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<News>> loader, List<News> news) {
        progressBar.setVisibility(View.GONE);
        emptyTextView.setText(R.string.no_news);

        // Clear the adapter of previous news data
        newsAdapter.clear();

        // If there is a valid list of {@link article}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (news != null && !news.isEmpty()) {
            newsAdapter.addAll(news);
            getSupportLoaderManager().restartLoader(0, null, this);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<News>> loader) {
        newsAdapter.clear();
    }
}