package ge.dgoginashvili.todoapplication.main.Interfaces

import ge.dgoginashvili.todoapplication.data.entity.todoEntity

interface MainViewInterface {
    abstract fun showData(tde: List<todoEntity>)
}