/**
 * It's a Copyright doc 2023
 */
package todoweb

import io.dropwizard.Application
import io.dropwizard.jdbi3.JdbiFactory
import io.dropwizard.setup.Bootstrap
import io.dropwizard.setup.Environment
import todoweb.db.TaskDbDao

import todoweb.db.TodoDao
import todoweb.db.TodoDbDao
import todoweb.resources.TodoDeleteResource
import todoweb.resources.TodoReadResource
import todoweb.resources.TodoWriteResource
import todoweb.resources.impl.TodoDeleteResourceImpl
import todoweb.resources.impl.TodoReadResourceImpl
import todoweb.resources.impl.TodoWriteResourceImpl
import todoweb.service.TodoDeleteService
import todoweb.service.TodoReadService
import todoweb.service.TodoWriteService
import todoweb.service.impl.TodoDeleteServiceImpl
import todoweb.service.impl.TodoReadServiceImpl
import todoweb.service.impl.TodoWriteServiceImpl


/**
 * To-Do Web Application. This application performs basic CRUD operations for To-Do.
 *
 * @author Syed Mohammad Mehdi
 */
class TodoWebApplication : Application<TodoWebConfiguration>() {

    override fun getName(): String {
        return "Todo Web Application"
    }

    override fun initialize(bootstrap: Bootstrap<TodoWebConfiguration>) {
        // TODO: application initialization
    }

    override fun run(configuration: TodoWebConfiguration,
                     environment: Environment) {
        val factory = JdbiFactory()
        val jdbi = factory.build(environment, configuration.database, "mysql")
        val todoDbDao = jdbi.onDemand(TodoDbDao::class.java)
        val taskDbDao = jdbi.onDemand(TaskDbDao::class.java)
        val dao = TodoDao()
        val todoReadService : TodoReadService = TodoReadServiceImpl(todoDbDao, taskDbDao)
        val todoDeleteService : TodoDeleteService = TodoDeleteServiceImpl(todoDbDao, taskDbDao)
        val todoWriteService : TodoWriteService = TodoWriteServiceImpl(todoDbDao, taskDbDao)
        val readResource : TodoReadResource = TodoReadResourceImpl(todoReadService)
        val writeResource : TodoWriteResource = TodoWriteResourceImpl(todoWriteService)
        val deleteResource : TodoDeleteResource = TodoDeleteResourceImpl(todoDeleteService)
        environment.jersey().register(readResource)
        environment.jersey().register(writeResource)
        environment.jersey().register(deleteResource)
    }

}

fun main(args: Array<String>) {
    TodoWebApplication().run(*args)
}

/*
Todo: 1. Exception Handling, 2. Builder for JacksonMapper, 3. Unit Tests for Service Classes,
4. Checking the docs of all the files
 */