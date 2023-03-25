/**
 * It's a Copyright doc 2023
 */
package todoweb.service.impl

import io.dropwizard.hibernate.UnitOfWork

import todoweb.core.domain.Todo
import todoweb.db.TaskDao
import todoweb.db.TodoDao
import todoweb.service.TodoWriteService
import todoweb.utils.TodoAppException
import todoweb.utils.TodoConstants

import java.lang.RuntimeException

/**
 * Implementation of [TodoWriteService] interface
 *
 * @author Syed Mohammad Mehdi
 */
class TodoWriteServiceImpl(
    private val todoDao: TodoDao,
    private val taskDao: TaskDao
) : TodoWriteService {

    @UnitOfWork
    override fun addTodo(todo: Todo): Todo {
        val id = todoDao.insertTodo(todo)
        todo.id = id
        if (!todo.tasks.isNullOrEmpty()) {
            taskDao.insertTasks(todo.tasks!!, id)
        }
        return todo
    }

    @UnitOfWork
    override fun updateTodoById(id: Long, todo: Todo) {
        if (todoDao.updateById(todo, id) == 0) {
            throw TodoAppException(TodoConstants.NOT_FOUND_TODO_WITH_ID(id))
        }
        try {
            taskDao.deleteById(id)
            todo.tasks?.let { taskDao.insertTasks(it, id) }
        } catch (e: RuntimeException) {
            throw TodoAppException("Exception occurred: ${e.message}")
        }
    }
}