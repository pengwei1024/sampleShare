package net.hiandroid.kotlinstudy.basic

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import net.hiandroid.kotlinstudy.R
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by pengwei on 2017/6/24.
 */
class HelloWorld : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView.text = "1234"
        textView.setOnClickListener {
            Log.d("tag", "click")
        }
        toast("1234")
    }

    fun AppCompatActivity.toast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    inline fun <reified T> T.debug(log: Any){
        Log.d(T::class.simpleName, log.toString())
    }


}