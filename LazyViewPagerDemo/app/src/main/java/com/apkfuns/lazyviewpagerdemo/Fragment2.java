package com.apkfuns.lazyviewpagerdemo;

/**
 * Created by pengwei on 2017/2/9.
 */

public class Fragment2 extends Fragment1 {
    @Override
    protected String showText() {
        return "第二个Fragment";
    }

    @Override
    protected int showImgRes() {
        return R.mipmap.ic_launcher_round;
    }
}
