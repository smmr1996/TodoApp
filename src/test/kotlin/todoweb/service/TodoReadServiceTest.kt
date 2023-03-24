/**
 * It's a Copyright doc 2023
 */
package todoweb.service

import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

import todoweb.core.domain.Task
import todoweb.core.domain.Todo
import todoweb.db.TaskDao
import todoweb.db.TodoDao
import todoweb.service.impl.TodoReadServiceImpl
import todoweb.utils.TodoAppException

import java.lang.RuntimeException

/**
 * Test class for [TodoReadServiceImpl].
 *
 * @author Syed Mohammad Mehdi
 */
class TodoReadServiceTest {

    private val todoDao = mockk<TodoDao>()
    private val taskDao = mockk<TaskDao>()
    private val todoReadService = TodoReadServiceImpl(todoDao, taskDao)

    @BeforeEach
    fun setUp() {
        clearAllMocks()
    }

    @Nested
    inner class GetAllTest {

        @Test
        fun `test getAll todos`() {
            every { todoDao.findAll() } returns listOf(todo)
            every { taskDao.findById(id) } returns listOf(task)
            assertEquals(listOf(todo), todoReadService.getAll())
        }

        @Test
        fun `test getAll todos throws exception`() {
            every { todoDao.findAll() } returns listOf(todo)
            every { taskDao.findById(id) } throws RuntimeException(TEST_STRING)
            assertThrows<RuntimeException> { todoReadService.getAll() }
        }
    }


    @Nested
    inner class GetTodoById {

        @Test
        fun `test getTodoById when valid id is passed`() {
            every { todoDao.findById(id) } returns todo
            every { taskDao.findById(id) } returns listOf(task)
            assertEquals(todo, todoReadService.getTodoById(id))
        }

        @Test
        fun `test getTodoById with invalid id`() {
            every { todoDao.findById(id) } returns null
            assertThrows<TodoAppException> { todoReadService.getTodoById(id) }
        }

        @Test
        fun `test getTodoById when taskDao throws RuntimeException`() {
            every { todoDao.findById(id) } returns todo
            every { taskDao.findById(id) } throws RuntimeException(TEST_STRING)
            assertThrows<TodoAppException> { todoReadService.getTodoById(id) }
        }
    }

    companion object {
        private const val id = 2L
        private const val TEST_NAME = "name"
        private const val TEST_DESCRIPTION = "description"
        private const val TEST_STRING = "TEST"
        private val task = Task(TEST_NAME, TEST_DESCRIPTION)
        val todo = Todo(
            id,
            TEST_NAME,
            TEST_DESCRIPTION, listOf(task))
    }
}