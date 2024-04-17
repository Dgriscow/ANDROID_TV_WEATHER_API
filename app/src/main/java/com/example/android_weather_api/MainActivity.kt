package com.example.android_weather_api

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.health.connect.datatypes.ExerciseRoute.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    //late init location service
    private lateinit var fusedLocationClient: FusedLocationProviderClient



    private lateinit var mainViewModel:WeatherActivityViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //set and create the view model
        Log.e("Loadf", "init model")
//        mainViewModel = WeatherActivityViewModel()
//
//
//        Log.e("Loadf", "init subscribe")
//        subscribe()
//
//
////        //AFTER SUBSCRIBING, GET THE WEATHER DATA FROM LOCAL LOCATION
//        getCurrWeatherDataFromLOC()




    }


    @SuppressLint("MissingPermission") //ignore missing permission, user will always have it
    private fun getCurrWeatherDataFromLOC() {
        //GET CURR USERS LOCATION
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        Log.e("Loadf", "welll.... call it!")

        //get the last known location

            Log.e("Loadf", "welll.... call it!")


            val lat:String = "42.33"
            val long:String =  "-88.37"

            Log.e("Loadf", "long lat $long $lat!")


            //CALL THE VIEW MODEL FOR WEATHER DATA
            mainViewModel.getWeatherDataOfCurrPosition(lat, long)











    }

    private fun subscribe() {

        Log.e("Loadf", "subscribe Functioneer:")



        //observe the loading state
        mainViewModel.isLoadingState.observe(this) { isLoading ->
            //Currently loading the API
            if (isLoading){
                Log.e("Loadf", "Loading results")
//                tvResult.text = resources.getString(R.string.loading)
            }


        }

        //observe the error state
        mainViewModel.errorState.observe(this){ isError ->
            //Encountered ERROR

            if(isError){

                Log.e("Loadf", "Error Loading results")

            }

        }

        mainViewModel.weatherDataPresent.observe(this){ currWeatherData ->

            //display the weather data to the UI
            Log.e("Loadf", "RESULTS LOADED: $currWeatherData")

            Log.e("Loadf", "call curr weather data")



        }




    }





}