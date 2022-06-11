package com.example.bookbub;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager.widget.ViewPager.OnPageChangeListener;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import retrofit2.Call;

import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.zip.Inflater;

//import javax.security.auth.callback.Callback;

import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;

public class NewActivity extends AppCompatActivity {

    //for inserting new book
    public static final String UPLOAD_URL = "https://myimon.000webhostapp.com/insert_new_book.php";
    public static final String UPLOAD_KEY = "image";
        TextView hindi,tamil,gujarati,english;
    CardView cardtamil,cardhindi,cardenglish,cardgujarati;
    CardView cardscifi,cardhorror,cardcomedy,cardstudy;


    Uri filePath;
    private int PICK_IMAGE_REQUEST = 1;
    private LinearLayout btnchoose;
    private Button buttonChoose;
    private Button buttonUpload;
    private Button buttonView;
    private TextView tvchoose;
    private ImageView imageView;
String booknm;
    private Bitmap bitmap;
    Button bt;
    String displayName = null;
    private ViewPager viewPager;
    private MyViewPagerAdapter myViewPagerAdapter;
    private LinearLayout dotsLayout;
    private OnPageChangeListener mPageChangeListener;
    TextView bookname,desc,authorname;

    RelativeLayout rv;
    private TextView[] dots;
    String strname=null,strdesc=null;
ImageView book_img;
Button btn_login;
    String strnm;

    private int[] layouts;
    EditText tvname = null,tvdesc = null;

    private Button btnSkip, btnNext;
    private String encodedpdf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        setContentView(R.layout.activity_new);
        getSupportActionBar().hide();
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
        btnSkip = (Button) findViewById(R.id.btn_skip);
        btnNext = (Button) findViewById(R.id.btn_next);
        layouts = new int[]{
                R.layout.publish_book_pg1,
                R.layout.publish_book_pg2,
                R.layout.publish_book_pg4,
                R.layout.publish_book_pg5,
                R.layout.publish_book_pg3,};
        addBottomDots(0);
        changeStatusBarColor();
        myViewPagerAdapter = new MyViewPagerAdapter();
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);

        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // launchHomeScreen();
                if (v == buttonChoose) {
                    showFileChooser();
                }

                if(v == buttonUpload){
                }

            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current = getItem(+1);
                if (current < layouts.length) {
                    // move to next screen
                    viewPager.setCurrentItem(current);
                }
//                else {
//                    launchHomeScreen();
//                }
            }
        });
        /*setContentView(R.layout.activity_new);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(
                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        setContentView(R.layout.activity_new);
        getSupportActionBar().hide();
        viewPager = findViewById(R.id.pagerIntroSlider);
        TabLayout tabLayout = findViewById(R.id.tabs);
        button = findViewById(R.id.button);
        // init slider pager adapter
        adapter = new SliderPagerAdapter(getSupportFragmentManager(),
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        // set adapter
        viewPager.setAdapter(adapter);
        // set dot indicators
        tabLayout.setupWithViewPager(viewPager);
        // make status bar transparent
        changeStatusBarColor();
        button.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                if (viewPager.getCurrentItem() < adapter.getCount()) {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                }
            }
        });*/

//        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//            }
//            @Override public void onPageSelected(int position) {
//                if (position == adapter.getCount() - 1) {
//                    button.setText(R.string.get_started);
//                } else {
//                    button.setText(R.string.next);
//                }
//            }
//            @Override public void onPageScrollStateChanged(int state) {
//            }
//        });
    }
    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }
    private String convertToString()
    {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imgByte = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgByte,Base64.DEFAULT);
    }
    private void selectImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 100);
    }
    private void addBottomDots(int currentPage)
    {
        dots = new TextView[layouts.length];
        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);
        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive[currentPage]);
            dotsLayout.addView(dots[i]);
        }
        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive[currentPage]);
    }
    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }
