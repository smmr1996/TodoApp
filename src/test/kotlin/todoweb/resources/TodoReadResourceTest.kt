/**
 * It's a Copyright doc
 */
package todoweb.resources

import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

import todoweb.core.domain.Task
import todoweb.core.domain.Todo
import todoweb.resources.impl.TodoReadResourceImpl
import todoweb.service.TodoReadService
import todoweb.utils.TodoAppException

import javax.ws.rs.core.Response

/**
 * Test class for [TodoReadResourceImpl].
 *
 * @author Syed Mohammad Mehdi
 */
class TodoReadResourceTest {

    private val todoReadService = mockk<TodoReadService>()
    private val todoReadResource = TodoReadResourceImpl(todoReadService)

    @BeforeEach
    fun setUp() {
        clearAllMocks()
    }

    @Nested
    inner class GetAllTest {

        @Test
        fun `test getAll todos`() {
            every { todoReadService.getAll() } returns listOf(todo)
            val response = todoReadResource.getAll()
            assertEquals(listOf(todo), response.entity)
        }

        @Test
        fun `test getAll todos throws exception`() {
            every { todoReadService.getAll() } throws TodoAppException(NOT_FOUND_TODO_WITH_ID(id))
            val response = todoReadResource.getAll()
            assertEquals(Response.Status.INTERNAL_SERVER_ERROR.statusCode, response.status)
        }
    }


    @Nested
    inner class GetTodoById {

        @Test
        fun `test getTodoById when valid id is passed`() {
            every { todoReadService.getTodoById(id) } returns todo
            val response = todoReadResource.getTodoById(id)
            assertEquals(todo, response.entity)
        }

        @Test
        fun `test getTodoById with invalid id`() {
            every { todoReadService.getTodoById(id) } throws TodoAppException(NOT_FOUND_TODO_WITH_ID(id))
            val response = todoReadResource.getTodoById(id)
            assertEquals(NOT_FOUND_TODO_WITH_ID(id), response.entity)
        }
    }

    companion object {
        private const val id = 2L
        private const val TEST_NAME = "name"
        private const val TEST_DESCRIPTION = "description"
        val NOT_FOUND_TODO_WITH_ID = {id: Long -> "Could not find ToDo with id: $id"}
        val todo = Todo(
            id,
            TEST_NAME,
            TEST_DESCRIPTION, listOf(Task(TEST_NAME, TEST_DESCRIPTION)))
    }
}