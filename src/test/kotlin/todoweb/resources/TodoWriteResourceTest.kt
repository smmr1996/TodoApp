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
import todoweb.resources.impl.TodoWriteResourceImpl
import todoweb.service.TodoWriteService

import javax.ws.rs.NotFoundException
import javax.ws.rs.core.Response

/**
 * Test class for [TodoWriteResourceImpl].
 *
 * @author Syed Mohammad Mehdi
 */
class TodoWriteResourceTest {

    private val todoWriteService = mockk<TodoWriteService>()
    private val todoWriteResource = TodoWriteResourceImpl(todoWriteService)

    @BeforeEach
    fun setUp() {
        clearAllMocks()
    }

    @Nested
    inner class AddTodo {

        @Test
        fun `test addTodo success`() {
            every { todoWriteService.addTodo(todo) } returns todo
            val response = todoWriteResource.addTodo(todoJson)
            assertEquals(todo, response.entity)
        }

        @Test
        fun `test addTodo fails`() {
            every { todoWriteService.addTodo(todo) } throws Exception("E")
            val response = todoWriteResource.addTodo(todoJson)
            assertEquals(Response.Status.INTERNAL_SERVER_ERROR.statusCode, response.status)
        }
    }

    @Nested
    inner class UpdateTodoWithId {

        @Test
        fun `test updateTodoWithId success`() {
            every { todoWriteService.updateTodoById(id, todo) } returns todo
            val response = todoWriteResource.updateTodoById(id, todoJson)
            assertEquals(todo, response.entity)
        }

        @Test
        fun `test updateTodoWithId fails`() {
            every { todoWriteService.updateTodoById(id, todo) } throws
                    NotFoundException(NOT_FOUND_TODO_WITH_ID(id))
            val response = todoWriteResource.updateTodoById(id, todoJson)
            assertEquals(NOT_FOUND_TODO_WITH_ID(id), response.entity)
        }
    }


    companion object {
        private const val id = 0L
        private const val TEST_NAME = "name"
        private const val TEST_DESCRIPTION = "description"
        val NOT_FOUND_TODO_WITH_ID = {id: Long -> "Could not find ToDo with id: $id"}
        val todo = Todo(
            id,
            TEST_NAME,
            TEST_DESCRIPTION, listOf(Task(0L, TEST_NAME, TEST_DESCRIPTION)))
        val todoJson = """
            {
                "name": "$TEST_NAME",
                "description": "$TEST_DESCRIPTION",
                "tasks": [
                    {
                        "name": "$TEST_NAME",
                        "description": "$TEST_DESCRIPTION"
                    }
                ]
            }
        """.trimIndent()
    }
}