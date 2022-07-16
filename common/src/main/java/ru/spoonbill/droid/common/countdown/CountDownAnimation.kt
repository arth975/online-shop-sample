package ru.spoonbill.app.common.countdown

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.TextView


@SuppressLint("SetTextI18n")
class CountDownAnimation(
    private val mTextView: TextView,
    private val mDuration: Long = 1000L,
    /**
     * Sets a new starting count number for the count down animation.
     *
     * @param startCount
     * The starting count number
     */
    var startCount: Int
) {
    private var mAnimation: Animation = AlphaAnimation(1.0f, 0.0f).apply { duration = mDuration }

    /**
     * Returns the starting count number for the count down animation.
     */
    private var mCurrentCount = 0
    private var mListener: CountDownListener? = null
    private val mHandler: Handler = Handler(Looper.myLooper()!!)
    private val mCountDown = Runnable {
        if (mCurrentCount > 0) {
            mTextView.text = mCurrentCount.toString() + ""
            mTextView.startAnimation(mAnimation)
            mCurrentCount--
        } else {
            mTextView.visibility = View.GONE
            mListener?.onCountDownEnd(this@CountDownAnimation)
        }
    }

    /**
     * Starts the count down animation.
     */
    fun start() {
        mHandler.removeCallbacks(mCountDown)
        mTextView.text = startCount.toString() + ""
        mTextView.visibility = View.VISIBLE
        mCurrentCount = startCount
        mHandler.post(mCountDown)
        for (i in 1..startCount) {
            mHandler.postDelayed(mCountDown, (i * 1000).toLong())
        }
    }

    /**
     * Cancels the count down animation.
     */
    fun cancel() {
        mHandler.removeCallbacks(mCountDown)
        mTextView.text = ""
        mTextView.visibility = View.GONE
    }
    /**
     * Returns the animation used during the count down.
     */
    /**
     * Sets the animation used during the count down. If the duration of the
     * animation for each number is not set, one second will be defined.
     */
    var animation: Animation
        get() = mAnimation
        set(animation) {
            mAnimation = animation
            if (mAnimation.duration == 0L) mAnimation.duration = 1000
        }

    /**
     * Binds a listener to this count down animation. The count down listener is
     * notified of events such as the end of the animation.
     *
     * @param listener
     * The count down listener to be notified
     */
    fun setCountDownListener(listener: CountDownListener?) {
        mListener = listener
    }

    /**
     * A count down listener receives notifications from a count down animation.
     * Notifications indicate count down animation related events, such as the
     * end of the animation.
     */
    interface CountDownListener {
        /**
         * Notifies the end of the count down animation.
         *
         * @param animation
         * The count down animation which reached its end.
         */
        fun onCountDownEnd(animation: CountDownAnimation?)
    }
}