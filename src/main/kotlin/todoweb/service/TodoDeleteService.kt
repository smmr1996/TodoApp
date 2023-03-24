/**
 * It's a Copyright doc 2023
 */
package todoweb.service

import javax.ws.rs.PathParam
import javax.ws.rs.core.Response

/**
 * Interface to Delete Todos
 *
 * This interface defines the methods to get "To-dos" from the Data Source.
 *
 * @author Syed Mohammad Mehdi
 */
interface TodoDeleteService {

    /**
     * A Delete method to delete the "To-do" with a particular [id]. Returns a [Response] with
     * Success or Failure message.
     */
    fun deleteTodoById(@PathParam("id") id: Long)
}