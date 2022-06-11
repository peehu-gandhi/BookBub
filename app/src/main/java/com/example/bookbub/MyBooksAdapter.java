package com.example.bookbub;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MyBooksAdapter extends RecyclerView.Adapter<MyBooksHolder>{
    Context c;
    List<Books> b;
    BookClickListener l;

    public MyBooksAdapter(Context c, List<Books> b, BookClickListener l) {
        this.c = c;
        this.b = b;
        this.l = l;
    }
    @NonNull
    @Override
    public MyBooksHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyBooksHolder(LayoutInflater.from(c).inflate(R.layout.book_list_layout,parent,false));
    }
    @Override
    public void onBindViewHolder(@NonNull MyBooksHolder holder, int position) {
        Glide.with(c)
                .load(b.get(position).getBook_img())
                .into(holder.iv);
            if(b.get(position).isPinned()){
                holder.ivpin.setImageResource(R.drawable.pin);
            }
            else{
                holder.ivpin.setImageResource(0);
            }
            holder.card_book.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    l.onClick(b.get(holder.getAdapterPosition()));

                }
            });
            holder.card_book.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    l.onLongClick(b.get(holder.getAdapterPosition()),holder.card_book);
                    return true ;
                }
            });

    }

    @Override
    public int getItemCount() {
        return b.size();
    }
}
class MyBooksHolder extends RecyclerView.ViewHolder{
    CardView card_book;
    ImageView iv,ivpin;
    public MyBooksHolder(@NonNull View itemView) {
        super(itemView);
        card_book=itemView.findViewById(R.id.card_book);
        iv=itemView.findViewById(R.id.iv);
        ivpin=itemView.findViewById(R.id.ivpin);

    }
}