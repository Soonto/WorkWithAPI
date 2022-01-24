package com.example.fragment


import android.app.Application
import android.view.GestureDetector
import android.view.MotionEvent
import com.example.fragment.view_model.MainActivityViewModel

class GestureListener(
    private val viewModel : MainActivityViewModel
) : GestureDetector.SimpleOnGestureListener() {
    override fun onFling(
        e1: MotionEvent?,
        e2: MotionEvent?,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        if(e1 != null && e2 != null){
            if(e1.x - e2.x > MIN_DISTANCE){
                viewModel.setRecycleViewMode()
            }
            if(e2.x - e1.x > MIN_DISTANCE)
            {
                viewModel.setCommonMode()
            }
        }

        return super.onFling(e1, e2, velocityX, velocityY)

    }

    companion object{
        const val MIN_DISTANCE = 120
    }
}