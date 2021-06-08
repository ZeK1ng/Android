package ge.dgoginashvili.todoapplication.main

import ge.dgoginashvili.todoapplication.data.entity.todoEntity

interface PresenterInterface {
    abstract fun dataFetched(todoEntities: List<todoEntity>)
}
