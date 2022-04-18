package com.token.quicksell.ui.sell

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.token.quicksell.R


@BindingAdapter("productImageUrl")
fun loadImage(imageView: ImageView, url: String?) {
    Glide.with(imageView.context)
        .load(url)
        .error(R.drawable.placeholder)
        .centerCrop()
        .into(imageView)
}
