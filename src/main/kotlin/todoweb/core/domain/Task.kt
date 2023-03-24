/**
 * It's a Copyright doc 2023
 */
package todoweb.core.domain

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Data Class to hold the properties of a Task.
 *
 * @author Syed Mohammad Mehdi
 */
data class Task(
    @field:JsonProperty("id") var id: Long = 0,
    @field:JsonProperty("name") val name: String,
    @field:JsonProperty("description") val description: String?
)
