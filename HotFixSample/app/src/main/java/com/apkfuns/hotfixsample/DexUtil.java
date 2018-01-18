package com.apkfuns.hotfixsample;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by pengwei on 2018/1/18.
 */

public class DexUtil {

    public static void showToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
