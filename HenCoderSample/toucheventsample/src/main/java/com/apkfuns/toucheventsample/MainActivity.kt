package com.apkfuns.toucheventsample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.TouchDelegate
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    companion object {
        @JvmStatic
        val TAG:String = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<TextView>(R.id.text).setOnTouchListener(View.OnTouchListener { view, motionEvent ->
            Log.d(TAG, "View setOnTouchListener")
            true
        })

    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        val bool = super.dispatchTouchEvent(ev)
//        Log.d(TAG, "dispatchTouchEvent:$bool")
        return true
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val bool = super.onTouchEvent(event)
        Log.d(TAG, "onTouchEvent:$bool")
        return bool
    }
}
