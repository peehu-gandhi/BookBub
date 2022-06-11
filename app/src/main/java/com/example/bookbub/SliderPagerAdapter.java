package com.example.bookbub;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class SliderPagerAdapter extends FragmentPagerAdapter {
    public SliderPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }
    @NonNull @Override public SliderItemFragment getItem(int position) {
        return SliderItemFragment.newInstance(position);
    }
    // size is hardcoded
    @Override public int getCount() {
        return 4;
    }
}