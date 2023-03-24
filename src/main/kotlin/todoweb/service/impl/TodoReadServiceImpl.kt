package todoweb.service.impl

import todoweb.core.domain.Todo
import todoweb.db.TaskDbDao
import todoweb.db.TodoDbDao
import todoweb.service.TodoReadService

class TodoReadServiceImpl(
    private val todoDbDao: TodoDbDao,
    private val taskDbDao: TaskDbDao
) : TodoReadService {

    override fun getAll(): List<Todo> {
        val todoList = todoDbDao.findAll()
        todoList.forEach {
            val taskList = taskDbDao.findById(it.id)
            it.tasks = taskList
        }
        return todoList
    }

    override fun getTodoById(id: Long): Todo {
        val todo = todoDbDao.findById(id)
        todo.tasks = taskDbDao.findById(id)
        return todo
    }
}