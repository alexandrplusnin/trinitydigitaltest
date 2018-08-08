package com.example.alexandr.trinitydigitaltest.ui

import android.content.Context
import android.graphics.*
import android.support.annotation.ColorRes
import android.support.annotation.DimenRes
import android.support.v4.content.ContextCompat

import com.squareup.picasso.Transformation

class StrokeCircleTransformation : Transformation {

    private val mStrokeWidth: Int
    private val mStrokeColor: Int

    constructor(@DimenRes strokeWidth: Int, @ColorRes strokeColor: Int, cnt: Context) {
        mStrokeWidth = cnt.resources.getDimension(strokeWidth).toInt()
        mStrokeColor = ContextCompat.getColor(cnt, strokeColor)
    }

    constructor() {
        mStrokeWidth = 0
        mStrokeColor = -1
    }

    override fun transform(source: Bitmap): Bitmap {
        val size = Math.min(source.width, source.height)

        val width = (source.width - size) / 2
        val height = (source.height - size) / 2

        val bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)

        val canvas = Canvas(bitmap)
        val shader = BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        if (width != 0 || height != 0) {
            // source isn't square, move viewport to center
            val matrix = Matrix()
            matrix.setTranslate((-width).toFloat(), (-height).toFloat())
            shader.setLocalMatrix(matrix)
        }

        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.shader = shader

        val r = size / 2f
        canvas.drawCircle(r, r, r - mStrokeWidth, paint)

        if (mStrokeColor != -1) {
            val stroke = Paint(Paint.ANTI_ALIAS_FLAG)
            stroke.strokeCap = Paint.Cap.BUTT
            stroke.style = Paint.Style.STROKE
            stroke.strokeWidth = mStrokeWidth.toFloat()
            stroke.color = mStrokeColor
            canvas.drawCircle(r, r, r - mStrokeWidth, stroke)
        }

        source.recycle()

        return bitmap
    }

    override fun key(): String {
        return "StrokeCircleTransformation()"
    }
}