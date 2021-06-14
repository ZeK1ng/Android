package ge.dgoginashvili.todoapplication.main.Interfaces

import ge.dgoginashvili.todoapplication.data.entity.todoEntity

interface PresenterInterface {
    abstract fun dataFetched(todoEntities: List<todoEntity>)
}
