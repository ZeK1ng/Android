package ge.dgoginashvili.weatherapp.dataModel

data class WeatherInfoModel(var name:String,var dt:Long,var main:HashMap<String, String>,var weather: List<HashMap<String, String>>)



