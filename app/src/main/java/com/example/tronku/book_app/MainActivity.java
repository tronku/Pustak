package com.example.tronku.book_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {

    private EditText searchBox;
    private ImageButton search;
    private RadioButton title, author, isbn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchBox = findViewById(R.id.search);
        title = findViewById(R.id.title);
        author = findViewById(R.id.author);
        isbn = findViewById(R.id.isbn);
        search = findViewById(R.id.search_button);

        title.setChecked(true);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchBox.length() > 0){
                    Intent search = new Intent(MainActivity.this, result.class);
                    search.putExtra("search_term", searchBox.getText().toString());
                    search.putExtra("search_type", searchType());
                    startActivity(search);
                }
            }
        });
    }

    public String searchType(){
        if(title.isChecked())
            return "title";
        else if(author.isChecked())
            return "author";
        else
            return "isbn";
    }
}
