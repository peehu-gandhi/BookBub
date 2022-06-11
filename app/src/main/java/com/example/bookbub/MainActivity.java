package com.example.bookbub;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    GridLayout mainGrid;
    BottomNavigationView bn ;
    FrameLayout fl;
    GridLayout grid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bn=findViewById(R.id.bottom);
        getSupportActionBar().hide();

        grid = (GridLayout) findViewById(R.id.grid);
        fl=findViewById(R.id.fl);
        setSingleEvent(grid);



        //final Fragment[] selectedFragment = {new HomeFragment()};
        bn.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId())
                {
                    case R.id.navigation_home:
                        {

                            Intent i=new Intent(MainActivity.this,MainActivity.class);
                            startActivity(i);
                         //   System.out.println("in1");
                        //selectedFragment[0] = new HomeFragment();
                        break;

                    }
                    case R.id.navigation_dashboard: {
                        Fragment f=new UserProfileFragment();
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.frame, f)
                                .commit();
                       // selectedFragment[0] = new UserProfileFragment();
                        break;
                    }
                    case R.id.navigation_multisel:
                        {
                            Fragment f=new AboutFragment();
                            getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.frame, f)
                                    .commit();
                            //selectedFragment[0] = new AboutFragment();
                        break;
                    }
                }
//                getSupportFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.container, selectedFragment[0])
//                        .commit();
                return true;
            }
        });
       // mainGrid = (GridLayout) findViewById(R.id.mainGrid);

        //Set Event
       // setSingleEvent(mainGrid);
        //setToggleEvent(mainGrid);
    }

    private void setToggleEvent(GridLayout mainGrid) {
        //Loop all child item of Main Grid
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            final CardView cardView = (CardView) mainGrid.getChildAt(i);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (cardView.getCardBackgroundColor().getDefaultColor() == -1) {
                        //Change background color
                        cardView.setCardBackgroundColor(Color.parseColor("#FF6F00"));
                        Toast.makeText(MainActivity.this, "State : True", Toast.LENGTH_SHORT).show();

                    } else {
                        //Change background color
                        cardView.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                        Toast.makeText(MainActivity.this, "State : False", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

//    private void setSingleEvent(GridLayout mainGrid) {
//        //Loop all child item of Main Grid
//        for (int i = 0; i < mainGrid.getChildCount(); i++) {
//            //You can see , all child item is CardView , so we just cast object to CardView
//            CardView cardView = (CardView) mainGrid.getChildAt(i);
//            final int finalI = i;
//            cardView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                    Intent intent = new Intent(MainActivity.this,ActivityOne.class);
//                    intent.putExtra("info","This is activity from card item index  "+finalI);
//                    startActivity(intent);
//
//                }
//            });
//        }
//    }
private void setSingleEvent(GridLayout grid) {
    for(int i=0;i<grid.getChildCount();i++)
    {
        final CardView c= (CardView) grid.getChildAt(i);
        final int finalI = i;
        c.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                if(finalI ==0){
                    Intent i=new Intent(MainActivity.this,NewActivity.class);
                    startActivity(i);
                }
                else if(finalI ==1)
                {
                    Intent i=new Intent(MainActivity.this,AllBooks.class);
                    startActivity(i);                }
                else if(finalI ==2)
                {
                    Fragment fragment = new UserProfileFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frame, fragment, fragment.getClass().getSimpleName()).addToBackStack(null).commit();

                }
                else if(finalI ==3)
                {
                    Fragment fragment = new AboutFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frame, fragment, fragment.getClass().getSimpleName()).addToBackStack(null).commit();
//                    Toast.makeText(MainActivity.this, "call1", Toast.LENGTH_SHORT).show();
//                    FragmentManager fm=getSupportFragmentManager();
//                    Toast.makeText(MainActivity.this, "call2", Toast.LENGTH_SHORT).show();
//                    fm.beginTransaction()
//                            .replace(R.id.frame,AboutFragment.class,null)
//                            .setReorderingAllowed(true)
//                            .addToBackStack("name")
//                            .commit();
//                    Toast.makeText(MainActivity.this, "call3", Toast.LENGTH_SHORT).show();

//                    fm.beginTransaction().replace(R.id.frame,AboutFragment.class,null);
                   // Toast.makeText(MainActivity.this, "call", Toast.LENGTH_SHORT).show();
                    //Fragment fragment2 = new AboutFragment();
//                    getSupportFragmentManager().beginTransaction()
//                            .replace(R.id.frame, fragment2, fragment2.getClass().getSimpleName()).addToBackStack(null).commit();
//
//
//                   final FragmentTransaction ft = getFragmentManager().beginTransaction();
//                    ft.replace(R.id.frame, new AboutFragment(), "NewFragmentTag");
//                    ft.commit();
//                    replace(R.id.frame, AboutFragment.newInstance(), "Your_TAG");
//                    addToBackStack(null);

//                    final AboutFragment fragment = new AboutFragment();
//                    final androidx.fragment.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                    transaction.replace(R.id.frame, fragment, "param3");
//                    transaction.addToBackStack(null);
//                    transaction.commit();
                }

//                if(finalI==1) System.out.println("1 ko cklick");
//                Intent i=new Intent(getContext(),NewActivity.class);
//                startActivity(i);
//                fl.removeAllViews();
//                Fragment selectedFragment=new PublishBook().newInstance(String.valueOf(finalI),"");
//                getChildFragmentManager().beginTransaction().replace(R.id.fl, selectedFragment).commit();
            }

        });
    }
}
}