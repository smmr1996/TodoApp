/**
 * It's a Copyright doc 2023
 */
package todoweb.service

import todoweb.utils.TodoAppException

import javax.ws.rs.PathParam

/**
 * Service Interface to Delete Todos
 *
 * This interface defines the methods to delete "To-dos".
 *
 * @author Syed Mohammad Mehdi
 */
interface TodoDeleteService {

    /**
     * A Delete method to delete the "To-do" with a particular [id]. Throws [TodoAppException]
     * if the Todo with the [id] is not found.
     */
    fun deleteTodoById(@PathParam("id") id: Long)
}