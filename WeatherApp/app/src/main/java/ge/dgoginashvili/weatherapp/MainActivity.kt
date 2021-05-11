package ge.dgoginashvili.weatherapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap

class MainActivity : AppCompatActivity() {
    var city = "Kingston"
    lateinit var iconImage: ImageView
    var temperature = "0"
    var weather_description = ""
    var iconUrl = "https://openweathermap.org/img/wn/{name}@2x.png"
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
        params["q"] = city
        params["appid"] = resources.getString(R.string.weather_api_client_key)
        params["units"] = resources.getString(R.string.weather_api_units)
        Log.i("params", params.toString())
        val getWeather = apiService.getWeather(params)

        getWeather.enqueue(object : Callback<WeatherInfoModel> {
            override fun onFailure(call: Call<WeatherInfoModel>, t: Throwable) {
                Log.e("Error","Api failure")
            }

            override fun onResponse(call: Call<WeatherInfoModel>, response: Response<WeatherInfoModel>) {
                val dailyLayoutContainer = findViewById<LinearLayout>(R.id.firstPageContainer)
                dailyLayoutContainer.setBackgroundResource(R.color.day)
                findViewById<TextView>(R.id.cityNameText).text = city.toUpperCase()

                if(response.isSuccessful){
                    response.body()?.getName()?.let { Log.d("data", it) }
                    val dt = response.body()!!.getDt() * 1000
                    val calendarDate = Calendar.getInstance()
                    calendarDate.timeInMillis = dt
                    val timeType = SimpleDateFormat("a").format(calendarDate.time)
                    val timeValue = SimpleDateFormat("h").format(calendarDate.time)
                    Log.d("DT",dt.toString())
                    Log.d("timeType",timeType)
                    Log.d( "timeValue",timeValue)
                    if((timeType.equals("AM") && Integer.parseInt(timeValue) <= 6) ||(timeType.equals("PM") && Integer.parseInt(timeValue) >= 6) ){
                        dailyLayoutContainer.setBackgroundResource(R.color.night)
                    }


                    val mainmp = response.body()!!.getMainInfo()
                    Log.d("MainBody",mainmp.toString())

                    val df = DecimalFormat("#")
                    df.roundingMode = RoundingMode.CEILING


                    temperature = df.format(mainmp["temp"]!!.toDouble()).toInt().toString() +  resources.getString(R.string.temp_sign)
                    val weathermp = response.body()!!.getWeatherInfo()
                    Log.d("Weather",weathermp.toString())

                    val icon = weathermp["icon"]!!
                    if (icon != null) {
                        Log.d("Icon", icon)
                    }
                    iconImage = findViewById(R.id.dailyWeatherImage)
                    iconUrl = iconUrl.replace("{name}",icon, true)
                    Log.d("IconUrl",iconUrl)
                    weather_description = weathermp[resources.getString(R.string.json_key_weather_description)]!!


                    Log.d("temp",temperature)
                    Log.d("descr",weather_description)
                    Glide.with(this@MainActivity)
                        .load(iconUrl)
                        .into(iconImage)
                }
            }

        })
    }
}
