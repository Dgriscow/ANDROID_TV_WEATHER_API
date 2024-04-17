package com.example.android_weather_api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android_weather_api.networkServices.ApiConfig
import com.example.android_weather_api.weatherDataRes.WeatherData
import com.google.android.gms.location.FusedLocationProviderClient
import retrofit2.Call
import retrofit2.Response
import java.lang.StringBuilder
import retrofit2.Callback


class WeatherActivityViewModel:ViewModel() {


    private val changeableWeatherDataToPresent = MutableLiveData<WeatherData>()
    val weatherDataPresent: LiveData<WeatherData> get() = changeableWeatherDataToPresent

    private val changeableIsLoadingState = MutableLiveData<Boolean>()
    val isLoadingState:LiveData<Boolean> get() = changeableIsLoadingState

    private val changeableErrorState = MutableLiveData<Boolean>()
    val errorState:LiveData<Boolean> get() = changeableErrorState

    private var errorMessage:String = ""





    //function will have to get location service data in a enviornment with a activity (Fragment, main act)
    fun getWeatherDataOfCurrPosition(lat:String, long:String ){

        changeableIsLoadingState.value = true //set the value of the changeable loading state to true,
        changeableErrorState.value = false //set the value of the changeable error state to false, no errors have occured



        //instantiate a CLIENT
        //the client is from the API config class, which calls for  the getCurrWeather function, get uses long and latitude data to return a
        //weather data object
        val currWeatherClient = ApiConfig.getAPIWeatherService().getCurrentWeather(lat, long)

        //fetch the API request through retrofit
        currWeatherClient.enqueue(object : Callback<WeatherData> {


            override fun onResponse(call: Call<WeatherData>, response: Response<WeatherData>){
                //create a response body to track the response of theAPI request
                val responseBody = response.body()

                //Catch Error from the responses body
                if (!response.isSuccessful || responseBody == null) {
                    onError("Data Processing Error")
                    return
                }


                //set the loading states and body
                changeableIsLoadingState.value = false
                changeableWeatherDataToPresent.postValue(responseBody)



            }

            override fun onFailure(call: Call<WeatherData>, t: Throwable){
                onError(t.message) //the throrn error message
                t.printStackTrace()
            }




        })





    }
    //a On error response function to display error message states
    private fun onError(inputMessage: String?) {

        //Double check the input message entered is actually valid, when it is use the normal input message
        val message = if (inputMessage.isNullOrBlank() or inputMessage.isNullOrEmpty()) "Unknown Error"
        else inputMessage

        //build the error message to be displayed and printed
        errorMessage = StringBuilder("Error").append("$message Some Data may not display properlu").toString()

        changeableErrorState.value = true

        changeableIsLoadingState.value = false

    }


}