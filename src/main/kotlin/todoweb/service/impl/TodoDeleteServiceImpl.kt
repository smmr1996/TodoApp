package todoweb.service.impl

import todoweb.db.TaskDbDao
import todoweb.db.TodoDbDao
import todoweb.service.TodoDeleteService

class TodoDeleteServiceImpl(
    private val todoDbDao: TodoDbDao,
    private val taskDbDao: TaskDbDao
) : TodoDeleteService {

    override fun deleteTodoById(id: Long) {
        taskDbDao.deleteById(id)
        todoDbDao.deleteById(id)
    }

}