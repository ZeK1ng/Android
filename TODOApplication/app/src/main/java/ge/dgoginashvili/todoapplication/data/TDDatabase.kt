package ge.dgoginashvili.todoapplication.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import ge.dgoginashvili.todoapplication.data.dao.todoItemDao
import ge.dgoginashvili.todoapplication.data.entity.ListClassConverter
import ge.dgoginashvili.todoapplication.data.entity.todoEntity


@Database(entities = [todoEntity::class], version = 2)
@TypeConverters(ListClassConverter::class)
abstract class TDDatabase() : RoomDatabase() {
    abstract fun todoItemDao(): todoItemDao

    companion object {
        private val dbName = "todoItem-db"
        private lateinit var INSTANTCE: TDDatabase


//  Had to change db in middle of progress
//        val MIGRATION_1_2 = object : Migration(1, 2) {
//            override fun migrate(database: SupportSQLiteDatabase) {
//                database.execSQL("DROP TABLE todoEntity")
//            }
//            override fun migrate(database: SupportSQLiteDatabase) {
//                database.execSQL("ALTER TABLE todoEntity ADD COLUMN date TEXT")
//            }
//        }

        fun getInstance(): TDDatabase {
            return INSTANTCE
        }

        fun createDatabase(context: Context) {
//            INSTANTCE = Room.databaseBuilder(context, TDDatabase::class.java, dbName).fallbackToDestructiveMigration().build()
            INSTANTCE = Room.databaseBuilder(context, TDDatabase::class.java, dbName).build()
        }
    }

}
