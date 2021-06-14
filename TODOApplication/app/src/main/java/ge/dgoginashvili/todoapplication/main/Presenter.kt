package ge.dgoginashvili.todoapplication.main

import android.util.Log
import ge.dgoginashvili.todoapplication.data.entity.todoEntity
import ge.dgoginashvili.todoapplication.main.Interfaces.AddViewInterface
import ge.dgoginashvili.todoapplication.main.Interfaces.MainViewInterface
import ge.dgoginashvili.todoapplication.main.Interfaces.PresenterInterface

class Presenter(var mainView: MainViewInterface?,var addView:AddViewInterface?):
    PresenterInterface {

    private val interactor = Interactor(this)


    fun getDBData() {
//        interactor.testDB()
        var ls =  interactor.getAllItems()
        Log.d("LIST",ls.toString())
    }
    fun saveEntity(td:todoEntity){
        interactor.addItem(td)
    }
    fun updateEntity(td:todoEntity){
        interactor.updateEntity(td)
    }
    override fun dataFetched(todoEntities: List<todoEntity>) {
        mainView?.showData(todoEntities)
    }
    fun purgeDB(){
        interactor.purgeDB()
    }

    fun detachView() {
        mainView = null
        addView = null
    }
    fun hasPinned(todos:List<todoEntity>): Boolean{
        todos.forEach{
            if(it.pinned){
                return true
            }
        }
        return false
    }
     fun getPinnedItems(todos:List<todoEntity>): List<todoEntity>? {
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
    fun getNonPinned(todos:List<todoEntity>): List<todoEntity>? {
        if(!hasPinned(todos)){
            return null
        }
        val nonPinned = ArrayList<todoEntity>()
        todos.forEach{
            if(!it.pinned){
                nonPinned.add(it)
            }
        }
        return nonPinned
    }
    
}