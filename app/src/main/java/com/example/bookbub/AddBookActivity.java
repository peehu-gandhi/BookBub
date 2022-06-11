package com.example.bookbub;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
public class AddBookActivity extends AppCompatActivity {
EditText ed_title,ed_book;
ImageView iv_done;
Books b;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
//        iv_done=findViewById(R.id.iv_done);
        //ed_title=findViewById(R.id.ed_title);
        //ed_book=findViewById(R.id.ed_book);
//        iv_done.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View view)
//            {
//                String title=ed_title.getText().toString();
//                String desc=ed_book.getText().toString();
//                if(desc.isEmpty()){
//                    Toast.makeText(AddBookActivity.this, "Enter something", Toast.LENGTH_SHORT).show();
//                    return ;
//                }
//                String s="YiPee hurrah";
//                b=new Books("https://www.linkpicture.com/q/book_cover.png",false);
//                Intent i=new Intent();
//                //i.putExtra("book",b);
//                setResult(Activity.RESULT_OK,i);
//                finish();
//                //startActivity(i);
//            }
//        });
    }
}