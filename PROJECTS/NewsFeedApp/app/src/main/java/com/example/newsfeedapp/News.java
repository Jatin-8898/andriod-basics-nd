package com.example.newsfeedapp;

import android.graphics.drawable.Drawable;

import java.util.Date;

public class News {
    private String articleName;
    private String sectionName;
    private String authorName;
    private String publishedDate;
    private String articleURL;
    private Drawable image;

    public News(String articleName, String sectionName, String authorName, String publishedDate, String articleURL, Drawable image) {
        this.articleName = articleName;
        this.sectionName = sectionName;
        this.authorName = authorName;
        this.publishedDate = publishedDate;
        this.articleURL = articleURL;
        this.image = image;
    }

    public News(String articleName, String sectionName, String authorName, String publishedDate, String articleURL) {
        this.articleName = articleName;
        this.sectionName = sectionName;
        this.authorName = authorName;
        this.publishedDate = publishedDate;
        this.articleURL = articleURL;
    }

    public String getArticleName() {
        return articleName;
    }

    public String getSectionName() {
        return sectionName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public String getArticleURL() {
        return articleURL;
    }

    public Drawable getImage() {
        return image;
    }

}
