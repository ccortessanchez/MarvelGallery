package com.example.ccsa.marvelapp.view.views

import android.util.AttributeSet
import android.widget.FrameLayout
import android.content.Context

class SquareFrameLayout @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0): FrameLayout(context, attrs, defStyleAttr) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        // Force element to have always same height as width
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }
}