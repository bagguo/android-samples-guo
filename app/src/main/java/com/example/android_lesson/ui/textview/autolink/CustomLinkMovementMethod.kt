package com.example.android_lesson.ui.textview.autolink

import android.text.Layout
import android.text.Spannable
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.MotionEvent
import android.widget.TextView

class CustomLinkMovementMethod : LinkMovementMethod() {
    //CharSequence -> Spannable
    override fun onTouchEvent(widget: TextView, buffer: Spannable, event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_UP) {
            val x = event.x.toInt()
            val y = event.y.toInt()

            val layout: Layout = widget.layout
            val line = layout.getLineForVertical(y)
            val off = layout.getOffsetForHorizontal(line, x.toFloat())

            val link = buffer.getSpans(off, off, ClickableSpan::class.java)

            if (link.isNotEmpty()) {
                link[0].onClick(widget)
                return true
            }
        }
        return super.onTouchEvent(widget, buffer, event)
    }
}
