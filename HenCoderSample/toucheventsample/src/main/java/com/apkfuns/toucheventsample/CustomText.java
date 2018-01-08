package com.apkfuns.toucheventsample;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by pengwei on 2018/1/8.
 */

public class CustomText extends android.support.v7.widget.AppCompatTextView {
    public CustomText(Context context) {
        super(context);
    }

    public CustomText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(MainActivity.getTAG(), "onTouchEvent");
        return super.onTouchEvent(event);
    }
}
