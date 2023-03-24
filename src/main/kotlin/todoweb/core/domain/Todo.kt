/**
 * It's a Copyright doc 2023
 */
package todoweb.core.domain

import com.fasterxml.jackson.annotation.JsonProperty
import todoweb.core.domain.Task

/**
 * Data Class to hold the properties of To-Do.
 *
 * @author Syed Mohammad Mehdi
 */
data class Todo(
    @field:JsonProperty("id") var id: Long = 0,
    @field:JsonProperty("name") val name: String,
    @field:JsonProperty("description") val description: String?,
    @field:JsonProperty("tasks") var tasks: List<Task>?
)
