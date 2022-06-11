package com.example.bookbub;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
public class PublishBook extends Fragment
{
//    private ViewPager viewPager;
//    private MyViewPagerAdapter myViewPagerAdapter;
//    private LinearLayout dotsLayout;
//    private TextView[] dots;
//    private int[] layouts;
//    private Button btnSkip, btnNext;
//    private PrefManager prefManager;
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//    private String mParam1;
//    private String mParam2;
//    public PublishBook() {}
//    public static PublishBook newInstance(String param1, String param2) {
//        PublishBook fragment = new PublishBook();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View root = inflater.inflate(R.layout.fragment_publish_book, container, false);
//        prefManager = new PrefManager(getContext());
//        if (!prefManager.isFirstTimeLaunch()) {
//            launchHomeScreen();
//            getActivity().finish();
//        }
//        if (Build.VERSION.SDK_INT >= 21) {
//            getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
//        }
//        getActivity().setContentView(R.layout.fragment_publish_book);
//        viewPager = (ViewPager) root.findViewById(R.id.view_pager);
//        dotsLayout = (LinearLayout) root.findViewById(R.id.layoutDots);
//        btnSkip = (Button) root.findViewById(R.id.btn_skip);
//        btnNext = (Button) root.findViewById(R.id.btn_next);
//        layouts = new int[]{
//                R.layout.publish_book_pg1,
//                R.layout.publish_book_pg2,
//                R.layout.publish_book_pg3,};
//        addBottomDots(0);
//        changeStatusBarColor();
//        myViewPagerAdapter = new MyViewPagerAdapter();
//        viewPager.setAdapter(myViewPagerAdapter);
//        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);
//        btnSkip.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v) {
//                launchHomeScreen();
//            }
//        });
//        btnNext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                int current = getItem(+1);
//                if (current < layouts.length) {
//                    // move to next screen
//                    viewPager.setCurrentItem(current);
//                } else {
//                    launchHomeScreen();
//                }
//            }
//        });
//        return inflater.inflate(R.layout.fragment_publish_book, container, false);
//    }
//    private void addBottomDots(int currentPage) {
//        dots = new TextView[layouts.length];
//        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
//        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);
//        dotsLayout.removeAllViews();
//        for (int i = 0; i < dots.length; i++) {
//            dots[i] = new TextView(getContext());
//            dots[i].setText(Html.fromHtml("&#8226;"));
//            dots[i].setTextSize(35);
//            dots[i].setTextColor(colorsInactive[currentPage]);
//            dotsLayout.addView(dots[i]);
//        }
//        if (dots.length > 0)
//            dots[currentPage].setTextColor(colorsActive[currentPage]);
//    }
//    private int getItem(int i) {
//        return viewPager.getCurrentItem() + i;
//    }
//
//    private void launchHomeScreen() {
//        prefManager.setFirstTimeLaunch(false);
//        startActivity(new Intent(PublishBook.this.getContext(), MainActivity.class));
//        getActivity().finish();
//    }
//    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {
//        @Override
//        public void onPageSelected(int position) {
//            addBottomDots(position);
//            if (position == layouts.length - 1) {
//                btnNext.setText(getString(R.string.start));
//                btnSkip.setVisibility(View.GONE);
//            } else {
//                btnNext.setText(getString(R.string.next));
//                btnSkip.setVisibility(View.VISIBLE);
//            }
//        }
//        @Override
//        public void onPageScrolled(int arg0, float arg1, int arg2) {
//        }
//        @Override
//        public void onPageScrollStateChanged(int arg0) {
//        }
//    };
//
//    private void changeStatusBarColor() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            Window window = getActivity().getWindow();
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
//        }
//    }
//
//    public static class MyViewPagerAdapter extends PagerAdapter {
//        private LayoutInflater layoutInflater;
//        public MyViewPagerAdapter() {
//        }
//
//        @Override
//        public Object instantiateItem(ViewGroup container, int position) {
//            layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            View view = layoutInflater.inflate(layouts[position], container, false);
//            container.addView(view);
//            return view;
//        }
//        @Override
//        public int getCount() {
//            return layouts.length;
//        }
//        @Override
//        public boolean isViewFromObject(View view, Object obj) {
//            return view == obj;
//        }
//        @Override
//        public void destroyItem(ViewGroup container, int position, Object object) {
//            View view = (View) object;
//            container.removeView(view);
//        }
//    }
}