//    private void launchHomeScreen() {
//       // prefManager.setFirstTimeLaunch(false);
//        startActivity(new Intent(NewActivity.this, NewActivity.class));
//        finish();
//    }
    OnPageChangeListener viewPagerPageChangeListener = new OnPageChangeListener() {
        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);
            // changing the next button text 'NEXT' / 'GOT IT'
            if (position == layouts.length - 1) {
                // last page. make button text to GOT IT
                btnNext.setText(getString(R.string.start));
                btnNext.setVisibility(View.GONE);
                btnSkip.setVisibility(View.GONE);
            } else
                {
                // still pages are left
                btnNext.setText(getString(R.string.next));
                btnSkip.setVisibility(View.VISIBLE);
            }
            if (position == 2) {
                tvname = findViewById(R.id.name);
                tvdesc = findViewById(R.id.desc);
                strname = tvname.getText().toString();
                System.out.println("thaii=="+strname);
                SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);

                SharedPreferences.Editor myEdit = sharedPreferences.edit();

                myEdit.putString("name", strname);

                myEdit.commit();
                booknm=strname;
                strdesc = tvdesc.getText().toString();
            }
            if(position == 0)
            {

            }
            if (position == 3) {

                LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
               // View v=inflater.inflate(R.layout.make_cover,null,false);
                //Bitmap b = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
                //Canvas c = new Canvas(b);
               // if(strname!=null)Toast.makeText(NewActivity.this, "val=="+strname, Toast.LENGTH_SHORT).show();
               /// v.layout(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());
                // bn.setText(tvname.getText().toString());
                //d.setText(tvdesc.getText().toString());
                //v.draw(c);

                //book_img.setImageBitmap(b);



            }
        }
        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }









        @Override
        public void onPageScrollStateChanged(int arg0) {


        }
    };
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }
    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;
        public MyViewPagerAdapter() {
        }
        @Override
        public void startUpdate(@NonNull ViewGroup container) {
            super.startUpdate(container);
        }
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(layouts[position], container, false);
            container.addView(view);
            if(position == 0)
            {
             //   Toast.makeText(NewActivity.this, "opopopopopooooo1", Toast.LENGTH_SHORT).show();
//                hindi=view.findViewById(R.id.hindi);
//                english=view.findViewById(R.id.english);
//                gujarati=view.findViewById(R.id.gujarati);
//                tamil=view.findViewById(R.id.tamil);
//                cardhindi=view.findViewById(R.id.cardhindi);
//                cardenglish=view.findViewById(R.id.cardenglish);
//                cardgujarati=view.findViewById(R.id.cardgujarati);
//                cardtamil=view.findViewById(R.id.cardtamil);


//                cardhindi.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//
//                        hindi.setTextColor(getResources().getColor(R.color.white));
//                        cardhindi.setBackgroundColor(getResources().getColor(R.color.startblue));
//                        english.setTextColor(getResources().getColor(R.color.startblue));
//                        cardenglish.setBackgroundColor(getResources().getColor(R.color.white));
//                        gujarati.setTextColor(getResources().getColor(R.color.startblue));
//                        cardgujarati.setBackgroundColor(getResources().getColor(R.color.white));
//                        tamil.setTextColor(getResources().getColor(R.color.white));
//                        cardtamil.setBackgroundColor(getResources().getColor(R.color.startblue));
//                    }
//                });
//                cardenglish.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        english.setTextColor(getResources().getColor(R.color.white));
//                        cardenglish.setBackgroundColor(getResources().getColor(R.color.startblue));
//                        gujarati.setTextColor(getResources().getColor(R.color.startblue));
//                        cardgujarati.setBackgroundColor(getResources().getColor(R.color.white));
//                        hindi.setTextColor(getResources().getColor(R.color.startblue));
//                        cardhindi.setBackgroundColor(getResources().getColor(R.color.white));
//                        tamil.setTextColor(getResources().getColor(R.color.white));
//                        cardtamil.setBackgroundColor(getResources().getColor(R.color.startblue));
//                        english.setTextColor(getResources().getColor(R.color.startblue));
//                        cardenglish.setBackgroundColor(getResources().getColor(R.color.white));
//                        //selected="english";
//                    }
//                });
//                cardgujarati.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        gujarati.setTextColor(getResources().getColor(R.color.white));
//                        cardgujarati.setBackgroundColor(getResources().getColor(R.color.startblue));
//                        //selected="english";
//                        hindi.setTextColor(getResources().getColor(R.color.startblue));
//                        cardhindi.setBackgroundColor(getResources().getColor(R.color.white));
//                        tamil.setTextColor(getResources().getColor(R.color.white));
//                        cardtamil.setBackgroundColor(getResources().getColor(R.color.startblue));
//                        english.setTextColor(getResources().getColor(R.color.startblue));
//                        cardenglish.setBackgroundColor(getResources().getColor(R.color.white));
//                    }
//                });
//                cardtamil.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        tamil.setTextColor(getResources().getColor(R.color.white));
//                        cardtamil.setBackgroundColor(getResources().getColor(R.color.startblue));
//                        english.setTextColor(getResources().getColor(R.color.startblue));
//                        cardenglish.setBackgroundColor(getResources().getColor(R.color.white));
//                        gujarati.setTextColor(getResources().getColor(R.color.startblue));
//                        cardgujarati.setBackgroundColor(getResources().getColor(R.color.white));
//                        hindi.setTextColor(getResources().getColor(R.color.startblue));
//                        cardhindi.setBackgroundColor(getResources().getColor(R.color.white));
//                        //selected="english";
//                    }
//                });
//
//
//
//
//
//                TextView txt_choose=view.findViewById(R.id.txt_choose);
//                txt_choose.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                    }
//                });
            }
            if(position == 1)
            {
                TextView txt_choose=view.findViewById(R.id.txt_choose);
//                TextView scifi,horror,comedy,study;
//                scifi=view.findViewById(R.id.scifi);
//                horror=view.findViewById(R.id.horror);
//                comedy=view.findViewById(R.id.comedy);
//                study=view.findViewById(R.id.study);
//                cardscifi=view.findViewById(R.id.cardscifi);
//                cardhorror=view.findViewById(R.id.cardcomedy);
////                cardstudy=view.findViewById(R.id.cardstudy);ew.findViewById(R.id.cardhorror);
//                cardcomedy=vi
                txt_choose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    }
                });

