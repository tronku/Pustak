package com.example.tronku.book_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class result extends AppCompatActivity {

    private EditText search_box;
    private ImageButton search;
    private Intent getSearch;
    private String search_key, search_type;
    private RecyclerView recyclerView;
    private String googleApiBaseUri = "https://www.googleapis.com/books/v1/volumes?q=";
    private List<Books> booksList = new ArrayList<>();
    private JsonObjectRequest jsonObjectRequest;
    private RequestQueue requestQueue;
    private RadioButton title, author, isbn;
    private BooksAdapter adapter;
    private String tag = "Stop";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        getSearch = getIntent();
        recyclerView = findViewById(R.id.recyclerview);
        search_box = findViewById(R.id.search_box);
        title = findViewById(R.id.title_result);
        author = findViewById(R.id.author_result);
        isbn = findViewById(R.id.isbn_result);
        search = findViewById(R.id.search_button_result);
        adapter = new BooksAdapter(this, booksList);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        search_key = getSearch.getStringExtra("search_term");
        search_type = getSearch.getStringExtra("search_type");
        search_box.setText(search_key);
        setSearchTypeButton(search_type);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search_key = search_box.getText().toString();
                search_type = getSearchType();
                booksList.clear();
                adapter.setList(booksList);
                requestQueue.cancelAll(tag);
                jsonRequest(search_type, search_key);
            }
        });

        jsonRequest(search_type, search_key);
    }

    private void setSearchTypeButton(String search_type) {
        if(search_type.equals("title")){
            title.setChecked(true);
        }
        else if(search_type.equals("author"))
            author.setChecked(true);
        else
            isbn.setChecked(true);
    }

    private String getSearchType(){
        if(title.isChecked())
            return "title";
        else if(author.isChecked())
            return "author";
        else
            return "isbn";
    }

    public void jsonRequest(String search_type, String search_key){

        String url = googleApiBaseUri + search_type + ":" + search_key + "&maxResult=10";
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try{

                    JSONArray items = response.getJSONArray("items");
                    for(int i = 0;i<items.length();i++){
                        JSONObject bookItem = items.getJSONObject(i);
                        JSONObject bookInfo = bookItem.getJSONObject("volumeInfo");
                        JSONArray authors = bookInfo.getJSONArray("authors");
                        JSONObject img_Urls = bookInfo.getJSONObject("imageLinks");
                        JSONArray categories = bookInfo.getJSONArray("categories");

                        Books books = new Books();
                        books.setTitle(bookInfo.getString("title"));
                        books.setPublisher(bookInfo.getString("publisher"));
                        books.setPgs(bookInfo.getString("pageCount"));
                        books.setDescription(bookInfo.getString("description"));
                        books.setImgThumbnail(img_Urls.getString("smallThumbnail"));
                        books.setImgUrl(img_Urls.getString("thumbnail"));

                        //authors
                        String authorNames = "";
                        for(int j=0;j<authors.length(); j++){
                            if(j<authors.length()-1)
                                authorNames+= authors.get(j) + ", ";
                            else
                                authorNames+= authors.get(j);
                        }
                        books.setAuthor(authorNames);


                        //categories
                        String categoryNames ="";
                        for(int j=0;j<categories.length();j++){
                            if(j<categories.length()-1){
                                categoryNames+= categories.get(j) + " | ";
                            }
                            else
                                categoryNames+= categories.get(j);
                        }
                        books.setCategory(categoryNames);

                        booksList.add(books);
                    }
                    adapter.setList(booksList);
                }
                catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        jsonObjectRequest.setTag(tag);
        requestQueue = Volley.newRequestQueue(result.this);
        requestQueue.add(jsonObjectRequest);

    }



}
