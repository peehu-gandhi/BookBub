package com.example.bookbub;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.bookbub.model.Book;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.List;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.Holder> {
    Context c;
    List<Ads> images;
    public SliderAdapter(List<Ads> images, Context c){
        this.c=c;
        this.images = images;

    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.slider_item,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder viewHolder, int position) {
        final Ads b = images.get(position);
        RequestOptions options = new RequestOptions();
        Glide.with(c).load(b.getThe_caption_Title()).apply(options).into(viewHolder.imageView);
    }

    @Override
    public int getCount() {
        return images.size();
    }

    public class Holder extends  SliderViewAdapter.ViewHolder{

        ImageView imageView;

        public Holder(View itemView){
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);

        }
    }

}