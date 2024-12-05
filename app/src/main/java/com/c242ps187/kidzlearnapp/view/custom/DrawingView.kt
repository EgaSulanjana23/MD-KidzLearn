package com.c242ps187.kidzlearnapp.view.custom

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.DashPathEffect
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PorterDuff
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class DrawingView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val dashedPaint = Paint().apply {
        color = Color.GRAY
        style = Paint.Style.STROKE
        strokeWidth = 8f
        pathEffect = DashPathEffect(floatArrayOf(10f, 20f), 0f)
    }

    private val drawingPaint = Paint().apply {
        color = Color.BLACK
        style = Paint.Style.STROKE
        strokeWidth = 24f
    }

    private val dashedBitmap = Bitmap.createBitmap(800, 800, Bitmap.Config.ARGB_8888)
    private val dashedCanvas = Canvas(dashedBitmap)

    private val drawingBitmap = Bitmap.createBitmap(800, 800, Bitmap.Config.ARGB_8888)
    private val drawingCanvas = Canvas(drawingBitmap)

    private var currentPath = Path()

    init {
        // Gambar huruf pada lapisan garis putus-putus
        drawDashedLetters(dashedCanvas)
    }

    private fun drawDashedLetters(canvas: Canvas) {
        val centerX = canvas.width / 2f
        val centerY = canvas.height / 2f

        val pathA = Path().apply {
            moveTo(centerX - 150f, centerY + 200f) // Garis miring kiri huruf A
            lineTo(centerX, centerY - 200f)     // Titik puncak huruf A
            lineTo(centerX + 150f, centerY + 200f) // Garis miring kanan huruf A
            moveTo(centerX - 75f, centerY)       // Garis horizontal tengah
            lineTo(centerX + 25f, centerY)
        }
        canvas.drawPath(pathA, dashedPaint)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // Lapisan 1: Huruf dengan garis putus-putus
        canvas.drawBitmap(dashedBitmap, 0f, 0f, null)

        // Lapisan 2: Gambar pengguna
        canvas.drawBitmap(drawingBitmap, 0f, 0f, null)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                currentPath.moveTo(event.x, event.y)
            }
            MotionEvent.ACTION_MOVE -> {
                currentPath.lineTo(event.x, event.y)
                drawingCanvas.drawPath(currentPath, drawingPaint)
            }
            MotionEvent.ACTION_UP -> {
                currentPath.reset()
            }
        }
        invalidate() // Redraw view
        return true
    }

    fun clearCanvas() {
        drawingCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR)
        invalidate() // Redraw view
    }

    fun getBitmap(): Bitmap {
        val resultBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val resultCanvas = Canvas(resultBitmap)
        resultCanvas.drawBitmap(dashedBitmap, 0f, 0f, null) // Tambahkan lapisan huruf
        resultCanvas.drawBitmap(drawingBitmap, 0f, 0f, null) // Tambahkan lapisan pengguna
        return resultBitmap
    }
}