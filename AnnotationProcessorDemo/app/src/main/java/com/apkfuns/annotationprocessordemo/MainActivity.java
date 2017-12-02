package com.apkfuns.annotationprocessordemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.apkfuns.annotationprocessordemo.utils.Utils;
import com.apkfuns.annotations.BindClass;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * https://github.com/JakeWharton/butterknife
 */
@BindClass(name = "TestActivity")
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.title)
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        title.setText("123");
    }

    @OnClick(R.id.title)
    public void onTextClick(View view) {
        Utils.showToast(this, "Hello World");
    }


}
