package com.addweup.myview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View


class ProgressIndicator: View{
    constructor(ctx: Context) : super(ctx){
        initPaint()
    }

    constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs){
        initAttrs(attrs)
        initPaint()
    }

    constructor(ctx: Context, attrs: AttributeSet, defStyle:Int): super(ctx, attrs, defStyle){
        initAttrs(attrs)
        initPaint()
    }

    private val TAG = "ProgressIndicator"

    private val attrs = object{
        var measureWidth = 0
        var measureHeight = 0
        var tailLength = 40f
        var strokeWidth = 10f
        var dotRadius = 20f
        var dotCount = 5
        var dotStrokeWidth = 5f
        var progressBg = Color.parseColor("#e8e8e8")
        var progressFg = Color.parseColor("#23afbd")
        var progressFg2 = Color.parseColor("#ffffff")
    }

    private val paint = object{
        var bg = Paint()
        var fg = Paint()
        var fg2 = Paint()
    }

    var currentStep = 1
        set(value){
            field = value
            invalidate()
        }

    private fun initAttrs(attrs: AttributeSet){
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ProgressIndicator)
        this.attrs.tailLength =typedArray.getDimension(R.styleable.ProgressIndicator_tailLength, this.attrs.tailLength)
        this.attrs.strokeWidth =typedArray.getDimension(R.styleable.ProgressIndicator_strokeWidth, this.attrs.strokeWidth)
        this.attrs.dotRadius =typedArray.getDimension(R.styleable.ProgressIndicator_dotRadius, this.attrs.dotRadius)
        this.attrs.dotCount =typedArray.getInteger(R.styleable.ProgressIndicator_dotCount, this.attrs.dotCount)
        this.attrs.dotStrokeWidth =typedArray.getDimension(R.styleable.ProgressIndicator_dotStrokeWidth, this.attrs.dotStrokeWidth)
        this.attrs.progressBg =typedArray.getColor(R.styleable.ProgressIndicator_progressBg, this.attrs.progressBg)
        this.attrs.progressFg =typedArray.getColor(R.styleable.ProgressIndicator_progressFg, this.attrs.progressFg)
        this.attrs.progressFg2 =typedArray.getColor(R.styleable.ProgressIndicator_progressFg2, this.attrs.progressFg2)
        typedArray.recycle()
    }

    private fun initPaint(){
        paint.bg.color = attrs.progressBg
        paint.bg.strokeWidth = attrs.strokeWidth

        paint.fg.color =  attrs.progressFg
        paint.fg.strokeWidth = attrs.strokeWidth

        paint.fg2.color =  attrs.progressFg2
        paint.fg2.strokeWidth = attrs.strokeWidth
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        Log.i(TAG, "onMeasure")
        attrs.measureWidth = View.MeasureSpec.getSize(widthMeasureSpec)
        attrs.measureHeight = View.MeasureSpec.getSize(heightMeasureSpec)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        Log.i(TAG, "onLayout")
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        Log.i(TAG, "onDraw")
        Log.i(TAG, "MeasureWidth $attrs.measureWidth, MeasureHeight $attrs.measureHeight")
        val centerY = (attrs.measureHeight / 2).toFloat()
        val stepWidth = (attrs.measureWidth - attrs.tailLength*2)/(attrs.dotCount-1)
        drawLine(canvas, centerY, stepWidth)
        drawCircle(canvas, centerY, stepWidth)
    }

    private fun drawLine(canvas: Canvas?, centerY: Float, stepWidth: Float){
        val left: Float = 0f
        val middle = attrs.tailLength + stepWidth * currentStep
        val right: Float = attrs.measureWidth.toFloat()
        canvas?.drawLine(left, centerY, middle, centerY, paint.fg)
        canvas?.drawLine(middle, centerY, right, centerY, paint.bg)
    }

    private fun drawCircle(canvas: Canvas?, centerY: Float, stepWidth: Float){
        for(i in 0 until  currentStep){
            val x = attrs.tailLength+stepWidth*i
            canvas?.drawCircle(x, centerY, attrs.dotRadius, paint.fg)
            canvas?.drawCircle(x, centerY, attrs.dotRadius-attrs.dotStrokeWidth, paint.fg2)
        }
        canvas?.drawCircle(attrs.tailLength+stepWidth*currentStep, centerY, attrs.dotRadius, paint.fg)
        for(i in currentStep+1 until attrs.dotCount){
            canvas?.drawCircle(attrs.tailLength+stepWidth*i, centerY, attrs.dotRadius, paint.bg)
        }
    }
}
