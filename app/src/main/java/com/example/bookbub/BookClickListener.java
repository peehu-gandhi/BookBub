package com.example.bookbub;

import androidx.cardview.widget.CardView;

public interface BookClickListener {
    void onClick(Books b);
    void onLongClick(Books b, CardView c);
}
