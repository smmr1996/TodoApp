/**
 * It's a Copyright doc 2023
 */
package todoweb.core.builder

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import todoweb.core.domain.Todo

/**
 * This is a builder class which builds the [Todo] object.
 *
 * @author Syed Mohammad Mehdi
 */
class TodoBuilder {

    companion object {

        /**
         * Mapper Method that takes [json] string. Creates and returns the [Todo] object based
         * on the input.
         */
        fun buildTodoFromJson(json: String): Todo {
            val mapper = jacksonObjectMapper()
            return  mapper.readValue(json, Todo::class.java)
        }
    }
}