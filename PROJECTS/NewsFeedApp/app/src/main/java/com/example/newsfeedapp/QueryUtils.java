package com.example.newsfeedapp;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public final class QueryUtils {

    /** Tag for the log messages */
    private static final String LOG_TAG = QueryUtils.class.getSimpleName();

    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils() {
    }

    /**
     * Query the Guardian API and return a list of {@link News} objects.
     */
    public static List<News> fetchNewsData(String requestUrl) {
        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        // Extract relevant fields from the JSON response and create a list of {@link News} and Return the list of {@link News}
        return extractFeatureFromJson(jsonResponse);
    }

    /**
     * Returns new URL object from the given string URL.
     */
    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the URL ", e);
        }
        return url;
    }

    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the news JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // Closing the input stream could throw an IOException, which is why
                // the makeHttpRequest(URL url) method signature specifies than an IOException
                // could be thrown.
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    /**
     * Return a list of {@link News} objects that has been built up from
     * parsing the given JSON response.
     */
    private static List<News> extractFeatureFromJson(String newsJSON) {
        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(newsJSON)) {
            return null;
        }

        // Create an empty ArrayList that we can start adding news to
        List<News> news = new ArrayList<>();

        // Try to parse the JSON response string. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            // Create a JSONObject from the JSON response string
            JSONObject baseJsonResponse = new JSONObject(newsJSON);

            // Extract the Response
            JSONObject response = baseJsonResponse.getJSONObject("response");

            // Extract the result from the Results array
            JSONArray results = response.getJSONArray("results");

            // For each news in the newsArray, create an {@link News} object
            for (int i = 0; i < results.length(); i++) {

                // Get a single news at position i within the list of news
                JSONObject currentNews = results.getJSONObject(i);

                // Extract the value for the key called "Article Title"
                String articleTitle = currentNews.getString("webTitle");

                // Extract the value for the key called "Date"
                String publicationDate = currentNews.getString("webPublicationDate");

                DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault());
                Date date = format.parse(publicationDate);

                DateFormat format2 = new SimpleDateFormat("HH:mm | dd MMM yy", Locale.getDefault());
                assert date != null;
                String publishedDate = format2.format(date);

                // Extract the value for the key called "Section Name"
                String sectionName = currentNews.getString("sectionName");

                // Extract the value for the key called "url"
                String url = currentNews.getString("webUrl");

                // Extract the fields
                JSONObject fields = currentNews.getJSONObject("fields");

                // Extract the value for the key called "Author Name"
                String authorName = fields.getString("byline");

                // Extract the value for the key called "Image"
                String image = fields.getString("thumbnail");

                Drawable img = null;
                try {
                    Bitmap bitmap = getBitmapFromURL(image);
                    img = new BitmapDrawable(Resources.getSystem(), bitmap);
                } catch (IOException e) {
                    Log.e(LOG_TAG, "Problem with getting bitmap from URL.", e);
                }

                // Create a new {@link News} object
                News newsResponse = new News(articleTitle, sectionName, authorName, publishedDate, url, img);

                // Add the new {@link News} to the list of news.
                news.add(newsResponse);
            }

        } catch (JSONException | ParseException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash.
            Log.e("QueryUtils", "Problem parsing the news JSON results", e);
        }

        // Return the list of news
        return news;
    }

    private static Bitmap getBitmapFromURL(String imageUrl) throws IOException {
        HttpURLConnection connection = null;
        InputStream input = null;
        try {
            URL url = new URL(imageUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(15000);
            connection.setConnectTimeout(20000);
            connection.setDoInput(true);
            connection.connect();
            input = connection.getInputStream();
            return BitmapFactory.decodeStream(input);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem with getting bitmap form URL.", e);
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            if (input != null) {
                input.close();
            }
        }
    }

}
