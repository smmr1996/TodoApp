/**
 * It's a Copyright doc 2023
 */
package todoweb.db

import todoweb.core.Todo
import todoweb.utils.TodoConstants.Companion.NOT_FOUND_TODO_WITH_ID

import javax.ws.rs.NotFoundException

/**
 * Data Access Layer to Read/Write/Delete To-Do tasks
 *
 * @author Syed Mohammad Mehdi
 */
class TodoDao {
    private val map = mutableMapOf<Long, Todo>()

    /**
     * A method to return all the "To-dos". Returns a list of [Todo]
     */
    fun getAll(): List<Todo> {
        return map.values.toList()
    }

    /**
     * A method to add the [Todo]. It persists the object in the DB and returns it.
     */
    fun addTodo(todo: Todo): Todo {
        todo.id = (map.size + 1).toLong()
        map[todo.id] = todo
        return todo
    }

    /**
     * A method to return a [Todo] with [id] else throws [NotFoundException].
     */
    fun getTodoById(id: Long): Todo {
        return map[id] ?: throw NotFoundException(NOT_FOUND_TODO_WITH_ID(id))
    }

    /**
     * A method to update and return a [Todo] with matching [id] else throws [NotFoundException].
     */
    fun updateTodoById(id: Long, todo: Todo): Todo {
        if (map[id] == null) {
            throw NotFoundException(NOT_FOUND_TODO_WITH_ID(id))
        }
        todo.id = id
        map[id] = todo
        return todo
    }

    /**
     * A method to delete a [Todo] with matching [id] else throws [NotFoundException].
     */
    fun deleteToDoById(id: Long) {
        if (map[id] == null) {
            throw NotFoundException(NOT_FOUND_TODO_WITH_ID(id))
        }
        map.remove(id)
    }
}