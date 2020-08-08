package com.example.newsfeedapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.List;

public class NewsAdapter extends ArrayAdapter<News> {

    public NewsAdapter(@NonNull Context context, List<News> news) {
        super(context, 0, news);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_items_content, parent, false);
        }

        News news = getItem(position);
        assert news != null;

        TextView articleName = listItemView.findViewById(R.id.article_title);
        articleName.setText(news.getArticleName());

        TextView sectionName = listItemView.findViewById(R.id.section_name);
        sectionName.setText(news.getSectionName());

        TextView authorName = listItemView.findViewById(R.id.author_name);
        if(news.getAuthorName().equals("")){
            authorName.setText(R.string.no_author);
        }else {
            authorName.setText(news.getAuthorName());
        }
        authorName.setText(news.getAuthorName());

        TextView publishedDate = listItemView.findViewById(R.id.published_date);
        publishedDate.setText(news.getPublishedDate());

        ImageView imageView = listItemView.findViewById(R.id.image);
        imageView.setImageDrawable(news.getImage());

        return listItemView;
    }

}
