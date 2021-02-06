package com.nakhli.testeurosport.ui

import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide

//Show Toast
fun Context?.toast(msgText: String, duration: Int = Toast.LENGTH_LONG) {
    return Toast.makeText(this, msgText, duration).show()
}

//Load Image
fun Context.setImage(thumb: String, image: ImageView) {
    Glide.with(this).load(thumb).into(image)
}

//Enum for News Type
enum class CurrentNewsType(val type: Int) {
    STORY_TYPE(0),
    VIDEO_TYPE(1),
}