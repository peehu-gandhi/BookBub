package com.example.bookbub;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public class ApiClient {

    private static final String BaseUrl = "https://myimon.000webhostapp.com/insert_new_book.php/";
    private static Retrofit retrofit;
    public static Retrofit getApiClient() {

        retrofit = new Retrofit.Builder().baseUrl(BaseUrl).
                addConverterFactory(GsonConverterFactory.create()).build();
        return retrofit;
    }
}
interface ApiInterface {

    @FormUrlEncoded
    @POST("upload.php")
    Call<Img_Pojo> uploadImage(@Field("title") String title, @Field("image") String image,@Field("book_name") String book_name,
@Field("author") String author,@Field("pages") String pages,@Field("language") String language,@Field("title") String genres,@Field("books_upl") String books_upl,@Field("pdf")String pdf
    );
}
