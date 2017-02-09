package com.apkfuns.lazyviewpagerdemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.ViewPager);
        MainViewPagerAdapter adapter = new MainViewPagerAdapter(getSupportFragmentManager(),
                new Fragment[]{new Fragment1(), new Fragment2(), new Fragment3()});
        viewPager.setAdapter(adapter);
    }

    class MainViewPagerAdapter extends FragmentPagerAdapter {

        private Fragment[] data;

        public MainViewPagerAdapter(FragmentManager fm, Fragment[] data) {
            super(fm);
            this.data = data;
        }

        @Override
        public Fragment getItem(int position) {
            return data[position];
        }

        @Override
        public int getCount() {
            return data.length;
        }
    }
}
