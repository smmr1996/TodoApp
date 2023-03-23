/**
 * It's a Copyright doc 2023
 */
package todoweb

import io.dropwizard.Application
import io.dropwizard.setup.Bootstrap
import io.dropwizard.setup.Environment

import todoweb.db.TodoDao
import todoweb.resources.TodoDeleteResource
import todoweb.resources.TodoReadResource
import todoweb.resources.TodoWriteResource
import todoweb.resources.impl.TodoDeleteResourceImpl
import todoweb.resources.impl.TodoReadResourceImpl
import todoweb.resources.impl.TodoWriteResourceImpl


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
        val dao = TodoDao()
        val readResource : TodoReadResource = TodoReadResourceImpl(dao)
        val writeResource : TodoWriteResource = TodoWriteResourceImpl(dao)
        val deleteResource : TodoDeleteResource = TodoDeleteResourceImpl(dao)
        environment.jersey().register(readResource)
        environment.jersey().register(writeResource)
        environment.jersey().register(deleteResource)
    }
}

fun main(args: Array<String>) {
    TodoWebApplication().run(*args)
}