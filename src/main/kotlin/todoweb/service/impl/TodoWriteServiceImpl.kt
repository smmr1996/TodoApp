package todoweb.service.impl

import todoweb.core.domain.Task
import todoweb.core.domain.Todo
import todoweb.db.TaskDbDao
import todoweb.db.TodoDbDao
import todoweb.service.TodoWriteService

class TodoWriteServiceImpl(
    private val todoDbDao: TodoDbDao,
    private val taskDbDao: TaskDbDao
) : TodoWriteService {

    override fun addTodo(todo: Todo): Todo {
        todoDbDao.insertTodo(todo)
        if (todo.tasks != null) {
            taskDbDao.insertTasks(todo.tasks as List<Task>, todo.id)
        }
        return todo
    }

    override fun updateTodoById(id: Long, todo: Todo): Todo {
        TODO("Not yet implemented")
    }


}