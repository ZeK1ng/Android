package ge.dgoginashvili.weatherapp
import retrofit2.Call
import retrofit2.http.*


interface ApiService {
    @GET("/data/2.5/weather")
    fun getWeather(@QueryMap params:Map<String, String>): Call<WeatherInfoModel>
}