package com.token.quicksell.ui.sell

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.token.quicksell.R
import com.token.quicksell.utils.GlideApp


@BindingAdapter("productImageUrl")
fun loadImage(imageView: ImageView, url: String) {
    GlideApp.with(imageView.context)
        .load(url)
        .error(R.drawable.placeholder)
        .centerCrop()
        .into(imageView)
}
