/**
 * It's a Copyright doc 2023
 */
package todoweb.service.impl

import io.dropwizard.hibernate.UnitOfWork

import todoweb.db.TaskDao
import todoweb.db.TodoDao
import todoweb.service.TodoDeleteService
import todoweb.utils.TodoAppException
import todoweb.utils.TodoConstants
import java.lang.RuntimeException

/**
 * Implementation of [TodoDeleteService] interface
 *
 * @author Syed Mohammad Mehdi
 */
class TodoDeleteServiceImpl(
    private val todoDao: TodoDao,
    private val taskDao: TaskDao
) : TodoDeleteService {

    @UnitOfWork
    override fun deleteTodoById(id: Long) {
        if (taskDao.deleteById(id) == 0) {
            throw TodoAppException(TodoConstants.NOT_FOUND_TODO_WITH_ID(id))
        }
        try {
            todoDao.deleteById(id)
        } catch (e: RuntimeException) {
            throw TodoAppException("Exception Occurred!")
        }
    }
}