/**
 * It's a Copyright doc 2023
 */
package todoweb.resources

import javax.ws.rs.core.Response

/**
 * Interface to Read Todos
 *
 * This interface defines the methods to get "To-do" from the Data Source.
 *
 * @author Syed Mohammad Mehdi
 */
interface TodoReadResource {

    /**
     * A GET method to return all the "To-dos". Returns a [Response] with list of all "To-Dos"
     * or Exception/Error messages.
     */
    fun getAll(): Response

    /**
     * A GET method to return "To-do" with a particular [id]. Returns a [Response] with the
     * "To-Do" or Exception/Error messages.
     */
    fun getTodoById(id: Long): Response
}