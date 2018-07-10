package com.example.tronku.book_app;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class detail extends AppCompatActivity {

    private Intent details;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        details = getIntent();

        String title = details.getStringExtra("book_title");
        String author = details.getStringExtra("author");
        String categories = details.getStringExtra("category");
        String publisher = details.getStringExtra("publisher");
        String isbn = details.getStringExtra("isbn");
        String pgs = details.getStringExtra("pgs");
        String description = details.getStringExtra("description");
        String pubDate = details.getStringExtra("pubDate");
        String thumbnail = details.getStringExtra("thumbnail");

        CollapsingToolbarLayout layout = findViewById(R.id.collapse);
        layout.setTitleEnabled(true);
        layout.setTitle(title);

        TextView booktitle = findViewById(R.id.book_name_details);
        TextView bookAuthor = findViewById(R.id.author_name_details);
        TextView category = findViewById(R.id.category_details);
        TextView publisherName = findViewById(R.id.publisher_name_details);
        TextView bookIsbn = findViewById(R.id.isbn_details);
        TextView bookPgs = findViewById(R.id.pgs_details);
        TextView desc = findViewById(R.id.description);
        TextView bookDate = findViewById(R.id.pubDate_details);
        ImageView img = findViewById(R.id.book_thumbnail_details);

        booktitle.setText(title);
        bookAuthor.setText(author);
        category.setText(categories);
        publisherName.setText(publisher);
        bookIsbn.setText("ISBN 13: " + isbn);
        bookPgs.setText("Pages: " + pgs);
        desc.setText(description);
        bookDate.setText(pubDate);

        RequestOptions options = new RequestOptions().centerCrop().placeholder(R.drawable.img_load).error(R.drawable.img_load);
        Glide.with(this).load(thumbnail).into(img);
    }
}
