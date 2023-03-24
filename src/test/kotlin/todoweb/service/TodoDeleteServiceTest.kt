/**
 * It's a Copyright doc 2023
 */
package todoweb.service

import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

import todoweb.db.TaskDao
import todoweb.db.TodoDao
import todoweb.service.impl.TodoDeleteServiceImpl
import todoweb.utils.TodoAppException

import java.lang.RuntimeException

/**
 * Test class for [TodoDeleteServiceImpl].
 *
 * @author Syed Mohammad Mehdi
 */
class TodoDeleteServiceTest {

    private val todoDao = mockk<TodoDao>()
    private val taskDao = mockk<TaskDao>()
    private val todoDeleteService = TodoDeleteServiceImpl(todoDao, taskDao)

    @BeforeEach
    fun setUp() {
        clearAllMocks()
    }

    @Test
    fun `test deleteTodoById when valid id is passed`() {
        every { taskDao.deleteById(id) } returns 1
        every { todoDao.deleteById(id) } just runs
        todoDeleteService.deleteTodoById(id)
        verify(exactly = 1)  { todoDao.deleteById(id) }
    }

    @Test
    fun `test deleteTodoById when invalid id is passed`() {
        every { taskDao.deleteById(id) } returns 0
        assertThrows<TodoAppException> { todoDeleteService.deleteTodoById(id) }
    }

    @Test
    fun `test deleteTodoById when exception occurs`() {
        every { taskDao.deleteById(id) } returns 1
        every { todoDao.deleteById(id) } throws RuntimeException("Test")
        assertThrows<TodoAppException> { todoDeleteService.deleteTodoById(id) }
    }

    companion object {
        const val id = 1L
    }
}