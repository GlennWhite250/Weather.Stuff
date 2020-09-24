package com.example.weatherstuff.data

import com.example.weatherstuff.data.resposne.CurrentWeatherResposne
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

//A global variable for the api key we are using
const val API_KEY = "980464932132ec90507fbf9176533320"

/**
 * The path for the qurery of the current weather
 * https://api.weatherstack.com/current?access_key=980464932132ec90507fbf9176533320&query=Atlanta&unit=f&lang=en
 */
interface WeatherStackApiService {
    @GET("current")
    fun getCurrentWeather(
        @Query("query") location: String,
        @Query("unit") unit: String,
        @Query("lang") languageCode: String = "en"
    ) : Deferred<CurrentWeatherResposne>

    companion object{
        operator fun invoke(): WeatherStackApiService{
            val requestInterceptor = Interceptor{ chain ->
                val  url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("access_key", API_KEY)
                    .build()

                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()

                return@Interceptor chain.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://api.weatherstack.com/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WeatherStackApiService::class.java)
        }
    }
}