package ge.dgoginashvili.weatherapp.api
import ge.dgoginashvili.weatherapp.dataModel.WeatherInfoModel
import ge.dgoginashvili.weatherapp.dataModel.HourlyWeatherInfoModel
import retrofit2.Call
import retrofit2.http.*


interface ApiService {
    @GET("/data/2.5/weather")
    fun getWeather(@QueryMap params:Map<String, String>): Call<WeatherInfoModel>

    @GET("/data/2.5/forecast")
    fun get5DayWeather(@QueryMap params:Map<String, String>): Call<HourlyWeatherInfoModel>
}