package com.example.bookbub;

import com.google.gson.annotations.SerializedName;

public class Img_Pojo {
    @SerializedName("image_name")
    private String Title;

    @SerializedName("image")
    private String Image;


    @SerializedName("response")
    private String Response;


    @SerializedName("book_name")
    private String book_name;
    @SerializedName("response")
    public String getResponse() {
        return Response;
    }
    @SerializedName("author")
    private String author;

    @SerializedName("pages")
    private String pages;

    @SerializedName("language")
    private String language;


    @SerializedName("genres")
    private String genres;


    @SerializedName("books_upl")
    private String books_upl;


    @SerializedName("pdf")
    private String pdf;



}
