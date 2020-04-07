package me.tatocaster.covid_19geocount.common.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import me.tatocaster.covid_19geocount.R

class CustomProgressBar : FrameLayout {
    constructor(context: Context) : super(context) {
        initViews()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initViews()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initViews()
    }

    private fun initViews() {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.view_progressbar, this, true)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return true
    }

    fun hide() {
        visibility = View.GONE
    }

    fun show() {
        visibility = View.VISIBLE
    }
}