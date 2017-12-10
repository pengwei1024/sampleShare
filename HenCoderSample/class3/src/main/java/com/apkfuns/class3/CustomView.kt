package com.apkfuns.class3

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.opengl.ETC1.getWidth
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import android.util.Log
import android.view.MotionEvent


/**
 * Created by zsn on 2017/12/8.
 */
class CustomView : View {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val path = Path()
    private var curWidth:Int = 400
    private val linePaint = Paint()

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.textSize = 45f
        paint.style = Paint.Style.STROKE
        // 设置拐角
//        val pathEffect = CornerPathEffect(20f)
//        paint.pathEffect = pathEffect
//        path.lineTo(400f, 400f)
//        path.lineTo(800f, 0f)
//        canvas.drawPath(path, paint)

        // 围绕圆形绘制文字
        path.addCircle(300f, 300f, 200f, Path.Direction.CCW)
        canvas.drawTextOnPath("Hello World, Hello World, Hello World, Hello World",
                path,  0f, 0f, paint)

        // 自适应长宽的文本控件
        canvas.translate(0f, 550f)
        val tp = TextPaint()
        tp.color = Color.BLUE
        tp.style = Paint.Style.FILL
        tp.textSize = 50f
        val message = "paint,draw paint指用颜色画,如油画颜料、水彩或者水墨画,而draw 通常指用铅笔、钢笔或者粉笔画,后者一般并不涂上颜料。两动词的相应名词分别为p"
        val myStaticLayout = StaticLayout(message, tp, curWidth, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false)
        myStaticLayout.draw(canvas)
        linePaint.color = Color.RED
        linePaint.style = Paint.Style.STROKE
        canvas.drawRect(0f, 0f, curWidth.toFloat(), myStaticLayout.height.toFloat(), linePaint)

        val typeface = Typeface.createFromAsset(context.assets, "mzd.ttf")
        paint.typeface = typeface
        // 删除线
        paint.isStrikeThruText = true
        // 下划线
        paint.isUnderlineText = true
//        paint.letterSpacing = 1f
        canvas.drawText("你好世界, 你好china", 450f, 100f, paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when(event.action) {
            MotionEvent.ACTION_MOVE -> {
                curWidth = event.rawX.toInt()
                Log.w("###", "ACTION_MOVE:" + curWidth)
                postInvalidate()
            }
            else -> {}
        }
        return true
    }
}