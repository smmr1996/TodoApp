/**
 * It's a Copyright doc
 */
package todoweb.resources

import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import todoweb.db.TodoDao
import todoweb.resources.impl.TodoDeleteResourceImpl
import todoweb.service.TodoDeleteService

import javax.ws.rs.NotFoundException
import javax.ws.rs.core.Response.Status

/**
 * Test class for [TodoDeleteResource].
 *
 * @author Syed Mohammad Mehdi
 */
class TodoDeleteResourceTest {

    private val todoDeleteService = mockk<TodoDeleteService>()
    private val todoDeleteResource = TodoDeleteResourceImpl(todoDeleteService)

    @BeforeEach
    fun setUp() {
        clearAllMocks()
    }

    @Test
    fun `test deleteTodoById when valid id is passed`() {
        val id = id
        every { todoDeleteService.deleteTodoById(id) } just runs
        val response = todoDeleteResource.deleteTodoById(id)
        assertEquals(Status.OK.statusCode, response.status)
    }

    @Test
    fun `test deleteTodoById when invalid id is passed`() {
        val id = id
        every { todoDeleteService.deleteTodoById(id) } throws NotFoundException((NOT_FOUND_TODO_WITH_ID(id)))
        val response = todoDeleteResource.deleteTodoById(id)
        assertEquals(Status.NOT_FOUND.statusCode, response.status)
    }

    companion object {
        const val id = 1L
        val NOT_FOUND_TODO_WITH_ID = {id: Long -> "Could not find ToDo with id: $id"}
    }
}