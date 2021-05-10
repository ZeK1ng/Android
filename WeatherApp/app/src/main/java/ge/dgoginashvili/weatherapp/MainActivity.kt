package ge.dgoginashvili.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        doApiCall()
    }

    private fun doApiCall() {
        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(resources.getString(R.string.base_weather_api_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val apiService = retrofit.create(ApiService::class.java)
        val params = HashMap<String, String>()
        params["q"] = "London"
        params["appid"] = resources.getString(R.string.weather_api_client_key)
        params["units"] = resources.getString(R.string.weather_api_units)
        Log.i("params", params.toString())
        val getWeather = apiService.getWeather(params)

        getWeather.enqueue(object : Callback<WeatherInfoModel> {
            override fun onFailure(call: Call<WeatherInfoModel>, t: Throwable) {
                Log.e("Error","Api failure")
            }

            override fun onResponse(call: Call<WeatherInfoModel>, response: Response<WeatherInfoModel>) {
                if(response.isSuccessful){
                    response.body()?.getName()?.let { Log.d("data", it) }
                    val dt = response.body()!!.getDt() * 1000
                    val calendarDate = Calendar.getInstance()
                    calendarDate.timeInMillis = dt
                    Log.d("DT",dt.toString())
                    Log.d("time",SimpleDateFormat("a").format(calendarDate.time))
                    Log.d("time",SimpleDateFormat("h").format(calendarDate.time))
                    val currentDate = Date(dt)
                    val dateFormat = SimpleDateFormat("YYYY-MM-dd HH:mm:ss")
                    val date = dateFormat.format(currentDate)
                    Log.d("time",date)

                    val mainmp = response.body()!!.getMainInfo()
                    Log.d("time",mainmp.toString())

                    val weathermp = response.body()!!.getWeatherInfo()
                    Log.d("time",weathermp.toString())
                    val temp = "5"+ resources.getString(R.string.temp_sign)
                    Log.d("temp",temp)

                }
            }

        })
    }
}