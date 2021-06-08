package ge.dgoginashvili.todoapplication

import android.app.Application
import ge.dgoginashvili.todoapplication.data.TDDatabase


class ToDoApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        TDDatabase.createDatabase(this)
    }
}