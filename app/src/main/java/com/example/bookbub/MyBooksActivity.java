package com.example.bookbub;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.strictmode.IntentReceiverLeakedViolation;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
public class MyBooksActivity extends AppCompatActivity {
    RecyclerView rv;
    MyBooksAdapter ad;
    List<Books> b=new ArrayList<>();
    FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_books);
        rv=findViewById(R.id.rv);
        fab=findViewById(R.id.fab);
        filllist();
    }
    private void updateRecycler(List<Books> b) {

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MyBooksActivity.this,AddBookActivity.class);
                startActivityForResult(i,101);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==101){
            if(resultCode== Activity.RESULT_OK){
                Toast.makeText(this, "INSERTED IN Databae", Toast.LENGTH_SHORT).show();
                b.clear();
                //add list in books from database
                //b.addAll()
                ad.notifyDataSetChanged();
            }
        }
    }

    private BookClickListener li=new BookClickListener() {
        @Override
        public void onClick(Books b) {

        }

        @Override
        public void onLongClick(Books b, CardView c) {

        }
    };
    private void filllist()
    {
        String url = "https://myimon.000webhostapp.com/BookBub/MyBooks.php";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response){
                        try {
                            b=new ArrayList<>();
                            //String serv_name,all_sub_serv;
                            JSONArray array = new JSONArray(response);
                            //ArrayList<Mobile> oneplusMobiles;
                            for (int i = 0; i < array.length(); i++)
                            {
                                JSONObject im = array.getJSONObject(i);
                                String bookurl=im.getString("book_cover");
                                b.add(new Books(bookurl,false));
                                rv.setHasFixedSize(true);
                                rv.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayout.VERTICAL));
                                ad=new MyBooksAdapter(getApplicationContext(),b,li);
                                rv.setAdapter(ad);


//                                rv.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL,false));
//                                rv.setAdapter(ad);
                            }
                        } catch (Exception e)
                        {
                            System.out.println("excepto=="+e);
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("err==" + error);
                    }
                });
        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);
        ///volleyyyyyy.....
        updateRecycler(b);

    }
}