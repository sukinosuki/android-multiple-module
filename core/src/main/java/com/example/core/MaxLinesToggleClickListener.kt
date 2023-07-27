package com.example.core

import android.transition.ChangeBounds
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.Transition
import androidx.transition.TransitionManager


class MaxLinesToggleClickListener(val collapsedLines: Int) : View.OnClickListener {

    val transition = ChangeBounds().apply {
        duration = 200
        interpolator = FastOutSlowInInterpolator()
    }

    override fun onClick(v: View) {
        TransitionManager.beginDelayedTransition(findParent(v), transition as Transition)
        val textView = v as TextView
        textView.maxLines =
            if (textView.maxLines > collapsedLines) collapsedLines else Int.MAX_VALUE
    }

    fun findParent(view: View): ViewGroup {
        var parentView: View? = view

        while (parentView != null) {
            val parent = parentView.parent as View
            if (parent is RecyclerView) {
                return parent
            }

            parentView = parent
        }

        return view.parent as ViewGroup
    }
}