//
//                cardhorror.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//
//                        horror.setTextColor(getResources().getColor(R.color.white));
//                        cardhorror.setBackgroundColor(getResources().getColor(R.color.startblue));
//
//                    }
//                });
//                cardcomedy.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        comedy.setTextColor(getResources().getColor(R.color.white));
//                        cardcomedy.setBackgroundColor(getResources().getColor(R.color.startblue));
//
//                        //selected="english";
//                    }
//                });
//                cardstudy.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        study.setTextColor(getResources().getColor(R.color.white));
//                        cardstudy.setBackgroundColor(getResources().getColor(R.color.startblue));
//                        //selected="english";
//                    }
//                });
//                cardscifi.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        scifi.setTextColor(getResources().getColor(R.color.white));
//                        cardscifi.setBackgroundColor(getResources().getColor(R.color.startblue));
//
//                        //selected="english";
//                    }
//                });
//



            }
            if(position == 2)
            {
                tvname=findViewById(R.id.name);
                tvdesc=findViewById(R.id.desc);
                tvname.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        strnm=tvname.getText().toString();
                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });

            }

            if(position==3){
        View v2=layoutInflater.inflate(R.layout.make_cover,null,false);
        bookname=v2.findViewById(R.id.bookname);
                desc=v2.findViewById(R.id.desc);
                authorname=v2.findViewById(R.id.authorname);
                bookname.setText(strname);
                desc.setText(strdesc);
                btn_login= view.findViewById(R.id.btn_login);
                btn_login.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       // uploadImage();
                      //  uploadretrofit();
                        selectImage();
                    }
                });
                authorname.setText("Peehu");
                book_img=view.findViewById(R.id.book_img);
                rv=v2.findViewById(R.id.rv);
                book_img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        selectImage();
                    }
                });
