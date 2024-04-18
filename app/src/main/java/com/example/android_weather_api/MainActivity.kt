package com.example.android_weather_api

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.health.connect.datatypes.ExerciseRoute.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.app.ActivityCompat
import com.example.android_weather_api.weatherDataRes.WeatherData
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.squareup.picasso.Picasso
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
        mainViewModel = WeatherActivityViewModel()


        Log.e("Loadf", "init subscribe")
        subscribe()


//        //AFTER SUBSCRIBING, GET THE WEATHER DATA FROM LOCAL LOCATION
        getCurrWeatherDataFromLOC()




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
            setOnScreenData(currWeatherData)



        }




    }


    //Set View Texts and Images Onto the Screen
    private fun setOnScreenData(dataToSet:WeatherData){

        //set the variables for the titles
        val conditionText = findViewById<TextView>(R.id.weatherConditionTitle)
        val visibilityText = findViewById<TextView>(R.id.visibilityValueTxt)
        val realFeelText = findViewById<TextView>(R.id.realFeeltempValueTxt)
        val temperatureText = findViewById<TextView>(R.id.tempValueTxt)
        val windSpeedText = findViewById<TextView>(R.id.windSpeedltempValueTxt)
        val windDegText = findViewById<TextView>(R.id.windDegltempValueTxt)
        //Declare the image user later when setting with PICASSO


        //SET THE CONDITION VALUES FROM THE DATA
        conditionText.text = dataToSet.weather?.get(0)?.main.toString() //setting condition
        //set visibility
        visibilityText.text = dataToSet.visibility.toString()
        //real feel text setting
        realFeelText.text = dataToSet.main?.feelsLike.toString()
        //Setting Temperature
        temperatureText.text = dataToSet.main?.temp.toString()
        //Wind speed text
        windSpeedText.text = dataToSet.wind?.speed.toString()
        //set wind degrees
        windDegText.text = dataToSet.wind?.deg.toString()

        //Declare the user for the IMAGE
        val conditionImage = findViewById<AppCompatImageView>(R.id.ConditionIcon)

        //seperate the IMAGE URL
        val imageID = dataToSet.weather?.get(0)?.icon.toString()

        //NOW CREATE THE URL FROM THE ID
        val imageURL = "https://openweathermap.org/img/wn/$imageID@2x.png"
        //this uses the openweather APIS image URL to load in their own image ICONS

//        //now use PICASSO to populate the image
        Picasso.with(this)
            .load(imageURL)
            .resize(200, 200)

            .centerCrop()
            .into(conditionImage)









    }





}