package ge.dgoginashvili.weatherapp.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ge.dgoginashvili.weatherapp.R
import ge.dgoginashvili.weatherapp.adapter.HourlyDetailsAdapter
import ge.dgoginashvili.weatherapp.api.ApiService
import ge.dgoginashvili.weatherapp.dataModel.HourlyWeatherInfoModel
import ge.dgoginashvili.weatherapp.dataModel.ListItem
import ge.dgoginashvili.weatherapp.utils.Utils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap

class five_days_fragment : Fragment() {
    var city = "Tbilisi"
    var hourlyDetailsData = arrayListOf<Map<String, String>>()
    lateinit var hourlyDitView: RecyclerView
    lateinit var hourlyDitViewAdapter: HourlyDetailsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_five_days, container, false)
        hourlyDitView = view.findViewById(R.id.rv_hourly_dits)
        hourlyDitViewAdapter = HourlyDetailsAdapter(hourlyDetailsData)
        hourlyDitView.adapter = hourlyDitViewAdapter
        hourlyDitView.layoutManager = LinearLayoutManager(context)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addEventListeners()
        doApiCall()
    }

    private fun addEventListeners() {
        view?.findViewById<ImageButton>(R.id.tbilisiButtonHourly)!!.setOnClickListener {
            city = "Tbilisi"
            doApiCall()
        }
        view?.findViewById<ImageButton>(R.id.londonButtonHourly)!!.setOnClickListener {
            city = "London"
            doApiCall()
        }
        view?.findViewById<ImageButton>(R.id.kingstonButtonHourly)!!.setOnClickListener {
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
        val forecast = apiService.get5DayWeather(params)
        forecast.enqueue(object : Callback<HourlyWeatherInfoModel> {
            override fun onFailure(call: Call<HourlyWeatherInfoModel>, t: Throwable) {
                Log.e("Error", "Api failure")
                Toast.makeText(context,"Could not connect to server",Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<HourlyWeatherInfoModel>,
                response: Response<HourlyWeatherInfoModel>
            ) {
                if (response.isSuccessful) {
                    val dailyLayoutContainer =
                        view?.findViewById<LinearLayout>(R.id.secondPageContainer)
                    dailyLayoutContainer!!.setBackgroundResource(R.color.day)
                    view?.findViewById<TextView>(R.id.cityNameTextHourly)!!.text =
                        city.toUpperCase()
                    processResponse(response.body()!!.list, dailyLayoutContainer)
                }
            }
        })
    }

    private fun processResponse(
        resp: List<ListItem>,
        dailyLayoutContainer: LinearLayout
    ) {
        var ind = 0
        hourlyDetailsData.clear()
        for (elem in resp) {
            val dt = elem.dt * 1000
            val mainMap = elem.main
            val weatherMap = elem.weather[0]
            val date = processDt(dt, ind++, dailyLayoutContainer)
            val temp =
                Utils.roundAndFormat(mainMap[getResString(R.string.json_key_temperature)]!!) + getResString(
                    R.string.temp_sign
                )
            val icon = weatherMap["icon"]!!
            val description =
                weatherMap[resources.getString(R.string.json_key_weather_description)]!!
            var iconUrl = "https://openweathermap.org/img/wn/{name}@2x.png"
            iconUrl = iconUrl.replace("{name}", icon, true)
            val paramMap = HashMap<String, String>()
            paramMap["date"] = date
            paramMap["temp"] = temp
            paramMap["desc"] = description
            paramMap["iconUrl"] = iconUrl
            hourlyDetailsData.add(paramMap)
            hourlyDitViewAdapter.notifyDataSetChanged()
        }
    }

    private fun processDt(dt: Long, ind: Int, dailyLayoutContainer: LinearLayout): String {
        val calendarDate = Calendar.getInstance()
        calendarDate.timeInMillis = dt
        val timeType = SimpleDateFormat("a").format(calendarDate.time)
        var timeValue = SimpleDateFormat("h").format(calendarDate.time)
        var timeDay = SimpleDateFormat("d").format(calendarDate.time)
        val timeMonth = Utils.getMonth(SimpleDateFormat("M").format(calendarDate.time).toInt())
        if (timeValue.toInt() < 10) {
            timeValue = "0$timeValue"
        }
        if (timeDay.toInt() < 10) {
            timeDay = "0$timeDay"
        }
        if (ind == 0) {
            if ((timeType == "AM" && Integer.parseInt(timeValue) <= 6)
                || (timeType == "PM" && Integer.parseInt(timeValue) >= 6)
            ) {
                dailyLayoutContainer.setBackgroundResource(R.color.night)
            }
        }
        return "$timeValue $timeType $timeDay $timeMonth"
    }


    private fun getResString(key: Int): String {
        return resources.getString(key)
    }

}
