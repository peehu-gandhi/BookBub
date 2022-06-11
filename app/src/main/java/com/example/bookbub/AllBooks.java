package com.example.bookbub;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bookbub.model.Book;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
public class AllBooks extends AppCompatActivity implements BookCallback{
    private RecyclerView rvBooks;
    private BookAdapter bookAdapter;
    private List<Book> mdata ;
    SearchView sv;

    private ProgressBar progressBar;
    StaggeredGridLayoutManager m;
    private static  final String BASE_URL = "https://myimon.000webhostapp.com/allbooks.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_all_books);
                progressBar = findViewById(R.id.progressbar);
        sv=findViewById(R.id.sv);

        m=new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        getSupportActionBar().hide();
        initViews();
        initmdataBooks();

        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // inside on query text change method we are
                // calling a method to filter our recycler view.
                filter(newText);
                return false;
            }
        });


    }

    private void filter(String text) {
        // creating a new array list to filter our data.
        ArrayList<Book> filteredlist = new ArrayList<>();
        if(mdata !=null) {
            // running a for loop to compare elements.
            for (Book item : mdata) {
                // checking if the entered string matched with any item of our recycler view.
                if (item.getTitle().toLowerCase().contains(text.toLowerCase())) {
                    // if the item is matched we are
                    // adding it to our filtered list.
                    filteredlist.add(item);
                }
            }
        }
        if (filteredlist.isEmpty()) {
        } else {
            // at last we are passing that filtered
            // list to our adapter class.
            bookAdapter.filterList(filteredlist);
        }
    }

    private void setupBookAdapter() {

        bookAdapter = new BookAdapter(mdata,this,getApplicationContext());
        rvBooks.setLayoutManager(m);
        rvBooks.setAdapter(bookAdapter);
        m.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
        rvBooks.setLayoutManager(m);
        rvBooks.setItemAnimator(new DefaultItemAnimator());

    }
    private void initmdataBooks() {
        // for testing purpos I'm creating a random set of books
        // you may get your data from web service or firebase database.
        mdata = new ArrayList<>();
        loadBooks();

//
//
//
//
//        mdata.add(new Book(R.drawable.bookcover));
//        mdata.add(new Book(R.drawable.bookcover));
//        mdata.add(new Book(R.drawable.bookcover));
//        mdata.add(new Book(R.drawable.bookcover));
//        mdata.add(new Book(R.drawable.bookcover));
//        mdata.add(new Book(R.drawable.bookcover));
    }

    private void loadBooks() {
        progressBar.setVisibility(View.VISIBLE);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, BASE_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        progressBar.setVisibility(View.GONE);
                        try {

                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i<array.length(); i++){
                                JSONObject object = array.getJSONObject(i);
                                String bookname = object.getString("bookname");
                                String title = object.getString("title");
                                String author = object.getString("author");
                                String book_cover = object.getString("book_cover");
                                int pages = object.getInt("pages");
                                float rating= (float) object.getDouble("rating");
                                Book b = new Book(bookname,title,author,book_cover,pages,89,rating);
                                mdata.add(b);
                            }
                        }catch (Exception e){
                        }
                        setupBookAdapter();
//                        mAdapter = new RecyclerAdapter(HomeActivity.this,products);
//                        recyclerView.setAdapter(mAdapter);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressBar.setVisibility(View.GONE);
                Toast.makeText(AllBooks.this, error.toString(),Toast.LENGTH_LONG).show();

            }
        });

        Volley.newRequestQueue(AllBooks.this).add(stringRequest);

    }


    private void initViews() {

        rvBooks = findViewById(R.id.rv_book);
        rvBooks.setLayoutManager(m);

//        rvBooks.setHasFixedSize(true);
        // we need first to setupe the custom item animator that we just create
//        rvBooks.setItemAnimator(new CustomItemAnimator());
        // to test the animation we need to simulate the add book operation


    }
    @Override
    public void onBookItemClick(int pos,
                                ImageView imgBook,
                                TextView title,
                                RatingBar ratingBar) {
        // create intent and send book object to Details activity
        Intent intent = new Intent(this,BookDetailsActivity.class);
        intent.putExtra("bookname",mdata.get(pos).getTitle());
       // Pair<View,String> p1 = Pair.create((View)imgContainer,"containerTN"); // second arg is the tansition string Name
        Pair<View,String> p2 = Pair.create((View)imgBook,"bookTN"); // second arg is the tansition string Name
        Pair<View,String> p3 = Pair.create((View)title,"booktitleTN"); // second arg is the tansition string Name
//        Pair<View,String> p4 = Pair.create((View)authorName,"authorTN"); // second arg is the tansition string Name
//        Pair<View,String> p5 = Pair.create((View)nbpages,"bookpagesTN"); // second arg is the tansition string Name
//        Pair<View,String> p6 = Pair.create((View)score,"scoreTN"); // second arg is the tansition string Name
        Pair<View,String> p7 = Pair.create((View)ratingBar,"rateTN"); // second arg is the tansition string Name
//        ActivityOptionsCompat optionsCompat =
//                ActivityOptionsCompat.makeSceneTransitionAnimation(this,p1,p2 ,p3,p4,p5,p6,p7);
        // start the activity with scene transition
        ActivityOptionsCompat optionsCompat =
                ActivityOptionsCompat.makeSceneTransitionAnimation(this,p2 ,p3,p7);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            startActivity(intent,optionsCompat.toBundle());
        }
        else
            startActivity(intent);


    }
}