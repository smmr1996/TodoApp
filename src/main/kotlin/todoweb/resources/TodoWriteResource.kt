/**
 * It's a Copyright doc 2023
 */
package todoweb.resources

import javax.ws.rs.core.Response

/**
 * Interface to Write Todos
 *
 * This interface defines the methods to write/update "To-do" into the Data Source.
 *
 * @author Syed Mohammad Mehdi
 */
interface TodoWriteResource {

    /**
     * A PUT method which takes a [todoJson] as an input to update the already existing "To-do"
     * having particular [id]. Returns a [Response] with the updated "To-do" or
     * exception/failure caused.
     */
    fun updateTodoById(id: Long, todoJson: String): Response

    /**
     * A POST method which takes a [todoJson] as an input and persists it in the DB.
     * Returns a [Response] with the created "to-do"
     */
    fun addTodo(todoJson: String): Response

}