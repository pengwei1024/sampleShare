package com.apkfuns.annotationprocessordemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.apkfuns.annotations.BindClass;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * https://github.com/JakeWharton/butterknife
 */
@BindClass()
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
}
