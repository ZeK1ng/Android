package ge.dgoginashvili.todoapplication.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


@Entity
data class todoEntity(
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "tasks") val tasks: ArrayList<String>,
    @ColumnInfo(name = "done") val done: ArrayList<String>,
    @ColumnInfo val pinned: Boolean
){
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0
}
class ListClassConverter {
    @TypeConverter
    fun fromString(value: String): ArrayList<String>{
        val type = object : TypeToken<ArrayList<String>>(){}.type
        return Gson().fromJson(value,type)
    }
    @TypeConverter
    fun fromList(list:ArrayList<String>):String {
        return Gson().toJson(list)
    }
}

