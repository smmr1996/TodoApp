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
import todoweb.core.Task
import todoweb.core.Todo

import todoweb.db.TodoDao
import todoweb.resources.impl.TodoReadResourceImpl
import javax.ws.rs.NotFoundException
import javax.ws.rs.core.Response

/**
 * Test class for [TodoReadResourceImpl].
 *
 * @author Syed Mohammad Mehdi
 */
class TodoReadResourceTest {

    private val todoDao = mockk<TodoDao>()
    private val todoReadResource = TodoReadResourceImpl(todoDao)

    @BeforeEach
    fun setUp() {
        clearAllMocks()
    }

    @Nested
    inner class GetAllTest {

        @Test
        fun `test getAll todos`() {
            every { todoDao.getAll() } returns listOf(todo)
            val response = todoReadResource.getAll()
            assertEquals(listOf(todo), response.entity)
        }

        @Test
        fun `test getAll todos throws exception`() {
            every { todoDao.getAll() } throws NotFoundException(NOT_FOUND_TODO_WITH_ID(id))
            val response = todoReadResource.getAll()
            assertEquals(Response.Status.INTERNAL_SERVER_ERROR.statusCode, response.status)
        }
    }


    @Nested
    inner class GetTodoById {

        @Test
        fun `test getTodoById when valid id is passed`() {
            every { todoDao.getTodoById(id) } returns todo
            val response = todoReadResource.getTodoById(id)
            assertEquals(todo, response.entity)
        }

        @Test
        fun `test getTodoById with invalid id`() {
            every { todoDao.getTodoById(id) } throws NotFoundException(NOT_FOUND_TODO_WITH_ID(id))
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