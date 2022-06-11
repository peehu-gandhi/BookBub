package com.example.bookbub;
import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.Toast;

public class HomeFragment extends Fragment
{
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    FrameLayout fl;
    GridLayout grid;
    private String mParam1;
    private String mParam2;
    public HomeFragment() {}
    public static HomeFragment newInstance(String param1, String param2)
    {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_home, container, false);
        grid = (GridLayout) v.findViewById(R.id.grid);
        fl=v.findViewById(R.id.fl);
        setSingleEvent(grid);
        return v;
    }
    private void setSingleEvent(GridLayout grid) {
    for(int i=0;i<grid.getChildCount();i++)
    {
        if(i==0){
            Toast.makeText(getContext(), "0 pe click", Toast.LENGTH_SHORT).show();
        }
        else if(i==1){
            Toast.makeText(getContext(), "1 pe click", Toast.LENGTH_SHORT).show();
        }
        else if(i==2){
            Toast.makeText(getContext(), "2 pe click", Toast.LENGTH_SHORT).show();
        }
        else if(i==3){
            Toast.makeText(getContext(), "3 pe click", Toast.LENGTH_SHORT).show();
        }
        final CardView c= (CardView) grid.getChildAt(i);
        int finalI = i;
        c.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
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