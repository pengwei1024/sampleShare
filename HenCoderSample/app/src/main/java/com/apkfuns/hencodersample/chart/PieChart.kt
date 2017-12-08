package com.apkfuns.hencodersample.chart

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

/**
 * Created by pengwei on 2017/12/6.
 * 饼图
 */
class PieChart : View {

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        var oval = RectF(50f, 50f, 500f, 500f)
        var ovalFirst = RectF(50-10f, 50-10f, 500-10f, 500-10f)
        val paint = Paint()
        paint.isAntiAlias = true
        paint.color = Color.parseColor("#F44236")
        // 180 °C 是水平线上部分
        canvas.drawArc(ovalFirst, 180f, 120f, true, paint)

        // 第二块扇形
        paint.color = Color.parseColor("#FEC107")
        canvas.drawArc(oval, 300f, 45f, true, paint)

        // 第三块扇形
        paint.color = Color.parseColor("#029688")
        canvas.drawArc(oval, 345f, 195f, true, paint)
    }
}