//                View v=layoutInflater.inflate(R.layout.make_cover,null,false);
//                TextView bn=v.findViewById(R.id.bookname);
//                TextView d=v.findViewById(R.id.desc);
//                if(strname!=null)Toast.makeText(NewActivity.this, "val=="+strname, Toast.LENGTH_SHORT).show();
//                v.layout(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());
//               // bn.setText(tvname.getText().toString());
//                //d.setText(tvdesc.getText().toString());
//                Bitmap b = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
//                Canvas c = new Canvas(b);
//                v.draw(c);
            }
            if(position==4)
            {
            bt=view.findViewById(R.id.bt);
                System.out.println("here="+booknm);
                tvchoose=view.findViewById(R.id.tvchoose);
                btnchoose=view.findViewById(R.id.btnchoose);
            bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    uploadretrofit(strnm);
                    Toast.makeText(NewActivity.this, "Book Uploaded", Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(getApplicationContext(),AllBooks.class);
                    startActivity(i);


                }
            });
                btnchoose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        Intent choosefile = new Intent(Intent.ACTION_GET_CONTENT);
                        choosefile.setType("application/pdf");
                        choosefile = Intent.createChooser(choosefile, "Choose a file");
                        startActivityForResult(choosefile, 21);
                        bt.setVisibility(View.VISIBLE);
                    }
                });

            }
            return view;
        }
        private void uploadPdfToServer()
        {
            retrofit2.Call<ResponsePOJO> call = RetrofitClient.getInstance().getAPI().uploadDocument(encodedpdf);
            call.enqueue(new retrofit2.Callback<ResponsePOJO>() {
                @Override
                public void onResponse( retrofit2.Call<ResponsePOJO> call, Response<ResponsePOJO> response) {

                    Toast.makeText(NewActivity.this, response.body().getRemarks(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure( retrofit2.Call<ResponsePOJO> call, Throwable t) {
                    Toast.makeText(NewActivity.this, "Network Failed", Toast.LENGTH_SHORT).show();

                }
            });
//            Toast.makeText(NewActivity.this, "upppppp", Toast.LENGTH_SHORT).show();
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }

    private void uploadretrofit(String strnm) {

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        String image = convertToString();

// Retrieving the value using its keys the file name
// must be same in both saving and retrieving the data
        @SuppressLint("WrongConstant") SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_APPEND);

// The value will be default as empty string because for
// the very first time when the app is opened, there is nothing to show
        String nm = sh.getString("name", "");



        retrofit2.Call<Img_Pojo> call = apiInterface.uploadImage(strnm,image,strnm,"peehu"+strnm,"90","Hindi",

               "study" ,"900",encodedpdf);
        call.enqueue(new Callback<Img_Pojo>() {
            @Override
            public void onResponse(Call<Img_Pojo> call, Response<Img_Pojo> response) {

                Img_Pojo img_pojo = response.body();
                Toast.makeText(NewActivity.this, img_pojo.getResponse(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<Img_Pojo> call, Throwable t) {
                Log.d("Server Response",""+t.toString());


            }
        });


    }
//
//    private String getBookName() {
//
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
      //  if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            if(requestCode== 100 && resultCode==RESULT_OK && data!=null)
            {
                Uri path = data.getData();

                try {

                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),path);

                    book_img.setImageBitmap(bitmap);
                } catch (IOException e) {
                    System.out.println("err=="+e.getMessage());
                }
            }
            filePath = data.getData();

        if(requestCode==21 && resultCode==RESULT_OK && data!=null)
        {



            Uri path=data.getData();
            String uriString = path.toString();
            File myFile = new File(uriString);
            displayName = myFile.getName();
            tvchoose.setText(displayName);
            byte[] pdfinbytes= new byte[0];
            try {
                InputStream is=NewActivity.this.getContentResolver().openInputStream(path);
                pdfinbytes = new byte[is.available()];
                is.read(pdfinbytes);
                encodedpdf=Base64.encodeToString(pdfinbytes,Base64.DEFAULT);
            }
             catch (IOException e) {
                e.printStackTrace();
            }
        }

        viewPager.setOnPageChangeListener(mPageChangeListener);

        viewPager.addOnPageChangeListener(new OnPageChangeListener()
        {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                if(position==2)
                {

                    strname=tvname.getText().toString();
                    strdesc=tvdesc.getText().toString();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }


}
