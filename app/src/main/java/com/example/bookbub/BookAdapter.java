package com.example.bookbub;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.bookbub.model.Book;

import java.util.ArrayList;
import java.util.List;
public class BookAdapter  extends RecyclerView.Adapter<BookAdapter.bookviewholder>
{
    List<Book> mdata;
    BookCallback callback;
    Context c;
    public BookAdapter(List<Book> mdata , BookCallback callback, Context c) {
        this.mdata = mdata;
        this.callback = callback ;
        this.c=c;
    }
    @NonNull
    @Override
    public bookviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_book2,parent,false);
        return new bookviewholder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull bookviewholder holder, int position) {

        // bind book item data here
        // I'm just going to bint the book image..

        //load book image using Glide


        final Book b = mdata.get(position);

        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.bookcover2)
                .error(R.drawable.bookcover2);
        Glide.with(c).load(b.getImgUrl()).apply(options).into(holder.imgBook);
        holder.title.setText(b.getTitle());
        holder.ratingBar.setRating((float) b.getRating());
    }
    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public void filterList(List<Book> filteredlist) {
        // below line is to add our filtered
        // list in our course array list.
        mdata = filteredlist;
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged();
    }

    public class bookviewholder extends RecyclerView.ViewHolder {
        ImageView imgBook,imgFav;
        ImageView imgContainer;
        TextView title;
        RatingBar ratingBar;
        public bookviewholder(@NonNull View itemView) {
            super(itemView);
            imgBook = itemView.findViewById(R.id.item_book_img);
            title = itemView.findViewById(R.id.item_book_title);
            ratingBar = itemView.findViewById(R.id.item_book_ratingbar);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callback.onBookItemClick(getAdapterPosition(),
                            imgBook,
                            title,
                            ratingBar);
                }
            });
        }
    }
}