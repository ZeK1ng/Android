package ge.dgoginashvili.weatherapp.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ge.dgoginashvili.weatherapp.R
import ge.dgoginashvili.weatherapp.adapter.DetailsAdapter
import ge.dgoginashvili.weatherapp.api.ApiService
import ge.dgoginashvili.weatherapp.dataModel.WeatherInfoModel
import ge.dgoginashvili.weatherapp.utils.Utils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap


class daily_page_fragment : Fragment() {
    var city = "Tbilisi"
    lateinit var iconImage: ImageView
    lateinit var temperTextV: TextView
    lateinit var descripTextV: TextView
    var temperature = "0"
    var weather_description = ""
    var feelsLike = ""
    var humidity = ""
    var pressure = ""
    var iconUrl = "https://openweathermap.org/img/wn/{name}@2x.png"
    var detailsData = arrayListOf<Pair<String, String>>()
    lateinit var ditRecView: RecyclerView
    lateinit var adapter: DetailsAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_daily_page, container, false)
        ditRecView = view.findViewById(R.id.rv_details)
        adapter = DetailsAdapter(detailsData)
        ditRecView.adapter = adapter
        ditRecView.layoutManager = LinearLayoutManager(context)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addEventListeners()
        doApiCall()
    }

    private fun addEventListeners() {
        view?.findViewById<ImageButton>(R.id.tbilisiButton)!!.setOnClickListener {
            city = "Tbilisi"
            doApiCall()
        }
        view?.findViewById<ImageButton>(R.id.londonButton)!!.setOnClickListener {
            city = "London"
            doApiCall()
        }
        view?.findViewById<ImageButton>(R.id.kingstonButton)!!.setOnClickListener {
            city = "Kingston"
            doApiCall()
        }
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
        val getWeather = apiService.getWeather(params)

        getWeather.enqueue(object : Callback<WeatherInfoModel> {
            override fun onFailure(call: Call<WeatherInfoModel>, t: Throwable) {
                Log.e("Error", "Api failure")
            }

            override fun onResponse(
                call: Call<WeatherInfoModel>,
                response: Response<WeatherInfoModel>
            ) {
                val dailyLayoutContainer = view?.findViewById<LinearLayout>(R.id.firstPageContainer)
                dailyLayoutContainer!!.setBackgroundResource(R.color.day)
                view?.findViewById<TextView>(R.id.cityNameText)!!.text = city.toUpperCase()

                if (response.isSuccessful) {
                    response.body()!!.name.let { Log.d("data", it) }
                    handleTime(dailyLayoutContainer, response)
                    getDetails(response)
                    getIcon(response)

                    temperTextV = view?.findViewById(R.id.temperatureText)!!
                    temperTextV.text = temperature
                    descripTextV = view?.findViewById(R.id.descripText)!!
                    descripTextV.text = weather_description.toUpperCase()
                    addDetails(response)

                }
            }

        })
    }

    private fun addDetails(response: Response<WeatherInfoModel>) {
        detailsData.clear()
        detailsData.add(Pair(getResString(R.string.Temperature), temperature))
        detailsData.add(Pair(getResString(R.string.FeelsLike), feelsLike))
        detailsData.add(Pair(getResString(R.string.Humidity), humidity))
        detailsData.add(Pair(getResString(R.string.Pressure), pressure))
        adapter.notifyDataSetChanged()
    }

    private fun getIcon(response: Response<WeatherInfoModel>) {
        val weathermp = response.body()!!.weather[0]

        val icon = weathermp["icon"]!!

        iconImage = view?.findViewById(R.id.dailyWeatherImage)!!
        iconUrl = "https://openweathermap.org/img/wn/{name}@2x.png"
        iconUrl = iconUrl.replace("{name}", icon, true)
        weather_description =
            weathermp[resources.getString(R.string.json_key_weather_description)]!!

        Glide.with(activity!!)
            .load(iconUrl)
            .into(iconImage)
    }

    private fun getDetails(response: Response<WeatherInfoModel>) {
        val mainmp = response.body()!!.main
        temperature =
            Utils.roundAndFormat(mainmp[getResString(R.string.json_key_temperature)]!!) + getResString(
                R.string.temp_sign
            )
        feelsLike =
            Utils.roundAndFormat(mainmp[getResString(R.string.json_key_feels_like)]!!) + getResString(
                R.string.temp_sign
            )
        humidity = mainmp[getResString(R.string.json_key_humidity)] + "%"
        pressure = mainmp[getResString(R.string.json_key_pressure)]!!

    }

    private fun handleTime(
        dailyLayoutContainer: LinearLayout,
        response: Response<WeatherInfoModel>
    ) {
        val dt = response.body()!!.dt * 1000
        val calendarDate = Calendar.getInstance()
        calendarDate.timeInMillis = dt
        val timeType = SimpleDateFormat("a").format(calendarDate.time)
        val timeValue = SimpleDateFormat("h").format(calendarDate.time)
        if ((timeType == "AM" && Integer.parseInt(timeValue) <= 6)
            || (timeType == "PM" && Integer.parseInt(timeValue) >= 18)
        ) {
            dailyLayoutContainer.setBackgroundResource(R.color.night)
        }

    }

    private fun getResString(key: Int): String {
        return resources.getString(key)
    }


}