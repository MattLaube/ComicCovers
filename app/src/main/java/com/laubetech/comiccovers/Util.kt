package com.laubetech.comiccovers

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import androidx.swiperefreshlayout.widget.CircularProgressDrawable

fun ImageView.loadImage(uri: String?, prgressDrawable: CircularProgressDrawable){
    val  options = RequestOptions()
        .placeholder(prgressDrawable)
        .error(R.mipmap.ic_launcher)

    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(uri)
        .into(this)
}