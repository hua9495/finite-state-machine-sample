package com.alexchan.finite_state_machine.core.state

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.alexchan.finite_state_machine.R
import com.alexchan.finite_state_machine.databinding.LayoutLoadingPlaceholderViewBinding

class DataLoadingPlaceholderView : ConstraintLayout {

    private lateinit var binding: LayoutLoadingPlaceholderViewBinding

    constructor(context: Context) : super(context) {
        sharedInit()
    }

    constructor(context: Context, attr: AttributeSet) : super(context, attr) {
        sharedInit()
    }

    var onRetry: (() -> Unit)? = null

    private fun sharedInit() {
        val view = inflate(context, R.layout.layout_loading_placeholder_view, this)
        binding = LayoutLoadingPlaceholderViewBinding.bind(view)
        binding.retryButton.setOnClickListener {
            onRetry?.invoke()
        }
    }

    fun bind(state: State<*>) {
        with(binding) {
            when (state) {
                is State.Loading -> {
                    loadingIndicator.isVisible = true
                    errorTitleTextView.isVisible = false
                    errorSubtitleTextView.isVisible = false
                    placeholderImageView.isVisible = false
                    retryButton.isVisible = false
                }

                is State.LoadingFailed -> {
                    loadingIndicator.isVisible = false
                    errorTitleTextView.isVisible = true
                    errorSubtitleTextView.isVisible = true
                    placeholderImageView.isVisible = true
                    retryButton.isVisible = true
                }

                is State.RetryingLoad -> {
                    loadingIndicator.isVisible = true
                    errorTitleTextView.isVisible = false
                    errorSubtitleTextView.isVisible = false
                    placeholderImageView.isVisible = false
                    retryButton.isVisible = false
                }

                is State.Initial, is State.Loaded -> {
                    loadingIndicator.isVisible = false
                    errorTitleTextView.isVisible = false
                    errorSubtitleTextView.isVisible = false
                    placeholderImageView.isVisible = false
                    retryButton.isVisible = false
                }
            }
        }
    }
}
