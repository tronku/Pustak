package com.example.tronku.book_app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.BooksViewHolder> {

    private Context context;
    private List<Books> booksList;
    private RequestOptions option;

    public BooksAdapter(Context context, List<Books> booksList) {
        this.context = context;
        this.booksList = booksList;
        option = new RequestOptions().centerCrop().placeholder(R.drawable.img_load).error(R.drawable.img_load);
    }

    public void setList(List<Books> booksList){
        this.booksList = booksList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BooksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.book_row, parent, false);
        final BooksViewHolder viewHolder = new BooksViewHolder(view);
        viewHolder.book_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent details = new Intent(context, detail.class);
                details.putExtra("book_title", booksList.get(viewHolder.getAdapterPosition()).getTitle());
                details.putExtra("author", booksList.get(viewHolder.getAdapterPosition()).getAuthor());
                details.putExtra("publisher", booksList.get(viewHolder.getAdapterPosition()).getPublisher());
                details.putExtra("category", booksList.get(viewHolder.getAdapterPosition()).getCategory());
                details.putExtra("isbn", booksList.get(viewHolder.getAdapterPosition()).getIsbn());
                details.putExtra("pgs", booksList.get(viewHolder.getAdapterPosition()).getPgs());
                details.putExtra("pubDate", booksList.get(viewHolder.getAdapterPosition()).getPubDate());
                details.putExtra("description", booksList.get(viewHolder.getAdapterPosition()).getDescription());
                details.putExtra("thumbnail", booksList.get(viewHolder.getAdapterPosition()).getImgUrl());
                Pair<View, String> pair1 = Pair.create((View)viewHolder.thumbnail, "thumbnail");
                Pair<View, String> pair2 = Pair.create((View)viewHolder.title, "title");
                ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity)context, pair1, pair2);
                context.startActivity(details, optionsCompat.toBundle());
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BooksViewHolder holder, int position) {
        holder.title.setText(booksList.get(position).getTitle());
        holder.author.setText(booksList.get(position).getAuthor());
        holder.isbn.setText("ISBN: " + booksList.get(position).getIsbn());
        holder.pgs.setText("Pages: " + booksList.get(position).getPgs());
        holder.category.setText(booksList.get(position).getCategory());
        holder.publisher.setText(booksList.get(position).getPublisher());

        Glide.with(context).load(booksList.get(position).getImgUrl()).apply(option).into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return booksList.size();
    }

    public class BooksViewHolder extends RecyclerView.ViewHolder{

        TextView title, author, publisher, isbn, pgs, category;
        LinearLayout book_container;
        ImageView thumbnail;

        public BooksViewHolder(View itemView) {
            super(itemView);

            book_container = itemView.findViewById(R.id.book_container);
            title = itemView.findViewById(R.id.book_name);
            author = itemView.findViewById(R.id.author_name);
            publisher = itemView.findViewById(R.id.publisher_name);
            isbn = itemView.findViewById(R.id.isbn);
            pgs = itemView.findViewById(R.id.pgs);
            category = itemView.findViewById(R.id.category);
            thumbnail = itemView.findViewById(R.id.book_thumbnail);
        }
    }
}
