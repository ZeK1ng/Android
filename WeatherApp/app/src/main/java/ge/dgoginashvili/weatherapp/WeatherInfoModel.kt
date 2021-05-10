package ge.dgoginashvili.weatherapp

class WeatherInfoModel {
    private var name: String = ""
    private var dt: Long = 0
    private  var main: HashMap<String, String>? = null
    private  var weather: List<HashMap<String, String>>? = null

    fun getWeatherInfo(): HashMap<String, String> {
        return this.weather?.get(0)!!
    }
    fun getMainInfo(): HashMap<String, String> {
        return this.main!!
    }

    fun getDt(): Long {
        return this.dt
    }

    fun getName(): String {
        return this.name
    }

    fun setName(name: String) {
        this.name = name
    }
}

