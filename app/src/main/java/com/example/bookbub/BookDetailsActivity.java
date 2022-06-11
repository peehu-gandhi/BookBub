package com.example.bookbub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.github.barteksc.pdfviewer.PDFView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class BookDetailsActivity extends AppCompatActivity {
    WebView wv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);
         wv1 = findViewById(R.id.webView);
        getSupportActionBar().hide();

        String bookname;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                bookname= null;
            } else {
                bookname= extras.getString("bookname");
            }
        } else {
            bookname= (String) savedInstanceState.getSerializable("bookname");
        }
        loadBook(bookname);

//wv.loadData( doc, "text/html",  "UTF-8");

//        PDFView pdfView=findViewById(R.id.pdfView);
//        pdfView.fromAsset("")
//                .pages(0, 2, 1, 3, 3, 3) // all pages are displayed by default
//                .enableSwipe(true) // allows to block changing pages using swipe
//                .swipeHorizontal(true)
//                .enableDoubletap(true)
//                .defaultPage(0)
//                .enableAnnotationRendering(false) // render annotations (such as comments, colors or forms)
//                .password(null)
//                .scrollHandle(null)
//                .load();

    }

    private void loadBook(String bookname) {
        String urllogin="https://myimon.000webhostapp.com/return_book.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urllogin,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            //converting response to json object
                            JSONObject obj = new JSONObject(response);

                            JSONObject userJson = obj.getJSONObject("locations");
                            String loc=userJson.getString("location");
                                //Toast.makeText(getApplicationContext(),"loc==="+loc, Toast.LENGTH_SHORT).show();
                            //}
                            loadWebView(loc);

                        }
                            catch (JSONException e) {
                            System.out.println("error=="+e.getMessage());
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("title",bookname);
                return params;
            }
        };

        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);



    }
    private void loadWebView(String loc) {
        // Enable responsive layout
        wv1.getSettings().setUseWideViewPort(true);
// Zoom out if the content width is greater than the width of the viewport
        wv1.getSettings().setLoadWithOverviewMode(true);
        wv1.getSettings().setSupportZoom(true);
        wv1.getSettings().setBuiltInZoomControls(true); // allow pinch to zooom
        wv1.getSettings().setDisplayZoomControls(false); // disable the default zoom controls on the page

        wv1.getSettings().setSupportZoom(true);
        wv1.getSettings().setBuiltInZoomControls(true); // allow pinch to zooom
        wv1.getSettings().setDisplayZoomControls(false); // disable the default zoom controls on the page

        wv1.getSettings().setLoadsImagesAutomatically(true);
        wv1.getSettings().setJavaScriptEnabled(true);
        String doc="<iframe src=\"http://flowpaper.com/flipbook/"+loc+"\" width=\"100%\" height=\"100%\" style=\"border: none;\" allowFullScreen  frameborder=\"0\"   allowtransparency=\"true\">";
        wv1.getSettings().setJavaScriptEnabled(true);
        wv1.getSettings().setAllowFileAccess(true);
        wv1.loadUrl(doc);
        wv1.loadData( doc , "text/html",  "UTF-8");
        wv1.loadData( doc, "text/html",  "UTF-8");
    }
}