package com.apkfuns.hencodersample

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

/**
 * Created by pengwei on 2017/12/6.
 */
class CustomView : View {

    private val paint: Paint = Paint()
    private val rectPaint:Paint = Paint()
    private val rectF = RectF(400f, 50f, 700f, 400f)
    private val PinkPaint = Paint()

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        PinkPaint.color = Color.parseColor("#66ffaaff")
        paint.isAntiAlias = true
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 10f
        canvas?.drawCircle(300f, 300f, 200f, paint)

        rectPaint.color = Color.parseColor("#88880000")
        canvas?.drawRect(100f, 100f, 500f, 500f, rectPaint)
        canvas?.drawPoint(800f, 400f, paint)

        // 绘制多个点
        var points = floatArrayOf(0f, 0f, 50f, 50f, 50f, 100f, 100f, 50f, 100f, 100f, 150f, 50f, 150f, 100f)
        canvas?.drawPoints(points, 2 /* 跳过两个数，即前两个 0 */,
                8 /* 一共绘制 8 个数（4 个点）*/, paint)

        // 绘制椭圆
        canvas?.drawOval(rectF, paint)

        // 绘制多条线
        points = floatArrayOf(20f, 20f, 120f, 20f, 70f, 20f, 70f, 120f, 20f, 120f, 120f, 120f, 150f, 20f, 250f, 20f, 150f, 20f, 150f, 120f, 250f, 20f, 250f, 120f, 150f, 120f, 250f, 120f)
        canvas?.drawLines(points, paint)

        // 圆角矩形
        canvas?.drawRoundRect(rectF, 50f, 50f, PinkPaint)

        // 画扇形
        val rectF = RectF(200f, 600f, 800f, 1000f)
        canvas?.drawArc(rectF, 180f, 180f, false, rectPaint)

        // 绘制图片
        val options = BitmapFactory.Options()
        options.inSampleSize = 1
        val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher, options)
        canvas?.drawBitmap(bitmap, 0f, 600f, paint)

        // 绘制文字
        val textPaint = Paint()
        textPaint.textSize = 72f
        canvas?.drawText("Hello world", 0f, 800f, textPaint)
    }
}