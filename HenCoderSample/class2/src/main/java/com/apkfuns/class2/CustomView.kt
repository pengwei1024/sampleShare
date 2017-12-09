package com.apkfuns.class2

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.graphics.LightingColorFilter
import android.graphics.ColorFilter
import android.graphics.CornerPathEffect
import android.graphics.PathEffect
import android.graphics.DiscretePathEffect
import android.graphics.BlurMaskFilter









/**
 * Created by pengwei on 2017/12/8.
 * LinearGradient、RadialGradient、SweepGradient、BitmapShader、ComposeShader
 */
class CustomView : View {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val paint = Paint(Paint.ANTI_ALIAS_FLAG)

        // 绘制渐变的圆形
//        var shader  = LinearGradient(100f, 100f, 500f, 500f, Color.parseColor("#E91E63"),
//                Color.parseColor("#2196F3"), Shader.TileMode.CLAMP)
//        paint.shader = shader
//        canvas.drawCircle(300f, 300f, 200f, paint)
//        paint.textSize = 72f
//        canvas.drawText("Hello World", 20f, 60f, paint)


        // BitmapShader 和 配合实现圆形头像
//        var bitmap:Bitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher)
//        bitmap = Bitmap.createScaledBitmap(bitmap, 600, 600, true)
//        val shader = BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
//        paint.shader = shader
//        canvas.drawCircle(300f, 300f, 200f, paint)


        // 混合着色器
//        var bitmap1 = BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher)
//        val shader1 = BitmapShader(bitmap1, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT)
//        var bitmap2 = BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher_round)
//        val shader2 = BitmapShader(bitmap2, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT)
//        val shader = ComposeShader(shader1, shader2, PorterDuff.Mode.SRC_OVER)
//        paint.shader = shader
//        // ComposeShader() 在硬件加速下是不支持两个相同类型的 Shader 的，所以这里也需要关闭硬件加速才能看到效果
//        setLayerType(View.LAYER_TYPE_SOFTWARE, null)
//        canvas.drawRect(0f, 0f, 500f, 500f, paint)


        // ColorFilter
//        var option = BitmapFactory.Options()
//        option.inSampleSize = 2
//        var bitmap = BitmapFactory.decodeResource(resources, R.mipmap.pic1, option)
//        val lightingColorFilter = LightingColorFilter(0x00ffff, 0x003000)
//        paint.colorFilter = lightingColorFilter
//        // LightingColorFilter PorterDuffColorFilter 和 ColorMatrixColorFilter
//        canvas.drawBitmap(bitmap, 0f, 0f, paint)

        // setStrokeCap
//        paint.strokeCap = Paint.Cap.ROUND // 设置线头的形状。线头形状有三种：BUTT 平头、ROUND 圆头、SQUARE 方头。默认为 BUTT
//        paint.strokeJoin = Paint.Join.ROUND // 拐角的形状
//        paint.strokeWidth = 30f
//        canvas.drawLine(50f, 50f, 300f, 300f, paint)


        // 绘制虚线轮廓
//        paint.style = Paint.Style.STROKE
//        var pathEffect = DashPathEffect(floatArrayOf(10f, 5f), 10f)
//        paint.pathEffect = pathEffect
//        canvas.drawCircle(300f, 300f, 200f, paint)
//
//        var path = Path()
//        path.lineTo(100f, 100f)
//        path.lineTo(200f, 0f)
//        var pathEffect2 = CornerPathEffect(20f)
//        val pathEffect3 = DiscretePathEffect(20f, 5f)
//        paint.pathEffect = pathEffect3
//        canvas.drawPath(path, paint)

        // 文字添加阴影
//        paint.textSize = 70f
//        paint.setShadowLayer(10f, 0f, 0f, Color.RED)
//        canvas.drawText("Hello World", 80f, 300f, paint)

        // 设置模糊效果
        // 需要关闭硬件加速
        setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        paint.maskFilter = BlurMaskFilter(50f, BlurMaskFilter.Blur.NORMAL)
        val options = BitmapFactory.Options()
        options.inSampleSize = 2
        val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.pic2, options)
        canvas.drawBitmap(bitmap, 100f, 100f, paint)
    }
}