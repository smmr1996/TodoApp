/**
 * It's a Copyright doc 2023
 */
package todoweb.service

import todoweb.core.domain.Todo

import javax.ws.rs.core.Response

/**
 * Interface to Read Todos
 *
 * This interface defines the methods to get "To-do".
 *
 * @author Syed Mohammad Mehdi
 */
interface TodoReadService {

    /**
     * A method to read return all the "To-dos". Returns a list of [Todo] or Exception/Error messages.
     */
    fun getAll(): List<Todo>

    /**
     * A GET method to return "To-do" with a particular [id]. Returns a [Response] with list
     * of all "To-Dos" or Exception/Error messages.
     */
    fun getTodoById(id: Long): Todo
}