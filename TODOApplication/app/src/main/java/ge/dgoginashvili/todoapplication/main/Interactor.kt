package ge.dgoginashvili.todoapplication.main

import android.os.AsyncTask
import android.util.Log
import ge.dgoginashvili.todoapplication.data.TDDatabase
import ge.dgoginashvili.todoapplication.data.entity.todoEntity
import ge.dgoginashvili.todoapplication.main.Interfaces.PresenterInterface
import ge.dgoginashvili.todoapplication.main.Utils.Utils

class Interactor(val presenter: Presenter) {
    fun testDB() {
        GetAllItems().execute()
    }

    fun purgeDB() {
        NukeDB().execute()
    }

    fun getAllItems() {
        GetAllItemsHelper(presenter).execute()
    }
    fun addItem(item:todoEntity){
        AddItem(item).execute()
    }

    class AddItem(private val item:todoEntity):AsyncTask<Void, Void,  Void>() {
        override fun doInBackground(vararg params: Void?):Void?{
            val dao = TDDatabase.getInstance().todoItemDao()
            dao.addTodoItem(item)
            return null
        }
    }

    class GetAllItemsHelper(private val presenter: PresenterInterface) : AsyncTask<Void, Void,  List<todoEntity>>() {
        override fun doInBackground(vararg params: Void?): List<todoEntity> {
            val dao = TDDatabase.getInstance().todoItemDao()
            val ls = dao.getAllToDoItems()
            if(ls.isNotEmpty()){
                Log.d("Items",ls[0].toString()+ls[0].id)
            }
            return ls
        }

        override fun onPostExecute(result: List<todoEntity>?) {
            super.onPostExecute(result)
            if(result != null) {
                presenter.dataFetched(result)
            }
        }
    }

    class NukeDB() : AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg params: Void?): Void? {
            val dao = TDDatabase.getInstance().todoItemDao()
            dao.nukeDBase()
            return null
        }
    }

    class GetAllItems() : AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg params: Void?): Void? {
            val dao = TDDatabase.getInstance().todoItemDao()
            val tasks = ArrayList<String>()
            tasks.add("123")
            tasks.add("chess")
            tasks.add("watching")
            val done = ArrayList<String>()
            tasks.add("dd1")
            tasks.add("dd2")
            tasks.add("dd3")

            val newItemEntity = todoEntity("Test", tasks, done, false,Utils.getTime())
            dao.addTodoItem(newItemEntity)
            Log.d("Items", dao.getAllToDoItems().toString())
            return null
        }
    }
}