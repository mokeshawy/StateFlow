package com.example.stateflow.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.example.stateflow.R
import com.squareup.picasso.Picasso

object Utils {

    // constants for url.
    const val BASE_URL  = "https://newsapi.org/"
    const val END_POINT = "v2/top-headlines"
    const val Q_KEY     = "q"
    const val API_KEY   = "apiKey"
    const val TESLA_KEY = "tesla"
    const val API       = "9b3d814ad7e840fa97fa9608886787f5"

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
        Picasso.get().load(uri).fit().centerInside().placeholder(progressDrawable).into(this)
    }

    //This function is used check if the device is connected to the Internet or not.
    @RequiresApi(Build.VERSION_CODES.M)
    fun isNetworkAvailable(context: Context) : Boolean{
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

        return when{
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true

            //for other device how are able to connect with Ethernet
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true

            else -> false
        }
    }

    // function for constants toast.
    fun constToast( context: Context , text : String ){
        Toast.makeText(context , text , Toast.LENGTH_SHORT).show()
    }
}