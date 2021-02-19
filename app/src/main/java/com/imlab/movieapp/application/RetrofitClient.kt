package com.imlab.movieapp.application

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.imlab.movieapp.interfaces.IWebService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    val webservice: IWebService by lazy {

        Log.d("LiveData","En el retrofit")

        Retrofit.Builder()
                .baseUrl( AppConstans.BASE_URL )
                .addConverterFactory( GsonConverterFactory.create( GsonBuilder().create() ) )
                .build().create( IWebService::class.java )

    }

}