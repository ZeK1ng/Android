package ge.dgoginashvili.todoapplication.main.Utils

import ge.dgoginashvili.todoapplication.main.activity.AddPageActivity
import java.text.SimpleDateFormat
import java.util.*

class Utils {
    companion object {
        fun getTime():String{
            val calendarDate = Calendar.getInstance();
            val date = Calendar.getInstance().time
            val formatter = SimpleDateFormat.getDateTimeInstance() //or use getDateInstance()
            return formatter.format(date);
        }
    }

}