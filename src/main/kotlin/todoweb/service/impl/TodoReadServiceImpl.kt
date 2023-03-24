/**
 * It's a Copyright doc 2023
 */
package todoweb.service.impl

import todoweb.core.domain.Todo
import todoweb.db.TaskDao
import todoweb.db.TodoDao
import todoweb.service.TodoReadService
import todoweb.utils.TodoAppException
import todoweb.utils.TodoConstants

import java.lang.RuntimeException

/**
 * Implementation of [TodoReadService] interface
 *
 * @author Syed Mohammad Mehdi
 */
class TodoReadServiceImpl(
    private val todoDao: TodoDao,
    private val taskDao: TaskDao
) : TodoReadService {

    override fun getAll(): List<Todo> {
        return try{
            val todoList = todoDao.findAll()
            todoList.forEach {
                val taskList = taskDao.findById(it.id)
                it.tasks = taskList
            }
            todoList
        } catch (e: RuntimeException) {
            throw TodoAppException("Error Occurred: ${e.message}")
        }
    }

    override fun getTodoById(id: Long): Todo {
        val todo = todoDao.findById(id)
            ?: throw TodoAppException(TodoConstants.NOT_FOUND_TODO_WITH_ID(id))
        return try {
            todo.tasks = taskDao.findById(id)
            todo
        } catch (e: RuntimeException) {
            throw TodoAppException("Exception occurred!")
        }
    }
}