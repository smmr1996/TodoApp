/**
 * It's a Copyright doc 2023
 */
package todoweb.core

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Data Class to hold the properties of a Task.
 *
 * @author Syed Mohammad Mehdi
 */
data class Task(
    @field:JsonProperty("name") private val name: String,
    @field:JsonProperty("description") private val description: String?
)
