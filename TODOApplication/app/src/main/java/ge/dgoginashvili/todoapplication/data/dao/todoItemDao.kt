package ge.dgoginashvili.todoapplication.data.dao

import androidx.room.*
import ge.dgoginashvili.todoapplication.data.entity.todoEntity
import retrofit2.http.DELETE

@Dao
interface todoItemDao {
    @Query("Select * from todoEntity")
    fun getAllToDoItems(): List<todoEntity>

    @Insert
    fun addTodoItem(item: todoEntity)

    @Update
    fun updateTodoItem(item: todoEntity)

    @Query("DELETE FROM todoEntity")
    fun nukeDBase();

    @Delete
    fun deleteItem(item:todoEntity)
}