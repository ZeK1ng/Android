package ge.dgoginashvili.todoapplication.main.Utils

import ge.dgoginashvili.todoapplication.data.entity.todoEntity
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
        fun getEntityExtraName():String{
            return "entity"
        }
        fun compareByDate(ent1:todoEntity,ent2:todoEntity):Int{
            val formatter = SimpleDateFormat.getDateTimeInstance() //or use getDateInstance()
            val d1 = formatter.parse(ent1.date)
            val d2 = formatter.parse(ent2.date)
            return d2.compareTo(d1)
        }
    }

}