/**
 * It's a Copyright doc 2023
 */
package todoweb

import io.dropwizard.Application
import io.dropwizard.jdbi3.JdbiFactory
import io.dropwizard.setup.Bootstrap
import io.dropwizard.setup.Environment

import todoweb.db.TaskDao
import todoweb.db.TodoDao
import todoweb.resources.TodoDeleteResource
import todoweb.resources.TodoReadResource
import todoweb.resources.TodoWriteResource
import todoweb.resources.impl.TodoDeleteResourceImpl
import todoweb.resources.impl.TodoReadResourceImpl
import todoweb.resources.impl.TodoWriteResourceImpl
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

    override fun run(configuration: TodoWebConfiguration, environment: Environment) {
        val factory = JdbiFactory()
        val jdbi = factory.build(environment, configuration.database, "mysql")
        val todoDao = jdbi.onDemand(TodoDao::class.java)
        val taskDao = jdbi.onDemand(TaskDao::class.java)

        val todoReadService = TodoReadServiceImpl(todoDao, taskDao)
        val todoDeleteService = TodoDeleteServiceImpl(todoDao, taskDao)
        val todoWriteService = TodoWriteServiceImpl(todoDao, taskDao)

        val readResource: TodoReadResource = TodoReadResourceImpl(todoReadService)
        val writeResource: TodoWriteResource = TodoWriteResourceImpl(todoWriteService)
        val deleteResource: TodoDeleteResource = TodoDeleteResourceImpl(todoDeleteService)

        environment.jersey().register(readResource)
        environment.jersey().register(writeResource)
        environment.jersey().register(deleteResource)
    }

}

fun main(args: Array<String>) {
    TodoWebApplication().run(*args)
}

/**
 * TODO: Exception handling (DONE)
 * TODO: Writing UTs for services (DONE)
 * TODO: Builder/Factory for Jackson Mapper
 * TODO: Check the Documentation and file formatting (DONE)
 */