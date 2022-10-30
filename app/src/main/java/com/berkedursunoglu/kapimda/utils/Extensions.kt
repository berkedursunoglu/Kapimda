package com.berkedursunoglu.kapimda.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

object Extensions {

    fun ImageView.getImage(url:String,context:Context,view:ImageView){
        Glide.with(context).load(url).into(view)
    }

}