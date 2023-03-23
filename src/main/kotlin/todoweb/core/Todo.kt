/**
 * It's a Copyright doc 2023
 */
package todoweb.core

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Data Class to hold the properties of To-Do.
 *
 * @author Syed Mohammad Mehdi
 */
data class Todo(
    @field:JsonProperty("id") var id: Long,
    @field:JsonProperty("name") private val name: String,
    @field:JsonProperty("description") private val description: String?,
    @field:JsonProperty("tasks") private val tasks: List<Task>?
)
