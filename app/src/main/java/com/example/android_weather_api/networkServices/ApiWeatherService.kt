package com.example.android_weather_api.networkServices

import com.example.android_weather_api.weatherDataRes.WeatherData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

//Navigation interface for a API, Interface because only one abstract interface service is needed
interface ApiWeatherService {

    //Get current data from a API call
    // Get current weather data
    // v2 / weather <- retriving curr weather API RESPONSE
    @GET("weather")
    fun getCurrentWeather(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("appid") appid: String = ApiConfig.API_KEY
    ): Call<WeatherData>



}