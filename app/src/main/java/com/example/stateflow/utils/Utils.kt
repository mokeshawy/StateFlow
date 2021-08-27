package com.example.stateflow.utils

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.example.stateflow.R
import com.squareup.picasso.Picasso

object Utils {

    // constants for url.
    const val BASE_URL      = "https://newsapi.org/"
    const val END_POINT     = "v2/top-headlines"
    const val Q_KEY         = "q"
    const val API_KEY       = "apiKey"
    const val TESLA_KEY     = "tesla"
    const val API = "9b3d814ad7e840fa97fa9608886787f5"

    // make progress loading for image.
    fun getProgressDrawable( context: Context) : CircularProgressDrawable{
        return CircularProgressDrawable(context).apply {
            strokeWidth = 10f
            centerRadius = 40f
            start()
        }
    }

    // function show image using picasso.
    fun ImageView.loadImage( uri : String? , progressDrawable: CircularProgressDrawable){
        progressDrawable.setColorSchemeColors(R.color.colorPrimary)
        Picasso.get().load(uri).fit().centerInside().placeholder(progressDrawable).into(this)
    }
}