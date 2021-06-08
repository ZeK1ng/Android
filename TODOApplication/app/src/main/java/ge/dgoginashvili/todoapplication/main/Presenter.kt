package ge.dgoginashvili.todoapplication.main

import android.util.Log
import ge.dgoginashvili.todoapplication.data.dao.todoItemDao
import ge.dgoginashvili.todoapplication.data.entity.todoEntity

class Presenter(var view:MainViewInterface?): PresenterInterface{

    private val interactor = Interactor(this)


    fun testDB() {
        interactor.purgeDB()
        interactor.testDB()
        var ls =  interactor.getAllItems()
        Log.d("LIST",ls.toString())
    }

    override fun dataFetched(todoEntities: List<todoEntity>) {
        view?.showText(todoEntities.toString())
    }

    fun detachView() {
        view = null
    }
    private fun hasPinned(todos:List<todoEntity>): Boolean{
        todos.forEach{
            if(it.pinned){
                return true
            }
        }
        return false
    }
    private fun getPinnedItems(todos:List<todoEntity>): List<todoEntity>? {
        if(!hasPinned(todos)){
            return null
        }
        val pinnedList = ArrayList<todoEntity>()
        todos.forEach{
            if(it.pinned){
                pinnedList.add(it)
            }
        }
        return pinnedList
    }
    
}