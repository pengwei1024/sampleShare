package com.apkfuns.lazyviewpagerdemo;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by pengwei on 2017/2/9.
 */

public class Fragment1 extends MainTabFragment {

    private ImageView icon;
    private TextView text;

    @Override
    protected int getLayout() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initView(View mView) {
        super.initView(mView);
        icon = (ImageView) mView.findViewById(R.id.icon);
        text = (TextView) mView.findViewById(R.id.text);
    }

    @Override
    public void onFirstUserVisible() {
        super.onFirstUserVisible();
        icon.setImageResource(showImgRes());
        text.setText(showText());
    }

    protected String showText() {
        return "第一个Fragment";
    }

    protected int showImgRes() {
        return R.mipmap.ic_launcher;
    }
}
