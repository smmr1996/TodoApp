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

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

import todoweb.core.domain.Task
import todoweb.core.domain.Todo
import todoweb.db.TaskDao
import todoweb.db.TodoDao
import todoweb.service.impl.TodoWriteServiceImpl
import todoweb.utils.TodoAppException

/**
 * Test class for [TodoWriteServiceImpl].
 *
 * @author Syed Mohammad Mehdi
 */
class TodoWriteServiceTest {

    private val todoDao: TodoDao = mockk()

    private val taskDao: TaskDao = mockk()

    private val todoWriteService = TodoWriteServiceImpl(todoDao, taskDao)

    @BeforeEach
    fun setUp() {
        clearAllMocks()
    }

    @Nested
    inner class AddTodo {

        @Test
        fun `test addTodo success`() {
            every { todoDao.insertTodo(todo) } returns id
            every {taskDao.insertTasks(listOf(task), id) } just runs
            val result = todoWriteService.addTodo(todo)
            assertEquals(todo, result)
        }

        @Test
        fun `test addTodo fails`() {
            every { todoDao.insertTodo(todo) } throws RuntimeException(TEST_STRING)
            assertThrows<RuntimeException> { todoWriteService.addTodo(todo) }
        }

        @Test
        fun `test addTodo with no tasks`() {
            val todoCopy = todo.copy()
            todoCopy.tasks = null
            every { todoDao.insertTodo(todoCopy) } returns id
            assertEquals(todoCopy, todoWriteService.addTodo(todoCopy))

        }
    }

    @Nested
    inner class UpdateTodoWithId {

        @Test
        fun `test updateTodoWithId success`() {
            every { todoDao.updateById(todo, id) } returns 1
            every { taskDao.deleteById(id) } returns 1
            every { taskDao.insertTasks(listOf(task), id) } just runs
            todoWriteService.updateTodoById(id, todo)
            verify(exactly = 1) { taskDao.insertTasks(listOf(task), id) }
        }

        @Test
        fun `test updateTodoWithId fails with invalid id`() {
            every { todoDao.updateById(todo, id) } returns 0
            assertThrows<TodoAppException> { todoWriteService.updateTodoById(id, todo) }
        }

        @Test
        fun `test updateTodoWithId with no tasks`() {
            val todoCopy = todo.copy()
            todoCopy.tasks = null
            every { todoDao.updateById(todoCopy, id) } returns 1
            every { taskDao.deleteById(id) } returns 1
            verify(exactly = 0) { taskDao.insertTasks(listOf(task), id) }
        }

        @Test
        fun `test updateTodoWithId fails with RuntimeException`() {
            every { todoDao.updateById(todo, id) } returns 1
            every { taskDao.deleteById(id) } throws RuntimeException(TEST_STRING)
            assertThrows<TodoAppException> { todoWriteService.updateTodoById(id, todo) }
        }
    }


    companion object {
        private const val id = 0L
        private const val TEST_STRING = "TEST"
        private const val TEST_NAME = "name"
        private const val TEST_DESCRIPTION = "description"
        private val task = Task(TEST_NAME, TEST_DESCRIPTION)
        private val todo = Todo(
            id,
            TEST_NAME,
            TEST_DESCRIPTION, listOf(task)
        )
    }
}