package ge.dgoginashvili.todoapplication.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ge.dgoginashvili.todoapplication.data.dao.todoItemDao
import ge.dgoginashvili.todoapplication.data.entity.ListClassConverter
import ge.dgoginashvili.todoapplication.data.entity.todoEntity


@Database(entities = [todoEntity::class], version = 1)
@TypeConverters(ListClassConverter::class)
abstract class TDDatabase() : RoomDatabase() {
    abstract fun todoItemDao(): todoItemDao

    companion object {
        private val dbName = "todoItem-db"
        private lateinit var INSTANTCE: TDDatabase

        fun getInstance(): TDDatabase {
            return INSTANTCE
        }

        fun createDatabase(context: Context) {
            INSTANTCE = Room.databaseBuilder(context, TDDatabase::class.java, dbName).build()
        }
    }
}