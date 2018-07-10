package com.example.tronku.book_app;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
        return new BooksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BooksViewHolder holder, int position) {
        holder.title.setText(booksList.get(position).getTitle());
        holder.author.setText(booksList.get(position).getAuthor());
        holder.category.setText(booksList.get(position).getCategory());
        holder.rating.setText(booksList.get(position).getRating());
        holder.publisher.setText(booksList.get(position).getPublisher());

        Glide.with(context).load(booksList.get(position).getImgUrl()).apply(option).into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return booksList.size();
    }

    public class BooksViewHolder extends RecyclerView.ViewHolder{

        TextView title, author, publisher, rating, category;
        ImageView thumbnail;

        public BooksViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.book_name);
            author = itemView.findViewById(R.id.author_name);
            publisher = itemView.findViewById(R.id.publisher_name);
            rating = itemView.findViewById(R.id.rating);
            category = itemView.findViewById(R.id.category);
            thumbnail = itemView.findViewById(R.id.book_thumbnail);
        }
    }
}
