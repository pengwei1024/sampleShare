package com.apkfuns.class4

import android.content.Context
import android.graphics.*
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
        val path = Path()
        val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.pic1)
        // 保存画布，剪切后恢复，不然剪切后画布就变小了
        canvas.save()
//        canvas.clipRect(100f, 100f, 600f, 600f)
        path.addArc(RectF(100f, 100f, 500f, 500f), 180f, 120f)
        path.lineTo(300f, 300f)
        canvas.clipPath(path)
        canvas.drawBitmap(bitmap, 0f, 0f, paint)
        canvas.restore()
        paint.strokeWidth = 10f
        canvas.drawPoint(bitmap.width.toFloat(), 10f, paint)
        canvas.drawPoint(10f, bitmap.height.toFloat(), paint)
        Log.w("###", "width=${canvas.width} height=${canvas.height}")

        val bit = Bitmap.createBitmap(BitmapFactory.decodeResource(resources, R.mipmap.pic1))
        canvas.drawBitmap(bit, 0f,0f ,paint)
        canvas.save()
        canvas.scale(.5f, .5f, bit.width/2f, bit.height/2f)
        canvas.rotate(-45f, bit.width/2f, bit.height/2f)
        canvas.drawBitmap(bit, 0f,0f ,paint)
        canvas.restore()
        canvas.drawPoint(bit.width/2f, bit.height/2f, paint)
    }
}