package com.apkfuns.hencodersample

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

/**
 * Created by pengwei on 2017/12/6.
 */
class PathView: View {

    private val paint = Paint()
    private val path = Path()

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int): super(context, attrs, defStyleAttr, defStyleRes) {
        paint.color = Color.RED
        path.addArc(RectF(200f, 200f, 400f, 400f), -225f, 225f)
        path.arcTo(RectF(400f, 200f, 600f, 400f), -180f, 225f, false)
        path.lineTo(400f, 542f)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawPath(path, paint)
    }

    /**
     *  第一组： addXxx() ——添加子图形
     *  第二组：xxxTo() ——画线（直线或曲线）
     */
}