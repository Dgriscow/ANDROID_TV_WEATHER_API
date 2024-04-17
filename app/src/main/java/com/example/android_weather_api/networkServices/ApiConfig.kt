package com.example.android_weather_api.networkServices

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {
   companion object{


       fun getAPIWeatherService():ApiWeatherService{
           // API response interceptor
           val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

           // Client builder
           val client = OkHttpClient.Builder()
               .addInterceptor((loggingInterceptor)) //add a interceptor to the client
               .build() //build the client

           //Retrofit the API response to the object, retrofit contains the API service to return
           val retrofit = Retrofit.Builder()
               .baseUrl("https://api.openweathermap.org/data/2.5/")
               .addConverterFactory(GsonConverterFactory.create())
               .client(client)
               .build()

           return retrofit.create(ApiWeatherService::class.java)
           
       }


       const val API_KEY = "2356ce2875cfefb17837ec73536fafb6"



   }
}