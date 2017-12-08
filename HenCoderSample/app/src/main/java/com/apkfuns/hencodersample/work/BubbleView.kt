package com.apkfuns.hencodersample.work

import android.content.Context
import android.content.res.TypedArray
import android.graphics.*
import android.util.AttributeSet
import android.widget.TextView
import com.apkfuns.hencodersample.R

/**
 * Created by pengwei on 2017/12/6.
 */
class BubbleView : TextView {

    private val paint = Paint()
    private var path: Path? = null
    private var arrowHeight: Int = 20
    private var arrowWidth: Int = 40
    private var arrowPadding: Int = 15
    private var bubbleColor: Int? = null
    private val corners: Float = 15f

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        setPadding(paddingLeft, arrowHeight + paddingTop, paddingRight, paddingBottom)
        if (attrs != null) {
            val types: TypedArray? = context?.obtainStyledAttributes(attrs, R.styleable.BubbleView)
            bubbleColor = types?.getColor(R.styleable.BubbleView_bubbleColor, Color.RED)
            types?.recycle()
        }
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        setupBubble()
    }

    /**
     * 设置 bubble 样式
     */
    private fun setupBubble() {
        paint.style = Paint.Style.FILL
        paint.color = bubbleColor!!
        paint.isAntiAlias = true
        path = Path()
        val realWidth: Int = width - arrowPadding
        path?.moveTo((realWidth - arrowWidth).toFloat(), arrowHeight.toFloat())
        path?.lineTo(realWidth.toFloat(), arrowHeight.toFloat())
        path?.lineTo((realWidth - arrowWidth / 2).toFloat(), 0f)
        path?.close()
        path?.addRoundRect(RectF(0f, arrowHeight.toFloat(), width.toFloat(), height.toFloat()),
                corners, corners, Path.Direction.CCW)
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.drawPath(path, paint)
        super.onDraw(canvas)
    }
}