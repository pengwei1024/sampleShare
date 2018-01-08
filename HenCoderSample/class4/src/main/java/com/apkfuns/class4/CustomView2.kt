package com.apkfuns.class4

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
 * Created by pengwei on 2017/12/20.
 */
class CustomView2 : View {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val paint = Paint()

        // 斜切
        canvas.skew(0f, 0.1f)
        val bit = Bitmap.createBitmap(BitmapFactory.decodeResource(resources, R.mipmap.pic1))
        canvas.drawBitmap(bit, 0f,0f ,paint)
    }
}