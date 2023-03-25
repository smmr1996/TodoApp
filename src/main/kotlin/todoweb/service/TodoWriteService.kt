/**
 * It's a Copyright doc 2023
 */
package todoweb.service

import todoweb.core.domain.Todo
import todoweb.utils.TodoAppException

/**
 * Interface to Write Todos
 *
 * This interface declares the service methods to write "To-dos".
 *
 * @author Syed Mohammad Mehdi
 */
interface TodoWriteService {

    /**
     * A method to add the [todo]. Returns the persisted [Todo] with id.
     */
    fun addTodo(todo: Todo): Todo

    /**
     * A method to update the [todo] with a particular [id]. Throws [TodoAppException] with
     * relevant errors.
     */
    fun updateTodoById(id: Long, todo: Todo)
}