package com.apkfuns.class4

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View

/**
 * Created by pengwei on 2017/12/12.
 */
class CustomView: View {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val paint = Paint()
        val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.pic1)
        // 保存画布，剪切后恢复，不然剪切后画布就变小了
        canvas.save()
        canvas.clipRect(100f, 100f, 600f, 600f)
        canvas.drawBitmap(bitmap, 0f, 0f, paint)
        canvas.restore()
        paint.strokeWidth = 10f
        canvas.drawPoint(bitmap.width.toFloat(), 10f, paint)
        canvas.drawPoint(10f, bitmap.height.toFloat(), paint)
        Log.w("###", "width=${canvas.width} height=${canvas.height}")
    }
}