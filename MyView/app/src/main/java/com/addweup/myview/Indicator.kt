package com.addweup.myview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View

class Indicator: View{
    constructor(ctx: Context) : super(ctx)
    constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs)

    private val TAG = "Indicator"
    private var measureWidth = 0
    private var measureHeight = 0
    private var tailWidth = 40f
    private var lineHeight = 10f
    private var dotRaduis = 20f
    private var dotCount = 5
    private var bgPaint: Paint? = null
    private var fgPaint: Paint? = null
    private var fg2Paint: Paint? = null
    private var circleWidth = 5f

    var currentStep = 3

    init {
        bgPaint = Paint()
        bgPaint?.color = Color.parseColor("#e8e8e8")
        bgPaint?.strokeWidth = lineHeight

        fgPaint = Paint()
        fgPaint?.color =  Color.parseColor("#23afbd")
        fgPaint?.strokeWidth = lineHeight

        fg2Paint = Paint()
        fg2Paint?.color =  Color.parseColor("#ffffff")
        fg2Paint?.strokeWidth = lineHeight
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        Log.i(TAG, "onMeasure")
        measureWidth = View.MeasureSpec.getSize(widthMeasureSpec)
        measureHeight = View.MeasureSpec.getSize(heightMeasureSpec)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        Log.i(TAG, "onLayout")
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        Log.i(TAG, "onDraw")
        Log.i(TAG, String.format("MeasureWidth %d, MeasureHeight %d", measureWidth, measureHeight))
        val centerY = (measureHeight / 2).toFloat()
        val stepWidth = (measureWidth - tailWidth*2)/(dotCount-1)
        drawLine(canvas, centerY, stepWidth)
        drawCircle(canvas, centerY, stepWidth)
    }

    private fun drawLine(canvas: Canvas?, centerY: Float, stepWidth: Float){
        val left: Float = 0f
        val middle = tailWidth + stepWidth * currentStep
        val right: Float = measureWidth.toFloat()
        canvas?.drawLine(left, centerY, middle, centerY, fgPaint)
        canvas?.drawLine(middle, centerY, right, centerY, bgPaint)
    }

    private fun drawCircle(canvas: Canvas?, centerY: Float, stepWidth: Float){

        for(i in 0 until  currentStep){
            val x = tailWidth+stepWidth*i
            canvas?.drawCircle(x, centerY, dotRaduis, fgPaint)
            canvas?.drawCircle(x, centerY, dotRaduis-circleWidth, fg2Paint)
        }
        canvas?.drawCircle(tailWidth+stepWidth*currentStep, centerY, dotRaduis, fgPaint)
        for(i in currentStep+1 until dotCount){
            canvas?.drawCircle(tailWidth+stepWidth*i, centerY, dotRaduis, bgPaint)
        }
    }
}